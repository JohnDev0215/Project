package X5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: X5.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0866j0 extends s7.i implements InterfaceC2786o {
    public /* synthetic */ long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ long f10276m;

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        long jLongValue = ((Number) obj).longValue();
        long jLongValue2 = ((Number) obj2).longValue();
        C0866j0 c0866j0 = new C0866j0(3, (InterfaceC2189c) obj3);
        c0866j0.l = jLongValue;
        c0866j0.f10276m = jLongValue2;
        return c0866j0.invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        long j3 = this.l;
        long j10 = this.f10276m;
        return new Float(j10 > 0 ? j3 / j10 : 0.0f);
    }
}
