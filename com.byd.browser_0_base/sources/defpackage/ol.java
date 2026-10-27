package defpackage;

import defpackage.AbstractC0208s;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CallbackToFutureAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class ol<T> implements c91<T> {
    public final WeakReference<ml<T>> e;
    public final a f = new a();

    /* JADX INFO: compiled from: CallbackToFutureAdapter.java */
    public class a extends AbstractC0208s<T> {
        public a() {
        }

        @Override // defpackage.AbstractC0208s
        public final String e() {
            ml<T> mlVar = ol.this.e.get();
            if (mlVar == null) {
                return "Completer object has been garbage collected, future will fail soon";
            }
            return "tag=[" + mlVar.a + "]";
        }
    }

    public ol(ml<T> mlVar) {
        this.e = new WeakReference<>(mlVar);
    }

    @Override // defpackage.c91
    public final void a(Runnable runnable, Executor executor) {
        this.f.a(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        ml<T> mlVar = this.e.get();
        boolean zCancel = this.f.cancel(z);
        if (zCancel && mlVar != null) {
            mlVar.a = null;
            mlVar.b = null;
            mlVar.c.g(null);
        }
        return zCancel;
    }

    @Override // java.util.concurrent.Future
    public final T get() {
        return this.f.get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f.e instanceof AbstractC0208s.b;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f.isDone();
    }

    public final String toString() {
        return this.f.toString();
    }

    @Override // java.util.concurrent.Future
    public final T get(long j, TimeUnit timeUnit) {
        return this.f.get(j, timeUnit);
    }
}
