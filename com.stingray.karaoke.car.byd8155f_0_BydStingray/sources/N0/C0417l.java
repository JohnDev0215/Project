package N0;

/* JADX INFO: renamed from: N0.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0417l extends AbstractC0418m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final I f4386b;

    public C0417l(String str, I i7) {
        this.f4385a = str;
        this.f4386b = i7;
    }

    @Override // N0.AbstractC0418m
    public final I a() {
        return this.f4386b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0417l)) {
            return false;
        }
        C0417l c0417l = (C0417l) obj;
        if (!A7.m.a(this.f4385a, c0417l.f4385a)) {
            return false;
        }
        if (!A7.m.a(this.f4386b, c0417l.f4386b)) {
            return false;
        }
        c0417l.getClass();
        return A7.m.a(null, null);
    }

    public final int hashCode() {
        int iHashCode = this.f4385a.hashCode() * 31;
        I i7 = this.f4386b;
        return (iHashCode + (i7 != null ? i7.hashCode() : 0)) * 31;
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("LinkAnnotation.Url(url="), this.f4385a, ')');
    }
}
