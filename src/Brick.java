import java.awt.*;
import java.awt.event.KeyEvent;

public class Brick implements Wall{

	int x, y;
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	private boolean live = true;
	
	TankWar tw;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image image = tk.getImage(Brick.class.getClassLoader().getResource("images/brick.png"));
	private static Image image1 = tk.getImage(Brick.class.getClassLoader().getResource("images/brick1.png"));
	private static Image image2 = tk.getImage(Brick.class.getClassLoader().getResource("images/brick2.png"));
	
	public enum Shape {square, tall, fat};
	Shape s;
	
	public Brick(int x, int y, TankWar tw, Shape s) {
		this.x = x;
		this.y = y;
		this.tw = tw;
		this.s = s;
	}

	public void draw(Graphics g) {
		if(!live) {
			tw.walls.remove(this);
			return;
		}
		if(this.s == Shape.square) {
			g.drawImage(image, x, y, null);
		}
		else if(this.s == Shape.tall) {
			g.drawImage(image1, x, y, null);
		}
		else if(this.s == Shape.fat) {
			g.drawImage(image2, x, y, null);
		}
	}
	
	public Rectangle getRect() {
		if(this.s == Shape.tall) {
			return new Rectangle(x, y, WIDTH/2, HEIGHT);
		}
		else if(this.s == Shape.fat) {
			return new Rectangle(x, y, WIDTH, HEIGHT/2);
		}
		else {
			return new Rectangle(x, y, WIDTH, HEIGHT);
		}
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

}
