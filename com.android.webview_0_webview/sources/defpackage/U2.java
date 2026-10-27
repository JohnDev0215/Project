package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class U2 implements Runnable {
    public final /* synthetic */ AtomicReference s;
    public final /* synthetic */ Callable t;
    public final /* synthetic */ ReentrantLock u;
    public final /* synthetic */ AtomicBoolean v;
    public final /* synthetic */ Condition w;

    public U2(W2 w2, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
        this.s = atomicReference;
        this.t = callable;
        this.u = reentrantLock;
        this.v = atomicBoolean;
        this.w = condition;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.s.set(this.t.call());
        } catch (Exception unused) {
        }
        this.u.lock();
        try {
            this.v.set(false);
            this.w.signal();
        } finally {
            this.u.unlock();
        }
    }
}
