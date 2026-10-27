package o3;

import F0.X;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.InterfaceC0986y;
import e0.C1178r;
import n3.C1945j;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: o3.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2040m extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1945j f20476m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f20477n;
    public final /* synthetic */ C1178r o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2040m(C1178r c1178r, C1945j c1945j, boolean z6) {
        super(1);
        this.f20476m = c1945j;
        this.f20477n = z6;
        this.o = c1178r;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        final C1945j c1945j = this.f20476m;
        final boolean z6 = this.f20477n;
        final C1178r c1178r = this.o;
        InterfaceC0986y interfaceC0986y = new InterfaceC0986y() { // from class: o3.l
            @Override // androidx.lifecycle.InterfaceC0986y
            public final void b(androidx.lifecycle.A a10, EnumC0980s enumC0980s) {
                C1945j c1945j2 = c1945j;
                boolean z10 = z6;
                C1178r c1178r2 = c1178r;
                if (z10 && !c1178r2.contains(c1945j2)) {
                    c1178r2.add(c1945j2);
                }
                if (enumC0980s == EnumC0980s.ON_START && !c1178r2.contains(c1945j2)) {
                    c1178r2.add(c1945j2);
                }
                if (enumC0980s == EnumC0980s.ON_STOP) {
                    c1178r2.remove(c1945j2);
                }
            }
        };
        c1945j.f19870s.M0(interfaceC0986y);
        return new X(c1945j, 10, interfaceC0986y);
    }
}
