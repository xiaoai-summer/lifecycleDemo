package com.example.lifecycledemo;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private TextView mTxt;
    private MyListener mMyListener;
    private boolean mIsGirl = false;

    @Override
    public int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTxt = findViewById(R.id.txt);
        mIsGirl = true;
//        mMyListener = new MyListener(mIsGirl, new IPersonCallback() {
//            @Override
//            public void setPerson(Person person) {
//                mTxt.setText(person.getName());
//            }
//        });

        mMyListener = new MyListener(mIsGirl, getLifecycle(), new IPersonCallback() {
            @Override
            public void setPerson(Person person) {
                mTxt.setText(person.getName());
            }
        });
        getLifecycle().addObserver(mMyListener);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        mMyListener.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        mMyListener.stop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, ev.getAction() + "----" + ev.getX() + "*****" + ev.getRawX());
        return super.dispatchTouchEvent(ev);
    }
}