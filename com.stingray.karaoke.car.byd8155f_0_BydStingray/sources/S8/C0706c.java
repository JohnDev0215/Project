package S8;

import java.util.concurrent.locks.LockSupport;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: S8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0706c extends AbstractC0704a {
    public final Thread o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final N f8107p;

    public C0706c(InterfaceC2194h interfaceC2194h, Thread thread, N n10) {
        super(interfaceC2194h, true);
        this.o = thread;
        this.f8107p = n10;
    }

    @Override // S8.d0
    public final void s(Object obj) {
        Thread threadCurrentThread = Thread.currentThread();
        Thread thread = this.o;
        if (A7.m.a(threadCurrentThread, thread)) {
            return;
        }
        LockSupport.unpark(thread);
    }
}
