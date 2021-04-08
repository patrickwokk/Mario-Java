// Patrick Kwok
// id:010917833
// 23 October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//------------------------------------------------------
//Sprite Class
//------------------------------------------------------
abstract class Sprite extends Object
{
	//---------------------
	// Member Variables
	//---------------------
	int x, y;
	int width, height;
	
	BufferedImage image;
	
	abstract void update();
	abstract void draw(Graphics g);
	
	boolean isTube()     { return false; }
	boolean isMario()	 { return false; }
	boolean isGoomba()    { return false; }
	boolean isFireball() { return false; }
	boolean isBrick()    { return false; }
	
}

