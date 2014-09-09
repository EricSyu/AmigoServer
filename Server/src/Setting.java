import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Setting extends Thread implements MonitorProtocol{
	public Boolean BTSwitchopen=false;
	public Boolean BTSwitchclose=false;
	public Boolean BTConnectflag=false;
	public Boolean BTSearchflag=false;
	public Boolean Amigoconnect=false;
	public Boolean Amigounconnect=false;
	public Boolean Wificonnect=false;
	public Boolean Wifiunconnect=false;
	public Boolean MobileCamuncon=false;
	public Boolean MobileCamcon=false;
	public Boolean Forward=false;
	public Boolean Turn=false;
	public Boolean Searchreceive=false;
	public String BTSearch="";
	public int BTMatch=-1, Speed=0;
	public static ServerSocket server;
	
	public void run(){
		try {
			server=new ServerSocket(100);
			while(true){
				if(BTSwitchopen){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(BluetoothSwitch);
					out.flush();
					out.writeInt(Open);
					out.flush();
					socket.close();
					out.close();
					BTSwitchopen=false;
				}else if(BTSwitchclose){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(BluetoothSwitch);
					out.flush();
					out.writeInt(Close);
					out.flush();
					socket.close();
					out.close();
					BTSwitchclose=false;
				}else if(BTConnectflag){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(BluetoothSwitch);
					out.flush();
					out.writeInt(Connected);
					out.flush();
					out.writeInt(BTMatch);
					out.flush();
					socket.close();
					out.close();
					BTConnectflag=false;
				}else if(BTSearchflag){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out.writeInt(BluetoothSwitch);
					out.flush();
					out.writeInt(Search);
					out.flush();
					BTSearch=in.readLine();
					Searchreceive=true;
					Thread.sleep(100);
					in.close();
					socket.close();
					out.close();
					BTSearchflag=false;
				}else if(Amigoconnect){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(AmigoConnSwitch);
					out.flush();
					out.writeInt(Connected);
					out.flush();
					socket.close();
					out.close();
					Amigoconnect=false;
				}else if(Amigounconnect){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(AmigoConnSwitch);
					out.flush();
					out.writeInt(Stopped);
					out.flush();
					socket.close();
					out.close();
					Amigounconnect=false;
				}else if(Wificonnect){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(WifiposSwitch);
					out.flush();
					out.writeInt(Connected);
					out.flush();
					socket.close();
					out.close();
					Wificonnect=false;
				}else if(Wifiunconnect){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(WifiposSwitch);
					out.flush();
					out.writeInt(Stopped);
					out.flush();
					socket.close();
					out.close();
					Wifiunconnect=false;
				}else if(MobileCamcon){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(MobilecamSwitch);
					out.flush();
					out.writeInt(Connected);
					out.flush();
					socket.close();
					out.close();
					MobileCamcon=false;
				}else if(MobileCamuncon){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(MobilecamSwitch);
					out.flush();
					out.writeInt(Stopped);
					out.flush();
					socket.close();
					out.close();
					MobileCamuncon=false;
				}else if(Forward){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(Trans);
					out.flush();
					out.writeInt(Speed*18/10);
					out.flush();
					socket.close();
					out.close();
					Forward=false;
				}else if(Turn){
					Socket socket=server.accept();
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(Rotate);
					out.flush();
					out.writeInt(Speed*18/10);
					out.flush();
					socket.close();
					out.close();
					Turn=false;
				}
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
