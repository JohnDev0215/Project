package rx.functions;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes6.dex */
public interface Func0<R> extends Callable<R> {
    @Override // java.util.concurrent.Callable
    R call();
}
