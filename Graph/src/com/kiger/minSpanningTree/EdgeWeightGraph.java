package com.kiger.minSpanningTree;

import com.kiger.graph.Edge;
import com.kiger.graph.Vertex;

import java.util.Scanner;

/**
 * @ClassName EdgeWeightGraph
 * @Description 边带权图无向图
 * @Author zk_kiger
 * @Date 2019/11/27 15:47
 * @Version 1.0
 */

public class EdgeWeightGraph {

    private int V;  // 顶点数目
    private int E;  // 边的数目
    private Vertex<Integer>[] adj;  // 邻接表

    private EdgeWeightGraph(int V) {
        this.V = V;
        this.E = 0;
        // 实例化邻接表
        adj = new Vertex[V+1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new Vertex<>();
            adj[i].setData(i);
        }
    }

    public EdgeWeightGraph(Scanner input) {
        this(6);

        int E = 9;
        int a[][] = {
                {1, 2, 1},
                {1, 3, 2},
                {2, 3, 6},
                {2, 4, 11},
                {3, 4, 9},
                {3, 5, 13},
                {4, 5, 7},
                {4, 6, 3},
                {5, 6, 4},
        };
        for (int i = 0; i < a.length; i++) {
            addEdge(a[i][0], a[i][1], a[i][2]);
        }
    }

    private void addEdge(int v, int w, int weight) {
        adj[v].add(new Edge(v, w, weight, null));
        adj[w].add(new Edge(w, v, weight, null));
        E++;
    }

    // 获得某个顶点的链表
    public Vertex getVertex(int v) {
        return adj[v];
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

}
