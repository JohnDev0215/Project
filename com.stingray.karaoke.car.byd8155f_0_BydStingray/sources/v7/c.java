package V7;

import A7.m;
import Q8.o;
import i8.C1417b;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f9548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1417b f9549b;

    public c(Class cls, C1417b c1417b) {
        this.f9548a = cls;
        this.f9549b = c1417b;
    }

    public final String a() {
        return o.w0(this.f9548a.getName(), '.', '/').concat(".class");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c) {
            if (m.a(this.f9548a, ((c) obj).f9548a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f9548a.hashCode();
    }

    public final String toString() {
        return c.class.getName() + ": " + this.f9548a;
    }
}
