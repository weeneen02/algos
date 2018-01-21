
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    	
    	int nV = 0; //총 vertex 개수
        int nE = 0; //총 edge 개수
        double dist[];//가중치 저장
        
        
        //Edge <Double, Integer>ed = new  <Double, Integer> Edge();
       
        //ArrayList<Edge> ad = new <Edge> ArrayList();
        ArrayList<ArrayList<Edge>> adlist = new <ArrayList<Edge>> ArrayList();
        
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");        
        while(st.hasMoreTokens()){
			//nextToken()을 통해 띄어쓰기(" ")를 제거하고 남은 숫자를 스트링 형태에서 int로 형변환하여 arr에 저장. k++를 이용해 인덱스 증가
			nV =  Integer.parseInt(st.nextToken());
			nE =  Integer.parseInt(st.nextToken());
		}
        
        //노드의 수와 간선수를 입력받았으면 dist배열의 크기를 설정한다. 
    	dist = new double[nV];
    	for(int i = 0;i<nV;i++)
    	{
    		dist[i] = Double.MAX_VALUE;
    	}
    	
    	
    	/*
    	for ( int i = 0;i <nV;i++)
        {
        	System.out.println(dist[i]);
        }   테스트용 코드 */
    	
    	
    	PriorityQueue<double[]> pq = new PriorityQueue<>(nV, new Comparator<double[]>() { // 거리값, 정점번호에 대해 정렬
            @Override
            public int compare(double[] o1, double[] o2) {
                if(o1[0] - o2[0] > 0)   return 1;
                return -1;
            }
        });
    	

        for(int i = 0; i < nV; i++){
           // ad.add(new <Double, Integer> Edge()); //edge 초기화 (메모리 할당)
        	adlist.add(new <Edge> ArrayList());
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
    			
    			//ad.get(t1).set_Edge(t2, t3);
    	       // ad.get(t2).set_Edge(t1, t3);
    			Edge <Double, Integer>ed = new  <Double, Integer> Edge();
    			ed.set_Edge(t3, t2);
    			adlist.get(t1).add(ed);
    			
    		}
        	
        }
        
        
        dist[0] = 0; //시작하는 정점 0 의 과 0의 거리는 무한대이다. 
        pq.add(new double[]{0,0});// 큐에 먼저 0부터 넣게 된다. 
        while(!pq.isEmpty())
        {
        	double d = pq.peek()[0]; //큐의 내용을 삭제하지 않고 가장 위에 있는 값을 보게 된다. 여기서는 가중치 
        	double curr = pq.peek()[1]; //마찬가지로 가장 위에잇는 값의 정점 번ㄴ호
        	pq.poll(); //방문 노드는 큐에서 제외한다. 
        	if(dist[(int)curr] != d) continue; //루프에서 나오게되는 조건 현재 노드의 정점과 거리가 같지 않으면 진행
        	
        	for(int i = 0 ;i < adlist.get((int)curr).size();i++)
        	{
        		int next = (int)adlist.get((int)curr).get(i).v;
        		double wi = (double)adlist.get((int)curr).get(i).weight;
        		System.out.println("current v and weight is"+ next +" "+wi);
        		
        		if(dist[next] >dist[(int)curr] * wi)
        		{
        			dist[next] = dist[(int)curr] * wi;
        			pq.add(new double []{dist[next],next});
        			
        		}
        		
        	}
        }
        
        
        
        
        
        /*for (int  i = 0 ;i<adlist.size();i++)
        {
        	for(int j = 0;j<adlist.get(i).size();j++)
        	{
        		System.out.println(i+ "->" +adlist.get(i).get(j).v +" "+adlist.get(i).get(j).weight );
        	}
        	System.out.println("\n");
        	
        } 출력 테스트용 함수 */
        for ( int i = 0;i <nV;i++)
        {
        	System.out.println(i+ " " + dist[i]);
        }
    	
    	
    	
       
    }
}