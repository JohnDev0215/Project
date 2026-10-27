package kotlin.internal;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;

/* JADX INFO: compiled from: UProgressionUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class UProgressionUtilKt {
    /* JADX INFO: renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m1471differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int iM607uintRemainderJ1ME1BU = UnsignedKt.m607uintRemainderJ1ME1BU(i, i3);
        int iM607uintRemainderJ1ME1BU2 = UnsignedKt.m607uintRemainderJ1ME1BU(i2, i3);
        int iUintCompare = UnsignedKt.uintCompare(iM607uintRemainderJ1ME1BU, iM607uintRemainderJ1ME1BU2);
        int iM353constructorimpl = UInt.m353constructorimpl(iM607uintRemainderJ1ME1BU - iM607uintRemainderJ1ME1BU2);
        return iUintCompare >= 0 ? iM353constructorimpl : UInt.m353constructorimpl(iM353constructorimpl + i3);
    }

    /* JADX INFO: renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m1472differenceModulosambcqE(long j, long j2, long j3) {
        long jM609ulongRemaindereb3DHEI = UnsignedKt.m609ulongRemaindereb3DHEI(j, j3);
        long jM609ulongRemaindereb3DHEI2 = UnsignedKt.m609ulongRemaindereb3DHEI(j2, j3);
        int iUlongCompare = UnsignedKt.ulongCompare(jM609ulongRemaindereb3DHEI, jM609ulongRemaindereb3DHEI2);
        long jM431constructorimpl = ULong.m431constructorimpl(jM609ulongRemaindereb3DHEI - jM609ulongRemaindereb3DHEI2);
        return iUlongCompare >= 0 ? jM431constructorimpl : ULong.m431constructorimpl(jM431constructorimpl + j3);
    }

    /* JADX INFO: renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m1474getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            return UnsignedKt.uintCompare(i, i2) >= 0 ? i2 : UInt.m353constructorimpl(i2 - m1471differenceModuloWZ9TVnA(i2, i, UInt.m353constructorimpl(i3)));
        }
        if (i3 < 0) {
            return UnsignedKt.uintCompare(i, i2) <= 0 ? i2 : UInt.m353constructorimpl(i2 + m1471differenceModuloWZ9TVnA(i, i2, UInt.m353constructorimpl(-i3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    /* JADX INFO: renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m1473getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        if (j3 > 0) {
            return UnsignedKt.ulongCompare(j, j2) >= 0 ? j2 : ULong.m431constructorimpl(j2 - m1472differenceModulosambcqE(j2, j, ULong.m431constructorimpl(j3)));
        }
        if (j3 < 0) {
            return UnsignedKt.ulongCompare(j, j2) <= 0 ? j2 : ULong.m431constructorimpl(j2 + m1472differenceModulosambcqE(j, j2, ULong.m431constructorimpl(-j3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
