package com.example;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import lombok.extern.slf4j.Slf4j;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Simplest possible use of Objectify. Uses the ObjectifyService singleton, assuming
 * you only want to use a single datastore.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        // Initialize the singleton factory, using the default discovery rules. The default rules are roughly:
        //  * If you're running inside GAE, the projectId will be automatically obtained
        //  * If you aren't running inside GAE, the projectId will come from the GOOGLE_CLOUD_PROJECT environment variable
        //  * The default datastore "(default)" will be used
        ObjectifyService.init();

        /* Alternatively, you can explicitly configure the datastore
        final Datastore datastore = DatastoreOptions.newBuilder()
                .setProjectId("my-project")
                .setDatabaseId("my-datastore")
                .build().getService();
        ObjectifyService.init(new ObjectifyFactory(datastore));
         */

        // Register any entity classes you intend to use
        ObjectifyService.register(Thing.class);

        // To use Objectify, you must create a session of work. Sessions should be short-lived.
        ObjectifyService.run(Main::someWork);
        ObjectifyService.run(Main::someMoreWork);
    }

    private static void someWork() {
        final Key<Thing> bobKey = ofy().save().entity(new Thing(null, "bob")).now();

        final Thing bob = ofy().load().key(bobKey).now();

        log.info("Thing is: {}", bob);
    }

    private static void someMoreWork() {
        final Thing bob = ofy().load().type(Thing.class).filter("name", "bob").first().now();

        log.info("Thing is: {}", bob);
    }
}
