package com.kiger.child;

import org.junit.Test;
import com.kiger.child.TreeChild.Node;

import java.util.List;

/**
 * @ClassName TreeChildTest
 * @Description 测试
 * @Author zk_kiger
 * @Date 2019/6/26 22:55
 * @Version 1.0
 */

public class TreeChildTest {
    @Test
    public void test() {
        TreeChild<String> tree = new TreeChild<String>("root");
        Node<String> root = tree.getRoot();
        System.out.println("根节点：" + root);

        tree.addNode("节点1", root);
        tree.addNode("节点2", root);
        tree.addNode("节点3", root);
        System.out.println("添加子节点后的根节点：" + root);
        System.out.println("树的深度：" + tree.getDeep());

        List<Node<String>> nodes = tree.getChildren(root);
        System.out.println("根节点的第一个子节点：" + nodes.get(0));
        System.out.println("根节点的第二个子节点：" + nodes.get(1));
        System.out.println("根节点的第三个子节点：" + nodes.get(2));

        tree.addNode("节点4", nodes.get(0));
        System.out.println("根节点的第一个子节点：" + nodes.get(0));
        System.out.println("树的深度：" + tree.getDeep());
    }
}
