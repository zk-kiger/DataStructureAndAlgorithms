package com.kiger.minSpanningTree;

import com.kiger.graph.Edge;
import com.kiger.graph.Vertex;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName LazyPrim
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/11/27 16:48
 * @Version 1.0
 */

public class LazyPrim {

    // 记录顶点是否访问
    private boolean[] marked;
    // 存储生成树顶点
    private ArrayDeque<Edge> mst;
    // 存储最小生成树的权值
    int sum = 0;

    public LazyPrim(){}

    public void prim(EdgeWeightGraph G, int v) {
        marked = new boolean[G.getV()+1];
        mst = new ArrayDeque<>();

        prim_(G, v);
        System.out.println(sum);
    }
    private void prim_(EdgeWeightGraph G, int v) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        visit(G, v, queue);
        while (!queue.isEmpty()) {
            // 取得边权最小的判断顶点是否访问过
            Edge edge = queue.remove();
            int v1 = edge.getpVertex();
            int v2 = edge.getVertexIndex();
            if (marked[v1] && marked[v2])   // 边失效
                continue;
            sum += edge.getWeight();
            mst.add(edge);
            if (!marked[v1])
                visit(G, v1, queue);
            if (!marked[v2])
                visit(G, v2, queue);
        }
    }

    // 将与顶点v关联(且没有访问过)的边添加到优先队列中
    private void visit(EdgeWeightGraph G, int v, PriorityQueue quque) {
        // 标记顶点v并将所有连接v和为标记顶点的边添加队列
        marked[v] = true;
        Vertex vertex = G.getVertex(v);
        for (int i = 0; i < vertex.size(); i++) {
            int nextVertex = vertex.get(i).getVertexIndex();
            if (!marked[nextVertex])
                quque.add(vertex.get(i));
        }
    }

    public int getSum() {
        return sum;
    }

}
