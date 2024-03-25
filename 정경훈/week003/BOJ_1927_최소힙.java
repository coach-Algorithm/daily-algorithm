import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1927_최소힙 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0;i<N;i++){
            int input = Integer.parseInt(br.readLine());
            if (input == 0){
                if(pq.isEmpty()) {
                    sb.append(0);
                }else{
                    sb.append(pq.poll());
                }
                sb.append("\n");
            }else {
                pq.add(input);
            }

        }

        System.out.println(sb.toString());

    }
}
