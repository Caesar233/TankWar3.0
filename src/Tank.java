import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.*;

import BGMTest.Music;

public class Tank {
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	public static final Color MYTANK_COLOR = Color.RED;
	public static final Color ENEMYTANK_COLOR = Color.BLUE;
	
	private boolean live = true;
	
	private int life = 100;
	private BloodBar bb = new BloodBar();
	
	TankWar tw;
	
	private boolean good;
	
	private int x , y;
	private int oldX, oldY;
	
	private static Random r = new Random();
	
	private boolean bL = false, bU = false, bR = false, bD = false;
		
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.U;
	
	private int step = r.nextInt(12) + 3;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] myTankImage = null;
	private static Image[] enemyTankImage = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	private static Map<String, Image> imgs2 = new HashMap<String, Image>();
	
	static {
		myTankImage = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource("images/myTankL.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/myTankLU.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/myTankU.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/myTankRU.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/myTankR.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/myTankRD.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/myTankD.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/myTankLD.png"))
		};
		
		imgs.put("L", myTankImage[0]);
		imgs.put("LU", myTankImage[1]);
		imgs.put("U", myTankImage[2]);
		imgs.put("RU", myTankImage[3]);
		imgs.put("R", myTankImage[4]);
		imgs.put("RD", myTankImage[5]);
		imgs.put("D", myTankImage[6]);
		imgs.put("LD", myTankImage[7]);
	}
	
	static {
		enemyTankImage = new Image[] {
				tk.getImage(Music.class.getClassLoader().getResource("images/enemyTankL.png")),
				tk.getImage(Music.class.getClassLoader().getResource("images/enemyTankLU.png")),
				tk.getImage(Music.class.getClassLoader().getResource("images/enemyTankU.png")),
				tk.getImage(Music.class.getClassLoader().getResource("images/enemyTankRU.png")),
				tk.getImage(Music.class.getClassLoader().getResource("images/enemyTankR.png")),
				tk.getImage(Music.class.getClassLoader().getResource("images/enemyTankRD.png")),
				tk.getImage(Music.class.getClassLoader().getResource("images/enemyTankD.png")),
				tk.getImage(Music.class.getClassLoader().getResource("images/enemyTankLD.png"))
		};
		
		imgs2.put("L", enemyTankImage[0]);
		imgs2.put("LU", enemyTankImage[1]);
		imgs2.put("U", enemyTankImage[2]);
		imgs2.put("RU", enemyTankImage[3]);
		imgs2.put("R", enemyTankImage[4]);
		imgs2.put("RD", enemyTankImage[5]);
		imgs2.put("D", enemyTankImage[6]);
		imgs2.put("LD", enemyTankImage[7]);
	}
	
	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}
	
	public Tank(int x, int y, boolean good, Direction dir, TankWar tw) {
		this(x, y, good);
		this.dir = dir;
		this.tw = tw;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			if(!good){
				tw.tanks.remove(this);
			}
			return;
		}
		
		if(good)
			bb.draw(g);
		
		if(good){
			switch(ptDir) {
			case L :
				g.drawImage(imgs.get("L"), x, y, null);
				break;
			case LU :
				g.drawImage(imgs.get("LU"), x, y, null);
				break;
			case U :
				g.drawImage(imgs.get("U"), x, y, null);
				break;
			case RU :
				g.drawImage(imgs.get("RU"), x, y, null);
				break;
			case R :
				g.drawImage(imgs.get("R"), x, y, null);
				break;
			case RD :
				g.drawImage(imgs.get("RD"), x, y, null);
				break;
			case D :
				g.drawImage(imgs.get("D"), x, y, null);
				break;
			case LD :
				g.drawImage(imgs.get("LD"), x, y, null);
				break;
			}
		}
		else
		{
			switch(ptDir) {
			case L :
				g.drawImage(imgs2.get("L"), x, y, null);
				break;
			case LU :
				g.drawImage(imgs2.get("LU"), x, y, null);
				break;
			case U :
				g.drawImage(imgs2.get("U"), x, y, null);
				break;
			case RU :
				g.drawImage(imgs2.get("RU"), x, y, null);
				break;
			case R :
				g.drawImage(imgs2.get("R"), x, y, null);
				break;
			case RD :
				g.drawImage(imgs2.get("RD"), x, y, null);
				break;
			case D :
				g.drawImage(imgs2.get("D"), x, y, null);
				break;
			case LD :
				g.drawImage(imgs2.get("LD"), x, y, null);
				break;
			}
		}
		
		move();
	}

	void move() {
		
		this.oldX = x;
		this.oldY = y;
		
		switch(dir) {
		case L :
			x -= XSPEED;
			break;
		case LU :
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U :
			y -= YSPEED;
			break;
		case RU :
			x += XSPEED;
			y -= YSPEED;
			break;
		case R :
			x += XSPEED;
			break;
		case RD :
			x += XSPEED;
			y += YSPEED;
			break;
		case D :
			y += YSPEED;
			break;
		case LD :
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP :
			break;
		}
		
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
		
		if(x < 0) 
			x = 0;
		if(y < 31) 
			y = 31;
		if(x + Tank.WIDTH > TankWar.GAME_WIDTH) 
			x = TankWar.GAME_WIDTH - Tank.WIDTH;
		if(y + Tank.HEIGHT > TankWar.GAME_HEIGHT) 
			y = TankWar.GAME_HEIGHT - Tank.HEIGHT;
		
		if(!good) {
			if(step == 0) {
				Direction[] dirs = Direction.values();
				step = r.nextInt(12) + 3;
				int rn = r.nextInt(dirs.length);
				dir = dirs[rn];
			}
			step --;
			
			if(r.nextInt(40) > 38)
				this.fire();
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_F2 :
			if(!this.live) {
				this.live = true;
				this.life = 100;
				for(int i=0; i<tw.walls.size(); i++) {
					Wall w = tw.walls.get(i);
					if(w instanceof Home) {
						w.setLive(true);
					}
				}
			}
			break;
		case KeyEvent.VK_LEFT :
			bL = true;
			break;
		case KeyEvent.VK_UP :
			bU = true;
			break;
		case KeyEvent.VK_RIGHT :
			bR = true;
			break;
		case KeyEvent.VK_DOWN :
			bD = true;
			break;
		}
		locateDirection();
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_CONTROL :
			fire();
			break;
		case KeyEvent.VK_LEFT :
			bL = false;
			break;
		case KeyEvent.VK_UP :
			bU = false;
			break;
		case KeyEvent.VK_RIGHT :
			bR = false;
			break;
		case KeyEvent.VK_DOWN :
			bD = false;
			break;
		case KeyEvent.VK_A :
			superFire();
			break;
		}
		locateDirection();
	}
	
	void locateDirection() {
		if(bL && !bU && !bR && !bD) 
			dir = Direction.L;
		else if(bL && bU && !bR && !bD) 
			dir = Direction.LU;
		else if(!bL && bU && !bR && !bD) 
			dir = Direction.U;
		else if(!bL && bU && bR && !bD) 
			dir = Direction.RU;
		else if(!bL && !bU && bR && !bD) 
			dir = Direction.R;
		else if(!bL && !bU && bR && bD) 
			dir = Direction.RD;
		else if(!bL && !bU && !bR && bD) 
			dir = Direction.D;
		else if(bL && !bU && !bR && bD) 
			dir = Direction.LD;
		else if(!bL && !bU && !bR && !bD) 
			dir = Direction.STOP;
	}

	public Missile fire() {
		if(!live)
			return null;
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.WIDTH/2;
		Missile m = new Missile(x, y, good, ptDir, this.tw);
		tw.missiles.add(m);
		return m;
	}
	
	public Missile fire(Direction dir) {
		if(!live)
			return null;
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.WIDTH/2;
		Missile m = new Missile(x, y, good, dir, this.tw);
		tw.missiles.add(m);
		return m;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	public boolean isGood() {
		return good;
	}
	
	public boolean collidesWithWall(Wall w) {
		if(this.live && this.getRect().intersects(w.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}

	public boolean collidesWithConcrete(Concrete c) {
		if(this.live && this.getRect().intersects(c.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}
	
	public boolean collidesWithBrick(Brick b) {
		if(this.live && this.getRect().intersects(b.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}
	
	public boolean collidesWithWater(Water w) {
		if(this.live && this.getRect().intersects(w.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}
	
	public boolean collidesWithHome(Home h) {
		if(this.live && this.getRect().intersects(h.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}
	
	private void stay() {
		x = oldX;
		y = oldY;
	}
	
	public boolean collidesWithWalls(java.util.List<Wall> walls) {
		for(int i=0; i<walls.size(); i++) {
			Wall w = walls.get(i);
			if(this != w) {
				if(this.live && w.isLive() && this.getRect().intersects(w.getRect())) {
					this.stay();
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean collidesWithTanks(java.util.List<Tank> tanks) {
		for(int i=0; i<tanks.size(); i++) {
			Tank t = tanks.get(i);
			if(this != t) {
				if(this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					this.stay();
					t.stay();
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void superFire() {
		Direction[] dirs = Direction.values();
		for(int i=0; i<8; i++) {
			fire(dirs[i]);
		}
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	private class BloodBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y-10, WIDTH, 10);
			int w = WIDTH * life/100;
			g.fillRect(x, y-10, w, 10);
			g.setColor(c);
		}
	}
	
	public boolean eat(Blood b) {
		if(this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			this.life = 100;
			b.setLive(false);
			AudioClip ac = null;
			try {
				ac = Applet.newAudioClip(TankWar.class.getClassLoader().getResource("musics/award.wav").toURI().toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			ac.play();
			return true;
		}
		return false;
	}
	
}
