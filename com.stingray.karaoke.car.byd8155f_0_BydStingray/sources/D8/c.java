package D8;

import Q7.InterfaceC0652e;
import Q7.InterfaceC0656i;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.N;
import T.C0746i0;
import T7.C0790i;
import h8.C1391h;
import j8.C1567l;
import l8.C1790h;
import l8.InterfaceC1788f;
import o8.C2061f;
import p8.AbstractC2126a;

/* JADX INFO: loaded from: classes.dex */
public final class c extends C0790i implements b {

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final C1567l f1216Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final InterfaceC1788f f1217R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final C0746i0 f1218S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final C1790h f1219T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final C1391h f1220U;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(InterfaceC0652e interfaceC0652e, InterfaceC0656i interfaceC0656i, R7.h hVar, boolean z6, int i7, C1567l c1567l, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, C1790h c1790h, C1391h c1391h, N n10) {
        super(interfaceC0652e, interfaceC0656i, hVar, z6, i7, n10 == null ? N.c : n10);
        A7.m.f(interfaceC0652e, "containingDeclaration");
        A7.m.f(hVar, "annotations");
        A7.k.p(i7, "kind");
        A7.m.f(c1567l, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        A7.m.f(c1790h, "versionRequirementTable");
        this.f1216Q = c1567l;
        this.f1217R = interfaceC1788f;
        this.f1218S = c0746i0;
        this.f1219T = c1790h;
        this.f1220U = c1391h;
    }

    @Override // D8.m
    public final InterfaceC1788f G0() {
        return this.f1217R;
    }

    @Override // D8.m
    public final AbstractC2126a O() {
        return this.f1216Q;
    }

    @Override // T7.C0790i, T7.t
    public final /* bridge */ /* synthetic */ T7.t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, N n10, R7.h hVar, C2061f c2061f) {
        return t1(interfaceC0657j, interfaceC0666t, i7, hVar, n10);
    }

    @Override // T7.t, Q7.InterfaceC0666t
    public final boolean j0() {
        return false;
    }

    @Override // T7.t, Q7.InterfaceC0666t
    public final boolean m() {
        return false;
    }

    @Override // T7.t, Q7.InterfaceC0666t
    public final boolean n() {
        return false;
    }

    @Override // T7.C0790i
    /* JADX INFO: renamed from: n1 */
    public final /* bridge */ /* synthetic */ C0790i e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, N n10, R7.h hVar, C2061f c2061f) {
        return t1(interfaceC0657j, interfaceC0666t, i7, hVar, n10);
    }

    @Override // D8.m
    public final C0746i0 r0() {
        return this.f1218S;
    }

    public final c t1(InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, int i7, R7.h hVar, N n10) {
        A7.m.f(interfaceC0657j, "newOwner");
        A7.k.p(i7, "kind");
        A7.m.f(hVar, "annotations");
        c cVar = new c((InterfaceC0652e) interfaceC0657j, (InterfaceC0656i) interfaceC0666t, hVar, this.f8843P, i7, this.f1216Q, this.f1217R, this.f1218S, this.f1219T, this.f1220U, n10);
        cVar.f8890H = this.f8890H;
        return cVar;
    }

    @Override // D8.m
    public final l v() {
        return this.f1220U;
    }

    @Override // T7.t, Q7.InterfaceC0670x
    public final boolean z() {
        return false;
    }
}
