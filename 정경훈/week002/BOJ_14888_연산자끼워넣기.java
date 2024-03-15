import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기 {

    //plus, minus, multi, divide
    static int[] oper;
    static int N, max, min;
    static int[] num;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        oper = new int[4];
        for (int i=0;i<4;i++){
            oper[i] = Integer.parseInt(st.nextToken());
        }
        int index = 1;
        int rst = num[0];

        calc(index, rst);

        System.out.println(max + "\n" + min);
    }

    static void calc(int index, int rst){
        if(index == N){
            // max, min 측정
            if(max < rst) max = rst;
            if(min > rst) min = rst;
            return;
        }
        // 더하기 빼기 곱하기 나누기 각각
        for (int i = 0;i<4;i++){
            if(oper[i] != 0){
                switch (i){
                    case 0: // 더하기
                        oper[i]--;
                        calc(index+1, rst + num[index]);
                        oper[i]++;
                        break;

                    case 1:
                        oper[i]--;
                        calc(index+1, rst - num[index]);
                        oper[i]++;
                        break;

                    case 2:
                        oper[i]--;
                        calc(index+1, rst * num[index]);
                        oper[i]++;
                        break;

                    case 3:
                        oper[i]--;
                        calc(index+1, rst / num[index]);
                        oper[i]++;
                        break;
                }
            }
        }

    }
}
