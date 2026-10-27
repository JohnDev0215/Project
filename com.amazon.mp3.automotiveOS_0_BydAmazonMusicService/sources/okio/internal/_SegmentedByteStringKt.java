package okio.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.SegmentedByteString;

/* JADX INFO: compiled from: -SegmentedByteString.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0005\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0000¨\u0006\t"}, d2 = {"", "", "value", "fromIndex", "toIndex", "binarySearch", "Lokio/SegmentedByteString;", "pos", "segment", "okio"}, k = 2, mv = {1, 5, 1})
public final class _SegmentedByteStringKt {
    public static final int binarySearch(int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i) {
                i2 = i5 + 1;
            } else {
                if (i6 <= i) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return (-i2) - 1;
    }

    public static final int segment(SegmentedByteString segmentedByteString, int i) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        int iBinarySearch = binarySearch(segmentedByteString.getDirectory(), i + 1, 0, segmentedByteString.getSegments().length);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }
}
