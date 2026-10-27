package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a01 {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final List e;

    public a01(boolean z, boolean z2, boolean z3, boolean z4, ArrayList arrayList) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
        this.e = arrayList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a01)) {
            return false;
        }
        a01 a01Var = (a01) obj;
        return this.a == a01Var.a && this.b == a01Var.b && this.c == a01Var.c && this.d == a01Var.d && v03.b(this.e, a01Var.e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public final int hashCode() {
        boolean z = this.a;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = r1 * 31;
        boolean z2 = this.b;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (i + r2) * 31;
        boolean z3 = this.c;
        ?? r22 = z3;
        if (z3) {
            r22 = 1;
        }
        int i3 = (i2 + r22) * 31;
        boolean z4 = this.d;
        int i4 = (i3 + (z4 ? 1 : z4)) * 31;
        List list = this.e;
        return i4 + (list == null ? 0 : list.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GaiaState(isActive=");
        sb.append(this.a);
        sb.append(", isConnected=");
        sb.append(this.b);
        sb.append(", isActiveOnSameDevice=");
        sb.append(this.c);
        sb.append(", localPlaybackEnabled=");
        sb.append(this.d);
        sb.append(", devices=");
        return hf2.e(sb, this.e, ')');
    }
}
