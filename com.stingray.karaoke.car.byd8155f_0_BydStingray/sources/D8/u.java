package D8;

import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.N;
import T.C0746i0;
import T7.K;
import h8.C1391h;
import j8.C1579y;
import l8.C1790h;
import l8.InterfaceC1788f;
import o8.C2061f;
import p8.AbstractC2126a;

/* JADX INFO: loaded from: classes.dex */
public final class u extends K implements b {

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final C1579y f1277P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final InterfaceC1788f f1278Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final C0746i0 f1279R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final C1790h f1280S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final C1391h f1281T;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(InterfaceC0657j interfaceC0657j, K k10, R7.h hVar, C2061f c2061f, int i7, C1579y c1579y, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, C1790h c1790h, C1391h c1391h, N n10) {
        super(interfaceC0657j, k10, hVar, c2061f, i7, n10 == null ? N.c : n10);
        A7.m.f(interfaceC0657j, "containingDeclaration");
        A7.m.f(hVar, "annotations");
        A7.k.p(i7, "kind");
        A7.m.f(c1579y, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        A7.m.f(c1790h, "versionRequirementTable");
        this.f1277P = c1579y;
        this.f1278Q = interfaceC1788f;
        this.f1279R = c0746i0;
        this.f1280S = c1790h;
        this.f1281T = c1391h;
    }

    @Override // D8.m
    public final InterfaceC1788f G0() {
        return this.f1278Q;
    }

    @Override // D8.m
    public final AbstractC2126a O() {
        return this.f1277P;
    }

    @Override // T7.K, T7.t
    public final T7.t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, N n10, R7.h hVar, C2061f c2061f) {
        C2061f c2061f2;
        A7.m.f(interfaceC0657j, "newOwner");
        A7.k.p(i7, "kind");
        A7.m.f(hVar, "annotations");
        K k10 = (K) interfaceC0666t;
        if (c2061f == null) {
            C2061f name = getName();
            A7.m.e(name, "name");
            c2061f2 = name;
        } else {
            c2061f2 = c2061f;
        }
        u uVar = new u(interfaceC0657j, k10, hVar, c2061f2, i7, this.f1277P, this.f1278Q, this.f1279R, this.f1280S, this.f1281T, n10);
        uVar.f8890H = this.f8890H;
        return uVar;
    }

    @Override // D8.m
    public final C0746i0 r0() {
        return this.f1279R;
    }

    @Override // D8.m
    public final l v() {
        return this.f1281T;
    }
}
