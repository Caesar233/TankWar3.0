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
File f = new File("C:\\Documents and Settings\\Administrator\\����\\������.wav"); //����������������ļ����ڵľ���¹��
cb = f.toURL(); 
AudioClip aau; 
aau = Applet.newAudioClip(cb); 
//aau.play();
aau.loop();
//ѭ������ aau.play() ���� aau.stop()ֹͣ���� 
Music frame=new Music(); 

//frame.setBounds(0, 0, 300, 200); 
//frame.setVisible(true); 

} catch (MalformedURLException e) { 
e.printStackTrace(); 
} 

} 
}