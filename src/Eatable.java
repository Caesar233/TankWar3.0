import java.awt.*;

public interface Eatable {
	
	public void draw(Graphics g);
	
	public Rectangle getRect();
	
	public boolean isLive();

	public void setLive(boolean live);
}
