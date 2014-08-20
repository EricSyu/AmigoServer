import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import java.awt.SystemColor;


public class Server {

	private JFrame frmServer;
	private JButton btnPositioning, button_1;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label;
	private JTextArea textArea_1;
	private static Socket wclient;
	static Boolean flag=true, _flag=true, posflag=true, monflag=true, positflag=true, conflag=false, Infoflag=false;
	int mode=0;
	JButton btnNewButton_3 = new JButton("2");
	JButton btnNewButton_4 = new JButton("1");
	JButton btnNewButton_5 = new JButton("0");
	JButton btnNewButton_6 = new JButton("3");
	JButton btnNewButton_7 = new JButton("4");
	JButton btnNewButton_8 = new JButton("5");
	JButton btnNewButton_9 = new JButton("8");
	JButton btnNewButton_10 = new JButton("7");
	JButton btnNewButton_11 = new JButton("6");
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
		lblData.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		
		textField_1 = new JTextField("250");
		textField_1.setBounds(78, 84, 96, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("PORT:");
		lblNewLabel.setBounds(22, 48, 53, 23);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		
		textField = new JTextField("861");
		textField.setBounds(78, 52, 96, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSetting = new JLabel("Setting_______________________");
		lblSetting.setBounds(10, 10, 292, 28);
		panel.add(lblSetting);
		lblSetting.setForeground(Color.GRAY);
		lblSetting.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 8, 246, 435);
		panel.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Position", null, panel_1, null);
		panel_1.setLayout(null);
		
		label = new JLabel(new ImageIcon("C:\\Users\\owuser\\Desktop\\404\\9Zones.png"));
		label.setBounds(323, 162, 222, 231);
		panel_1.add(label);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(65, 162, 189, 231);
		panel_1.add(textArea_1);
		
		btnPositioning = new JButton("Position");
		button_1 = new JButton("Stop");
		
		final Locating lct=new Locating(label, textArea_1);
		final WifiSignal wifisgl=new WifiSignal();
		
		btnPositioning.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( posflag ){
					lct.start();
					wifisgl.start();
					posflag=false;
				}else{
					Locating.flag=true;
					WifiSignal.flag=true;
				}
				
				btnPositioning.setEnabled(false);
				button_1.setEnabled(true);
			}
		});
		btnPositioning.setBounds(151, 63, 103, 23);
		panel_1.add(btnPositioning);
		
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Locating.flag=false;
				WifiSignal.flag=false;
				btnPositioning.setEnabled(true);
				button_1.setEnabled(false);
			}
		});
		button_1.setEnabled(false);
		button_1.setBounds(323, 63, 103, 23);
		panel_1.add(button_1);
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Monitor", null, panel_2, null);
		panel_2.setLayout(null);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(36, 50, 517, 309);
		panel_2.add(lblNewLabel_1);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
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
		btnMonitor.setBounds(69, 403, 87, 23);
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
		btnNewButton_2.setBounds(166, 403, 87, 23);
		panel_2.add(btnNewButton_2);
		
		Panel panel_3 = new Panel();
		tabbedPane.addTab("Position & Go", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(324, 214, 100, 151);
		panel_3.add(textArea_3);
		
		final CarInfo ci=new CarInfo(textArea_3, btnNewButton_5, btnNewButton_4, btnNewButton_3, 
				btnNewButton_6, btnNewButton_7, btnNewButton_8, btnNewButton_11, 
				btnNewButton_10, btnNewButton_9);
		
		final Propel propel=new Propel();
		final MonitorZones mz=new MonitorZones(ci);
		
		final JButton btnNewButton_13 = new JButton("Connect");
		btnNewButton_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!conflag){
					if(mode==0) propel.start();
					else if(mode==1) mz.start();
					btnNewButton_13.setText("Connecting...");
					conflag=true;
					if(!Infoflag){
						Infoflag=true;
						ci.start();
					}
				}else{
					if(mode==0) propel.core=false;
					else if(mode==1) mz.core=false;
					btnNewButton_13.setText("Connect");
					conflag=false;
				}
			}
		});
		btnNewButton_13.setBounds(324, 150, 112, 23);
		panel_3.add(btnNewButton_13);
		
		JButton btnNewButton_15 = new JButton("\u2191");
		btnNewButton_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ci.mc.flag=true;
				ci.mc.control="w";
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ci.mc.flag=false;
			}
		});
		btnNewButton_15.setBounds(392, 370, 63, 23);
		panel_2.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("\u2193");
		btnNewButton_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ci.mc.flag=true;
				ci.mc.control="s";
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ci.mc.flag=false;
			}
		});
		btnNewButton_16.setBounds(392, 403, 63, 23);
		panel_2.add(btnNewButton_16);
		
		JButton button = new JButton("\u2190");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ci.mc.flag=true;
				ci.mc.control="a";
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ci.mc.flag=false;
			}
		});
		button.setBounds(319, 403, 63, 23);
		panel_2.add(button);
		
		JButton btnNewButton_17 = new JButton("\u2192");
		btnNewButton_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ci.mc.flag=true;
				ci.mc.control="d";
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ci.mc.flag=false;
			}
		});
		btnNewButton_17.setBounds(465, 403, 63, 23);
		panel_2.add(btnNewButton_17);
		
		final JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(434, 214, 145, 151);
		panel_3.add(textArea_2);
		
		final Position position=new Position( btnNewButton_5, btnNewButton_4, btnNewButton_3, 
				btnNewButton_6, btnNewButton_7, btnNewButton_8, btnNewButton_11, 
				btnNewButton_10, btnNewButton_9, textArea_2 );
		final WifiSignal _wifisgl=new WifiSignal();
		
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
		
