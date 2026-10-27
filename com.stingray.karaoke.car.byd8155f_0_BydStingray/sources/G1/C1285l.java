package g1;

import T.InterfaceC0769u0;
import android.os.Handler;
import e0.C1184x;
import java.util.ArrayList;

/* JADX INFO: renamed from: g1.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1285l implements InterfaceC0769u0 {
    public final C1283j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Handler f14732m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1184x f14733n = new C1184x(new C1284k(this, 0));
    public boolean o = true;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C1284k f14734p = new C1284k(this, 1);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList f14735q = new ArrayList();

    public C1285l(C1283j c1283j) {
        this.l = c1283j;
    }

    @Override // T.InterfaceC0769u0
    public final void a() {
        this.f14733n.e();
    }

    @Override // T.InterfaceC0769u0
    public final void c() {
    }

    @Override // T.InterfaceC0769u0
    public final void d() {
        C1184x c1184x = this.f14733n;
        G2.a aVar = c1184x.f13750g;
        if (aVar != null) {
            aVar.k();
        }
        c1184x.b();
    }
}
