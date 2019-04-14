package com.npaw.techtest.plugindataservice.plugindata.rest;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.npaw.techtest.plugindataservice.plugindata.bizz.GetPluginDataParameter;
import com.npaw.techtest.plugindataservice.plugindata.bizz.PluginDataService;
import com.npaw.techtest.plugindataservice.plugindata.rest.mapper.PluginDataRestMapper;


@Path(value = "/pluginData")
public class PluginDataRestResource
{
    private final PluginDataService pluginDataService;

    @Inject
    public PluginDataRestResource(final PluginDataService pluginDataService)
    {
        this.pluginDataService = pluginDataService;
    }

    @GET
    @Produces(value = MediaType.APPLICATION_XML)
    public PluginDataRest getPluginData(
        @QueryParam("accountCode") @NotBlank(message = "Account code must not be blank") final String accountCode,
        @QueryParam("targetDevice") @NotBlank(message = "Target device must not be blank") final String targetDevice,
        @QueryParam("pluginVersion") @NotBlank(message = "Plugin version must not be blank") final String pluginVersion)
    {
        final GetPluginDataParameter parameter = new GetPluginDataParameter(accountCode, targetDevice, pluginVersion);
        return PluginDataRestMapper.map(pluginDataService.getPluginData(parameter));
    }
}
