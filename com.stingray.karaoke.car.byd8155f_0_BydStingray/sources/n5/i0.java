package N5;

import com.stingray.shared.logs.segment.ActionOwner;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4699a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f0 f4700b;
    public final boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ActionOwner f4701e;

    public i0(int i7, f0 f0Var, boolean z6, boolean z10, ActionOwner actionOwner) {
        A7.m.f(actionOwner, "actionOwner");
        this.f4699a = i7;
        this.f4700b = f0Var;
        this.c = z6;
        this.d = z10;
        this.f4701e = actionOwner;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i0)) {
            return false;
        }
        i0 i0Var = (i0) obj;
        return this.f4699a == i0Var.f4699a && A7.m.a(this.f4700b, i0Var.f4700b) && this.c == i0Var.c && this.d == i0Var.d && this.f4701e == i0Var.f4701e;
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.f4699a) * 31;
        f0 f0Var = this.f4700b;
        return this.f4701e.hashCode() + AbstractC1347d.d(AbstractC1347d.d((iHashCode + (f0Var == null ? 0 : f0Var.hashCode())) * 31, 31, this.c), 31, this.d);
    }

    public final String toString() {
        return "SongChangedData(index=" + this.f4699a + ", song=" + this.f4700b + ", songListChanged=" + this.c + ", playWhenReady=" + this.d + ", actionOwner=" + this.f4701e + ")";
    }

    public /* synthetic */ i0(int i7, f0 f0Var, boolean z6, boolean z10, ActionOwner actionOwner, int i10) {
        this(i7, f0Var, z6, (i10 & 8) != 0 ? true : z10, (i10 & 16) != 0 ? ActionOwner.HOST : actionOwner);
    }
}
