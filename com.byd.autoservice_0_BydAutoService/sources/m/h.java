package m;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import l0.x;
import l0.y;
import l0.z;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f3787c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y f3788d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3789e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f3786b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final z f3790f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<x> f3785a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    public class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3791a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3792b = 0;

        public a() {
        }

        @Override // l0.y
        public void a(View view) {
            int i3 = this.f3792b + 1;
            this.f3792b = i3;
            if (i3 == h.this.f3785a.size()) {
                y yVar = h.this.f3788d;
                if (yVar != null) {
                    yVar.a(null);
                }
                d();
            }
        }

        @Override // l0.z, l0.y
        public void b(View view) {
            if (this.f3791a) {
                return;
            }
            this.f3791a = true;
            y yVar = h.this.f3788d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        public void d() {
            this.f3792b = 0;
            this.f3791a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f3789e) {
            Iterator<x> it = this.f3785a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f3789e = false;
        }
    }

    public void b() {
        this.f3789e = false;
    }

    public h c(x xVar) {
        if (!this.f3789e) {
            this.f3785a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f3785a.add(xVar);
        xVar2.h(xVar.c());
        this.f3785a.add(xVar2);
        return this;
    }

    public h e(long j3) {
        if (!this.f3789e) {
            this.f3786b = j3;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f3789e) {
            this.f3787c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.f3789e) {
            this.f3788d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.f3789e) {
            return;
        }
        for (x xVar : this.f3785a) {
            long j3 = this.f3786b;
            if (j3 >= 0) {
                xVar.d(j3);
            }
            Interpolator interpolator = this.f3787c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f3788d != null) {
                xVar.f(this.f3790f);
            }
            xVar.j();
        }
        this.f3789e = true;
    }
}
