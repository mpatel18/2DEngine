package Demo;

import Engine.*;

// import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Demo {

    public static EngineCore engine;
    public static InputHandler inputHandler;
    public static AssetsCenter assetsCenter;
    public static ArrayList<BufferedImage> sprites;

    public static int size;
    public static int ratio;
    public static BufferedImage background;


    public static void main(String[] args) throws ResourceNotFound {
        start();
    }

    public static void start() throws ResourceNotFound {

        assetsCenter = new AssetsCenter("Assets/");
        sprites = assetsCenter.getImageList("boiS.png");
        size = 50;
        ratio = 2;

        GameObject camera = new GameObject("Camera", new AffineTransform());
        Component camComp = new Camera(camera);
        camera.addLogic(camComp);
        camera.addGraphic(camComp);

        GameObject level = new GameObject("Level", new AffineTransform());
        Component levelComp = new Level(level);
        level.addLogic(levelComp);
        level.addGraphic(levelComp);

        engine = new EngineCore(size, ratio, 10, "game", "/", camera);
        inputHandler = new InputHandler(engine.Frame);

        GameObject player = new GameObject("Player", new AffineTransform());
        Component playerComp = new Player(player);
        player.addLogic(playerComp);
        player.addGraphic(playerComp);

        GameObject enemy = new GameObject("Enemy", new AffineTransform());
        Component enemyComp = new Enemy(enemy);
        enemy.addLogic(enemyComp);
        enemy.addGraphic(enemyComp);

        Component enemyCircleCollider = new CircleCollider(enemy, player, ((Enemy) enemyComp).getPos(), ((Player) playerComp).getPos());
        Component playerCircleCollider = new CircleCollider(player, enemy, ((Player) playerComp).getPos(), ((Enemy) enemyComp).getPos());

        player.addLogic(playerCircleCollider);
        enemy.addLogic(enemyCircleCollider);

        engine.AddObject(camera);
        engine.AddObject(level);
        engine.AddObject(player);
        engine.AddObject(enemy);

        engine.start();
    }
}
