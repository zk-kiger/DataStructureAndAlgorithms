package com.kiger.Link;

import java.util.Scanner;

/**
 * @ClassName PolynomialCalculation
 * @Description 多项式计算
 * @Author zk_kiger
 * @Date 2019/9/16 21:01
 * @Version 1.0
 */

public class PolynomialCalculation {

    private LinkList<Polynomial> linkListA;
    private LinkList<Polynomial> linkListB;
    private Scanner input = new Scanner(System.in);

    public PolynomialCalculation() {}

    public LinkList<Polynomial> setLinkListA() {
        linkListA = new LinkList<>();
        System.out.println("请输入一元多项式的每一项的系数和指数:");

        Double coef = null;
        Integer expn = null;
        while(true) {
            coef = input.nextDouble();
            if (coef == 0) {
                break;
            }
            expn = input.nextInt();
            linkListA.add(new Polynomial(coef, expn));
        }
        return linkListA;
    }

    public LinkList<Polynomial> setLinkListB() {
        linkListB = new LinkList<>();
        System.out.println("请输入一元多项式的每一项的系数和指数:");

        Double coef = null;
        Integer expn = null;
        while(true) {
            coef = input.nextDouble();
            if (coef == 0) {
                break;
            }
            expn = input.nextInt();
            linkListB.add(new Polynomial(coef, expn));
        }
        return linkListB;
    }

    // 两多项式相加
    public static LinkList AddPolyn(LinkList<Polynomial> linkListA, LinkList<Polynomial> linkListB) {
        LinkList<Polynomial> linkList = new LinkList<>();
        int i = 0;
        int j = 0;
        int lenA = linkListA.length();
        int lenB = linkListB.length();
        while ((i < lenA) || (j < lenB)) {
            if (i == lenA) {
                for (int k = j; k < lenB; k++) {
                    linkList.add(new Polynomial(linkListB.get(k).getCoef(), linkListB.get(k).getExpn()));
                }
                break;
            }
            if (j == lenB) {
                for (int k = i; k < lenA; k++) {
                    linkList.add(new Polynomial(linkListA.get(k).getCoef(), linkListA.get(k).getExpn()));
                }
                break;
            }
            Polynomial a = linkListA.get(i);
            Polynomial b = linkListB.get(j);
            if (a.getExpn() == b.getExpn()) {
                if (a.getCoef() + b.getCoef() != 0) {
                    linkList.add(new Polynomial(a.getCoef()+b.getCoef(), a.getExpn()));
                }
                i++;
                j++;
            } else if(a.getExpn() > b.getExpn()) {
                linkList.add(new Polynomial(b.getCoef(), b.getExpn()));
                j++;
            } else {
                linkList.add(new Polynomial(a.getCoef(), a.getExpn()));
                i++;
            }
        }
        return linkList;
    }

    // 多项式相减
    public static LinkList SubPolyn(LinkList<Polynomial> linkListA, LinkList<Polynomial> linkListB) {
        // 让其中一个多项式每一项系数变为负数
        for (int i = 0; i < linkListB.length(); i++) {
            Polynomial p = linkListB.get(i);
            p.setCoef(p.getCoef() * (-1));
        }

        // 调用多项式相加
        LinkList linkList = AddPolyn(linkListA, linkListB);

        return linkList;
    }

    // 多项式相乘
    public static LinkList MultPolyn(LinkList<Polynomial> linkListA, LinkList<Polynomial> linkListB) {
        // linkListA的每一项乘以linkListB的每一项，然后两项相加
        LinkList linkList = new LinkList();
        for (int i = 0; i < linkListA.length(); i++) {
            Polynomial a = linkListA.get(i);
            LinkList linkListTemp = new LinkList();
            for (int j = 0; j < linkListB.length(); j++) {
                Polynomial b = linkListB.get(j);
                linkListTemp.add(new Polynomial(a.getCoef()*b.getCoef(), a.getExpn()+b.getExpn()));
            }
            linkList = AddPolyn(linkList, linkListTemp);
        }
        return linkList;
    }

    // 多项式求导
    public static LinkList CoefPolyn(LinkList<Polynomial> linkList) {

        for (int i = 0; i < linkList.length(); i++) {
            Polynomial p = linkList.get(i);
            if (p.getExpn() == 0) {
                linkList.delete(i);
                continue;
            }
            p.setCoef(p.getCoef() * p.getExpn());
            p.setExpn(p.getExpn() - 1);
        }

        return linkList;
    }

}
