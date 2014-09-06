import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Search extends Thread{
	private JTextField textField, textField_1;
	private JTextArea textArea;
	private static String[] record=new String[15];
	BufferedReader in;
	static String sum="";
	int[] cnt=new int[15];
	public Search(JTextField textField0, JTextField textField1, JTextArea textArea0){
		for (int i=0; i<15; i++) cnt[i]=0;
		textField=textField0;
		textField_1=textField1;
		textArea=textArea0;
	}
	@Override
	public void run(){
		try{
			ServerSocket wifiSocket=new ServerSocket(Integer.parseInt(textField.getText()));
			int text = Integer.parseInt(textField_1.getText());
			int i, j, flag=0, loop=0;
			
			while( text > loop ){
				Socket wclient = wifiSocket.accept();
				
				in = new BufferedReader(new 
						InputStreamReader(wclient.getInputStream()));
				String str = in.readLine();
				String[] wifi = str.split("BSSID: ");
				if( loop==0 ){
					for( i=1; i<wifi.length; i++ ){
						record[i-1]=wifi[i].substring(0, 17);
						cnt[i-1]++;
					}
				}else{
					for( i=1; i<wifi.length; i++ ){
						for( j=0; j<15; j++ ){
							if( record[j].equals(wifi[i].substring(0, 17)) ){
								cnt[j]++;
								break;
							}
						}
						if(j==15){
							for( j=0; j<15; j++ ){
								if( cnt[j]==0 ){
									record[j]=wifi[i].substring(0, 17);
									cnt[j]++;
									flag=0;
									break;
								}
							}
						}
					}
				}
				sum+="AP "+str.substring(str.length()-2)+" ["+(loop+1)+"]\n";
				textArea.setText(sum);
				loop++;
				wclient.close();
			}
			sum="";
			String wrt="", _wrt="";
			for( i=0; i<15; i++ ){
				if( cnt[i]==0 ) break;
				else {
					wrt+=record[i]+"\n"+cnt[i]+"\r\n";
					_wrt+=record[i]+"__";
				}
			}
			FileWriter fw = new FileWriter("C://CamTest//Wifi.txt");
			fw.write(wrt);
			fw.flush();
			fw.close();
			
			FileWriter _fw = new FileWriter("C://CamTest//Wifimac.txt");
			_fw.write(_wrt);
			_fw.flush();
			_fw.close();
			
			in.close();
			
			wifiSocket.close();
		} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
