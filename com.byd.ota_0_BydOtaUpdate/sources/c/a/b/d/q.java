package c.a.b.d;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* JADX INFO: compiled from: VersionUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final DecimalFormat f2354a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final DecimalFormat f2355b;

    static {
        Locale locale = Locale.US;
        f2354a = new DecimalFormat("000", new DecimalFormatSymbols(locale));
        f2355b = new DecimalFormat("00000", new DecimalFormatSymbols(locale));
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder(f2354a.format(Integer.parseInt(str.substring(6, 8), 16)));
        sb.insert(1, ".");
        sb.insert(3, ".");
        return sb.toString();
    }

    public static String b(String str) {
        return str.substring(6);
    }

    public static String c(String str) {
        StringBuilder sb = new StringBuilder(f2355b.format(Integer.parseInt(str.substring(6, 10), 16)));
        sb.insert(1, ".");
        sb.insert(4, ".");
        return sb.toString();
    }
}
