
public class FloydWarshall {
		int[][] A=null;
		int[][] medium=null;
		int[] temp=null;
		
		public void Cal(int cost[][]){
			int n=cost.length;
			A=new int[n][n];
			temp=new int[n];
			medium=new int[n][n];
			
			for( int i=0; i<n; i++ ){
				temp[i]=-1;
				for( int j=0; j<n; j++ ){
					A[i][j]=cost[i][j];
					medium[i][j]=-1;
				}
			}

			for( int i=0; i<n; i++ ) A[i][i]=0;
			
			for( int i=0; i<n; i++ ){
				for( int j=0; j<n; j++ ){
					for( int k=0; k<n; k++ )
						if( A[i][k]+A[k][j]<A[i][j] ){
							A[i][j]=A[i][k]+A[k][j];
							medium[i][j]=k;
						}
				}
			}
		}
		
		int x=1;
		
		public void FindPath( int i, int j ){
			if( medium[i][j]==-1 ) return;
			FindPath(i, medium[i][j]);
			temp[x++]=medium[i][j];
			FindPath(medium[i][j], j);
		}
		
		public int[] FindPathArray( int s, int d ){
			FindPath(s,d);
			temp[0]=s;
			int[] respath = null;
			int t=0;
			while( temp[t]!=-1 ) t++;
			temp[t]=d;
			for( int i=0; i<=t; i++){
				if( i==0 ) respath=new int[t+1];
				respath[i]=temp[i];
			}
			
			return respath;
		}
	}