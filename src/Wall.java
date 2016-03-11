import java.awt.*;
import java.awt.event.KeyEvent;

public interface Wall {
	public void draw(Graphics g);
	
	public Rectangle getRect();
	
	public boolean isLive();

	public void setLive(boolean live);

}
