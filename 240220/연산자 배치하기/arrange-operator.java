import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    private static int[] calc;
    private static int[] number;
    private static int N;
    private static int MAX = Integer.MIN_VALUE;
    private static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) {

        try {
            // System.setIn(new FileInputStream("C:\\input.txt"));
            Scanner scanner = new Scanner(System.in);
            N = scanner.nextInt(); // 정수 갯수
            number = new int[N]; // N개의 숫자 2~11
            calc = new int[3]; // 연산자(+,-,*)

            for(int i = 0; i < N; i++) {
                number[i] = scanner.nextInt();
            }
            for(int i = 0; i < 3; i++) {
                calc[i] = scanner.nextInt();
            }

            dfs(number[0], 1);

            System.out.println(MIN + " " + MAX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int num, int idx) {
        if(idx == N) {
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if(calc[i] > 0) {
                calc[i]--;

                switch (i) {
                    // 덧셈
                    case 0 :
                        dfs(num + number[idx], idx + 1);
                        break;
                    // 뺄셈
                    case 1 :
                        dfs(num - number[idx], idx + 1);
                        break;
                    // 곱셈
                    case 2 :
                        dfs(num * number[idx], idx + 1);
                        break;
                }
                calc[i]++;
            }
        }
    }

}