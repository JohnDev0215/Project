package p;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class ac1 {
    public static final ac1 b = new ac1(new cc1(zb1.a(new Locale[0])));
    public final bc1 a;

    public ac1(cc1 cc1Var) {
        this.a = cc1Var;
    }

    public static ac1 a(String str) {
        if (str == null || str.isEmpty()) {
            return b;
        }
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            localeArr[i] = yb1.a(strArrSplit[i]);
        }
        return new ac1(new cc1(zb1.a(localeArr)));
    }

    public final Locale b(int i) {
        return ((cc1) this.a).a.get(i);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ac1) {
            if (this.a.equals(((ac1) obj).a)) {
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
