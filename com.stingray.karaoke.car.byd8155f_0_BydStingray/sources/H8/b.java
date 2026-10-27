package H8;

import A7.k;
import A7.m;
import B2.O;
import Q7.C0661n;
import Q7.InterfaceC0648a;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0665s;
import Q7.InterfaceC0666t;
import Q7.N;
import T7.K;
import T7.t;
import java.util.Collection;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class b extends K {
    @Override // T7.t, Q7.InterfaceC0650c
    public final /* bridge */ /* synthetic */ InterfaceC0650c a0(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n) {
        a0(interfaceC0652e, i7, c0661n);
        return this;
    }

    @Override // T7.t
    /* JADX INFO: renamed from: d1 */
    public final K a0(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n) {
        m.f(interfaceC0652e, "newOwner");
        k.p(i7, "modality");
        m.f(c0661n, "visibility");
        k.p(2, "kind");
        return this;
    }

    @Override // T7.K, T7.t
    public final t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, N n10, R7.h hVar, C2061f c2061f) {
        m.f(interfaceC0657j, "newOwner");
        k.p(i7, "kind");
        m.f(hVar, "annotations");
        return this;
    }

    @Override // T7.t, Q7.InterfaceC0650c
    public final void h0(Collection collection) {
        m.f(collection, "overriddenDescriptors");
    }

    @Override // T7.t, Q7.InterfaceC0666t
    public final boolean m() {
        return false;
    }

    @Override // T7.K, T7.t, Q7.InterfaceC0666t
    public final InterfaceC0665s v0() {
        return new O(this, 18);
    }

    @Override // T7.t, Q7.InterfaceC0649b
    public final Object w(InterfaceC0648a interfaceC0648a) {
        return null;
    }
}
