package com.kiger.cirlinklist;

/**
 * @ClassName Element
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/9/14 12:45
 * @Version 1.0
 */

public class Element {
    int id;
    int password;

    public Element(int id, int password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", password=" + password +
                '}';
    }
}
