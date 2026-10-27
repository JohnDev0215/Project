package m0;

import h2.AbstractC1347d;
import n7.AbstractC1985B;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f18884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f18885b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f18886e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f18887f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f18888g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f18889h;

    static {
        long j3 = AbstractC1792a.f18876a;
        z.a(AbstractC1792a.b(j3), AbstractC1792a.c(j3));
    }

    public e(float f10, float f11, float f12, float f13, long j3, long j10, long j11, long j12) {
        this.f18884a = f10;
        this.f18885b = f11;
        this.c = f12;
        this.d = f13;
        this.f18886e = j3;
        this.f18887f = j10;
        this.f18888g = j11;
        this.f18889h = j12;
    }

    public final float a() {
        return this.d - this.f18885b;
    }

    public final float b() {
        return this.c - this.f18884a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return Float.compare(this.f18884a, eVar.f18884a) == 0 && Float.compare(this.f18885b, eVar.f18885b) == 0 && Float.compare(this.c, eVar.c) == 0 && Float.compare(this.d, eVar.d) == 0 && AbstractC1792a.a(this.f18886e, eVar.f18886e) && AbstractC1792a.a(this.f18887f, eVar.f18887f) && AbstractC1792a.a(this.f18888g, eVar.f18888g) && AbstractC1792a.a(this.f18889h, eVar.f18889h);
    }

    public final int hashCode() {
        int iB = AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f18884a) * 31, 31, this.f18885b), 31, this.c), 31, this.d);
        int i7 = AbstractC1792a.f18877b;
        return Long.hashCode(this.f18889h) + AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(iB, 31, this.f18886e), 31, this.f18887f), 31, this.f18888g);
    }

    public final String toString() {
        String str = AbstractC1985B.v(this.f18884a) + ", " + AbstractC1985B.v(this.f18885b) + ", " + AbstractC1985B.v(this.c) + ", " + AbstractC1985B.v(this.d);
        long j3 = this.f18886e;
        long j10 = this.f18887f;
        boolean zA = AbstractC1792a.a(j3, j10);
        long j11 = this.f18888g;
        long j12 = this.f18889h;
        if (!zA || !AbstractC1792a.a(j10, j11) || !AbstractC1792a.a(j11, j12)) {
            StringBuilder sbJ = AbstractC1347d.j("RoundRect(rect=", str, ", topLeft=");
            sbJ.append((Object) AbstractC1792a.d(j3));
            sbJ.append(", topRight=");
            sbJ.append((Object) AbstractC1792a.d(j10));
            sbJ.append(", bottomRight=");
            sbJ.append((Object) AbstractC1792a.d(j11));
            sbJ.append(", bottomLeft=");
            sbJ.append((Object) AbstractC1792a.d(j12));
            sbJ.append(')');
            return sbJ.toString();
        }
        if (AbstractC1792a.b(j3) == AbstractC1792a.c(j3)) {
            StringBuilder sbJ2 = AbstractC1347d.j("RoundRect(rect=", str, ", radius=");
            sbJ2.append(AbstractC1985B.v(AbstractC1792a.b(j3)));
            sbJ2.append(')');
            return sbJ2.toString();
        }
        StringBuilder sbJ3 = AbstractC1347d.j("RoundRect(rect=", str, ", x=");
        sbJ3.append(AbstractC1985B.v(AbstractC1792a.b(j3)));
        sbJ3.append(", y=");
        sbJ3.append(AbstractC1985B.v(AbstractC1792a.c(j3)));
        sbJ3.append(')');
        return sbJ3.toString();
    }
}
