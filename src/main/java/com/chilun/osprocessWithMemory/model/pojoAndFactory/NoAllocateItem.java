package com.chilun.osprocessWithMemory.model.pojoAndFactory;

/**
 * @auther 齿轮
 * @create 2022-11-14-11:20
 */
public class NoAllocateItem implements Comparable<NoAllocateItem> {
    private int beginSite;
    private int size;

    public NoAllocateItem() {
    }

    public NoAllocateItem(int beginSite, int size) {
        this.beginSite = beginSite;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBeginSite() {
        return beginSite;
    }

    public void setBeginSite(int beginSite) {
        this.beginSite = beginSite;
    }

    @Override
    public int compareTo(NoAllocateItem o) {
        return size - o.size;
    }

    @Override
    public String toString() {
        return "[" + beginSite +
                "-" + (size+beginSite) +
                ']';
    }
}
