package faust.ui;

import faust.util.CustomResourceManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.AbstractUIElement;

import java.util.HashMap;
import java.util.Map;

public class CustomResourcePanel extends AbstractUIElement {
    private static final float START_X = 50.0f; // Left side of the screen
    private static final float START_Y = 600.0f; // Start high on the screen
    private static final float SPACING_Y = 50.0f; // Space between icons

    private static final Map<CustomResourceManager.ResourceType, Texture> resourceIcons = new HashMap<>();
    private static final BitmapFont font = FontHelper.energyNumFontRed;

    static {
        loadTextures();
    }

    public CustomResourcePanel() {
        super();
    }

    private static void loadTextures() {
        resourceIcons.put(CustomResourceManager.ResourceType.PRIDE, ImageMaster.loadImage("BasicModResources/images/ui/pride.png"));
        resourceIcons.put(CustomResourceManager.ResourceType.GLOOM, ImageMaster.loadImage("BasicModResources/images/ui/gloom.png"));
        resourceIcons.put(CustomResourceManager.ResourceType.LUST, ImageMaster.loadImage("BasicModResources/images/ui/lust.png"));
        resourceIcons.put(CustomResourceManager.ResourceType.WRATH, ImageMaster.loadImage("BasicModResources/images/ui/wrath.png"));
        resourceIcons.put(CustomResourceManager.ResourceType.GLUTTONY, ImageMaster.loadImage("BasicModResources/images/ui/gluttony.png"));
        resourceIcons.put(CustomResourceManager.ResourceType.SLOTH, ImageMaster.loadImage("BasicModResources/images/ui/sloth.png"));
        resourceIcons.put(CustomResourceManager.ResourceType.ENVY, ImageMaster.loadImage("BasicModResources/images/ui/envy.png"));
    }

    @Override
    public void render(SpriteBatch sb) {
        float yOffset = START_Y;

        for (CustomResourceManager.ResourceType type : CustomResourceManager.ResourceType.values()) {
            Texture icon = resourceIcons.get(type);
            int resourceAmount = CustomResourceManager.getResourceAmount(type);

            if (icon != null) {
                sb.draw(icon, START_X, yOffset, 40.0f, 40.0f); // Draw icon at position
                FontHelper.renderFontLeft(sb, font, Integer.toString(resourceAmount), START_X + 20, yOffset + 30, com.badlogic.gdx.graphics.Color.WHITE); // Draw number
            }

            yOffset -= SPACING_Y; // Move down for next icon
        }
    }

    @Override
    public void update() {
        // Nothing special needed for now, will update dynamically
    }
}
