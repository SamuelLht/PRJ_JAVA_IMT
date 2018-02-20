package jvmd.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import jvmd.app.JVMD;

public class JVMDDesktop {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "JVMD";
        config.width = 800;
        config.height = 600;
        config.forceExit = false; // cleaner exit
        new LwjglApplication(new JVMD(), config);
    }
}
