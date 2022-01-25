package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollx, scrolly;
    private double xpos, ypos, lasty, lastx;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {
        this.scrollx = 0.0;
        this.scrolly = 0.0;
        this.xpos = 0.0;
        this.ypos = 0.0;
        this.lastx = 0.0;
        this.lasty = 0.0;
    }

    public static MouseListener get() {
        if (instance == null) {
            instance = new MouseListener();
        }

        return MouseListener.instance;
    }

    public static void mousePosCallBack(long window, double xpos, double ypos) {
        get().lastx = get().xpos;
        get().lasty = get().ypos;
        get().xpos = xpos;
        get().ypos = ypos;
    }

    public static void mouseButtonCallBack(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length){
                get().mouseButtonPressed[button] = true;
            }
        }
        else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallBack(long window, double xoffset, double yoffset) {
        get().scrollx = xoffset;
        get().scrolly = yoffset;
    }

    public static void endFrame() {
        get().scrollx = 0;
        get().scrolly = 0;
        get().lastx = get().xpos;
        get().lasty = get().ypos;
    }

    public static float getx() {
        return (float)get().xpos;
    }
    public static float gety() {
        return (float)get().ypos;
    }
    public static float getDx() {
        return (float)(get().lastx - get().xpos);
    }
    public static float getDy() {
        return (float)(get().lasty - get().ypos);
    }

    public static float getScrollX() {
        return (float)get().scrollx;
    }
    public static float getScrollY() {
        return (float)get().scrolly;
    }
}
