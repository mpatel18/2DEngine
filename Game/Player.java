package Game;

import Engine.Component;
import Engine.GameObject;
import Engine.ResourceNotFound;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Component {

    public int headIndex;
    public int tempHead;

    public int bodyIndex;
    public int tempBody;

    public int frameCount;

    public static AffineTransform position;
    public static AffineTransform childPosition;
    public static int offset;
    public static boolean moving;
    public static boolean lockControls;

    public Player(GameObject object) {
        super(object);

        AffineTransform position = new AffineTransform(1, 0, 0, 1, 0, 0);
        this.position = new AffineTransform(position);
        this.position.translate((Game.engine.getWidth()/2) - Game.size/Game.ratio, (Game.engine.getHeight()/2) - Game.size/Game.ratio);

        headIndex = 0;
        tempHead = headIndex;
        bodyIndex = 8;
        tempBody = bodyIndex;

        frameCount = 0;
        moving = false;

        try {
            Image img = Game.assetsCenter.getImage("boiS.png", headIndex);
            int width = img.getWidth(null);
            int height = img.getHeight(null);

            offset = 10;
            childPosition = new AffineTransform(this.position);
            childPosition.translate(width/5 - 1, height - offset);

        } catch (ResourceNotFound e){
        }

    }

    @Override
    public void logic() {

        if(!lockControls) {
            int speed = 8;

            try {
                Image img = Game.assetsCenter.getImage("boiS.png", headIndex);
                Image img1 = Game.assetsCenter.getImage("boiS.png", bodyIndex);
                int size = img.getWidth(null);
                int length = img.getHeight(null) + img1.getHeight(null);


                if (Game.inputHandler.inputs.contains("D")) {
                    headIndex = 2;
                    tempHead = headIndex;
                    bodyIndex = 18;
                    tempBody = bodyIndex;
                    moving = true;
                    position.translate(speed, 0);
                    childPosition.translate(speed, 0);
                }

                if (Game.inputHandler.inputs.contains("A")) {
                    headIndex = 6;
                    tempHead = headIndex;
                    bodyIndex = 28;
                    tempBody = bodyIndex;
                    moving = true;
                    position.translate(-speed, 0);
                    childPosition.translate(-speed, 0);
                }

                if (Game.inputHandler.inputs.contains("W")) {
                    headIndex = 4;
                    tempHead = headIndex;
                    bodyIndex = 8;
                    tempBody = bodyIndex;
                    moving = true;
                    position.translate(0, -speed);
                    childPosition.translate(0, -speed);
                }

                if (Game.inputHandler.inputs.contains("S")) {
                    headIndex = 0;
                    tempHead = headIndex;
                    bodyIndex = 8;
                    tempBody = bodyIndex;
                    moving = true;
                    position.translate(0, speed);
                    childPosition.translate(0, speed);
                }

                if (Game.inputHandler.inputs.isEmpty()) {
                    moving = false;
                }

            } catch (ResourceNotFound e) {
            }
        }

        if(Game.inputHandler.inputs.contains("C")){
            lockControls = true;
        }

        if(Game.inputHandler.inputs.contains("G")){
            lockControls = false;
        }
    }

    @Override
    public void graphics(Graphics2D G) throws ResourceNotFound {
        Image img1 = Game.assetsCenter.getImage("boiS.png", headIndex);
        Image img2 = Game.assetsCenter.getImage("boiS.png", bodyIndex);
        runAnimation();
        G.drawImage(img2, childPosition, null);
        G.drawImage(img1, this.position, null);
    }

    public void runAnimation(){
//        if(moving) {
//            if (headIndex == tempHead + 1) {
//                headIndex = tempHead;
//            } else {
//                while (headIndex < tempHead + 1) {
//                    headIndex++;
//                }
//            }
//        } else {
//            headIndex = tempHead;
//        }

        if (moving) {
            if (bodyIndex == tempBody + 9) {
                bodyIndex = tempBody;
            } else {
                if (bodyIndex < tempBody + 9) {
                    bodyIndex++;
                }
            }
        } else {
            bodyIndex = tempBody;
        }
    }

    public AffineTransform getPos(){
        return this.position;
    }
}
