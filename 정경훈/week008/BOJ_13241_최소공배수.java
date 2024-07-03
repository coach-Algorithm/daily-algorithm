import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13241_최소공배수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        // A*B / GCD(최소공배수) = LCM
        long ans = 0;
        if(A > B){
            ans = A*B/(GCD(A,B));
        }else if(A < B){
            ans = A*B/(GCD(B,A));
        }else{
            ans = A;
        }

        System.out.println(ans);

    }
    // x가 y보다 큰 경우로 설정
    static long GCD(long x, long y){
        while(y != 0){
            long r = x%y;
            x = y;
            y = r;
        }
        return x;
    }
}
