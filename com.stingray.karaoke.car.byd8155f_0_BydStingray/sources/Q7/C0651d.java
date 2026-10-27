package Q7;

import java.util.List;
import o8.C2061f;

/* JADX INFO: renamed from: Q7.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0651d implements Q {
    public final Q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC0655h f7182m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f7183n;

    public C0651d(Q q4, InterfaceC0655h interfaceC0655h, int i7) {
        A7.m.f(interfaceC0655h, "declarationDescriptor");
        this.l = q4;
        this.f7182m = interfaceC0655h;
        this.f7183n = i7;
    }

    @Override // Q7.InterfaceC0654g
    public final F8.K C() {
        return this.l.C();
    }

    @Override // Q7.Q
    public final E8.o D() {
        return this.l.D();
    }

    @Override // Q7.Q
    public final boolean Q() {
        return true;
    }

    @Override // Q7.Q
    public final boolean R() {
        return this.l.R();
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return this.l.S(interfaceC0659l, obj);
    }

    @Override // Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0654g b1() {
        return this.l.b1();
    }

    @Override // Q7.Q
    public final int e0() {
        return this.l.e0();
    }

    @Override // Q7.InterfaceC0658k
    public final N g() {
        return this.l.g();
    }

    @Override // Q7.Q
    public final int getIndex() {
        return this.l.getIndex() + this.f7183n;
    }

    @Override // Q7.InterfaceC0657j
    public final C2061f getName() {
        return this.l.getName();
    }

    @Override // Q7.Q
    public final List getUpperBounds() {
        return this.l.getUpperBounds();
    }

    @Override // Q7.InterfaceC0654g
    public final F8.A j() {
        return this.l.j();
    }

    @Override // R7.a
    public final R7.h k() {
        return this.l.k();
    }

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        return this.f7182m;
    }

    public final String toString() {
        return this.l + "[inner-copy]";
    }

    @Override // Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0657j b1() {
        return this.l.b1();
    }

    @Override // Q7.Q, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final Q b1() {
        return this.l.b1();
    }
}
