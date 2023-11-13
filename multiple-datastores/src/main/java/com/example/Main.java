package com.example;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Demonstrate using multiple datastores (and thus multiple ObjectifyFactory intances).
 * Does NOT use the ObjectifyService class; you can ignore it.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        final Main main = new Main();
        main.run();
    }

    private final ObjectifyFactory factory1;
    private final ObjectifyFactory factory2;

    public Main() {
        final Datastore datastore1 = DatastoreOptions.newBuilder().setDatabaseId("datastore1").build().getService();
        this.factory1 = new ObjectifyFactory(datastore1);
        this.factory1.register(Thing.class);

        final Datastore datastore2 = DatastoreOptions.newBuilder().setDatabaseId("datastore2").build().getService();
        this.factory2 = new ObjectifyFactory(datastore2);
        this.factory2.register(OtherThing.class);
    }

    public void run() {
        factory1.run(() -> {
            final Key<Thing> bobKey = factory1.ofy().save().entity(new Thing(null, "bob")).now();
            final Thing bob = factory1.ofy().load().key(bobKey).now();
            log.info("Thing is: {}", bob);
        });

        factory2.run(() -> {
            final Key<OtherThing> nedKey = factory2.ofy().save().entity(new OtherThing(null, "ned")).now();
            final OtherThing ned = factory2.ofy().load().key(nedKey).now();
            log.info("OtherThing is: {}", ned);
        });
    }
}
