package code;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;




public class Server {

	private JFrame frmServer;
	private JTextField textField;
	private JTextField textField_1;
	static Boolean flag=true, _flag=true, posflag=true, monflag=true, positflag=true, conflag=false, Infoflag=false;
	int mode=0, speed=0, Amigostatus=0, Wifistatus=0, Camstatus=0;
	String BTname="";
	JButton btnNewButton_3 = new JButton("2");
	JButton btnNewButton_4 = new JButton("1");
	JButton btnNewButton_5 = new JButton("0");
	JButton btnNewButton_6 = new JButton("3");
	JButton btnNewButton_7 = new JButton("4");
	JButton btnNewButton_8 = new JButton("5");
	JButton btnNewButton_9 = new JButton("8");
	JButton btnNewButton_10 = new JButton("7");
	JButton btnNewButton_11 = new JButton("6");
	pathalgo vtest=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Server window = new Server();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\owuser\\Downloads\\icon.png"));
		frmServer.setTitle("Amigo");
		frmServer.setResizable(false);
		frmServer.setBounds(100, 100, 615, 520);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(0, 0, 594, 482);
		frmServer.getContentPane().add(tabbedPane);
		
		final WifiSignal wifisgl=new WifiSignal();
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Control", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("AmigoBot Setting___________________________");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setFont(new Font("敺株�甇��擃�", Font.BOLD, 25));
		lblNewLabel_4.setBounds(10, 10, 529, 34);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Bluetooth :");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblNewLabel_5.setBounds(24, 76, 118, 28);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("AmigoBot :");
		lblNewLabel_6.setForeground(Color.GRAY);
		lblNewLabel_6.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblNewLabel_6.setBounds(24, 168, 118, 28);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Wifi :");
		lblNewLabel_7.setForeground(Color.GRAY);
		lblNewLabel_7.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblNewLabel_7.setBounds(24, 222, 118, 28);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("MobileCam :");
		lblNewLabel_8.setForeground(Color.GRAY);
		lblNewLabel_8.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblNewLabel_8.setBounds(24, 275, 133, 28);
		panel_1.add(lblNewLabel_8);
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Monitor", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("嚙盤嚙緯嚙踝蕭嚙踝蕭嚙踝蕭", Font.BOLD, 13));
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setBounds(337, 76, 184, 289);
		panel_1.add(textArea_1);
		
		final JTextArea textArea_4 = new JTextArea();
		textArea_4.setText("0");
		textArea_4.setBounds(226, 402, 32, 25);
		panel_2.add(textArea_4);
		
		final Setting set=new Setting();
		set.start();
		
		final Info info = new Info();
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(24, 114, 118, 28);
		panel_1.add(comboBox_1);
		
		final JButton btnNewButton_19 = new JButton("Connect");
		btnNewButton_19.setEnabled(false);
		
