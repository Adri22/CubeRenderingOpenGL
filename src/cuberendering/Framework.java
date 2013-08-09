
package cuberendering;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Framework
{
    // initialize new timer
    //
    Timer time = new Timer();
    
    // frame-variables
    //
    int width = 0;
    int height = 0;
    boolean resize = false;
    boolean fullscreen = false;
    
    // mouse-variables
    //
    int mouseX;
    int mouseY;
    int left_mouse_click_pos_X;
    int left_mouse_click_pos_Y;
    int right_mouse_click_pos_X;
    int right_mouse_click_pos_Y;
    boolean mouse_click_left = false;
    boolean mouse_click_right = false;
    
    // keyboard-variables
    //
    boolean esc_down = false;
    
    public Framework(int width, int height, boolean resize, boolean fullscreen)
    {
        this.width = width;
        this.height = height;
        this.resize = resize;
        this.fullscreen = fullscreen;
        
        time.startTime();
        
        try{
            Display.setDisplayMode(new DisplayMode(this.width, this.height));
            Display.setFullscreen(this.fullscreen);
            Display.create();
        }catch(LWJGLException ex){
            Logger.getLogger(CubeRendering.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        Display.setResizable(this.resize);
    }
    
    public void writeFPSinTitle()
    {
        Display.setTitle("fps: " + time.updateFPS());
    }
    
    public void updateFrame()
    {
        Display.update();
        writeFPSinTitle();
    }
    
    public void killFrame()
    {
        Display.destroy();
    }
    
    public boolean closeEvent()
    {
        return Display.isCloseRequested();
    }
    
    public boolean ESC()
    {
        return this.esc_down;
    }
    
    public void inputEvents()
    {
        this.mouseX = Mouse.getX();
        this.mouseY = Mouse.getY();
        
        // mouseclicks
        //
        if(Mouse.isButtonDown(0)){
            this.left_mouse_click_pos_X = Mouse.getX();
            this.left_mouse_click_pos_Y = Mouse.getY();
            this.mouse_click_left = true;
        } else {
            this.mouse_click_left = false;
        }
        
        if(Mouse.isButtonDown(1)){
            this.right_mouse_click_pos_X = Mouse.getX();
            this.right_mouse_click_pos_Y = Mouse.getY();
            this.mouse_click_right = true;
        } else {
            this.mouse_click_right = false;
        }
        //
        // -------------------------------------
        
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            this.esc_down = true;
        }
        
        // keyboard-buffer // currently not needed
        // TODO: insert key-events here and stuff like that ...
        while(Keyboard.next() && !this.esc_down){
	    if(Keyboard.getEventKeyState()){
	    } else {
	    }
	}
    }
    
    // show framework-stats on runtime
    //
    public void display()
    {
        // System.out.println("MousePos-X: " + this.mouseX + ", MousePos-Y: " + this.mouseY);
        
        // System.out.println("fps: " + time.updateFPS() + " | time-last_frame: " + time.getDelta());
        
        if(this.mouse_click_left){
            System.out.println("left mouse down @ X: " + this.left_mouse_click_pos_X + ", Y: " + this.left_mouse_click_pos_Y);
        }
        if(this.mouse_click_right){
            System.out.println("right mouse down @ X: " + this.right_mouse_click_pos_X + ", Y: " + this.right_mouse_click_pos_Y);
        }
    }
}