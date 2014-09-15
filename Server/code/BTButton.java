package code;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class BTButton extends Thread{
	public JButton Blutooth, Amigo, OneClick;
	public JComboBox comboBox;
	
	public BTButton( JButton _Blutooth, JButton _Amigo, JButton _OneClick, JComboBox _comboBox ){
		Blutooth=_Blutooth;
		Amigo=_Amigo;
		comboBox=_comboBox;
		OneClick=_OneClick;
	}
	public void run(){
		if(Blutooth.getText().equals("Open")){
			Setting.BTSwitchopen=true;
			OneClick.setEnabled(false);
			while(!Info.BTstatus.equals("Open")){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Blutooth.setText("Search");
			Blutooth.setEnabled(true);
		}else if(Blutooth.getText().equals("Search")){
			Setting.BTSearchflag=true;
			while(!Setting.Searchreceive){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String[] option=Setting.BTSearch.split("_");
			comboBox.setModel(new DefaultComboBoxModel(option));
			Blutooth.setText("Connect");
			Blutooth.setEnabled(true);
		}else if(Blutooth.getText().equals("Connect")){
			Setting.BTConnectflag=true;
			Setting.BTMatch=comboBox.getSelectedIndex();
			Blutooth.setText("Close");
			while(!Info.BTstatus.equals("Connected")){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Amigo.setEnabled(true);
			Blutooth.setEnabled(true);
		}else if(Blutooth.getText().equals("Close")){
			Setting.BTSwitchclose=true;
			comboBox.setModel(new DefaultComboBoxModel());
			Blutooth.setText("Open");
			Blutooth.setEnabled(true);
			OneClick.setEnabled(true);
		}
	}
}
