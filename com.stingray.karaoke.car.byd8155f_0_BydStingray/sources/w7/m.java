package W7;

import H7.InterfaceC0320e;
import java.lang.reflect.Method;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m extends A7.i implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final m f9890t = new m(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        Method method = (Method) obj;
        A7.m.f(method, "p0");
        return new w(method);
    }

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        return "<init>";
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        return A7.A.f443a.b(w.class);
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        return "<init>(Ljava/lang/reflect/Method;)V";
    }
}
