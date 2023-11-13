package com.example;

import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Startup implements ServletContextListener {
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        // Register any entity classes you intend to use
        ObjectifyService.factory().register(Thing.class);
    }
}
