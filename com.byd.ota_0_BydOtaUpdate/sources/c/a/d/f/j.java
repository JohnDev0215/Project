package c.a.d.f;

import android.content.Context;
import android.text.TextUtils;
import c.a.b.d.n;
import c.a.g.l.p;
import c.a.g.l.r;
import java.util.Objects;

/* JADX INFO: compiled from: OnPowerLevelImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2462a = "j";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f2463b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f2464c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.a.a f2465d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.g.c f2466e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.a.d.a f2467f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.g.d f2468g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.b.c.a f2469h = c.a.b.c.a.k();

    public j(Context context, c.a.a.a aVar, c.a.g.c cVar, c.a.d.a aVar2, c.a.g.d dVar) {
        this.f2464c = context;
        this.f2465d = aVar;
        this.f2466e = cVar;
        this.f2467f = aVar2;
        this.f2468g = dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c() {
        String str = f2462a;
        c.a.b.d.e.c(str, "report result");
        if (c.a.g.l.d.b()) {
            c.a.b.d.e.c(str, "downloadReport");
            this.f2466e.l(c.a.g.l.h.f(c.a.g.l.d.a()));
        }
        if (p.c()) {
            c.a.b.d.e.c(str, "submitReport");
            this.f2467f.C(true);
            this.f2466e.j(c.a.g.l.h.f(p.a()));
        }
        if (c.a.g.l.j.a()) {
            c.a.b.d.e.c(str, "reportMessageResult otadiagnose");
            this.f2466e.h("otadiagnose");
        }
        if (c.a.g.l.j.b()) {
            c.a.b.d.e.c(str, "reportMessageResult otaupgrade");
            this.f2466e.h("otaupgrade");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void e() {
        this.f2467f.i0(c.a.g.l.l.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void g(int i2) {
        this.f2467f.s(i2);
    }

    public void a() {
        int iT;
        String str = f2462a;
        c.a.b.d.e.c(str, "dealOnPowerLevel");
        if (this.f2465d.b()) {
            return;
        }
        if (!this.f2469h.w()) {
            this.f2465d.m(false, "dealOnPowerLevel getUpdateFlag false");
        } else if (this.f2469h.b() == 0) {
            if (this.f2469h.s() < 10) {
                this.f2465d.m(true, "dealOnPowerLevel showCountWhenCarOff");
            } else {
                this.f2465d.m(false, "dealOnPowerLevel not showCountWhenCarOff");
            }
        }
        if (c.a.b.d.p.a(this.f2464c) && !r.g()) {
            c.a.b.d.l.d().a(new Runnable() { // from class: c.a.d.f.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2424c.c();
                }
            });
        }
        final int iN = this.f2469h.n();
        c.a.b.d.e.c(str, "normalCode = " + iN);
        if (iN != -1) {
            f2463b = false;
            this.f2469h.R(-1);
            if (iN == 10000) {
                c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.f.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2427c.e();
                    }
                });
                return;
            } else {
                c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.f.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2425c.g(iN);
                    }
                });
                return;
            }
        }
        if (f2463b) {
            f2463b = false;
        } else {
            this.f2467f.n();
        }
        String strP = this.f2469h.p();
        String strB = n.b("yyyy-MM-dd");
        c.a.b.d.e.c(str, "real savedTime = " + strP);
        if (TextUtils.isEmpty(strP)) {
            this.f2469h.T(strB);
            strP = strB;
        }
        c.a.b.d.e.c(str, "savedTime = " + strP + ", currentTime = " + strB);
        if (!strP.equals(strB)) {
            if (c.a.g.l.l.f()) {
                return;
            }
            this.f2469h.T(strB);
            if (!c.a.b.d.p.a(this.f2464c) || this.f2466e.n()) {
                return;
            }
            this.f2466e.i(true);
            this.f2466e.d();
            return;
        }
        if (!this.f2469h.w()) {
            if (c.a.g.l.l.f() || !c.a.b.d.p.a(this.f2464c) || this.f2466e.n()) {
                return;
            }
            this.f2466e.i(true);
            this.f2466e.g();
            return;
        }
        if (this.f2469h.b() <= 0 && (iT = this.f2469h.t()) < 2) {
            this.f2469h.X(iT + 1);
            c.a.b.d.l lVarD = c.a.b.d.l.d();
            c.a.d.a aVar = this.f2467f;
            Objects.requireNonNull(aVar);
            lVarD.b(new a(aVar));
        }
    }

    public void h(boolean z) {
        f2463b = z;
    }
}
