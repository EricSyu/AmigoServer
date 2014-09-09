import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Monitor extends Thread{
	private JLabel img, cal;
	static Boolean flag=true;
//	static Socket client;
	public static final String file_name = "D:/CamTest/monitor";
	public static String getpicname="";
	static ServerSocket serverSocket;
	
	public Monitor( JLabel _img, JLabel calender ){
		img=_img;
		cal=calender;
	}
	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, Transparency.TRANSLUCENT);
	    Graphics2D g2d = bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	@Override
	public void run(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS");
		
		try {
			serverSocket = new ServerSocket(168);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(true){
				if(flag) { 
//					System.out.println("Server: Connect");
					Socket client = serverSocket.accept();
//					System.out.println("Server: Connecting...");
					
					Date date=new Date();
					String time=sdf.format(date);
					 
					OutputStream out = new FileOutputStream(file_name+time+".jpeg");
					 
					byte buf[] = new byte[1024];
					int len;

					 // Ū�J�q����ݶǨӪ��Ӥ�
					InputStream inputStream = client.getInputStream();
					
					while ((len = inputStream.read(buf)) != -1) {
						// �g�J��q����
						out.write(buf, 0, len);
					}
					
					File file=new File(file_name+time+".jpeg");
					BufferedImage image=ImageIO.read(file);
					BufferedImage resizedImage=resize(image, 520, 300);
					ImageIcon imgicon = new ImageIcon(resizedImage);
					
					img.setIcon(imgicon);
					cal.setText(time);
					
					out.close();
					inputStream.close();
					client.close();
					getpicname=file_name+time+".jpeg";
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
