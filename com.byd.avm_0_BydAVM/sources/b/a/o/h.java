package b.a.o;

import android.view.View;
import android.view.animation.Interpolator;
import b.g.k.x;
import b.g.k.y;
import b.g.k.z;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f1490c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    y f1491d;
    private boolean e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f1489b = -1;
    private final z f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<x> f1488a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1492a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1493b = 0;

        a() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            int i = this.f1493b + 1;
            this.f1493b = i;
            if (i == h.this.f1488a.size()) {
                y yVar = h.this.f1491d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            if (this.f1492a) {
                return;
            }
            this.f1492a = true;
            y yVar = h.this.f1491d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        void d() {
            this.f1493b = 0;
            this.f1492a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.e) {
            Iterator<x> it = this.f1488a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.e = false;
        }
    }

    void b() {
        this.e = false;
    }

    public h c(x xVar) {
        if (!this.e) {
            this.f1488a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f1488a.add(xVar);
        xVar2.h(xVar.c());
        this.f1488a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.e) {
            this.f1489b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.e) {
            this.f1490c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.e) {
            this.f1491d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.e) {
            return;
        }
        for (x xVar : this.f1488a) {
            long j = this.f1489b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f1490c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f1491d != null) {
                xVar.f(this.f);
            }
            xVar.j();
        }
        this.e = true;
    }
}
