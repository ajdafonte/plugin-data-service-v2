package com.npaw.techtest.plugindataservice.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.npaw.techtest.plugindataservice.plugindata.rest.PluginDataRestResource;


@ApplicationPath("/api")
public class AppConfig extends ResourceConfig
{
    public AppConfig()
    {
        // register resources (controllers)
        register(PluginDataRestResource.class);

        // define dependency injection
        register(new AppBinder());
    }
}
