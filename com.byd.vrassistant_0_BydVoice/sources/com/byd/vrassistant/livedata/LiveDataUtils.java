package com.byd.vrassistant.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class LiveDataUtils {

    public interface Zipper<A, B, R> {
        R apply(A a, B b);
    }

    public interface Zipper3<A, B, C, R> {
        R apply(A a, B b, C c);
    }

    public static <A, B, R> LiveData<R> zip(final LiveData<A> liveData, final LiveData<B> liveData2, Zipper<A, B, R> zipper) {
        final SafeZipper safeZipper = new SafeZipper(zipper);
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
        mediatorLiveData.addSource(liveData, new SafeObserver<A>() { // from class: com.byd.vrassistant.livedata.LiveDataUtils.1
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(A a) {
                atomicBoolean.set(true);
                if (atomicBoolean2.get()) {
                    mediatorLiveData.setValue(safeZipper.apply(a, liveData2.getValue()));
                }
            }
        });
        mediatorLiveData.addSource(liveData2, new SafeObserver<B>() { // from class: com.byd.vrassistant.livedata.LiveDataUtils.2
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(B b) {
                atomicBoolean2.set(true);
                if (atomicBoolean.get()) {
                    mediatorLiveData.setValue(safeZipper.apply(liveData.getValue(), b));
                }
            }
        });
        return mediatorLiveData;
    }

    public static <A, B, C, R> LiveData<R> zip(final LiveData<A> liveData, final LiveData<B> liveData2, final LiveData<C> liveData3, Zipper3<A, B, C, R> zipper3) {
        final SafeZipper3 safeZipper3 = new SafeZipper3(zipper3);
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
        final AtomicBoolean atomicBoolean3 = new AtomicBoolean(false);
        mediatorLiveData.addSource(liveData, new SafeObserver<A>() { // from class: com.byd.vrassistant.livedata.LiveDataUtils.3
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(A a) {
                atomicBoolean.set(true);
                if (atomicBoolean2.get() && atomicBoolean3.get()) {
                    mediatorLiveData.setValue(safeZipper3.apply(a, liveData2.getValue(), liveData3.getValue()));
                }
            }
        });
        mediatorLiveData.addSource(liveData2, new SafeObserver<B>() { // from class: com.byd.vrassistant.livedata.LiveDataUtils.4
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(B b) {
                atomicBoolean2.set(true);
                if (atomicBoolean.get() && atomicBoolean3.get()) {
                    mediatorLiveData.setValue(safeZipper3.apply(liveData.getValue(), b, liveData3.getValue()));
                }
            }
        });
        mediatorLiveData.addSource(liveData3, new SafeObserver<C>() { // from class: com.byd.vrassistant.livedata.LiveDataUtils.5
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(C c) {
                atomicBoolean3.set(true);
                if (atomicBoolean.get() && atomicBoolean2.get()) {
                    mediatorLiveData.setValue(safeZipper3.apply(liveData.getValue(), liveData2.getValue(), c));
                }
            }
        });
        return mediatorLiveData;
    }

    public static <T> SafeMediatorLiveData<T> safeIt(LiveData<T> liveData) {
        final SafeMediatorLiveData<T> safeMediatorLiveData = new SafeMediatorLiveData<>();
        safeMediatorLiveData.addSource(liveData, new SafeObserver<T>() { // from class: com.byd.vrassistant.livedata.LiveDataUtils.6
            @Override // com.byd.vrassistant.livedata.SafeObserver
            public void onSafeChanged(T t) {
                safeMediatorLiveData.postValue(t);
            }
        });
        return safeMediatorLiveData;
    }
}
