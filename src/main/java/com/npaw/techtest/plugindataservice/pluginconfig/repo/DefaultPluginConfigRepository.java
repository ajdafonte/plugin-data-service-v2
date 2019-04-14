package com.npaw.techtest.plugindataservice.pluginconfig.repo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.npaw.techtest.plugindataservice.pluginconfig.domain.PluginConfigData;


public class DefaultPluginConfigRepository implements PluginConfigRepository
{
    private static final String CLIENTS_CONFIG_FILENAME = "/clients-config.json";
    private static final ConcurrentHashMap<String, List<PluginConfigData>> CLIENTS_PLUGIN_CONFIG;

    static
    {
        try (final Reader reader = new InputStreamReader(DefaultPluginConfigRepository.class.getResourceAsStream(CLIENTS_CONFIG_FILENAME)))
        {
            final ObjectMapper mapper = new ObjectMapper();
            CLIENTS_PLUGIN_CONFIG = mapper.readValue(reader, new TypeReference<ConcurrentHashMap<String, List<PluginConfigData>>>()
            {
            });
        }
        catch (final IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<PluginConfigData>> findPluginConfigBy(final String accountCode)
    {
        return accountCode == null ? Optional.empty() : Optional.ofNullable(CLIENTS_PLUGIN_CONFIG.get(accountCode));
    }
}
