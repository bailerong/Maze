import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*题目描述：
给定一个 n 行 m 列的地牢，
其中 '.' 表示可以通行的位置，
'X' 表示不可通行的障碍，牛牛从 (x0 , y0 )
 位置出发，遍历这个地牢，和一般的游戏所不同的是，
 他每一步只能按照一些指定的步长遍历地牢，
 要求每一步都不可以超过地牢的边界，也不能到达障碍上。
地牢的出口可能在任意某个可以通行的位置上。
牛牛想知道最坏情况下，他需要多少步才可以离开这个地牢。
 */
/*
* 输入描述：
每个输入包含 1 个测试用例。每个测试用例的第一行包含两个整数 n 和 m（1 <= n, m <= 50），
表示地牢的长和宽。接下来的 n 行，每行 m 个字符，描述地牢，地牢将至少包含两个 '.'。
接下来的一行，包含两个整数 x0, y0，表示牛牛的出发位置（0 <= x0 < n, 0 <= y0 < m，左上角的坐标为 （0, 0），
出发位置一定是 '.'）。之后的一行包含一个整数 k（0 < k <= 50）表示牛牛合法的步长数，
接下来的 k 行，每行两个整数 dx, dy 表示每次可选择移动的行和列步长（-50 <= dx, dy <= 50）
* */
/*
* 题目解体思路：
* 我们的输入描述和我们的题目描述都非常的长，且难以理解，我在这里拿大白话理解一下
* 就是按照广度优先遍历，找到我们的牛牛走的最长的路径
*
* */
//这是我们的先建立的一个指向标，用来描述我们现在所在的位置，和我们的现在所走的步数。
class point {
    public int x;//我们的横坐标
    public int y;//我们的纵坐标
    public int steps;//我们的进行的步数

    point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    point(int x, int y, int steps) {
        this(x, y);
        this.steps = steps;
    }
}
public class MaxstepMaze {
    public static void main(String[] args) {
        //这里的代码都是我们在为我们的输入做准备，我们的系统输入
        //这里面没什么好说的，要注意的一点就是我们的数组赋值。
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        boolean[][] maze=new boolean[n][m];
        //将我们的maze数组用true和false进行赋值
        for(int i=0;i<n;i++){
            String s=scanner.next();
            for(int j=0;j<m;j++){

                maze[i][j]=s.charAt(j)== '.'?true:false;
            }
        }
        point start=new point(scanner.nextInt(),scanner.nextInt(),0);
        int k=scanner.nextInt();
        int[][] dirs=new int[k][2];
        for(int i=0;i<k;i++){
            dirs[i][0]=scanner.nextInt();
            dirs[i][1]=scanner.nextInt();
        }
        boolean[][] visited=new boolean[n][m];
            System.out.println(BFSmaze(maze,start,visited,dirs));
        }
        scanner.close();
    }
    public static int BFSmaze(boolean[][] maze,point start,boolean[][] visited,int[][] dirs){
        Queue<point> queue=new LinkedList<point>();
        int maxsteps=0;
        queue.add(start);
        visited[start.x][start.y]=true;
        point now,next;
        while(!queue.isEmpty()){
            now=queue.poll();
            if(now.steps>maxsteps){
                maxsteps=now.steps;
            }
            //将我们的各个方向都要进行遍历
            for(int[] dir:dirs){
                next=new point(now.x+dir[0],now.y+dir[1],now.steps+1);
                if(next.x<0||next.x>=maze.length
                ||next.y<0||next.y>=maze[0].length
                ||visited[next.x][next.y]==true
                ||maze[next.x][next.y]==false){
                    continue;
                }
                queue.add(next);
                visited[next.x][next.y]=true;

            }
        }

        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[0].length;j++){
                if(maze[i][j]==true&&visited[i][j]==false){
                    return -1;
                }
            }
        }
        return maxsteps;
    }
}
