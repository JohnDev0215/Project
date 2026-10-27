package io.reactivex.internal.fuseable;

/* JADX INFO: loaded from: classes7.dex */
public interface SimpleQueue<T> {
    void clear();

    boolean isEmpty();

    boolean offer(T t);

    T poll() throws Exception;
}
