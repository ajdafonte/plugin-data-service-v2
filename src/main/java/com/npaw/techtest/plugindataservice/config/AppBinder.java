package com.npaw.techtest.plugindataservice.config;

import org.glassfish.jersey.internal.inject.AbstractBinder;

import com.npaw.techtest.plugindataservice.pluginconfig.DefaultPluginConfigService;
import com.npaw.techtest.plugindataservice.pluginconfig.PluginConfigService;
import com.npaw.techtest.plugindataservice.plugindata.bizz.DefaultPluginDataService;
import com.npaw.techtest.plugindataservice.plugindata.bizz.PluginDataService;


/**
 *
 */
public class AppBinder extends AbstractBinder
{
    @Override
    protected void configure()
    {
        bind(DefaultPluginConfigService.class).to(PluginConfigService.class);
        bind(DefaultPluginDataService.class).to(PluginDataService.class);
    }
}
