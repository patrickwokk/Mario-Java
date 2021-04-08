// Patrick Kwok
// id:010917833
// 23 October 2020
//------------------------------------------------------
// Assignment 5
//------------------------------------------------------

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

//------------------------------------------------------
// Model Class
//------------------------------------------------------
class Model
{
	//---------------------
	// Member Variables
	//---------------------
	int x;
	int y;
	int dest_x;
	int dest_y;
	ArrayList<Sprite> sprites;
	Mario mario; //redundant reference to Mario for easy update
	TubeComparator comparator;
	int fireballCount;
	int maxScroll =3000;

	//---------------------
	// Default Constructor
	//---------------------
	Model()
	{
	    sprites = new ArrayList<Sprite>();
		mario = new Mario(200,50);
		sprites.add(mario);
		comparator = new TubeComparator();
		fireballCount = 0;
	}
	
	//---------------------
	// Update method 
	//---------------------
	public void update()
	{	
		
		for (int i = 0; i < sprites.size(); i++)
		{
			Sprite s = sprites.get(i); //polymorphism->mario
			s.update();
			if(sprites.get(i).isTube())
			{
				if (Collide(mario,(sprites.get(i))))
				{
					mario.getOutOfTube(((Tube)sprites.get(i)));
				}
			}

			// LAST CHANGE
			if(sprites.get(i) instanceof Goomba) {
				if(((Goomba)sprites.get(i)).getHealth() == 0) {
					sprites.remove(i);
				}
			}
			
			if(sprites.get(i).isFireball())
			{
				Fireball f = (Fireball)sprites.get(i);
				if(f.x > maxScroll)
				{
					sprites.remove(f);
				}
				
			}
			
		}
		fireballCount++;
	}
	
	//---------------------
	//Add tube Method
	//---------------------
	public void addTube(int x, int y)
	{
		Tube t = new Tube(x, y, this);
		boolean didIClickedOnATube = false;
		Iterator<Sprite> tubeIterator = sprites.iterator();
				
//		while(tubeIterator.hasNext())
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isGoomba())
			{
				Goomba g = (Goomba)sprites.get(i);
				if(g.isOnFire && g.goombaHealth < 0)
				{
					sprites.remove(g);

				}
			}
			
			if(sprites.get(i).isTube())
			{
	//			Tube temp = tubeIterator.next();
				Tube temp = (Tube)sprites.get(i);
	//			if (tubes.get(i).didIClickOnATube(x, y) == true)
				if (temp.didIClickOnATube(x,y))
				{
					sprites.remove(temp);
	//				tubeIterator.remove();
					didIClickedOnATube = true;
					return;
				}
			}
			
//			if(sprites.get(i).isFireball())
//			{
//				Fireball f = (Fireball)sprites.get(i);
//				if(f.x > MAX_SCROLL)
//				
//			}
		}
		if(!didIClickedOnATube)
			sprites.add(t); 
//		sprites.sort(comparator);
	}
	
	//---------------------
	//Add Goomba Method
	//---------------------
	public void addGoomba(int x, int y)
	{
		
		Goomba g = new Goomba (x, y, this);
		sprites.add(g);
	}
	
	//---------------------
	//Add Fireball Method
	//---------------------
	public void addFireball()
	{
		if(fireballCount > 5) {
			Fireball f = new Fireball (mario.x+mario.width, mario.y, this);
			sprites.add(f);
			fireballCount = 0;
		}
	}
	
	
	//---------------------
	// Collision method 
	//---------------------
	boolean Collide (Sprite a, Sprite b)
	{
		if(a.x+a.width < b.x) //left side
		{	
//			System.out.println("coming from left");
			return false;
		}			
		if(a.x > b.x+b.width) // right side
		{	
//			System.out.println("coming from right");
			return false;
		}	
		if(a.y > b.y+b.height) //bottom side
		{	
//			System.out.println("coming from bottom");
			return false;
		}		
		if(a.y+a.height < b.y) // top side
		{	
//			System.out.println("coming from top");
			return false;
		}												
		return true;	
	}
	
	//---------------------
	// comparator method (NOT REQUIRED)
	//---------------------
	class TubeComparator implements Comparator<Tube>
	{
		public int compare(Tube a, Tube b)
		{
			if(a.x < b.x)
				return -1;
			else if(a.x > b.x)
				return 1;
			else
				return 0;
		}

		public boolean equals(Object obj)
		{
			return false;
		}
	}
	
	//---------------------
	// Json marshal method 
	//---------------------
	Json marshal()
	{
		Json ob = Json.newObject();
		Json spritesOb = Json.newObject();
		Json tmpList = Json.newList();
		ob.add("sprites", spritesOb);
		spritesOb.add("tubes", tmpList);
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isTube())
				tmpList.add(((Tube)sprites.get(i)).marshal());
		}
		tmpList = Json.newList();
		spritesOb.add("goombas", tmpList);
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isGoomba())
				tmpList.add(((Goomba)sprites.get(i)).marshal());
		}
		return ob;	
	}
	
	//---------------------
	// Json unmarshal method
	//---------------------
	void unmarshal (Json ob)
	{
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
	    Json jsonList = ob.get("sprites");
	    Json tubesList = jsonList.get("tubes");
	    Json goombasList = jsonList.get("goombas");
	    //add tubes back from the map
	    for(int i = 0; i < tubesList.size(); i++)
	    {
	        sprites.add(new Tube(tubesList.get(i), this));
	    }
	    //add goombas back from the map
	    for(int i = 0; i < goombasList.size(); i++)
	    {
	        sprites.add(new Goomba(goombasList.get(i), this));
	    }
	}
	
	
}



