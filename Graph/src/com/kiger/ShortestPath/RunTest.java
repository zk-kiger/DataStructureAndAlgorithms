package com.kiger.ShortestPath;

import com.kiger.minSpanningTree.EdgeWeightGraph;
import com.kiger.minSpanningTree.Kruskal;
import com.kiger.minSpanningTree.LazyPrim;

import java.util.Scanner;

/**
 * @ClassName RunTest
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/11/27 15:42
 * @Version 1.0
 */

public class RunTest {

    public static void main(String[] args) {

        EdgeWeightGraph g = new EdgeWeightGraph(new Scanner(System.in));
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.dijkstra(g, 1);

    }

}
