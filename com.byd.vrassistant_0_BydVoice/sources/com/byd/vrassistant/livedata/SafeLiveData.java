package com.byd.vrassistant.livedata;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

/* JADX INFO: loaded from: classes.dex */
public class SafeLiveData<T> extends LiveData<T> {
    public SafeLiveData(T t) {
        super(t);
    }

    public SafeLiveData() {
    }

    @Override // androidx.lifecycle.LiveData
    public void observe(LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        super.observe(lifecycleOwner, new SafeObserver<T>() { // from class: com.byd.vrassistant.livedata.SafeLiveData.1
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(T t) {
                observer.onChanged(t);
            }
        });
    }

    @Override // androidx.lifecycle.LiveData
    public void observeForever(final Observer<? super T> observer) {
        super.observeForever(new SafeObserver<T>() { // from class: com.byd.vrassistant.livedata.SafeLiveData.2
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(T t) {
                observer.onChanged(t);
            }
        });
    }
}
