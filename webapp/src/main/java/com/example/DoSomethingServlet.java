package com.example;

import com.googlecode.objectify.Key;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Slf4j
public class DoSomethingServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final Key<Thing> bobKey = ofy().save().entity(new Thing(null, "bob")).now();

        final Thing bob = ofy().load().key(bobKey).now();

        resp.getWriter().println("Thing is " + bob);

        log.debug("Created {}", bob);
    }
}
