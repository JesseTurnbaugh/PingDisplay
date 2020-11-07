package com.displayping;

import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.plugins.worldhopper.ping.Ping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.awt.Color;

class DisplayPingTests {
    @Test
    @DisplayName("Says true/false if ping is high")
    void IsPingHigh() {
        int[] pings = {20, 100};
        Assertions.assertAll(() -> Assertions.assertTrue(PingOverlay.isPingHigh(pings[1])),
                             () -> Assertions.assertFalse(PingOverlay.isPingHigh(pings[0])));
    }

    @Test
    @DisplayName("Makes sure that the ping number outputs the correct color")
    void CorrectColors() {
        int[] pings = {20, 100};
        Assertions.assertAll(() -> Assertions.assertTrue((PingOverlay.getPingValueColor(pings[0])) ==  Color.green),
                             () -> Assertions.assertTrue((PingOverlay.getPingValueColor(pings[1])) ==  Color.red));
    }

    @Test
    @DisplayName("Says true/false if world is valid")
    void IsWorldValid() {
        int[] worldNum = {20, 301};
        Assertions.assertAll(() -> Assertions.assertTrue(DisplayPingPlugin.validWorld(worldNum[1])),
                             () -> Assertions.assertFalse(DisplayPingPlugin.validWorld(worldNum[0])));
    }

    @Test
    @DisplayName("Find if the Ping Indicator is in the correct location")
    void CorrectLocation() {
        Assertions.assertTrue((PingOverlay.X_OFFSET == 85 && PingOverlay.Y_OFFSET == 1));
    }

    @Test
    @DisplayName("Makes sure color is never yellow, because thats ugly")
    void OnlyValidColors() {
        int[] pings = {0, 100, 95, 69, 28, 33, 41};
        Assertions.assertAll(() -> Assertions.assertFalse((PingOverlay.getPingValueColor(pings[0])) ==  Color.yellow),
                             () -> Assertions.assertFalse((PingOverlay.getPingValueColor(pings[1])) ==  Color.yellow),
                             () -> Assertions.assertFalse((PingOverlay.getPingValueColor(pings[2])) ==  Color.yellow),
                             () -> Assertions.assertFalse((PingOverlay.getPingValueColor(pings[3])) ==  Color.yellow),
                             () -> Assertions.assertFalse((PingOverlay.getPingValueColor(pings[4])) ==  Color.yellow),
                             () -> Assertions.assertFalse((PingOverlay.getPingValueColor(pings[5])) ==  Color.yellow),
                             () -> Assertions.assertFalse((PingOverlay.getPingValueColor(pings[6])) ==  Color.yellow));
    }
}
