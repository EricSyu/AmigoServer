import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Propel extends Thread{
	static int start, end;
	static Boolean flag=false, core=true;
//	Socket socket;
	int ini=9999, i;
	int[][] zone404={
			//	0   1   2   3   4     5   6   7   8
				{0, 360, ini, ini, ini, 540, ini, ini, ini},//0
				{360, 0, 300, ini, 300, ini, ini, ini, ini},//1
				{ini, 300, 0, 360, 423, ini, ini, ini, ini},//2
				{ini, ini, 360, 0, ini, ini, ini, ini, 360},//3
				{ini, 300, 423, ini, 0, ini, ini, 540, ini},//4
				{540, ini, ini, ini, ini, 0, 360, ini, ini},//5
				{ini, ini, ini, ini, ini, 360, 0, 350, ini},//6
				{ini, ini, ini, ini, 540, ini, 350, 0, 350},//7
				{ini, ini, ini, 360, ini, ini, ini, 350, 0},//8
		};
	
	@Override
	public void run(){
		try {
			
			ServerSocket server=new ServerSocket(404);
			
			while(core){
				Thread.sleep(500);
				if(flag){
					System.out.println("Ready to connect...");
					Socket socket=server.accept();
					System.out.println("Connecting...");
					
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					
//					out.writeInt(0);
//					out.flush();
					
					FloydWarshall fw=new FloydWarshall();
					fw.Cal(zone404);
					int[] array=fw.FindPathArray(start, end);
					out.writeInt(array.length);
					out.flush();
					
					for( i=0; i<array.length; i++ ){
						out.writeInt(array[i]);
						out.flush();
						
						System.out.println(array[i]);
					}
    				
//    				out.close();
    				flag=false;
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
