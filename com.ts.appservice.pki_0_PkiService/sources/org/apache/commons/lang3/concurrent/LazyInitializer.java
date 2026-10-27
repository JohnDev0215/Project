package org.apache.commons.lang3.concurrent;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyInitializer<T> implements ConcurrentInitializer<T> {
    private static final Object NO_INIT = new Object();
    private volatile T object = (T) NO_INIT;

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() {
        T tInitialize = this.object;
        if (tInitialize == NO_INIT) {
            synchronized (this) {
                tInitialize = this.object;
                if (tInitialize == NO_INIT) {
                    tInitialize = initialize();
                    this.object = tInitialize;
                }
            }
        }
        return tInitialize;
    }

    protected abstract T initialize();
}
