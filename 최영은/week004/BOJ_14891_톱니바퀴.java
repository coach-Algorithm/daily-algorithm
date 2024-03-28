import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14891_톱니바퀴 {
    private static int C = 4;
    private static int S = 8;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[C+1];
        for(int i = 1 ; i <= C ; i++) {
            int flag = 0;
            String str = br.readLine();
            for(int j = 0 ; j < S ; j++) {
                flag = flag << 1;
                if(str.charAt(j) == '1') flag |= 1;
            }
            arr[i] = flag;
        }

        int K = Integer.parseInt(br.readLine());
        for(int k = 0 ; k < K ; k++) {
            String[] in = br.readLine().split(" ");
            int wheel = Integer.parseInt(in[0]);
            boolean dir = in[1].charAt(0)=='1'; //시계 = t, 반시계 = f

            turn(wheel, dir, 1);

            // 2번 회전한거 돌리기

            int flag = arr[wheel];
            if(!dir) {
                int f = flag & 1;
                flag = (flag >> 1 ) | (f << 7);
            }
            else {
                int f = (flag & (1<<7))>>7;
                flag = (flag << 1) | f;
            }
            arr[wheel] = flag;
            turn(wheel, dir, -1);

        }

        // 점수 계산
        int score = 0;
        for(int i = 1 ; i <= C ; i++) {
//            int t = arr[i];
//            for(int j = 7 ; j >= 0 ; j--) {
//                int tt = t >> j & 1;
//                System.out.print(tt);
//            }
//            System.out.println();
          
            if(((arr[i] >> 7)&1) > 0) {
                score += (1 << (i-1));
            }
        }
        System.out.println(score);
    }

    private static void turn(int wheel, boolean dir, int next) {
//        System.out.println(wheel+"번 휠 "+(dir?"":"반")+"시계방향으로 회전");

        if((next == 1 && wheel < C) || (next == -1) && wheel > 1) {

            // 맞닿은 극이 같은지 다른지
//            System.out.println("   "+wheel+"과"+(wheel+next)+"비교 : " +((arr[wheel] >> 5)&1)+", "+((arr[wheel+next] >>1)&1)+" / "+((arr[wheel] >> 1)&1)+", "+((arr[wheel+next] >>5)&1));
            if((next == 1 && ((arr[wheel] >> 5)&1) !=((arr[wheel+next] >>1)&1)) ||
                    (next == -1 && ((arr[wheel] >> 1)&1) !=((arr[wheel+next] >>5)&1))) {
                turn(wheel+next, !dir, next);
            }
        }

        // 회전
        int flag = arr[wheel];
        if(dir) {
            int f = flag & 1;
            flag = (flag >> 1 ) | (f << 7);
        }
        else {
            int f = (flag & (1<<7))>>7;
            flag = (flag << 1) | f;
        }
        arr[wheel] = flag;
    }
}
