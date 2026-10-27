package A3;

import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import V8.f0;
import q7.InterfaceC2189c;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public final class n implements InterfaceC0817f {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f0 f396m;

    public /* synthetic */ n(f0 f0Var, int i7) {
        this.l = i7;
        this.f396m = f0Var;
    }

    @Override // V8.InterfaceC0817f
    public final Object b(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) throws Throwable {
        switch (this.l) {
            case 0:
                this.f396m.b(new m(interfaceC0818g, 0), interfaceC2189c);
                break;
            case 1:
                this.f396m.b(new m(interfaceC0818g, 1), interfaceC2189c);
                break;
            case 2:
                this.f396m.b(new m(interfaceC0818g, 21), interfaceC2189c);
                break;
            default:
                this.f396m.b(new m(interfaceC0818g, 22), interfaceC2189c);
                break;
        }
        return EnumC2303a.l;
    }
}
