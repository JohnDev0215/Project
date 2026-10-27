package X5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import g6.C1308f;
import g6.C1310h;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class T0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V0 f10202m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f10203n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T0(V0 v02, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10202m = v02;
        this.f10203n = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new T0(this.f10202m, this.f10203n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((T0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        V0 v02 = this.f10202m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                C1310h c1310h = v02.c;
                Object value = ((V8.f0) v02.f10206a.f4637P.l).getValue();
                A7.m.c(value);
                String str = ((N5.f0) value).f4691a.f13136a;
                A7.m.c(str);
                this.l = 1;
                c1310h.getClass();
                Z8.e eVar = S8.E.f8084a;
                obj = AbstractC0727y.F(Z8.d.f11010n, new C1308f(c1310h, str, null), this);
                if (obj == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            v02.l = null;
            v02.f10214k.l((n6.w) obj);
        } catch (Exception unused) {
            v02.c(this.f10203n);
        }
        return m7.q.f19267a;
    }
}
