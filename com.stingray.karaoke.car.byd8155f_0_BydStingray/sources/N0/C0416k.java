package N0;

/* JADX INFO: renamed from: N0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0416k extends AbstractC0418m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4383a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final I f4384b;

    public C0416k(String str, I i7) {
        this.f4383a = str;
        this.f4384b = i7;
    }

    @Override // N0.AbstractC0418m
    public final I a() {
        return this.f4384b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0416k)) {
            return false;
        }
        C0416k c0416k = (C0416k) obj;
        if (!A7.m.a(this.f4383a, c0416k.f4383a)) {
            return false;
        }
        if (!A7.m.a(this.f4384b, c0416k.f4384b)) {
            return false;
        }
        c0416k.getClass();
        return A7.m.a(null, null);
    }

    public final int hashCode() {
        int iHashCode = this.f4383a.hashCode() * 31;
        I i7 = this.f4384b;
        return (iHashCode + (i7 != null ? i7.hashCode() : 0)) * 31;
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("LinkAnnotation.Clickable(tag="), this.f4383a, ')');
    }
}
