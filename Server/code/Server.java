package code;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Server {

	private JFrame frmServer;
	private JTextField textField;
	private JTextField textField_1;
	static Boolean flag=true, _flag=true, posflag=true, monflag=true, positflag=true, conflag=false, Infoflag=false;
	int mode=0, speed=0;
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
	FloydWarshall fw=new FloydWarshall();
	 vposini vpi=null;
	private JTextField txtWifisignal;
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
		
		Info.secpo=2;
		frmServer = new JFrame();
		frmServer.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmServer.setBackground(Color.BLACK);
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
		tabbedPane.addTab("Setting", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("AmigoBot Setting______________________");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setFont(new Font("敺株�甇�擃�", Font.BOLD, 25));
		lblNewLabel_4.setBounds(10, 10, 529, 34);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Bluetooth :");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
		lblNewLabel_5.setBounds(24, 76, 118, 28);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("AmigoBot :");
		lblNewLabel_6.setForeground(Color.GRAY);
		lblNewLabel_6.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
		lblNewLabel_6.setBounds(24, 168, 118, 28);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Wifi :");
		lblNewLabel_7.setForeground(Color.GRAY);
		lblNewLabel_7.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
		lblNewLabel_7.setBounds(24, 222, 118, 28);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("MobileCam :");
		lblNewLabel_8.setForeground(Color.GRAY);
		lblNewLabel_8.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
		lblNewLabel_8.setBounds(24, 275, 133, 28);
		panel_1.add(lblNewLabel_8);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setBounds(355, 77, 184, 342);
		panel_1.add(textArea_1);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(24, 114, 118, 28);
		panel_1.add(comboBox_1);
		
		final JButton btnNewButton_19 = new JButton("Connect");
		btnNewButton_19.setEnabled(false);
		
		final JButton btnNewButton_23 = new JButton("Initializing...");
		
		final Setting set=new Setting();
		set.start();
		speed=set.Speed;
		
		final Info info = new Info();
		info.start();
		
		
		
		final JButton button_1 = new JButton("Initializing...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		final JButton btnNewButton_18 = new JButton("Initializing...");
		btnNewButton_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BTButton BT=new BTButton(btnNewButton_18, btnNewButton_19, btnNewButton_23, comboBox_1, button_1);
				BT.start();
				if(!btnNewButton_18.getText().equals("Close"))
					btnNewButton_18.setEnabled(false);
			}
		});
		btnNewButton_18.setBounds(174, 114, 110, 28);
		panel_1.add(btnNewButton_18);
		btnNewButton_18.setEnabled(false);
		
		btnNewButton_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnNewButton_19.setEnabled(false);
				Setting.Amigoconnect=true;
				button_1.setEnabled(true);
				button_1.setText("Start");;
			}
		});
		btnNewButton_19.setBounds(174, 168, 110, 28);
		panel_1.add(btnNewButton_19);
		
		final JButton btnNewButton_20 = new JButton("Initializing...");
		btnNewButton_20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnNewButton_20.getText().equals("Send")){
					Setting.Wificonnect=true;
					btnNewButton_20.setText("Stop");
				}else{
					Setting.Wifiunconnect=true;
					btnNewButton_20.setText("Send");
				}
			}
		});
		btnNewButton_20.setBounds(174, 222, 110, 28);
		panel_1.add(btnNewButton_20);
		btnNewButton_20.setEnabled(false);
		
		final JButton btnNewButton_21 = new JButton("Initializing...");
		btnNewButton_21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnNewButton_21.getText().equals("Connect")){
					Setting.MobileCamcon=true;
					btnNewButton_21.setText("Stop");
				}else{
					Setting.MobileCamuncon=true;
					btnNewButton_21.setText("Connect");
				}
			}
		});
		btnNewButton_21.setBounds(174, 275, 110, 28);
		panel_1.add(btnNewButton_21);
		btnNewButton_21.setEnabled(false);
		
		btnNewButton_23.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OneClickConnect oneClick=new OneClickConnect(btnNewButton_23, btnNewButton_18, button_1);
				oneClick.start();
				if(!btnNewButton_23.getText().equals("Close"))
					btnNewButton_23.setEnabled(false);
			}
		});
		btnNewButton_23.setBounds(24, 390, 260, 28);
		panel_1.add(btnNewButton_23);
		btnNewButton_23.setEnabled(false);
		
		info.Initial(textArea_1, btnNewButton_18, btnNewButton_19
				, btnNewButton_20, btnNewButton_21, btnNewButton_23);
		


		JLabel lblWandermode = new JLabel("WanderMode :");
		lblWandermode.setForeground(Color.GRAY);
		lblWandermode.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWandermode.setBounds(24, 328, 138, 28);
		panel_1.add(lblWandermode);

		
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(button_1.getText().equals("Start")){
//					Setting.WanderOpen=true;
				
					
					button_1.setText("Stop");
					
				}else{
//					Setting.WanderClose=true;
				
					button_1.setText("Start");
				}
			}
		});
		button_1.setEnabled(false);
		button_1.setBounds(174, 330, 110, 28);
		panel_1.add(button_1);
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Monitor", null, panel_2, null);
		panel_2.setLayout(null);
		
		final JTextArea textArea_4 = new JTextArea();
		textArea_4.setBackground(UIManager.getColor("Button.background"));
		textArea_4.setForeground(Color.BLACK);
		textArea_4.setFont(new Font("敺株�甇�擃�", Font.BOLD, 13));
		textArea_4.setText("0");
		textArea_4.setBounds(226, 402, 32, 25);
		panel_2.add(textArea_4);
		textArea_4.setEditable(false);
		

		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.BLUE);
		lblNewLabel_1.setBounds(36, 29, 517, 309);
		panel_2.add(lblNewLabel_1);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
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
				Setting.Forward=true;
				set.Speed=Integer.parseInt(textArea_4.getText());
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Setting.Forward=true;
				set.Speed=0;
			}
		});
		btnNewButton_15.setBounds(417, 369, 63, 23);
		panel_2.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("\u2193");
		btnNewButton_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Setting.Forward=true;
				set.Speed=-Integer.parseInt(textArea_4.getText());
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Setting.Forward=true;
				set.Speed=0;
			}
		});
		btnNewButton_16.setBounds(417, 403, 63, 23);
		panel_2.add(btnNewButton_16);
		
		JButton button = new JButton("\u2190");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Setting.Turn=true;
				set.Speed=Integer.parseInt(textArea_4.getText());
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Setting.Turn=true;
				set.Speed=0;
			}
		});
		button.setBounds(344, 403, 63, 23);
		panel_2.add(button);
		
		JButton btnNewButton_17 = new JButton("\u2192");
		btnNewButton_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Setting.Turn=true;
				set.Speed=-Integer.parseInt(textArea_4.getText());
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Setting.Turn=true;
				set.Speed=0;
			}
		});
		btnNewButton_17.setBounds(490, 403, 63, 23);
		panel_2.add(btnNewButton_17);
		
		JButton btnNewButton_14 = new JButton("\uFF0B");
		btnNewButton_14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				speed+=10;
				set.Speed=speed;
			
				if(speed>1000){
					speed=1000;
					set.Speed=speed;
				}
				textArea_4.setText(speed+"");
				Setting.SetAddSpeed=true;
			}
		});
		btnNewButton_14.setBounds(160, 403, 56, 23);
		panel_2.add(btnNewButton_14);
		
		JButton button_2 = new JButton("\uFF0D");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				speed-=10;
				set.Speed=speed;
				
				if(speed<0){
					speed=0;
					set.Speed=speed;
				}
				textArea_4.setText(speed+"");
				Setting.SetSubtractSpeed=true;
			}
		});
		button_2.setBounds(268, 403, 56, 23);
		panel_2.add(button_2);
		
		JLabel lblNewLabel_3 = new JLabel("Speed Setting :");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
		lblNewLabel_3.setBounds(171, 369, 154, 23);
		panel_2.add(lblNewLabel_3);
		
		Panel panel_3 = new Panel();
		tabbedPane.addTab("Position & Go", null, panel_3, null);
		panel_3.setLayout(null);
		
		final JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(388, 214, 145, 151);
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
//					vpi=new vposini();
//					vpi.start();
					
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
		btnNewButton_12.setBounds(388, 150, 112, 23);
		panel_3.add(btnNewButton_12);
		
		
	
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if( mode==0 ){

					int[] path;
					if(Info.secpo!=-1){
						path=fw.FindPathArray(Info.secpo, 2);
					}else{
//						path=fw.FindPathArray(Position.start, 2);
						path=fw.FindPathArray(Position.start, 2);
					}

					
					for(int i=0;i<path.length;i++){
						System.out.print(path[i]+",");
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{
					
					int[] path=new int[]{2, 3, 8, 7, 6, 5, 0, 1, 2};
					vtest=new pathalgo(path);
					vtest.start();

				}
			}
		});
		btnNewButton_3.setBounds(63, 86, 87, 87);
		panel_3.add(btnNewButton_3);
		
