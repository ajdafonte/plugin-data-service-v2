package com.npaw.techtest.plugindataservice.pluginconfig;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;

import com.npaw.techtest.plugindataservice.common.PluginConfigData;


public class DefaultPluginConfigService implements PluginConfigService
{
    @Context
    private Configuration configuration;

    @Override
    public Optional<List<PluginConfigData>> getPluginConfigByClient(final String accountCode)
    {
        final ConcurrentHashMap<String, List<PluginConfigData>> clientsConfig =
            (ConcurrentHashMap<String, List<PluginConfigData>>) configuration.getProperty("clientsConfig");
        return Optional.ofNullable(clientsConfig.get(accountCode));
    }

    @Override
    public Optional<PluginConfigData> findPluginConfig(final String accountCode, final String targetDevice, final String pluginVersion)
    {
        // retrieve client configuration
        final Optional<List<PluginConfigData>> clientPluginConfigs = getPluginConfigByClient(accountCode);

        // check if targetDevice and pluginVersion belongs to specified client
        final Predicate<PluginConfigData> filterPredicate =
            pluginConfig -> pluginConfig.getTargetDevice().equals(targetDevice) && pluginConfig.getPluginVersion().equals(pluginVersion);

        return clientPluginConfigs.flatMap(pluginConfigs -> pluginConfigs.stream().filter(filterPredicate).findFirst());
    }
}
