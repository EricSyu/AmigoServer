package code;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
 
import javax.swing.JButton;
import javax.swing.JTextArea;
 
 
public class Info extends Thread{
	public JTextArea textArea;
	public JButton BTbtn, Wifibtn, Cambtn, Amigobtn, OneClick;
	public static String BTstatus="", Amigostatus="", Wifistatus="", Camstatus="";
	public static int x=0, y=0, theta=0, Motor=0, stall=0,comdeg=-1;
	public static int[] sensor=new int[]{-100, -100, -100, -100, -100, -100, -100, -100};
	static double carang=0;
	public static int secpo=-1;
	public void Initial( JTextArea _textArea, JButton _BTbtn, JButton _Amigobtn
			, JButton _Wifibtn, JButton _Cambtn, JButton _OneClick ){
		textArea=_textArea;
		BTbtn=_BTbtn;
		Wifibtn=_Wifibtn;
		Cambtn=_Cambtn;
		Amigobtn=_Amigobtn;
		OneClick=_OneClick;
		
	}
	@Override
	public void run(){
		try {
			int i, cnt=0;
			Boolean initial=false;
			ServerSocket server=new ServerSocket(200);
			
			while(true){
				String info="";
				Socket socket=server.accept();
				BufferedReader brin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				BTstatus=brin.readLine();
				Amigostatus=brin.readLine();
				Wifistatus=brin.readLine();
				Camstatus=brin.readLine();
				Motor=Integer.parseInt(brin.readLine());
				stall=Integer.parseInt(brin.readLine());
				x=(Integer.parseInt(brin.readLine()));
				y=(Integer.parseInt(brin.readLine()));
				theta=Integer.parseInt(brin.readLine());
				comdeg=(int) Double.parseDouble(brin.readLine());
				if(pathalgo.po==true){
					if(pathalgo.status==0) {
						carang=theta+theta/29.0f;
//					if(pathalgo.carx>550)carang=theta+theta/29.0f;
				}	
//					if(Setting.status==1) carang=theta+theta/29.0f+3;	
					if(pathalgo.status==2) {carang=(theta+1)%360;}
					
				}
				else{
					 
					 if(pathalgo.status==3) carang=theta-(360-theta)/29.0f;
					 if(pathalgo.status==4) carang=theta-(360-theta)/15.0f;
					 if(pathalgo.status==5) carang=theta-27;
				}
				
				
				
			
				for( i=0; i<8; i++ ) sensor[i]=Integer.parseInt(brin.readLine());
				
				info+="Blustooth: "+BTstatus+"\nAmigostatus: "+Amigostatus+"\nWifistatus: "
						+Wifistatus+"\nCamstatus: "+Camstatus+"\nMotor: "+Motor+"\nStall: "
						+stall+"\n("+x+", "+y+")\n"+"Angle: "+theta+"\n"+"compassdegree: "+comdeg+"\n";
				for( i=0; i<8; i++ ) info+="Sensor "+i+": "+sensor[i]+"\n";
				
				textArea.setText(info);
				
				if(BTstatus.equals("Close")){
					BTbtn.setText("Open");
					OneClick.setText("OneClickConnect");
					Setting.Searchreceive=false;
				}
				else if(BTstatus.equals("Open") && !Setting.Searchreceive) BTbtn.setText("Search");
				else if(BTstatus.equals("Open") && Setting.Searchreceive) BTbtn.setText("Connect");
				else if(BTstatus.equals("Connected")) BTbtn.setText("Close");
				
				if(Wifistatus.equals("Stopped")) Wifibtn.setText("Send");
				else Wifibtn.setText("Stop");
				
				if(Camstatus.equals("Stopped")) Cambtn.setText("Connect");
				else Cambtn.setText("Stop");
				if(!initial){
					initial=true;
					BTbtn.setEnabled(true);
					Cambtn.setEnabled(true);
					Wifibtn.setEnabled(true);
					if(BTstatus.equals("Close")){
						OneClick.setEnabled(true);
						OneClick.setText("OneClickConnect");
					}
				}
				
				
				
				brin.close();
				socket.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}