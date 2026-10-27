package O;

import E0.AbstractC0156f;
import K.InterfaceC0355n0;
import N1.C0420a;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: O.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0487s extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4991m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0420a f4992n;
    public final /* synthetic */ InterfaceC0355n0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0487s(C0420a c0420a, InterfaceC0355n0 interfaceC0355n0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4992n = c0420a;
        this.o = interfaceC0355n0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0487s c0487s = new C0487s(this.f4992n, this.o, interfaceC2189c);
        c0487s.f4991m = obj;
        return c0487s;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0487s) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2722B c2722b = (C2722B) this.f4991m;
            c2722b.getClass();
            r rVar = new r(this.f4992n, new B5.u(AbstractC0156f.t(c2722b).f1466E), this.o, null);
            this.l = 1;
            if (C7.a.w(c2722b, rVar, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
