import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class WifiSignal extends Thread{
	
	static Boolean flag=true;
	Socket wificlient;
	BufferedReader in;
	BufferedWriter out=null;
	
	@Override
	public void run(){
		try {
			
			ServerSocket wifiSocket=new ServerSocket(861);
			wificlient=wifiSocket.accept();
			while(true){
				while(flag){
					in = new BufferedReader(new 
							InputStreamReader(wificlient.getInputStream()));
					String str = in.readLine();
//					System.out.println("WifiInfo:\n"+str);
					FileWriter fw = new FileWriter("C://CamTest//Positioning.txt");
					fw.write(str+"\n");
					fw.flush();
					fw.close();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
