package D;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1041a = 0.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1042b = true;
    public AbstractC0120b c = null;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof O)) {
            return false;
        }
        O o = (O) obj;
        return Float.compare(this.f1041a, o.f1041a) == 0 && this.f1042b == o.f1042b && A7.m.a(this.c, o.c) && A7.m.a(null, null);
    }

    public final int hashCode() {
        int iD = AbstractC1347d.d(Float.hashCode(this.f1041a) * 31, 31, this.f1042b);
        AbstractC0120b abstractC0120b = this.c;
        return (iD + (abstractC0120b == null ? 0 : abstractC0120b.hashCode())) * 31;
    }

    public final String toString() {
        return "RowColumnParentData(weight=" + this.f1041a + ", fill=" + this.f1042b + ", crossAxisAlignment=" + this.c + ", flowLayoutData=null)";
    }
}
