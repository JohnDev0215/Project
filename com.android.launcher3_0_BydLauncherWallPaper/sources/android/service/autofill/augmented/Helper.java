package android.service.autofill.augmented;

import android.content.ComponentName;
import android.metrics.LogMaker;
import com.android.internal.logging.MetricsLogger;

/* JADX INFO: loaded from: classes3.dex */
public final class Helper {
    private static final MetricsLogger sMetricsLogger = new MetricsLogger();

    public static void logResponse(int i, String str, ComponentName componentName, int i2, long j) {
        sMetricsLogger.write(new LogMaker(1724).setType(i).setComponentName(componentName).addTaggedData(1456, Integer.valueOf(i2)).addTaggedData(908, str).addTaggedData(1145, Long.valueOf(j)));
    }

    private Helper() {
        throw new UnsupportedOperationException("contains only static methods");
    }
}
