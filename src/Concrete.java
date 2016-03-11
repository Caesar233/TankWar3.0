import java.awt.*;
import java.awt.event.KeyEvent;

public class Concrete implements Wall{

	int x, y;
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	private boolean live = true;
	
	TankWar tw;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image image = tk.getImage(Concrete.class.getClassLoader().getResource("images/concrete.png"));
	
	public Concrete(int x, int y, TankWar tw) {
		this.x = x;
		this.y = y;
		this.tw = tw;
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
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
