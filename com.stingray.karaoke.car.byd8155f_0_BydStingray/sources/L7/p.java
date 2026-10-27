package L7;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class p extends o implements d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f4059g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(Method method, Object obj) {
        super(method, false, 4);
        A7.m.f(method, "method");
        this.f4059g = obj;
    }

    @Override // L7.o, L7.e
    public final Object q(Object[] objArr) {
        l9.d.H(this, objArr);
        return c(this.f4059g, objArr);
    }
}
