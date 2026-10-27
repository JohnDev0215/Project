package h;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Invocation.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f5577a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<?> f5578b;

    l(Method method, List<?> list) {
        this.f5577a = method;
        this.f5578b = Collections.unmodifiableList(list);
    }

    public Method a() {
        return this.f5577a;
    }

    public String toString() {
        return String.format("%s.%s() %s", this.f5577a.getDeclaringClass().getName(), this.f5577a.getName(), this.f5578b);
    }
}
