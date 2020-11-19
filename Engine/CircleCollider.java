package Engine;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class CircleCollider extends Component {

    public int radius;
    public GameObject other;

    public AffineTransform currPosition;
    public AffineTransform otherPosition;

    public CircleCollider(GameObject object, GameObject other, AffineTransform currPosition, AffineTransform otherPosition) {
        super(object);

        radius = 30;
        this.other = other;
        this.currPosition = currPosition;
        this.otherPosition = otherPosition;

    }

    @Override
    public void logic(){
        if(detectCircleCollision()){
              System.out.println("Collision");
        }
    }

    @Override
    public void graphics(Graphics2D G){
    }

    /* For Circle Collisions */
    public boolean detectCircleCollision() {
        CircleCollider other = null;
        for (int i = 0; i < this.other.objectLogic.size(); i++) {
            if (this.other.objectLogic.get(i) instanceof CircleCollider) {
                other = (CircleCollider) this.other.objectLogic.get(i);
            }
        }

        if (other != null) {
            int otherRadius = other.radius;
            double x = this.otherPosition.getTranslateX() - this.currPosition.getTranslateX();
            double y = this.otherPosition.getTranslateY() - this.currPosition.getTranslateY();
            double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

            if(radius + otherRadius > distance){
                return true;
            }
        }

        return false;
    }
}
