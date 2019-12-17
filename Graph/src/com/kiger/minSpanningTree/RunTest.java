package com.kiger.minSpanningTree;

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
        LazyPrim lazyPrim = new LazyPrim();
        lazyPrim.prim(g, 3);

        Kruskal kruskal = new Kruskal();
        kruskal.kruskal(g);
    }

}
