package com.amazon.music.find.livedata;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private static final String TAG = "SingleLiveEvent";
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) SingleLiveEvent.class);
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @Override // androidx.lifecycle.LiveData
    @MainThread
    public void observe(LifecycleOwner owner, final Observer<? super T> observer) {
        if (hasActiveObservers()) {
            logger.info("SingleLiveEvent::observe. Multiple observers registered but only one will be notified of changes.");
        }
        super.observe(owner, new Observer<T>() { // from class: com.amazon.music.find.livedata.SingleLiveEvent.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable T t) {
                if (SingleLiveEvent.this.mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    @MainThread
    public void setValue(@Nullable T t) {
        this.mPending.set(true);
        super.setValue(t);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
