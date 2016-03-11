import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.util.List;

public class TankWar extends Frame{
	public static final int GAME_WIDTH = 1400;
	public static final int GAME_HEIGHT = 900;
	
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	
	Tank myTank = new Tank(531, 825, true, Direction.STOP, this);
	
	Blood b = new Blood();
	
	List<Missile> missiles = new ArrayList<Missile>();
	List<Explode> explodes = new ArrayList<Explode>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<Wall> walls = new ArrayList<Wall>();
	
	Image offScreenImage = null;
	
	public static void main(String[] args) {
		TankWar tw = new TankWar();
		tw.launchFrame();
	}
	
	public void launchFrame() {
		
		AudioClip ac = null;
		try {
			ac = Applet.newAudioClip(TankWar.class.getClassLoader().getResource("musics/tankWarBGM.wav").toURI().toURL());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		ac.play();
		
		int initTankCount = Integer.parseInt(PropertyManager.getProperty("initTankCount"));
		
		for(int i=0; i<initTankCount; i++) {
			tanks.add(new Tank(100 + 100*(i+1), 100, false, Direction.D, this));
		}
		
		walls.add(new Brick(660, 840, this, Brick.Shape.fat));
		walls.add(new Brick(700, 840, this, Brick.Shape.fat));
		walls.add(new Brick(660, 860, this, Brick.Shape.tall));
		walls.add(new Brick(720, 860, this, Brick.Shape.tall));
		walls.add(new Home(680, 860, this));
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
		
		addKeyListener(new KeyMonitor());
		
		setLayout(null);
		setLocation(250, 100);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setTitle("TankWar");
		setBackground(BACKGROUND_COLOR);
		setResizable(false);
		setVisible(true);
		
		new Thread(new PaintThread()).start();
//		new Thread(new TankThread()).start();
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("missiles count:" + missiles.size(), 30, 60);
		g.drawString("explodes count:" + explodes.size(), 30, 80);
		g.drawString("tanks count:" + tanks.size(), 30, 100);
		g.drawString("tanks life:" + myTank.getLife(), 30, 120);
		g.setColor(c);
		
		if(tanks.size() <= 0) {
//			for(int i=0; i<Integer.parseInt(PropertyManager.getProperty("reProduceTankCount")); i++) {
//				tanks.add(new Tank(100 + 100*(i+1), 100, false, Direction.D, this));
//			}
			tanks.add(new Tank(0, 30, false, Direction.D, this));
			tanks.add(new Tank(640, 30, false, Direction.D, this));
			tanks.add(new Tank(1360, 30, false, Direction.D, this));
		}
		
		for(int i=0; i<missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTanks(tanks);
			m.hitTank(myTank);
			m.hitWalls(walls);
			m.draw(g);
//			if(!m.isLive()) 
//				missiles.remove(m);
//			else
//				m.draw(g);
		}
		
		for(int i=0; i<explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		for(int i=0; i<tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.collidesWithWalls(walls);
			t.collidesWithTanks(tanks);
			t.draw(g);
		}
		
		for(int i=0; i<walls.size(); i++) {
			Wall w = walls.get(i);
			w.draw(g);
		}
		
		myTank.draw(g);
		b.draw(g);
		myTank.eat(b);
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(BACKGROUND_COLOR);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private class PaintThread implements Runnable {
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	private class TankThread implements Runnable {
//		TankWar tw;
//		public void run() {
//			boolean flag = true;
//			while(flag) {
//				tanks.add(new Tank(0, 30, false, Direction.D, tw));
//				tanks.add(new Tank(640, 30, false, Direction.D, tw));
//				tanks.add(new Tank(1360, 30, false, Direction.D, tw));
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	private class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);			
		}
		
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}
	}
	
	
}














