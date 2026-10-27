package defpackage;

import J.N;
import android.os.Process;
import android.util.Pair;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: mv, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1187mv implements InterfaceC1020jv {
    public static final ReferenceQueue j = new ReferenceQueue();
    public static final Set k = new HashSet();
    public final C1242nv a;
    public final String b;
    public final int c;
    public volatile long d;
    public final Runnable e = new Runnable(this) { // from class: kv
        public final C1187mv s;

        {
            this.s = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.s.g();
        }
    };
    public final Object f = new Object();
    public boolean g;
    public LinkedList h;
    public List i;

    public C1187mv(C1242nv c1242nv, String str, int i) {
        this.a = c1242nv.d();
        this.b = Z7.c(str, ".PreNativeTask.run");
        this.c = i;
    }

    public static void d() {
        while (true) {
            C1132lv c1132lv = (C1132lv) j.poll();
            if (c1132lv == null) {
                return;
            }
            N.MERCiIV8(c1132lv.a);
            synchronized (k) {
                k.remove(c1132lv);
            }
        }
    }

    @Override // defpackage.InterfaceC1020jv
    public void a(Runnable runnable) {
        c(runnable, 0L);
    }

    @Override // defpackage.InterfaceC1020jv
    public void c(Runnable runnable, long j2) {
        if (this.d != 0) {
            N.MGnQU$47(this.d, runnable, j2);
            return;
        }
        synchronized (this.f) {
            f();
            if (this.d != 0) {
                N.MGnQU$47(this.d, runnable, j2);
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

    public void e() {
        int i = this.c;
        C1242nv c1242nv = this.a;
        long jM5_IQXaH = N.M5_IQXaH(i, c1242nv.a, c1242nv.b, c1242nv.c, c1242nv.d, c1242nv.e);
        synchronized (this.f) {
            if (this.h != null) {
                Iterator it = this.h.iterator();
                while (it.hasNext()) {
                    N.MGnQU$47(jM5_IQXaH, (Runnable) it.next(), 0L);
                }
                this.h = null;
            }
            if (this.i != null) {
                for (Pair pair : this.i) {
                    N.MGnQU$47(jM5_IQXaH, (Runnable) pair.first, ((Long) pair.second).longValue());
                }
                this.i = null;
            }
            this.d = jM5_IQXaH;
        }
        synchronized (k) {
            k.add(new C1132lv(this));
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
            if (PostTask.b == null) {
                z = false;
            } else {
                PostTask.b.add(this);
            }
        }
        if (!z) {
            e();
        } else {
            this.h = new LinkedList();
            this.i = new ArrayList();
        }
    }

    public void g() {
        TraceEvent traceEventM = TraceEvent.M(this.b);
        try {
            synchronized (this.f) {
                if (this.h == null) {
                    if (traceEventM != null) {
                        traceEventM.close();
                        return;
                    }
                    return;
                }
                Runnable runnable = (Runnable) this.h.poll();
                int i = this.a.a;
                if (i == 1) {
                    Process.setThreadPriority(0);
                } else if (i != 2) {
                    Process.setThreadPriority(10);
                } else {
                    Process.setThreadPriority(-1);
                }
                runnable.run();
                if (traceEventM != null) {
                    traceEventM.close();
                }
            }
        } catch (Throwable th) {
            if (traceEventM != null) {
                try {
                    traceEventM.close();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
            }
            throw th;
        }
    }

    public void h() {
        PostTask.d.execute(this.e);
    }
}
