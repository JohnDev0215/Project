package com.android.car.settings.storage;

import android.content.Context;
import android.content.res.Resources;
import android.text.BidiFormatter;
import android.text.format.Formatter;
import androidx.mediarouter.media.SystemMediaRouteProvider;

/* JADX INFO: loaded from: classes5.dex */
public final class FileSizeFormatter {
    public static final long GIGABYTE_IN_BYTES = 1000000000;
    public static final long KILOBYTE_IN_BYTES = 1000;
    public static final long MEGABYTE_IN_BYTES = 1000000;

    private FileSizeFormatter() {
    }

    public static String formatFileSize(Context context, long sizeBytes, int suffix, long mult) {
        Formatter.BytesResult res = formatBytes(context.getResources(), sizeBytes, suffix, mult);
        return BidiFormatter.getInstance().unicodeWrap(context.getString(getFileSizeSuffix(context), res.value, res.units));
    }

    private static int getFileSizeSuffix(Context context) {
        Resources res = context.getResources();
        return res.getIdentifier("fileSizeSuffix", "string", SystemMediaRouteProvider.PACKAGE_NAME);
    }

    private static Formatter.BytesResult formatBytes(Resources res, long sizeBytes, int suffix, long mult) {
        int roundFactor;
        String roundFormat;
        boolean isNegative = sizeBytes < 0;
        float result = (isNegative ? -sizeBytes : sizeBytes) / mult;
        if (mult == 1) {
            roundFactor = 1;
            roundFormat = "%.0f";
        } else if (result < 1.0f) {
            roundFactor = 100;
            roundFormat = "%.2f";
        } else if (result < 10.0f) {
            roundFactor = 10;
            roundFormat = "%.1f";
        } else {
            roundFactor = 1;
            roundFormat = "%.0f";
        }
        if (isNegative) {
            result = -result;
        }
        String roundedString = String.format(roundFormat, Float.valueOf(result));
        long roundedBytes = (((long) Math.round(roundFactor * result)) * mult) / ((long) roundFactor);
        String units = res.getString(suffix);
        return new Formatter.BytesResult(roundedString, units, roundedBytes);
    }
}
