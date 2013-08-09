
package cuberendering;

public class App 
{
    Framework frame;
    Graphic g;
    CubeObject cube;
    
    public void init(int width, int height, boolean resize, boolean fullscreen)
    {
        System.out.println("init");
        
        // init window
        //
        frame = new Framework(800, 600, resize, fullscreen);
        
        // init graphics-controller and give him the currently working framework
        //
        g = new Graphic(frame);
        
        // init openGL
        //
        g.initGL(width, height);
        
        // init objects with working graphics-controller
        //
        cube = new CubeObject(2, 2, 2, "CUBE", g);
    }
    
    public void run()
    {
        // main-loop
        //
        do{
            cube.drawObject();
            
            frame.updateFrame();
            frame.inputEvents();
            
            // frame.display();
        }while(!frame.ESC() && !frame.closeEvent());
    }
    
    public void exit()
    {
        System.out.println("exit");
        frame.killFrame();
    }
}