package a5;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DisposeOnCancel.java */
/* JADX INFO: loaded from: classes.dex */
final class c implements Future<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final t4.b f50e;

    c(t4.b bVar) {
        this.f50e = bVar;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z6) {
        this.f50e.dispose();
        return false;
    }

    @Override // java.util.concurrent.Future
    public Object get() {
        return null;
    }

    @Override // java.util.concurrent.Future
    public Object get(long j7, TimeUnit timeUnit) {
        return null;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return false;
    }
}
