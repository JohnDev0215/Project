package b.b.p;

import android.view.View;
import android.view.animation.Interpolator;
import b.h.m.x;
import b.h.m.y;
import b.h.m.z;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f1571c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y f1572d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1573e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1570b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final z f1574f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<x> f1569a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    public class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1575a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1576b = 0;

        public a() {
        }

        @Override // b.h.m.y
        public void a(View view) {
            int i2 = this.f1576b + 1;
            this.f1576b = i2;
            if (i2 == h.this.f1569a.size()) {
                y yVar = h.this.f1572d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // b.h.m.z, b.h.m.y
        public void b(View view) {
            if (this.f1575a) {
                return;
            }
            this.f1575a = true;
            y yVar = h.this.f1572d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        public void d() {
            this.f1576b = 0;
            this.f1575a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f1573e) {
            Iterator<x> it = this.f1569a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f1573e = false;
        }
    }

    public void b() {
        this.f1573e = false;
    }

    public h c(x xVar) {
        if (!this.f1573e) {
            this.f1569a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f1569a.add(xVar);
        xVar2.h(xVar.c());
        this.f1569a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.f1573e) {
            this.f1570b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f1573e) {
            this.f1571c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.f1573e) {
            this.f1572d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.f1573e) {
            return;
        }
        for (x xVar : this.f1569a) {
            long j = this.f1570b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f1571c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f1572d != null) {
                xVar.f(this.f1574f);
            }
            xVar.j();
        }
        this.f1573e = true;
    }
}
