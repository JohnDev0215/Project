package l5;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class t extends w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Method f18666b;
    public final /* synthetic */ int c;

    public t(int i7, Method method) {
        this.f18666b = method;
        this.c = i7;
    }

    @Override // l5.w
    public final Object a(Class cls) {
        String strA = C1778f.a(cls);
        if (strA != null) {
            throw new AssertionError("UnsafeAllocator is used for non-instantiable type: ".concat(strA));
        }
        return this.f18666b.invoke(null, cls, Integer.valueOf(this.c));
    }
}
