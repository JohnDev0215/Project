package a.a.n;

import a.g.k.x;
import a.g.k.y;
import a.g.k.z;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f91c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    y f92d;
    private boolean e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f90b = -1;
    private final z f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<x> f89a = new ArrayList<>();

    class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f93a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f94b = 0;

        a() {
        }

        @Override // a.g.k.y
        public void a(View view) {
            int i = this.f94b + 1;
            this.f94b = i;
            if (i == h.this.f89a.size()) {
                y yVar = h.this.f92d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // a.g.k.z, a.g.k.y
        public void b(View view) {
            if (this.f93a) {
                return;
            }
            this.f93a = true;
            y yVar = h.this.f92d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        void d() {
            this.f94b = 0;
            this.f93a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.e) {
            Iterator<x> it = this.f89a.iterator();
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
            this.f89a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f89a.add(xVar);
        xVar2.h(xVar.c());
        this.f89a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.e) {
            this.f90b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.e) {
            this.f91c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.e) {
            this.f92d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.e) {
            return;
        }
        for (x xVar : this.f89a) {
            long j = this.f90b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f91c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f92d != null) {
                xVar.f(this.f);
            }
            xVar.j();
        }
        this.e = true;
    }
}
