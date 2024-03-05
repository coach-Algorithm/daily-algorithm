import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Queue;

public class BOJ_1874_스택수열 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        boolean ok = true;
        Deque<Integer> dq = new ArrayDeque<>();

        int num = 1;

        for (int i=0;i<n;i++){
            // 찾아야 하는 수
            int in = Integer.parseInt(br.readLine());

            while(in >= num){
                dq.addFirst(num);
                num++;
                sb.append("+" + "\n");
            }
            // 탈출시 q에는 in의 값만큼 들어감.
            int out = dq.pollFirst();
            sb.append("-" + "\n");
            if(in != out){
                ok = false;
                break;
            }
        }

        if(ok){
            System.out.println(sb.toString());
        }else{
            System.out.println("NO");
        }

    }
}
