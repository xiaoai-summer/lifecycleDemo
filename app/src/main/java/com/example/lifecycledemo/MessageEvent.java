package com.example.lifecycledemo;

/**
 * Created by wangxiaoyan on 2021/4/19.
 */
public class MessageEvent {
    private Person mPerson;
    public MessageEvent(Person person) {
        this.mPerson = person;
    }

    public Person getPerson() {
        return mPerson;
    }

    public void setPerson(Person mPerson) {
        this.mPerson = mPerson;
    }
}
