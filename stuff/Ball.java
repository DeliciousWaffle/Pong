package stuff;

public class Ball{
    //coordinate location of the ball
    private double x;
    private double y;
    //size of the ball, won't be changing this
    private static double radius = .01;
    
    /** Creates a new ball.
        @param x0 is the x-value of the ball.
        @param y0 is the y-value of the ball.
    */    
    public Ball(double x0, double y0){
        x = x0;
        y = y0;
    }
    
    /** Returns the ball's x-value.
        @return the ball's current x-value.
    */    
    public double getX(){
        return x;
    }
        
    /** Returns the ball's y-value.
        @return the ball's current y-value.
    */    
    public double getY(){
        return y;
    } 
    
    /** Sets the ball's x-value to where we want it.
        @param x0 is the x-value we want set.
    */
    public void setX(double x0){
        x = x0;
    } 
    
    /** Sets the ball's y-value to where we want it.
        @param y0 is the y-value we want set.
    */    
    public void setY(double y0){
        y = y0;
    }           
    
    /** Returns the ball's radius.
        @returns the ball's radius.
    */    
    public double getRadius(){
        return radius;
    } 
    
    /** Moves the ball's position along the x-axis.
        @param dx is how much we want the ball's x-value to move by.
    */
    public void moveX(double dx){
        x += dx;
    }
    
    /** Moves the ball's position along the y-axis.
        @param dy is how much we want the ball's y-value to move by.
    */    
    public void moveY(double dy){
        y += dy;
    }        
    
    /** If the ball reaches the rightmost part of the screen, go left.
        @param dx is the ball's current x-value movement speed.
    */    
    public double goLeft(double dx){
        //when going right, ball had a postive x-value, so we're making it negative to go left
        return -dx;
    } 
    
    /** If the ball reaches the leftmost part of the screen, go right.
        @param dx is the ball's current x-value movement speed.
    */    
    public double goRight(double dx){
        //when going left, ball had a negative x-value, so we're making it positive to go right
        return Math.abs(dx);
    }  
           
    /** If the ball reaches the topmost part of the screen, go down.
        @param dy is the ball's current y-value movement speed.
    */    
    public double goDown(double dy){
        return -dy;
    }    
    
    /** If the ball reaches the bottomost part of the screen, go up.
        @param dy is the ball's current y-value movement speed.
    */    
    public double goUp(double dy){
        return Math.abs(dy);
    }  
    
    /** Generates a value within a certain range to give the ball interesting movement.
        @param rx is the ball's current x-value movement speed. 
    */    
    public double getRand(double rx){
        rx = Math.random() * .0009;
        
        //this makes things extra random
        if(rx < .0005){
            return -rx; 
        }
        else{
            return rx;
        }        
    }     
    
    /** Resets the background to black to help emulate motion. */
    public void reset(){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(.5, .5, 5);
    }    
    
    /** Draws the ball at its current location. */
    public void draw(){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(x, y, radius);
    }    
}    
        
        