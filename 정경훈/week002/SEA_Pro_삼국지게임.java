import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SEA_Pro_삼국지게임 {
    static int n;
    static int[][] map;
    static int[][] allyMap;
    static HashMap<String, Integer> monarches;
    static int[] parents;
    static int[][] enemy;

    static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dx = {0, 0, -1, 1, 1, -1, 1, -1};

    void init(int N, int mSoldier[][], char mMonarch[][][])
    {
        n = N;
        map = new int[N][N];
        allyMap = new int[N][N];
        monarches = new HashMap<>();

        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                map[i][j] = mSoldier[i][j];
                allyMap[i][j] = i*n + j;
                monarches.put(String.valueOf(mMonarch[i][j]), i*N+j);
            }
        }

        // 유니온 파인드 활용
        parents = new int[N*N];

        for(int i=0;i<N*N;i++){
            parents[i] = i;
        }

        // 적대 관계 표시용 배열 0 : 동맹이거나 아무 관계 x / 1 : 적대 관계
        enemy = new int[N*N][N*N];

    }
    void destroy()
    {

    }
    int ally(char mMonarchA[], char mMonarchB[]) {
        String A = String.valueOf(mMonarchA);
        String B = String.valueOf(mMonarchB);

        // 동맹 관계 확인용 인덱스 판별
        int idxA = monarches.get(A);
        int idxB = monarches.get(B);

        int relA = find(idxA);
        int relB = find(idxB);

        if(relA == relB) {
//            System.out.println(-1);
            return -1;
        }

        if(enemy[relA][relB] == 1) {
//            System.out.println(-2);
            return -2;
        }

        // 아무 관계가 아니라면 동맹
        union(relA, relB);

        // 적군 동기화
        if(relA < relB){
            syncEnemy(relB, relA);
        }else{
            syncEnemy(relA, relB);
        }
//        System.out.println(1);
        return 1;
    }

    // 동맹시 같은 적으로 매핑
    static void syncEnemy(int x, int y){ // x가 큰 값 y가 작은 값
        for (int i=0;i<n*n;i++){
            // 적대 관계면
            if(enemy[x][i] == 1){
                enemy[y][i] = 1;
                enemy[i][y] = 1;
                enemy[x][i] = 0;
                enemy[i][x] = 0;
            }
        }
    }
    int attack(char mMonarchA[], char mMonarchB[], char mGeneral[]) {
        String A = String.valueOf(mMonarchA);
        String B = String.valueOf(mMonarchB);
        String G = String.valueOf(mGeneral);

        // 동맹 관계 확인용 인덱스 판별
        int idxA = monarches.get(A);
        int idxB = monarches.get(B);

        int relA = find(idxA);
        int relB = find(idxB);

        if(relA == relB) {
//            System.out.println(-1);
            return -1;
        }

        // B 군주의 주변 탐색
        int y = idxB/n;
        int x = idxB%n;

        // B를 공격할 A의 병력
        int sumA = 0;
        // B를 보호할 B동맹의 병력
        int sumB = map[y][x];
        boolean isAttack = false;
        List<Integer> listB = new ArrayList<>();
        for(int i=0;i<8;i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || nx < 0 || ny >= n || nx >= n) continue;

            int r = ny * n + nx;
            int rel = find(r);
            // 주변이 A와 동맹 관계라면 공격 병력 더하기
            if(rel == relA){
                isAttack = true;
                sumA += map[ny][nx]/2;
                map[ny][nx] -= map[ny][nx]/2;
            }
            if(rel == relB){
                listB.add(r);

            }
        }

        // 공격 병력이 없다 == 인접해 있지 않다.
        if(!isAttack) {
//            System.out.println(-2);
            return -2;
        }

        // 공격 시작
        // 방어 병력 빼기
        for(int i=0;i<listB.size();i++){
            int node = listB.get(i);
            int ny = node/n;
            int nx = node%n;
            sumB += map[ny][nx]/2;
            map[ny][nx] -= map[ny][nx]/2;
        }

        // A동맹과 B동맹은 적대
        enemy[relA][relB] = 1;
        enemy[relB][relA] = 1;

        // 서로의 병력 비교
        // 공격을 실패한 경우
        if(sumB >= sumA){

            map[y][x] = sumB - sumA;
//            System.out.println(0);
            return 0;
        }

        // 공격을 성공한 경우
        // 군주 교체가 필요함.
        // 부모 초기화
        if(parents[idxB] == idxB){
            int newParents = -1;
            for(int i=0;i<n*n;i++){
                if(i == idxB) continue;
                if(parents[i] == idxB) {
                    if (newParents == -1) {
                        newParents = i;
                    }
                    parents[i] = newParents;
                }
            }
            // 혼자만 동맹이었을 경우
            if(newParents == -1){
                for (int i=0;i<n*n;i++){
                    enemy[idxB][i] = 0;
                    enemy[i][idxB] = 0;
                }
            }else{
                syncEnemy(idxB, newParents);
                enemy[relA][newParents] = 1;
                enemy[newParents][relA] = 1;
                enemy[relA][idxB] = 0;

            }

        }else{ // 최상위가 아니면 어짜피 비어있음.
            for(int i=0;i<n*n;i++){
                if(parents[i] == idxB){
                    parents[i] = parents[idxB];
                }
            }
        }
        parents[idxB] = idxB;
        union(idxB, relA);

        if(relA > idxB){
            syncEnemy(relA, idxB);
        }

        monarches.remove(B);
        monarches.put(G, idxB);


        map[y][x] = sumA - sumB;
//        System.out.println(1);
        return 1;
    }
    int recruit(char mMonarch[], int mNum, int mOption)
    {
        String M = String.valueOf(mMonarch);

        int idxM = monarches.get(M);

        if(mOption == 0){
////            System.out.println("원래는 이거다" + map[idxM/n][idxM%n]);
            map[idxM/n][idxM%n] += mNum;
////            System.out.println("더한다" + map[idxM/n][idxM%n]);
//            System.out.println(map[idxM/n][idxM%n]);
            return map[idxM/n][idxM%n];
        }else{
            int sum = 0;
            int relM = find(idxM);
            for (int i=0;i<n*n;i++){
                if(find(i) == relM){
////                    System.out.println("원래 이랬어" + map[i/n][i%n]);
                    map[i/n][i%n] += mNum;
////                    System.out.println("더한다" + map[i/n][i%n]);
                    sum += map[i/n][i%n];
                }
            }
//            System.out.println(sum);
            return sum;
        }
    }

    static boolean union(int x, int y){
        x = find(x); // x의 부모 노드 찾기
        y = find(y); // y의 부모 노드 찾기

        // 이미 같은 그래프에 속해 있을 때 false 반환
        if(x == y) return false;

        if(x <= y) parents[y] = x;
        else parents[x] = y;
        return true;
    }

    // 작은 값으로 통일되도록
    static int find(int x){
        if(parents[x] == x) return x;
        return find(parents[x]);
    }

}
