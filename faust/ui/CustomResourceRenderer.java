package faust.ui;
import basemod.interfaces.PostRenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class CustomResourceRenderer implements PostRenderSubscriber {
    private final CustomResourceUI resourceUI = new CustomResourceUI();

    @Override
    public void receivePostRender(SpriteBatch sb) {
        resourceUI.render(sb);
    }
}
