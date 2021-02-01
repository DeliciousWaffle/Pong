package stuff;

public class Blocker{
    private double x;
    private double y;
    //size of the blocker will not change
    private static double hw = .01;
    private static double hh = .06;
    
    /** Creates a new Blocker.
        @param x0 is the x-value.
        @param y0 is the y-value.
    */    
    public Blocker(double x0, double y0){
        x = x0;
        y = y0;
    }
    
    /** Returns the x-value of the Blocker.
        @return the x-value.
    */    
    public double getX(){
        return x;
    }
    
    /** Returns the y-value of the Blocker.
        @return the y-value.
    */    
    public double getY(){
        return y;    
    }
    
    /** Returns the half width of the Blocker.
        @return the half width.
    */ 
    public double getHw(){
        return hw;
    }
    
    /** Returns the half height of the Blocker.
        @return the half height.
    */ 
    public double getHh(){
        return hh;
    } 
    
    /** Makes the Blocker move up by adding the incomming value
        to the current y-value.
        @param dy is how far up we want to go.
    */           
    public void goUp(double dy){
        y += dy;
    }
    
    /** Makes the Blocker move down by subtracting the incomming
        value to the current y-value.
        @param dy is how far down we want to go.
    */ 
    public void goDown(double dy){
        y -= dy;
    }
    
    /** Draws the Blocker. */
    public void draw(){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(x, y, hw, hh);  
    }                 
}        