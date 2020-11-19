package Engine;

import java.awt.geom.AffineTransform;

public class CameraObject extends GameObject {

    public AffineTransform position;

    public CameraObject(){
        position = new AffineTransform();
    }
}
