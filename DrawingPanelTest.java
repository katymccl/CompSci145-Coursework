import java.awt.*;
public class DrawingPanelTest{

   public static void main(String args[]){
        
        //create a drawing panel of width=400px and height=300px
        DrawingPanel panel = new DrawingPanel(400, 300);
        //set the background of the panel to CYAN
        panel.setBackground(Color.CYAN);
        //create a graphic object for the panel 
        Graphics g = panel.getGraphics();
        
        //draw square
        drawFigure_1(g, 180, 115, 36);
        
        //draw MickeyBox
        drawFigure_2(g, 50, 25);
        
   }
   
   public static void drawFigure_1(Graphics g, int x, int y, int z) {
        //set your drawing color to red
        g.setColor(Color.RED);
        for (int i = 1; i <= 5; i++) {
            //draw a rectangle, (x,y) is the top-left cordiante of the rectangle, and ((i*z), (i*z)) 
            //are the width and height of the rectangle
            g.drawRect(x, y, i * z, i * z);
        }
        g.setColor(Color.BLACK);
        //the first two pramaters are the starting (x,y) cordinates of the line, and the second two
        //parameters are the ending cordinates of the line
        g.drawLine(x, y, x +(5*z), y + (5*z));
   }
   
   public static void drawFigure_2(Graphics g, int x, int y){
        g.setColor(Color.BLUE);
        //(x,y) are the center, and 40 is the radius
        g.fillOval(x, y, 40, 40);
        g.fillOval(x+80, y, 40, 40);
        g.setColor(Color.RED);
        g.fillRect(x+20, y+20, 80, 80);
        g.setColor(Color.BLACK);
        g.drawLine(x+20, y+60, x+100, y+60);
   }
}
   