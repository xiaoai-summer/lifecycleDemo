package com.example.lifecycledemo;

/**
 * Created by wangxiaoyan on 2021/4/18.
 */
public class Person {
    private String mName;
    private long mHeight;

    public Person(String name, int height) {
        this.mName = name;
        this.mHeight = height;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public long getHeight() {
        return mHeight;
    }

    public void setHeight(long mHeight) {
        this.mHeight = mHeight;
    }
}
