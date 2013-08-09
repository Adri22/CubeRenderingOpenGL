
package cuberendering;

public class Timer
{
    long lastFPS = 0;
    long fps = 0;
    long lastFrameTime = 0;
    
    public Timer()
    {
        lastFrameTime = getTime();
    }
    
    public long getTime()
    {
        return System.nanoTime() / 1000000;
    }
    
    public void startTime()
    {
        lastFPS = getTime(); //set lastFPS to current Time
    }
    
    public long updateFPS()
    {
        if(getTime() - lastFPS > 1000){
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
        
        return fps;
    }
    
    public int getDelta()
    {
        long time = getTime();
        int delta = (int)(time - lastFrameTime);
        lastFrameTime = time;

        return delta;
    }
    
    //
    // insert function for VSync-handling
}