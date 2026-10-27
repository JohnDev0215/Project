package K7;

import H7.InterfaceC0320e;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class T extends A7.i implements InterfaceC2785n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final T f3749t = new T(2);

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        return "loadProperty";
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        return A7.A.f443a.b(B8.s.class);
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        return "loadProperty(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;)Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;";
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        B8.s sVar = (B8.s) obj;
        j8.G g7 = (j8.G) obj2;
        A7.m.f(sVar, "p0");
        A7.m.f(g7, "p1");
        return sVar.f(g7);
    }
}
