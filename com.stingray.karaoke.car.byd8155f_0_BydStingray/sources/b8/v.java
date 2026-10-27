package B8;

import B5.K;
import T.C0746i0;
import h8.C1391h;
import l8.InterfaceC1788f;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class v extends K {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2058c f815e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(C2058c c2058c, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, C1391h c1391h) {
        super(interfaceC1788f, c0746i0, c1391h);
        A7.m.f(c2058c, "fqName");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        this.f815e = c2058c;
    }

    @Override // B5.K
    public final C2058c b() {
        return this.f815e;
    }
}
