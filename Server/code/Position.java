package code;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.CvMat;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_legacy.CvEM;
import org.bytedeco.javacpp.opencv_legacy.CvEMParams;
import org.bytedeco.javacpp.helper.opencv_core.AbstractCvMat;

public class Position extends Thread{
	private JButton zone0, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8;
	private JTextArea textArea;
	public double NorMax=1.0, NorMin=0.0, reset=0;
	static Boolean flag=true;
	static int start=-1;
	static String _str="-1";
	static CvEMParams params=new CvEMParams();
	static CvMat Max=CvMat.create( 1, 6, opencv_core.CV_32FC1 );
	static CvMat Min=CvMat.create( 1, 6, opencv_core.CV_32FC1 );
	static CvMat[] trainData=new CvMat[9];
	int[][] LockZone=new int[5][5]; 
	
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
	
	public void Parameters(){
		params.covs();
		params.means();
		params.weights();
		params.probs();
		params.nclusters(3);
		params.cov_mat_type(CvEM.COV_MAT_SPHERICAL);
		params.start_step(CvEM.START_AUTO_STEP);
		params.term_crit().max_iter(300);    // 嚙踝蕭嚙踐迭嚙瞇嚙諒堆蕭嚙踝蕭嚙踝蕭
		params.term_crit().epsilon(0.001);
		params.term_crit().type(opencv_core.CV_TERMCRIT_ITER|opencv_core.CV_TERMCRIT_EPS);
	}
	
