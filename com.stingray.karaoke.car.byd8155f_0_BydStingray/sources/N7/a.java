package N7;

import java.util.ServiceLoader;
import n7.AbstractC1998m;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class a extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f4786m = new a(0);

    @Override // z7.InterfaceC2772a
    public final Object a() {
        ServiceLoader serviceLoaderLoad = ServiceLoader.load(c.class, c.class.getClassLoader());
        A7.m.e(serviceLoaderLoad, "implementations");
        c cVar = (c) AbstractC1998m.Z(serviceLoaderLoad);
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalStateException("No BuiltInsLoader implementation was found. Please ensure that the META-INF/services/ is not stripped from your application and that the Java virtual machine is not running under a security manager");
    }
}
