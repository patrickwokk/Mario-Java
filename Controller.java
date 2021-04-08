// Patrick Kwok
// id:010917833
// 23  October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//------------------------------------------------------
// Controller Class
//------------------------------------------------------
class Controller implements ActionListener, MouseListener, KeyListener
{
	//---------------------
	// Member Variables
	//---------------------
	View view;
	Model model;
	
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spacebar;
	boolean ctrl;
	boolean addTubeEditor = false;
	boolean addGoombaEditor = false;
	
	//---------------------
	// Default Constructor
	//---------------------
	Controller(Model m)
	{
		model = m;
	}
	
	//---------------------
	// Click method
	//---------------------
	public void actionPerformed(ActionEvent e)
	{ }
	
	//---------------------
	// setter method
	//---------------------
	void setView(View v)
	{
		view = v;
	}
	
	//---------------------
	// mouse click method
	//---------------------
	public void mousePressed(MouseEvent e)
	{
		if(addTubeEditor)
			model.addTube(e.getX()+model.mario.x - model.mario.marioOffset, e.getY());
		else if(addGoombaEditor)
			model.addGoomba(e.getX()+model.mario.x - model.mario.marioOffset, e.getY());
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) 
	{
//		if(e.getY() < 100) { System.out.println("break here");
	}
	
	//---------------------
	// keyboard methods
	//---------------------
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: spacebar = true; break;
			case KeyEvent.VK_CONTROL: ctrl = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: spacebar = false; break;
			case KeyEvent.VK_CONTROL: ctrl = false; break;
		}
		
		char c = e.getKeyChar();
		if(c == 's')
		{
//			System.out.println("you have enter 's' key");
			model.marshal().save("map.json");
			System.out.println("you have saved map.json");
		}
		if(c == 'l') 
		{
//			System.out.println("you have enter 'l' key");
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("you have loaded map.json");
		}
		if(c == 'q')
			System.exit(0);
		if(c == 't')
		{
			addTubeEditor = !addTubeEditor; //swap true and false
			System.out.println("Tube editing: " + addTubeEditor);
		}
		if(c == 'g')
		{
			addGoombaEditor = !addGoombaEditor; //swap true and false
			System.out.println("Goomba editing: " + addGoombaEditor);
		}
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

	
	//---------------------
	// update method
	//---------------------
	void update()
	{
		model.mario.saveLastCoordinates();
		if(keyRight) 
		{
			model.mario.updateImage();
			model.mario.x+=15;
			model.mario.flip = false;
		}
		if(keyLeft) 
		{
			model.mario.updateImage();
			model.mario.x-=15;
			model.mario.flip = true;
		}
		if(keyUp || spacebar)
			model.mario.jump();
		
		if(ctrl)
			model.addFireball();
			
//		if(keyDown) model.dest_y++;
	}
}



