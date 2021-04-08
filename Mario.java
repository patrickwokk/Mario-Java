// Patrick Kwok
// id:010917833
// 23 October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import java.awt.image.BufferedImage;
import java.awt.Graphics;

//------------------------------------------------------
// Mario Class
//------------------------------------------------------
class Mario extends Sprite
{
	//---------------------
	// Member Variables
	//---------------------
	int px, py; //save mario last coordinate
	int marioImageNum;
	int marioOffset;
	double vert_vel;
	int numFramesinAir;
	boolean marioIsTop;
	boolean flip;
	static BufferedImage[] mario_images;
	
	//---------------------
	// Constructor
	//---------------------
	public Mario (int x, int y)
	{
		this.x = x;
		this.y = y;
		width = 60; //mario width
		height = 95;
		marioImageNum = 0;
		marioOffset = this.x;
		vert_vel = -12.0;
		numFramesinAir = 0;
		marioIsTop = false;
		flip = false;
		
		mario_images = new BufferedImage[5];
		mario_images[0] = View.loadImage("mario1.png");
		mario_images[1] = View.loadImage("mario2.png");
		mario_images[2] = View.loadImage("mario3.png");
		mario_images[3] = View.loadImage("mario4.png");
		mario_images[4] = View.loadImage("mario5.png");
	}
	
	//---------------------
	// Update method
	//---------------------
	void update()
	{
		numFramesinAir++;
		if (!marioIsTop)
		{
			vert_vel += 1.0; //when pressing key
			y += vert_vel;	
		}
		if (y > 500)
		{
			//vert_vel = -38; //bounce mario
			vert_vel = 0;
			y = 500;//snap back to the ground
			numFramesinAir = 0;
		}
		if (y < 0)
		{
			y = 0;
		}
		

	}
	
	//---------------------
	// draw method
	//---------------------
	void draw(Graphics g)
	{
		if(flip) //flip the image
			g.drawImage(mario_images[marioImageNum], marioOffset + width, y, -width, height, null);
		else //draw normal
			g.drawImage(mario_images[marioImageNum], marioOffset, y, null);	
	}
	
	//---------------------
	// isMario method Override
	//---------------------
	@Override
	boolean isMario()
	{
		return true;
	}
	
	//---------------------
	// updating image method
	//---------------------
	void updateImage()
	{
		marioImageNum++;
		if(marioImageNum > 4)
			marioImageNum = 0;
	}
		
	//---------------------
	// Jump method
	//---------------------
	void jump ()
	{
		if(numFramesinAir < 5)
			vert_vel -= 5;
		marioIsTop = false;	
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
	// Get out of Tube method
	//---------------------
	void getOutOfTube (Tube t)
	{
		//getting out of tube if mario on left side 
		if(x+width >= t.x && px + width <= t.x)
         {
			x = t.x - width;	 	
		 } 

        //getting out of tube if mario on right side
        if(x <= t.x+t.width && px >= t.x + t.width)
        {
            x = t.x + t.width;
        }

        //getting out of tube if mario on bottom side
        if(y <= t.height +t.y && py >= t.y + t.height)
        {  
			y = t.y + t.height;
		}
        
        //getting out of tube if mario on upper side
        if(y+height >= t.y && py+height <= t.y)
        {
        	numFramesinAir = 0;
        	vert_vel = 0;
			y = t.y - height;	
		}
	}
}

