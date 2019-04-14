package com.npaw.techtest.plugindataservice.pluginconfig.bizz;

import java.util.List;
import java.util.Optional;

import com.npaw.techtest.plugindataservice.pluginconfig.domain.PluginConfigData;


public interface PluginConfigService
{
    /**
     * Find a plugin configuration data considering some parameters.
     *
     * @param accountCode The account code of the client.
     * @param targetDevice The target device.
     * @param pluginVersion The plugin version.
     * @return the {@link PluginConfigData} object or an empty Optional if no plugin configuration was found.
     */
    Optional<PluginConfigData> findPluginConfig(final String accountCode, final String targetDevice, final String pluginVersion);

    /**
     * Retrieve the configuration of a certain client.
     *
     * @param accountCode The account code of the client.
     * @return list of {@link PluginConfigData} that belongs to a certain client or an empty Optional if no configuration was found.
     */
    Optional<List<PluginConfigData>> getPluginConfigByClient(final String accountCode);
}
