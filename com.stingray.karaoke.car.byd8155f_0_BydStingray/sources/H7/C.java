package H7;

import java.lang.reflect.Type;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C extends A7.j implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C f2843t = new C(1, H.class, "typeToString", "typeToString(Ljava/lang/reflect/Type;)Ljava/lang/String;", 1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        Type type = (Type) obj;
        A7.m.f(type, "p0");
        return H.o(type);
    }
}
