package spi;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xb extends ac {
    public final /* synthetic */ Method a;
    public final /* synthetic */ int b;

    public xb(Method method, int i) {
        this.a = method;
        this.b = i;
    }

    @Override // spi.ac
    public final <T> T b(Class<T> cls) {
        ac.a(cls);
        return (T) this.a.invoke(null, cls, Integer.valueOf(this.b));
    }
}
