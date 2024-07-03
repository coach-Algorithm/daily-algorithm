import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
    static int N, ans;
    static int[][] team;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ans = Integer.MAX_VALUE;
        visited = new boolean[N];
        team = new int[N][N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++){
                team[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0,N/2);
        System.out.print(ans);

    }

    static void comb(int idx, int r){
        if(r == 0){
            int start = 0;
            int link = 0;
            for (int i=0;i<N;i++){
                for (int j=0;j<N;j++){
                    if(visited[i]){
                        if (visited[j]){
                            start += team[i][j];
                        }
                    }else{
                        if(!visited[j]){
                            link += team[i][j];
                        }
                    }
                }
            }
            ans = Math.min(ans, Math.abs(start - link));
            return;
        }
        if(idx == N){
            return;
        }

        visited[idx] = true;
        comb(idx+1, r-1);
        visited[idx] = false;
        comb(idx+1, r);
    }
}
