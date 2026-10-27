package b.b.g.r;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.icu.util.Calendar;
import android.provider.Settings;
import android.text.TextUtils;
import com.byd.carsettings.model.dilink.BydDate;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: BydDateUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SimpleDateFormat f1804a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f1805b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1806c = false;

    public static BydDate a(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (!TextUtils.isEmpty(str) && str.contains(" ")) {
            int i6 = 0;
            b.b.m.c.a.c("BydDateUtils", "--getBydDateTime---date = " + str);
            String[] strArrSplit = str.split(" ");
            if (strArrSplit != null && strArrSplit.length >= 2) {
                String str2 = strArrSplit[0];
                String str3 = strArrSplit[1];
                b.b.m.c.a.c("BydDateUtils", "--getBydDateTime---dateStr = " + str2);
                b.b.m.c.a.c("BydDateUtils", "--getBydDateTime---timeStr = " + str3);
                if (!str2.contains("-")) {
                    return null;
                }
                String[] strArrSplit2 = str2.split("-");
                String[] strArrSplit3 = str3.split(":");
                try {
                    i2 = Integer.parseInt(strArrSplit2[0]);
                } catch (Exception e2) {
                    e = e2;
                    i = 0;
                    i2 = 0;
                    i3 = 0;
                }
                try {
                    i3 = Integer.parseInt(strArrSplit2[1]);
                    try {
                        i = Integer.parseInt(strArrSplit2[2]);
                    } catch (Exception e3) {
                        e = e3;
                        i = 0;
                        i4 = 0;
                    }
                    try {
                        i4 = Integer.parseInt(strArrSplit3[0]);
                        try {
                            i5 = Integer.parseInt(strArrSplit3[1]);
                        } catch (Exception e4) {
                            e = e4;
                            i5 = 0;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        i4 = 0;
                        i5 = i4;
                        b.b.m.c.a.c("BydDateUtils", "--getBydDateTime---e = " + e.toString());
                        return new BydDate(i2, i3, i, i4, i5, i6);
                    }
                    try {
                        i6 = Integer.parseInt(strArrSplit3[2]);
                    } catch (Exception e6) {
                        e = e6;
                        b.b.m.c.a.c("BydDateUtils", "--getBydDateTime---e = " + e.toString());
                    }
                } catch (Exception e7) {
                    e = e7;
                    i = 0;
                    i3 = 0;
                    i4 = i3;
                    i5 = i4;
                    b.b.m.c.a.c("BydDateUtils", "--getBydDateTime---e = " + e.toString());
                    return new BydDate(i2, i3, i, i4, i5, i6);
                }
                return new BydDate(i2, i3, i, i4, i5, i6);
            }
        }
        return null;
    }

    public static BydDate b() {
        Calendar calendar = Calendar.getInstance();
        return new BydDate(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
    }

    public static BydDate c(Context context) {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        BydDate bydDate = new BydDate(i, i2, i3, i4, calendar.get(12), calendar.get(13));
        bydDate.setH24(g(context));
        bydDate.setAm(!bydDate.isH24() && h());
        if (h() && i4 >= 12) {
            bydDate.setDay(i4 - 12);
        }
        return bydDate;
    }

    public static String d(long j) {
        if (f1804a == null) {
            f1804a = new SimpleDateFormat("yyyy-MM-dd");
        }
        return f1804a.format(new Date(j));
    }

    public static String e(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(str).format(new Date(j));
    }

    public static int f(int i, int i2) {
        switch (i2) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return i(i) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 0;
        }
    }

    public static boolean g(Context context) {
        return !TimeManagerImpl.TIME_FORMAT_TWELVE.equals(Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24));
    }

    public static boolean h() {
        return Calendar.getInstance().get(9) == 0;
    }

    public static boolean i(int i) {
        return (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
    }

    public static void j(Context context) {
        f1805b = System.currentTimeMillis();
        if (context != null) {
            f1806c = context.getSharedPreferences("carsettings_spf", 0).getInt("version_function_items", -1) < 0;
        }
    }
}