//		final JButton btnNewButton_4 = new JButton("1");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					int[] path;
					if(Info.secpo!=-1){
						
						path=fw.FindPathArray(Info.secpo, 1);
					}else{path=fw.FindPathArray(Position.start, 1);}
					
					for(int i=0;i<path.length;i++){
						System.out.println(path[i]);
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{
					int[] path=new int[]{1, 0, 5, 6, 7, 8, 3, 2, 4, 1};

					vtest=new pathalgo(path);
					vtest.start();


				}
			}
		});
		btnNewButton_4.setBounds(160, 85, 87, 87);
		panel_3.add(btnNewButton_4);
		
//		final JButton btnNewButton_5 = new JButton("0");
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					int[] path;
					if(Info.secpo!=-1){
						
						path=fw.FindPathArray(Info.secpo, 0);
					}else{path=fw.FindPathArray(Position.start, 0);}
					for(int i=0;i<path.length;i++){
						System.out.print(path[i]+",");
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{
					int[] path=new int[]{0, 5, 6, 7, 8, 3, 2, 1, 0};

					vtest=new pathalgo(path);
					vtest.start();


				}
			}
		});
		btnNewButton_5.setBounds(257, 85, 87, 87);
		panel_3.add(btnNewButton_5);
		
//		final JButton btnNewButton_6 = new JButton("3");
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					int[] path;
					if(Info.secpo!=-1){
						
						path=fw.FindPathArray(Info.secpo, 3);
					}else{path=fw.FindPathArray(Position.start, 3);}
					for(int i=0;i<path.length;i++){
						System.out.print(path[i]+",");
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{

				
					

					int[] path=new int[]{3, 8, 7, 6, 5, 0, 1, 2, 3};
					vtest=new pathalgo(path);
					vtest.start();


				}
			}
		});
		btnNewButton_6.setBounds(63, 183, 87, 87);
		panel_3.add(btnNewButton_6);
		
