package X5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: X5.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0872m0 extends s7.i implements InterfaceC2786o {
    public /* synthetic */ long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ t6.h f10318m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ D0 f10319n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0872m0(D0 d02, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.f10319n = d02;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        long jLongValue = ((Number) obj).longValue();
        C0872m0 c0872m0 = new C0872m0(this.f10319n, (InterfaceC2189c) obj3);
        c0872m0.l = jLongValue;
        c0872m0.f10318m = (t6.h) obj2;
        return c0872m0.invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        long j3 = this.l;
        t6.h hVar = this.f10318m;
        if (hVar == null) {
            return Boolean.FALSE;
        }
        x6.e eVar = this.f10319n.f10111G;
        A7.m.f(eVar, "timingConfig");
        return Boolean.valueOf(((long) ((eVar.o + eVar.f23815h) + hVar.d.f22475a)) <= j3);
    }
}
