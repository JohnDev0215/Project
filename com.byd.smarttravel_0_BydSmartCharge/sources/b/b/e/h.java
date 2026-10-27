package b.b.e;

import android.view.View;
import android.view.animation.Interpolator;
import b.g.j.x;
import b.g.j.y;
import b.g.j.z;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f672c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y f673d;
    public boolean e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f671b = -1;
    public final z f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<x> f670a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    public class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f674a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f675b = 0;

        public a() {
        }

        @Override // b.g.j.y
        public void a(View view) {
            int i = this.f675b + 1;
            this.f675b = i;
            if (i == h.this.f670a.size()) {
                y yVar = h.this.f673d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // b.g.j.z, b.g.j.y
        public void b(View view) {
            if (this.f674a) {
                return;
            }
            this.f674a = true;
            y yVar = h.this.f673d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        public void d() {
            this.f675b = 0;
            this.f674a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.e) {
            Iterator<x> it = this.f670a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.e = false;
        }
    }

    public void b() {
        this.e = false;
    }

    public h c(x xVar) {
        if (!this.e) {
            this.f670a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f670a.add(xVar);
        xVar2.h(xVar.c());
        this.f670a.add(xVar2);
        return this;
    }

    public h e(long j) {
        if (!this.e) {
            this.f671b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.e) {
            this.f672c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.e) {
            this.f673d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.e) {
            return;
        }
        for (x xVar : this.f670a) {
            long j = this.f671b;
            if (j >= 0) {
                xVar.d(j);
            }
            Interpolator interpolator = this.f672c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f673d != null) {
                xVar.f(this.f);
            }
            xVar.j();
        }
        this.e = true;
    }
}