		final JButton btnNewButton_18 = new JButton("Initializing...");
		btnNewButton_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnNewButton_18.getText().equals("Open")){
					set.BTSwitchopen=true;
					while(!Info.BTstatus.equals("Open")){
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					btnNewButton_18.setText("Search");
				}else if(btnNewButton_18.getText().equals("Search")){
					set.BTSearchflag=true;
					while(!set.Searchreceive){
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					String[] option=set.BTSearch.split("_");
					comboBox_1.setModel(new DefaultComboBoxModel(option));
					btnNewButton_18.setText("Connect");
				}else if(btnNewButton_18.getText().equals("Connect")){
					set.BTConnectflag=true;
					set.BTMatch=comboBox_1.getSelectedIndex();
					btnNewButton_18.setText("Close");
					while(!info.BTstatus.equals("Connected")){
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					btnNewButton_19.setEnabled(true);
					Amigostatus=0;
				}else if(btnNewButton_18.getText().equals("Close")){
					set.BTSwitchclose=true;
					comboBox_1.setModel(new DefaultComboBoxModel());
					btnNewButton_18.setText("Open");
				}
			}
		});
		btnNewButton_18.setBounds(164, 114, 110, 28);
		panel_1.add(btnNewButton_18);
		btnNewButton_18.setEnabled(false);
		
		btnNewButton_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnNewButton_19.setEnabled(false);
				set.Amigoconnect=true;
			}
		});
		btnNewButton_19.setBounds(164, 168, 110, 28);
		panel_1.add(btnNewButton_19);
		
		final JButton btnNewButton_20 = new JButton("Initializing...");
		btnNewButton_20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnNewButton_20.getText().equals("Send")){
					set.Wificonnect=true;
					btnNewButton_20.setText("Stop");
				}else{
					set.Wifiunconnect=true;
					btnNewButton_20.setText("Send");
				}
			}
		});
		btnNewButton_20.setBounds(164, 222, 110, 28);
		panel_1.add(btnNewButton_20);
		btnNewButton_20.setEnabled(false);
		
		final JButton btnNewButton_21 = new JButton("Initializing...");
		btnNewButton_21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnNewButton_21.getText().equals("Connect")){
					set.MobileCamcon=true;
					btnNewButton_21.setText("Stop");
				}else{
					set.MobileCamuncon=true;
					btnNewButton_21.setText("Connect");
				}
			}
		});
		btnNewButton_21.setBounds(164, 275, 110, 28);
		panel_1.add(btnNewButton_21);
		btnNewButton_21.setEnabled(false);
		
		final JButton btnNewButton_23 = new JButton("Initializing...");
		btnNewButton_23.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnNewButton_23.getText().equals("OneClickConnect")){
					set.BTSwitchopen=true;
					while(!Info.BTstatus.equals("Open")){
						try {
							Thread.sleep(50);
						} catch (InterruptedException ex0) {
							// TODO Auto-generated catch block
							ex0.printStackTrace();
						}
					}
					set.BTSearchflag=true;
					while(!set.Searchreceive){
						try {
							Thread.sleep(50);
						} catch (InterruptedException ex1) {
							// TODO Auto-generated catch block
							ex1.printStackTrace();
						}
					}
					int index=0;
					String[] option=set.BTSearch.split("_");
					for(int i=0; i<option.length; i++){
						if(option[i].indexOf("Hotlife")>0)
							index=i;
					}
					set.BTConnectflag=true;
					set.BTMatch=index;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					while(!info.BTstatus.equals("Connected")){
						try {
							Thread.sleep(50);
						} catch (InterruptedException ex2) {
							// TODO Auto-generated catch block
							ex2.printStackTrace();
						}
					}
					set.Amigoconnect=true;
					btnNewButton_23.setText("Close");
					
				}else{
					set.BTSwitchclose=true;
					btnNewButton_23.setText("OneClickConnect");
				}
				
			}
		});
		btnNewButton_23.setBounds(24, 337, 250, 28);
		panel_1.add(btnNewButton_23);
		btnNewButton_23.setEnabled(false);
		
		info.Initial(textArea_1, btnNewButton_18, btnNewButton_19
				, btnNewButton_20, btnNewButton_21, btnNewButton_23);
		
		final JButton btnNewButton_24 = new JButton("start");
		
		
		btnNewButton_24.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnNewButton_24.getText().equals("start")==true){
				btnNewButton_24.setText("stop");
				vtest=new pathalgo();
				vtest.start();
				}
				else{
					btnNewButton_24.setText("start");
					vtest.setstop(true);
				}
				
			}
		});
		btnNewButton_24.setBounds(55, 390, 87, 23);
		panel_1.add(btnNewButton_24);
		info.start();
		
		Panel panel_3 = new Panel();
		tabbedPane.addTab("Position & Go", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(324, 214, 100, 151);
		panel_3.add(textArea_3);
		
		final Propel propel=new Propel();
		final MonitorZones mz=new MonitorZones();
		
		final JButton btnNewButton_13 = new JButton("Connect");
		btnNewButton_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if(!conflag){
//					if(mode==0) propel.start();
//					else if(mode==1) mz.start();
//					btnNewButton_13.setText("Connecting...");
//					conflag=true;
//					if(!Infoflag){
//						Infoflag=true;
//					}
//				}else{
//					if(mode==0) Propel.core=false;
//					else if(mode==1) MonitorZones.core=false;
//					btnNewButton_13.setText("Connect");
//					conflag=false;
//				}
			}
		});
		btnNewButton_13.setBounds(324, 150, 112, 23);
		panel_3.add(btnNewButton_13);
		
		final JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(434, 214, 145, 151);
		panel_3.add(textArea_2);
		
		final Position position=new Position( btnNewButton_5, btnNewButton_4, btnNewButton_3, 
				btnNewButton_6, btnNewButton_7, btnNewButton_8, btnNewButton_11, 
				btnNewButton_10, btnNewButton_9, textArea_2 );
		
		final JButton btnNewButton_12 = new JButton("Position");
		btnNewButton_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(_flag) {
					position.start();
					wifisgl.start();
					btnNewButton_12.setText("Stop");
					_flag=false;
				}else {
					if(positflag){
						Position.flag=false;
						WifiSignal.flag=false;
						btnNewButton_12.setText("Position");
						positflag=false;
					}else{
						Position.flag=true;
						WifiSignal.flag=true;
						btnNewButton_12.setText("Stop");
						positflag=true;
					}
				}
			}
		});
		btnNewButton_12.setBounds(324, 117, 112, 23);
		panel_3.add(btnNewButton_12);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=2;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{2, 3, 8, 7, 6, 5, 0, 1, 4, 2};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_3.setBounds(22, 86, 87, 87);
		panel_3.add(btnNewButton_3);
		
