package L1;

import Y4.e;
import android.graphics.Rect;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Comparator {
    public final Rect l = new Rect();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Rect f3977m = new Rect();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f3978n;
    public final e o;

    public b(boolean z6, e eVar) {
        this.f3978n = z6;
        this.o = eVar;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        this.o.getClass();
        Rect rect = this.l;
        ((G1.e) obj).f(rect);
        Rect rect2 = this.f3977m;
        ((G1.e) obj2).f(rect2);
        int i7 = rect.top;
        int i10 = rect2.top;
        if (i7 < i10) {
            return -1;
        }
        if (i7 > i10) {
            return 1;
        }
        int i11 = rect.left;
        int i12 = rect2.left;
        boolean z6 = this.f3978n;
        if (i11 < i12) {
            return z6 ? 1 : -1;
        }
        if (i11 > i12) {
            return z6 ? -1 : 1;
        }
        int i13 = rect.bottom;
        int i14 = rect2.bottom;
        if (i13 < i14) {
            return -1;
        }
        if (i13 > i14) {
            return 1;
        }
        int i15 = rect.right;
        int i16 = rect2.right;
        if (i15 < i16) {
            return z6 ? 1 : -1;
        }
        if (i15 > i16) {
            return z6 ? -1 : 1;
        }
        return 0;
    }
}
