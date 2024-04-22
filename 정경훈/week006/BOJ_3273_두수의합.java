import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273_두수의합 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());

        Arrays.sort(num);

        int left = 0;
        int right = n-1;
        int ans = 0;

        while (left < right){
            int sum = num[left] + num[right];
            if(sum == x){
                ans++;
                left++;
                right--;
            }else if(sum > x){
                right--;
            }else {
                left++;
            }
        }
        System.out.print(ans);
    }
}
