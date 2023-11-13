Demonstrates using Objectify v5 (legacy version) in a simple GAE web application. 

Objectify v5 uses the legacy datastore interface that was introduced in the original Google App Engine
service. This requires the 

This example uses a singleton ObjectifyFactory instance in the ObjectifyService class.

The important things to note:

1. Include `com.google.appengine:appengine-api-1.0-sdk` v2+ on the classpath.
2. Add `<app-engine-apis>true</app-engine-apis>` to `appengine-web.xml` to enable the legacy datastore API.
3. Register your entity classes in a servlet context listener.
4. Wrap all requests in the `ObjectifyFilter`.

`ObjectifyFilter` is based on the `javax.servlet` classes. If you are using a more modern container which
uses the `jakarta.servlet` classes, you will need to construct your own `ObjectifyFilter`. That's ok! It's
almost a one-liner:

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try (Closeable closeable = ObjectifyService.begin()) {
			chain.doFilter(request, response);
		}
	}
