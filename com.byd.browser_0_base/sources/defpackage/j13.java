package defpackage;

import android.os.Handler;
import defpackage.vu2;

/* JADX INFO: compiled from: TabLoadTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class j13 {
    public final c b;
    public boolean c;
    public boolean d;
    public final Handler a = new Handler();
    public final a e = new a();
    public final b f = new b();

    /* JADX INFO: compiled from: TabLoadTracker.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            j13 j13Var = j13.this;
            j13Var.c = false;
            ((h61) vu2.this.a).T(null);
        }
    }

    /* JADX INFO: compiled from: TabLoadTracker.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            j13 j13Var = j13.this;
            j13Var.d = false;
            ((h61) vu2.this.a).T(null);
        }
    }

    /* JADX INFO: compiled from: TabLoadTracker.java */
    public interface c {
    }

    public j13(int i, vu2.f fVar) {
        this.b = fVar;
    }
}
