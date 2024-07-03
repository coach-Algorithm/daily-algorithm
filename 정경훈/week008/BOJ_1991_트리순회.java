import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1991_트리순회 {
    static StringBuilder sb =  new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Node[] tree = new Node[N+1];

        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char mainNode = st.nextToken().charAt(0);
            char leftNode = st.nextToken().charAt(0);
            char rightNode = st.nextToken().charAt(0);

            // 1. 메인 노드가 생성이 안된 경우
            if(tree[mainNode-'A'] == null){
                tree[mainNode-'A'] = new Node(mainNode);
            }

            // 2. 왼쪽 자식이 있는 경우
            if(leftNode != '.') {
                if (tree[leftNode - 'A'] == null) tree[leftNode - 'A'] = new Node(leftNode);
                tree[mainNode - 'A'].left = tree[leftNode - 'A'];
            }

            // 2. 오른쪽 자식이 있는 경우
            if(rightNode != '.'){
                if(tree[rightNode-'A'] == null) tree[rightNode-'A'] = new Node(rightNode);
                tree[mainNode-'A'].right = tree[rightNode-'A'];
            }
        }

        preorder(tree[0]);
        sb.append('\n');
        inorder(tree[0]);
        sb.append('\n');
        postorder(tree[0]);

        System.out.print(sb.toString());
    }

    // 전위 순회
    static void preorder(Node node){
        if(node == null) return;
        sb.append(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    // 중위 순회
    static void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        sb.append(node.data);
        inorder(node.right);
    }

    // 후위 순회
    static void postorder(Node node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.data);
    }

    static class Node{
        char data;
        Node left;
        Node right;

        public Node(char data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
