package Engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.util.ArrayList;

import javax.swing.JFrame;

public class EngineCore extends Canvas implements Runnable  {

	public int Width,
			   Height,
			   Scale;
	public String Name;
	public JFrame Frame;
	public Boolean running;
	public static GameObject camera;
	public AssetsCenter assetsCenter;
	public ArrayList<BufferedImage> sprites;
	
	private int SleepTime = 0;
	
	public ArrayList<GameObject> elements, tempElements;

	private BufferedImage BackGround;
	private int[] pixles;
	
	public static int FCount = 0;
	public static int LCount = 0;
	
	private String path;

	public static int runSpeed = 60;
	public static AssetsCenter assets;
	public static InputHandler inputs;
	
	public EngineCore(int Size, int Ratio, int Scale, String Name, String path, GameObject camera) throws ResourceNotFound{
		
		
		//initiation the variables
		this.Height = Size;
		this.Width = Size * Ratio;
		this.Scale = Scale;
		this.Name = Name;
		this.camera = camera;
		assetsCenter = new AssetsCenter("Assets/");
		sprites = assetsCenter.getImageList("boiS.png");
		
		this.path = path;
		this.Frame = new JFrame(Name);
		
		// Starting the data collection/storage systems
		inputs = new InputHandler(Frame);
		assets = new AssetsCenter(this.path);
		elements = new ArrayList<>();
		
		
		// Hard-coding the sky-box (not the best thing to do)
		this.BackGround = new BufferedImage (this.Width,this.Height,BufferedImage.TYPE_INT_RGB);
		this.pixles = ((DataBufferInt)BackGround.getRaster().getDataBuffer()).getData();
		
		// Setting up the canvas 
		setMinimumSize(new Dimension(this.Width*this.Scale, this.Height * this.Scale));
		setMaximumSize(new Dimension(this.Width*this.Scale, this.Height * this.Scale));
		setPreferredSize(new Dimension(this.Width*this.Scale, this.Height * this.Scale));
		
		this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.Frame.setLayout(new BorderLayout());
		
		this.Frame.add(this, BorderLayout.CENTER);
		this.Frame.pack();
		
		this.Frame.setResizable(true);
		this.Frame.setLocationRelativeTo(null);
		this.Frame.setVisible(true);
	}
	
	public synchronized void start() {
		this.running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		this.running = false;
		new Thread(this).start();
	}
	
	
	@Override
	public void run() {
		long now = System.nanoTime();
		long lasttime = System.nanoTime();
		long LT = System.nanoTime();
		double nsPL = 1000000000D/ runSpeed;
		
		double delta = 0;
		
		while(this.running) {
			Frame.requestFocusInWindow();
			tempElements = new ArrayList<GameObject>(elements);
			boolean render = true;

			//Time Management variables
			now = System.nanoTime();
			delta += (now - lasttime) / nsPL;
			lasttime = now;
			
			//Logic set to perform only 60 times per second
			if(delta >= 1) {
				LCount++;
				logic();
				delta -=1;
				render = true;
			}
			
			//Sleep to limit the number of graphic updates (too much would slow the logic too)
			try {
				Thread.sleep(SleepTime);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(render) {
				FCount++;
				try {
					graphic();
				} catch (ResourceNotFound e){
				}
			}
			
			//Graphics update free to use all available resources. 
			if(System.nanoTime() - LT >= 1000000000) {
				
				LT += 1000000000;
				System.out.println("FPS: " + FCount +" LPS: " + LCount);
				LCount = 0;
				FCount = 0;
			}
		}
	}
	
	public void logic() {
		for(int i = GameObject.Min; i <= GameObject.Max; i++) {
			for(GameObject j: tempElements)
				j.logic(i);
		}
	}
	
	
	
	
	public void graphic() throws ResourceNotFound {
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
			
		Graphics2D G = (Graphics2D) bs.getDrawGraphics();
		G.clearRect(0, 0, this.Width*10, this.Height*10);
		G.setTransform(camera.getPosition());
		//Background color (in most cases you will have an element background, which draws a picture instead)
		//G.setColor(Color.decode("#33FFFF"));
		//G.fillRect(0, 0, Width*Scale, Height*Scale);

		//calling the graphic methods of every element
		for(int i = GameObject.Min; i <= GameObject.Max; i++) {
			 for(GameObject j: tempElements)
				 j.graphic(i,G);
		}

		G.dispose();
		bs.show();
	}
	
	public void AddObject(GameObject newObject) {
		elements.add(newObject);
	}
	
}
