// Patrick Kwok
// id:010917833
// 23 October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import javax.swing.JFrame;
import java.awt.Toolkit;

//------------------------------------------------------
// Game Class
//------------------------------------------------------
public class Game extends JFrame
{
	//---------------------
	// Member variables
	//---------------------
	Model model;
	View view;
	Controller controller;
	
	//---------------------
	// Default Constructor
	//---------------------
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller,model);
		
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		
		//try catch-block to load map?
		this.setTitle("Assignment5");
		this.setSize(700, 700);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try 
		{
			Json j = Json.load("map.json");
			model.unmarshal(j);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("'ERROR' while loading map");
			System.exit(0);
		}
	}
	
	//---------------------
	// run method
	//---------------------
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 40 milliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
//------------------------------------------------------
// Main Program
//------------------------------------------------------
	public static void main(String[] args)
	{
		//Tube t = new Tube (300,305);
		//Json ob = t.marshal();
		//ob.save("tubetest.json");
		
		Game g = new Game();
		g.run();
	}
}