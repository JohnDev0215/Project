package n2;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19536a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f19537b;
    public final long c;

    public G(F f10) {
        this.f19536a = f10.f19534a;
        this.f19537b = f10.f19535b;
        this.c = f10.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof G)) {
            return false;
        }
        G g7 = (G) obj;
        return this.f19536a == g7.f19536a && this.f19537b == g7.f19537b && this.c == g7.c;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f19536a), Float.valueOf(this.f19537b), Long.valueOf(this.c)});
    }
}
