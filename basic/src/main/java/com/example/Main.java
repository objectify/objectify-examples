package com.example;

import com.googlecode.objectify.Key;
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
        // Initialize the singleton factory
        ObjectifyService.init();

        // Register any entity classes you intend to use
        ObjectifyService.factory().register(Thing.class);

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
