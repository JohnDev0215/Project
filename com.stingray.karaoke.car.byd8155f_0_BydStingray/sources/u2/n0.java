package u2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class n0 {
    public static final n0 d = new n0(new e2.g0[0]);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f22880e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22881a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d5.d0 f22882b;
    public int c;

    static {
        int i7 = AbstractC1343A.f15195a;
        f22880e = Integer.toString(0, 36);
    }

    public n0(e2.g0... g0VarArr) {
        this.f22882b = d5.I.x(g0VarArr);
        this.f22881a = g0VarArr.length;
        int i7 = 0;
        while (true) {
            d5.d0 d0Var = this.f22882b;
            if (i7 >= d0Var.size()) {
                return;
            }
            int i10 = i7 + 1;
            for (int i11 = i10; i11 < d0Var.size(); i11++) {
                if (((e2.g0) d0Var.get(i7)).equals(d0Var.get(i11))) {
                    AbstractC1346c.q("TrackGroupArray", "", new IllegalArgumentException("Multiple identical TrackGroups added to one TrackGroupArray."));
                }
            }
            i7 = i10;
        }
    }

    public final e2.g0 a(int i7) {
        return (e2.g0) this.f22882b.get(i7);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n0.class != obj.getClass()) {
            return false;
        }
        n0 n0Var = (n0) obj;
        return this.f22881a == n0Var.f22881a && this.f22882b.equals(n0Var.f22882b);
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = this.f22882b.hashCode();
        }
        return this.c;
    }
}
