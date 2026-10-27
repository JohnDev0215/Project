package h2;

import android.os.Looper;
import android.os.Message;
import e2.C1206n;
import e2.U;
import java.util.ArrayDeque;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f15222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x f15223b;
    public final m c;
    public final CopyOnWriteArraySet d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayDeque f15224e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayDeque f15225f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f15226g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f15227h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f15228i;

    public o(Looper looper, v vVar, m mVar) {
        this(new CopyOnWriteArraySet(), looper, vVar, mVar, true);
    }

    public final void a(Object obj) {
        obj.getClass();
        synchronized (this.f15226g) {
            try {
                if (this.f15227h) {
                    return;
                }
                this.d.add(new n(obj));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b() {
        g();
        ArrayDeque arrayDeque = this.f15225f;
        if (arrayDeque.isEmpty()) {
            return;
        }
        x xVar = this.f15223b;
        if (!xVar.f15248a.hasMessages(1)) {
            xVar.getClass();
            w wVarB = x.b();
            wVarB.f15246a = xVar.f15248a.obtainMessage(1);
            xVar.getClass();
            Message message = wVarB.f15246a;
            message.getClass();
            xVar.f15248a.sendMessageAtFrontOfQueue(message);
            wVarB.a();
        }
        ArrayDeque arrayDeque2 = this.f15224e;
        boolean zIsEmpty = arrayDeque2.isEmpty();
        arrayDeque2.addAll(arrayDeque);
        arrayDeque.clear();
        if (zIsEmpty) {
            while (!arrayDeque2.isEmpty()) {
                ((Runnable) arrayDeque2.peekFirst()).run();
                arrayDeque2.removeFirst();
            }
        }
    }

    public final void c(int i7, l lVar) {
        g();
        this.f15225f.add(new h.k(new CopyOnWriteArraySet(this.d), i7, lVar, 2));
    }

    public final void d() {
        g();
        synchronized (this.f15226g) {
            this.f15227h = true;
        }
        for (n nVar : this.d) {
            m mVar = this.c;
            nVar.d = true;
            if (nVar.c) {
                nVar.c = false;
                mVar.c(nVar.f15220a, nVar.f15221b.l());
            }
        }
        this.d.clear();
    }

    public final void e(U u4) {
        g();
        CopyOnWriteArraySet<n> copyOnWriteArraySet = this.d;
        for (n nVar : copyOnWriteArraySet) {
            if (nVar.f15220a.equals(u4)) {
                nVar.d = true;
                if (nVar.c) {
                    nVar.c = false;
                    C1206n c1206nL = nVar.f15221b.l();
                    this.c.c(nVar.f15220a, c1206nL);
                }
                copyOnWriteArraySet.remove(nVar);
            }
        }
    }

    public final void f(int i7, l lVar) {
        c(i7, lVar);
        b();
    }

    public final void g() {
        if (this.f15228i) {
            AbstractC1346c.j(Thread.currentThread() == this.f15223b.f15248a.getLooper().getThread());
        }
    }

    public o(CopyOnWriteArraySet copyOnWriteArraySet, Looper looper, v vVar, m mVar, boolean z6) {
        this.f15222a = vVar;
        this.d = copyOnWriteArraySet;
        this.c = mVar;
        this.f15226g = new Object();
        this.f15224e = new ArrayDeque();
        this.f15225f = new ArrayDeque();
        this.f15223b = vVar.a(looper, new k(this, 0));
        this.f15228i = z6;
    }
}
