package spi;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yb extends ac {
    public final /* synthetic */ Method a;

    public yb(Method method) {
        this.a = method;
    }

    @Override // spi.ac
    public final <T> T b(Class<T> cls) {
        ac.a(cls);
        return (T) this.a.invoke(null, cls, Object.class);
    }
}
