Demonstrates using Objectify in a simple GAE web application.

This example uses a singleton ObjectifyFactory instance in the ObjectifyService class.

The important things to note:

1. Call `ObjectifyService.init()` and register your entity classes in a servlet context listener
2. Wrap all requests in the `ObjectifyService$Filter` (or `FilterJavax` if using `javax.servlet` instead of `jakarta.servlet`).