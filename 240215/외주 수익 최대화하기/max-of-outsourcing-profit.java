import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Main {

    public void solution() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int n = Integer.parseInt(br.readLine()); // 일자의 수

            int[] t = new int[n]; // 기한
            int[] p = new int[n]; // 수익
            int[] dp = new int[n + 1]; // dp[n] 까지의 최대 수익을 저장하는 배열

            for (int i = 0; i < n; i++) {
                String[] line = br.readLine().split(" ");
                t[i] = Integer.parseInt(line[0]);
                p[i] = Integer.parseInt(line[1]);
            }

            for (int i = n; i > 0; i--) {
                if(i + t[i - 1] > n + 2) {
                    // 기한을 벗어났을 때
                    dp[i - 1] = dp[i + 1];
                }else{
                    // 기한을 벗어나지 않았을 때
                    dp[i - 1] = Math.max(dp[i], p[i - 1] + dp[i - 1 + t[i - 1]]);
                }
            }

            System.out.println(dp[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        main.solution();
    }
}