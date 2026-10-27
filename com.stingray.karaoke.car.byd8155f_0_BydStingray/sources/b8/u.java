package B8;

import B5.K;
import Q7.N;
import T.C0746i0;
import a.AbstractC0904a;
import j8.C1565j;
import j8.EnumC1564i;
import l8.AbstractC1787e;
import l8.InterfaceC1788f;
import o8.C2057b;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class u extends K {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1565j f810e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u f811f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C2057b f812g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final EnumC1564i f813h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f814i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(C1565j c1565j, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, N n10, u uVar) {
        super(interfaceC1788f, c0746i0, n10);
        A7.m.f(c1565j, "classProto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        this.f810e = c1565j;
        this.f811f = uVar;
        this.f812g = AbstractC0904a.D(interfaceC1788f, c1565j.f17395p);
        EnumC1564i enumC1564i = (EnumC1564i) AbstractC1787e.f18842f.c(c1565j.o);
        this.f813h = enumC1564i == null ? EnumC1564i.CLASS : enumC1564i;
        this.f814i = AbstractC1787e.f18843g.c(c1565j.o).booleanValue();
    }

    @Override // B5.K
    public final C2058c b() {
        return this.f812g.b();
    }
}
