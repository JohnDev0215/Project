package x;

import T.C0738e0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: x.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2628c0 extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2634f0 f23489m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f23490n;
    public final /* synthetic */ x0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2628c0(C2634f0 c2634f0, Object obj, x0 x0Var, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f23489m = c2634f0;
        this.f23490n = obj;
        this.o = x0Var;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C2628c0) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C2628c0(this.f23489m, this.f23490n, this.o, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        x0 x0Var = this.o;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2634f0 c2634f0 = this.f23489m;
            c2634f0.g1();
            c2634f0.f23528x = Long.MIN_VALUE;
            c2634f0.j1(0.0f);
            Object value = c2634f0.o.getValue();
            Object obj2 = this.f23490n;
            boolean zEquals = obj2.equals(value);
            C0738e0 c0738e0 = c2634f0.f23519n;
            float f10 = zEquals ? -4.0f : obj2.equals(c0738e0.getValue()) ? -5.0f : -3.0f;
            x0Var.q(obj2);
            x0Var.o(0L);
            c0738e0.setValue(obj2);
            c2634f0.j1(0.0f);
            c2634f0.W0(obj2);
            x0Var.j(f10);
            if (f10 == -3.0f) {
                this.l = 1;
                if (C2634f0.e1(c2634f0, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        x0Var.i();
        return m7.q.f19267a;
    }
}
