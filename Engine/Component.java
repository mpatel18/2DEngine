package Engine;

import java.awt.*;

public class Component {
	
	public GameObject parent = null; 
	public boolean active = true;
	public int Priority = 0 ;
	
	public Component(GameObject object){
		parent = object;
	}
	
	public void graphics(Graphics2D G) throws ResourceNotFound {}
	
	public void logic() {}
}
