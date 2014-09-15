package code;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Level extends Thread{
	private JTextField textField, textField_1, FileName;
	private JTextArea textArea;
	BufferedReader in;
	static String sum="", _sum="";
	
	public Level(JTextField textField0, JTextField textField1
			, JTextArea textArea0, JTextField _FileName){
		textField=textField0;
		textField_1=textField1;
		textArea=textArea0;
		FileName=_FileName;
	}
	@Override
	public void run(){
		try {
			ServerSocket wifiSocket=new ServerSocket(Integer.parseInt(textField.getText()));
			FileReader fr = new FileReader("C://CamTest//Wifimac.txt");
			BufferedReader br = new BufferedReader(fr);
			String fstr=br.readLine();
			String[] mac = fstr.split("__");
			int[] level=new int[mac.length];
			int text = Integer.parseInt(textField_1.getText());
			int i, j, loop=0;
			while( text > loop ){
				Socket wclient = wifiSocket.accept();
				
				in = new BufferedReader(new 
						InputStreamReader(wclient.getInputStream()));
				String sstr = in.readLine();
				String[] wifi = sstr.split("BSSID: ");
				
				for( i=0; i<mac.length; i++ ) level[i]=-100;
				
				for( i=0; i<mac.length; i++ ){
					for( j=1; j<wifi.length; j++ ){
						if(mac[i].equals(wifi[j].substring(0, 17)))
							level[i]=Integer.parseInt( wifi[j].substring(wifi[j].indexOf("level: ")+7
									, wifi[j].indexOf("level: ")+10) );
					}
				}
				for( i=0; i<mac.length; i++ ) {
					sum+=level[i]+"  ";
					_sum+=level[i]+"  ";
				}
				
				sum+="["+(loop+1)+"]\n";
				_sum+="\r\n";
				textArea.setText(sum);
				loop++;
				wclient.close();
			}
			FileWriter fw = new FileWriter("C://CamTest//"+FileName.getText()+".txt", false);
			fw.write(_sum);
			fw.flush();
			fw.close();
			br.close();
			
			sum="";
			_sum="";
			
			in.close();
			wifiSocket.close();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
