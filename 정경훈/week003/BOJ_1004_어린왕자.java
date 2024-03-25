import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1004_어린왕자 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sum = 0;

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            int n = Integer.parseInt(br.readLine());

            for(int j=0;j<n;j++){
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                int isContain = 0;

                // 1. 출발점과 점사이 거리 구하기
                double d1 = Math.pow(cx-sx, 2) + Math.pow(cy-sy, 2);
                if(d1 < Math.pow(r,2)){
                    isContain++;
                }

                // 2. 출발점과 점사이 거리 구하기
                double d2 = Math.pow(cx-ex, 2) + Math.pow(cy-ey, 2);
                if(d2 < Math.pow(r,2)){
                    isContain++;
                }

                if(isContain == 1) sum++;

            }

            System.out.println(sum);
        }
    }
}
