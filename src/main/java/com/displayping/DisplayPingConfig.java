package com.displayping;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(DisplayPingPlugin.CONFIG_GROUP_KEY)
public interface DisplayPingConfig extends Config
{
    @ConfigItem(
            position = 1,
            keyName = "highLowPing",
            name = "High Low Ping",
            description = "Chooses the boundary for deciding the color between high and low ping"
    )
    default int highLowPing(){return 65;}
}
