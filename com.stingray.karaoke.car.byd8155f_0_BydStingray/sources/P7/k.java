package P7;

import Q7.InterfaceC0671y;
import T7.B;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class k extends B {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f5442s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(InterfaceC0671y interfaceC0671y, C2058c c2058c, int i7) {
        super(interfaceC0671y, c2058c);
        this.f5442s = i7;
        switch (i7) {
            case 1:
                A7.m.f(interfaceC0671y, "module");
                A7.m.f(c2058c, "fqName");
                super(interfaceC0671y, c2058c);
                break;
            default:
                break;
        }
    }

    @Override // Q7.D
    public final /* bridge */ /* synthetic */ y8.n z0() {
        switch (this.f5442s) {
        }
        return y8.m.f24213b;
    }
}
