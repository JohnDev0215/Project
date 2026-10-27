package x;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: x.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2633f extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f23512m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2629d f23513n;
    public final /* synthetic */ T.X o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ T.X f23514p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2633f(Object obj, C2629d c2629d, T.X x8, T.X x9, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23512m = obj;
        this.f23513n = c2629d;
        this.o = x8;
        this.f23514p = x9;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C2633f(this.f23512m, this.f23513n, this.o, this.f23514p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2633f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C2629d c2629d = this.f23513n;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            if (!A7.m.a(this.f23512m, c2629d.f23493e.getValue())) {
                C2640i0 c2640i0 = AbstractC2637h.f23537a;
                InterfaceC2647m interfaceC2647m = (InterfaceC2647m) this.o.getValue();
                this.l = 1;
                if (C2629d.c(this.f23513n, this.f23512m, interfaceC2647m, null, this, 12) == enumC2303a) {
                    return enumC2303a;
                }
            }
            return m7.q.f19267a;
        }
        if (i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AbstractC1386c.u(obj);
        C2640i0 c2640i02 = AbstractC2637h.f23537a;
        InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f23514p.getValue();
        if (interfaceC2782k != null) {
            interfaceC2782k.b(c2629d.d());
        }
        return m7.q.f19267a;
    }
}
