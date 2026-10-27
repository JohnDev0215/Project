package f2;

import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f14547e = new b(-1, -1, -1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14549b;
    public final int c;
    public final int d;

    public b(int i7, int i10, int i11) {
        this.f14548a = i7;
        this.f14549b = i10;
        this.c = i11;
        this.d = AbstractC1343A.D(i11) ? AbstractC1343A.v(i11, i10) : -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f14548a == bVar.f14548a && this.f14549b == bVar.f14549b && this.c == bVar.c;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f14548a), Integer.valueOf(this.f14549b), Integer.valueOf(this.c)});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AudioFormat[sampleRate=");
        sb.append(this.f14548a);
        sb.append(", channelCount=");
        sb.append(this.f14549b);
        sb.append(", encoding=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.c, ']');
    }
}
