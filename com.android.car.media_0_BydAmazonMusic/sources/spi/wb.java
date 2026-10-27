package spi;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wb extends ac {
    public final /* synthetic */ Method a;
    public final /* synthetic */ Object b;

    public wb(Method method, Object obj) {
        this.a = method;
        this.b = obj;
    }

    @Override // spi.ac
    public final <T> T b(Class<T> cls) {
        ac.a(cls);
        return (T) this.a.invoke(this.b, cls);
    }
}
