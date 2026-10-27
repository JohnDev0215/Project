package O3;

import a4.AbstractC0922c;
import a4.ThreadFactoryC0923d;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ExecutorService f5139e = Executors.newCachedThreadPool(new ThreadFactoryC0923d());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f5140a = new LinkedHashSet(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashSet f5141b = new LinkedHashSet(1);
    public final Handler c = new Handler(Looper.getMainLooper());
    public volatile o d = null;

    public q(a aVar) {
        d(new o(aVar));
    }

    public final synchronized void a(n nVar) {
        Throwable th;
        try {
            o oVar = this.d;
            if (oVar != null && (th = oVar.f5138b) != null) {
                nVar.a(th);
            }
            this.f5141b.add(nVar);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final synchronized void b(n nVar) {
        a aVar;
        try {
            o oVar = this.d;
            if (oVar != null && (aVar = oVar.f5137a) != null) {
                nVar.a(aVar);
            }
            this.f5140a.add(nVar);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void c() {
        o oVar = this.d;
        if (oVar == null) {
            return;
        }
        a aVar = oVar.f5137a;
        if (aVar != null) {
            synchronized (this) {
                Iterator it = new ArrayList(this.f5140a).iterator();
                while (it.hasNext()) {
                    ((n) it.next()).a(aVar);
                }
            }
            return;
        }
        Throwable th = oVar.f5138b;
        synchronized (this) {
            ArrayList arrayList = new ArrayList(this.f5141b);
            if (arrayList.isEmpty()) {
                AbstractC0922c.b("Lottie encountered an error but no failure listener was added:", th);
                return;
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((n) it2.next()).a(th);
            }
        }
    }

    public final void d(o oVar) {
        if (this.d != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.d = oVar;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            c();
        } else {
            this.c.post(new A2.l(this, 5));
        }
    }

    public q(Callable callable) {
        ExecutorService executorService = f5139e;
        p pVar = new p(callable);
        pVar.l = this;
        executorService.execute(pVar);
    }
}
