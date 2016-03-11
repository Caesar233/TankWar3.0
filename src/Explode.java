import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Explode {
	int x, y;
	private boolean live = true;
	
	private TankWar tw;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image[] imgs = {
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode1.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode2.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode3.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode4.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode5.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode6.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode7.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode8.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode9.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode10.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode11.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode12.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode13.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode14.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode15.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode16.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode17.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode18.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/explode19.png"))
	};
	
	int step = 0;
	
	private static boolean init = false;
	
	public Explode(int x, int y, TankWar tw) {
		this.x = x;
		this.y = y;
		this.tw = tw;
	}
	
	public void draw(Graphics g) {
		
		if(!init) {
			for (int i = 0; i < imgs.length; i++) {
				g.drawImage(imgs[i], -100, -100, null);
			}
			init = true;
		}
		
		if(!live) {
			tw.explodes.remove(this);
			return;
		}
		
		if(step == imgs.length) {
			live = false;
			step = 0;
			return;
		}
		
		g.drawImage(imgs[step], x, y, null);
		
		step ++;
	}

}
