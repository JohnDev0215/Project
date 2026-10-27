package defpackage;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: ListenableFuture.java */
/* JADX INFO: loaded from: classes.dex */
public interface c91<V> extends Future<V> {
    void a(Runnable runnable, Executor executor);
}
