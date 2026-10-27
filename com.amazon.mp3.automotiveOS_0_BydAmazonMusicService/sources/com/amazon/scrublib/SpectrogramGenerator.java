package com.amazon.scrublib;

import com.amazon.scrublib.fft.Complex;
import com.amazon.scrublib.fft.Transformer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: SpectrogramGenerator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 )2\u00020\u0001:\u0001)B\u001f\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b'\u0010(J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002J\u001e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002R\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001d\u0010\u0014R\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010#\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006*"}, d2 = {"Lcom/amazon/scrublib/SpectrogramGenerator;", "", "", "samplingFrequency", "", "calculateStaticMultiplicationConstant", "", "createHanningWindow", "inputArray", "", "generateSpectrogram", "([D)[[D", "inputSize", "getClosestWindow", "startIndex", "endIndex", "generateSingleColumn", "windowSize", "I", "getWindowSize", "()I", "windowOverlap", "getWindowOverlap", "getSamplingFrequency", "hanningWindow", "[D", "getHanningWindow$DMPScrubCore_release", "()[D", "spectrogramHeight", "getSpectrogramHeight", "Lcom/amazon/scrublib/fft/Transformer;", "transformer", "Lcom/amazon/scrublib/fft/Transformer;", "getTransformer", "()Lcom/amazon/scrublib/fft/Transformer;", "staticMultiplicationConstant", "D", "getStaticMultiplicationConstant", "()D", "<init>", "(III)V", "Companion", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class SpectrogramGenerator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int samplingFrequency;
    private final int spectrogramHeight;
    private final double staticMultiplicationConstant;
    private final int windowOverlap;
    private final int windowSize;
    private final Transformer transformer = new Transformer();
    private final double[] hanningWindow = createHanningWindow();

    public SpectrogramGenerator(int i, int i2, int i3) {
        this.windowSize = i;
        this.windowOverlap = i2;
        this.samplingFrequency = i3;
        this.spectrogramHeight = (i / 2) + 1;
        this.staticMultiplicationConstant = calculateStaticMultiplicationConstant(i3);
    }

    public final int getWindowSize() {
        return this.windowSize;
    }

    public final int getWindowOverlap() {
        return this.windowOverlap;
    }

    public final int getSamplingFrequency() {
        return this.samplingFrequency;
    }

    public final int getSpectrogramHeight() {
        return this.spectrogramHeight;
    }

    public final double getStaticMultiplicationConstant() {
        return this.staticMultiplicationConstant;
    }

    private final double calculateStaticMultiplicationConstant(int samplingFrequency) {
        double dPow = 0.0d;
        for (double d : this.hanningWindow) {
            dPow += Math.pow(d, 2.0d);
        }
        return ((double) 1) / (dPow * ((double) samplingFrequency));
    }

    private final double[] createHanningWindow() {
        IntRange intRangeUntil = RangesKt___RangesKt.until(0, this.windowSize);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(Double.valueOf(((IntIterator) it).nextInt()));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Double.valueOf((((double) 1) - Math.cos((((Number) it2.next()).doubleValue() * 6.283185307179586d) / ((double) (this.windowSize - 1)))) * 0.5d));
        }
        return CollectionsKt___CollectionsKt.toDoubleArray(arrayList2);
    }

    public final double[][] generateSpectrogram(double[] inputArray) {
        Intrinsics.checkNotNullParameter(inputArray, "inputArray");
        int length = inputArray.length;
        int i = this.windowOverlap;
        int iCeil = (int) Math.ceil(((double) (length + i)) / ((double) (this.windowSize - i)));
        double[][] dArr = new double[iCeil][];
        for (int i2 = 0; i2 < iCeil; i2++) {
            dArr[i2] = new double[this.spectrogramHeight];
        }
        for (int i3 = 0; i3 < iCeil; i3++) {
            int i4 = this.windowSize;
            int i5 = (i4 - this.windowOverlap) * i3;
            double[] dArrGenerateSingleColumn = generateSingleColumn(inputArray, i5, Math.min(inputArray.length, i4 + i5));
            int i6 = this.spectrogramHeight;
            for (int i7 = 0; i7 < i6; i7++) {
                dArr[i3][i7] = dArrGenerateSingleColumn[i7] * this.staticMultiplicationConstant;
            }
        }
        return dArr;
    }

    public final int getClosestWindow(int inputSize) {
        int i = this.windowSize;
        if (inputSize < i) {
            return 0;
        }
        int i2 = this.windowOverlap;
        return (((inputSize - i) / (i - i2)) * (i - i2)) + i;
    }

    public final double[] generateSingleColumn(double[] inputArray, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(inputArray, "inputArray");
        int i = this.windowSize;
        double[] dArr = new double[i];
        int iMin = Math.min(i, endIndex - startIndex);
        for (int i2 = 0; i2 < iMin; i2++) {
            dArr[i2] = inputArray[i2 + startIndex] * this.hanningWindow[i2];
        }
        List listTake = ArraysKt___ArraysKt.take(this.transformer.memory_efficient_fft(dArr), this.spectrogramHeight);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listTake, 10));
        Iterator it = listTake.iterator();
        while (it.hasNext()) {
            arrayList.add(Double.valueOf(((Complex) it.next()).magnitudeSquared()));
        }
        double[] doubleArray = CollectionsKt___CollectionsKt.toDoubleArray(arrayList);
        int i3 = this.spectrogramHeight - 1;
        for (int i4 = 1; i4 < i3; i4++) {
            doubleArray[i4] = doubleArray[i4] * ((double) 2);
        }
        return doubleArray;
    }

    /* JADX INFO: compiled from: SpectrogramGenerator.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lcom/amazon/scrublib/SpectrogramGenerator$Companion;", "", "()V", "getTimeFromIdx", "", "idx", "samplingFrequency", "windowSize", "windowOverlap", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getTimeFromIdx(int idx, int samplingFrequency, int windowSize, int windowOverlap) {
            return (int) ((((double) MathKt__MathJVMKt.roundToInt(((double) (idx * (windowSize - windowOverlap))) + (((double) windowSize) / 2.0d))) * 1000.0d) / ((double) samplingFrequency));
        }
    }
}
