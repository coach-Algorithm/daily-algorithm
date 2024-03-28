import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N+1];
        int[] pay = new int[N+1];
        int[] dp = new int[N+2];

        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=1;i<=N;i++){
            if(i + time[i] <= N+1) {
//                System.out.println("dp[" +i + "] : " + dp[i]);
                dp[i+time[i]] = Math.max(dp[i+time[i]], dp[i] + pay[i]);
            }
            dp[i+1] = Math.max(dp[i], dp[i+1]);


        }

        System.out.print(dp[N+1]);
    }
}
