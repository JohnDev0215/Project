package H8;

import A7.m;
import F8.A;
import F8.AbstractC0287w;
import F8.H;
import F8.K;
import F8.a0;
import java.util.Arrays;
import java.util.List;
import y8.n;

/* JADX INFO: loaded from: classes.dex */
public final class f extends A {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final K f2858m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e f2859n;
    public final h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final List f2860p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f2861q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String[] f2862r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final String f2863s;

    public f(K k10, e eVar, h hVar, List list, boolean z6, String... strArr) {
        m.f(hVar, "kind");
        m.f(list, "arguments");
        m.f(strArr, "formatParams");
        this.f2858m = k10;
        this.f2859n = eVar;
        this.o = hVar;
        this.f2860p = list;
        this.f2861q = z6;
        this.f2862r = strArr;
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        this.f2863s = String.format(hVar.l, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
    }

    @Override // F8.AbstractC0287w
    public final K B0() {
        return this.f2858m;
    }

    @Override // F8.AbstractC0287w
    public final boolean F0() {
        return this.f2861q;
    }

    @Override // F8.AbstractC0287w
    /* JADX INFO: renamed from: I0 */
    public final AbstractC0287w M0(G8.f fVar) {
        m.f(fVar, "kotlinTypeRefiner");
        return this;
    }

    @Override // F8.a0
    /* JADX INFO: renamed from: M0 */
    public final a0 I0(G8.f fVar) {
        m.f(fVar, "kotlinTypeRefiner");
        return this;
    }

    @Override // F8.A, F8.a0
    public final a0 N0(H h10) {
        m.f(h10, "newAttributes");
        return this;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        String[] strArr = this.f2862r;
        return new f(this.f2858m, this.f2859n, this.o, this.f2860p, z6, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        m.f(h10, "newAttributes");
        return this;
    }

    @Override // F8.AbstractC0287w
    public final List o0() {
        return this.f2860p;
    }

    @Override // F8.AbstractC0287w
    public final H t0() {
        H.f2437m.getClass();
        return H.f2438n;
    }

    @Override // F8.AbstractC0287w
    public final n z0() {
        return this.f2859n;
    }
}
