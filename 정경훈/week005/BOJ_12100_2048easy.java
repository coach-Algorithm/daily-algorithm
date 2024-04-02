import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12100_2048easy {
    static int N, max;
    static int[][][][] map;

    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = 0;
        // 횟수
        map = new int[6][5][N+2][N+2];

        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++){
                int a = Integer.parseInt(st.nextToken());
                if(max < a) max = a;
                map[0][0][i][j] = a;
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));

        while (!q.isEmpty()){
            Node node = q.poll();

            if (node.cnt >= 5){
                continue;
            }

            for (int i=1;i<5;i++){
                switch (i){
                    case 1:
                        up(node.cnt, node.preD);
                        break;
                    case 2:
                        down(node.cnt, node.preD);
                        break;
                    case 3:
                        left(node.cnt, node.preD);
                        break;
                    case 4:
                        right(node.cnt, node.preD);
                        break;
                }
                q.add(new Node(i, node.cnt+1));
            }

        }

        System.out.print(max);


    }

    static void up(int cnt, int preD){
        System.out.println("up 전 : ");
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                System.out.print(map[cnt][preD][i][j]);
            }
            System.out.println();
        }
        for (int i=1;i<=N;i++){
            // 한 라인 : 세로
            // 채워질 때마다 1씩 증가
            int idx = 1;
            for (int j=1;j<=N;j++){
                if(map[cnt][preD][j][i] != 0){
                    // 빈칸이 아닌 경우 올릴 수 있을 때 까지 올리기
                    map[cnt+1][1][idx][i] = map[cnt][preD][j][i];
                    if(map[cnt+1][1][idx-1][i] == map[cnt+1][1][idx][i]){
                        map[cnt+1][1][idx-1][i] *= 2;
                        if (max < map[cnt+1][1][idx-1][i]) {
                            max = map[cnt+1][1][idx-1][i];
                            System.out.println(cnt + " up " + max);
                        }
                        map[cnt+1][1][idx][i] = 0;
                    }else{ // 다른 값인 경우 idx++
                        idx++;
                    }
                }
            }
        }
        System.out.println("후 : ");
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                System.out.print(map[cnt+1][1][i][j]);
            }
            System.out.println();
        }
    }

    static void down(int cnt, int preD){
        System.out.println("down 전 : ");
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                System.out.print(map[cnt][preD][i][j]);
            }
            System.out.println();
        }
        for (int i=1;i<=N;i++){
            // 한 라인 : 세로
            // 채워질 때마다 1씩 증가
            int idx = N;
            for (int j=N;j>=1;j--){
                if(map[cnt][preD][j][i] == 0){
                    continue;
                } else {
                    // 빈칸이 아닌 경우 내릴 수 있을 때까지 내리기
                    map[cnt+1][2][idx][i] = map[cnt][preD][j][i];
                    if(map[cnt+1][2][idx+1][i] == map[cnt+1][2][idx][i]){
                        map[cnt+1][2][idx+1][i] *= 2;
                        if (max < map[cnt+1][2][idx+1][i]) {
                            max = map[cnt+1][2][idx+1][i];
                            System.out.println(cnt + " down " + max);
                        }
                        map[cnt+1][2][idx][i] = 0;
                    }else{ // 다른 값인 경우 idx--
                        idx--;
                    }
                }
            }
        }
        System.out.println("후 : ");
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                System.out.print(map[cnt+1][2][i][j]);
            }
            System.out.println();
        }
    }

    static void left(int cnt, int preD){
        System.out.println("left 전 : ");
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                System.out.print(map[cnt][preD][i][j]);
            }
            System.out.println();
        }
        for (int i=1;i<=N;i++){
            // 한 라인 : 가로
            // 채워질 때마다 1씩 증가
            int idx = 1;
            for (int j=1;j<=N;j++){
                if(map[cnt][preD][i][j] == 0){
                    continue;
                } else {
                    // 빈칸이 아닌 경우 올릴 수 있을 때 까지 올리기
                    map[cnt+1][3][i][idx] = map[cnt][preD][i][j];
                    if(map[cnt+1][3][i][idx-1] == map[cnt+1][3][i][idx]){
                        map[cnt+1][3][i][idx-1] *= 2;
                        if (max < map[cnt+1][3][i][idx-1]){
                            max = map[cnt+1][3][i][idx-1];
                            System.out.println(cnt + " left " + max);
                        }
                        map[cnt+1][3][i][idx] = 0;
                    }else{ // 다른 값인 경우 idx++
                        idx++;
                    }
                }
            }
        }
        System.out.println("후 : ");
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                System.out.print(map[cnt+1][3][i][j]);
            }
            System.out.println();
        }
    }

    static void right(int cnt, int preD){
        System.out.println("right 전 : ");
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                System.out.print(map[cnt][preD][i][j]);
            }
            System.out.println();
        }
        for (int i=1;i<=N;i++){
            // 한 라인 : 가로
            // 채워질 때마다 1씩 증가
            int idx = N;
            for (int j=N;j>=1;j--){
                if(map[cnt][preD][i][j] == 0){
                    continue;
                } else {
                    // 빈칸이 아닌 경우 올릴 수 있을 때 까지 올리기
                    map[cnt+1][4][i][idx] = map[cnt][preD][i][j];
                    if(map[cnt+1][4][i][idx+1] == map[cnt+1][4][i][idx]){
                        map[cnt+1][4][i][idx+1] *= 2;
                        if (max < map[cnt+1][4][i][idx+1]){
                            max = map[cnt+1][4][i][idx+1];
                            System.out.println(cnt + " right " + max);
                        }
                        map[cnt+1][4][i][idx] = 0;
                    }else{ // 다른 값인 경우 idx--
                        idx--;
                    }
                }
            }
        }
        System.out.println("후 : ");
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                System.out.print(map[cnt+1][4][i][j]);
            }
            System.out.println();
        }
    }

    static class Node{
        int preD; // 이전 이동 방향
        int cnt; // 이동 횟수

        public Node(int preD, int cnt){
            this.preD = preD;
            this.cnt = cnt;
        }

    }
}
