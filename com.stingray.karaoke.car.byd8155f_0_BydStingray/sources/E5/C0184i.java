package E5;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: E5.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0184i extends M {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Method f1812k;
    public final /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Class f1813m;

    public C0184i(Method method, Object obj, Class cls) {
        this.f1812k = method;
        this.l = obj;
        this.f1813m = cls;
    }

    @Override // E5.M
    public final Object f() {
        return this.f1812k.invoke(this.l, this.f1813m);
    }

    public final String toString() {
        return this.f1813m.getName();
    }
}
