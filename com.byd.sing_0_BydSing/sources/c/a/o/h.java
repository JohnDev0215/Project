package c.a.o;

import android.view.View;
import android.view.animation.Interpolator;
import c.g.k.x;
import c.g.k.y;
import c.g.k.z;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f2057c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    y f2058d;
    private boolean e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2056b = -1;
    private final z f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<x> f2055a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2059a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2060b = 0;

        a() {
        }

        @Override // c.g.k.y
        public void b(View view) {
            int i = this.f2060b + 1;
            this.f2060b = i;
            if (i == h.this.f2055a.size()) {
                y yVar = h.this.f2058d;
                if (yVar != null) {
                    yVar.b(null);
                }
                d();
            }
        }

        @Override // c.g.k.z, c.g.k.y
        public void c(View view) {
            if (this.f2059a) {
                return;
            }
            this.f2059a = true;
            y yVar = h.this.f2058d;
            if (yVar != null) {
                yVar.c(null);
            }
        }

        void d() {
            this.f2060b = 0;
            this.f2059a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.e) {
            Iterator<x> it = this.f2055a.iterator();
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
            this.f2055a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f2055a.add(xVar);
        xVar2.h(xVar.c());
        this.f2055a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.e) {
            this.f2056b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.e) {
            this.f2057c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.e) {
            this.f2058d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.e) {
            return;
        }
        for (x xVar : this.f2055a) {
            long j = this.f2056b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f2057c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f2058d != null) {
                xVar.f(this.f);
            }
            xVar.j();
        }
        this.e = true;
    }
}
