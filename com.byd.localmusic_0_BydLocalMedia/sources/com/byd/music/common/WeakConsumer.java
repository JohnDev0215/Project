package com.byd.music.common;

import io.reactivex.functions.Consumer;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes5.dex */
public class WeakConsumer<T> implements Consumer<T> {
    private SoftReference<WeakConsumerCallBack<T>> callBack;

    public interface WeakConsumerCallBack<T> {
        void accept(T t);
    }

    public WeakConsumer(WeakConsumerCallBack<T> weakConsumerCallBack) {
        this.callBack = new SoftReference<>(weakConsumerCallBack);
    }

    @Override // io.reactivex.functions.Consumer
    public void accept(T t) throws Exception {
        if (this.callBack.get() != null) {
            this.callBack.get().accept(t);
            this.callBack.clear();
        }
    }
}
