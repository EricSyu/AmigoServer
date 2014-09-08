import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;


public class Info extends Thread{
	public JTextArea textArea;
	public static String info="";
	public static Boolean Motor, stall;
	public static int x, y, theta;
	public static int[] sensor=new int[8];
	
	public Info( JTextArea _textArea ){
		textArea=_textArea;
	}
	public void run(){
		try {
			int i;
			ServerSocket server=new ServerSocket(101);
			
			while(true){
				Socket socket=server.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				info+=in.readLine()+"\n";
				info+=in.readLine()+"\n";
				info+=in.readLine()+"\n";
				info+=in.readLine()+"\n";
				Motor=in.ready();
				stall=in.ready();
				x=in.read();
				y=in.read();
				theta=in.read();
				for( i=0; i<8; i++ ) sensor[i]=in.read();
				info+="Motor: "+Motor+"\nStall: "+stall+"\n("
						+x+", "+y+")+\n"+"Angle: "+theta+"\n";
				for( i=0; i<8; i++ ) info+="Sensor "+i+": "+sensor[i]+"\n";
				textArea.setText(info);
				
				Thread.sleep(100);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
