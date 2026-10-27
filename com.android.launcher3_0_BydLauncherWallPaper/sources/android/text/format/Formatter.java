package android.text.format;

import android.content.Context;
import android.icu.text.MeasureFormat;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.net.NetworkUtils;
import android.text.BidiFormatter;
import android.text.TextUtils;
import com.android.internal.R;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class Formatter {
    public static final int FLAG_CALCULATE_ROUNDED = 2;
    public static final int FLAG_IEC_UNITS = 8;
    public static final int FLAG_SHORTER = 1;
    public static final int FLAG_SI_UNITS = 4;
    private static final int MILLIS_PER_MINUTE = 60000;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;

    public static class BytesResult {
        public final long roundedBytes;
        public final String units;
        public final String value;

        public BytesResult(String str, String str2, long j) {
            this.value = str;
            this.units = str2;
            this.roundedBytes = j;
        }
    }

    private static Locale localeFromContext(Context context) {
        return context.getResources().getConfiguration().getLocales().get(0);
    }

    private static String bidiWrap(Context context, String str) {
        return TextUtils.getLayoutDirectionFromLocale(localeFromContext(context)) == 1 ? BidiFormatter.getInstance(true).unicodeWrap(str) : str;
    }

    public static String formatFileSize(Context context, long j) {
        return formatFileSize(context, j, 4);
    }

    public static String formatFileSize(Context context, long j, int i) {
        if (context == null) {
            return "";
        }
        BytesResult bytes = formatBytes(context.getResources(), j, i);
        return bidiWrap(context, context.getString(R.string.fileSizeSuffix, bytes.value, bytes.units));
    }

    public static String formatShortFileSize(Context context, long j) {
        if (context == null) {
            return "";
        }
        BytesResult bytes = formatBytes(context.getResources(), j, 5);
        return bidiWrap(context, context.getString(R.string.fileSizeSuffix, bytes.value, bytes.units));
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.text.format.Formatter.BytesResult formatBytes(android.content.res.Resources r16, long r17, int r19) {
        /*
            r0 = r17
            r2 = r19 & 8
            if (r2 == 0) goto L9
            r2 = 1024(0x400, float:1.435E-42)
            goto Lb
        L9:
            r2 = 1000(0x3e8, float:1.401E-42)
        Lb:
            r3 = 0
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            r6 = 0
            r7 = 1
            if (r5 >= 0) goto L15
            r5 = r7
            goto L16
        L15:
            r5 = r6
        L16:
            if (r5 == 0) goto L19
            long r0 = -r0
        L19:
            float r0 = (float) r0
            r1 = 17039774(0x104019e, float:2.424573E-38)
            r8 = 1147207680(0x44610000, float:900.0)
            int r9 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            r10 = 1
            if (r9 <= 0) goto L2c
            r1 = 17040419(0x1040423, float:2.424754E-38)
            long r12 = (long) r2
            float r9 = (float) r2
            float r0 = r0 / r9
            goto L2d
        L2c:
            r12 = r10
        L2d:
            int r9 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r9 <= 0) goto L38
            r1 = 17040598(0x10404d6, float:2.424804E-38)
            long r14 = (long) r2
            long r12 = r12 * r14
            float r9 = (float) r2
            float r0 = r0 / r9
        L38:
            int r9 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r9 <= 0) goto L43
            r1 = 17040240(0x1040370, float:2.4247037E-38)
            long r14 = (long) r2
            long r12 = r12 * r14
            float r9 = (float) r2
            float r0 = r0 / r9
        L43:
            int r9 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r9 <= 0) goto L4e
            r1 = 17041357(0x10407cd, float:2.4250168E-38)
            long r14 = (long) r2
            long r12 = r12 * r14
            float r9 = (float) r2
            float r0 = r0 / r9
        L4e:
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r8 <= 0) goto L59
            r1 = 17041041(0x1040691, float:2.4249282E-38)
            long r8 = (long) r2
            long r12 = r12 * r8
            float r2 = (float) r2
            float r0 = r0 / r2
        L59:
            int r2 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            java.lang.String r8 = "%.0f"
            java.lang.String r9 = "%.2f"
            r10 = 100
            if (r2 == 0) goto L85
            r2 = 1120403456(0x42c80000, float:100.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L6a
            goto L85
        L6a:
            r2 = 1065353216(0x3f800000, float:1.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L72
        L70:
            r8 = r9
            goto L86
        L72:
            r2 = 1092616192(0x41200000, float:10.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L81
            r2 = r19 & 1
            if (r2 == 0) goto L70
            r10 = 10
            java.lang.String r8 = "%.1f"
            goto L86
        L81:
            r2 = r19 & 1
            if (r2 == 0) goto L70
        L85:
            r10 = r7
        L86:
            if (r5 == 0) goto L89
            float r0 = -r0
        L89:
            java.lang.Object[] r2 = new java.lang.Object[r7]
            java.lang.Float r5 = java.lang.Float.valueOf(r0)
            r2[r6] = r5
            java.lang.String r2 = java.lang.String.format(r8, r2)
            r5 = r19 & 2
            if (r5 != 0) goto L9a
            goto La4
        L9a:
            float r3 = (float) r10
            float r0 = r0 * r3
            int r0 = java.lang.Math.round(r0)
            long r3 = (long) r0
            long r3 = r3 * r12
            long r5 = (long) r10
            long r3 = r3 / r5
        La4:
            r0 = r16
            java.lang.String r0 = r0.getString(r1)
            android.text.format.Formatter$BytesResult r1 = new android.text.format.Formatter$BytesResult
            r1.<init>(r2, r0, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.format.Formatter.formatBytes(android.content.res.Resources, long, int):android.text.format.Formatter$BytesResult");
    }

    @Deprecated
    public static String formatIpAddress(int i) {
        return NetworkUtils.intToInetAddress(i).getHostAddress();
    }

    public static String formatShortElapsedTime(Context context, long j) {
        int i;
        int i2;
        int i3;
        long j2 = j / 1000;
        if (j2 >= 86400) {
            i = (int) (j2 / 86400);
            j2 -= (long) (SECONDS_PER_DAY * i);
        } else {
            i = 0;
        }
        if (j2 >= 3600) {
            i2 = (int) (j2 / 3600);
            j2 -= (long) (i2 * 3600);
        } else {
            i2 = 0;
        }
        if (j2 >= 60) {
            i3 = (int) (j2 / 60);
            j2 -= (long) (i3 * 60);
        } else {
            i3 = 0;
        }
        int i4 = (int) j2;
        MeasureFormat measureFormat = MeasureFormat.getInstance(localeFromContext(context), MeasureFormat.FormatWidth.SHORT);
        if (i >= 2 || (i > 0 && i2 == 0)) {
            return measureFormat.format(new Measure(Integer.valueOf(i + ((i2 + 12) / 24)), MeasureUnit.DAY));
        }
        if (i > 0) {
            return measureFormat.formatMeasures(new Measure(Integer.valueOf(i), MeasureUnit.DAY), new Measure(Integer.valueOf(i2), MeasureUnit.HOUR));
        }
        if (i2 >= 2 || (i2 > 0 && i3 == 0)) {
            return measureFormat.format(new Measure(Integer.valueOf(i2 + ((i3 + 30) / 60)), MeasureUnit.HOUR));
        }
        if (i2 > 0) {
            return measureFormat.formatMeasures(new Measure(Integer.valueOf(i2), MeasureUnit.HOUR), new Measure(Integer.valueOf(i3), MeasureUnit.MINUTE));
        }
        if (i3 >= 2 || (i3 > 0 && i4 == 0)) {
            return measureFormat.format(new Measure(Integer.valueOf(i3 + ((i4 + 30) / 60)), MeasureUnit.MINUTE));
        }
        if (i3 > 0) {
            return measureFormat.formatMeasures(new Measure(Integer.valueOf(i3), MeasureUnit.MINUTE), new Measure(Integer.valueOf(i4), MeasureUnit.SECOND));
        }
        return measureFormat.format(new Measure(Integer.valueOf(i4), MeasureUnit.SECOND));
    }

    public static String formatShortElapsedTimeRoundingUpToMinutes(Context context, long j) {
        long j2 = ((j + 60000) - 1) / 60000;
        if (j2 == 0 || j2 == 1) {
            return MeasureFormat.getInstance(localeFromContext(context), MeasureFormat.FormatWidth.SHORT).format(new Measure(Long.valueOf(j2), MeasureUnit.MINUTE));
        }
        return formatShortElapsedTime(context, j2 * 60000);
    }
}
