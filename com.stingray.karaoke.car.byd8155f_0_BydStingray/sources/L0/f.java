package L0;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final f d = new f(0.0f, new G7.a(0.0f, 0.0f), 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3881a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final G7.a f3882b;
    public final int c;

    public f(float f10, G7.a aVar, int i7) {
        this.f3881a = f10;
        this.f3882b = aVar;
        this.c = i7;
        if (Float.isNaN(f10)) {
            throw new IllegalArgumentException("current must not be NaN");
        }
    }

    public final G7.a a() {
        return this.f3882b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.f3881a == fVar.f3881a && A7.m.a(this.f3882b, fVar.f3882b) && this.c == fVar.c;
    }

    public final int hashCode() {
        return ((this.f3882b.hashCode() + (Float.hashCode(this.f3881a) * 31)) * 31) + this.c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ProgressBarRangeInfo(current=");
        sb.append(this.f3881a);
        sb.append(", range=");
        sb.append(this.f3882b);
        sb.append(", steps=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.c, ')');
    }
}
