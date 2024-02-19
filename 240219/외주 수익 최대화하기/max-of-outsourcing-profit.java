import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public void solution() {
        try {
            // System.setIn(new FileInputStream("C:\\외주수익.txt"));
            Scanner scanner = new Scanner(System.in);

            int n = scanner.nextInt(); // 일자의 수

            int[] t = new int[n + 1]; // 기한
            int[] p = new int[n + 1]; // 수익
            int[] dp = new int[n + 2]; // dp[n] 까지의 최대 수익을 저장하는 배열

            for (int i = 1; i <= n; i++) {
                t[i] = scanner.nextInt();
                p[i] = scanner.nextInt();
            }

            for (int i = n; i > 0; i--) {
                if(i + t[i] > n + 1) {
                    // 기한을 벗어났을 때
                    dp[i] = dp[i + 1];
                }else{
                    // 기한을 벗어나지 않았을 때
                    dp[i] = Math.max(dp[i + 1], p[i] + dp[i + t[i]]);
                }
            }

            System.out.println(dp[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        main.solution();
    }
}