package a2;

import A7.m;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.C0748j0;
import T.L0;
import V8.d0;
import h8.AbstractC1386c;
import m7.q;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: a2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0908b extends i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2194h f11047m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ d0 f11048n;
    public final /* synthetic */ C0748j0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0908b(InterfaceC2194h interfaceC2194h, d0 d0Var, C0748j0 c0748j0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f11047m = interfaceC2194h;
        this.f11048n = d0Var;
        this.o = c0748j0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0908b(this.f11047m, this.f11048n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0908b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2195i c2195i = C2195i.l;
            InterfaceC2194h interfaceC2194h = this.f11047m;
            boolean zA = m.a(interfaceC2194h, c2195i);
            C0748j0 c0748j0 = this.o;
            d0 d0Var = this.f11048n;
            if (zA) {
                L0 l02 = new L0(c0748j0, 2);
                this.l = 1;
                if (d0Var.b(l02, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                C0907a c0907a = new C0907a(d0Var, c0748j0, null);
                this.l = 2;
                if (AbstractC0727y.F(interfaceC2194h, c0907a, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
        } else {
            if (i7 != 1 && i7 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return q.f19267a;
    }
}
