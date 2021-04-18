package com.example.lifecycledemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private TextView mTxt;
    private MyListener mMyListener;
    private boolean mIsGirl = false;
    private MainViewModel mMainViewModel = new MainViewModel();
    private LiveDataHelper mLiveDataHelper;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTxt = findViewById(R.id.txt);
        mIsGirl = true;
        mLiveDataHelper = mMainViewModel.getLiveDataHelper();
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
                mLiveDataHelper.updatePersonLiveData(person);
            }
        });
        getLifecycle().addObserver(mMyListener);


        mLiveDataHelper.registPersonLiveData(this, mPersonObserver);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLiveDataHelper.updatePersonLiveData(new Person("myidol", 158));
            }
        }, 2000);

        EventBus.getDefault().register(this);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new MessageEvent(new Person("xiaoxingxing", 1)));
            }
        }, 3000);
    }

    private Observer<Person> mPersonObserver = new Observer<Person>() {
        @Override
        public void onChanged(Person person) {
            mTxt.setText(person.getName());
        }
    };

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
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(MessageEvent messageEvent) {
        Log.i(TAG, "EVENT--BUS onReceiveEvent" + messageEvent.getPerson().getName());
        mTxt.setText(messageEvent.getPerson().getName());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, ev.getAction() + "----" + ev.getX() + "*****" + ev.getRawX());
        return super.dispatchTouchEvent(ev);
    }
}