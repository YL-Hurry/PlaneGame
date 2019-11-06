package PlaneGame;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends GameObject{
	double degree;
    
    public Shell(){
        degree = Math.random()*Math.PI*2;
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 3;
    }
    
    
    public void draw(Graphics g){
      
        Color c = g.getColor();
        g.setColor(Color.yellow);
 
        g.fillOval((int)x, (int)y, width, height);
         
        x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);
         
        if(y>Constant.GameHeight-height||y<30){
            degree = -degree;
        }
        if(x<0||x>Constant.GameWidth-width){
            degree = Math.PI-degree;
        }
  
        g.setColor(c);
    }

}
