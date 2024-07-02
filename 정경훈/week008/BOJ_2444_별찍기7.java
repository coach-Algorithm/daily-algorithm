import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2444_별찍기7 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i=N-1;i>=0;i--){
            sb.append(" ".repeat(i));
            int star = N-i;
            star = star * 2 - 1;
            sb.append("*".repeat(Math.max(0, star)));
            sb.append("\n");
        }
        for (int i=1;i<N;i++){
            sb.append(" ".repeat(i));
            int star = N-i;
            star = star * 2 - 1;
            sb.append("*".repeat(Math.max(0, star)));
            sb.append("\n");
        }


        System.out.print(sb.toString());

    }
}
