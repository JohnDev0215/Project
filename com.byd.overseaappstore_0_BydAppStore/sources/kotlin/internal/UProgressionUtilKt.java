package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class UProgressionUtilKt {
    /* JADX INFO: renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m1275differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int iM411uintRemainderJ1ME1BU = UnsignedKt.m411uintRemainderJ1ME1BU(i, i3);
        int iM411uintRemainderJ1ME1BU2 = UnsignedKt.m411uintRemainderJ1ME1BU(i2, i3);
        int iUintCompare = UnsignedKt.uintCompare(iM411uintRemainderJ1ME1BU, iM411uintRemainderJ1ME1BU2);
        int iM157constructorimpl = UInt.m157constructorimpl(iM411uintRemainderJ1ME1BU - iM411uintRemainderJ1ME1BU2);
        return iUintCompare >= 0 ? iM157constructorimpl : UInt.m157constructorimpl(iM157constructorimpl + i3);
    }

    /* JADX INFO: renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m1276differenceModulosambcqE(long j, long j2, long j3) {
        long jM413ulongRemaindereb3DHEI = UnsignedKt.m413ulongRemaindereb3DHEI(j, j3);
        long jM413ulongRemaindereb3DHEI2 = UnsignedKt.m413ulongRemaindereb3DHEI(j2, j3);
        int iUlongCompare = UnsignedKt.ulongCompare(jM413ulongRemaindereb3DHEI, jM413ulongRemaindereb3DHEI2);
        long jM235constructorimpl = ULong.m235constructorimpl(jM413ulongRemaindereb3DHEI - jM413ulongRemaindereb3DHEI2);
        return iUlongCompare >= 0 ? jM235constructorimpl : ULong.m235constructorimpl(jM235constructorimpl + j3);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* JADX INFO: renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m1277getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        long jM1276differenceModulosambcqE;
        if (j3 > 0) {
            if (UnsignedKt.ulongCompare(j, j2) >= 0) {
                return j2;
            }
            jM1276differenceModulosambcqE = j2 - m1276differenceModulosambcqE(j2, j, ULong.m235constructorimpl(j3));
        } else {
            if (j3 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (UnsignedKt.ulongCompare(j, j2) <= 0) {
                return j2;
            }
            jM1276differenceModulosambcqE = j2 + m1276differenceModulosambcqE(j, j2, ULong.m235constructorimpl(-j3));
        }
        return ULong.m235constructorimpl(jM1276differenceModulosambcqE);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* JADX INFO: renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m1278getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        int iM1275differenceModuloWZ9TVnA;
        if (i3 > 0) {
            if (UnsignedKt.uintCompare(i, i2) >= 0) {
                return i2;
            }
            iM1275differenceModuloWZ9TVnA = i2 - m1275differenceModuloWZ9TVnA(i2, i, UInt.m157constructorimpl(i3));
        } else {
            if (i3 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (UnsignedKt.uintCompare(i, i2) <= 0) {
                return i2;
            }
            iM1275differenceModuloWZ9TVnA = i2 + m1275differenceModuloWZ9TVnA(i, i2, UInt.m157constructorimpl(-i3));
        }
        return UInt.m157constructorimpl(iM1275differenceModuloWZ9TVnA);
    }
}
