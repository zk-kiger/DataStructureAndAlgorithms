package com.kiger.Link;

import java.util.Stack;

/**
 * @ClassName Polynomial
 * @Description 多项式结点
 * @Author zk_kiger
 * @Date 2019/9/16 21:00
 * @Version 1.0
 */

public class Polynomial {

    // 系数
    private double coef;
    // 指数
    private int expn;

    public Polynomial() {
    }

    public Polynomial(double coef, int expn) {
        this.coef = coef;
        this.expn = expn;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public int getExpn() {
        return expn;
    }

    public void setExpn(int expn) {
        this.expn = expn;
    }

    @Override
    public String toString() {
        if (expn == 0) {
            if (coef > 0) {
                return "+" + coef;
            }
            if (coef < 0) {
                return String.valueOf(coef);
            }
        }
        if (expn == 1) {
            if (coef > 0) {
                return "+" + coef + "x";
            }
            if (coef < 0) {
                return coef + "x";
            }
        }
        if (coef > 0) {
            return "+" + coef + "x^" + expn;
        }
        return coef + "x^" + expn;
    }
}
