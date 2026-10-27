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
    private Interpolator f1689c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    y f1690d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1691e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f1688b = -1;
    private final z f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<x> f1687a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1692a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1693b = 0;

        a() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            int i = this.f1693b + 1;
            this.f1693b = i;
            if (i == h.this.f1687a.size()) {
                y yVar = h.this.f1690d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            if (this.f1692a) {
                return;
            }
            this.f1692a = true;
            y yVar = h.this.f1690d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        void d() {
            this.f1693b = 0;
            this.f1692a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f1691e) {
            Iterator<x> it = this.f1687a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f1691e = false;
        }
    }

    void b() {
        this.f1691e = false;
    }

    public h c(x xVar) {
        if (!this.f1691e) {
            this.f1687a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f1687a.add(xVar);
        xVar2.h(xVar.c());
        this.f1687a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.f1691e) {
            this.f1688b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f1691e) {
            this.f1689c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.f1691e) {
            this.f1690d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.f1691e) {
            return;
        }
        for (x xVar : this.f1687a) {
            long j = this.f1688b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f1689c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f1690d != null) {
                xVar.f(this.f);
            }
            xVar.j();
        }
        this.f1691e = true;
    }
}
