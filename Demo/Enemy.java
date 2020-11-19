package Demo;

import Engine.Component;
import Engine.GameObject;
import Engine.ResourceNotFound;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Enemy extends Component {

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

    public Enemy(GameObject object) {
        super(object);

        AffineTransform position = new AffineTransform(1, 0, 0, 1, 0, 0);
        this.position = new AffineTransform(position);
        this.position.translate(Demo.engine.getWidth(), Demo.engine.getHeight());

        headIndex = 0;
        tempHead = headIndex;
        bodyIndex = 8;
        tempBody = bodyIndex;

        frameCount = 0;
        moving = false;

        try {
            Image img = Demo.assetsCenter.getImage("boiS.png", headIndex);
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
                Image img = Demo.assetsCenter.getImage("boiS.png", headIndex);
                Image img1 = Demo.assetsCenter.getImage("boiS.png", bodyIndex);
                int size = img.getWidth(null);
                int length = img.getHeight(null) + img1.getHeight(null);


                if (Demo.inputHandler.inputs.contains("Right")) {
                    headIndex = 2;
                    tempHead = headIndex;
                    bodyIndex = 18;
                    tempBody = bodyIndex;
                    moving = true;
                    position.translate(speed, 0);
                    childPosition.translate(speed, 0);
                }

                if (Demo.inputHandler.inputs.contains("Left")) {
                    headIndex = 6;
                    tempHead = headIndex;
                    bodyIndex = 28;
                    tempBody = bodyIndex;
                    moving = true;
                    position.translate(-speed, 0);
                    childPosition.translate(-speed, 0);
                }

                if (Demo.inputHandler.inputs.contains("Up")) {
                    headIndex = 4;
                    tempHead = headIndex;
                    bodyIndex = 8;
                    tempBody = bodyIndex;
                    moving = true;
                    position.translate(0, -speed);
                    childPosition.translate(0, -speed);
                }

                if (Demo.inputHandler.inputs.contains("Down")) {
                    headIndex = 0;
                    tempHead = headIndex;
                    bodyIndex = 8;
                    tempBody = bodyIndex;
                    moving = true;
                    position.translate(0, speed);
                    childPosition.translate(0, speed);
                }

                if (Demo.inputHandler.inputs.isEmpty()) {
                    moving = false;
                }

            } catch (ResourceNotFound e) {
            }
        }

        if(Demo.inputHandler.inputs.contains("C")){
            lockControls = true;
        }

        if(Demo.inputHandler.inputs.contains("G")){
            lockControls = false;
        }
    }

    @Override
    public void graphics(Graphics2D G) throws ResourceNotFound {
        Image img1 = Demo.assetsCenter.getImage("boiS.png", headIndex);
        Image img2 = Demo.assetsCenter.getImage("boiS.png", bodyIndex);
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
