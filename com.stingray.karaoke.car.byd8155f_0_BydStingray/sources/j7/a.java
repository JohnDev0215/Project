package J7;

import A7.A;
import A7.i;
import A7.m;
import B8.s;
import H7.InterfaceC0320e;
import j8.C1579y;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a extends i implements InterfaceC2785n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final a f3110t = new a(2);

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        return "loadFunction";
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        return A.f443a.b(s.class);
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        return "loadFunction(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;)Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;";
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        s sVar = (s) obj;
        C1579y c1579y = (C1579y) obj2;
        m.f(sVar, "p0");
        m.f(c1579y, "p1");
        return sVar.e(c1579y);
    }
}
