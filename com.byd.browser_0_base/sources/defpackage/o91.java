package defpackage;

import android.os.LocaleList;
import java.util.Locale;

/* JADX INFO: compiled from: LocaleListCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class o91 {
    public static final o91 b = new o91(new q91(new LocaleList(new Locale[0])));
    public final p91 a;

    /* JADX INFO: compiled from: LocaleListCompat.java */
    public static class a {
        public static final /* synthetic */ int a = 0;

        static {
            new Locale("en", "XA");
            new Locale("ar", "XB");
        }
    }

    public o91(q91 q91Var) {
        this.a = q91Var;
    }

    public static o91 a(String str) {
        if (str == null || str.isEmpty()) {
            return b;
        }
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            String str2 = strArrSplit[i];
            int i2 = a.a;
            localeArr[i] = Locale.forLanguageTag(str2);
        }
        return new o91(new q91(new LocaleList(localeArr)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o91) {
            if (this.a.equals(((o91) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }
}
