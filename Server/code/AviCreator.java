package code;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import ch.randelshofer.media.avi.AVIOutputStream;

public class AviCreator extends Thread{
	public int Header;
	public AviCreator( int _Header ){
		Header=_Header;
	}
	public void run(){
		try {
			int i;
			SimpleDateFormat avisdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm");
			Date avidate=new Date();
			String avitime=avisdf.format(avidate);
			AVIOutputStream aviout = null;
			aviout = new AVIOutputStream( new File("D:/CamTest/avi/"+avitime+".avi"), 
					AVIOutputStream.VideoFormat.JPG );
			aviout.setVideoCompressionQuality( 1f );
	        aviout.setTimeScale(1);
	        aviout.setFrameRate(28);

	        for( i=1; i<=280; i++ )
	        	aviout.writeFrame( ImageIO.read(new File( "D:/CamTest/"+Header+"_"+i+".jpeg" ) ) );
	        
	        for( i=1; i<=280; i++ ){
	        	File file=new File("D:/CamTest/"+Header+"_"+i+".jpeg");
	        	file.delete();
	        }
	        aviout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
