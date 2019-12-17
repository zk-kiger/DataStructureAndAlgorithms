package com.kiger.graph;

/**
 * @ClassName DepthFirstSearch
 * @Description 深度优先搜索
 * @Author zk_kiger
 * @Date 2019/11/13 22:41
 * @Version 1.0
 */

public class DepthFirstSearch {

    private boolean[] marked;

    public DepthFirstSearch() {}

    public void dfs(Graph G, int v) {
        // 初始化标记数组
        marked = new boolean[G.getV()];
        dfs_(G, v);
    }
    private void dfs_(Graph G, int v) {
        marked[v] = true;
        System.out.print(v + " ");

        Vertex vertex = G.getVertex(v);
        for (int i = 0; i < vertex.size(); i++) {
            int next = vertex.get(i).getVertexIndex();
            if (!marked[next])
                dfs_(G, next);
        }
    }

}
