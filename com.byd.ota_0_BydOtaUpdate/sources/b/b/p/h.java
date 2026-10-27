package b.b.p;

import android.view.View;
import android.view.animation.Interpolator;
import b.f.m.w;
import b.f.m.x;
import b.f.m.y;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f900c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public x f901d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f902e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f899b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final y f903f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<w> f898a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    public class a extends y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f904a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f905b = 0;

        public a() {
        }

        @Override // b.f.m.x
        public void a(View view) {
            int i2 = this.f905b + 1;
            this.f905b = i2;
            if (i2 == h.this.f898a.size()) {
                x xVar = h.this.f901d;
                if (xVar != null) {
                    xVar.a(null);
                }
                d();
            }
        }

        @Override // b.f.m.y, b.f.m.x
        public void b(View view) {
            if (this.f904a) {
                return;
            }
            this.f904a = true;
            x xVar = h.this.f901d;
            if (xVar != null) {
                xVar.b(null);
            }
        }

        public void d() {
            this.f905b = 0;
            this.f904a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f902e) {
            Iterator<w> it = this.f898a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f902e = false;
        }
    }

    public void b() {
        this.f902e = false;
    }

    public h c(w wVar) {
        if (!this.f902e) {
            this.f898a.add(wVar);
        }
        return this;
    }

    public h d(w wVar, w wVar2) {
        this.f898a.add(wVar);
        wVar2.h(wVar.c());
        this.f898a.add(wVar2);
        return this;
    }

    public h e(long j) {
        if (!this.f902e) {
            this.f899b = j;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f902e) {
            this.f900c = interpolator;
        }
        return this;
    }

    public h g(x xVar) {
        if (!this.f902e) {
            this.f901d = xVar;
        }
        return this;
    }

    public void h() {
        if (this.f902e) {
            return;
        }
        for (w wVar : this.f898a) {
            long j = this.f899b;
            if (j >= 0) {
                wVar.d(j);
            }
            Interpolator interpolator = this.f900c;
            if (interpolator != null) {
                wVar.e(interpolator);
            }
            if (this.f901d != null) {
                wVar.f(this.f903f);
            }
            wVar.j();
        }
        this.f902e = true;
    }
}
