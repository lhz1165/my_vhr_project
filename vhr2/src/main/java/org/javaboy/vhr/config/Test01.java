package org.javaboy.vhr.config;

import java.util.Arrays;
import java.util.Comparator;

public class Test01 {
    public static void main(String[] args) {
        Test01 test01 = new Test01();
        int[][]edge=new int[][]{{1, 2, 1}, {2, 3, 3}, {1, 3, 100}};
        int[][] matrix = test01.getMatrix(edge);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }

    }

    // 第一题
    public static int[][] merge(int[][] intervals) {
        if (null == intervals || intervals.length == 0) {
            return new int[][]{};
        }

        //首先根据二维数组的第一位数字排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //用作对比的数组
        int[][] tmpArry = new int[intervals.length][];
        //计数器
        int index = 0;
        //把用来对比的数组初始化位第一个数组
        tmpArry[0] = intervals[0];
        //从第二个开始依次比较
        for (int i = 1; i < intervals.length; i++) {
            //如果当前数组在tmpArry之内那么覆盖什么都不做
            if (tmpArry[index][1] >= intervals[i][1] && tmpArry[index][0] <= intervals[i][0]) {
                //如果两个组数没有任何关联
            } else if (tmpArry[index][1] < intervals[i][0] || tmpArry[index][0] > intervals[i][1]) {
                //那么换到下一个数组
                index++;
                tmpArry[index] = intervals[i];
            } else {
                // 重叠合并
                int minVal = Math.min(tmpArry[index][0], intervals[i][0]);
                int maxVal = Math.max(tmpArry[index][1], intervals[i][1]);
                tmpArry[index] = new int[]{minVal, maxVal};
            }
        }
        //为结果赋值
        int[][] result = new int[index + 1][tmpArry[0].length];
        for (int i = 0; i <= index; i++) {
            result[i] = tmpArry[i];
        }
        return result;
    }


    //第二题
    public boolean valid(String word, String abbr) {
        //word的指针
        int i = 0;
        //abbr的指针
        int j = 0;
        int len1 = word.length();
        int len2 = abbr.length();
        while (i < len1 && j < len2) {
            //如果abbr指到数字了 那么word指针移动
            if (Character.isDigit(abbr.charAt(j))) {
                i += (abbr.charAt(j) - '0');
                j++;
            }else {
                //比较是否相同字符
                if (word.charAt(i) != abbr.charAt(j)) {
                    return false;
                }else {
                    i++;
                    j++;
                }
            }
        }
        if (i >= len1) {
            return false;
        }
        return true;
    }
    //第三题
    public int minPath(int n, int[][] edges, int start, int end){
        //得到邻接矩阵
        int[][] mrtrix = getMatrix(edges);
        //使用Dijsktra求最小路径
        int[] shortPath = Dijsktra(mrtrix, start);
        //求最小惩罚
        int result=0;
        for (int i : shortPath) {
            result=result|edges[i][2];
        }
        return result;
    }
    //把edges转化为邻接矩阵
    public int[][] getMatrix(int[][] edges) {
        //把edges转化成邻接矩阵
        int[][] matrix = new int[edges.length][edges.length];
        for (int i = 0; i < edges.length; i++) {
            //行
            int r=0;
            //列
            int c=0;
            for (int j = 0; j < edges[i].length; j++) {
                if (j == 0) {
                    r = edges[i][j]-1;
                } else if (j == 1) {
                    c = edges[i][j]-1;
                }else {
                    //权值
                    matrix[r][c] = edges[i][j];
                    matrix[c][r] = edges[i][j];
                }
            }
        }
        return matrix;
    }

    //使用Dijsktra找出邻接矩阵最小的路径
    public static int[] Dijsktra(int[][]weight,int start){
        int length = weight.length;
        int[] shortPath = new int[length];//存放从start到各个点的最短距离
        shortPath[0] = 0;//start到他本身的距离最短为0
        String path[] = new String[length];//存放从start点到各点的最短路径的字符串表示
        for(int i=0;i<length;i++){
            path[i] = start+"->"+i;
        }
        int visited[] = new int[length];//标记当前该顶点的最短路径是否已经求出，1表示已经求出
        visited[0] = 1;//start点的最短距离已经求出
        for(int count = 1;count<length;count++){
            int k=-1;
            int dmin = Integer.MAX_VALUE;
            for(int i=0;i<length;i++){
                if(visited[i]==0 && weight[start][i]<dmin){
                    dmin = weight[start][i];
                    k=i;
                }
            }
            shortPath[k] = dmin;
            visited[k] = 1;
            //以k为中间点，修正从start到未访问各点的 |运算
            for(int i=0;i<length;i++){
                if(visited[i]==0 && (weight[start][k] | weight[k][i])<weight[start][i]){
                    weight[start][i] = weight[start][k]|weight[k][i];
                    path[i] = path[k]+"->"+i;
                }
            }
        }
        return shortPath;
    }




}
