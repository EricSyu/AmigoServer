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
	public JButton BTbtn, Wifibtn, Cambtn, Amigobtn;
	public static String BTstatus="", Amigostatus="", Wifistatus="", Camstatus="";
	public static int x=0, y=0, theta=0, Motor=0, stall=0;
	public static int[] sensor=new int[]{-100, -100, -100, -100, -100, -100, -100, -100};
	
	public void Initial( JTextArea _textArea, JButton _BTbtn, JButton _Amigobtn
			, JButton _Wifibtn, JButton _Cambtn ){
		textArea=_textArea;
		BTbtn=_BTbtn;
		Wifibtn=_Wifibtn;
		Cambtn=_Cambtn;
		Amigobtn=_Amigobtn;
	}
	@Override
	public void run(){
		try {
			int i, cnt=0;
			Boolean initial=false;
			ServerSocket server=new ServerSocket(200);
			
			while(true){
				String info="";
				System.out.println("connec ......");
				Socket socket=server.accept();
				System.out.println("connec sucess");
				BufferedReader brin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				System.out.println("debug1");
				BTstatus=brin.readLine();
				Amigostatus=brin.readLine();
				Wifistatus=brin.readLine();
				Camstatus=brin.readLine();
				Motor=Integer.parseInt(brin.readLine());
				stall=Integer.parseInt(brin.readLine());
				x=Integer.parseInt(brin.readLine());
				y=Integer.parseInt(brin.readLine());
				theta=Integer.parseInt(brin.readLine());
				for( i=0; i<8; i++ ) sensor[i]=Integer.parseInt(brin.readLine());
				System.out.println("debug2");
				info+="Blustooth: "+BTstatus+"\nAmigostatus: "+Amigostatus+"\nWifistatus: "
						+Wifistatus+"\nCamstatus: "+Camstatus+"\nMotor: "+Motor+"\nStall: "
						+stall+"\n("+x+", "+y+")\n"+"Angle: "+theta+"\n";
				for( i=0; i<8; i++ ) info+="Sensor "+i+": "+sensor[i]+"\n";
				
				if(BTstatus.equals("Close")) BTbtn.setText("Open");
				else if(BTstatus.equals("Open")) BTbtn.setText("Search");
				else if(BTstatus.equals("Connected")){
					BTbtn.setText("Close");
					Amigobtn.setEnabled(true);
				}
				
				if(Wifistatus.equals("Stopped")) Wifibtn.setText("Send");
				else Wifibtn.setText("Stop");
				
				if(Camstatus.equals("Stopped")) Cambtn.setText("Connect");
				else Cambtn.setText("Stop");
				if(!initial){
					initial=true;
					BTbtn.setEnabled(true);
					Cambtn.setEnabled(true);
					Wifibtn.setEnabled(true);
				}
				
				textArea.setText(info);
				Thread.sleep(100);
				
				brin.close();
				socket.close();
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