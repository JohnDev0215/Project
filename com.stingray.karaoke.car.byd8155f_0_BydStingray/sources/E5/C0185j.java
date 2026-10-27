package E5;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: E5.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0185j extends M {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Method f1814k;
    public final /* synthetic */ Class l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1815m;

    public C0185j(Method method, Class cls, int i7) {
        this.f1814k = method;
        this.l = cls;
        this.f1815m = i7;
    }

    @Override // E5.M
    public final Object f() {
        return this.f1814k.invoke(null, this.l, Integer.valueOf(this.f1815m));
    }

    public final String toString() {
        return this.l.getName();
    }
}
