package com.npaw.techtest.plugindataservice.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.npaw.techtest.plugindataservice.common.PluginConfigData;


class ClientsPluginConfiguration
{
    private static final ConcurrentHashMap<String, List<PluginConfigData>> myConfigData;

    static
    {
        try (final Reader reader = new InputStreamReader(ClientsPluginConfiguration.class.getResourceAsStream("/clients-config.json")))
        {
            final ObjectMapper mapper = new ObjectMapper();
            myConfigData = mapper.readValue(reader, new TypeReference<ConcurrentHashMap<String, List<PluginConfigData>>>()
            {
            });
        }
        catch (final IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    static ConcurrentHashMap<String, List<PluginConfigData>> getMyConfigData()
    {
        return myConfigData;
    }
}
