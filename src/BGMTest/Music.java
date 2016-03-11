package BGMTest;
import java.applet.AudioClip; 

import java.io.*; 
import java.applet.Applet; 
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException; 
import java.net.URL;
import java.util.HashMap;
import java.util.Map; 



public class Music extends Frame{ 
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] enemyTankImage = null;
	private static Image[] enemyTankIamges = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	
	
	

public Music(){ 
super(); 
} 
public static void main(String args[]) { 
try { 
URL cb; 
File f = new File("C:\\Documents and Settings\\Administrator\\桌面\\刀出鞘.wav"); //引号里面的是音乐文件所在的绝对鹿筋
cb = f.toURL(); 
AudioClip aau; 
aau = Applet.newAudioClip(cb); 
//aau.play();
aau.loop();
//循环播放 aau.play() 单曲 aau.stop()停止播放 
Music frame=new Music(); 

//frame.setBounds(0, 0, 300, 200); 
//frame.setVisible(true); 

} catch (MalformedURLException e) { 
e.printStackTrace(); 
} 

} 
}