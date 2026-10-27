package g5;

import P8.n;
import java.io.Serializable;

/* JADX INFO: renamed from: g5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1300a implements Serializable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1300a f14976n = new C1300a(new int[0]);
    public final int[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f14977m;

    public C1300a(int[] iArr) {
        int length = iArr.length;
        this.l = iArr;
        this.f14977m = length;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1300a)) {
            return false;
        }
        C1300a c1300a = (C1300a) obj;
        int i7 = c1300a.f14977m;
        int i10 = this.f14977m;
        if (i10 != i7) {
            return false;
        }
        for (int i11 = 0; i11 < i10; i11++) {
            n.p(i11, i10);
            int i12 = this.l[i11];
            n.p(i11, c1300a.f14977m);
            if (i12 != c1300a.l[i11]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i7 = 1;
        for (int i10 = 0; i10 < this.f14977m; i10++) {
            i7 = (i7 * 31) + this.l[i10];
        }
        return i7;
    }

    public final String toString() {
        int i7 = this.f14977m;
        if (i7 == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(i7 * 5);
        sb.append('[');
        int[] iArr = this.l;
        sb.append(iArr[0]);
        for (int i10 = 1; i10 < i7; i10++) {
            sb.append(", ");
            sb.append(iArr[i10]);
        }
        sb.append(']');
        return sb.toString();
    }
}
