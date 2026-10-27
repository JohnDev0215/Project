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
    private Interpolator f2216c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    y f2217d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2218e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2215b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final z f2219f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<x> f2214a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2220a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2221b = 0;

        a() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            int i2 = this.f2221b + 1;
            this.f2221b = i2;
            if (i2 == h.this.f2214a.size()) {
                y yVar = h.this.f2217d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            if (this.f2220a) {
                return;
            }
            this.f2220a = true;
            y yVar = h.this.f2217d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        void d() {
            this.f2221b = 0;
            this.f2220a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f2218e) {
            Iterator<x> it = this.f2214a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f2218e = false;
        }
    }

    void b() {
        this.f2218e = false;
    }

    public h c(x xVar) {
        if (!this.f2218e) {
            this.f2214a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f2214a.add(xVar);
        xVar2.h(xVar.c());
        this.f2214a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.f2218e) {
            this.f2215b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f2218e) {
            this.f2216c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.f2218e) {
            this.f2217d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.f2218e) {
            return;
        }
        for (x xVar : this.f2214a) {
            long j = this.f2215b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f2216c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f2217d != null) {
                xVar.f(this.f2219f);
            }
            xVar.j();
        }
        this.f2218e = true;
    }
}
