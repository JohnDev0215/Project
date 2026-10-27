package h6;

import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import T.S;
import k6.C1705m;
import kotlin.Metadata;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lh6/p;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class p extends b6.f {
    public final C1705m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f15595m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f15596n;
    public final C0738e0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(C1705m c1705m, W5.e eVar) {
        super(c1705m, eVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        this.l = c1705m;
        this.f15595m = "https://legal.stingray.com/%s/in-car-eula/markdown";
        this.f15596n = "en";
        this.o = C0735d.O("", S.f8224q);
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        AbstractC0727y.w(this.f12236f, null, null, new o(this, null), 3);
        return m7.q.f19267a;
    }

    @Override // b6.f
    public final void g() {
        i(true);
    }
}
