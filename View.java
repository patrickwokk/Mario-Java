// Patrick Kwok
// id:010917833
// 23 October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

//------------------------------------------------------
// View Class
//------------------------------------------------------
class View extends JPanel
{
	Model model;
	Mario mario;
	
	//---------------------
	// Default Constructor
	//---------------------
	public View(Controller c, Model m)
	{
		c.setView(this); //reference to controller
		this.model = m; //reference to model
	}

	//---------------------
	// Load Image Constructor
	//---------------------
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
			System.out.println(filename + " has been Loaded.");
		} 
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}
	
	
	//---------------------
	// drawing method
	//---------------------
	public void paintComponent(Graphics g)
	{		
		//draw sky
		g.setColor(new Color(128, 255, 255));	
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//draw ground
		g.setColor(new Color (100, 255, 80));
		g.fillRect(0, 595, this.getWidth(), 596);
		
		//Draw sprites
		for(int i = 0; i < model.sprites.size(); i++)
		{
			model.sprites.get(i).draw(g);
		}
	}
	
	
}