//		final JButton btnNewButton_7 = new JButton("4");
		btnNewButton_7.setBackground(Color.WHITE);
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					int[] path;
					if(Info.secpo!=-1){
						
						path=fw.FindPathArray(Info.secpo, 4);
					}else{path=fw.FindPathArray(Position.start, 4);}
					for(int i=0;i<path.length;i++){
						System.out.print(path[i]+",");
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{

					int[] path=new int[]{4, 5, 6, 7, 8, 3, 2, 1,0};
					

					
					vtest=new pathalgo(path);
					vtest.start();


				}
			}
		});
		btnNewButton_7.setBounds(160, 183, 87, 87);
		panel_3.add(btnNewButton_7);
		
		
//		final JButton btnNewButton_8 = new JButton("5");
		btnNewButton_8.setBackground(Color.WHITE);
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					int[] path;
					if(Info.secpo!=-1){
						
						path=fw.FindPathArray(Info.secpo, 5);
					}else{path=fw.FindPathArray(Position.start, 5);}
					for(int i=0;i<path.length;i++){
						System.out.print(path[i]+",");
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{

					int[] path=new int[]{5, 0, 1, 2, 3, 8, 7, 6, 5};
					
					vtest=new pathalgo(path);
					vtest.start();


				}
			}
		});
		btnNewButton_8.setBounds(257, 183, 87, 87);
		panel_3.add(btnNewButton_8);
		
//		final JButton btnNewButton_9 = new JButton("8");
		btnNewButton_9.setBackground(Color.WHITE);
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					int[] path;
					if(Info.secpo!=-1){
						
						path=fw.FindPathArray(Info.secpo, 8);
					}else{path=fw.FindPathArray(Position.start, 8);}
					for(int i=0;i<path.length;i++){
						System.out.print(path[i]+",");
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{

					int[] path=new int[]{8, 7, 6, 5, 0, 1, 2, 3, 8};
					
					vtest=new pathalgo(path);
					vtest.start();


				}
			}
		});
		btnNewButton_9.setBounds(63, 280, 87, 87);
		panel_3.add(btnNewButton_9);
		
