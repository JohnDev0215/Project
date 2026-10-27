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
    private Interpolator f2071c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    y f2072d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2073e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2070b = -1;
    private final z f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<x> f2069a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2074a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2075b = 0;

        a() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            int i = this.f2075b + 1;
            this.f2075b = i;
            if (i == h.this.f2069a.size()) {
                y yVar = h.this.f2072d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            if (this.f2074a) {
                return;
            }
            this.f2074a = true;
            y yVar = h.this.f2072d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        void d() {
            this.f2075b = 0;
            this.f2074a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f2073e) {
            Iterator<x> it = this.f2069a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f2073e = false;
        }
    }

    void b() {
        this.f2073e = false;
    }

    public h c(x xVar) {
        if (!this.f2073e) {
            this.f2069a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f2069a.add(xVar);
        xVar2.h(xVar.c());
        this.f2069a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.f2073e) {
            this.f2070b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f2073e) {
            this.f2071c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.f2073e) {
            this.f2072d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.f2073e) {
            return;
        }
        for (x xVar : this.f2069a) {
            long j = this.f2070b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f2071c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f2072d != null) {
                xVar.f(this.f);
            }
            xVar.j();
        }
        this.f2073e = true;
    }
}
