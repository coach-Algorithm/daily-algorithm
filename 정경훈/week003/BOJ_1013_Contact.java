import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1013_Contact {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<T;i++){
            sb.append(isPattern(br.readLine())? "YES\n" : "NO\n");
        }

        System.out.println(sb.toString());
    }

    static boolean isPattern(String str){
        char state = 'A';
        for(char ch : str.toCharArray()){
            state = next(state,ch);
            if(state>'H') return false;
        }
        return (state=='A'||state=='E'||state=='F');
    }

    static char next(char state, char ch){
        switch (state){
            case 'A': // 1 로 시작하면 패턴 시작 B로 이동 0이면 다른 패턴
                return ch == '1' ? 'B' : 'H';

            case 'B': // 여기왔을 때 1이면 패턴 망가짐. 0이면 다음으로 이동
                return ch == '1' ? 'Z' : 'C';

            case 'C': // 여기 왔을 때 1이면 패턴 망가짐. 0이면 다음으로 이동
                return ch == '1' ? 'Z' : 'D';

            case 'D': // 여기왔을 때 1이면 다음패턴, 0이면 무한로직
                return ch == '1' ? 'E' : 'D';

            case 'E': // 여기왔을 때 1이면 1무한로직 패턴 0이면 아예 다른 패턴
                return ch == '1' ? 'F' : 'H';

            case 'F': // 여기 왔을 때 1이면 무한로직 0이면 10 일지 01 의 0일지 판단
                return ch == '1' ? 'F' : 'G';

            case 'G': // 1이면 01로 판단하여 위로 가고 0이면 100의 마지막 0이라 생각하여 D로
                return ch == '1' ? 'A' : 'D';

            case 'H': // 01 패턴 1로 넘어갔다면 하나의 패턴이 끝난 처리 처음으로 리턴
                return ch == '1' ? 'A' : 'Z';

//            case 'I':
//                return ch == '1' ? 'B' : 'H';
        }
        return 'Z';
    }
}
