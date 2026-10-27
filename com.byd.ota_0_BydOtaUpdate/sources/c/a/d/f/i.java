package c.a.d.f;

import android.content.Context;
import java.util.Objects;

/* JADX INFO: compiled from: OffPowerLevelImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2454a = "i";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f2455b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f2456c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.a.a f2457d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.g.c f2458e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.a.d.a f2459f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.g.d f2460g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.b.c.a f2461h = c.a.b.c.a.k();

    public i(Context context, c.a.a.a aVar, c.a.g.c cVar, c.a.d.a aVar2, c.a.g.d dVar) {
        this.f2456c = context;
        this.f2457d = aVar;
        this.f2458e = cVar;
        this.f2459f = aVar2;
        this.f2460g = dVar;
    }

    public void a() {
        String str = f2454a;
        c.a.b.d.e.c(str, "dealOffPowerLevel");
        if (this.f2457d.b()) {
            return;
        }
        c.a.b.d.e.c(str, "dealOffPowerLevel,isShowDialog = " + f2455b);
        if (f2455b) {
            f2455b = false;
        } else {
            this.f2459f.n();
        }
        c.a.b.d.e.c(str, "dealOffPowerLevel,spData.getUpdateFlag() = " + this.f2461h.w());
        if (!this.f2461h.w() || this.f2461h.b() > 0) {
            return;
        }
        int iS = this.f2461h.s();
        c.a.b.d.e.c(str, "dealOffPowerLevel,showCount = " + iS);
        if (iS < 10) {
            this.f2461h.W(iS + 1);
            c.a.b.d.l lVarD = c.a.b.d.l.d();
            c.a.d.a aVar = this.f2459f;
            Objects.requireNonNull(aVar);
            lVarD.b(new a(aVar));
        }
    }

    public void b(boolean z) {
        f2455b = z;
    }
}
