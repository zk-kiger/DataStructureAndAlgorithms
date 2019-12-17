package com.kiger.graph;

import java.util.ArrayDeque;

/**
 * @ClassName BreadthFirstPaths
 * @Description 广度优先搜索
 * @Author zk_kiger
 * @Date 2019/11/15 21:00
 * @Version 1.0
 */

public class BreadthFirstSearch {

    private boolean[] marked;

    public BreadthFirstSearch() {}

    // 从顶点v开始广搜
    public void bfs(Graph G, int v) {
        marked = new boolean[G.getV()];
        bfs_(G, v);
    }
    private void bfs_(Graph G, int v) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        marked[v] = true;
        queue.add(v);
        while (!queue.isEmpty()) {
            int curVertex = queue.remove();
            System.out.print(curVertex + " ");

            Vertex vertex = G.getVertex(curVertex);
            for (int i = 0; i < vertex.size(); i++) {
                int nextVertex = vertex.get(i).getVertexIndex();
                if (!marked[nextVertex]) {
                    marked[nextVertex] = true;
                    queue.add(nextVertex);
                }
            }
        }
    }

}
