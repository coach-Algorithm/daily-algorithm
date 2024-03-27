import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460_구슬탈출2 {
    static int N, M;
    static int[][] point;
    static char[][] map;

    static boolean[][][][] visited;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M][N][M];
        // 0: blue 1: red 2: hole
        point = new int[3][2];

        map = new char[N][M];

        for (int i=0;i<N;i++){
            String input = br.readLine();
            for (int j=0;j<M;j++){
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'B'){
                    point[0][0] = i;
                    point[0][1] = j;
                    map[i][j] = '.';
                }
                if (map[i][j] == 'R'){
                    point[1][0] = i;
                    point[1][1] = j;
                    map[i][j] = '.';
                }
                if (map[i][j] == 'O'){
                    point[2][0] = i;
                    point[2][1] = j;
                }
            }
        }

        System.out.println(move());


    }

    static int move(){
        int answer = -1;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(point[0], point[1], 0));

        visited[point[0][0]][point[0][1]][point[1][0]][point[1][1]] = true;
        while (!q.isEmpty()){
            Node node = q.poll();

            if(node.cnt > 9) {
                return -1;
            }

            int[] blue = {node.by, node.bx};
            int[] red = {node.ry, node.rx};

            for (int i=0;i<4;i++){
                int[] newBlue = {blue[0], blue[1]};
                int[] newRed = {red[0], red[1]};

                boolean isRedHole = false;
                boolean isBlueHole = false;

                while (map[newRed[0] + dy[i]][newRed[1] + dx[i]] != '#'){
                    newRed[0] += dy[i];
                    newRed[1] += dx[i];
                    // 홀에 드갔어?
                    if(newRed[0] == point[2][0] && newRed[1] == point[2][1]){
                        isRedHole = true;
                        break;
                    }
                }

                while (map[newBlue[0] + dy[i]][newBlue[1] + dx[i]] != '#'){
                    newBlue[0] += dy[i];
                    newBlue[1] += dx[i];
                    // 홀에 드갔어?
                    if(newBlue[0] == point[2][0] && newBlue[1] == point[2][1]){
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) continue;

                // 파란색은 안빠지고 빨강색만 빠진 경우
                if (isRedHole && !isBlueHole){
                    answer = node.cnt + 1;
                    return answer;
                }

                if(newRed[0] == newBlue[0] && newRed[1] == newBlue[1]){
                    switch (i){
                        case 0: // 왼쪽으로 기울기 x값이 큰게 뒤로
                            if(blue[1] < red[1]) newRed[1] -= dx[i];
                            else newBlue[1] -= dx[i];
                            break;

                        case 1: // 오른쪽으로 기울기 x값이 작게 뒤로
                            if(blue[1] > red[1]) newRed[1] -= dx[i];
                            else newBlue[1] -= dx[i];
                            break;

                        case 2: // 위쪽으로 기울기 y값이 큰게 뒤로
                            if(blue[0] < red[0]) newRed[0] -= dy[i];
                            else newBlue[0] -= dy[i];
                            break;

                        case 3: // 아래쪽으로 기울기 y값이 작은게 뒤로
                            if(blue[0] > red[0]) newRed[0] -= dy[i];
                            else newBlue[0] -= dy[i];
                            break;
                    }
                }

                if(!visited[newBlue[0]][newBlue[1]][newRed[0]][newRed[1]]){
                    visited[newBlue[0]][newBlue[1]][newRed[0]][newRed[1]] = true;
                    q.add(new Node(newBlue, newRed, node.cnt + 1));
                }
            }

        }

        return answer;
    }

    static class Node{
        int by, bx;
        int ry, rx;
        int cnt;

        public Node(int[] blue, int[] red, int cnt){
            this.by = blue[0];
            this.bx = blue[1];
            this.ry = red[0];
            this.rx = red[1];
            this.cnt = cnt;
        }
    }
}

