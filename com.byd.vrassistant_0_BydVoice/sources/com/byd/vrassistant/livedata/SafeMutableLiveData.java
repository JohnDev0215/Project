package com.byd.vrassistant.livedata;

/* JADX INFO: loaded from: classes.dex */
public class SafeMutableLiveData<T> extends SafeLiveData<T> {
    public SafeMutableLiveData() {
    }

    public SafeMutableLiveData(T t) {
        super(t);
    }

    @Override // androidx.lifecycle.LiveData
    public void setValue(T t) {
        super.setValue(t);
    }

    @Override // androidx.lifecycle.LiveData
    public void postValue(T t) {
        super.postValue(t);
    }
}
