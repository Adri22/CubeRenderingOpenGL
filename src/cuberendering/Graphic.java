
package cuberendering;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Graphic
{
    Framework frame;
    
    private float rotateX = 0;
    private float rotateY = 0;
    private float rotateZ = 0;
    private float scale = 0;
    
    // light-variables
    //
    private float [] redDiffuse = {1.0f, 0.0f, 0.0f, 1.0f};
    private float [] greenDiffuse = {0.0f, 1.0f, 0.0f, 1.0f};
    private float [] blueDiffuse = {0.0f, 0.0f, 1.0f, 1.0f};
    private float [] posTopLeft = {-2.0f, 2.0f, 0.0f, 1.0f};
    private float [] posTopRight = {2.0f, 2.0f, 0.0f, 1.0f};
    private float [] posBottomFront = {0.0f, -2.0f, 1.0f, 1.0f};

    public Graphic(Framework frame)
    {
        this.frame = frame;
    }
    
    public void initGL(int width, int height)
    {
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
        
        resizeScene(width, height);
        initLight();
    }
    
    private void initLight()
    {
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, arrayToBuffer(redDiffuse));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, arrayToBuffer(posTopLeft));

        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, arrayToBuffer(greenDiffuse));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, arrayToBuffer(posTopRight));

        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, arrayToBuffer(blueDiffuse));
        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, arrayToBuffer(posBottomFront));

        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glEnable(GL11.GL_LIGHT1);
        GL11.glEnable(GL11.GL_LIGHT2);
        GL11.glEnable(GL11.GL_LIGHTING);
    }
    
    private void resizeScene(int width, int height) 
    {
        GL11.glViewport(0, 0, width, height);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(45.0f, (float)width / (float)height, 0.1f, 100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }
    
    public void drawCube(float x, float y, float z)
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0f, 0.0f, -3.0f);
        
        GL11.glRotatef(rotateX, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(rotateY, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(rotateZ, 0.0f, 0.0f, 1.0f);
        GL11.glScalef(scale, scale, scale);
        
        GL11.glBegin(GL11.GL_QUADS);
        
            // Front Side
            GL11.glNormal3f(0.0f, 0.0f, 1.0f);
            GL11.glVertex3f(-x, -y, z);
            GL11.glVertex3f(x, -y, z);
            GL11.glVertex3f(x, y, z);
            GL11.glVertex3f(-x, y, z);
            
            // Back Side
            GL11.glNormal3f(0.0f, 0.0f, -1.0f);
            GL11.glVertex3f(x, -y, -z);
            GL11.glVertex3f(-x, -y, -z);
            GL11.glVertex3f(-x, y, -z);
            GL11.glVertex3f(x, y, -z);
            
            // Left Side
            GL11.glNormal3f(-1.0f, 0.0f, 0.0f);
            GL11.glVertex3f(-x, -y, -z);
            GL11.glVertex3f(-x, -y, z);
            GL11.glVertex3f(-x, y, z);
            GL11.glVertex3f(-x, y, -z);
            
            // Right Side
            GL11.glNormal3f(1.0f, 0.0f, 0.0f);
            GL11.glVertex3f(x, -y, z);
            GL11.glVertex3f(x, -y, -z);
            GL11.glVertex3f(x, y, -z);
            GL11.glVertex3f(x, y, z);
            
            // Top Side
            GL11.glNormal3f(0.0f, 1.0f, 0.0f);
            GL11.glVertex3f(-x, y, z);
            GL11.glVertex3f(x, y, z);
            GL11.glVertex3f(x, y, -z);
            GL11.glVertex3f(-x, y, -z);
            
            // Bottom Side
            GL11.glNormal3f(0.0f, -1.0f, 0.0f);
            GL11.glVertex3f(x, -y, -z);
            GL11.glVertex3f(-x, -y, -z);
            GL11.glVertex3f(-x, -y, z);
            GL11.glVertex3f(x, -y, z);
            
        GL11.glEnd();
        
        animation();
    }
    
    private void animation()
    {
        boolean scaleUp = false;
        
        if(rotateX < 360){
            rotateX += frame.time.getDelta() * 0.1f;
        } else {
            rotateX = 0.0f;
        }

        if(rotateY < 360){
            rotateY += frame.time.getDelta() * 0.1f;
        } else {
            rotateY = 0.0f;
        }

        if(rotateZ < 360){
            rotateZ += frame.time.getDelta() * 0.1f;
        } else {
            rotateZ = 0.0f;
        }
        
        if(scale >= 1.5f){
            scaleUp = false;
        } else if(scale <= 0.25f){
            scaleUp = true;
        }
        
        if(scaleUp){
            scale += 0.0005f;
        } else {
            scale -= 0.0005f;
        }
    }
    
    private FloatBuffer arrayToBuffer(float [] data)
    {
        FloatBuffer buffer = ByteBuffer.allocateDirect(data.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.clear();
        buffer.put(data);
        buffer.rewind();
        
        return buffer;
    }
    
}