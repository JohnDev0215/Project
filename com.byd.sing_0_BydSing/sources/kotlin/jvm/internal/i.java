package kotlin.jvm.internal;

/* JADX INFO: compiled from: PackageReference.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Class<?> f4079b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4080c;

    public i(Class<?> cls, String str) {
        h.d(cls, "jClass");
        h.d(str, "moduleName");
        this.f4079b = cls;
        this.f4080c = str;
    }

    @Override // kotlin.jvm.internal.c
    public Class<?> a() {
        return this.f4079b;
    }

    public boolean equals(Object obj) {
        return (obj instanceof i) && h.a(a(), ((i) obj).a());
    }

    public int hashCode() {
        return a().hashCode();
    }

    public String toString() {
        return a().toString() + " (Kotlin reflection is not available)";
    }
}
