package s8;

import A7.k;
import A7.m;
import F8.AbstractC0287w;
import F8.O;
import F8.T;
import Q7.InterfaceC0654g;
import Q7.Q;
import R7.h;
import h6.n;

/* JADX INFO: renamed from: s8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2416d extends T {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final T f22379b;
    public final /* synthetic */ boolean c;

    public C2416d(T t10, boolean z6) {
        this.c = z6;
        this.f22379b = t10;
    }

    @Override // F8.T
    public final boolean a() {
        return this.f22379b.a();
    }

    @Override // F8.T
    public final boolean b() {
        return this.c;
    }

    @Override // F8.T
    public final h c(h hVar) {
        m.f(hVar, "annotations");
        return this.f22379b.c(hVar);
    }

    @Override // F8.T
    public final O d(AbstractC0287w abstractC0287w) {
        O oD = this.f22379b.d(abstractC0287w);
        if (oD == null) {
            return null;
        }
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        return n.h(oD, interfaceC0654gN instanceof Q ? (Q) interfaceC0654gN : null);
    }

    @Override // F8.T
    public final boolean e() {
        return this.f22379b.e();
    }

    @Override // F8.T
    public final AbstractC0287w f(int i7, AbstractC0287w abstractC0287w) {
        m.f(abstractC0287w, "topLevelType");
        k.p(i7, "position");
        return this.f22379b.f(i7, abstractC0287w);
    }
}
