package defpackage;

import android.util.Pair;
import java.util.LinkedList;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: ChainedTasks.java */
/* JADX INFO: loaded from: classes.dex */
public final class xm {
    public final LinkedList<Pair<d73, Runnable>> a = new LinkedList<>();
    public volatile boolean b;
    public final a c;

    /* JADX INFO: compiled from: ChainedTasks.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            xm.this.getClass();
            Object obj = PostTask.a;
            if (xm.this.b) {
                return;
            }
            Pair<d73, Runnable> pairPop = xm.this.a.pop();
            TraceEvent traceEventH0 = TraceEvent.H0("ChainedTask.run: ".concat(((Runnable) pairPop.second).getClass().getName()), null);
            try {
                ((Runnable) pairPop.second).run();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                if (xm.this.a.isEmpty()) {
                    return;
                }
                PostTask.c((d73) xm.this.a.peek().first, this);
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
    }

    public xm() {
        Object obj = PostTask.a;
        this.c = new a();
    }

    public final void a(d73 d73Var, Runnable runnable) {
        synchronized (this.a) {
            this.a.add(new Pair<>(d73Var, runnable));
        }
    }

    public final void b(boolean z) {
        synchronized (this.a) {
        }
        if (this.a.isEmpty()) {
            return;
        }
        if (z) {
            PostTask.d((d73) this.a.peek().first, new androidx.appcompat.widget.a(7, this));
        } else {
            PostTask.c((d73) this.a.peek().first, this.c);
        }
    }
}
