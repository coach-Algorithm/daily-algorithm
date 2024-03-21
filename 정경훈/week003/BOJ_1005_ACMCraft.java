import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1005_ACMCraft {

    static int N, K;
    static int[] delay;
    static int[] dp;
    static int[] cntEdge;
    static int sum;
    static boolean[] isBuild;
    static List<List<Integer>> build;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            delay = new int[N+1];
            dp = new int[N+1];
            cntEdge = new int[N+1];
            sum = 0;
            isBuild = new boolean[N+1];

            build = new ArrayList<>();
            build.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++){
                delay[j] = Integer.parseInt(st.nextToken());
                build.add(new ArrayList<>());
            }
            for (int j=0;j<K;j++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                // b가 완성되기 위해 세워져야할 건물들
                build.get(a).add(b);
                cntEdge[b]++;
            }

            int victory = Integer.parseInt(br.readLine());

            buildCheck();
//            System.out.println("d : " + d);


            System.out.println(dp[victory]);


        }
    }

    static void buildCheck(){
        Queue<Integer> q = new ArrayDeque<>();

        for (int i=1;i<=N;i++){
            dp[i] = delay[i];

            if(cntEdge[i] == 0){
                q.add(i);
            }
        }

        while (!q.isEmpty()){
            int id = q.poll();

            for (int idx : build.get(id)){
                dp[idx] = Math.max(dp[idx], dp[id] + delay[idx]);
                cntEdge[idx]--;

                if(cntEdge[idx] == 0){
                    q.add(idx);
                }
            }
        }
    }

}
