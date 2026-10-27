package T7;

import F8.AbstractC0287w;
import F8.W;
import Q7.C0661n;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0655h;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import o8.AbstractC2063h;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class N extends t implements M {

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public static final C f8815S;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final E8.o f8816P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final D8.v f8817Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public C0790i f8818R;

    static {
        A7.B b10 = A7.A.f443a;
        b10.h(new A7.s(b10.b(N.class), "withDispatchReceiver", "getWithDispatchReceiver()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;"));
        f8815S = new C();
    }

    public N(E8.o oVar, D8.v vVar, C0790i c0790i, M m10, R7.h hVar, int i7, Q7.N n10) {
        super(i7, vVar, m10, n10, hVar, AbstractC2063h.f20577e);
        this.f8816P = oVar;
        this.f8817Q = vVar;
        B8.A a10 = new B8.A(this, 25, c0790i);
        E8.l lVar = (E8.l) oVar;
        lVar.getClass();
        new E8.h(lVar, a10);
        this.f8818R = c0790i;
    }

    @Override // Q7.InterfaceC0656i
    public final InterfaceC0652e J() {
        InterfaceC0652e interfaceC0652eJ = this.f8818R.J();
        A7.m.e(interfaceC0652eJ, "underlyingConstructorDescriptor.constructedClass");
        return interfaceC0652eJ;
    }

    @Override // T7.t, Q7.InterfaceC0650c
    public final InterfaceC0650c a0(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n) {
        A7.m.f(interfaceC0652e, "newOwner");
        A7.k.p(i7, "modality");
        A7.m.f(c0661n, "visibility");
        A7.k.p(2, "kind");
        C0799s c0799sI1 = i1(W.f2454b);
        c0799sI1.f8871m = interfaceC0652e;
        c0799sI1.f8872n = i7;
        c0799sI1.o = c0661n;
        c0799sI1.f8874q = 2;
        c0799sI1.f8881x = false;
        h8.n nVarF1 = c0799sI1.f8870I.f1(c0799sI1);
        A7.m.d(nVarF1, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
        return (M) nVarF1;
    }

    @Override // T7.t
    public final t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, Q7.N n10, R7.h hVar, C2061f c2061f) {
        A7.m.f(interfaceC0657j, "newOwner");
        A7.k.p(i7, "kind");
        A7.m.f(hVar, "annotations");
        if (i7 != 1) {
        }
        return new N(this.f8816P, this.f8817Q, this.f8818R, this, hVar, 1, n10);
    }

    @Override // T7.AbstractC0795n
    /* JADX INFO: renamed from: n1, reason: merged with bridge method [inline-methods] */
    public final M b1() {
        InterfaceC0666t interfaceC0666tB1 = super.b1();
        A7.m.d(interfaceC0666tB1, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
        return (M) interfaceC0666tB1;
    }

    @Override // T7.AbstractC0795n, Q7.InterfaceC0657j
    public final InterfaceC0655h o() {
        return this.f8817Q;
    }

    @Override // T7.t, Q7.InterfaceC0666t, Q7.P
    /* JADX INFO: renamed from: o1, reason: merged with bridge method [inline-methods] */
    public final N f(W w10) {
        A7.m.f(w10, "substitutor");
        InterfaceC0666t interfaceC0666tF = super.f(w10);
        A7.m.d(interfaceC0666tF, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptorImpl");
        N n10 = (N) interfaceC0666tF;
        AbstractC0287w abstractC0287w = n10.f8900s;
        A7.m.c(abstractC0287w);
        C0790i c0790iF = this.f8818R.a().f(W.d(abstractC0287w));
        if (c0790iF == null) {
            return null;
        }
        n10.f8818R = c0790iF;
        return n10;
    }

    @Override // T7.t, Q7.InterfaceC0649b
    public final AbstractC0287w p() {
        AbstractC0287w abstractC0287w = this.f8900s;
        A7.m.c(abstractC0287w);
        return abstractC0287w;
    }

    @Override // T7.AbstractC0795n, Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        return this.f8817Q;
    }
}
