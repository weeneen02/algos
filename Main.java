
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

import java.util.*;
import java.io.*;


class Edge <W, V> 

 {  // Edge를 하나의 클래스로 표현하여 입력 받음
    public W weight; // edge에 부여된 가중치 
    public V v; // edge 끝 부분에 있는 vertex의 번호
    
    public void set_Edge(W weight, V v){ // edge에 값 setting하는 함수
        this.weight = weight;
        this.v = v;
        }
    
    }



public class Main {
    public static void main(String[] args) throws IOException{
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	
    	//BufferedWriter bw = null;
    	int C = Integer.parseInt(br.readLine()); //case
    	
    	for(int k = 0;k<C;k++)
    	{
    	int nV = 0; //총 vertex 개수
        int nE = 0; //총 edge 개수
        double dist[];//가중치 저장
        boolean[] check; //방문여부 조사 
        
                                 
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");        
        while(st.hasMoreTokens()){
			//nextToken()을 통해 띄어쓰기(" ")를 제거하고 남은 숫자를 스트링 형태에서 int로 형변환하여 arr에 저장. k++를 이용해 인덱스 증가
			nV =  Integer.parseInt(st.nextToken());
			nE =  Integer.parseInt(st.nextToken());
		}
        
        //노드의 수와 간선수를 입력받았으면 dist배열의 크기를 설정한다. 
    	dist = new double[nV];
    	check = new boolean[nV];
    	Arrays.fill(dist, Double.MAX_VALUE);
    	Arrays.fill(check, false);
    	/*for(int i = 0;i<nV;i++)
    	{
    		//dist[i] = Double.MAX_VALUE;
    		dist[i] = Double.MAX_VALUE;
    		//dist[i] =   987654321;
    	}*/
    	ArrayList<Edge> adlist[] = new ArrayList[nV];
    	
    	 	
    	PriorityQueue<double[]> pq = new PriorityQueue<>(nE, new Comparator<double[]>() { // 거리값, 정점번호에 대해 정렬
            @Override
            public int compare(double[] o1, double[] o2) {
                if(o1[0] - o2[0] > 0)   return 1;
                return -1;
            }
        });
    	

        for(int i = 0; i < nV; i++){
           // ad.add(new <Double, Integer> Edge()); //edge 초기화 (메모리 할당)
        	adlist[i]= new <Edge> ArrayList();
        }
        
        //System.out.println(C + " "+ nV+" "+nE);  //출력 테스트용
        
        for ( int i = 0;i<nE;i++)
        {
        	st = new StringTokenizer(br.readLine(), " ");  
        	while(st.hasMoreTokens()){
    			//nextToken()을 통해 띄어쓰기(" ")를 제거하고 남은 숫자를 스트링 형태에서 int로 형변환하여 arr에 저장. k++를 이용해 인덱스 증가
    			int t1 =  Integer.parseInt(st.nextToken());
    			int t2 =  Integer.parseInt(st.nextToken());
    			double t3 = Double.parseDouble(st.nextToken());
    			
    
    			Edge <Double, Integer>ed = new  <Double, Integer> Edge();
    			ed.set_Edge(t3, t2);   			
    			adlist[t1].add(ed);
    			
    			ed = new  <Double, Integer> Edge(); //이중 선언이 무엇인가 문제가 된다. 
    			ed.set_Edge(t3, t1);
    			adlist[t2].add(ed);
    			
    		}
        	
        }
        
                        
        /*for (int  i = 0 ;i<adlist.length;i++)
        {
        	for(int j = 0;j<adlist[i].size();j++)
        	{
        		System.out.println(i+ "->" +adlist[i].get(j).v +" "+adlist[i].get(j).weight );
        	}
        	System.out.println("\n");
        	
        }*/ //출력 테스트용 함수 
        
        
        dist[0] = 1; //시작하는 정점 0 의 과 0의 거리는 무한대이다. 이문제는 곱셈이기 때문에 1로 초기화 한다. 
        pq.add(new double[]{1,0});// 큐에 먼저 0부터 넣게 된다.  /거리값, 정점 번호 셋팅
        check[0] = true;
        while(!pq.isEmpty())
        {
        	double d = pq.peek()[0]; //큐의 내용을 삭제하지 않고 가장 위에 있는 값을 보게 된다. 여기서는 가중치 
        	
        	int curr = (int)pq.peek()[1]; //마찬가지로 가장 위에잇는 값의 정점 번ㄴ호
        	if(check[nV-1]==true)break;
        	//System.out.println("now im at "+pq.peek()[1]);
        	pq.poll(); //방문 노드는 큐에서 제외한다. 
        	check[curr] = true;
        	
        	System.out.println("pop");
        	if(dist[curr] < d) continue; //루프에서 나오게되는 조건 현재 노드의 정점과 거리가 같지 않으면 진행
        	                                   //만약 dist에 들어있는 값이 현재값보다 더 짧다면 지그 꺼낸값보다 좋기 때문에 지금 꺼낸 것을 무시한다는 뜻
        										//continue 문은 반복문의 끝으로 이동하여 다음 반복으로 넘어간다 while의 경우는 조건식으로 이동한다
        	for(int i = 0 ;i < adlist[(int)curr].size();i++)
        	{
        		//System.out.println("now curr is"+" "+curr); 
        				
        		int next = (int)adlist[curr].get(i).v;
        		double result = (double)adlist[curr].get(i).weight * dist[curr];
        		//System.out.println("current: " +curr+ " " +" next to "+ next +" next's weight" +(double)adlist[curr].get(i).weight  );
        		
        		if(dist[next] >result)
        		{  
        				dist[next] = result;
            			
            			//System.out.println("now dist is"+ next +" "+dist[next]);
            			pq.add(new double []{dist[next],next});
            			//System.out.println(dist[next]+" "+next);
        			//}
        		}
        		
        	}
        }
        
        
                
      
        DecimalFormat format = new DecimalFormat("########################.0000000000");

        String result = format.format(dist[nV-1]);
        System.out.println(result);
        
    	
    	}
    	
       
    }
}