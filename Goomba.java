// Patrick Kwok
// id:010917833
// 23 October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import java.awt.image.BufferedImage;
import java.awt.Graphics;

//------------------------------------------------------
// Goomba Class
//------------------------------------------------------
class Goomba extends Sprite
{
	//---------------------
	// Member Variables
	//---------------------
	int px, py;
	static BufferedImage image;
	static BufferedImage fireImage;
	Model model;
	double vert_velocity;
	boolean isOnFire = false;
	int numFramesOnfire = 0;
	boolean goombaIsTop;
	int direction;

	// FIRST CHANGE
	int goombaHealth = 42;
	
	//---------------------
	// Constructor
	//---------------------
	public Goomba(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		this.model = m;
		width = 37;
	    height = 44;
		vert_velocity = -12.0;
		goombaIsTop = false;
		direction = 1;
		loadGoombaImage();
		
	}
	
	//---------------------
    // Un-Marshal & 2nd constructor 
	//---------------------  
	public Goomba(Json ob, Model m)
	{
	    x = (int)ob.getLong("x");
	    y = (int)ob.getLong("y");
	    width = 37;
	    height = 44;
	    model = m;
	    vert_velocity = -12.0;
		goombaIsTop = false;
		direction = 1;
	    loadGoombaImage(); 
	    
	   // System.out.println("load a goomba at(" + x + ", " + y + ")");
	}	
	
	//---------------------
	// Update method
	//---------------------		
	void update()
	{ 
		saveLastCoordinates();
		if(!goombaIsTop) 
		{
			vert_velocity += 0.8;
			y += vert_velocity;		
		}
		if (y > 550)
		{
			vert_velocity = 0;
			y = 550;//snap back to the ground
		}
		if (y < 0)
		{
			y = 0;
		}
		
		x+=5*direction;
		
		for (int i = 0; i < model.sprites.size(); i++)
		{
			if(model.sprites.get(i).isTube())
			{
				if (Collide(model.sprites.get(i)))
				{
					getOutOfTube(model.sprites.get(i));
				}
			}	
		}
		
		if (isOnFire == true)
			goombaHealth -= 1; // SECOND CHANGE
	
	}

	// THIRD CHANGE
	int getHealth() {
		return goombaHealth;
	}
	
	//---------------------
	// Goomba on fire
	//---------------------
	void setOnFire() {
		isOnFire = true;
	}
	
	//---------------------
	// draw method
	//---------------------
	void draw(Graphics g)
	{
		if(isOnFire)
			g.drawImage(fireImage, x - model.mario.x + model.mario.marioOffset, y, null);
		else
			g.drawImage(image, x - model.mario.x + model.mario.marioOffset, y, null);
	}
	
	//---------------------
	// isGoomba method Override
	//---------------------
	@Override
	boolean isGoomba()
	{
		return true;
	}
	
	//---------------------
	//Load Goomba method
	//---------------------
	void loadGoombaImage()
	{
		if(image == null)
			image = View.loadImage("goomba.png");
		if(fireImage == null)
			fireImage = View.loadImage("goomba_fire.png");
	}
	
	//---------------------
	// Save Last Coordinate method
	//---------------------
	void saveLastCoordinates()
	{
		px = x;
		py = y;
	}
	
	//---------------------
	// Collision method 
	//---------------------
	boolean Collide (Sprite a)
	{
		if(x+width < a.x) //left side
		{	
//			System.out.println("coming from left");
			return false;
		}			
		if(x > a.x+a.width) // right side
		{	
//			System.out.println("coming from right");
			return false;
		}	
		if(y+height < a.y) // top side
		{	
//			System.out.println("coming from top");
			return false;
		}						
		return true;	
	}
	
	//---------------------
	// Get out of Tube method
	//---------------------
	void getOutOfTube (Sprite s)
	{
		//getting out of tube if goomba on left side 
		if(x+width >= s.x && px + width <= s.x)
         {
			x = s.x - width;
			direction = direction*-1;
		 } 

        //getting out of tube if goomba on right side
        if(x <= s.x+s.width && px >= s.x + s.width)
        {
            x = s.x + s.width;
            direction = direction*-1;
        }
        //getting out of tube if mario on upper side
        if(y+height >= s.y && py+height <= s.y)
        {
        	vert_velocity = 0;
			y = s.y - height;	
		}
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
