package H7;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class G extends A7.j implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final G f2847t = new G(1, Class.class, "getComponentType", "getComponentType()Ljava/lang/Class;", 0);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        Class cls = (Class) obj;
        A7.m.f(cls, "p0");
        return cls.getComponentType();
    }
}
