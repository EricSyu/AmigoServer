import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextArea;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.CvMat;
import org.bytedeco.javacpp.opencv_legacy.CvEM;
import org.bytedeco.javacpp.opencv_legacy.CvEMParams;


public class Position extends Thread{
	private JButton zone0, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8;
	private JTextArea textArea;
	static Boolean flag=true;
	static int start=-1;
	CvEMParams params=null;
	
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
		params.covs();
		params.means();
		params.weights();
		params.probs();
		params.nclusters(3);
		params.cov_mat_type(CvEM.COV_MAT_SPHERICAL);
		params.start_step(CvEM.START_AUTO_STEP);
		params.term_crit().max_iter(300);    // 限制迭代最高次數
		params.term_crit().epsilon(0.001);
		params.term_crit().type(opencv_core.CV_TERMCRIT_ITER|opencv_core.CV_TERMCRIT_EPS);
	}
	@Override
	public void run(){
		int i=0, j=0;
		CvMat label;
		CvEM [] gmm_model=new CvEM[9];
		
		CvMat matTrainData1=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone1
		FileReader fr = new FileReader("C://CamTest//1.txt");
		BufferedReader br = new BufferedReader(fr);
		String ln;
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData1.cols(); j++ ){
				matTrainData1.put( i, j, intdblevel[j] );
			}
		}
		fr.close();
		br.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData1.get( i, j ));
			}
			System.out.println();
		}
		
		CvMat matTrainData2=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone2
		FileReader fr2 = new FileReader("C://CamTest//2.txt");
		BufferedReader br2 = new BufferedReader(fr2);
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br2.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData2.cols(); j++ ){
				matTrainData2.put( i, j, intdblevel[j] );
			}
		}
		fr2.close();
		br2.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData2.get( i, j ));
			}
			System.out.println();
		}
		
		CvMat matTrainData3=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone3
		FileReader fr3 = new FileReader("C://CamTest//3.txt");
		BufferedReader br3 = new BufferedReader(fr3);
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br3.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData3.cols(); j++ ){
				matTrainData3.put( i, j, intdblevel[j] );
			}
		}
		fr3.close();
		br3.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData3.get( i, j ));
			}
			System.out.println();
		}
		
		CvMat matTrainData4=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone4
		FileReader fr4 = new FileReader("C://CamTest//4.txt");
		BufferedReader br4 = new BufferedReader(fr4);
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br4.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData4.cols(); j++ ){
				matTrainData4.put( i, j, intdblevel[j] );
			}
		}
		fr4.close();
		br4.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData4.get( i, j ));
			}
			System.out.println();
		}
		
		CvMat matTrainData5=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone5
		FileReader fr5 = new FileReader("C://CamTest//5.txt");
		BufferedReader br5 = new BufferedReader(fr5);
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br5.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData4.cols(); j++ ){
				matTrainData5.put( i, j, intdblevel[j] );
			}
		}
		fr5.close();
		br5.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData5.get( i, j ));
			}
			System.out.println();
		}
		
		CvMat matTrainData6=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone6
		FileReader fr6 = new FileReader("C://CamTest//6.txt");
		BufferedReader br6 = new BufferedReader(fr6);
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br6.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData4.cols(); j++ ){
				matTrainData6.put( i, j, intdblevel[j] );
			}
		}
		fr6.close();
		br6.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData6.get( i, j ));
			}
			System.out.println();
		}
		
		CvMat matTrainData7=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone7
		FileReader fr7 = new FileReader("C://CamTest//7.txt");
		BufferedReader br7 = new BufferedReader(fr7);
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br7.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData4.cols(); j++ ){
				matTrainData7.put( i, j, intdblevel[j] );
			}
		}
		fr7.close();
		br7.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData7.get( i, j ));
			}
			System.out.println();
		}
		
		CvMat matTrainData8=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone8
		FileReader fr8 = new FileReader("C://CamTest//8.txt");
		BufferedReader br8 = new BufferedReader(fr8);
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br8.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData4.cols(); j++ ){
				matTrainData8.put( i, j, intdblevel[j] );
			}
		}
		fr8.close();
		br8.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData8.get( i, j ));
			}
			System.out.println();
		}
		
		CvMat matTrainData9=opencv_core.CvMat.create(248, 6, opencv_core.CV_32F); //  zone9
		FileReader fr9 = new FileReader("C://CamTest//9.txt");
		BufferedReader br9 = new BufferedReader(fr9);
		for( i=0; i<matTrainData1.rows(); i++ ){
			ln=br9.readLine();
			String[] strdblevel = ln.split("  ");
			int[] intdblevel=new int[strdblevel.length];
			
			for( j=0; j<strdblevel.length; j++ ) intdblevel[j]=Integer.parseInt(strdblevel[j]);
			
			for( j=0; j<matTrainData4.cols(); j++ ){
				matTrainData9.put( i, j, intdblevel[j] );
		}
		fr9.close();
		br9.close();
		for( i=0; i<matTrainData1.rows(); i++ ){
			for( j=0; j<matTrainData1.cols(); j++ ){
				System.out.print(matTrainData9.get( i, j ));
			}
			System.out.println();
		}
		
		gmm_model[0].train(matTrainData1, null, params, label);
		gmm_model[1].train(matTrainData1, null, params, label);
		gmm_model[2].train(matTrainData1, null, params, label);
		gmm_model[3].train(matTrainData1, null, params, label);
		gmm_model[4].train(matTrainData1, null, params, label);
		gmm_model[5].train(matTrainData1, null, params, label);
		gmm_model[6].train(matTrainData1, null, params, label);
		gmm_model[7].train(matTrainData1, null, params, label);
		gmm_model[8].train(matTrainData1, null, params, label);
		
		
		
		
		int[][] zone=new int[5][5];
		
		for( i=0; i<5; i++ ) for( j=0; j<5; j++ ) zone[i][j]=-1;
		for( i=1; i<4; i++ ) for( j=1; j<4; j++ ) zone[i][j]=count++;
		
		count=0;
		
		while(true){
			while(flag){
				try{
					int[] sum=new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					int[] level=new int[6];
					int avg=20;
					
					while( avg>0 ){
						FileReader fr0 = new FileReader("C://CamTest//Positioning.txt");
						BufferedReader br0 = new BufferedReader(fr0);
						String _str=br0.readLine();
						
						if( _str.indexOf("SSID")>-1 ){
							String[] wifi = _str.split("BSSID: ");
							
							FileReader fr = new FileReader("C://CamTest//Wifimac.txt");
							BufferedReader br = new BufferedReader(fr);
							String str=br.readLine();
							String[] mac = str.split("__");
							
							fr.close();
							
							if( avg==20 ) for( i=0; i<mac.length; i++ ) level[i]=-100;
							
							for( i=1; i<wifi.length; i++){
								for( j=0; j<mac.length; j++ )
									if( wifi[i].indexOf(mac[j])>-1 ){
										if( avg==20 ) level[j]=Integer.parseInt(wifi[i].substring(wifi[i].indexOf("level: -")+7, wifi[i].indexOf("level: -")+10));
										else level[j]+=Integer.parseInt(wifi[i].substring(wifi[i].indexOf("level: -")+7, wifi[i].indexOf("level: -")+10));
									}		
							}
							avg--;
							Thread.sleep(50);
						}
						fr0.close();
						br0.close();
					}
					for( j=0; j<6; j++ ) level[j]/=20;
					
					FileReader fr = new FileReader("C://CamTest//Wifimac.txt");
					BufferedReader br = new BufferedReader(fr);
					String str=br.readLine();
					String[] mac = str.split("__");
					
					String info="";
					for( i=0; i<mac.length; i++ ) info+=mac[i]+": "+level[i]+"\n";
					
					textArea.setText(info);
					
					fr.close();
					br.close();
					
					int cnt=0;
					
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
					for( i=0; i<9; i++ ){
						if( min>sum[i] ) {
							min=sum[i];
							_mindex=i;
						}
//						info+=sum[i]+"\n";
					}
//					textArea.setText(info);
					if( count>999 ){
						mindex=-1;
						count=0;
					}
					
					if( mindex==-1 ) mindex=_mindex;
					else if( min<6500 ){
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
					count++;
				}catch(Exception exc){}
			}
		}
	}
}
