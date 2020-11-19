package Engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class GameObject {

    protected String name;
//    public int positionX, positionY;
    public static int Max, Min;
    public static AffineTransform position;

    public ArrayList<Component> objectLogic;
    public ArrayList<Component> objectGraphics;
    public ArrayList<GameObject> children;

    public GameObject(){
        this.objectLogic = new ArrayList<>();
        this.objectGraphics = new ArrayList<>();
        this.children = new ArrayList<>();
        // Game.gameObjects.add(this);
    }

    public GameObject(String name, AffineTransform position){
        Max = 0; Min = 0;
        this.name = name;
//        this.positionX = positionX;
//        this.positionY = positionY;
        this.position = position;
        objectLogic = new ArrayList<>();
        objectGraphics = new ArrayList<>();
    }

    public void logic(int priority){
        for(int i = 0; i < objectLogic.size(); i++){
            if(objectLogic.get(i).Priority == priority){
                objectLogic.get(i).logic();
            }
        }
    }

    public void graphic (int priority, Graphics2D G) throws ResourceNotFound{
        for(int i = 0; i < objectGraphics.size(); i++){
            if(objectGraphics.get(i).Priority == priority){
                objectGraphics.get(i).graphics(G);
            }
        }
    }

    public void addLogic(Component component){

        if(!this.objectLogic.contains(component)){
            if(Min > component.Priority){
                Min = component.Priority;
            }

            if(Max < component.Priority){
                Max = component.Priority;
            }

            objectLogic.add(component);
        }
    }

    public void addGraphic(Component component){
        if(!this.objectGraphics.contains(component)){
            if(Min > component.Priority){
                Min = component.Priority;
            }

            if(Max < component.Priority){
                Max = component.Priority;
            }

            objectGraphics.add(component);
        }
    }

//    public int getPositionX(){return positionX;}
//    public int getPositionY(){return positionY;}
//
//    public void setPositionX(int newX) {this.positionX = newX;}
//    public void setPositionY(int newY) {this.positionY = newY;}

    public AffineTransform getPosition(){ return position; }
    public void setPosition(AffineTransform position){ this.position = new AffineTransform(position); }

    public String getName(){ return name; }

    public void removeComponent(Component component){
        this.objectLogic.remove(component);
        this.objectGraphics.remove(component);
    }
}
