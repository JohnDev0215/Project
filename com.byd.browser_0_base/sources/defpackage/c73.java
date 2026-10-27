package defpackage;

import J.N;
import android.os.Process;
import android.util.Pair;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: TaskRunnerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c73 implements b73 {
    public static final ReferenceQueue<Object> j = new ReferenceQueue<>();
    public static final HashSet k = new HashSet();
    public final d73 a;
    public final String b;
    public final int c;
    public volatile long d;
    public final androidx.appcompat.widget.a e = new androidx.appcompat.widget.a(8, this);
    public final Object f = new Object();
    public boolean g;
    public LinkedList<Runnable> h;
    public ArrayList i;

    /* JADX INFO: compiled from: TaskRunnerImpl.java */
    public static class a extends WeakReference<c73> {
        public final long a;

        public a(c73 c73Var) {
            super(c73Var, c73.j);
            this.a = c73Var.d;
        }
    }

    public c73(d73 d73Var, String str, int i) {
        if (!d73Var.c) {
            if (!(d73Var.d != 0)) {
                d73 d73Var2 = new d73(d73Var);
                d73Var2.c = true;
                d73Var = d73Var2;
            }
        }
        this.a = d73Var;
        this.b = str.concat(".PreNativeTask.run");
        this.c = i;
    }

    public static void d() {
        while (true) {
            a aVar = (a) j.poll();
            if (aVar == null) {
                return;
            }
            N.MERCiIV8(aVar.a);
            HashSet hashSet = k;
            synchronized (hashSet) {
                hashSet.remove(aVar);
            }
        }
    }

    @Override // defpackage.b73
    public final void a(Runnable runnable, long j2) {
        if (this.d != 0) {
            N.MGnQU$47(this.d, runnable, j2, runnable.getClass().getName());
            return;
        }
        synchronized (this.f) {
            f();
            if (this.d != 0) {
                N.MGnQU$47(this.d, runnable, j2, runnable.getClass().getName());
                return;
            }
            if (j2 == 0) {
                this.h.add(runnable);
                h();
            } else {
                this.i.add(new Pair(runnable, Long.valueOf(j2)));
            }
        }
    }

    @Override // defpackage.b73
    public final void b(Runnable runnable) {
        a(runnable, 0L);
    }

    public void e() {
        int i = this.c;
        d73 d73Var = this.a;
        long jM5_IQXaH = N.M5_IQXaH(i, d73Var.a, d73Var.b, d73Var.c, d73Var.d, d73Var.e);
        synchronized (this.f) {
            try {
                LinkedList<Runnable> linkedList = this.h;
                if (linkedList != null) {
                    for (Runnable runnable : linkedList) {
                        N.MGnQU$47(jM5_IQXaH, runnable, 0L, runnable.getClass().getName());
                    }
                    this.h = null;
                }
                ArrayList<Pair> arrayList = this.i;
                if (arrayList != null) {
                    for (Pair pair : arrayList) {
                        N.MGnQU$47(jM5_IQXaH, (Runnable) pair.first, ((Long) pair.second).longValue(), pair.getClass().getName());
                    }
                    this.i = null;
                }
                this.d = jM5_IQXaH;
            } catch (Throwable th) {
                throw th;
            }
        }
        HashSet hashSet = k;
        synchronized (hashSet) {
            hashSet.add(new a(this));
        }
        d();
    }

    public final void f() {
        if (this.g) {
            return;
        }
        boolean z = true;
        this.g = true;
        synchronized (PostTask.a) {
            ArrayList arrayList = PostTask.b;
            if (arrayList == null) {
                z = false;
            } else {
                arrayList.add(this);
            }
        }
        if (!z) {
            e();
        } else {
            this.h = new LinkedList<>();
            this.i = new ArrayList();
        }
    }

    public void g() {
        TraceEvent traceEventH0 = TraceEvent.H0(this.b, null);
        try {
            synchronized (this.f) {
                LinkedList<Runnable> linkedList = this.h;
                if (linkedList == null) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                }
                Runnable runnablePoll = linkedList.poll();
                int i = this.a.a;
                if (i == 1) {
                    Process.setThreadPriority(0);
                } else if (i != 2) {
                    Process.setThreadPriority(10);
                } else {
                    Process.setThreadPriority(-1);
                }
                runnablePoll.run();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public void h() {
        PostTask.d.execute(this.e);
    }
}
