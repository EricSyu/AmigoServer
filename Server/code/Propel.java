package code;


public class Propel {
//	static int start, end;
	static Boolean flag=false, core=true;
//	Socket socket;
	int ini=9999, i;
	FloydWarshall fw=null;
	int[][] zone404={
			 //	 0   1   2    3    4     5   6    7     8
				{0, 300, ini, ini, ini, 360, ini, ini, ini},//0
				{300, 0, 300, ini, 300, ini, ini, ini, ini},//1
				{ini, 300, 0, 360, 423, ini, ini, ini, ini},//2
				{ini, ini, 360, 0, ini, ini, ini, ini, 360},//3
				{ini, 300, 423, ini, 0, ini, ini, 540, ini},//4
				{360, ini, ini, ini, ini, 0, 360, ini, ini},//5
				{ini, ini, ini, ini, ini, 360, 0, 350, ini},//6
				{ini, ini, ini, ini, 540, ini, 350, 0, 350},//7
				{ini, ini, ini, 360, ini, ini, ini, 350, 0},//8
		};
	Propel(){
		 fw=new FloydWarshall();
//		fw.Cal(zone404);
	}
	public int[] go(int start,int end){
		
		int[] result=fw.FindPathArray(start, end);
		return result;
	}
	
}
