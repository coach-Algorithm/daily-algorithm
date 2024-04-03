import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12100_2048easy {
    static int N, max;
    static List<int[][]> map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = 0;
        // 횟수
        map = new ArrayList<>();
        int[][] initMap = new int[N+2][N+2];

        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++){
                int a = Integer.parseInt(st.nextToken());
                if(max < a) max = a;
                initMap[i][j] = a;
            }
        }

        map.add(initMap);

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));
        int ID = 0;
        while (!q.isEmpty()){
            Node node = q.poll();

            if (node.cnt >= 5){
                continue;
            }
            int id = node.id;
//            System.out.println("id : " + id + "시작");
            for (int i=1;i<5;i++){
                switch (i){
                    case 1:
                        up(id);
                        break;
                    case 2:
                        down(id);
                        break;
                    case 3:
                        left(id);
                        break;
                    case 4:
                        right(id);
                        break;
                }
//                System.out.println("횟수 : " + (node.cnt+1) + " id : " + (map.size()-1));
                q.add(new Node(node.cnt+1, map.size()-1));
            }

        }

        System.out.print(max);


    }

    static void up(int id){

        int[][] preMap = map.get(id);

        int[][] newMap = new int[N+2][N+2];
        boolean[][] merged = new boolean[N+2][N+2];
        for (int i=1;i<=N;i++){
            // 한 라인 : 세로
            // 채워질 때마다 1씩 증가
            int idx = 1;
            for (int j=1;j<=N;j++){
                if(preMap[j][i] != 0){
                    // 빈칸이 아닌 경우 올릴 수 있을 때 까지 올리기
                    newMap[idx][i] = preMap[j][i];
                    if(merged[idx-1][i]) {
                        idx++;
                        continue;
                    }
                    if(newMap[idx-1][i] == newMap[idx][i]){
                        newMap[idx-1][i] *= 2;
                        merged[idx-1][i] = true;
                        if (max < newMap[idx-1][i]) {
                            max = newMap[idx-1][i];
                        }
                        newMap[idx][i] = 0;
                    }else{ // 다른 값인 경우 idx++
                        idx++;
                    }
                }
            }
        }

        map.add(newMap);
    }

    static void down(int id){
        int[][] preMap = map.get(id);

        int[][] newMap = new int[N+2][N+2];
        boolean[][] merged = new boolean[N+2][N+2];
        for (int i=1;i<=N;i++){
            // 한 라인 : 세로
            // 채워질 때마다 1씩 증가
            int idx = N;
            for (int j=N;j>=1;j--){
                if(preMap[j][i] != 0){
                    // 빈칸이 아닌 경우 올릴 수 있을 때 까지 올리기
                    newMap[idx][i] = preMap[j][i];
                    if(merged[idx+1][i]) {
                        idx--;
                        continue;
                    }
                    if(newMap[idx+1][i] == newMap[idx][i]){
                        newMap[idx+1][i] *= 2;
                        merged[idx+1][i] = true;
                        if (max < newMap[idx+1][i]) {
                            max = newMap[idx+1][i];
                        }
                        newMap[idx][i] = 0;
                    }else{ // 다른 값인 경우 idx++
                        idx--;
                    }
                }
            }
        }

        map.add(newMap);

    }

    static void left(int id){
        int[][] preMap = map.get(id);

        int[][] newMap = new int[N+2][N+2];
        boolean[][] merged = new boolean[N+2][N+2];
        for (int i=1;i<=N;i++){
            // 한 라인 : 가로
            // 채워질 때마다 1씩 증가
            int idx = 1;
            for (int j=1;j<=N;j++){
                if(preMap[i][j] != 0){
                    // 빈칸이 아닌 경우 올릴 수 있을 때 까지 올리기
                    newMap[i][idx] = preMap[i][j];
                    if(merged[i][idx-1]) {
                        idx++;
                        continue;
                    }
                    if(newMap[i][idx-1] == newMap[i][idx]){
                        newMap[i][idx-1] *= 2;
                        merged[i][idx-1] = true;
                        if (max < newMap[i][idx-1]) {
                            max = newMap[i][idx-1];
                        }
                        newMap[i][idx] = 0;
                    }else{ // 다른 값인 경우 idx++
                        idx++;
                    }
                }
            }
        }

        map.add(newMap);
    }

    static void right(int id){
        int[][] preMap = map.get(id);

        int[][] newMap = new int[N+2][N+2];
        boolean[][] merged = new boolean[N+2][N+2];
        for (int i=1;i<=N;i++){
            // 한 라인 : 가로
            // 채워질 때마다 1씩 증가
            int idx = N;

            for (int j=N;j>=1;j--){
                if(preMap[i][j] != 0){
                    // 빈칸이 아닌 경우 올릴 수 있을 때 까지 올리기
                    newMap[i][idx] = preMap[i][j];
                    if(merged[i][idx+1]) {
                        idx--;
                        continue;
                    }
                    if(newMap[i][idx+1] == newMap[i][idx]){
                        newMap[i][idx+1] *= 2;
                        merged[i][idx+1] = true;
                        if (max < newMap[i][idx+1]) {
                            max = newMap[i][idx+1];
                        }
                        newMap[i][idx] = 0;
                    }else{ // 다른 값인 경우 idx++
                        idx--;
                    }
                }
            }
        }

        map.add(newMap);
    }

    static class Node{
        int cnt; // 이동 횟수
        int id; // 이전 작품이 담긴 id

        public Node(int cnt, int id){
            this.cnt = cnt;
            this.id = id;
        }

    }
}
