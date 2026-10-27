package f9;

import A7.m;
import B5.RunnableC0079c;
import e7.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c f14651h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Logger f14652i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f14653a;
    public boolean c;
    public long d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f14654b = 10000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f14655e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f14656f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final RunnableC0079c f14657g = new RunnableC0079c(this, 18);

    static {
        String str = d9.b.f13650h + " TaskRunner";
        m.f(str, "name");
        f14651h = new c(new f(new d9.a(str, true)));
        Logger logger = Logger.getLogger(c.class.getName());
        m.e(logger, "getLogger(TaskRunner::class.java.name)");
        f14652i = logger;
    }

    public c(f fVar) {
        this.f14653a = fVar;
    }

    public static final void a(c cVar, a aVar) {
        cVar.getClass();
        byte[] bArr = d9.b.f13645a;
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(aVar.f14645a);
        try {
            long jA = aVar.a();
            synchronized (cVar) {
                cVar.b(aVar, jA);
            }
            threadCurrentThread.setName(name);
        } catch (Throwable th) {
            synchronized (cVar) {
                cVar.b(aVar, -1L);
                threadCurrentThread.setName(name);
                throw th;
            }
        }
    }

    public final void b(a aVar, long j3) {
        byte[] bArr = d9.b.f13645a;
        b bVar = aVar.c;
        m.c(bVar);
        if (bVar.d != aVar) {
            throw new IllegalStateException("Check failed.");
        }
        boolean z6 = bVar.f14650f;
        bVar.f14650f = false;
        bVar.d = null;
        this.f14655e.remove(bVar);
        if (j3 != -1 && !z6 && !bVar.c) {
            bVar.e(aVar, j3, true);
        }
        if (bVar.f14649e.isEmpty()) {
            return;
        }
        this.f14656f.add(bVar);
    }

    public final a c() {
        boolean z6;
        c cVar = this;
        byte[] bArr = d9.b.f13645a;
        while (true) {
            ArrayList arrayList = cVar.f14656f;
            if (arrayList.isEmpty()) {
                return null;
            }
            f fVar = cVar.f14653a;
            long jNanoTime = System.nanoTime();
            Iterator it = arrayList.iterator();
            long jMin = Long.MAX_VALUE;
            a aVar = null;
            while (true) {
                if (!it.hasNext()) {
                    cVar = this;
                    z6 = false;
                    break;
                }
                a aVar2 = (a) ((b) it.next()).f14649e.get(0);
                long jMax = Math.max(0L, aVar2.d - jNanoTime);
                if (jMax > 0) {
                    jMin = Math.min(jMax, jMin);
                } else {
                    if (aVar != null) {
                        cVar = this;
                        z6 = true;
                        break;
                    }
                    aVar = aVar2;
                }
            }
            ArrayList arrayList2 = cVar.f14655e;
            if (aVar != null) {
                byte[] bArr2 = d9.b.f13645a;
                aVar.d = -1L;
                b bVar = aVar.c;
                m.c(bVar);
                bVar.f14649e.remove(aVar);
                arrayList.remove(bVar);
                bVar.d = aVar;
                arrayList2.add(bVar);
                if (z6 || (!cVar.c && !arrayList.isEmpty())) {
                    RunnableC0079c runnableC0079c = cVar.f14657g;
                    m.f(runnableC0079c, "runnable");
                    ((ThreadPoolExecutor) fVar.f14480m).execute(runnableC0079c);
                }
                return aVar;
            }
            if (cVar.c) {
                if (jMin < cVar.d - jNanoTime) {
                    notify();
                }
                return null;
            }
            cVar.c = true;
            cVar.d = jNanoTime + jMin;
            try {
                try {
                    long j3 = jMin / 1000000;
                    long j10 = jMin - (1000000 * j3);
                    if (j3 > 0 || jMin > 0) {
                        cVar.wait(j3, (int) j10);
                    }
                } catch (InterruptedException unused) {
                    for (int size = arrayList2.size() - 1; -1 < size; size--) {
                        ((b) arrayList2.get(size)).b();
                    }
                    int i7 = -1;
                    for (int size2 = arrayList.size() - 1; i7 < size2; size2--) {
                        b bVar2 = (b) arrayList.get(size2);
                        bVar2.b();
                        if (bVar2.f14649e.isEmpty()) {
                            arrayList.remove(size2);
                        }
                        i7 = -1;
                    }
                }
            } finally {
                cVar.c = false;
            }
        }
    }

    public final void d(b bVar) {
        m.f(bVar, "taskQueue");
        byte[] bArr = d9.b.f13645a;
        if (bVar.d == null) {
            boolean zIsEmpty = bVar.f14649e.isEmpty();
            ArrayList arrayList = this.f14656f;
            if (zIsEmpty) {
                arrayList.remove(bVar);
            } else {
                m.f(arrayList, "<this>");
                if (!arrayList.contains(bVar)) {
                    arrayList.add(bVar);
                }
            }
        }
        boolean z6 = this.c;
        f fVar = this.f14653a;
        if (z6) {
            notify();
            return;
        }
        RunnableC0079c runnableC0079c = this.f14657g;
        m.f(runnableC0079c, "runnable");
        ((ThreadPoolExecutor) fVar.f14480m).execute(runnableC0079c);
    }

    public final b e() {
        int i7;
        synchronized (this) {
            i7 = this.f14654b;
            this.f14654b = i7 + 1;
        }
        return new b(this, android.hardware.bydauto.ac.tsimpl.a.e(i7, "Q"));
    }
}
