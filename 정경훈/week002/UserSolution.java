public class UserSolution {
    static int[][] map;
    static String[][] monarches;
    static String[] names;

    void init(int N, int mSoldier[][], char mMonarch[][][])
    {
        map = new int[N][N];

        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                map[i][j] = mSoldier[i][j];
                monarches[i][j] = String.valueOf(mMonarch[i][j]);
                names[i*N+j] = monarches[i][j];
            }
        }
    }
    void destroy()
    {

    }
    int ally(char mMonarchA[], char mMonarchB[])
    {
        return -4;
    }
    int attack(char mMonarchA[], char mMonarchB[], char mGeneral[])
    {
        return -3;
    }
    int recruit(char mMonarch[], int mNum, int mOption)
    {
        return -1;
    }
}
