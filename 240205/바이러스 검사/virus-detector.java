import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                
        Long rest = Long.parseLong(br.readLine());
        String[] customerArray = br.readLine().split(" ");
        String[] validationArray = br.readLine().split(" ");
        int answer = 0;

        for(String customer : customerArray) {
            if(Long.parseLong(customer) < Long.parseLong(validationArray[0]) || Long.parseLong(customer) == Long.parseLong(validationArray[0])) {
                answer++;
            }else {
                long diff = (Long.parseLong(customer) - Long.parseLong(validationArray[0]));
                if(diff == Long.parseLong(validationArray[1])) {
                    // System.out.println(answer + 2);
                    answer = answer + 2;
                }else {
                    // System.out.println((diff / Long.parseLong(validationArray[1])) + 2);
                    answer = answer + (int) (diff / Long.parseLong(validationArray[1])) + 2;
                }
                // long oneTeam = ((Long.parseLong(customer) - Long.parseLong(validationArray[0])) / Long.parseLong(validationArray[1])) + 2;
            }
            // System.out.println(answer);
        }

        System.out.println(answer);
    
    }
}

// n개 식당
// 검사팀장 : 1개 식당 1명 검사 가능, 팀장 무조건 필요
// 검사팀원 : 1개 식당 여러명 검사 가능

// 5
// 999999 999999 999999 999999 999999
// 111111 5

// 3
// 15
// 7 14


// 999999 -> 111111 --> 888888

// 최대값 -> 999999
// 999999 - LDR = 888888
// 888888 / MBR = 177777.6 (오름차순) --> 177778