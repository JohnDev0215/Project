package d.a.p.c;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ScalarCallable.java */
/* JADX INFO: loaded from: classes.dex */
public interface c<T> extends Callable<T> {
    @Override // java.util.concurrent.Callable
    T call();
}
