package com.kiger.minSpanningTree;

import com.kiger.graph.Edge;
import com.kiger.graph.Vertex;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @ClassName Prim
 * @Description 维护一个dis数组存储未加入
 * @Author zk_kiger
 * @Date 2019/11/26 22:06
 * @Version 1.0
 */

public class Prim {
    // 记录顶点是否访问
    private boolean[] marked;
    // 记录顶点到生成树的距离
    private int[] dis;
    // 存储生成树顶点
    private ArrayDeque<Integer> mst;
    // 存储最小生成树的权值
    int sum = 0;

    public Prim(){}

    public void prim(EdgeWeightGraph G, int v) {
        marked = new boolean[G.getV()+1];
        dis = new int[G.getV()+1];
        mst = new ArrayDeque<>();

        // 先将dis所有置为无穷
        Arrays.fill(dis, Integer.MAX_VALUE);

        // 更新与起点v相连接的边之间的权值
        Vertex vertex = G.getVertex(v);
        for (int i = 0; i < vertex.size(); i++) {
            Edge edge = vertex.get(i);
            dis[edge.getVertexIndex()] = edge.getWeight();
        }

        prim_(G, v);
    }
    private void prim_(EdgeWeightGraph G, int v) {
        int count = 0;
        int min,minVertex = 0;
        marked[v] = true;
        mst.add(v);
        ++count;
        while (count < G.getV()) {
            // 查找与生成树不连接，且边权最小的顶点
            min = Integer.MAX_VALUE;
            for (int i = 1; i < dis.length; i++) {
                if (!marked[i] && dis[i] < min) {
                    min = dis[i];
                    minVertex = i;
                }
            }
            marked[minVertex] = true;
            mst.add(minVertex);
            sum += dis[minVertex];
            ++count;

            // 加入新的顶点，更新dis数组
            Vertex vertex = G.getVertex(minVertex);
            for (int i = 0; i < vertex.size(); i++) {
                Edge edge = vertex.get(i);              // 相邻的边
                int nextVertex = edge.getVertexIndex(); // 相邻的顶点
                int nextWeight = edge.getWeight();      // 与相邻顶点之间的边权
                if (!marked[nextVertex] && nextWeight < dis[nextVertex])
                    dis[nextVertex] = nextWeight;
            }
        }
    }

    public int getSum() {
        return sum;
    }
}
