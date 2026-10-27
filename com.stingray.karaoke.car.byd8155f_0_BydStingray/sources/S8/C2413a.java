package s8;

import A7.m;
import F8.A;
import F8.AbstractC0287w;
import F8.H;
import F8.K;
import F8.O;
import F8.a0;
import G8.f;
import H8.i;
import java.util.List;
import n7.u;
import y8.n;

/* JADX INFO: renamed from: s8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2413a extends A implements I8.b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final O f22374m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC2414b f22375n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final H f22376p;

    public C2413a(O o, InterfaceC2414b interfaceC2414b, boolean z6, H h10) {
        m.f(o, "typeProjection");
        m.f(interfaceC2414b, "constructor");
        m.f(h10, "attributes");
        this.f22374m = o;
        this.f22375n = interfaceC2414b;
        this.o = z6;
        this.f22376p = h10;
    }

    @Override // F8.AbstractC0287w
    public final K B0() {
        return this.f22375n;
    }

    @Override // F8.AbstractC0287w
    public final boolean F0() {
        return this.o;
    }

    @Override // F8.AbstractC0287w
    public final AbstractC0287w I0(f fVar) {
        m.f(fVar, "kotlinTypeRefiner");
        return new C2413a(this.f22374m.d(fVar), this.f22375n, this.o, this.f22376p);
    }

    @Override // F8.A, F8.a0
    public final a0 L0(boolean z6) {
        if (z6 == this.o) {
            return this;
        }
        return new C2413a(this.f22374m, this.f22375n, z6, this.f22376p);
    }

    @Override // F8.a0
    /* JADX INFO: renamed from: M0 */
    public final a0 I0(f fVar) {
        m.f(fVar, "kotlinTypeRefiner");
        return new C2413a(this.f22374m.d(fVar), this.f22375n, this.o, this.f22376p);
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        if (z6 == this.o) {
            return this;
        }
        return new C2413a(this.f22374m, this.f22375n, z6, this.f22376p);
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        m.f(h10, "newAttributes");
        return new C2413a(this.f22374m, this.f22375n, this.o, h10);
    }

    @Override // F8.AbstractC0287w
    public final List o0() {
        return u.l;
    }

    @Override // F8.AbstractC0287w
    public final H t0() {
        return this.f22376p;
    }

    @Override // F8.A
    public final String toString() {
        StringBuilder sb = new StringBuilder("Captured(");
        sb.append(this.f22374m);
        sb.append(')');
        sb.append(this.o ? "?" : "");
        return sb.toString();
    }

    @Override // F8.AbstractC0287w
    public final n z0() {
        return i.a(1, true, new String[0]);
    }
}
