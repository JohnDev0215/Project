package g9;

import B8.t;
import c9.InterfaceC1048i;
import c9.w;
import h6.q;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Runnable {
    public final InterfaceC1048i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile AtomicInteger f15054m = new AtomicInteger(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j f15055n;

    public g(j jVar, InterfaceC1048i interfaceC1048i) {
        this.f15055n = jVar;
        this.l = interfaceC1048i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        t tVar;
        String strConcat = "OkHttp ".concat(((w) this.f15055n.f15060m.f2765m).g());
        j jVar = this.f15055n;
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(strConcat);
        try {
            jVar.f15063q.i();
            boolean z6 = false;
            try {
                try {
                } catch (Throwable th) {
                    jVar.l.l.O(this);
                    throw th;
                }
            } catch (IOException e10) {
                e = e10;
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.l.j(jVar, jVar.h());
                tVar = jVar.l.l;
            } catch (IOException e11) {
                e = e11;
                z6 = true;
                if (z6) {
                    l9.o oVar = l9.o.f18874a;
                    l9.o oVar2 = l9.o.f18874a;
                    String str = "Callback failure for " + j.a(jVar);
                    oVar2.getClass();
                    l9.o.i(str, 4, e);
                } else {
                    this.l.r(jVar, e);
                }
                tVar = jVar.l.l;
            } catch (Throwable th3) {
                th = th3;
                z6 = true;
                jVar.d();
                if (!z6) {
                    IOException iOException = new IOException("canceled due to " + th);
                    q.l(iOException, th);
                    this.l.r(jVar, iOException);
                }
                throw th;
            }
            tVar.O(this);
        } finally {
            threadCurrentThread.setName(name);
        }
    }
}
