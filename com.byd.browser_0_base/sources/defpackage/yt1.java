package defpackage;

/* JADX INFO: compiled from: PackageReference.kt */
/* JADX INFO: loaded from: classes.dex */
public final class yt1 implements mt {
    public final Class<?> a;

    public yt1(Class cls) {
        i21.e(cls, "jClass");
        this.a = cls;
    }

    @Override // defpackage.mt
    public final Class<?> a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof yt1) {
            if (i21.a(this.a, ((yt1) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString() + " (Kotlin reflection is not available)";
    }
}
