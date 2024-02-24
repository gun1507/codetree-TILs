import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Integer> curWork = new ArrayList();
    static int N;
    static int P[][];
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws FileNotFoundException {
        // System.setIn(new FileInputStream("C:\\workspace\\input.txt"));

        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt(); // 일의 갯수
        P= new int[N][N]; // 일의 강도

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                P[i][j] = scanner.nextInt();
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int index) {
        if(curWork.size() == N/2) {
            // for(int x : curWork) {
            //     System.out.print(x + "| ");
            // }
            // System.out.println(" ");


            // 오전 일 강도
            int left = calc(curWork);
            // 오후 일 강도
            int right = calcExtra(curWork);
            // 오전 - 오후 일의 최소값
            answer = Math.min(answer, Math.abs(left - right));
            return;
        }
        if(index == N) return;
        curWork.add(index);
        dfs(index + 1);
        curWork.remove(curWork.size() - 1);
        dfs(index + 1);
    }

    static int calcExtra(List<Integer> curWork) {
        List<Integer> extraWork = new ArrayList();
        for (int i = 0; i < N; i++) {
            if(!curWork.contains(i)) {
                extraWork.add(i);
            }
        }
        return calc(extraWork);
    }

    static int calc(List<Integer> curWork) {
        int force = 0;

        for(int n : curWork) {
            for(int m : curWork) {
                if(n != m) {
                    force = force + P[n][m];
                }
            }
        }
        return force;
    }
    
}