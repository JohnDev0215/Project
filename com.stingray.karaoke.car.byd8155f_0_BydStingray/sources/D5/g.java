package D5;

import B5.n;
import C1.j;
import android.content.Context;
import android.content.SharedPreferences;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f1193a = new j("Analytics", 3);

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str.concat(" == null"));
        }
    }

    public static void b(String str, String str2) {
        if (g(str)) {
            throw new NullPointerException(str2.concat(" cannot be null or empty"));
        }
    }

    public static void c(n nVar) {
        if (nVar == null) {
            return;
        }
        try {
            nVar.close();
        } catch (IOException unused) {
        }
    }

    public static SharedPreferences d(Context context, String str) {
        return context.getSharedPreferences("analytics-android-" + str, 0);
    }

    public static int e(CharSequence charSequence) {
        int length = charSequence.length();
        int i7 = 0;
        while (i7 < length && charSequence.charAt(i7) <= ' ') {
            i7++;
        }
        while (length > i7 && charSequence.charAt(length - 1) <= ' ') {
            length--;
        }
        return length - i7;
    }

    public static boolean f(int i7, Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Exception e10) {
            f1193a.l(e10, "Exception during permission check", new Object[0]);
            if (i7 < 2) {
                return f(i7 + 1, context.getApplicationContext(), str);
            }
            return false;
        }
    }

    public static boolean g(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0 || e(charSequence) == 0;
    }

    public static boolean h(Map map) {
        return map == null || map.size() == 0;
    }

    public static String i(Date date) {
        TimeZone timeZone = a.f1190a;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(a.f1190a, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(24);
        a.b(gregorianCalendar.get(1), 4, sb);
        sb.append('-');
        a.b(gregorianCalendar.get(2) + 1, 2, sb);
        sb.append('-');
        a.b(gregorianCalendar.get(5), 2, sb);
        sb.append('T');
        a.b(gregorianCalendar.get(11), 2, sb);
        sb.append(':');
        a.b(gregorianCalendar.get(12), 2, sb);
        sb.append(':');
        a.b(gregorianCalendar.get(13), 2, sb);
        sb.append('.');
        a.b(gregorianCalendar.get(14), 3, sb);
        sb.append('Z');
        return sb.toString();
    }
}
