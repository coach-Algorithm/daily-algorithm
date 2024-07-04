import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3003_킹퀸룩비숍나이트폰 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //
        int[] chess = new int[6];
        chess[0] = 1;
        chess[1] = 1;
        chess[2] = 2;
        chess[3] = 2;
        chess[4] = 2;
        chess[5] = 8;

        for (int i = 0; i < 6; i++) {
            int in = Integer.parseInt(st.nextToken());
            System.out.print((chess[i] - in) + " ");
        }
    }
}
