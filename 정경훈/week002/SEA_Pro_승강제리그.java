import java.util.TreeSet;

class SEA_Pro_승강제리그 {

    static int n, m, l;
    static TreeSet<Player>[][] leagues;

    void init(int N, int L, int mAbility[]) {
        n = N;
        l = L;
        m = N/L;

        leagues = new TreeSet[l][2];

//        TreeSet<Player> players = new TreeSet<>((o1, o2) -> o1.ability == o2.ability ? o1.id - o2.id : o2.ability - o1.ability);
        for (int i=0;i<l;i++){
//            players.clear();
            leagues[i][0] = new TreeSet<>((o1, o2) -> o1.ability == o2.ability ? o1.id - o2.id : o2.ability - o1.ability);
            leagues[i][1] = new TreeSet<>((o1, o2) -> o1.ability == o2.ability ? o1.id - o2.id : o2.ability - o1.ability);

//            for (int j=0;j<m;j++){
//                players.add(new Player(i*m+j, mAbility[i*m+j]));
//            }

            for (int j=0;j<m;j++){
//                Player p = players.pollFirst();
                if(j < m/2){
                    leagues[i][0].add(new Player(i*m+j, mAbility[i*m+j]));
                }else{
                    leagues[i][1].add(new Player(i*m+j, mAbility[i*m+j]));
                }
            }
        }

        check();
    }

    int move() {
        int sum = 0;
        for (int i=1;i<l;i++){
            Player p1 = leagues[i-1][1].pollLast(); // 상위 리그의 최하위
            Player p2 = leagues[i][0].pollFirst(); // 하위 리그의 최상위
            sum += p1.id + p2.id;

            leagues[i-1][1].add(p2);
            leagues[i][0].add(p1);
        }
        check();
//        System.out.println(sum);
        return sum;
    }

    int trade() {
        int sum = 0;
        for (int i=1;i<l;i++){
            Player p1 = leagues[i-1][1].pollFirst(); // 상위 리그의 중간
            Player p2 = leagues[i][0].pollFirst(); // 하위 리그의 최상위

            sum += p1.id + p2.id;

            leagues[i-1][1].add(p2);
            leagues[i][0].add(p1);
        }
        check();
//        System.out.println(sum);
        return sum;
    }

    static void check(){
        for (int i=0;i<l;i++){

            while (true){
                Player p1 = leagues[i][0].pollLast();
                Player p2 = leagues[i][1].pollFirst();

                if(p1.ability == p2.ability){
                    if(p2.id > p1.id){ // 비교했을 때 그대로면 while문 탈출
                        leagues[i][0].add(p1);
                        leagues[i][1].add(p2);
                        break;
                    }else{
                        leagues[i][1].add(p1);
                        leagues[i][0].add(p2);
                    }
                }else{
                    if(p1.ability > p2.ability){
                        leagues[i][0].add(p1);
                        leagues[i][1].add(p2);
                        break;
                    }else{
                        leagues[i][1].add(p1);
                        leagues[i][0].add(p2);
                    }
                }
            }

        }
    }

    static class Player{
        int id;
        int ability;

        public Player(int id,  int ability){
            this.id = id;
            this.ability = ability;
        }
    }

}