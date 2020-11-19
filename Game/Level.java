package Game;

import Engine.Component;
import Engine.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Level extends Component {

    public BufferedImage backgroundLevel;

    public Level(GameObject object) {
        super(object);

        try {
            backgroundLevel = ImageIO.read(new File("Assets/empt.png"));
        } catch (Exception e){
        }
    }

    @Override
    public void graphics(Graphics2D G) {
        G.drawImage(backgroundLevel, parent.getPosition(), null);
    }
}