//		final JButton btnNewButton_4 = new JButton("1");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=1;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{1, 0, 5, 6, 7, 8, 3, 2, 4, 1};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_4.setBounds(119, 85, 87, 87);
		panel_3.add(btnNewButton_4);
		
//		final JButton btnNewButton_5 = new JButton("0");
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=0;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{0, 5, 6, 7, 8, 3, 2, 4, 1, 0};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_5.setBounds(216, 85, 87, 87);
		panel_3.add(btnNewButton_5);
		
//		final JButton btnNewButton_6 = new JButton("3");
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=3;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{3, 8, 7, 6, 5, 0, 1, 2, 4, 3};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_6.setBounds(22, 183, 87, 87);
		panel_3.add(btnNewButton_6);
		
//		final JButton btnNewButton_7 = new JButton("4");
		btnNewButton_7.setBackground(Color.WHITE);
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=4;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{4, 7, 6, 5, 0, 1, 2, 3, 8, 4};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_7.setBounds(119, 183, 87, 87);
		panel_3.add(btnNewButton_7);
		btnNewButton_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
//		final JButton btnNewButton_8 = new JButton("5");
		btnNewButton_8.setBackground(Color.WHITE);
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=5;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{5, 0, 1, 4, 2, 3, 8, 7, 6, 5};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_8.setBounds(216, 183, 87, 87);
		panel_3.add(btnNewButton_8);
		
//		final JButton btnNewButton_9 = new JButton("8");
		btnNewButton_9.setBackground(Color.WHITE);
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=8;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{8, 7, 6, 5, 0, 1, 4, 2, 3, 8};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_9.setBounds(22, 280, 87, 87);
		panel_3.add(btnNewButton_9);
		
//		final JButton btnNewButton_10 = new JButton("7");
		btnNewButton_10.setBackground(Color.WHITE);
		btnNewButton_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=7;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{7, 6, 5, 0, 1, 4, 2, 3, 8, 7};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_10.setBounds(119, 278, 87, 87);
		panel_3.add(btnNewButton_10);
		
