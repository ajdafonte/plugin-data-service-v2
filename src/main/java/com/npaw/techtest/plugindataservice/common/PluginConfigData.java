package com.npaw.techtest.plugindataservice.common;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PluginConfigData
{
    private String targetDevice;
    private String pluginVersion;
    private int pingTime = 5;
    private List<HostConfigData> hosts;
}
