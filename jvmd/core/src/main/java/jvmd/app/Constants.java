package jvmd.app;

public class Constants {
	
	// General Constants
	public static final String APP_TITLE = "JVMD";
	public static final String CURRENT_VERSION = "1.0";
	
    // Screen Constants
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	// Game Constants
	public static final float GRAVITY =  -9.8f * 4;

	public static final float TIME_STEP = 1;

	public static final int VELOCITY_ITERATIONS = 1;

	public static final int POSITION_ITERATIONS = 1;
	
	public static final float PLAYER_WIDTH = 0.9f;
	public static final float PLAYER_HEIGHT = 1.5f;
	
	public enum State{
		Standing, Walking, Jumping
	}
	
}
