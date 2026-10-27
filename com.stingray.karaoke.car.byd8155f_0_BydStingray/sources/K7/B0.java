package K7;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class B0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f3721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3722b;

    public B0(ClassLoader classLoader) {
        this.f3721a = new WeakReference(classLoader);
        this.f3722b = System.identityHashCode(classLoader);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof B0) && this.f3721a.get() == ((B0) obj).f3721a.get();
    }

    public final int hashCode() {
        return this.f3722b;
    }

    public final String toString() {
        String string;
        ClassLoader classLoader = (ClassLoader) this.f3721a.get();
        return (classLoader == null || (string = classLoader.toString()) == null) ? "<null>" : string;
    }
}
