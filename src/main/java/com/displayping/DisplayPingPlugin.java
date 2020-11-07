package com.displayping;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.worldhopper.ping.Ping;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "DisplayPing"
)
public class DisplayPingPlugin extends Plugin
{
	static final String CONFIG_GROUP_KEY = "displaypingcontrol";

	@Inject
	private Client client;

	@Inject
	private DisplayPingConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private PingOverlay overlay;

	@Provides
	DisplayPingConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DisplayPingConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event){
		if(event.getGroup().equals(CONFIG_GROUP_KEY))
			overlay.reloadConfig();
	}

	@Override
	protected void startUp() throws Exception {
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	public static int ping(net.runelite.http.api.worlds.World world){
		int ping = net.runelite.client.plugins.worldhopper.ping.Ping.ping(world);
		return ping;
	}

	//all world numbers seem to be between 301 and 570ish, so this makes sure that the client has a valid world
	public static boolean validWorld(int worldNum) {
		return (worldNum > 300 && worldNum < 600);
	}

}
