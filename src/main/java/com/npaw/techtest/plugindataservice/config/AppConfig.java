package com.npaw.techtest.plugindataservice.config;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.npaw.techtest.plugindataservice.plugindata.rest.PluginDataRestResource;


@ApplicationPath("/api")
public class AppConfig extends ResourceConfig
{
    public AppConfig()
    {
        //
        register(PluginDataRestResource.class);

        //
        register(new AppBinder());

        //
        final Map<String, Object> props = new HashMap<>();
        props.put("clientsConfig", ClientsPluginConfiguration.getMyConfigData());
        addProperties(props);
    }
}
