
package cuberendering;

public class CubeObject 
{
    Graphic g;
    float x = 0;
    float y = 0;
    float z = 0;
    String name;
    
    public CubeObject(float x, float y, float z, String name, Graphic g)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
        this.g = g;
    }
    
    public void drawObject()
    {
        g.drawCube(this.x, this.y, this.z);
    }
}