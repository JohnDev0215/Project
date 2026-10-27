package kotlin.internal;

import com.chad.library.BuildConfig;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;

/* JADX INFO: compiled from: UProgressionUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", BuildConfig.FLAVOR, "getProgressionLastElement-Nkh28Cs", BuildConfig.FLAVOR, "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class UProgressionUtilKt {
    /* JADX INFO: renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m1278differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int iM414uintRemainderJ1ME1BU = UnsignedKt.m414uintRemainderJ1ME1BU(i, i3);
        int iM414uintRemainderJ1ME1BU2 = UnsignedKt.m414uintRemainderJ1ME1BU(i2, i3);
        int iUintCompare = UnsignedKt.uintCompare(iM414uintRemainderJ1ME1BU, iM414uintRemainderJ1ME1BU2);
        int iM160constructorimpl = UInt.m160constructorimpl(iM414uintRemainderJ1ME1BU - iM414uintRemainderJ1ME1BU2);
        return iUintCompare >= 0 ? iM160constructorimpl : UInt.m160constructorimpl(iM160constructorimpl + i3);
    }

    /* JADX INFO: renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m1279differenceModulosambcqE(long j, long j2, long j3) {
        long jM416ulongRemaindereb3DHEI = UnsignedKt.m416ulongRemaindereb3DHEI(j, j3);
        long jM416ulongRemaindereb3DHEI2 = UnsignedKt.m416ulongRemaindereb3DHEI(j2, j3);
        int iUlongCompare = UnsignedKt.ulongCompare(jM416ulongRemaindereb3DHEI, jM416ulongRemaindereb3DHEI2);
        long jM238constructorimpl = ULong.m238constructorimpl(jM416ulongRemaindereb3DHEI - jM416ulongRemaindereb3DHEI2);
        return iUlongCompare >= 0 ? jM238constructorimpl : ULong.m238constructorimpl(jM238constructorimpl + j3);
    }

    /* JADX INFO: renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m1281getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            return UnsignedKt.uintCompare(i, i2) >= 0 ? i2 : UInt.m160constructorimpl(i2 - m1278differenceModuloWZ9TVnA(i2, i, UInt.m160constructorimpl(i3)));
        }
        if (i3 < 0) {
            return UnsignedKt.uintCompare(i, i2) <= 0 ? i2 : UInt.m160constructorimpl(i2 + m1278differenceModuloWZ9TVnA(i, i2, UInt.m160constructorimpl(-i3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    /* JADX INFO: renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m1280getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        if (j3 > 0) {
            return UnsignedKt.ulongCompare(j, j2) >= 0 ? j2 : ULong.m238constructorimpl(j2 - m1279differenceModulosambcqE(j2, j, ULong.m238constructorimpl(j3)));
        }
        if (j3 < 0) {
            return UnsignedKt.ulongCompare(j, j2) <= 0 ? j2 : ULong.m238constructorimpl(j2 + m1279differenceModulosambcqE(j, j2, ULong.m238constructorimpl(-j3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
