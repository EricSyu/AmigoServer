package code;

import javax.swing.JButton;

public class OneClickConnect extends Thread{
	public JButton OneClick, BTButton, WanderMode;
	public OneClickConnect( JButton _OneClick, JButton _BTButton, JButton _WanderMode ){
		OneClick=_OneClick;
		BTButton=_BTButton;
		WanderMode=_WanderMode;
	}
	@Override
	public void run(){
		if(OneClick.getText().equals("OneClickConnect")){
			Setting.BTSwitchopen=true;
			BTButton.setEnabled(false);
			while(!Info.BTstatus.equals("Open")){
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex0) {
					// TODO Auto-generated catch block
					ex0.printStackTrace();
				}
			}
			Setting.BTSearchflag=true;
			while(!Setting.Searchreceive){
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex1) {
					// TODO Auto-generated catch block
					ex1.printStackTrace();
				}
			}
			int index=0;
			String[] option=Setting.BTSearch.split("_");
			for(int i=0; i<option.length; i++){
				if(option[i].indexOf("Hotlife")>0)
					index=i;
			}
			Setting.BTConnectflag=true;
			Setting.BTMatch=index;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(!Info.BTstatus.equals("Connected")){
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex2) {
					// TODO Auto-generated catch block
					ex2.printStackTrace();
				}
			}
			Setting.Amigoconnect=true;
			OneClick.setText("Close");
			OneClick.setEnabled(true);
			WanderMode.setEnabled(true);
			WanderMode.setText("Start");
		}else{
			Setting.BTSwitchclose=true;
			OneClick.setText("OneClickConnect");
			BTButton.setEnabled(true);
			WanderMode.setText("Start");
			WanderMode.setEnabled(false);
		}
	}
}
