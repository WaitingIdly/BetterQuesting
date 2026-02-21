package betterquesting.client;

import betterquesting.core.ModReference;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class BQ_Keybindings {
    public static KeyBinding openQuests;
    public static KeyBinding backPage;
    public static KeyBinding resetView;
    public static KeyBinding zoomIn;
    public static KeyBinding zoomOut;
    public static KeyBinding scrollUp;
    public static KeyBinding scrollRight;
    public static KeyBinding scrollDown;
    public static KeyBinding scrollLeft;

    public static void RegisterKeys() {
        openQuests = new KeyBinding("key.betterquesting.quests", Keyboard.KEY_GRAVE, ModReference.NAME);
        backPage = new KeyBinding("key.betterquesting.back", Keyboard.KEY_BACK, ModReference.NAME);

        resetView = new KeyBinding("key.betterquesting.resetView", Keyboard.KEY_DECIMAL, ModReference.NAME);
        resetView.setKeyConflictContext(KeyConflictContext.GUI);
        zoomIn = new KeyBinding("key.betterquesting.zoomIn", Keyboard.KEY_ADD, ModReference.NAME);
        zoomIn.setKeyConflictContext(KeyConflictContext.GUI);
        zoomOut = new KeyBinding("key.betterquesting.zoomOut", Keyboard.KEY_SUBTRACT, ModReference.NAME);
        zoomOut.setKeyConflictContext(KeyConflictContext.GUI);

        scrollUp = new KeyBinding("key.betterquesting.scrollUp", Keyboard.KEY_UP, ModReference.NAME);
        scrollUp.setKeyConflictContext(KeyConflictContext.GUI);
        scrollRight = new KeyBinding("key.betterquesting.scrollRight", Keyboard.KEY_RIGHT, ModReference.NAME);
        scrollRight.setKeyConflictContext(KeyConflictContext.GUI);
        scrollDown = new KeyBinding("key.betterquesting.scrollDown", Keyboard.KEY_DOWN, ModReference.NAME);
        scrollDown.setKeyConflictContext(KeyConflictContext.GUI);
        scrollLeft = new KeyBinding("key.betterquesting.scrollLeft", KeyConflictContext.GUI, Keyboard.KEY_LEFT, ModReference.NAME);
        scrollLeft.setKeyConflictContext(KeyConflictContext.GUI);

        ClientRegistry.registerKeyBinding(openQuests);
        ClientRegistry.registerKeyBinding(backPage);
        ClientRegistry.registerKeyBinding(resetView);
        ClientRegistry.registerKeyBinding(zoomIn);
        ClientRegistry.registerKeyBinding(zoomOut);
        ClientRegistry.registerKeyBinding(scrollUp);
        ClientRegistry.registerKeyBinding(scrollRight);
        ClientRegistry.registerKeyBinding(scrollDown);
        ClientRegistry.registerKeyBinding(scrollLeft);
    }
}
