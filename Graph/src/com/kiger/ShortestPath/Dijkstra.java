package com.kiger.ShortestPath;

import com.kiger.graph.Edge;
import com.kiger.graph.Vertex;
import com.kiger.minSpanningTree.EdgeWeightGraph;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName Dijkstra
 * @Description 使用优先队列实现Dijkstra算法
 *  思路：类似于BFS
 *      1、记录起点到每个点之间的权值到dis[],起点到起点为0
 *      2、先将起点入队,然后遍历与起点相连的顶点,得到与起点最近的顶点,标记
 *      3、将标记的最近顶点相连的顶点入队,再次判断最近顶点,直到顶点遍历完成
 * @Author zk_kiger
 * @Date 2019/12/16 19:58
 * @Version 1.0
 */

public class Dijkstra {

    // 用来标记是否访问
    private boolean[] marked;
    // 用来记录起点到每个顶点之间的权值
    private int[] dis;
    // 记录最短路径
    private int[] path;

    public Dijkstra(){}

    public void dijkstra(EdgeWeightGraph G, int s) {
        marked = new boolean[G.getV()+1];
        dis = new int[G.getV()+1];
        path = new int[G.getV()+1];
        path[s] = s;
        updateDis(G, s);
        dijkstra_(G, s);
        Iterable<Integer> integers = pathTo(s, 6);
        for (Integer i : integers
             ) {
            System.out.print(i + " ");
        }
    }
    private void dijkstra_(EdgeWeightGraph G, int s) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        Vertex start = G.getVertex(s);
        Edge edge = new Edge((int)start.getData(), (int)start.getData(), start.get(0));
        queue.add(edge);
        while (!queue.isEmpty()) {
            // 当前队列最短路径
            Edge now = queue.peek();
            // 标记这个顶点
            marked[now.getVertexIndex()] = true;
            queue.poll();
            // 遍历该点的相邻顶点
            Vertex nowVertex = G.getVertex(now.getVertexIndex());
            for (int i = 0; i < nowVertex.size(); i++) {
                Edge nextEdge = nowVertex.get(i);

                // 如果该顶点没有访问过且有路
                if (!marked[nextEdge.getVertexIndex()]) {
                    // 如果next顶点到起点的路程  >  now结点到->1+到next->now的路程就更新next
                    if (dis[nextEdge.getVertexIndex()] > nextEdge.getWeight() + dis[nextEdge.getpVertex()]) {
                        dis[nextEdge.getVertexIndex()] = nextEdge.getWeight() + dis[nextEdge.getpVertex()];
                    }
                    // 如果当前顶点到起点的路径小于等于
                    if ((nextEdge.getpVertex() == s)
                            || (dis[nextEdge.getVertexIndex()] >= nextEdge.getWeight() + dis[nextEdge.getpVertex()]))
                        path[nextEdge.getVertexIndex()] = nextEdge.getpVertex();
                    // 该边入队
                    queue.add(nextEdge);
                }
            }
        }
    }

    // 遍历起点更新起点到每个顶点的路径，没有为无穷
    private void updateDis(EdgeWeightGraph G, int s) {
        Vertex start = G.getVertex(s);
        for (int i = 0; i < start.size(); i++) {
            Edge edge = start.get(i);
            dis[edge.getVertexIndex()] = edge.getWeight();
        }
        for (int i = 0; i < dis.length; i++) {
            if (dis[i] == 0 && i != s)
                dis[i] = Integer.MAX_VALUE;
        }
    }

    // 打印最短路径
    private Iterable<Integer> pathTo(int v, int s) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = s; i != v; i = path[i]) {
            queue.add(i);
        }
        queue.add(v);
        return queue;
    }
}
