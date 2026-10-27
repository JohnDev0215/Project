package com.amazon.music.explore.events;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/* JADX INFO: loaded from: classes4.dex */
public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private boolean mPending = false;

    @Override // androidx.lifecycle.LiveData
    @MainThread
    public void observe(@NonNull LifecycleOwner lifecycleOwner, @NonNull final Observer<? super T> observer) {
        super.observe(lifecycleOwner, new Observer<T>() { // from class: com.amazon.music.explore.events.SingleLiveEvent.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(T t) {
                if (SingleLiveEvent.this.mPending) {
                    SingleLiveEvent.this.mPending = false;
                    observer.onChanged(t);
                    SingleLiveEvent.this.removeObserver(this);
                }
            }
        });
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    @MainThread
    public void setValue(T t) {
        this.mPending = true;
        super.setValue(t);
    }
}
