package kotlin.collections;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UArraySorting.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class UArraySortingKt {
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m3258partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM2885getw2LRezQ = UByteArray.m2885getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = bM2885getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m2885getw2LRezQ(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m2885getw2LRezQ(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM2885getw2LRezQ2 = UByteArray.m2885getw2LRezQ(bArr, i);
                UByteArray.m2890setVurrAj0(bArr, i, UByteArray.m2885getw2LRezQ(bArr, i2));
                UByteArray.m2890setVurrAj0(bArr, i2, bM2885getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m3262quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM3258partition4UcCI2c = m3258partition4UcCI2c(bArr, i, i2);
        int i3 = iM3258partition4UcCI2c - 1;
        if (i < i3) {
            m3262quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM3258partition4UcCI2c < i2) {
            m3262quickSort4UcCI2c(bArr, iM3258partition4UcCI2c, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m3259partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM3145getMh2AYeg = UShortArray.m3145getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int iM3145getMh2AYeg = UShortArray.m3145getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = sM3145getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(iM3145getMh2AYeg, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m3145getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM3145getMh2AYeg2 = UShortArray.m3145getMh2AYeg(sArr, i);
                UShortArray.m3150set01HTLdE(sArr, i, UShortArray.m3145getMh2AYeg(sArr, i2));
                UShortArray.m3150set01HTLdE(sArr, i2, sM3145getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m3263quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM3259partitionAa5vz7o = m3259partitionAa5vz7o(sArr, i, i2);
        int i3 = iM3259partitionAa5vz7o - 1;
        if (i < i3) {
            m3263quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM3259partitionAa5vz7o < i2) {
            m3263quickSortAa5vz7o(sArr, iM3259partitionAa5vz7o, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m3260partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM2963getpVg5ArA = UIntArray.m2963getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.uintCompare(UIntArray.m2963getpVg5ArA(iArr, i), iM2963getpVg5ArA) < 0) {
                i++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m2963getpVg5ArA(iArr, i2), iM2963getpVg5ArA) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM2963getpVg5ArA2 = UIntArray.m2963getpVg5ArA(iArr, i);
                UIntArray.m2968setVXSXFK8(iArr, i, UIntArray.m2963getpVg5ArA(iArr, i2));
                UIntArray.m2968setVXSXFK8(iArr, i2, iM2963getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m3264quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM3260partitionoBK06Vg = m3260partitionoBK06Vg(iArr, i, i2);
        int i3 = iM3260partitionoBK06Vg - 1;
        if (i < i3) {
            m3264quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM3260partitionoBK06Vg < i2) {
            m3264quickSortoBK06Vg(iArr, iM3260partitionoBK06Vg, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m3257partitionnroSd4(long[] jArr, int i, int i2) {
        long jM3041getsVKNKU = ULongArray.m3041getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.ulongCompare(ULongArray.m3041getsVKNKU(jArr, i), jM3041getsVKNKU) < 0) {
                i++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m3041getsVKNKU(jArr, i2), jM3041getsVKNKU) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM3041getsVKNKU2 = ULongArray.m3041getsVKNKU(jArr, i);
                ULongArray.m3046setk8EXiF4(jArr, i, ULongArray.m3041getsVKNKU(jArr, i2));
                ULongArray.m3046setk8EXiF4(jArr, i2, jM3041getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m3261quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM3257partitionnroSd4 = m3257partitionnroSd4(jArr, i, i2);
        int i3 = iM3257partitionnroSd4 - 1;
        if (i < i3) {
            m3261quickSortnroSd4(jArr, i, i3);
        }
        if (iM3257partitionnroSd4 < i2) {
            m3261quickSortnroSd4(jArr, iM3257partitionnroSd4, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m3266sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m3262quickSort4UcCI2c(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m3267sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m3263quickSortAa5vz7o(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m3268sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m3264quickSortoBK06Vg(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m3265sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m3261quickSortnroSd4(array, i, i2 - 1);
    }
}
