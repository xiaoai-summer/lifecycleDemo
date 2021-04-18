package com.example.lifecycledemo;

/**
 * Created by wangxiaoyan on 2021/4/18.
 */
public class MainViewModel {
    private LiveDataHelper mLiveDataHelper;

    public LiveDataHelper getLiveDataHelper() {
        if (mLiveDataHelper == null) {
            mLiveDataHelper = new LiveDataHelper();
        }
        return mLiveDataHelper;
    }
}
