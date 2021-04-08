// Patrick Kwok
// id:010917833
// 23 October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;

//------------------------------------------------------
// Fireball Class
//------------------------------------------------------
class Fireball extends Sprite
{
	//---------------------
	// Member Variables
	//---------------------
	static BufferedImage image;
	Model model;
	int px, py;
	double vert_velocity;
	int fireballSpeed;
	int direction;

	
	//---------------------
	// Constructor
	//---------------------
	public Fireball(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		this.model = m;
		width = 37;
	    height = 37;
	    vert_velocity = -40.0;
		fireballSpeed = 15;
	    direction = 1;
	    
	    loadFireballImage();
	}
	
	//---------------------
	// Update method
	//---------------------		
	void update()
	{ 
		x+=fireballSpeed*direction;
		vert_velocity += 8.0;
		y+=vert_velocity;
		saveLastCoordinates();
		
		if(y > 570)
		{
			vert_velocity = -30.3;
			y = 550 - height;
		}
		
		for (int i = 0; i < model.sprites.size(); i++)
		{
			if(model.sprites.get(i).isGoomba())
			{
				if (Collide(model.sprites.get(i)))
				{
					((Goomba)(model.sprites.get(i))).setOnFire();
				}
			}	
		}	
	}

	//---------------------
	// Collision method 
	//---------------------
	boolean Collide (Sprite a)
	{
		if(x+width < a.x) //left side
		{	
	//		System.out.println("coming from left");
			return false;
		}			
		if(x > a.x+a.width) // right side
		{	
	//		System.out.println("coming from right");
			return false;
		}	
		if(y+height < a.y) // top side
		{	
	//		System.out.println("coming from top");
			return false;
		}						
		return true;	
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
	// draw method
	//---------------------
	void draw(Graphics g)
	{
		g.drawImage(image, x - model.mario.x + model.mario.marioOffset, y, null);
	}
	
	//---------------------
	// isGoomba method Override
	//---------------------
	@Override
	boolean isFireball()
	{
		return true;
	}
	
	//---------------------
	//Load Goomba method
	//---------------------
	void loadFireballImage()
	{
		if(image == null)
			image = View.loadImage("fireball.png");
	}
	
	
	
	
}
