package X5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: X5.n0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0874n0 extends s7.i implements InterfaceC2786o {
    public /* synthetic */ long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ boolean f10342m;

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        long jLongValue = ((Number) obj).longValue();
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        C0874n0 c0874n0 = new C0874n0(3, (InterfaceC2189c) obj3);
        c0874n0.l = jLongValue;
        c0874n0.f10342m = zBooleanValue;
        return c0874n0.invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        long j3 = this.l;
        return Boolean.valueOf(1 <= j3 && j3 < 3001 && this.f10342m);
    }
}
