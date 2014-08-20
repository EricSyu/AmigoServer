import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JTextArea;


public class Position extends Thread{
	private JButton zone0, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8;
	private JTextArea textArea;
	static Boolean flag=true;
	static int start=-1;
	
	public Position( JButton _zone0, JButton _zone1, JButton _zone2, JButton _zone3, JButton _zone4, 
			JButton _zone5, JButton _zone6, JButton _zone7, JButton _zone8, JTextArea _textArea ){
		zone0=_zone0;
		zone1=_zone1;
		zone2=_zone2;
		zone3=_zone3;
		zone4=_zone4;
		zone5=_zone5;
		zone6=_zone6;
		zone7=_zone7;
		zone8=_zone8;
		textArea=_textArea;
	}
	@Override
	public void run(){
		int i=0, j=0, m=0, n=0, mindex=-1, count=0;
		int[][] zone=new int[5][5];
		
		for( i=0; i<5; i++ ) for( j=0; j<5; j++ ) zone[i][j]=-1;
		for( i=1; i<4; i++ ) for( j=1; j<4; j++ ) zone[i][j]=count++;
		
		count=0;
		
		while(true){
			while(flag){
				try{
					FileReader fr = new FileReader("C://CamTest//Wifimac.txt");
					BufferedReader br = new BufferedReader(fr);
					String str=br.readLine();
					String[] mac = str.split("__");
					
					FileReader fr0 = new FileReader("C://CamTest//Positioning.txt");
					BufferedReader br0 = new BufferedReader(fr0);
					String _str=br0.readLine();
					String[] wifi = _str.split("BSSID: ");
					
					int[] sum=new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					int[] level=new int[mac.length];
					int cnt=0;
					
					for( i=0; i<mac.length; i++ ) level[i]=-100;
					
					for( i=1; i<wifi.length; i++){
						for( j=0; j<mac.length; j++ )
							if( wifi[i].indexOf(mac[j])!=-1 )
								level[j]=Integer.parseInt(wifi[i].substring(wifi[i].indexOf("level: -")+7, 
									wifi[i].indexOf("level: -")+10));
					}
					
					while( cnt<9 ){
						FileReader fr1 = new FileReader("C://CamTest//"+(cnt+1)+".txt");
						BufferedReader br1 = new BufferedReader(fr1);
						String ln;
						while( ( ln=br1.readLine() )!=null ){
							String[] rdblevel = ln.split("  ");
							int[] dblevel=new int[rdblevel.length];
							int rsum=0;
							for( i=0; i<rdblevel.length; i++ ) dblevel[i]=Integer.parseInt(rdblevel[i]);
							
							for( i=0; i<rdblevel.length; i++ ) rsum+=Math.pow((dblevel[i]-level[i]), 2);
							sum[cnt]+=Math.sqrt(rsum);
						}
						cnt++;
						fr1.close();
						br1.close();
					}
					
					int min=sum[0], _mindex=0;
					String info="";
					for( i=0; i<9; i++ ){
						if( min>sum[i] ) {
							min=sum[i];
							_mindex=i;
						}
					}
					
					if( count>2999 ){
						mindex=-1;
						count=0;
					}
					
					if( mindex==-1 ) mindex=_mindex;
					else{
						for( i=1; i<4; i++ )
							for( j=1; j<4; j++ )
								if( mindex==zone[i][j] ){
									m=i;
									n=j;
									break;
								}
						
						if( _mindex==zone[m][n] || _mindex==zone[m+1][n] || _mindex==zone[m-1][n] 
								|| _mindex==zone[m][n+1] || _mindex==zone[m][n-1])
								mindex=_mindex;
					}
					
					for( i=0; i<mac.length; i++ ) info+=mac[i]+": "+level[i]+"\n";
					
					textArea.setText(info);
					
					switch(mindex){
					case 0:
						start=2;
						zone2.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 1: 
						start=3;
						zone3.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 2: 
						start=8;
						zone8.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone2.setBackground(Color.white);
						break;
					case 3: 
						start=1;
						zone1.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 4: 
						start=4;
						zone4.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 5: 
						start=7;
						zone7.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 6: 
						start=0;
						zone0.setBackground(Color.orange);
						zone2.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 7: 
						start=5;
						zone5.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 8: 
						start=6;
						zone6.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					}
					
					fr.close();
					br.close();
					fr0.close();
					br0.close();
					
					count++;
				}catch(Exception exc){}
			}
		}
		
	}
}
