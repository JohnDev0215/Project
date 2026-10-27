package i;

import android.view.View;
import android.view.animation.Interpolator;
import i0.a0;
import i0.y;
import i0.z;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f6818c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    z f6819d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f6820e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f6817b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final a0 f6821f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<y> f6816a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    class a extends a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f6822a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f6823b = 0;

        a() {
        }

        @Override // i0.z
        public void a(View view) {
            int i7 = this.f6823b + 1;
            this.f6823b = i7;
            if (i7 == h.this.f6816a.size()) {
                z zVar = h.this.f6819d;
                if (zVar != null) {
                    zVar.a(null);
                }
                d();
            }
        }

        @Override // i0.a0, i0.z
        public void b(View view) {
            if (this.f6822a) {
                return;
            }
            this.f6822a = true;
            z zVar = h.this.f6819d;
            if (zVar != null) {
                zVar.b(null);
            }
        }

        void d() {
            this.f6823b = 0;
            this.f6822a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f6820e) {
            Iterator<y> it = this.f6816a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f6820e = false;
        }
    }

    void b() {
        this.f6820e = false;
    }

    public h c(y yVar) {
        if (!this.f6820e) {
            this.f6816a.add(yVar);
        }
        return this;
    }

    public h d(y yVar, y yVar2) {
        this.f6816a.add(yVar);
        yVar2.h(yVar.c());
        this.f6816a.add(yVar2);
        return this;
    }

    public h e(long j7) {
        if (!this.f6820e) {
            this.f6817b = j7;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f6820e) {
            this.f6818c = interpolator;
        }
        return this;
    }

    public h g(z zVar) {
        if (!this.f6820e) {
            this.f6819d = zVar;
        }
        return this;
    }

    public void h() {
        if (this.f6820e) {
            return;
        }
        for (y yVar : this.f6816a) {
            long j7 = this.f6817b;
            if (j7 >= 0) {
                yVar.d(j7);
            }
            Interpolator interpolator = this.f6818c;
            if (interpolator != null) {
                yVar.e(interpolator);
            }
            if (this.f6819d != null) {
                yVar.f(this.f6821f);
            }
            yVar.j();
        }
        this.f6820e = true;
    }
}
