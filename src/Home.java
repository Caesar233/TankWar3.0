import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Home implements Wall {
	
	int x, y;
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	private boolean live = true;
	
	TankWar tw;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image image = tk.getImage(Home.class.getClassLoader().getResource("images/home.png"));
	private static Image gameover = tk.getImage(Home.class.getClassLoader().getResource("images/gameover.png"));
	private static Image homeExplode = tk.getImage(Home.class.getClassLoader().getResource("images/homeExplode.jpg"));
	
	public Home(int x, int y, TankWar tw) {
		this.x = x;
		this.y = y;
		this.tw = tw;
	}

	private int count = 0;
	
	public void draw(Graphics g) {
		if(live) {
			g.drawImage(image, x, y, null);
		}
		else if(count == 0){
			g.drawImage(homeExplode, x, y, null);
			g.drawImage(gameover, 574, 383, null);
			AudioClip ac = null;
			try {
				ac = Applet.newAudioClip(TankWar.class.getClassLoader().getResource("musics/lose.wav").toURI().toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			ac.play();
			count ++;
		}
		else if(count == 1) {
			g.drawImage(homeExplode, x, y, null);
			g.drawImage(gameover, 574, 383, null);
		}
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

	
}
