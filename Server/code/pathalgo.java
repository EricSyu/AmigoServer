package code;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;



import code.Setting;

public class pathalgo extends Thread implements MonitorProtocol{
	boolean fin=false;
	public  DataOutputStream  out=null;
	Socket socket=null;
	double tang=0;
	vec tnow=null;
	vec tnext=null;
	static double carx=0;
	static double cary=0;
	
	@Override
	public void run(){
		int[] a={2,3,8,7,6,5,0,1,2};
		
		pathgo(a);
		
	}
	public void inipos(){
		//判斷一開始在第幾區  visual
		qtvisual ql=new qtvisual();
		new Thread(ql).start();
		
		//..to be continue
	}
	 
	 
	public void checkpath() throws InterruptedException{
		qtvisual ql=new qtvisual();
		new Thread(ql).start();
		try {int xxx=0;
			while(ql.uang==360||ql.uang==370){
				if(xxx%10==0)System.out.println(ql.uang);
				Thread.sleep(100);xxx++;
				if(xxx==370){ql.uang=0;break;}
			}
			relrot(ql.uang);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void go(vec now,vec next) throws IOException, InterruptedException{
		if(fin==true){
			Thread.currentThread().isInterrupted();
			return;  }
		tnow=now;
		tnext=next;
	if(now.no==0&&next.no==1){
			
		setrotang(0);
		checkpath();
		setgodis(300,200);
		
   }
	
	if(now.no==1&&next.no==0){		
			setrotang(180);			
			setgodis(300,200);			
	}
	if(now.no==1&&next.no==2){
		setrotang(0);	
		checkpath();
		setgodis(300,200);
	}
	if(now.no==1&&next.no==4){
		
		setrotang(90);
		
		setgodis(300,300);
	}
	
	if(now.no==2&&next.no==1){
		setrotang(180);
	
		setgodis(300,200);
		
	}
	if(now.no==2&&next.no==3){
		System.out.println("2-3");
		setrotang(90);
		
		setgodis(360,200);

	}
	if(now.no==2&&next.no==4){

		setrotang(135);
		setgodis(423,200);

	}
	if(now.no==3&&next.no==2){
		
		setrotang(270);
	
		setgodis(360,200);
		
	}
	if(now.no==3&&next.no==8){//90
		System.out.println("3-8");
		setrotang(90);
		checkpath();
		setgodis(360,200);

	}
	if(now.no==8&&next.no==3){
		
		setrotang(270);
	
		setgodis(360,200);
		
	}
	if(now.no==4&&next.no==2){

		setrotang(315);
		
		setgodis(423,200);
		
	}
	
	if(now.no==4&&next.no==1){
		
		setrotang(270);
	
		setgodis(300,200);
	}
	if(now.no==4&&next.no==7){
		setrotang(90);
	
		setgodis(540,200);
	}
	if(now.no==7&&next.no==4){
		
		setrotang(270);
	
		setgodis(480,200);
	}
	
	if(now.no==0&&next.no==0x10){
		go(new vec(0),new vec(5));
		go(new vec(5),new vec(0));

	}
	if(now.no==0x10&&next.no==0){
		go(new vec(0x10),new vec(5));
		go(new vec(5),new vec(0));
	}
	
	if(now.no==0&&next.no==5){
		
		setrotang(90);
		
		setgodis(360,200);
	}
	if(now.no==5&&next.no==0){
		setrotang(270);
		checkpath();
		setgodis(360,200);
		
	}
	if(now.no==5&&next.no==6){
		setrotang(90);

		setgodis(360,200);
		
	}
	if(now.no==6&&next.no==5){
		
		setrotang(270);
		checkpath();
		setgodis(360,200);
		
	}
	if(now.no==8&&next.no==7){

		setrotang(165);//15
		checkpath();
		setgodis(306,200);

		
	}
	if(now.no==7&&next.no==8){

		setrotang(349);
	
		setgodis(306,200);

	}
	if(now.no==7&&next.no==6){

		setrotang(191);//5,11,12
		checkpath();
		setgodis(306,200);

		
	}
	if(now.no==6&&next.no==7){

		setrotang(11);

		setgodis(306,100);
		
	}
  }
	
	public void pathgo(int[] vi){
		
		vec now =null;
		vec next=null;
		for(int i=0;i<vi.length-1;i++){
			now=new vec(vi[i]);
			next=new vec(vi[i+1]);
			
			try {
				
				if(fin==true){
					break;  
					}
				go(now,next);
				
				Thread.sleep(1000);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				System.out.println( "travel bye!-go(now,next)");
				break;
			}

		}
	
		
	}
	public class qtvisual implements Runnable{
//		public String path="";
		int uang=360;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			byte[] buffer = new byte[100];
			  
			try {      
				 DatagramSocket ds=new DatagramSocket();
				InetAddress IPAddress = InetAddress.getByName("127.0.0.1");       
				byte[] sendData = new byte[1024];       
				byte[] receiveData = new byte[1024];       
				String sentence =  Monitor.getpicname;     
				sendData = sentence.getBytes();       
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1023);       
				ds.send(sendPacket); 
				
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
				System.out.println("debug1");
				ds.receive(receivePacket);
				
				String modifiedSentence = new String(receivePacket.getData());      
				System.out.println("FROM SERVER:" + modifiedSentence);
				int xx=modifiedSentence.indexOf("ang");
				int zero=modifiedSentence.indexOf("ang0.");
			
				int dan=0;
				if(zero>1||modifiedSentence.indexOf("ang0,")>1){
					uang=0;
				}else{
					
					double tan=Double.parseDouble(modifiedSentence.substring(xx+3, xx+7));
					
					if((tan*10)%10>5){
						if(tan<0)dan=(int) (tan-1);
						else dan=(int) (tan+1);
					}	
					else dan=(int)(tan);
					System.out.println(xx+" :" + modifiedSentence.substring(xx+3, xx+7)+" dan "+dan);
					uang=dan;
				}
				ds.close();

	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	boolean avoflag=false;
	double si41=Math.sin(Math.PI/180*41);
	double co75=Math.cos(Math.PI/180*75);
	double co15=Math.cos(Math.PI/180*15);
	public int avoidbum2() throws IOException, InterruptedException {
		
		
		try {
			int x=0;
			if(Info.sensor[2]*co15<450){
				forward(0);
				relrot(-85);
				Thread.sleep(500);
				forward(150);				
				while((Info.sensor[0]<300||Info.sensor[1]<250||x<1)&&fin==false){
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(85);
				return 1;
			}
			if(Info.sensor[3]*co15<450){
				forward(0);
				relrot(85);
				Thread.sleep(500);
				forward(150);
				while((Info.sensor[5]<300||Info.sensor[4]<250||x<1)&&fin==false){
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(-85);
				return 1;
			}
			
			if(Info.sensor[1]*si41<240||Info.sensor[2]*co75<200){
				forward(0);
				relrot(-49);
				Thread.sleep(500);
				forward(150);				
				while((Info.sensor[0]<300||Info.sensor[1]<250||x<1)&&fin==false){
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(49);
				return 1;
			}
			if(Info.sensor[4]*si41<240||Info.sensor[3]*co75<200){
				forward(0);
				relrot(49);
				Thread.sleep(500);
				forward(150);
				while((Info.sensor[5]<300||Info.sensor[4]<250||x<1)&&fin==false){
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(-49);
				return 1;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 0;
	}
	public int avoidbum38() throws IOException, InterruptedException {
		try {
			int x=0;
			if(Info.sensor[2]<450){
				forward(0);
				relrot(-75);
				Thread.sleep(500);
				forward(150);				
				while((Info.sensor[0]<300||Info.sensor[1]<250||x<1)&&fin==false){
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(75);
				return 1;
			}
			if(Info.sensor[3]<200){
				forward(0);
				relrot(75);
				Thread.sleep(500);
				forward(150);
				while((Info.sensor[5]<300||Info.sensor[4]<250||x<1)&&fin==false){
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(-75);
				return 1;
			}
			
			if(Info.sensor[1]*si41<240||Info.sensor[2]*co75<200){
				forward(0);
				relrot(-49);
				Thread.sleep(500);
				forward(150);				
				while((Info.sensor[0]<300||Info.sensor[1]<250||x<1)&&fin==false){
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(49);
				return 1;
			}
			if(Info.sensor[4]*si41<240||Info.sensor[3]*co75<200){
				forward(0);
				relrot(49);
				Thread.sleep(500);
				forward(150);
				while((Info.sensor[5]<300||Info.sensor[4]<250||x<1)&&fin==false){
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(-49);
				return 1;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 0;
	}
	public double calbumdis(int l1,int l2){
		double dis=1000;
		if(l1>=l2)dis=l2*Math.cos(Math.PI/180*60);
		else dis=l1*Math.cos(Math.PI/180*60);
		return dis;
	}
	public double calbumdis2(int l1,int l2){
		double dis=1000;
		if(l1>=l2)dis=l2*Math.sin(Math.PI/180*60);
		else dis=l1*Math.sin(Math.PI/180*60);
		return dis;
	}
	
	public void  backprepoint(){
//		(tang + 180)%360;
		
		try {
			int bang=(int)(Math.atan2(tnow.y-Info.y, tnow.x-Info.x)*180/Math.PI);
			System.out.println( "backprepoint: x"+ tnow.x+" y "+ tnow.y+" bang "+bang);
			setrotang((360+bang)%360);
			double tdis=Math.sqrt(Math.pow(tnow.y-Info.y, 2)+Math.pow(tnow.x-Info.x,2) );
			setgodis(tdis, 150);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public void avotonext(){
		int nang=(int)(Math.atan2(tnext.y-Info.y, tnext.x-Info.x)*180/Math.PI);
		System.out.println( "avotonext: x"+ Info.x+" y "+ Info.y+" bang "+nang);
		try {
			setrotang((360+nang)%360);
			double tdis=Math.sqrt(Math.pow(tnext.y-Info.y, 2)+Math.pow(tnext.x-Info.x,2) );
			setgodis(tdis, 150);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void forward(int speed) throws IOException{
		System.out.println( "forward ");
		if(socket!=null)socket.close();
		socket=null;
		socket=Setting.server.accept();
		out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(Trans);
		out.flush();
		out.writeInt(speed);
		out.flush();
		socket.close();
		
	}
	public void relrot(int ang) throws IOException{
		System.out.println( "relrot: "+ang);
		socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 double xt=0;
		out.writeInt(Rotate);
		out.flush();
		if(ang==0)out.writeInt(0);
		if(ang>0)out.writeInt(10);
		else if(ang<0)out.writeInt(-10);
		
		out.flush();
		double tang=Math.abs(ang)/10;
		while(xt<tang&&fin==false){
			xt+=0.1;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		 out.close();
		 socket.close();
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(Rotate);
		 out.flush();
		 out.writeInt(0);
		 out.flush();
		 out.close();
		 socket.close();
		
	}
	public void setrotang(int ang) throws IOException, InterruptedException{
		
		System.out.println( "setrotang");
		socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(Rotate);
		 out.flush();
		 out.writeInt(20);
		 socket.close();
		 socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(AbsoluteHeading);
		 out.flush();
		 double fixang=ang-ang/45;
		 out.writeInt((int) fixang);
		 out.flush();
		 Thread.sleep(4000);
		 
		 socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(Rotate);
		 out.flush();
		 out.writeInt(0);
		 out.flush();
		 out.close();
		 socket.close();
		 int x=0;
		 Thread.sleep(2000);
		 while(Math.abs(Info.theta-fixang)>6||Math.abs(Info.theta-fixang)<354&&Math.abs(Info.theta-fixang)>350){

		 }
		 if(Math.abs(Info.theta-fixang)>3||Math.abs(Info.theta-fixang)<358&&Math.abs(Info.theta-fixang)>350){
			 relrot(-45);
			 setrotang(ang); 
		 }
		 
	}

	public void setgodis(double dis,int speed) throws IOException, InterruptedException{
		System.out.println( "setgodis");
		double dtime=dis/(speed/10);
		double xt=0;
		
		forward(speed);
		
		while(xt<dtime&&fin==false){
			if(tnow.no==3&&tnext.no==8){
				if(avoidbum38()==1){
					avotonext();
					break;
				}
			}
			else if(avoidbum2()==1){
				avotonext();
				break;
			}
			xt+=0.1;
			Thread.sleep(100);
			carx=carx+0.1*speed/10*Math.cos(Info.theta);
			cary=cary+0.1*speed/10*Math.sin(Info.theta);
		}
//		Thread.sleep((long) dtime);
		forward(0);
		
		
	
	}
	public  void setstop(boolean t) {
		this.fin = t;
		Thread.currentThread().interrupt();
		while(Thread.currentThread().isInterrupted()==false){}
		this.fin = t;
		try {
			forward(0);
			relrot(0);
			out=null;
			socket=null;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	
	}
	public class vec{
		int x=0;
		int y=0;
		int no=0;
		vec(){}
		vec(int no){
			this.no=no;
			switch(no){
			case 0:
				x=0;
				y=0;
				break;
			case 1: 
				x=300;
				y=0;
				break;
			case 2:
				x=600;
				y=0;
				break;
			case 3:
				x=600;
				y=360;
				break;
			case 4:
				x=300;
				y=300;
				break;
			case 5:
				x=0;
				y=360;
				break;
			case 6:
				x=0;
				y=720;
				break;
			case 7:
				x=300;
				y=900;
				break;
			case 8:
				x=600;
				y=720;
				break;
			
				
			default: 
                System.out.println(""+no);
			}
		}
		public void set(int x,int y,int no){
			this.x=x;
			this.y=y;
			this.no=no;
		} 
		
	} 

}
