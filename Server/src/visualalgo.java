import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;


public class visualalgo extends Thread implements MonitorProtocol{
	boolean fin=false;
	public  DataOutputStream  out=null;
	Socket socket=null;
	double tang=0;
	vec tnow=null;
	public void run(){
		int[] a={2,3,8};
		pathgo(a);
	}
	public void inipos(){
		//判斷一開始在第幾區  visual
		qtvisual ql=new qtvisual();
		new Thread(ql).start();
		//..to be continue
	}
	
	public void go(vec now,vec next) throws IOException, InterruptedException{
		if(fin==true){
			Thread.currentThread().isInterrupted();
			return;  }
		tnow=now;
	if(now.no==0&&next.no==1){
			
		setrotang(0);
	
		setgodis(300,250);
		
   }
	
	if(now.no==1&&next.no==0){		
			setrotang(180);
		
			setgodis(300,250);			
	}
	if(now.no==1&&next.no==2){
		setrotang(0);
	
		setgodis(300,250);
	}
	if(now.no==1&&next.no==4){
		
		setrotang(90);
		
		setgodis(300,300);
	}
	
	if(now.no==2&&next.no==1){
		setrotang(180);
	
		setgodis(300,250);
		
	}
	if(now.no==2&&next.no==3){
		setrotang(90);
	
		setgodis(360,250);

	}
	if(now.no==2&&next.no==4){

		setrotang(135);
	
		setgodis(423,250);

	}
	if(now.no==3&&next.no==2){
		
		setrotang(270);
	
		setgodis(360,200);
		
	}
	if(now.no==3&&next.no==8){//90
		setrotang(90);
	
		setgodis(360,250);
		
	}
	if(now.no==8&&next.no==3){
		
		setrotang(270);
	
		setgodis(360,250);
		
	}
	if(now.no==4&&next.no==2){

		setrotang(315);
	
		setgodis(423,250);
		
	}
	
	if(now.no==4&&next.no==1){
		
		setrotang(270);
	
		setgodis(300,250);
	}
	if(now.no==4&&next.no==7){
		setrotang(90);
	
		setgodis(540,250);
	}
	if(now.no==7&&next.no==4){
		
		setrotang(270);
	
		setgodis(480,250);
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
	
		setgodis(360,250);
	}
	if(now.no==5&&next.no==0){
		setrotang(270);

		setgodis(360,250);
		
	}
	if(now.no==5&&next.no==6){
		setrotang(90);

		setgodis(360,250);
		
	}
	if(now.no==6&&next.no==5){
		
		setrotang(270);
	
		setgodis(360,250);
		
	}
	if(now.no==8&&next.no==7){

		setrotang(168);//15
	
		setgodis(306,200);

		
	}
	if(now.no==7&&next.no==8){

		setrotang(349);
	
		setgodis(306,200);

	}
	if(now.no==7&&next.no==6){

		setrotang(191);//5,11,12
	
		setgodis(306,250);

		
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
					Thread.currentThread().isInterrupted();
					return;  }
				go(now,next);
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println( "travel bye!-go(now,next)");
			}

		}
	
		
	}
	public class qtvisual implements Runnable{
//		public String path="";
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
			
			   
				ds.close();

	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	public void avoidbum() throws IOException, InterruptedException {
		 boolean stop=false;
		 
	
				if(Info.sensor[2]<450||Info.sensor[3]<450){
					try {
						if(Info.sensor[1]>Info.sensor[4]){
							forward(0);
							relrot(79);
							forward(150);
							while(Info.sensor[5]<500&&fin==false){
								if(Info.sensor[2]<450||Info.sensor[3]<450){
									backprepoint();
								}
							
							}
							forward(0);
							relrot(-79);
							
						}else {
							forward(0);
							relrot(-79);
							forward(150);
							while(Info.sensor[0]<500&&fin==false){
								if(Info.sensor[2]<450||Info.sensor[3]<450){
									backprepoint();
								}
							
							}
							forward(0);
							relrot(79);
							}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//if
				else if(Info.sensor[1]<250||Info.sensor[4]<250){
					if(Info.sensor[1]>Info.sensor[4]){
					
						
						forward(0);
						relrot(49);
						forward(150);
						while(Info.sensor[5]<500&&fin==false){
							if(Info.sensor[2]<450||Info.sensor[3]<450){
								backprepoint();
							}
						}
						forward(0);
						relrot(-49);
						
					}else {
							forward(0);
							relrot(-49);
							forward(150);
							while(Info.sensor[0]<500&&fin==false){
								if(Info.sensor[2]<450||Info.sensor[3]<450){
									backprepoint();
								}
							}
							forward(0);
							relrot(49);
							
						}
					} 
				
			
		}
	public void  backprepoint(){
//		(tang + 180)%360;
		
		try {
			int bang=(int)(Math.atan2(tnow.y-Info.y, tnow.x-Info.x)*180/Math.PI);
			setrotang(bang);
			while(Math.abs(tnow.y-Info.y)<15&&Math.abs(tnow.x-Info.x)<15){
				forward(150);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void forward(int speed) throws IOException{
		socket=null;
		socket=Setting.server.accept();
		out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(Trans);
		out.flush();
		out.writeInt(speed);
		out.flush();
		socket.close();
	}
	public void relrot(int ang) throws IOException, InterruptedException{
		socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 
		out.writeInt(Rotate);
		out.flush();
		out.writeInt(10);
		out.flush();
		double tang=Math.abs(ang/10);
		Thread.sleep((long) tang);
		out.writeInt(Rotate);
		out.flush();
		out.writeInt(0);
		out.close();
		socket.close();
	}
	public void setrotang(int ang) throws IOException{
		socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(AbsoluteHeading);
		out.flush();
		out.writeInt(ang);
		out.flush();
		out.close();
		socket.close();
	}
	public void setgodis(double dis,int speed) throws IOException, InterruptedException{
		socket=null;
		 socket=Setting.server.accept();
		  out = new DataOutputStream(socket.getOutputStream());
		double dtime=dis/(speed/10);
		double xt=0;
		
		out.writeInt(Trans);
		out.flush();
		out.writeInt((int) speed);
		out.flush();
		while(xt<dtime&&fin==false){
			avoidbum();
			xt+=0.1;
			Thread.sleep(100);
		}
//		Thread.sleep((long) dtime);
		out.writeInt(0);
		
		socket.close();
	
	}
	public  void setstop(boolean t) {
		
		Thread.currentThread().interrupt();
		while(Thread.currentThread().isInterrupted()==false){}
		this.fin = t;
		try {
			forward(0);
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
