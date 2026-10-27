package T7;

import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: T7.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0796o implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0797p f8856m;

    public /* synthetic */ C0796o(C0797p c0797p, int i7) {
        this.l = i7;
        this.f8856m = c0797p;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                C2061f c2061f = (C2061f) obj;
                C0797p c0797p = this.f8856m;
                if (c2061f != null) {
                    return c0797p.j(c2061f, c0797p.i().a(c2061f, Y7.b.f10801q));
                }
                c0797p.getClass();
                C0797p.h(8);
                throw null;
            default:
                C2061f c2061f2 = (C2061f) obj;
                C0797p c0797p2 = this.f8856m;
                if (c2061f2 != null) {
                    return c0797p2.j(c2061f2, c0797p2.i().b(c2061f2, Y7.b.f10801q));
                }
                c0797p2.getClass();
                C0797p.h(4);
                throw null;
        }
    }
}
