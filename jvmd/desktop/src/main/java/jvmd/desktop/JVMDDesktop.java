package jvmd.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import jvmd.app.Constants;
import jvmd.app.JVMD;

public class JVMDDesktop {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Constants.APP_TITLE;
        config.width = Constants.SCREEN_WIDTH;
        config.height = Constants.SCREEN_HEIGHT;
        config.forceExit = false; // cleaner exit
        // Avoid OpenAL library bug
        LwjglApplicationConfiguration.disableAudio = true;
        new LwjglApplication(new JVMD(), config);
    }
}
