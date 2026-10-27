package com.amazon.scrublib;

import com.amazon.scrublib.models.PeakRaw;
import com.amazon.scrublib.models.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PeakFinder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J#\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0002\u0010\nJ'\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ-\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b¢\u0006\u0002\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/amazon/scrublib/PeakFinder;", "", "()V", "convertPointToPeak", "Lcom/amazon/scrublib/models/PeakRaw;", "point", "Lcom/amazon/scrublib/models/Point;", "spectrum", "", "", "(Lcom/amazon/scrublib/models/Point;[[D)Lcom/amazon/scrublib/models/PeakRaw;", "generatePeakFromSpectrogram", "", "minimumPeakHeight", "", "([[DD)Ljava/util/List;", "getPointMaxima", "kernel", "", "([[D[[Z)Ljava/util/List;", "Companion", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PeakFinder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final boolean[][] kernel;

    public final List<PeakRaw> generatePeakFromSpectrogram(double[][] spectrum, double minimumPeakHeight) {
        Intrinsics.checkNotNullParameter(spectrum, "spectrum");
        List<Point> pointMaxima = getPointMaxima(spectrum, kernel);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(pointMaxima, 10));
        Iterator<T> it = pointMaxima.iterator();
        while (it.hasNext()) {
            arrayList.add(convertPointToPeak((Point) it.next(), spectrum));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            Double amplitude = ((PeakRaw) obj).getAmplitude();
            Intrinsics.checkNotNull(amplitude);
            if (amplitude.doubleValue() >= minimumPeakHeight) {
                arrayList2.add(obj);
            }
        }
        return CollectionsKt___CollectionsKt.toList(arrayList2);
    }

    private final PeakRaw convertPointToPeak(Point point, double[][] spectrum) {
        return new PeakRaw(Integer.valueOf(point.getX()), Double.valueOf(spectrum[point.getX()][point.getY()]), Integer.valueOf(point.getY()));
    }

    public final List<Point> getPointMaxima(double[][] spectrum, boolean[][] kernel2) {
        Intrinsics.checkNotNullParameter(spectrum, "spectrum");
        Intrinsics.checkNotNullParameter(kernel2, "kernel");
        int length = kernel2.length;
        ArrayList arrayList = new ArrayList();
        int iFloor = (int) Math.floor(kernel2.length / 2);
        int length2 = spectrum.length;
        int length3 = spectrum[0].length;
        for (int i = 0; i < length2; i++) {
            for (int i2 = 0; i2 < length3; i2++) {
                double d = -1.0d;
                for (int i3 = 0; i3 < length; i3++) {
                    for (int i4 = 0; i4 < length; i4++) {
                        int i5 = (i + i3) - iFloor;
                        int i6 = (i2 + i4) - iFloor;
                        if (i5 >= 0 && i5 < length2 && i6 >= 0 && i6 < length3 && kernel2[i3][i4]) {
                            double d2 = spectrum[i5][i6];
                            if (d2 >= d) {
                                d = d2;
                            }
                        }
                    }
                }
                if (d == spectrum[i][i2]) {
                    arrayList.add(new Point(i, i2));
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: PeakFinder.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/scrublib/PeakFinder$Companion;", "", "()V", "kernel", "", "", "[[Z", "createKernel", "size", "", "(I)[[Z", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean[][] createKernel(int size) {
            boolean[][] zArr = new boolean[size][];
            for (int i = 0; i < size; i++) {
                zArr[i] = new boolean[size];
            }
            for (int i2 = 0; i2 < size; i2++) {
                for (int i3 = 0; i3 < size; i3++) {
                    double d = ((double) size) / 2.0d;
                    if (Math.abs((((double) i2) - d) + 0.5d) + Math.abs((((double) i3) - d) + 0.5d) <= d) {
                        zArr[i2][i3] = true;
                    }
                }
            }
            return zArr;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        kernel = companion.createKernel(21);
    }
}
