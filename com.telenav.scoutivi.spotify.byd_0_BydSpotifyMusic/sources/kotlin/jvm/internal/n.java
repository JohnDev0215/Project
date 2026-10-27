package kotlin.jvm.internal;

/* JADX INFO: compiled from: PackageReference.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n implements d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Class<?> f7503e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f7504g;

    public n(Class<?> jClass, String moduleName) {
        l.e(jClass, "jClass");
        l.e(moduleName, "moduleName");
        this.f7503e = jClass;
        this.f7504g = moduleName;
    }

    @Override // kotlin.jvm.internal.d
    public Class<?> a() {
        return this.f7503e;
    }

    public boolean equals(Object obj) {
        return (obj instanceof n) && l.a(a(), ((n) obj).a());
    }

    public int hashCode() {
        return a().hashCode();
    }

    public String toString() {
        return a().toString() + " (Kotlin reflection is not available)";
    }
}
