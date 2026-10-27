package T;

import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class N0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f8208m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2194h f8209n;
    public final /* synthetic */ V8.O o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public N0(InterfaceC2194h interfaceC2194h, V8.O o, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f8209n = interfaceC2194h;
        this.o = o;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        N0 n02 = new N0(this.f8209n, this.o, interfaceC2189c);
        n02.f8208m = obj;
        return n02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((N0) create((C0748j0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0748j0 c0748j0 = (C0748j0) this.f8208m;
            C2195i c2195i = C2195i.l;
            InterfaceC2194h interfaceC2194h = this.f8209n;
            boolean zA = A7.m.a(interfaceC2194h, c2195i);
            V8.O o = this.o;
            if (zA) {
                L0 l02 = new L0(c0748j0, 0);
                this.l = 1;
                if (o.b(l02, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                M0 m02 = new M0(o, c0748j0, null);
                this.l = 2;
                if (AbstractC0727y.F(interfaceC2194h, m02, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
        } else {
            if (i7 != 1 && i7 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
