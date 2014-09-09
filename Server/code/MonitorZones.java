package code;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MonitorZones extends Thread{
	static Boolean flag=false, reach=true, core=true;
	int[] array;
	int i;
	public MonitorZones(){
	}
	
	@Override
	public void run(){
		try {
			
			ServerSocket server=new ServerSocket(404);
			
			while(core){
				Thread.sleep(500);
				
				if(flag){
					Socket socket=server.accept();
					
					
//					flag=false;''
					socket.close();
				}
			}
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
