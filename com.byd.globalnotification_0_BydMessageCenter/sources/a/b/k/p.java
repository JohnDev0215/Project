package a.b.k;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f48a;

    public class a extends a.h.k.u {
        public a() {
        }

        @Override // a.h.k.t
        public void a(View view) {
            p.this.f48a.o.setAlpha(1.0f);
            p.this.f48a.r.d(null);
            p.this.f48a.r = null;
        }

        @Override // a.h.k.u, a.h.k.t
        public void b(View view) {
            p.this.f48a.o.setVisibility(0);
        }
    }

    public p(l lVar) {
        this.f48a = lVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        l lVar = this.f48a;
        lVar.p.showAtLocation(lVar.o, 55, 0, 0);
        this.f48a.z();
        if (!this.f48a.M()) {
            this.f48a.o.setAlpha(1.0f);
            this.f48a.o.setVisibility(0);
            return;
        }
        this.f48a.o.setAlpha(0.0f);
        l lVar2 = this.f48a;
        a.h.k.s sVarB = a.h.k.q.b(lVar2.o);
        sVarB.a(1.0f);
        lVar2.r = sVarB;
        a.h.k.s sVar = this.f48a.r;
        a aVar = new a();
        View view = sVar.f835a.get();
        if (view != null) {
            sVar.e(view, aVar);
        }
    }
}
