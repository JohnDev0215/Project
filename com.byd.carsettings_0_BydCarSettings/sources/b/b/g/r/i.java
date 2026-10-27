package b.b.g.r;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.byd.carsettings.R;
import com.byd.carsettings.common.SizeUnitType;
import java.math.BigDecimal;
import java.util.Locale;

/* JADX INFO: compiled from: CalculateUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static StringBuffer f1808a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f1809b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f1810c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f1811d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f1812e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static long f1813f = 0;
    public static boolean g = false;

    /* JADX INFO: compiled from: CalculateUtils.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1814a;

        static {
            int[] iArr = new int[SizeUnitType.values().length];
            f1814a = iArr;
            try {
                iArr[SizeUnitType.GB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1814a[SizeUnitType.MB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1814a[SizeUnitType.KB.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1814a[SizeUnitType.B.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static String a(float f2) {
        return b(f2, 1);
    }

    public static String b(float f2, int i) {
        if (!"ar".equals(Locale.getDefault().getLanguage())) {
            return String.format("%." + i + "f", Float.valueOf(f2));
        }
        return String.format(Locale.CHINA, "%." + i + "f", Float.valueOf(f2));
    }

    public static String c(long j) {
        SizeUnitType sizeUnitTypeE = e(j);
        String strD = d(sizeUnitTypeE);
        String strA = b.b.m.g.e.a(2, (j * 1.0f) / sizeUnitTypeE.size);
        String strA2 = b.b.m.g.e.a(2, 0.0d);
        if (f1808a == null) {
            f1808a = new StringBuffer();
        }
        f1808a.setLength(0);
        StringBuffer stringBuffer = f1808a;
        if (strA.equals(strA2)) {
            strA = "0";
        }
        stringBuffer.append(strA);
        StringBuffer stringBuffer2 = f1808a;
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(TextUtils.isEmpty(strD) ? sizeUnitTypeE.name() : strD);
        stringBuffer2.append(sb.toString());
        String string = f1808a.toString();
        b.b.m.c.a.a("CalculateUtils", "---getSizeText---unit: " + sizeUnitTypeE + ", value: " + string + ", unitText: " + strD);
        return string;
    }

    public static String d(SizeUnitType sizeUnitType) {
        int i = a.f1814a[sizeUnitType.ordinal()];
        if (i == 1) {
            return f1809b;
        }
        if (i == 2) {
            return f1810c;
        }
        if (i == 3) {
            return f1811d;
        }
        if (i != 4) {
            return null;
        }
        return f1812e;
    }

    public static SizeUnitType e(long j) {
        float f2 = j * 1.0f;
        SizeUnitType sizeUnitType = SizeUnitType.GB;
        if (f2 / sizeUnitType.size >= 1.0f) {
            return sizeUnitType;
        }
        SizeUnitType sizeUnitType2 = SizeUnitType.MB;
        if (f2 / sizeUnitType2.size >= 1.0f) {
            return sizeUnitType2;
        }
        SizeUnitType sizeUnitType3 = SizeUnitType.KB;
        return f2 / ((float) sizeUnitType3.size) >= 1.0f ? sizeUnitType3 : SizeUnitType.B;
    }

    public static void f(Context context) {
        if (context == null) {
            return;
        }
        f1809b = context.getString(R.string.unit_gb);
        f1810c = context.getString(R.string.unit_mb);
        f1811d = context.getString(R.string.unit_kb);
        f1812e = context.getString(R.string.unit_b);
    }

    public static void g() {
        g = false;
        f1813f = System.currentTimeMillis();
        Log.d("CalculateUtils", "app_start onAppCreate: " + f1813f);
    }

    public static void h() {
        if (g) {
            return;
        }
        g = true;
        Log.d("CalculateUtils", "app_start onMainActivityResumeCompleted appStartTime: " + new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(f1813f)).longValue());
    }
}
