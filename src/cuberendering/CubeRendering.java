
package cuberendering;

public class CubeRendering
{
    public static void main(String[] args)
    {
        App app = new App();
        
        app.init(800, 600, false, false);
        app.run();
        app.exit();
    }
}
