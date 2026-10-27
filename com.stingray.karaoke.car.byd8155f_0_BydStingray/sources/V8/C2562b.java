package v8;

import A7.A;
import A7.i;
import A7.m;
import H7.InterfaceC0320e;
import T7.Q;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: v8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C2562b extends i implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C2562b f23038t = new C2562b(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        Q q4 = (Q) obj;
        m.f(q4, "p0");
        return Boolean.valueOf(q4.d1());
    }

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        return "declaresDefaultValue";
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        return A.f443a.b(Q.class);
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        return "declaresDefaultValue()Z";
    }
}
