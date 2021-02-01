import stuff.*;
import java.awt.Font;

/** A two player Pong game! First person to 3 wins.
    @author Jake Bussa
*/    
public class Pong{
    public static void main(String[] args){
        StdDraw.setCanvasSize(1100, 800);
        
        //allows for smooth transitioning between creating new images
        StdDraw.enableDoubleBuffering();        
                
        Ball ball = new Ball(.5, .5);
        
        //movement speed of the ball
        double dx = .001;
        double dy = 0;
        
        //ball will either go left or right at the very start of the game
        double r = Math.random();
        if(r < .5){
            dx = -(dx);
        }
        
        //player1 is on the left side, player2 is on the right side
        Blocker player1 = new Blocker(.05, .5);
        Blocker player2 = new Blocker(.95, .5);
        
        //player 1 and 2's movement speed to control the blocker
        double p1Dy = .001;
        double p2Dy = .001;
        
        int p1Score = 0;
        int p2Score = 0;
        
        //if one of the players scored, the loser gets to hold the ball and launch it when they like
        boolean holdBall = false;
        
        //the font for the beginning text, scores, and winner text
        Font font = new Font("ComicSans", Font.BOLD, 90);
        StdDraw.setFont(font);
        
        //game is paused at the start, displays opening text
        boolean showText = true;
        
        while(p1Score < 3 && p2Score < 3){            
            //deleting and redrawing the ball to create the illusion of movement
            ball.reset();
            ball.draw();
            
            //doing the same for both player's blockers, ball.reset(); is in charge of redrawing the screen so we don't worry about it here
            player1.draw();
            player2.draw();
            
            //shows the opening text
            if(showText == true){
                StdDraw.text(.5, .7, "Hit space to start!");
            }    
            //as soon as one of players hits the space bar, start the game and get rid of the opening text
            if(StdDraw.isKeyPressed(32)){
                showText = false;
            }    
            
            //a player has not scored, the ball moves around normally && ball will not move until one of the players hits the space bar at the beginning
            if(holdBall == false && showText == false){
                ball.moveX(dx);
                ball.moveY(dy);
            }
            
            //***a player scored, the loser holds the ball and chooses when to launch it with the space bar***
            if(holdBall == true){
                //the ball will follow the player until launched
                //player1 currently holds the ball
                if(ball.getX() == .1){
                    ball.setY(player1.getY());
                }
                //player2 currently holds the ball
                else if(ball.getX() == .9){
                    ball.setY(player2.getY());
                }
                //when the player hits the space bar, launch the ball
                if(StdDraw.isKeyPressed(32)){
                    dx = ball.goLeft(dx);
                    dy = ball.getRand(dy);
                    holdBall = false;
                } 
            }  
             
            //***this checks to see if one of the players moved their blockers***
            checkMovement(player1, player2, p1Dy, p2Dy);
            
            //***this checks to see if the player successfully blocked the ball; NOTE: Not 100% sure about the logic here***
            //player 1 made contact with the ball
            if(ball.getX() - ball.getRadius() <= player1.getX() + player1.getHw()      //checking the front
            && ball.getY() - ball.getRadius() <= player1.getY() + player1.getHh()      //checking the top
            && ball.getY() - ball.getRadius() >= player1.getY() - player1.getHh()){    //checking the bottom
                dx = ball.goRight(dx);
                dy = ball.getRand(dy);
            }
            //player 2 made contact with the ball
            if(ball.getX() + ball.getRadius() >= player2.getX() - player2.getHw()      //checking he front
            && ball.getY() + ball.getRadius() <= player2.getY() + player2.getHh()      //checking the top
            && ball.getY() + ball.getRadius() >= player2.getY() - player2.getHh()){    //checking the bottom
                dx = ball.goLeft(dx);
                dy = ball.getRand(dy);
            }
            
            //***one of the players scored***
            //the ball reaches the right side, player1 scored
            if((ball.getX() + ball.getRadius()) >= 1){
                holdBall = true;
                ball = new Ball(.9, player2.getY());
                p1Score++;   
            }
            //the ball reaches the left side, player2 scored
            else if((ball.getX() - ball.getRadius()) <= 0){
                holdBall = true;
                ball = new Ball(.1, player1.getY());
                p2Score++;
            }
            
            //***ball reaches the top or bottom sides***
            //the ball reaches the top, go down  
            if((ball.getY() + ball.getRadius()) >= 1){
                dy = ball.goDown(dy);
            } 
            //the ball reaches the bottom, go up 
            else if((ball.getY() - ball.getRadius()) <= 0){
                dy = ball.goUp(dy);
            }
            
            //***showing the player's scores***
            StdDraw.setPenColor(StdDraw.WHITE);
            
            //converting the score to a string so that it can be displayed
            String p1ScoreString = "" + p1Score;
            String p2ScoreString = "" + p2Score;
            StdDraw.text(.4, .9, p1ScoreString);
            StdDraw.text(.6, .9, p2ScoreString);    
            
            //after doing all of that stuff, this in conjunction with double buffering allows for the illusion of movement
            StdDraw.show();
        }
        
        //showing who won
        if(p1Score == 3){
            StdDraw.text(.5, .5, "Player 1 won!");
        }
        else{
            StdDraw.text(.5, .5, "Player 2 won!");
        }
                
        StdDraw.show(); 
        
        //wait a little bit and then close the program
        StdDraw.pause(6000);
        System.exit(0);
    }     
    
    /** Checks to see if one of the players moved their blockers.
        @param player1 is player1's blocker
        @param player2 is player2's blocker
        @param p1Dy is the movement speed of player1's blocker
        @param p2Dy is the movement speed of player2's blocker
    */         
    public static void checkMovement(Blocker player1, Blocker player2, double p1Dy, double p2Dy){
        //player1 moved up (w key), also makes sure that the player doesn't go off the screen
        if(StdDraw.isKeyPressed(87) && player1.getY() + player1.getHh() <= 1){
             player1.goUp(p1Dy);
        }
        //player1 moved down (d key)
        else if(StdDraw.isKeyPressed(83) && player1.getY() - player1.getHh() >= 0){
            player1.goDown(p1Dy); 
        } 
         
        //player2 moved up (up arrow)
        if(StdDraw.isKeyPressed(38) && player2.getY() + player1.getHh() <= 1){
            player2.goUp(p2Dy);
        }    
        //player 2 moves down (down arrow)
        else if(StdDraw.isKeyPressed(40) && player2.getY() - player2.getHh() >= 0){
            player2.goDown(p2Dy);
        }  
    }    
}       