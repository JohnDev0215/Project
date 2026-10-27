package a0;

/* JADX INFO: renamed from: a0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0905a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f11025a;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C0905a) && this.f11025a == ((C0905a) obj).f11025a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f11025a);
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("DeltaCounter(count="), this.f11025a, ')');
    }
}
