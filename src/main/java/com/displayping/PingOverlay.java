package com.displayping;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.api.events.FocusChanged;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.plugins.fps.FpsConfig;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;


/**
 * This is meant to show the ping to the connected world similarly to the FPS plugin
 */

public class PingOverlay extends Overlay{

    private static final int Y_OFFSET = 1;
    private static final int X_OFFSET = 1;
    private static final String PING_STRING = "Ping";
    private static final int HIGH_LOW_BOUNDARY = 70;


    @Inject
    private PingOverlay(){
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPriority(OverlayPriority.HIGH);
        setPosition(OverlayPosition.DYNAMIC);
    }

    private boolean isPingHigh(){ return true;}

    private Color getPingValueColor(){return isPingHigh()? Color.red : Color.green;}

    @Override
    public Dimension render(Graphics2D graphics) {
        Widget logoutButton = client.getWidget(WidgetInfo.RESIZABLE_MINIMAP_LOGOUT_BUTTON);
        int xOffset = X_OFFSET;
        if (logoutButton != null && !logoutButton.isHidden())
        {
            xOffset += logoutButton.getWidth();
        }

        final String text = client.getFPS() + PING_STRING;
        final int textWidth = graphics.getFontMetrics().stringWidth(text);
        final int textHeight = graphics.getFontMetrics().getAscent() - graphics.getFontMetrics().getDescent();

        final int width = (int) client.getRealDimensions().getWidth();
        final Point point = new Point(width - textWidth - xOffset, textHeight + Y_OFFSET);
        OverlayUtil.renderTextLocation(graphics, point, text, getPingValueColor());

        return null;
    }
}
