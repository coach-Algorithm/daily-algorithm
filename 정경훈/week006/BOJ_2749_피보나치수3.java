import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2749_피보나치수3 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 피사노 주기의 개념
        // 피보나치 수를 K로 나눈 나머지는 항상 주기를 가지게 된다.
        // 주기의 길이가 P 이면, N번째 피보나치 수를 M으로 나눈 나머지는 N%P번째 피보나치 수를 M을 나눈 나머지와 같다.
        // M = 10^k 일 때, k>2 라면, 주기는 항상 15 X 10^(k-1)이다.
        // 여기서는 M = 10^6이기 때문에 주기는 15 X 10^5 = 1500000이 된다.
        int p = 1_500_000;
        long n = Long.parseLong(br.readLine()) % p;
        long[] fibo = new long[(int)n + 1];
        fibo[0] = 0;
        fibo[1] = 1;

        for (int i=2;i<=n;i++){
            fibo[i] = (fibo[i-1] + fibo[i-2]) % 1_000_000;
        }

        System.out.print(fibo[(int)n]);
    }
}
