package code;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import code.MonitorProtocol;


public class Setting extends Thread implements MonitorProtocol{
	public static  Boolean BTSwitchopen=false;
	public static Boolean BTSwitchclose=false;
	public static Boolean BTConnectflag=false;
	public static Boolean BTSearchflag=false;
	public static Boolean Amigoconnect=false;
	public static Boolean Amigounconnect=false;
	public static Boolean Wificonnect=false;
	public static Boolean Wifiunconnect=false;
	public static Boolean MobileCamuncon=false;
	public static Boolean MobileCamcon=false;
	public static Boolean Forward=false;
	public static Boolean Turn=false;
	public static Boolean SetAddSpeed=false;
	public static Boolean SetSubtractSpeed=false;
	public static Boolean Searchreceive=false;
	public static Boolean WanderOpen=false;
	public static Boolean WanderClose=false;
	public static String BTSearch="";
	public static int BTMatch=-1;
	public int Speed=0;

	 static DataOutputStream  out = null;
	 

	public static ServerSocket server;
	private static Socket socket2;
	private static DataOutputStream out2;
	

	@Override
	public void run(){
		try {
			server=new ServerSocket(100);
			
			while(true){
				if(BTSwitchopen){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(BluetoothSwitch);
					out.flush();
					out.writeInt(Open);
					out.flush();
					socket.close();
					out.close();
					BTSwitchopen=false;
				}else if(BTSwitchclose){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(BluetoothSwitch);
					out.flush();
					out.writeInt(Close);
					out.flush();
					socket.close();
					out.close();
					BTSwitchclose=false;
				}else if(BTConnectflag){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
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
					out = new DataOutputStream(socket.getOutputStream());
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
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(AmigoConnSwitch);
					out.flush();
					out.writeInt(Connected);
					out.flush();
					socket.close();
					out.close();
					Amigoconnect=false;
				}else if(Amigounconnect){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(AmigoConnSwitch);
					out.flush();
					out.writeInt(Stopped);
					out.flush();
					socket.close();
					out.close();
					Amigounconnect=false;
				}else if(Wificonnect){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(WifiposSwitch);
					out.flush();
					out.writeInt(Connected);
					out.flush();
					socket.close();
					out.close();
					Wificonnect=false;
				}else if(Wifiunconnect){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(WifiposSwitch);
					out.flush();
					out.writeInt(Stopped);
					out.flush();
					socket.close();
					out.close();
					Wifiunconnect=false;
				}else if(MobileCamcon){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(MobilecamSwitch);
					out.flush();
					out.writeInt(Connected);
					out.flush();
					socket.close();
					out.close();
					MobileCamcon=false;
				}else if(MobileCamuncon){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(MobilecamSwitch);
					out.flush();
					out.writeInt(Stopped);
					out.flush();
					socket.close();
					out.close();
					MobileCamuncon=false;
				}else if(Forward){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(Trans);
					out.flush();
					out.writeInt(Speed);
					out.flush();
					socket.close();
					out.close();
					Forward=false;
				}else if(Turn){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(Rotate);
					out.flush();
					out.writeInt(Speed*18/10);
					out.flush();
					socket.close();
					out.close();
					Turn=false;
				}else if(SetAddSpeed){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(MaxTransRotV);
					out.flush();
					out.writeInt(Speed);
					out.flush();
					socket.close();
					out.close();
					SetAddSpeed=false;
				}else if(SetSubtractSpeed){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(MaxTransRotV);
					out.flush();
					out.writeInt(Speed);
					out.flush();
					socket.close();
					out.close();
					SetSubtractSpeed=false;
				}else if(WanderOpen){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(WanderMode);
					out.flush();
					out.writeInt(Open);
					out.flush();
					socket.close();
					out.close();
					WanderOpen=false;
				}else if(WanderClose){
					Socket socket=server.accept();
					out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(WanderMode);
					out.flush();
					out.writeInt(Close);
					out.flush();
					socket.close();
					out.close();
					WanderClose=false;
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
	/****
	public static void relrot(int ang) throws IOException{
		System.out.println( "relrot: "+ang);
		Socket socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 double xt=0;
		out.writeInt(Rotate);
		out.flush();
		if(ang==0)out.writeInt(0);
		if(ang>0)out.writeInt(10);
		else if(ang<0)out.writeInt(-10);
		
		out.flush();
		double tang=Math.abs(ang)/10;
		while(xt<tang){
			xt+=0.1;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		 out.close();
		 socket.close();
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(Rotate);
		 out.flush();
		 out.writeInt(0);
		 out.flush();
		 out.close();
		 socket.close();
		
	}
	static int rotc=0;
	static boolean po=false;
	static int ta=0;
	static int status=0;
	public  static void setrotang(int ang) throws IOException, InterruptedException{
		rotc++;
		System.out.println( "setrotang"+ang);
		
		int ttx=(int) (Math.abs(ang-Info.carang)/9);
		if(ang==0)ttx=(int) (Math.abs(360-Info.carang)/9);
		Socket socket=null;
		 socket=Setting.server.accept();
		out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(MaxRotV);
		 out.flush();
		 out.writeInt(10);
		 out.flush();
		 out.close();
		 socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(AbsoluteHeading);
		 out.flush();
		 if(ta==0){
			 if(ang<=180){
					po=true;
					ta++;
				}else{
					po=false;
					ta++;
				}
		 }
		 double fixang=0;
		 if(po==true){
			 fixang=ang-ang/30;
			 status=0;
//			 if(Info.carang>=180&&ang>180){fixang=ang-ang/15;
//			 status=1;
			 if(Info.carang>180&&ang==270){
				 fixang=ang-ang/30-10;
					status=2;
					
				}
			 if(Info.carang>180&&ang==0){
					fixang=360-360/30-15;  
					status=2;
					
				}
		}
		
		 else{
			 fixang=ang-(360-ang)/30;
			 
		 }
		
		 
		 out.writeInt((int) fixang);
		 out.flush();
		 
		 Thread.sleep(ttx*1000+1000);
		 
		 socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(Rotate);
		 out.flush();
		 out.writeInt(0);
		 out.flush();
		
		 out.close();
		 socket.close();
		 int x=0;
		 Thread.sleep(250);
		 
//		if(Info.carang>350&&Math.abs(Info.carang-360)>2&&ang==0&&rotc==1){
//			
//			
//			 relrot(45); 
//			 relrot(0); 
//			 setrotang(ang);
//		}
//		 if((Math.abs(Info.carang-ang)>2)&&rotc==1){
//			 System.out.println( "Math.abs(Info.carang-ang)");
//		
//		     relrot(45); 
//		     relrot(0); 
//		     setrotang(ang); 	
//		 }
		 rotc=0;
		
	}
	****/
}