	public void PredictZones(int zone, Mat TestFeature, CvEM [] gmm_model){
		double result=gmm_model[0].calcLikelihood(TestFeature);
		int _zone=0, m=1, n=1, i, j;
		
		for( i=1; i<gmm_model.length; i++){
			if( gmm_model[i].calcLikelihood(TestFeature)>result ){
				result = gmm_model[i].calcLikelihood(TestFeature);
				_zone=i;
			}
		}
		if( reset<100 ){
			reset=0;
			zone=-1;
		}
		if( zone==-1 ) zone=_zone;
		else{
			for( i=1; i<4; i++ ){
				for( j=1; j<4; j++ ){
					if( _zone==LockZone[i][j] ){
						m=i;
						n=j;
						break;
					}
				}
			}
			if( _zone==LockZone[m][n] || _zone==LockZone[m+1][n] || _zone==LockZone[m-1][n] 
					|| _zone==LockZone[m][n+1] || _zone==LockZone[m][n-1])
				zone=_zone;
		}
		
		switch(zone){
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
	}
	
	public void FindMaxMin() throws IOException{
		int i, j, cnt=0;
		while( cnt<9 ){
			FileReader fr = new FileReader("C://CamTest//"+(cnt+1)+".txt");
			BufferedReader br = new BufferedReader(fr);
			String ln;
			for( i=0; i<248; i++ ){
				ln=br.readLine();
				String[] strdblevel = ln.split("  ");
				for( j=0; j<strdblevel.length; j++ ){
					if( Max.get( 0, j )<Double.parseDouble(strdblevel[j]) )
						Max.put( 0, j, Double.parseDouble(strdblevel[j]) );
					if( Min.get( 0, j )>Double.parseDouble(strdblevel[j]) )
						Min.put( 0, j, Double.parseDouble(strdblevel[j]) );
				}	
			}
			
			fr.close();
			br.close();
			cnt++;
		}
	}
	
	public void Normalize(String[] strdblevel, int cnt, int i){
		int j;
		for( j=0; j<strdblevel.length; j++ ){
			double tmpVal=Double.parseDouble(strdblevel[j]);
			if( tmpVal<Min.get( 0, j ) ) tmpVal=NorMin;
			else if( tmpVal>Max.get( 0, j ) ) tmpVal=NorMax;
			else{
				if( Min.get( 0, j )==Max.get( 0, j ) )
					tmpVal=( NorMax-NorMin )*( tmpVal-Min.get( 0, j ) );
				else
					tmpVal=( NorMax-NorMin )*( tmpVal-Min.get( 0, j ) )/( Max.get( 0, j )-Min.get( 0, j ) )+NorMin;
			}
			trainData[cnt].put(i, j, tmpVal);
		}
	}
	
	@Override
	public void run(){

		int i, j, cnt=0, avg=0, zone=0, count=1;
		CvEM [] gmm_model=new CvEM[9];
		
		for( i=0; i<5; i++ )
			for( j=0; j<5; j++ )
				if( i==0 || j==0 ) LockZone[i][j]=-1;
		for( i=1; i<4; i++ )
			for( j=1; j<4; j++ )
				LockZone[j][i]=count++;
		
		for( i=0; i<6; i++ ){
			Max.put( 0, i, -100 );
			Min.put( 0, i, -20 );
		}
		
		for( i=0; i<9; i++ ) trainData[i]=AbstractCvMat.create(248, 6, opencv_core.CV_32FC1);
		
		try{
			FindMaxMin();
			
			while( cnt<9 ){
				FileReader fr = new FileReader("C://CamTest//"+(cnt+1)+".txt");
				BufferedReader br = new BufferedReader(fr);
				String ln;
				for( i=0; i<248; i++ ){
					ln=br.readLine();
					String[] strdblevel = ln.split("  ");
					Normalize(strdblevel, cnt, i);
				}
				fr.close();
				br.close();
				cnt++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Parameters();
		
		for( i=0; i<9; i++ ) gmm_model[i]=new CvEM();
		
		for( i=0; i<9; i++ ) gmm_model[i].train(trainData[i], null, params, null);
		while(true){
			while(flag){
				try{
					CvMat matTestFeature=AbstractCvMat.create(1, 6, opencv_core.CV_32FC1);
					
					int[] level=new int[]{0, 0, 0, 0, 0, 0};
					int[] _level=new int[]{-100, -100, -100, -100, -100, -100};
					while(avg<5){
						
								
						FileReader posfr = new FileReader("C://CamTest//Positioning.txt");
						BufferedReader posbr = new BufferedReader(posfr);
						synchronized(posbr){
							String reg=posbr.readLine();
							_str=reg;
							if( _str!=null ){
								if( _str.indexOf("SSID")>-1 ){
									String[] wifi = _str.split("BSSID: ");
									
									FileReader macfr = new FileReader("C://CamTest//Wifimac.txt");
									BufferedReader macbr = new BufferedReader(macfr);
									String str=macbr.readLine();
									String[] mac = str.split("__");
									
									macbr.close();
									posbr.close();
									
									for( i=1; i<wifi.length; i++){
										for( j=0; j<mac.length; j++ ){
											if( wifi[i].indexOf(mac[j])!=-1 )
												_level[j]=Integer.parseInt(wifi[i].substring(
														wifi[i].indexOf("level: -")+7, wifi[i].indexOf("level: -")+10));
										}
									}
									for( i=0; i<mac.length; i++ ) level[i]+=_level[i];
									Thread.sleep(10);
									avg++;
								}
							}
						}
					}
					avg=0;
					for( i=0; i<6; i++ ){ 
						level[i]/=5;
						
						double tmpVal=level[i];
						if( tmpVal<Min.get( 0, i ) ) tmpVal=NorMin;
						else if( tmpVal>Max.get( 0, i ) ) tmpVal=NorMax;
						else{
							if( Min.get( 0, i )==Max.get( 0, i ) )
								tmpVal=( NorMax-NorMin )*( tmpVal-Min.get( 0, i ) );
							else
								tmpVal=( NorMax-NorMin )*( tmpVal-Min.get( 0, i ) )/( Max.get( 0, i )-Min.get( 0, i ) )+NorMin;
						}
						matTestFeature.put(0, i, tmpVal);
					}
					
					FileReader fr = new FileReader("C://CamTest//Wifimac.txt");
					BufferedReader br = new BufferedReader(fr);
					String str=br.readLine();
					String[] mac = str.split("__");
					String info="";
					for( i=0; i<mac.length; i++ ) info+=mac[i]+": "+level[i]+"\n";
					textArea.setText(info);
					br.close();
					
					Mat TestFeature=new Mat(matTestFeature);
					
					PredictZones(zone, TestFeature, gmm_model);
					Thread.sleep(10);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
