package com.kiger.fileDecompression;

/**
 * @ClassName Node
 * @Description 哈夫曼树结点
 * @Author zk_kiger
 * @Date 2019/11/7 18:44
 * @Version 1.0
 */

public class Node {

    private int weight;
    private int index;
    private Node leftChild;
    private Node rightChild;

    public Node(int index, int weight) {
        this.weight = weight;
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
