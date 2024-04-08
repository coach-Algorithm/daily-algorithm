import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11399_ATM {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            int a = Integer.parseInt(st.nextToken());
            pq.add(a);

        }
        int sum = 0;
        int answer = 0;
        while (!pq.isEmpty()){
            int n = pq.poll();
            sum += n;
            answer += sum;
//            System.out.println(n + " " + answer);
        }

        System.out.println(answer);
    }
}
