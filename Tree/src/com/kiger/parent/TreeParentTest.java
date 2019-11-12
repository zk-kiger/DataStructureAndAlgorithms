package com.kiger.parent;

import org.junit.Test;
import com.kiger.parent.TreeParent.Node;

import java.util.List;

/**
 * @ClassName TreeParentTest
 * @Description 测试
 * @Author zk_kiger
 * @Date 2019/6/25 23:17
 * @Version 1.0
 */

public class TreeParentTest {
    @Test
    public void test() {
        TreeParent<String> tree = new TreeParent<String>("root");
        Node<String> root = tree.getRoot();
        System.out.println("根节点：" + root);

        tree.addNode("节点1", root);
        tree.addNode("节点2", root);
        System.out.println("树的深度：" + tree.getDeep());

        List<Node<String>> nodes = tree.getChildren(root);
        System.out.println("根节点的第一个子节点：" + nodes.get(0));

        tree.addNode("节点3", nodes.get(0));
        System.out.println("树的深度：" + tree.getDeep());

        tree.removeChildren(root);
        System.out.println("根节点的第一个子节点：" + nodes.get(0));
        System.out.println("树的深度：" + tree.getDeep());

    }
}
