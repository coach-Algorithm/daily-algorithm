import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019_DSLR {
    static int T;
    static int A,B;
    static String[] command;
    static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            command = new String[10000];
            visited = new boolean[10000];
            q.clear();

            q.add(A);
            visited[A] = true;
            command[A] = "";

            while (!q.isEmpty()){
                int now = q.poll();

                if(now == B){
                    sb.append(command[now]);
                    sb.append("\n");
                    break;
                }

                int D = (2*now)%10000;
                int S = (now == 0) ? 9999 : now-1;
                int L = (now % 1000) * 10 + now/1000;
                int R = (now % 10) * 1000 + now/10;

                if(!visited[D]){
                    q.add(D);
                    visited[D] = true;
                    command[D] = command[now] + "D";
                }
                if(!visited[S]){
                    q.add(S);
                    visited[S] = true;
                    command[S] = command[now] + "S";
                }
                if(!visited[L]){
                    q.add(L);
                    visited[L] = true;
                    command[L] = command[now] + "L";
                }
                if(!visited[R]){
                    q.add(R);
                    visited[R] = true;
                    command[R] = command[now] + "R";
                }
            }

        }
        System.out.println(sb.toString());
    }


}
