package com.npaw.techtest.plugindataservice.pluginconfig.repo;

import java.util.List;
import java.util.Optional;

import com.npaw.techtest.plugindataservice.pluginconfig.domain.PluginConfigData;


public interface PluginConfigRepository
{
    /**
     * Find a plugin configuration data considering the account code of the client.
     *
     * @param accountCode The account code of the client.
     * @return the {@link PluginConfigData} object or an empty Optional if no plugin configuration was found.
     */
    Optional<List<PluginConfigData>> findPluginConfigBy(String accountCode);
}
