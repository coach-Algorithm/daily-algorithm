import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_28278_스택2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());

        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());

            switch (order) {
                case 1:
                    int x = Integer.parseInt(st.nextToken());
                    q.addFirst(x);
                    break;

                case 2:
                    if (!q.isEmpty()) {
                        sb.append(q.pollFirst());
                        sb.append("\n");

                    } else {
                        sb.append(-1);
                        sb.append("\n");
                    }
                    break;

                case 3:
                    sb.append(q.size());
                    sb.append("\n");
                    break;

                case 4:
                    if (!q.isEmpty()) {
                        sb.append(0);
                        sb.append("\n");
                    } else {
                        sb.append(1);
                        sb.append("\n");
                    }
                    break;

                case 5:
                    if (!q.isEmpty()) {
                        sb.append(q.peekFirst());
                        sb.append("\n");
                    } else {
                        sb.append(-1);
                        sb.append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}
