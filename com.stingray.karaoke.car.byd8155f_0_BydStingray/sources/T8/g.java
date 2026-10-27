package t8;

import F8.AbstractC0287w;
import Q7.InterfaceC0671y;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f22499a;

    public g(Object obj) {
        this.f22499a = obj;
    }

    public abstract AbstractC0287w a(InterfaceC0671y interfaceC0671y);

    public Object b() {
        return this.f22499a;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            Object objB = b();
            g gVar = obj instanceof g ? (g) obj : null;
            if (!A7.m.a(objB, gVar != null ? gVar.b() : null)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        Object objB = b();
        if (objB != null) {
            return objB.hashCode();
        }
        return 0;
    }

    public String toString() {
        return String.valueOf(b());
    }
}
