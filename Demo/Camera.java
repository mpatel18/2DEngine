package Demo;

import Engine.Component;
import Engine.GameObject;
import java.awt.geom.AffineTransform;

public class Camera extends Component {

    public boolean cameraControl = false;

    public Camera(GameObject object) {
        super(object);

        AffineTransform position = new AffineTransform(1, 0, 0, 1, 0, 0);
        parent.setPosition(position);

    }

    @Override
    public void logic(){

        if(cameraControl) {
            int distance = 10;
            double scale = 1;

            if (Demo.inputHandler.inputs.contains("D")) {
                parent.position.translate(distance, 0);
            }

            if (Demo.inputHandler.inputs.contains("A")) {
                parent.position.translate(-distance, 0);
            }

            if (Demo.inputHandler.inputs.contains("W")) {
                parent.position.translate(0, -distance);
            }

            if (Demo.inputHandler.inputs.contains("S")) {
                parent.position.translate(0, distance);
            }

            if (Demo.inputHandler.inputs.contains("Up")) {
                scale = scale + 0.1;
                parent.position.scale(scale, scale);
            }

            if (Demo.inputHandler.inputs.contains("Down")) {
                scale = scale - 0.1;
                parent.position.scale(scale, scale);
            }

            if (Demo.inputHandler.inputs.contains("Right")) {
                parent.position.rotate(.1);
            }

            if (Demo.inputHandler.inputs.contains("Left")) {
                parent.position.rotate(-.1);
            }
        }

        if(Demo.inputHandler.inputs.contains("C")){
            cameraControl = true;
        }

        if(Demo.inputHandler.inputs.contains("G")){
            cameraControl = false;
        }
    }
}
