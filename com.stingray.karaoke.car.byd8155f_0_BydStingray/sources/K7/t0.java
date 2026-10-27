package K7;

import Q7.InterfaceC0650c;
import java.lang.ref.SoftReference;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class t0 extends v0 implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2772a f3837m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile SoftReference f3838n;

    public t0(InterfaceC0650c interfaceC0650c, InterfaceC2772a interfaceC2772a) {
        if (interfaceC2772a == null) {
            throw new IllegalArgumentException("Argument for @NotNull parameter 'initializer' of kotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal.<init> must not be null");
        }
        this.f3838n = null;
        this.f3837m = interfaceC2772a;
        if (interfaceC0650c != null) {
            this.f3838n = new SoftReference(interfaceC0650c);
        }
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        Object obj;
        SoftReference softReference = this.f3838n;
        Object obj2 = v0.l;
        if (softReference != null && (obj = softReference.get()) != null) {
            if (obj == obj2) {
                return null;
            }
            return obj;
        }
        Object objA = this.f3837m.a();
        if (objA != null) {
            obj2 = objA;
        }
        this.f3838n = new SoftReference(obj2);
        return objA;
    }
}
