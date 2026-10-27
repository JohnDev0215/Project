package O;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: O.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0474e extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0480k f4958m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f4959n;
    public final /* synthetic */ boolean o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0474e(InterfaceC0480k interfaceC0480k, boolean z6, boolean z10) {
        super(1);
        this.f4958m = interfaceC0480k;
        this.f4959n = z6;
        this.o = z10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        L0.j jVar = (L0.j) obj;
        long jA = this.f4958m.a();
        jVar.l(x.c, new w(this.f4959n ? K.O.f3190m : K.O.f3191n, jA, this.o ? 1 : 3, h6.e.m(jA)));
        return m7.q.f19267a;
    }
}
