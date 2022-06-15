import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class dungeon
{
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    int R = 5, C = 7, sr = 0, sc = 0, move_count = 0, nodes_left_in_layer = 1, nodes_in_next_layer = 0;
    String[][] m = new String[R][C];
    Boolean reachedEnd = false;
    Queue<Integer> rq = new LinkedList<>();
    Queue<Integer> cq = new LinkedList<>();

    private int solve(Boolean[][] visited)
    {
        rq.add(sr);
        cq.add(sc);
        visited[sr][sc] = true;

        while ((rq.size() > 0))
        {
            int r = rq.remove();
            int c = cq.remove();

            if (Objects.equals(m[r][c], "E"))
            {
                reachedEnd = true;
                break;
            }
            explore_neighbors(r, c, visited);
            nodes_left_in_layer--;
            if (nodes_left_in_layer == 0)
            {
                nodes_left_in_layer = nodes_in_next_layer;
                nodes_in_next_layer = 0;
                move_count++;
            }
        }
        if (reachedEnd)
        {
            return move_count;
        }
        return -1;
    }

    private void explore_neighbors(int r, int c, Boolean[][] visited)
    {
        int rr, cc;
        for (int i = 0; i < 4; i++)
        {
            rr = r + dr[i];
            cc = c + dc[i];

            if (rr < 0 || cc < 0) continue;
            if (rr >= R || cc >= C) continue;
            if (visited[rr][cc]) continue;
            if (Objects.equals(m[rr][cc], "#")) continue;

            rq.add(rr);
            cq.add(cc);
            visited[rr][cc] = true;
            nodes_in_next_layer++;
        }
    }

    public static void main(String...args)
    {
        dungeon ob = new dungeon();
        Boolean[][] visited = new Boolean[ob.R][ob.C];

        ob.m[4][3] = "E";
        ob.m[2][1] = "#";
        ob.m[1][1] = "#";
        ob.m[4][0] = "#";
        ob.m[3][2] = "#";
        ob.m[4][2] = "#";
        ob.m[0][3] = "#";
        ob.m[3][3] = "#";
        ob.m[1][5] = "#";
        ob.m[4][5] = "#";
        for (int i = 0; i < ob.R; i++)
        {
            for (int j = 0; j < ob.C; j++)
            {
                visited[i][j] = false;
            }
        }
        System.out.println(ob.solve(visited));
    }
}
