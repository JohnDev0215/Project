package io.reactivex.rxjava3.operators;

/* JADX INFO: loaded from: classes7.dex */
public interface SimpleQueue<T> {
    void clear();

    boolean isEmpty();

    boolean offer(T value);

    T poll() throws Throwable;
}
