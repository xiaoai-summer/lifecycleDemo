package com.example.lifecycledemo;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import static androidx.lifecycle.Lifecycle.State.STARTED;

/**
 * Created by wangxiaoyan on 2021/4/18.
 */
public class MyListener implements LifecycleObserver {
    private static final String TAG = "MyListener";
    private IPersonCallback mCallback;
    private boolean mIsGirl;
    private Lifecycle mLifecycle;

    public MyListener(boolean girl, Lifecycle lifecycle, IPersonCallback personCallback) {
        this.mCallback = personCallback;
        this.mIsGirl = girl;
        this.mLifecycle = lifecycle;
    }

    public MyListener(boolean girl, IPersonCallback personCallback) {
        this.mCallback = personCallback;
        this.mIsGirl = girl;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        if (mLifecycle.getCurrentState().isAtLeast(STARTED)) {
            Person person = new Person("xiaoai", 180);
            mCallback.setPerson(person);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        Log.i(TAG, "stop");
    }
}
