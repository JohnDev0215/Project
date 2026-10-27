package z8;

import A7.m;
import F8.AbstractC0287w;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0658k;
import T7.AbstractC0795n;
import o8.C2061f;

/* JADX INFO: renamed from: z8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2793a extends A9.b implements InterfaceC2796d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f24418n = 1;
    public final C2061f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final InterfaceC0658k f24419p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C2793a(InterfaceC0649b interfaceC0649b, AbstractC0287w abstractC0287w, C2061f c2061f) {
        super(abstractC0287w);
        m.f(abstractC0287w, "receiverType");
        this.f24419p = (AbstractC0795n) interfaceC0649b;
        this.o = c2061f;
    }

    public final C2061f a1() {
        switch (this.f24418n) {
        }
        return this.o;
    }

    @Override // A9.b
    public final String toString() {
        switch (this.f24418n) {
            case 0:
                return b() + ": Ctx { " + ((InterfaceC0652e) this.f24419p) + " }";
            default:
                return "Cxt { " + ((AbstractC0795n) this.f24419p) + " }";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2793a(InterfaceC0652e interfaceC0652e, AbstractC0287w abstractC0287w, C2061f c2061f) {
        super(abstractC0287w);
        m.f(abstractC0287w, "receiverType");
        this.f24419p = interfaceC0652e;
        this.o = c2061f;
    }
}