//		final JButton btnNewButton_10 = new JButton("7");
		btnNewButton_10.setBackground(Color.WHITE);
		btnNewButton_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					int[] path;
					if(Info.secpo!=-1){
						
						path=fw.FindPathArray(Info.secpo, 7);
					}else{path=fw.FindPathArray(Position.start, 7);}
					for(int i=0;i<path.length;i++){
						System.out.print(path[i]+",");
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{

					int[] path=new int[]{7, 6, 5, 0, 1, 2, 3, 8, 7};
					

					vtest=new pathalgo(path);
					vtest.start();


				}
			}
		});
		btnNewButton_10.setBounds(160, 280, 87, 87);
		panel_3.add(btnNewButton_10);
		
//		final JButton btnNewButton_11 = new JButton("6");
		btnNewButton_11.setBackground(Color.WHITE);
		btnNewButton_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					int[] path;

					if(Info.secpo!=-1){

						
						path=fw.FindPathArray(Info.secpo, 6);
					}else{path=fw.FindPathArray(Position.start, 6);}
					for(int i=0;i<path.length;i++){
						System.out.print(path[i]+",");
					}
					vtest=new pathalgo(path);
					vtest.start();
				}else{

					int[] path=new int[]{6, 5, 0, 1,  2, 3, 8, 7, 6};
				

					vtest=new pathalgo(path);
					vtest.start();

				}
			}
		});
		btnNewButton_11.setBounds(257, 280, 87, 87);
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
		comboBox.setBounds(388, 119, 112, 21);
		panel_3.add(comboBox);
		
		JLabel lblMode = new JLabel("Mode :");
		lblMode.setForeground(Color.GRAY);
		lblMode.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
		lblMode.setBounds(388, 86, 76, 23);
		panel_3.add(lblMode);
		
		JLabel lblWfiInfo = new JLabel("Wifi Info:");
		lblWfiInfo.setForeground(Color.GRAY);
		lblWfiInfo.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
		lblWfiInfo.setBounds(388, 183, 100, 21);
		panel_3.add(lblWfiInfo);
		
		
		

		JPanel panel = new JPanel();
		tabbedPane.addTab("Build", null, panel, null);
		panel.setLayout(null);
		
		JButton btnSearch = new JButton("Build");
		btnSearch.setBounds(22, 174, 119, 23);
		panel.add(btnSearch);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(183, 174, 119, 23);
		panel.add(btnNewButton);
		
		JLabel lblData = new JLabel("DATA:");
		lblData.setForeground(Color.GRAY);
		lblData.setBounds(22, 81, 58, 21);
		panel.add(lblData);
		lblData.setFont(new Font("敺株�甇�擃�", Font.BOLD, 16));
		
		textField_1 = new JTextField("62");
		textField_1.setFont(new Font("敺株�甇�擃�", Font.BOLD, 12));
		textField_1.setBounds(112, 81, 96, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("PORT:");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(22, 48, 53, 23);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("敺株�甇�擃�", Font.BOLD, 16));
		
		textField = new JTextField("861");
		textField.setFont(new Font("敺株�甇�擃�", Font.BOLD, 12));
		textField.setBounds(112, 48, 96, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSetting = new JLabel("Wifi Build Setting___________");
		lblSetting.setBounds(10, 10, 292, 28);
		panel.add(lblSetting);
		lblSetting.setForeground(Color.GRAY);
		lblSetting.setFont(new Font("敺株�甇�擃�", Font.BOLD, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 8, 246, 435);
		panel.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel_9 = new JLabel("FileName:");
		lblNewLabel_9.setFont(new Font("敺株�甇�擃�", Font.BOLD, 16));
		lblNewLabel_9.setForeground(Color.GRAY);
		lblNewLabel_9.setBounds(22, 112, 103, 21);
		panel.add(lblNewLabel_9);
		
		txtWifisignal = new JTextField();
		txtWifisignal.setText("WifiSignal");
		txtWifisignal.setFont(new Font("敺株�甇�擃�", Font.BOLD, 12));
		txtWifisignal.setBounds(112, 114, 96, 21);
		panel.add(txtWifisignal);
		txtWifisignal.setColumns(10);
		
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
				Level lvl=new Level(textField, textField_1, textArea, txtWifisignal);
				lvl.start();
			}
		});
	}
}
