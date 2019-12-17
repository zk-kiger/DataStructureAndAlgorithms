package com.kiger.graph;

import com.kiger.ShortestPath.Dijkstra;

import java.util.Scanner;

/**
 * @ClassName RunTest
 * @Description
 * @Author zk_kiger
 * @Date 2019/11/14 10:59
 * @Version 1.0
 */

public class RunTest {

    public static void main(String[] args) {
        Graph G = new Graph(new Scanner(System.in));
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        Dijkstra dijkstra = new Dijkstra();
        Paths paths = new Paths(G, 0);

        System.out.println("从顶点 0 深搜路径如下：");
        depthFirstSearch.dfs(G, 0);

        System.out.println();
        System.out.println("从顶点 0 广搜路径如下：");
        breadthFirstSearch.bfs(G, 0);

        System.out.println();
        System.out.println("使用深搜输出顶点 0 到顶点 5 的路径：");
        paths.dfs(G);
        Iterable<Integer> path = paths.pathTo(5);
        for (Integer v :
                path) {
            System.out.print(v + " ");
        }

        System.out.println();
        System.out.println("使用广搜输出顶点 0 到顶点 5 的路径：");
        paths.bfs(G);
        Iterable<Integer> path_ = paths.pathTo(5);
        for (Integer v :
                path_) {
            System.out.print(v + " ");
        }
    }
}
