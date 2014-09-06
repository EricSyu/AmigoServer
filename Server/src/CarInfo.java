import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;


public class CarInfo extends Thread{
	JTextArea textArea;
	JButton zone0, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8;
	Boolean flag=true;
	BufferedReader in;
	String reach;
	static String control;
	ManualControl mc=new ManualControl();
	
	public CarInfo(JTextArea _textArea, JButton _zone0, 
			JButton _zone1, JButton _zone2, JButton _zone3, JButton _zone4, 
			JButton _zone5, JButton _zone6, JButton _zone7, JButton _zone8){
		textArea=_textArea;
		zone0=_zone0;
		zone1=_zone1;
		zone2=_zone2;
		zone3=_zone3;
		zone4=_zone4;
		zone5=_zone5;
		zone6=_zone6;
		zone7=_zone7;
		zone8=_zone8;
	}
	@Override
	public void run(){
		try {
			
			ServerSocket Carserver=new ServerSocket(405);
			
			System.out.println("Info ready to connect...");
			Socket Carsocket=Carserver.accept();
			System.out.println("Info connecting...");
			
			in = new BufferedReader(new 
					InputStreamReader(Carsocket.getInputStream()));
			
			mc.Connect(Carsocket);
			mc.start();
			
			while(flag){
//				System.out.println("In ready to connect...");
				reach=in.readLine();
				
//				System.out.println("In connecting...");
				String[] str=reach.split("Stall:");
				String[] _str=str[1].split("reach:");
				if( reach.indexOf("true")<0 ){
					zone0.setEnabled(false);
					zone1.setEnabled(false);
					zone2.setEnabled(false);
					zone3.setEnabled(false);
					zone4.setEnabled(false);
					zone5.setEnabled(false);
					zone6.setEnabled(false);
					zone7.setEnabled(false);
					zone8.setEnabled(false);
				}else{
					zone0.setEnabled(true);
					zone1.setEnabled(true);
					zone2.setEnabled(true);
					zone3.setEnabled(true);
					zone4.setEnabled(true);
					zone5.setEnabled(true);
					zone6.setEnabled(true);
					zone7.setEnabled(true);
					zone8.setEnabled(true);
				}
				textArea.setText(str[0]+"\nStall: "+_str[0]+"\nReach: "+_str[1]);
			}
			Carserver.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
