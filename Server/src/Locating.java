import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//import com.googlecode.javacv.cpp.opencv_core.CvMat;
//import com.googlecode.javacv.cpp.opencv_legacy.CvEM;
//import com.googlecode.javacv.cpp.opencv_legacy.CvEMParams;

public class Locating extends Thread{
	private JTextArea textArea;
	private JLabel lblNewLabel;
	static Boolean flag=true;
//	CvMat matrix;
//	CvEMParams params;
	
	public Locating( JLabel lblNewLabel0, JTextArea textArea0 ){
		lblNewLabel=lblNewLabel0;
		textArea=textArea0;
//		params.covs();
//		params.means();
//		params.weights();
//		params.probs();
//		params.nclusters(3);
//		params.cov_mat_type(CvEM.COV_MAT_SPHERICAL);
//		params.start_step(CvEM.START_AUTO_STEP);
//		params.term_crit().max_iter(10);    // 限制迭代最高次數
//		params.term_crit().epsilon(0.1);
//		params.term_crit().type(getPriority());
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
					
					if( count>1999 ){
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
					
					ImageIcon imgicon = new ImageIcon("C:\\Users\\owuser\\Desktop\\404\\zone"+(mindex+1)+".png");
					lblNewLabel.setIcon(imgicon);
					
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
