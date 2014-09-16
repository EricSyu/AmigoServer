package code;

import javax.swing.JTextArea;

public class carxy implements Runnable{
	public JTextArea textArea;
	public void print( JTextArea _textArea){
		 textArea=_textArea;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			textArea.setText("carx: "+pathalgo.carx+"\ncary: "+pathalgo.cary);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}

	

}
