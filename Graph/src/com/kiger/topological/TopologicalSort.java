package com.kiger.topological;

import com.kiger.graph.Vertex;
import com.kiger.minSpanningTree.EdgeWeightGraph;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName TopologicalSort
 * @Description 拓扑排序 : 就是对一个有向图构造拓扑序列的过程
 * 思路：
 *      从AOV网中选择一个入度为0的顶点输出，并删去此顶点，并删除以此顶点为尾的弧，
 *      重复此步骤，直到输出全部顶点或者AOV网中不存在入度为0的顶点为止
 * @Author zk_kiger
 * @Date 2019/12/17 19:22
 * @Version 1.0
 */

public class TopologicalSort {
    // 拓扑序列
    private List<Integer> ans;

    public TopologicalSort() {}

    public void topologicalSort(EdgeWeightGraph G) {
        topologicalSort_(G);
    }
    private void topologicalSort_(EdgeWeightGraph G) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // 将入度为0的点入队
        for (int i = 1; i <= G.getV(); i++) {
            if (G.getVertex(i).getIn() == 0)
                queue.add(i);
        }

        while(!queue.isEmpty()) {
            Integer v = queue.peek();
            queue.remove();
            ans.add(v);

            // 对此顶点弧表遍历,相邻顶点入度-1
            Vertex vertex = G.getVertex(v);
            for (int i = 0; i < vertex.size(); i++) {
                int nextVertex = vertex.get(i).getVertexIndex();
                int in = G.getVertex(nextVertex).getIn();
                G.getVertex(vertex.get(i).getVertexIndex()).setIn(--in);
                if (in == 0)
                    queue.add(nextVertex);
            }
        }
    }

}
