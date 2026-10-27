package f9;

import A7.m;
import e7.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import l9.d;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f14647a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14648b;
    public boolean c;
    public a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f14649e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f14650f;

    public b(c cVar, String str) {
        m.f(cVar, "taskRunner");
        m.f(str, "name");
        this.f14647a = cVar;
        this.f14648b = str;
        this.f14649e = new ArrayList();
    }

    public final void a() {
        byte[] bArr = d9.b.f13645a;
        synchronized (this.f14647a) {
            if (b()) {
                this.f14647a.d(this);
            }
        }
    }

    public final boolean b() {
        a aVar = this.d;
        if (aVar != null && aVar.f14646b) {
            this.f14650f = true;
        }
        ArrayList arrayList = this.f14649e;
        boolean z6 = false;
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            if (((a) arrayList.get(size)).f14646b) {
                a aVar2 = (a) arrayList.get(size);
                if (c.f14652i.isLoggable(Level.FINE)) {
                    d.A(aVar2, this, "canceled");
                }
                arrayList.remove(size);
                z6 = true;
            }
        }
        return z6;
    }

    public final void c(a aVar, long j3) {
        m.f(aVar, "task");
        synchronized (this.f14647a) {
            if (!this.c) {
                if (e(aVar, j3, false)) {
                    this.f14647a.d(this);
                }
            } else if (aVar.f14646b) {
                if (c.f14652i.isLoggable(Level.FINE)) {
                    d.A(aVar, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                if (c.f14652i.isLoggable(Level.FINE)) {
                    d.A(aVar, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    public final boolean e(a aVar, long j3, boolean z6) {
        m.f(aVar, "task");
        b bVar = aVar.c;
        if (bVar != this) {
            if (bVar != null) {
                throw new IllegalStateException("task is in multiple queues");
            }
            aVar.c = this;
        }
        f fVar = this.f14647a.f14653a;
        long jNanoTime = System.nanoTime();
        long j10 = jNanoTime + j3;
        ArrayList arrayList = this.f14649e;
        int iIndexOf = arrayList.indexOf(aVar);
        if (iIndexOf != -1) {
            if (aVar.d <= j10) {
                if (c.f14652i.isLoggable(Level.FINE)) {
                    d.A(aVar, this, "already scheduled");
                }
                return false;
            }
            arrayList.remove(iIndexOf);
        }
        aVar.d = j10;
        if (c.f14652i.isLoggable(Level.FINE)) {
            d.A(aVar, this, z6 ? "run again after ".concat(d.R(j10 - jNanoTime)) : "scheduled after ".concat(d.R(j10 - jNanoTime)));
        }
        Iterator it = arrayList.iterator();
        int size = 0;
        while (true) {
            if (!it.hasNext()) {
                size = -1;
                break;
            }
            if (((a) it.next()).d - jNanoTime > j3) {
                break;
            }
            size++;
        }
        if (size == -1) {
            size = arrayList.size();
        }
        arrayList.add(size, aVar);
        return size == 0;
    }

    public final void f() {
        byte[] bArr = d9.b.f13645a;
        synchronized (this.f14647a) {
            this.c = true;
            if (b()) {
                this.f14647a.d(this);
            }
        }
    }

    public final String toString() {
        return this.f14648b;
    }
}
