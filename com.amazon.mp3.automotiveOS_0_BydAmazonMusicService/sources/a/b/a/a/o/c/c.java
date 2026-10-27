package a.b.a.a.o.c;

import android.media.MediaCodecInfo;
import android.util.Range;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public MediaCodecInfo.VideoCapabilities f95a;

    @RequiresApi(21)
    public final Range<Double> a(int i, int i2) {
        Range<Double> supportedFrameRatesFor;
        MediaCodecInfo.VideoCapabilities videoCapabilities = this.f95a;
        if (videoCapabilities != null && (supportedFrameRatesFor = videoCapabilities.getSupportedFrameRatesFor(i, i2)) != null) {
            return supportedFrameRatesFor;
        }
        Range<Double> rangeCreate = Range.create(Double.valueOf(0.0d), Double.valueOf(0.0d));
        Intrinsics.checkExpressionValueIsNotNull(rangeCreate, "Range.create(0.0, 0.0)");
        return rangeCreate;
    }

    public final boolean b(int i, int i2) {
        MediaCodecInfo.VideoCapabilities videoCapabilities = this.f95a;
        return videoCapabilities != null && videoCapabilities.isSizeSupported(i, i2);
    }
}
