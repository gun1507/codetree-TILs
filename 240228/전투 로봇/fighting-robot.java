import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static int N;
    static int space[][];
    static int startPoint[];
    static int level = 2;
    static int dx[];
    static int dy[];
    static int answer = 0;
    static int killed = 0;
    static Map<Integer, LinkedList<int[]>> monster = new HashMap<>();

    public static void main(String[] args) {
        // System.setIn(new FileInputStream("C:\\input.txt"));
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        space = new int[N][N];

        dx = new int[]{-1, 0, 1, 0};
        dy = new int[]{0, -1, 0, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                space[i][j] = scanner.nextInt();
                if(space[i][j] == 9) {
                    startPoint = new int[]{i, j};
                }else if(space[i][j] != 0 && space[i][j] != 9) {
                    if(monster.get(space[i][j]) == null) {
                        LinkedList first = new LinkedList<int[]>();
                        first.add(new int[]{i, j});
                        monster.put(space[i][j], first);
                    }else{
                        monster.get(space[i][j]).add(new int[]{i, j});
                    }

                }
            }
        }

        // System.out.println("bfs 시작");

        Arrays.sort(monster.keySet().toArray());
        monster.forEach((key, value) -> {
            // System.out.println("key : " + key);
            if(key < level) {
                for(int[] endPoint : value) {
                    answer = answer + bfs(startPoint, endPoint);
                    killed++;
                    if(killed == level) {
                        level++; // 레벨업
                        killed = 0;
                    }
                    startPoint = endPoint;
                }
            }
        });
        System.out.println(answer);
    }

    private static int bfs(int[] startPoint, int[] endPoint) {
        boolean visit[][] = new boolean[N][N];
        visit[startPoint[0]][startPoint[1]] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startPoint, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            if(node.x == endPoint[0] && node.y == endPoint[1]) {
                return node.dist;
            }

            for (int i = 0; i < 4; i++) {
                if(node.x + dx[i] >= N || node.y + dy[i] >= N || node.x + dx[i] < 0 || node.y + dy[i] < 0) continue; // 영역 밖일때
                if((!visit[node.x + dx[i]][node.y + dy[i]]) && // 이미 방문한 곳일때
                    (space[node.x + dx[i]][node.y + dy[i]] < level) // 현재 레벨보다 큰 몬스터가 있을때
                ) {
                    visit[node.x + dx[i]][node.y + dy[i]] = true;
                    if(node.x + dx[i] == endPoint[0] && node.y + dy[i] == endPoint[1]) {
                        return node.dist + 1;
                    }
                    q.offer(new Node(new int[]{node.x + dx[i], node.y + dy[i]}, node.dist + 1));
                    // System.out.println("[" + (node.x + dx[i]) + ", " + (node.y + dy[i]) + "]");
                }
            }
        }
        return -1;
    }

    private static class Node {
        int x;
        int y;
        int dist;

        public Node(int[] point, int dist) {
            this.x = point[0];
            this.y = point[1];
            this.dist = dist;
        }
    }
}