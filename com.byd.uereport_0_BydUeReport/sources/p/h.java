package p;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import o0.x;
import o0.y;
import o0.z;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f6953c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y f6954d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6955e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f6952b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final z f6956f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<x> f6951a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    public class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6957a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f6958b = 0;

        public a() {
        }

        @Override // o0.y
        public void b(View view) {
            int i8 = this.f6958b + 1;
            this.f6958b = i8;
            if (i8 == h.this.f6951a.size()) {
                y yVar = h.this.f6954d;
                if (yVar != null) {
                    yVar.b(null);
                }
                d();
            }
        }

        @Override // o0.z, o0.y
        public void c(View view) {
            if (this.f6957a) {
                return;
            }
            this.f6957a = true;
            y yVar = h.this.f6954d;
            if (yVar != null) {
                yVar.c(null);
            }
        }

        public void d() {
            this.f6958b = 0;
            this.f6957a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f6955e) {
            Iterator<x> it = this.f6951a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f6955e = false;
        }
    }

    public void b() {
        this.f6955e = false;
    }

    public h c(x xVar) {
        if (!this.f6955e) {
            this.f6951a.add(xVar);
        }
        return this;
    }

    public h d(x xVar, x xVar2) {
        this.f6951a.add(xVar);
        xVar2.h(xVar.c());
        this.f6951a.add(xVar2);
        return this;
    }

    public h e(long j8) {
        if (!this.f6955e) {
            this.f6952b = j8;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f6955e) {
            this.f6953c = interpolator;
        }
        return this;
    }

    public h g(y yVar) {
        if (!this.f6955e) {
            this.f6954d = yVar;
        }
        return this;
    }

    public void h() {
        if (this.f6955e) {
            return;
        }
        for (x xVar : this.f6951a) {
            long j8 = this.f6952b;
            if (j8 >= 0) {
                xVar.d(j8);
            }
            Interpolator interpolator = this.f6953c;
            if (interpolator != null) {
                xVar.e(interpolator);
            }
            if (this.f6954d != null) {
                xVar.f(this.f6956f);
            }
            xVar.j();
        }
        this.f6955e = true;
    }
}
