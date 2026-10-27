package q6;

import kotlin.jvm.internal.l;
import p6.x;

/* JADX INFO: compiled from: SegmentedByteString.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final int a(int[] binarySearch, int i7, int i8, int i9) {
        l.e(binarySearch, "$this$binarySearch");
        int i10 = i9 - 1;
        while (i8 <= i10) {
            int i11 = (i8 + i10) >>> 1;
            int i12 = binarySearch[i11];
            if (i12 < i7) {
                i8 = i11 + 1;
            } else {
                if (i12 <= i7) {
                    return i11;
                }
                i10 = i11 - 1;
            }
        }
        return (-i8) - 1;
    }

    public static final int b(x segment, int i7) {
        l.e(segment, "$this$segment");
        int iA = a(segment.w(), i7 + 1, 0, segment.x().length);
        return iA >= 0 ? iA : ~iA;
    }
}
