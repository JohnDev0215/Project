package J;

/* JADX INFO: loaded from: classes.dex */
public final class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3037a;

    public b(float f10) {
        this.f3037a = f10;
    }

    @Override // J.a
    public final float a(long j3, a1.b bVar) {
        return bVar.y(this.f3037a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof b) && a1.e.a(this.f3037a, ((b) obj).f3037a);
    }

    public final int hashCode() {
        return Float.hashCode(this.f3037a);
    }

    public final String toString() {
        return "CornerSize(size = " + this.f3037a + ".dp)";
    }
}
