package com.npaw.techtest.plugindataservice.pluginconfig.bizz;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.inject.Inject;

import com.npaw.techtest.plugindataservice.pluginconfig.domain.PluginConfigData;
import com.npaw.techtest.plugindataservice.pluginconfig.repo.PluginConfigRepository;


public class DefaultPluginConfigService implements PluginConfigService
{
    private final PluginConfigRepository pluginConfigRepository;

    @Inject
    public DefaultPluginConfigService(final PluginConfigRepository pluginConfigRepository)
    {
        this.pluginConfigRepository = pluginConfigRepository;
    }

    @Override
    public Optional<List<PluginConfigData>> getPluginConfigByClient(final String accountCode)
    {
        return pluginConfigRepository.findPluginConfigBy(accountCode);
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
