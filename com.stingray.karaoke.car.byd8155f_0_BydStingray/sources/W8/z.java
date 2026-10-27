package W8;

import Q5.C0576h2;
import Q5.C0602o0;
import S8.AbstractC0727y;
import V8.InterfaceC0818g;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import s7.InterfaceC2411d;

/* JADX INFO: loaded from: classes.dex */
public final class z extends AbstractC2410c implements InterfaceC0818g {
    public final InterfaceC0818g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2194h f9944m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f9945n;
    public InterfaceC2194h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC2189c f9946p;

    public z(InterfaceC0818g interfaceC0818g, InterfaceC2194h interfaceC2194h) {
        super(x.l, C2195i.l);
        this.l = interfaceC0818g;
        this.f9944m = interfaceC2194h;
        this.f9945n = ((Number) interfaceC2194h.j(0, new C0576h2(6))).intValue();
    }

    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) {
        try {
            Object objM = m(interfaceC2189c, obj);
            return objM == EnumC2303a.l ? objM : m7.q.f19267a;
        } catch (Throwable th) {
            this.o = new t(th, interfaceC2189c.getContext());
            throw th;
        }
    }

    @Override // s7.AbstractC2408a, s7.InterfaceC2411d
    public final InterfaceC2411d getCallerFrame() {
        InterfaceC2189c interfaceC2189c = this.f9946p;
        if (interfaceC2189c instanceof InterfaceC2411d) {
            return (InterfaceC2411d) interfaceC2189c;
        }
        return null;
    }

    @Override // s7.AbstractC2410c, q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        InterfaceC2194h interfaceC2194h = this.o;
        return interfaceC2194h == null ? C2195i.l : interfaceC2194h;
    }

    @Override // s7.AbstractC2408a
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        Throwable thA = m7.l.a(obj);
        if (thA != null) {
            this.o = new t(thA, getContext());
        }
        InterfaceC2189c interfaceC2189c = this.f9946p;
        if (interfaceC2189c != null) {
            interfaceC2189c.resumeWith(obj);
        }
        return EnumC2303a.l;
    }

    public final Object m(InterfaceC2189c interfaceC2189c, Object obj) {
        InterfaceC2194h context = interfaceC2189c.getContext();
        AbstractC0727y.k(context);
        InterfaceC2194h interfaceC2194h = this.o;
        if (interfaceC2194h != context) {
            if (interfaceC2194h instanceof t) {
                throw new IllegalStateException(Q8.i.q0("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((t) interfaceC2194h).f9941m + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
            }
            if (((Number) context.j(0, new C0602o0(this, 2))).intValue() != this.f9945n) {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.f9944m + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
            this.o = context;
        }
        this.f9946p = interfaceC2189c;
        A a10 = B.f9904a;
        InterfaceC0818g interfaceC0818g = this.l;
        A7.m.d(interfaceC0818g, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        a10.getClass();
        Object objF = interfaceC0818g.f(obj, this);
        if (!A7.m.a(objF, EnumC2303a.l)) {
            this.f9946p = null;
        }
        return objF;
    }
}
