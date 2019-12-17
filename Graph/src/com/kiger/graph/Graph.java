package com.kiger.graph;

import java.util.Scanner;

/**
 * @ClassName Graph
 * @Description 无权无向图
 * @Author zk_kiger
 * @Date 2019/11/13 22:22
 * @Version 1.0
 */

public class Graph{

    private int V;  // 顶点数目
    private int E;  // 边的数目
    private Vertex<Integer>[] adj;  // 邻接表

    private Graph(int V) {
        this.V = V;
        this.E = 0;
        // 实例化邻接表
        adj = new Vertex[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Vertex<>();
            adj[i].setData(i);
        }
    }

    public Graph(Scanner input) {
        this(6);
        // 读取V并初始化图
        /*this(input.nextInt());
        this.E = input.nextInt();
        for (int i = 0; i < E; i++) {
            int v = input.nextInt();
            int w = input.nextInt();
            addEdge(v, w);
        }*/
        this.E = 8;
        int a[][] = {
                {0, 5},
                {2, 4},
                {2, 3},
                {1, 2},
                {0, 1},
                {0, 4},
                {3, 5},
                {0, 2}
        };
        for (int i = 0; i < a.length; i++) {
            addEdge(a[i][0], a[i][1]);
        }
    }

    private void addEdge(int v, int w) {
        // 将w添加到v的链表中
        adj[v].add(new Edge(v, w, null));
        adj[w].add(new Edge(w, v, null));
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
