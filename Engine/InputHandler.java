package Engine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputHandler implements KeyListener {
    public static ArrayList<String> inputs;
    public JFrame currentFrame;

    public InputHandler(JFrame frame){
        inputs = new ArrayList<>();
        currentFrame = frame;
        currentFrame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(!inputs.contains(KeyEvent.getKeyText(e.getKeyCode()))) {
            inputs.add(KeyEvent.getKeyText(e.getKeyCode()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(inputs.contains(KeyEvent.getKeyText(e.getKeyCode()))){
            inputs.remove(KeyEvent.getKeyText(e.getKeyCode()));
        }
    }
}
