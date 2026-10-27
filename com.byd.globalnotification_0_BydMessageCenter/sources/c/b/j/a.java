package c.b.j;

import android.os.Looper;
import androidx.recyclerview.widget.RecyclerView;
import c.b.c;
import c.b.f.d;
import c.b.h.b.b;
import e.a.a.c;
import e.a.a.e;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static c a(Callable<c> callable) {
        try {
            c cVarCall = callable.call();
            b.a(cVarCall, "Scheduler Callable result can't be null");
            return cVarCall;
        } catch (Throwable th) {
            throw c.b.h.f.b.a(th);
        }
    }

    public static void b() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("You must only modify the ObservableList on the main thread.");
        }
    }

    public static void c(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else {
            boolean z = true;
            if (!(th instanceof c.b.f.b) && !(th instanceof IllegalStateException) && !(th instanceof NullPointerException) && !(th instanceof IllegalArgumentException) && !(th instanceof c.b.f.a)) {
                z = false;
            }
            if (!z) {
                th = new d(th);
            }
        }
        th.printStackTrace();
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }

    public static <T> void d(RecyclerView recyclerView, e<? super T> eVar, List<T> list, e.a.a.c<T> cVar, c.InterfaceC0059c<? super T> interfaceC0059c, c.d dVar, a.p.e.b<T> bVar) {
        e.a.a.c cVar2 = (e.a.a.c) recyclerView.getAdapter();
        e.a.a.c cVar3 = cVar2 == null ? new e.a.a.c() : cVar2;
        cVar3.f2519c = eVar;
        cVar3.j(list);
        if (cVar3.g != null) {
            cVar3.g = null;
            if (cVar3.f1636a.a()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            cVar3.f1637b = false;
        }
        cVar3.h = null;
        if (cVar2 != cVar3) {
            recyclerView.setAdapter(cVar3);
        }
    }
}
