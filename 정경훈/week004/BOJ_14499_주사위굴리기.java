import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기 {
    static int N, M, x, y, k;
    static int[] dice;
    static int[][] map;
    // 1 동 2 서 3북 4 남
    static int[] dy = {0, 0, 0, -1, 1};
    static int[] dx = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        dice = new int[7];


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i=0;i<k;i++){
            int d = Integer.parseInt(st.nextToken());
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

            move(d);

            if(map[ny][nx] == 0){
                map[ny][nx] = dice[6];
            }else{
                dice[6] = map[ny][nx];
                map[ny][nx] = 0;
            }

            y = ny;
            x = nx;

            sb.append(dice[1]);
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

    // 움직이는 방향을 넣으면 다음 바텀이 어딘지 표시
    static void move(int d){

        switch (d){
            case 1: // 동
                roll_right();
                break;
            case 2: // 서
                roll_left();
                break;
            case 3: // 북
                roll_up();
                break;
            case 4: // 남
                roll_down();
                break;

        }
    }

    // 윗 값이 temp 담김
    // 위 <- 왼 <- 바닥 <- 오 <- 위
    static void roll_right(){
        int temp = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[6];
        dice[6] = dice[3];
        dice[3] = temp;
    }

    // 윗 값이 temp 담김
    // 위 <- 오 <- 바닥 <- 왼 <- 위
    static void roll_left(){
        int temp = dice[1];
        dice[1] = dice[3];
        dice[3] = dice[6];
        dice[6] = dice[4];
        dice[4] = temp;
    }

    // 윗 값이 temp 담김
    // 위 <- 앞 <- 바닥 <- 뒤 <- 위
    static void roll_up(){
        int temp = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[6];
        dice[6] = dice[2];
        dice[2] = temp;
    }

    // 윗 값이 temp 담김
    // 위 <- 뒤 <- 바닥 <- 앞 <- 위
    static void roll_down(){
        int temp = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[6];
        dice[6] = dice[5];
        dice[5] = temp;
    }
}
