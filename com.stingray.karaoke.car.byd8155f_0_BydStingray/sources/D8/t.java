package D8;

import Q7.C0661n;
import Q7.InterfaceC0657j;
import Q7.L;
import Q7.N;
import T.C0746i0;
import T7.H;
import h8.C1391h;
import j8.G;
import l8.AbstractC1787e;
import l8.C1790h;
import l8.InterfaceC1788f;
import o8.C2061f;
import p8.AbstractC2126a;

/* JADX INFO: loaded from: classes.dex */
public final class t extends H implements b {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final G f1272M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final InterfaceC1788f f1273N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final C0746i0 f1274O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final C1790h f1275P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final C1391h f1276Q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(InterfaceC0657j interfaceC0657j, L l, R7.h hVar, int i7, C0661n c0661n, boolean z6, C2061f c2061f, int i10, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, G g7, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, C1790h c1790h, C1391h c1391h) {
        super(interfaceC0657j, l, hVar, i7, c0661n, z6, c2061f, i10, N.c, z10, z11, z14, z12, z13);
        A7.m.f(interfaceC0657j, "containingDeclaration");
        A7.m.f(hVar, "annotations");
        A7.k.p(i7, "modality");
        A7.m.f(c0661n, "visibility");
        A7.m.f(c2061f, "name");
        A7.k.p(i10, "kind");
        A7.m.f(g7, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        A7.m.f(c1790h, "versionRequirementTable");
        this.f1272M = g7;
        this.f1273N = interfaceC1788f;
        this.f1274O = c0746i0;
        this.f1275P = c1790h;
        this.f1276Q = c1391h;
    }

    @Override // D8.m
    public final InterfaceC1788f G0() {
        return this.f1273N;
    }

    @Override // D8.m
    public final AbstractC2126a O() {
        return this.f1272M;
    }

    @Override // T7.H
    public final H e1(InterfaceC0657j interfaceC0657j, int i7, C0661n c0661n, L l, int i10, C2061f c2061f) {
        A7.m.f(interfaceC0657j, "newOwner");
        A7.k.p(i7, "newModality");
        A7.m.f(c0661n, "newVisibility");
        A7.k.p(i10, "kind");
        A7.m.f(c2061f, "newName");
        return new t(interfaceC0657j, l, k(), i7, c0661n, this.f8801r, c2061f, i10, this.f8809z, this.f8790A, z(), this.f8792D, this.B, this.f1272M, this.f1273N, this.f1274O, this.f1275P, this.f1276Q);
    }

    @Override // D8.m
    public final C0746i0 r0() {
        return this.f1274O;
    }

    @Override // D8.m
    public final l v() {
        return this.f1276Q;
    }

    @Override // T7.H, Q7.InterfaceC0670x
    public final boolean z() {
        return AbstractC1787e.f18829D.c(this.f1272M.o).booleanValue();
    }
}
