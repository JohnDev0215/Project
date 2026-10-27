package androidx.lifecycle;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import a2.C0908b;
import b9.InterfaceC1014a;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class P extends s7.i implements InterfaceC2785n {
    public InterfaceC1014a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0908b f11936m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f11937n;
    public final /* synthetic */ b9.c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0908b f11938p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P(b9.c cVar, C0908b c0908b, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = cVar;
        this.f11938p = c0908b;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new P(this.o, this.f11938p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((P) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        C0908b c0908b;
        InterfaceC1014a interfaceC1014a;
        Throwable th;
        InterfaceC1014a interfaceC1014a2;
        InterfaceC1014a interfaceC1014a3;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f11937n;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                b9.c cVar = this.o;
                this.l = cVar;
                c0908b = this.f11938p;
                this.f11936m = c0908b;
                this.f11937n = 1;
                Object objC = cVar.c(null, this);
                interfaceC1014a = cVar;
                if (objC == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    if (i7 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    interfaceC1014a2 = this.l;
                    try {
                        AbstractC1386c.u(obj);
                        interfaceC1014a3 = interfaceC1014a2;
                        ((b9.c) interfaceC1014a3).e(null);
                        return m7.q.f19267a;
                    } catch (Throwable th2) {
                        th = th2;
                        ((b9.c) interfaceC1014a2).e(null);
                        throw th;
                    }
                }
                c0908b = this.f11936m;
                InterfaceC1014a interfaceC1014a4 = this.l;
                AbstractC1386c.u(obj);
                interfaceC1014a = interfaceC1014a4;
            }
            O o = new O(c0908b, null);
            this.l = interfaceC1014a;
            this.f11936m = null;
            this.f11937n = 2;
            if (AbstractC0727y.h(o, this) == enumC2303a) {
                return enumC2303a;
            }
            interfaceC1014a3 = interfaceC1014a;
            ((b9.c) interfaceC1014a3).e(null);
            return m7.q.f19267a;
        } catch (Throwable th3) {
            InterfaceC1014a interfaceC1014a5 = interfaceC1014a;
            th = th3;
            interfaceC1014a2 = interfaceC1014a5;
            ((b9.c) interfaceC1014a2).e(null);
            throw th;
        }
    }
}
