package code;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class vposini extends Thread{
	int uzone=360;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] buffer = new byte[100];
		  
		try {      
			 DatagramSocket ds=new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("127.0.0.1");       
			byte[] sendData = new byte[1024];       
			byte[] receiveData = new byte[1024];       
			  
			boolean avoid=false;
			String sentence =  "10";   				
			sendData = sentence.getBytes();       
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1023);       
			ds.send(sendPacket); 
			
			sentence = Monitor.getpicname;  
//			sentence="D:\\CamTest\\2_947.jpeg";
			sendData = sentence.getBytes();       
			 sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1023);       
			ds.send(sendPacket); 
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
			System.out.println("debug1");
			ds.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());      
			System.out.println("FROM SERVER:" + modifiedSentence);
			
			ds.receive(receivePacket);
			 modifiedSentence = new String(receivePacket.getData());      
			System.out.println("FROM SERVER:" + modifiedSentence);
			int xx=modifiedSentence.indexOf("zone");
			
				
			int tan=Integer.parseInt(modifiedSentence.substring(xx+4, xx+5));
				
			
				System.out.println("zone: "+tan);
				uzone=tan;
				Position.start=tan;
			ds.close();
				

		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
