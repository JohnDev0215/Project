package k6;

import V8.InterfaceC0818g;
import V8.f0;
import q7.InterfaceC2189c;

/* JADX INFO: renamed from: k6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1693a implements InterfaceC0818g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1696d f18241m;

    public /* synthetic */ C1693a(C1696d c1696d, int i7) {
        this.l = i7;
        this.f18241m = c1696d;
    }

    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) {
        switch (this.l) {
            case 0:
                Boolean bool = (Boolean) obj;
                bool.getClass();
                f0 f0Var = this.f18241m.f18259r;
                f0Var.getClass();
                f0Var.m(null, bool);
                break;
            default:
                C1696d.f18245s = (String) obj;
                C1696d c1696d = this.f18241m;
                c1696d.f18255m.l(c1696d.f18246a.a());
                if (c1696d.f18248e) {
                    J5.h hVar = c1696d.f18247b;
                    hVar.d(null, true);
                    hVar.b();
                    c1696d.f18248e = false;
                }
                break;
        }
        return m7.q.f19267a;
    }
}
