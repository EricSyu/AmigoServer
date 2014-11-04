package code;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
	static Boolean flag=true, firstSend=true;

	int count=0;
	public static String getpicname="";

	public static final String file_name = "C:/CamTest/monitor/";

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
		int pics=0, header=0;
		
		try {
			serverSocket = new ServerSocket(168);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(true){
				if(flag) { 
					Socket client = serverSocket.accept();
					
					Date date=new Date();
					String time=sdf.format(date);
					
					pics++;
					OutputStream out = new FileOutputStream(file_name+header+"_"+pics+".jpeg");
//					OutputStream _out = new FileOutputStream("D:/XAMPP/htdocs/Monitor/client/monitor/cloud_"+count+".jpeg");
					
					byte buf[] = new byte[1024];
					byte bigbuf[] = new byte[20000];
					int len, offset=0;
					 // �桡蕭嚙賤区嚙踝蕭�Ｘ�嚙賤駆嚙踝蕭�橥蕭�佇��歹蕭撉ⓕ�嚙褒门蕭�佇��歹蕭�賂蕭�⒪蕭
					InputStream inputStream = client.getInputStream();
					
					while ((len = inputStream.read(buf)) != -1) {
						// 嚙賤区嚙踝蕭�ｇ蕭嚙賤駆嚙踝蕭閮恠蕭�蕭嚙賢�嚙賡豲簧韧
						out.write(buf, 0, len);
//						_out.write(buf, 0, len);
						for( int i=0; i<len; ++i ){
							bigbuf[offset++] = buf[i];
						}
					}
					System.out.println("null:"+buf);
					if(firstSend){
						CamSendThread CamSend=new CamSendThread(bigbuf);
						CamSend.start();
					}
					
					File file=new File(file_name+header+"_"+pics+".jpeg");
					BufferedImage image=ImageIO.read(file);
					
					BufferedImage resizedImage=resize(image, 520, 300);
					ImageIcon imgicon = new ImageIcon(resizedImage);
					
					img.setIcon(imgicon);
					cal.setText(time);
					
					out.close();
//					_out.close();
					inputStream.close();
					client.close();
					getpicname=file_name+header+"_"+pics+".jpeg";
					if( pics>=280 && pathalgo.visfin ){
						AviCreator avi=new AviCreator( header, pics );
						avi.start();
						header++;
						pics=0;
					}
//					if( count<200 ) count++;
//					else count=0;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class CamSendThread extends Thread{
		private byte byteBuffer[] = new byte[1024];
		private OutputStream Out;
		static ServerSocket returnSocket;
		public CamSendThread(byte[] buf){
			byteBuffer= buf;
		}

		@Override
		public void run() {
			try{
				firstSend=false;
				returnSocket = new ServerSocket(1133);
				System.out.println("Ready");
				Socket client = returnSocket.accept();
				System.out.println("connect");
				Out = client.getOutputStream();
				
				int amount;
				ByteArrayInputStream inputstream = new ByteArrayInputStream(byteBuffer);
				
				while ((amount = inputstream.read(byteBuffer)) != -1) Out.write(byteBuffer, 0, amount);
				Out.flush();
				System.out.println("Suc");
				Out.close();
				client.close();
				returnSocket.close();
				firstSend=true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

