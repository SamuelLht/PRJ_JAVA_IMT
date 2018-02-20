package imt.uvinfo.daleks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import imt.uvinfo.daleks.app.Daleks;

public class DaleksDesktop {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Daleks";
        config.width = 800;
        config.height = 600;
        config.forceExit = false; // cleaner exit
        new LwjglApplication(new Daleks(), config);
    }
}
