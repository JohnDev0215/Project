package x;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: x.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2624a0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f23479m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f23480n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C2634f0 f23481p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ x0 f23482q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ float f23483r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2624a0(Object obj, Object obj2, C2634f0 c2634f0, x0 x0Var, float f10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23480n = obj;
        this.o = obj2;
        this.f23481p = c2634f0;
        this.f23482q = x0Var;
        this.f23483r = f10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C2624a0 c2624a0 = new C2624a0(this.f23480n, this.o, this.f23481p, this.f23482q, this.f23483r, interfaceC2189c);
        c2624a0.f23479m = obj;
        return c2624a0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2624a0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        C2634f0 c2634f0 = this.f23481p;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f23479m;
            Object obj2 = this.f23480n;
            Object obj3 = this.o;
            if (A7.m.a(obj2, obj3)) {
                c2634f0.f23530z = null;
                if (A7.m.a(c2634f0.o.getValue(), obj2)) {
                    return qVar;
                }
            } else {
                C2634f0.a1(c2634f0);
            }
            boolean zA = A7.m.a(obj2, obj3);
            float f10 = this.f23483r;
            if (!zA) {
                x0 x0Var = this.f23482q;
                x0Var.q(obj2);
                x0Var.o(0L);
                c2634f0.f23519n.setValue(obj2);
                x0Var.j(f10);
            }
            c2634f0.j1(f10);
            if (c2634f0.f23529y.f22638b != 0) {
                AbstractC0727y.w(interfaceC0725w, null, null, new Z(c2634f0, null), 3);
            } else {
                c2634f0.f23528x = Long.MIN_VALUE;
            }
            this.l = 1;
            if (C2634f0.e1(c2634f0, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        c2634f0.i1();
        return qVar;
    }
}
