Demonstrates using multiple datastores with Objectify

In this example, we do not use the `ObjectifyService` class at all. We maintain two `ObjectifyFactory` instances, one for each datastore.

Keep the contract of calling `run()` to start a session and call `ofy()` on the correct
factory instance. You can open multiple sessions concurrently.