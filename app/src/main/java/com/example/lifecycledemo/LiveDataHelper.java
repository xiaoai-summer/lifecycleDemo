package com.example.lifecycledemo;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * Created by wangxiaoyan on 2021/4/18.
 */
public class LiveDataHelper {
    private MutableLiveData<Person> mPersonLiveData;

    public MutableLiveData<Person> getPersonLiveData() {
        if (mPersonLiveData == null) {
            mPersonLiveData = new MutableLiveData<>();
        }
        return mPersonLiveData;
    }

    public void registPersonLiveData(LifecycleOwner lifecycleOwner, Observer<Person> observer) {
        getPersonLiveData().observe(lifecycleOwner, observer);
    }

    public void updatePersonLiveData(Person person) {
        getPersonLiveData().setValue(person);
    }
}
