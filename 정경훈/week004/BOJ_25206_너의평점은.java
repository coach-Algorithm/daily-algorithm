import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25206_너의평점은 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double credit = 0;
        double total = 0;
        for (int i=0;i<20;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String title = st.nextToken();
            double grade = Double.parseDouble(st.nextToken());
            String mark = st.nextToken();

            switch (mark){
                case "A+":
                    total += grade;
                    credit += grade * 4.5;
                    break;

                case "A0":
                    total += grade;
                    credit += grade * 4.0;
                    break;

                case "B+":
                    total += grade;
                    credit += grade * 3.5;
                    break;

                case "B0":
                    total += grade;
                    credit += grade * 3.0;
                    break;

                case "C+":
                    total += grade;
                    credit += grade * 2.5;
                    break;

                case "C0":
                    total += grade;
                    credit += grade * 2.0;
                    break;

                case "D+":
                    total += grade;
                    credit += grade * 1.5;
                    break;

                case "D0":
                    total += grade;
                    credit += grade;
                    break;

                case "F":
                    total += grade;
                    break;

                default:
                    break;
            }

        }
        System.out.println(credit / total);

    }
}
