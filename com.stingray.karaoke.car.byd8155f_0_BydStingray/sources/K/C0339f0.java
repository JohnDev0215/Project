package K;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: K.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0339f0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2722B f3252m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0355n0 f3253n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0339f0(C2722B c2722b, InterfaceC0355n0 interfaceC0355n0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3252m = c2722b;
        this.f3253n = interfaceC0355n0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0339f0(this.f3252m, this.f3253n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0339f0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i7 = 0;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i10 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i10 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            InterfaceC0355n0 interfaceC0355n0 = this.f3253n;
            C0343h0 c0343h0 = new C0343h0(interfaceC0355n0, 0);
            C0345i0 c0345i0 = new C0345i0(interfaceC0355n0, 0);
            C0345i0 c0345i02 = new C0345i0(interfaceC0355n0, 1);
            A.G0 g02 = new A.G0(interfaceC0355n0, 6);
            float f10 = A.J.f47a;
            Object objW = C7.a.w(this.f3252m, new A.H(A.G.f23m, new A7.y(), null, new A.E(c0343h0, i7), g02, c0345i02, new A.F(c0345i0, 0), null), this);
            if (objW != enumC2303a) {
                objW = qVar;
            }
            if (objW != enumC2303a) {
                objW = qVar;
            }
            if (objW != enumC2303a) {
                objW = qVar;
            }
            if (objW == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
