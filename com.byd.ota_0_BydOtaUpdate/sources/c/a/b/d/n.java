package c.a.b.d;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.provider.Settings;
import android.text.format.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: TimeUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SimpleDateFormat f2351a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final SimpleDateFormat f2352b = new SimpleDateFormat("yyyy-MM-dd");

    public static String a() {
        return f2351a.format(new Date());
    }

    public static String b(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }

    public static long c() {
        return System.currentTimeMillis();
    }

    public static String d(String str, long j) {
        return new SimpleDateFormat(str).format(new Date(j));
    }

    public static String e(Context context, long j) {
        return TimeManagerImpl.TIME_FORMAT_TWELVE.equals(Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24)) ? DateUtils.getRelativeTimeSpanString(j, c(), 1000L, 81).toString() : DateUtils.getRelativeTimeSpanString(j, c(), 1000L, 145).toString();
    }
}
