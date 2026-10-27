package l5;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class u extends w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Method f18667b;

    public u(Method method) {
        this.f18667b = method;
    }

    @Override // l5.w
    public final Object a(Class cls) {
        String strA = C1778f.a(cls);
        if (strA != null) {
            throw new AssertionError("UnsafeAllocator is used for non-instantiable type: ".concat(strA));
        }
        return this.f18667b.invoke(null, cls, Object.class);
    }
}
