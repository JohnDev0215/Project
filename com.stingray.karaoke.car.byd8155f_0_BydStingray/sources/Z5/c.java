package Z5;

import A7.m;
import J5.h;
import N5.Z;
import S8.AbstractC0727y;
import W5.e;
import b6.f;
import g6.C1310h;
import k6.C1705m;
import kotlin.Metadata;
import m7.q;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"LZ5/c;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class c extends f {
    public final h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Z f10902m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(C1705m c1705m, e eVar, h hVar, Z z6, C1310h c1310h) {
        super(c1705m, eVar);
        m.f(c1705m, "languageManager");
        m.f(eVar, "appNavigationManager");
        m.f(hVar, "loginManager");
        m.f(z6, "playerManager");
        m.f(c1310h, "scoringTestRepoManager");
        this.l = hVar;
        this.f10902m = z6;
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        AbstractC0727y.w(this.f12236f, null, null, new b(this, null), 3);
        return q.f19267a;
    }
}
