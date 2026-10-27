package com.byd.vrassistant.livedata;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

/* JADX INFO: loaded from: classes.dex */
public class SafeMediatorLiveData<T> extends MediatorLiveData<T> {
    @Override // androidx.lifecycle.LiveData
    public void observe(LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        super.observe(lifecycleOwner, new SafeObserver<T>() { // from class: com.byd.vrassistant.livedata.SafeMediatorLiveData.1
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(T t) {
                observer.onChanged(t);
            }
        });
    }

    @Override // androidx.lifecycle.LiveData
    public void observeForever(final Observer<? super T> observer) {
        super.observeForever(new SafeObserver<T>() { // from class: com.byd.vrassistant.livedata.SafeMediatorLiveData.2
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(T t) {
                observer.onChanged(t);
            }
        });
    }
}
