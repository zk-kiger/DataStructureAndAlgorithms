package com.kiger.graph;

/**
 * @ClassName Edge
 * @Description 边结点
 * @Author zk_kiger
 * @Date 2019/11/12 22:32
 * @Version 1.0
 */

public class Edge {
    // 边的另一个顶点
    private int pVertex;
    // 存在边的顶点下标
    private int vertexIndex;
    // 与顶点之间的权值
    private int weight;
    // 相连的下一个边
    private Edge next;

    public Edge() {}

    public Edge(int pVertex, int vertexIndex, Edge next) {
        this.pVertex = pVertex;
        this.vertexIndex = vertexIndex;
        this.next = next;
    }

    public Edge(int pVertex, int vertexIndex, int weight, Edge next) {
        this.pVertex = pVertex;
        this.vertexIndex = vertexIndex;
        this.weight = weight;
        this.next = next;
    }

    public int getVertexIndex() {
        return vertexIndex;
    }

    public void setVertexIndex(int vertexIndex) {
        this.vertexIndex = vertexIndex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }

    public int getpVertex() {
        return pVertex;
    }

    public void setpVertex(int pVertex) {
        this.pVertex = pVertex;
    }
}
