package com.npaw.techtest.plugindataservice.config;

import org.glassfish.jersey.internal.inject.AbstractBinder;

import com.npaw.techtest.plugindataservice.pluginconfig.bizz.DefaultPluginConfigService;
import com.npaw.techtest.plugindataservice.pluginconfig.bizz.PluginConfigService;
import com.npaw.techtest.plugindataservice.pluginconfig.repo.DefaultPluginConfigRepository;
import com.npaw.techtest.plugindataservice.pluginconfig.repo.PluginConfigRepository;
import com.npaw.techtest.plugindataservice.plugindata.bizz.DefaultPluginDataService;
import com.npaw.techtest.plugindataservice.plugindata.bizz.PluginDataService;


public class AppBinder extends AbstractBinder
{
    @Override
    protected void configure()
    {
        bind(DefaultPluginConfigRepository.class).to(PluginConfigRepository.class);
        bind(DefaultPluginConfigService.class).to(PluginConfigService.class);
        bind(DefaultPluginDataService.class).to(PluginDataService.class);
    }
}
