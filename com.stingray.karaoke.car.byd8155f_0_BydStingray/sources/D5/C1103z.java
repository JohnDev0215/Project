package d5;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: renamed from: d5.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1103z extends b0 implements Serializable {
    public final Comparator[] l;

    public C1103z(C1094p c1094p, C1094p c1094p2) {
        this.l = new Comparator[]{c1094p, c1094p2};
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int i7 = 0;
        while (true) {
            Comparator[] comparatorArr = this.l;
            if (i7 >= comparatorArr.length) {
                return 0;
            }
            int iCompare = comparatorArr[i7].compare(obj, obj2);
            if (iCompare != 0) {
                return iCompare;
            }
            i7++;
        }
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C1103z) {
            return Arrays.equals(this.l, ((C1103z) obj).l);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.l);
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("Ordering.compound("), Arrays.toString(this.l), ")");
    }
}
