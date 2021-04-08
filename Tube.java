// Patrick Kwok
// id:010917833
// 23 October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import java.awt.image.BufferedImage;
import java.awt.Graphics;

//------------------------------------------------------
// Tube Class
//------------------------------------------------------
public class Tube extends Sprite
{
	//---------------------
	// Member Variables
	//---------------------
	//final int width = 55; //width of tube
	//final int height = 400; // height of tube
	
	static BufferedImage image;
	Model model;
	
	//---------------------
	//Constructor
	//---------------------
	public Tube(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		
		width = 55;
		height = 400;
	    model = m;
		
		loadTubeImage();
	}
	
	//---------------------
    // Un-Marshal & 2nd constructor 
	//---------------------  
	public Tube(Json ob, Model m)
	{
	    x = (int)ob.getLong("x");
	    y = (int)ob.getLong("y");
	    width = 55;
	    height = 400;
	    model = m;
	    loadTubeImage(); 
	    
	   // System.out.println("load a tube at(" + x + ", " + y + ")");
	}	
	
	//---------------------
	// Update method
	//---------------------
	void update()
	{}
	
	//---------------------
	// draw method
	//---------------------
	void draw(Graphics g)
	{
		g.drawImage(image, x - model.mario.x + model.mario.marioOffset, y, null);
	}
	
	//---------------------
	// isTube method Override
	//---------------------
	@Override
	boolean isTube()
	{
		return true;
	}
	
	//---------------------
	//Load tube method
	//---------------------
	void loadTubeImage()
	{
		if(image == null)
			image = View.loadImage("tube.png"); //load tube image
	}
	
	//---------------------
	//tube checking method
	//---------------------
	boolean didIClickOnATube(int mouse_x, int mouse_y) // click on x and y coordinate
	{
		if(mouse_x >= x && mouse_x <= x+width && mouse_y>= y && mouse_y <= y+height)
			return true;
		else
			return false;
	}
	
	//---------------------
	// Marshal tube
	//---------------------
    Json marshal()
    {
    	Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
}
 
