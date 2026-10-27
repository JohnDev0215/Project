package com.byd.vrassistant.livedata;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class Result<T> extends LiveData<T> {
    private static final int START_VERSION = -1;
    protected boolean isAllowNullValue;
    private final AtomicInteger mCurrentVersion = new AtomicInteger(-1);

    public Result(T t) {
        setValue(t);
    }

    public Result() {
    }

    @Override // androidx.lifecycle.LiveData
    public void observe(LifecycleOwner lifecycleOwner, Observer<? super T> observer) {
        super.observe(lifecycleOwner, createObserverWrapper(observer, this.mCurrentVersion.get()));
    }

    @Override // androidx.lifecycle.LiveData
    public void observeForever(Observer<? super T> observer) {
        super.observeForever(createObserverWrapper(observer, this.mCurrentVersion.get()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void observeSticky(LifecycleOwner lifecycleOwner, Observer<T> observer) {
        super.observe(lifecycleOwner, createObserverWrapper(observer, -1));
    }

    public void observeStickyForever(Observer<? super T> observer) {
        super.observeForever(createObserverWrapper(observer, -1));
    }

    @Override // androidx.lifecycle.LiveData
    protected void setValue(T t) {
        this.mCurrentVersion.getAndIncrement();
        super.setValue(t);
    }

    class ObserverWrapper implements Observer<T> {
        private final Observer<? super T> mObserver;
        private int mVersion;

        public ObserverWrapper(Observer<? super T> observer, int i) {
            this.mObserver = observer;
            this.mVersion = i;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(T t) {
            if (Result.this.mCurrentVersion.get() > this.mVersion) {
                if (t != null || Result.this.isAllowNullValue) {
                    this.mObserver.onChanged(t);
                }
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return Objects.equals(this.mObserver, ((ObserverWrapper) obj).mObserver);
        }

        public int hashCode() {
            return Objects.hash(this.mObserver);
        }
    }

    @Override // androidx.lifecycle.LiveData
    public void removeObserver(Observer<? super T> observer) {
        if (observer.getClass().isAssignableFrom(ObserverWrapper.class)) {
            super.removeObserver(observer);
        } else {
            super.removeObserver(createObserverWrapper(observer, -1));
        }
    }

    private Result<T>.ObserverWrapper createObserverWrapper(Observer<? super T> observer, int i) {
        return new ObserverWrapper(observer, i);
    }

    public void clean() {
        super.setValue(null);
    }
}
