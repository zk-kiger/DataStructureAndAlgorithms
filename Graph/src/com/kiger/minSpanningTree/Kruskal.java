package com.kiger.minSpanningTree;

import com.kiger.graph.Edge;
import com.kiger.graph.Vertex;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName Kruskal
 * @Description 贪心的思想
 * 每次选择边权最小的边，判断两个顶点是否连通，没有则加入最小生成树
 * @Author zk_kiger
 * @Date 2019/11/27 19:24
 * @Version 1.0
 */

public class Kruskal {

    // 并查集数组
    int[] f;
    // 存储最小生成树的边
    private ArrayDeque<Edge> mst;
    // 存储最小生成树的权值
    int sum = 0;

    public Kruskal(){}

    public void kruskal(EdgeWeightGraph G) {
        f = new int[G.getV()+1];
        mst = new ArrayDeque<>();

        // 初始化并查集数组
        for (int i = 0; i < f.length; i++)
            f[i] = i;

        // 将边添加到优先队列中
        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        for (int i = 1; i <= G.getV(); i++) {
            Vertex vertex = G.getVertex(i);
            for (int j = 0; j < vertex.size(); j++) {
                queue.add(vertex.get(j));
            }
        }

        Kruskal_(G, queue);
        System.out.println(sum);
    }
    private void Kruskal_(EdgeWeightGraph G, PriorityQueue<Edge> queue) {
        int v = G.getV();
        int count = 0;
        while (!queue.isEmpty()) {
            Edge edge = queue.remove();
            int v1 = edge.getpVertex();
            int v2 = edge.getVertexIndex();
            // 判断两个顶点是否连通
            if (merge(v1, v2)) {
                // 如果为连通，那么就选这条边
                ++count;
                mst.add(edge);
                sum += edge.getWeight();
            }
            // 选择了n-1条边退出
            if (count == v - 1)
                break;
        }
    }

    // 判断两个顶点是否为在同一集合，如果不在那么合并两个顶点
    private boolean merge(int v, int w) {
        int t1 = getF(v);
        int t2 = getF(w);
        if (t1 != t2) {
            // 如果没在同一集合，约定t2的父顶点为t1
            f[t2] = t1;
            return true;
        }
        return false;
    }

    // 并查集寻找顶点的父顶点
    private int getF(int v) {
        if (f[v] == v) {
            return v;
        } else {
            // 更新父顶点 - 路径压缩
            f[v] = getF(f[v]);
            return f[v];
        }
    }

}