//		final JButton btnNewButton_3 = new JButton("2");
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=2;
					propel.flag=true;
				}else{
					int[] path=new int[]{2, 3, 8, 7, 6, 5, 0, 1, 4, 2};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_3.setBounds(22, 86, 87, 87);
		panel_3.add(btnNewButton_3);
		
//		final JButton btnNewButton_4 = new JButton("1");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=1;
					propel.flag=true;
				}else{
					int[] path=new int[]{1, 0, 5, 6, 7, 8, 3, 2, 4, 1};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_4.setBounds(119, 85, 87, 87);
		panel_3.add(btnNewButton_4);
		
//		final JButton btnNewButton_5 = new JButton("0");
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=0;
					propel.flag=true;
				}else{
					int[] path=new int[]{0, 5, 6, 7, 8, 3, 2, 4, 1, 0};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_5.setBounds(216, 85, 87, 87);
		panel_3.add(btnNewButton_5);
		
//		final JButton btnNewButton_6 = new JButton("3");
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=3;
					propel.flag=true;
				}else{
					int[] path=new int[]{3, 8, 7, 6, 5, 0, 1, 2, 4, 3};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_6.setBounds(22, 183, 87, 87);
		panel_3.add(btnNewButton_6);
		
//		final JButton btnNewButton_7 = new JButton("4");
		btnNewButton_7.setBackground(Color.WHITE);
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=4;
					propel.flag=true;
				}else{
					int[] path=new int[]{4, 7, 6, 5, 0, 1, 2, 3, 8, 4};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_7.setBounds(119, 183, 87, 87);
		panel_3.add(btnNewButton_7);
		
//		final JButton btnNewButton_8 = new JButton("5");
		btnNewButton_8.setBackground(Color.WHITE);
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=5;
					propel.flag=true;
				}else{
					int[] path=new int[]{5, 0, 1, 4, 2, 3, 8, 7, 6, 5};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_8.setBounds(216, 183, 87, 87);
		panel_3.add(btnNewButton_8);
		
//		final JButton btnNewButton_9 = new JButton("8");
		btnNewButton_9.setBackground(Color.WHITE);
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=8;
					propel.flag=true;
				}else{
					int[] path=new int[]{8, 7, 6, 5, 0, 1, 4, 2, 3, 8};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_9.setBounds(22, 280, 87, 87);
		panel_3.add(btnNewButton_9);
		
//		final JButton btnNewButton_10 = new JButton("7");
		btnNewButton_10.setBackground(Color.WHITE);
		btnNewButton_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=7;
					propel.flag=true;
				}else{
					int[] path=new int[]{7, 6, 5, 0, 1, 4, 2, 3, 8, 7};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_10.setBounds(119, 278, 87, 87);
		panel_3.add(btnNewButton_10);
		
//		final JButton btnNewButton_11 = new JButton("6");
		btnNewButton_11.setBackground(Color.WHITE);
		btnNewButton_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( mode==0 ){
					propel.start=position.start;
					propel.end=6;
					propel.flag=true;
				}else{
					int[] path=new int[]{6, 5, 0, 1, 4, 2, 3, 8, 7, 6};
					mz.array=path;
					mz.flag=true;
				}
			}
		});
		btnNewButton_11.setBounds(216, 278, 87, 87);
		panel_3.add(btnNewButton_11);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Best path", "Monitor zones"}));
		comboBox.setBackground(Color.WHITE);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( comboBox.getSelectedIndex()==0 ) mode=0;
				else if( comboBox.getSelectedIndex()==1 )mode=1;
			}
		});
		comboBox.setBounds(324, 86, 112, 21);
		panel_3.add(comboBox);
		
		JLabel lblMode = new JLabel("Mode :");
		lblMode.setForeground(Color.GRAY);
		lblMode.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblMode.setBounds(324, 53, 76, 23);
		panel_3.add(lblMode);
		
		JLabel lblCarInfo = new JLabel("Car Info:");
		lblCarInfo.setForeground(Color.GRAY);
		lblCarInfo.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblCarInfo.setBounds(324, 183, 100, 21);
		panel_3.add(lblCarInfo);
		
		JLabel lblWfiInfo = new JLabel("Wifi Info:");
		lblWfiInfo.setForeground(Color.GRAY);
		lblWfiInfo.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblWfiInfo.setBounds(434, 183, 100, 21);
		panel_3.add(lblWfiInfo);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Search srch=new Search(textField, textField_1, textArea, wclient);
				srch.start();
			}
		});
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Level lvl=new Level(textField, textField_1, textArea, wclient);
				lvl.start();
			}
		});
		
	}
}
