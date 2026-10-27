package I6;

import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3030a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3031b;
    public final int c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f3032e;

    public b(float f10, float f11, int i7, long j3, int i10) {
        i7 = (i10 & 4) != 0 ? 0 : i7;
        j3 = (i10 & 16) != 0 ? 0L : j3;
        this.f3030a = f10;
        this.f3031b = f11;
        this.c = i7;
        this.d = 1.0f;
        this.f3032e = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Float.compare(this.f3030a, bVar.f3030a) == 0 && Float.compare(this.f3031b, bVar.f3031b) == 0 && this.c == bVar.c && Float.compare(this.d, bVar.d) == 0 && this.f3032e == bVar.f3032e;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3032e) + AbstractC1347d.b(AbstractC2641j.b(this.c, AbstractC1347d.b(Float.hashCode(this.f3030a) * 31, 31, this.f3031b), 31), 31, this.d);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SoundConfig(volumeL=");
        sb.append(this.f3030a);
        sb.append(", volumeR=");
        sb.append(this.f3031b);
        sb.append(", loop=");
        sb.append(this.c);
        sb.append(", rate=");
        sb.append(this.d);
        sb.append(", fadeIn=");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f3032e, ")", sb);
    }
}
