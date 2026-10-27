package l5;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class s extends w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Method f18665b;
    public final /* synthetic */ Object c;

    public s(Method method, Object obj) {
        this.f18665b = method;
        this.c = obj;
    }

    @Override // l5.w
    public final Object a(Class cls) {
        String strA = C1778f.a(cls);
        if (strA != null) {
            throw new AssertionError("UnsafeAllocator is used for non-instantiable type: ".concat(strA));
        }
        return this.f18665b.invoke(this.c, cls);
    }
}
