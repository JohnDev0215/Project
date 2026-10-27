package p;

import android.graphics.Rect;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class lv0 implements Comparator {
    public final Rect c = new Rect();
    public final Rect e = new Rect();
    public final boolean k;
    public final u11 l;

    public lv0(boolean z, u11 u11Var) {
        this.k = z;
        this.l = u11Var;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        this.l.getClass();
        Rect rect = this.c;
        ((c3) obj).f(rect);
        Rect rect2 = this.e;
        ((c3) obj2).f(rect2);
        int i = rect.top;
        int i2 = rect2.top;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = rect.left;
        int i4 = rect2.left;
        boolean z = this.k;
        if (i3 < i4) {
            return z ? 1 : -1;
        }
        if (i3 > i4) {
            return z ? -1 : 1;
        }
        int i5 = rect.bottom;
        int i6 = rect2.bottom;
        if (i5 < i6) {
            return -1;
        }
        if (i5 > i6) {
            return 1;
        }
        int i7 = rect.right;
        int i8 = rect2.right;
        if (i7 < i8) {
            return z ? 1 : -1;
        }
        if (i7 > i8) {
            return z ? -1 : 1;
        }
        return 0;
    }
}
