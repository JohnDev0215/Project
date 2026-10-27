package retrofit2;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Invocation.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f5224a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<?> f5225b;

    k(Method method, List<?> list) {
        this.f5224a = method;
        this.f5225b = Collections.unmodifiableList(list);
    }

    public Method a() {
        return this.f5224a;
    }

    public String toString() {
        return String.format("%s.%s() %s", this.f5224a.getDeclaringClass().getName(), this.f5224a.getName(), this.f5225b);
    }
}
