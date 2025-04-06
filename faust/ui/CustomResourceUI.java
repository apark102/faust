package faust.ui;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import faust.util.CustomResourceManager;
import faust.util.CustomResourceManager.ResourceType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.EnumMap;
import com.badlogic.gdx.graphics.Texture;
public class CustomResourceUI {
    private static final float START_X = 40 * Settings.scale;
    private static final float START_Y = Settings.HEIGHT - 250 * Settings.scale;
    private static final float ICON_SPACING = 60 * Settings.scale;

    private final EnumMap<ResourceType, Texture> icons = new EnumMap<>(ResourceType.class);

    public CustomResourceUI() {
        // Load icons from your resources folder
        icons.put(ResourceType.PRIDE, new Texture("faust/images/ui/pride.png"));
        icons.put(ResourceType.GLOOM, new Texture("faust/images/ui/gloom.png"));
        icons.put(ResourceType.LUST, new Texture("faust/images/ui/lust.png"));
        icons.put(ResourceType.WRATH, new Texture("faust/images/ui/wrath.png"));
        icons.put(ResourceType.GLUTTONY, new Texture("faust/images/ui/gluttony.png"));
        icons.put(ResourceType.SLOTH, new Texture("faust/images/ui/sloth.png"));
        icons.put(ResourceType.ENVY, new Texture("faust/images/ui/envy.png"));
    }

    public void render(SpriteBatch sb) {
        if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
            float y = START_Y;
        for (ResourceType type : ResourceType.values()) {
            Texture icon = icons.get(type);
            int amount = CustomResourceManager.getResourceAmount(type);

            // Draw icon
            sb.draw(icon, START_X, y, 48 * Settings.scale, 48 * Settings.scale);

            // Draw amount using Slay the Spire's font
            FontHelper.renderFontLeftTopAligned(
                    sb, FontHelper.energyNumFontRed,
                    String.valueOf(amount),
                    START_X + 50 * Settings.scale,  // a little to the right of the icon
                    y + 30 * Settings.scale,
                    com.badlogic.gdx.graphics.Color.WHITE
            );

            y -= ICON_SPACING; // Move down for next icon
            }
        }
    }
}
