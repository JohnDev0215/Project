package B8;

import H7.InterfaceC0320e;
import o8.C2057b;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class B extends A7.i implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final B f745t = new B(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C2057b c2057b = (C2057b) obj;
        A7.m.f(c2057b, "p0");
        return c2057b.f();
    }

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        return "getOuterClassId";
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        return A7.A.f443a.b(C2057b.class);
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        return "getOuterClassId()Lorg/jetbrains/kotlin/name/ClassId;";
    }
}
