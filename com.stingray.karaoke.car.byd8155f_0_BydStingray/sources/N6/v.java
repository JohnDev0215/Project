package n6;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f20136a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f20137b;

    public v(long j3, long j10) {
        this.f20136a = j3;
        this.f20137b = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return this.f20136a == vVar.f20136a && this.f20137b == vVar.f20137b;
    }

    public final int hashCode() {
        return Long.hashCode(this.f20137b) + (Long.hashCode(this.f20136a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RhythmSection(startTick=");
        sb.append(this.f20136a);
        sb.append(", endTick=");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f20137b, ")", sb);
    }
}
