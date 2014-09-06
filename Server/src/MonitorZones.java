import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MonitorZones extends Thread{
	static Boolean flag=false, reach=true, core=true;
	int[] array;
	int i;
	CarInfo ci;
	public MonitorZones(CarInfo Info){
		ci=Info;
	}
	
	@Override
	public void run(){
		try {
			
			ServerSocket server=new ServerSocket(404);
			
			while(core){
				Thread.sleep(500);
				
				if(flag){
					Socket socket=server.accept();
					
					while(ci.reach.indexOf("true")>-1 && core){
						DataOutputStream out = new DataOutputStream(socket.getOutputStream());
						
//						out.writeInt(1);
//						out.flush();
						
						out.writeInt(array.length);
						out.flush();
						
						for( i=0; i<array.length; i++ ){
							out.writeInt(array[i]);
							out.flush();
							
							System.out.println(array[i]);
						}
						Thread.sleep(1000);
					}
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
