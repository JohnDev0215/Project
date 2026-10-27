package com.amazon.scrublib.fft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: Transformer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0002J\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/amazon/scrublib/fft/Transformer;", "", "", "dataR", "dataI", "", "_fft_in_place", "a", "b", "bitReversalShuffle2", "", "Lcom/amazon/scrublib/fft/Complex;", "memory_efficient_fft", "([D)[Lcom/amazon/scrublib/fft/Complex;", "data", "transform", "<init>", "()V", "Companion", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class Transformer {
    private static final double[] W_SUB_N_I;
    private static final String[] W_SUB_N_I_str;
    private static final double[] W_SUB_N_R;
    private static final String[] W_SUB_N_R_str;

    public final Complex[] memory_efficient_fft(double[] a2) {
        Intrinsics.checkNotNullParameter(a2, "a");
        return transform(a2);
    }

    static {
        String[] strArr = {"0x1.0p0", "-0x1.0p0", "0x1.1a62633145c07p-54", "0x1.6a09e667f3bcdp-1 ", "0x1.d906bcf328d46p-1", "0x1.f6297cff75cbp-1", "0x1.fd88da3d12526p-1", "0x1.ff621e3796d7ep-1 ", "0x1.ffd886084cd0dp-1", "0x1.fff62169b92dbp-1", "0x1.fffd8858e8a92p-1", "0x1.ffff621621d02p-1 ", "0x1.ffffd88586ee6p-1", "0x1.fffff62161a34p-1", "0x1.fffffd8858675p-1", "0x1.ffffff621619cp-1 ", "0x1.ffffffd885867p-1", "0x1.fffffff62161ap-1", "0x1.fffffffd88586p-1", "0x1.ffffffff62162p-1 ", "0x1.ffffffffd8858p-1", "0x1.fffffffff6216p-1", "0x1.fffffffffd886p-1", "0x1.ffffffffff621p-1 ", "0x1.ffffffffffd88p-1", "0x1.fffffffffff62p-1", "0x1.fffffffffffd9p-1", "0x1.ffffffffffff6p-1 ", "0x1.ffffffffffffep-1", "0x1.fffffffffffffp-1", "0x1.0p0", "0x1.0p0 ", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0 ", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0 ", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0 ", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0 ", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0 ", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0 ", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0", "0x1.0p0"};
        W_SUB_N_R_str = strArr;
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(Double.valueOf(Double.parseDouble(str)));
        }
        W_SUB_N_R = CollectionsKt___CollectionsKt.toDoubleArray(arrayList);
        String[] strArr2 = {"0x1.1a62633145c07p-52", "-0x1.1a62633145c07p-53", "-0x1.0p0", "-0x1.6a09e667f3bccp-1", "-0x1.87de2a6aea963p-2", "-0x1.8f8b83c69a60ap-3", "-0x1.917a6bc29b42cp-4", "-0x1.91f65f10dd814p-5", "-0x1.92155f7a3667ep-6", "-0x1.921d1fcdec784p-7", "-0x1.921f0fe670071p-8", "-0x1.921f8becca4bap-9", "-0x1.921faaee6472dp-10", "-0x1.921fb2aecb36p-11", "-0x1.921fb49ee4ea6p-12", "-0x1.921fb51aeb57bp-13", "-0x1.921fb539ecf31p-14", "-0x1.921fb541ad59ep-15", "-0x1.921fb5439d73ap-16", "-0x1.921fb544197ap-17", "-0x1.921fb544387bap-18", "-0x1.921fb544403c1p-19", "-0x1.921fb544422c2p-20", "-0x1.921fb54442a83p-21", "-0x1.921fb54442c73p-22", "-0x1.921fb54442cefp-23", "-0x1.921fb54442d0ep-24", "-0x1.921fb54442d15p-25", "-0x1.921fb54442d17p-26", "-0x1.921fb54442d18p-27", "-0x1.921fb54442d18p-28", "-0x1.921fb54442d18p-29", "-0x1.921fb54442d18p-30", "-0x1.921fb54442d18p-31", "-0x1.921fb54442d18p-32", "-0x1.921fb54442d18p-33", "-0x1.921fb54442d18p-34", "-0x1.921fb54442d18p-35", "-0x1.921fb54442d18p-36", "-0x1.921fb54442d18p-37", "-0x1.921fb54442d18p-38", "-0x1.921fb54442d18p-39", "-0x1.921fb54442d18p-40", "-0x1.921fb54442d18p-41", "-0x1.921fb54442d18p-42", "-0x1.921fb54442d18p-43", "-0x1.921fb54442d18p-44", "-0x1.921fb54442d18p-45", "-0x1.921fb54442d18p-46", "-0x1.921fb54442d18p-47", "-0x1.921fb54442d18p-48", "-0x1.921fb54442d18p-49", "-0x1.921fb54442d18p-50", "-0x1.921fb54442d18p-51", "-0x1.921fb54442d18p-52", "-0x1.921fb54442d18p-53", "-0x1.921fb54442d18p-54", "-0x1.921fb54442d18p-55", "-0x1.921fb54442d18p-56", "-0x1.921fb54442d18p-57", "-0x1.921fb54442d18p-58", "-0x1.921fb54442d18p-59", "-0x1.921fb54442d18p-60"};
        W_SUB_N_I_str = strArr2;
        ArrayList arrayList2 = new ArrayList(strArr2.length);
        for (String str2 : strArr2) {
            arrayList2.add(Double.valueOf(Double.parseDouble(str2)));
        }
        W_SUB_N_I = CollectionsKt___CollectionsKt.toDoubleArray(arrayList2);
    }

    public final Complex[] transform(double[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        double[] dArrCopyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "copyOf(this, size)");
        double[] dArr = new double[dArrCopyOf.length];
        _fft_in_place(dArrCopyOf, dArr);
        List<Pair> listZip = ArraysKt___ArraysKt.zip(dArrCopyOf, dArr);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listZip, 10));
        for (Pair pair : listZip) {
            arrayList.add(new Complex(((Number) pair.getFirst()).doubleValue(), ((Number) pair.getSecond()).doubleValue()));
        }
        Object[] array = arrayList.toArray(new Complex[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return (Complex[]) array;
    }

    private final void _fft_in_place(double[] dataR, double[] dataI) {
        int length = dataR.length;
        bitReversalShuffle2(dataR, dataI);
        int i = 0;
        int i2 = 4;
        IntProgression intProgressionStep = RangesKt___RangesKt.step(RangesKt___RangesKt.until(0, length), 4);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (true) {
                int i3 = first + 1;
                int i4 = first + 2;
                int i5 = first + 3;
                double d = dataR[first];
                double d2 = dataI[first];
                double d3 = dataR[i4];
                double d4 = dataI[i4];
                double d5 = dataR[i3];
                double d6 = dataI[i3];
                double d7 = dataR[i5];
                double d8 = dataI[i5];
                dataR[first] = d + d3 + d5 + d7;
                dataI[first] = d2 + d4 + d6 + d8;
                double d9 = d - d5;
                dataR[i3] = d9 + (d4 - d8);
                double d10 = d2 - d6;
                dataI[i3] = d10 + (d7 - d3);
                dataR[i4] = ((d - d3) + d5) - d7;
                dataI[i4] = ((d2 - d4) + d6) - d8;
                dataR[i5] = d9 + (d8 - d4);
                dataI[i5] = d10 + (d3 - d7);
                if (first == last) {
                    break;
                } else {
                    first += step;
                }
            }
        }
        int i6 = 2;
        while (i2 < length) {
            int i7 = i2 << 1;
            i6++;
            double d11 = W_SUB_N_R[i6];
            double d12 = W_SUB_N_I[i6];
            IntProgression intProgressionStep2 = RangesKt___RangesKt.step(RangesKt___RangesKt.until(i, length), i7);
            int first2 = intProgressionStep2.getFirst();
            int last2 = intProgressionStep2.getLast();
            int step2 = intProgressionStep2.getStep();
            if ((step2 > 0 && first2 <= last2) || (step2 < 0 && last2 <= first2)) {
                while (true) {
                    int i8 = first2 + i2;
                    double d13 = 1.0d;
                    double d14 = 0.0d;
                    while (i < i2) {
                        int i9 = first2 + i;
                        double d15 = dataR[i9];
                        double d16 = dataI[i9];
                        int i10 = i8 + i;
                        double d17 = dataR[i10];
                        double d18 = dataI[i10];
                        double d19 = d13 * d17;
                        double d20 = d14 * d18;
                        dataR[i9] = (d15 + d19) - d20;
                        double d21 = d18 * d13;
                        double d22 = d17 * d14;
                        dataI[i9] = d16 + d21 + d22;
                        dataR[i10] = d15 - (d19 - d20);
                        dataI[i10] = d16 - (d21 + d22);
                        double d23 = (d13 * d11) - (d14 * d12);
                        d14 = (d13 * d12) + (d14 * d11);
                        i++;
                        d13 = d23;
                    }
                    if (first2 != last2) {
                        first2 += step2;
                        i = 0;
                    }
                }
            }
            i2 = i7;
            i = 0;
        }
    }

    private final void bitReversalShuffle2(double[] a2, double[] b) {
        int length = a2.length;
        int i = length >> 1;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 < i2) {
                double d = a2[i3];
                a2[i3] = a2[i2];
                a2[i2] = d;
                double d2 = b[i3];
                b[i3] = b[i2];
                b[i2] = d2;
            }
            int i4 = i;
            while (i4 <= i2 && i4 > 0) {
                i2 -= i4;
                i4 >>= 1;
            }
            i2 += i4;
        }
    }
}