//		final JButton btnNewButton_11 = new JButton("6");
		btnNewButton_11.setBackground(Color.WHITE);
		btnNewButton_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if( mode==0 ){
//					Propel.start=Position.start;
//					Propel.end=6;
//					Propel.flag=true;
//				}else{
//					int[] path=new int[]{6, 5, 0, 1, 4, 2, 3, 8, 7, 6};
//					mz.array=path;
//					MonitorZones.flag=true;
//				}
			}
		});
		btnNewButton_11.setBounds(216, 278, 87, 87);
		panel_3.add(btnNewButton_11);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Best path", "Monitor zones"}));
		comboBox.setBackground(Color.WHITE);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( comboBox.getSelectedIndex()==0 ) mode=0;
				else if( comboBox.getSelectedIndex()==1 )mode=1;
			}
		});
		comboBox.setBounds(324, 86, 112, 21);
		panel_3.add(comboBox);
		
		JLabel lblMode = new JLabel("Mode :");
		lblMode.setForeground(Color.GRAY);
		lblMode.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblMode.setBounds(324, 53, 76, 23);
		panel_3.add(lblMode);
		
		JLabel lblCarInfo = new JLabel("Car Info:");
		lblCarInfo.setForeground(Color.GRAY);
		lblCarInfo.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblCarInfo.setBounds(324, 183, 100, 21);
		panel_3.add(lblCarInfo);
		
		JLabel lblWfiInfo = new JLabel("Wifi Info:");
		lblWfiInfo.setForeground(Color.GRAY);
		lblWfiInfo.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblWfiInfo.setBounds(434, 183, 100, 21);
		panel_3.add(lblWfiInfo);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(36, 50, 517, 309);
		panel_2.add(lblNewLabel_1);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblNewLabel_2.setBounds(177, 10, 274, 23);
		panel_2.add(lblNewLabel_2);
		
		final Monitor monitor=new Monitor(lblNewLabel_1, lblNewLabel_2);
		
		final JButton btnMonitor = new JButton("Monitor");
		btnMonitor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(flag) {
					monitor.start();
					btnMonitor.setText("Stop");
					flag=false;
				}else {
					if(monflag){
						Monitor.flag=false;
						btnMonitor.setText("Monitor");
						monflag=false;
					}else{
						Monitor.flag=true;
						btnMonitor.setText("Stop");
						monflag=true;
					}
				}
			}
		});
		btnMonitor.setBounds(36, 369, 87, 23);
		panel_2.add(btnMonitor);
		
		JButton btnNewButton_2 = new JButton("OpenFile");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Runtime.getRuntime().exec("explorer C:\\CamTest\\monitor");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(36, 403, 87, 23);
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_15 = new JButton("\u2191");
		btnNewButton_15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				set.Forward=true;
				set.Speed=Integer.parseInt(textArea_4.getText());
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				set.Forward=true;
				set.Speed=0;
			}
		});
		btnNewButton_15.setBounds(417, 369, 63, 23);
		panel_2.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("\u2193");
		btnNewButton_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				set.Forward=true;
				set.Speed=-Integer.parseInt(textArea_4.getText());
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				set.Forward=true;
				set.Speed=0;
			}
		});
		btnNewButton_16.setBounds(417, 403, 63, 23);
		panel_2.add(btnNewButton_16);
		
		JButton button = new JButton("\u2190");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				set.Turn=true;
				set.Speed=Integer.parseInt(textArea_4.getText());
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				set.Turn=true;
				set.Speed=0;
			}
		});
		button.setBounds(344, 403, 63, 23);
		panel_2.add(button);
		
		JButton btnNewButton_17 = new JButton("\u2192");
		btnNewButton_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				set.Turn=true;
				set.Speed=-Integer.parseInt(textArea_4.getText());
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				set.Turn=true;
				set.Speed=0;
			}
		});
		btnNewButton_17.setBounds(490, 403, 63, 23);
		panel_2.add(btnNewButton_17);
		
		JButton btnNewButton_14 = new JButton("\uFF0B");
		btnNewButton_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				set.Speed=set.Speed-10;
				if(set.Speed>1000) set.Speed=1000;
				textArea_4.setText(set.Speed+"");
				set.SetAddSpeed=true;
			}
		});
		btnNewButton_14.setBounds(160, 403, 56, 23);
		panel_2.add(btnNewButton_14);
		
		JButton button_2 = new JButton("\uFF0D");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				set.Speed=set.Speed-10;
				if(set.Speed<0) set.Speed=0;
				textArea_4.setText(set.Speed+"");
				set.SetSubtractSpeed=true;
			}
		});
		button_2.setBounds(268, 403, 56, 23);
		panel_2.add(button_2);
		
		JLabel lblNewLabel_3 = new JLabel("Speed Setting :");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		lblNewLabel_3.setBounds(171, 369, 154, 23);
		panel_2.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Build", null, panel, null);
		panel.setLayout(null);
		
		JButton btnSearch = new JButton("Level");
		btnSearch.setBounds(22, 174, 80, 23);
		panel.add(btnSearch);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(112, 174, 80, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Erase");
		btnNewButton_1.setBounds(202, 174, 80, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblData = new JLabel("DATA:");
		lblData.setBounds(22, 81, 58, 21);
		panel.add(lblData);
		lblData.setFont(new Font("敺株�甇��擃�", Font.BOLD, 16));
		
		textField_1 = new JTextField("250");
		textField_1.setBounds(78, 84, 96, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("PORT:");
		lblNewLabel.setBounds(22, 48, 53, 23);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("敺株�甇��擃�", Font.BOLD, 16));
		
		textField = new JTextField("861");
		textField.setBounds(78, 52, 96, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSetting = new JLabel("Build Setting__________________");
		lblSetting.setBounds(10, 10, 292, 28);
		panel.add(lblSetting);
		lblSetting.setForeground(Color.GRAY);
		lblSetting.setFont(new Font("敺株�甇��擃�", Font.BOLD, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 8, 246, 435);
		panel.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Search srch=new Search(textField, textField_1, textArea);
				srch.start();
			}
		});
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Level lvl=new Level(textField, textField_1, textArea);
				lvl.start();
			}
		});
	}
}