package p;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class xs {
    public final int a;
    public final Method b;

    public xs(int i, Method method) {
        this.a = i;
        this.b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xs)) {
            return false;
        }
        xs xsVar = (xs) obj;
        return this.a == xsVar.a && this.b.getName().equals(xsVar.b.getName());
    }

    public final int hashCode() {
        return this.b.getName().hashCode() + (this.a * 31);
    }
}
