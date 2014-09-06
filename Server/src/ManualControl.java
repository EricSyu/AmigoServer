import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ManualControl extends Thread{
	static Boolean flag=false;
	Socket socket;
	String control;
	
	public void Connect(Socket _socket){
		socket=_socket;
	}
	
	@Override
	public void run(){
		try {
			while(true){
				if(flag){
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					
					out.writeChars(control);
					out.flush();
					
					System.out.println(control);
					Thread.sleep(1000);
				}
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
