package com.npaw.techtest.plugindataservice.plugindata.bizz;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import com.npaw.techtest.plugindataservice.common.HostConfigData;
import com.npaw.techtest.plugindataservice.common.PluginConfigData;
import com.npaw.techtest.plugindataservice.pluginconfig.PluginConfigService;
import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;


public class DefaultPluginDataService implements PluginDataService
{
    private final PluginConfigService pluginConfigService;

    @Inject
    public DefaultPluginDataService(final PluginConfigService pluginConfigService)
    {
        this.pluginConfigService = pluginConfigService;
    }

    @Override
    public PluginData getPluginData(final GetPluginDataParameter parameter)
    {
        final Optional<PluginConfigData> clientDevice = pluginConfigService.findPluginConfig(parameter.getAccountCode(),
            parameter.getTargetDevice(), parameter.getPluginVersion());

        return clientDevice.map(pluginConfig ->
            new PluginData(getHost(pluginConfig.getHosts()).getName(), pluginConfig.getPingTime(), UUID.randomUUID().toString())).orElse(null);
    }

    private HostConfigData getHost(final List<HostConfigData> hosts)
    {
        final int rand = ThreadLocalRandom.current().nextInt(100);
        int prob = 0;
        for (final HostConfigData host : hosts)
        {
            prob += host.getLoad();
            if (rand <= prob)
            {
                return host;
            }
        }
        return hosts.get(0);
    }

}
