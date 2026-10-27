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
    private Interpolator f2206c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    y f2207d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2208e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2205b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final z f2209f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<x> f2204a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2210a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2211b = 0;

        a() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            int i = this.f2211b + 1;
            this.f2211b = i;
            if (i == h.this.f2204a.size()) {
                y yVar = h.this.f2207d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            if (this.f2210a) {
                return;
            }
            this.f2210a = true;
            y yVar = h.this.f2207d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        void d() {
            this.f2211b = 0;
            this.f2210a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f2208e) {
            Iterator<x> it = this.f2204a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f2208e = false;
        }
    }

    void b() {
        this.f2208e = false;
    }

    public h c(x xVar) {
        if (!this.f2208e) {
            this.f2204a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f2204a.add(xVar);
        xVar2.h(xVar.c());
        this.f2204a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.f2208e) {
            this.f2205b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f2208e) {
            this.f2206c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.f2208e) {
            this.f2207d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.f2208e) {
            return;
        }
        for (x xVar : this.f2204a) {
            long j = this.f2205b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f2206c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f2207d != null) {
                xVar.f(this.f2209f);
            }
            xVar.j();
        }
        this.f2208e = true;
    }
}
