package p;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class ri2 {
    public final String a;
    public final String b;
    public final boolean c;
    public final int d;
    public final String e;
    public final int f;
    public final int g;

    public ri2(String str, String str2, boolean z, int i, String str3, int i2) {
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = i;
        this.e = str3;
        this.f = i2;
        Locale locale = Locale.US;
        v03.i(locale, "US");
        String upperCase = str2.toUpperCase(locale);
        v03.i(upperCase, "this as java.lang.String).toUpperCase(locale)");
        this.g = kotlin.text.b.i0(upperCase, "INT") ? 3 : (kotlin.text.b.i0(upperCase, "CHAR") || kotlin.text.b.i0(upperCase, "CLOB") || kotlin.text.b.i0(upperCase, "TEXT")) ? 2 : kotlin.text.b.i0(upperCase, "BLOB") ? 5 : (kotlin.text.b.i0(upperCase, "REAL") || kotlin.text.b.i0(upperCase, "FLOA") || kotlin.text.b.i0(upperCase, "DOUB")) ? 4 : 1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ri2)) {
            return false;
        }
        ri2 ri2Var = (ri2) obj;
        if (this.d != ri2Var.d) {
            return false;
        }
        if (!v03.b(this.a, ri2Var.a) || this.c != ri2Var.c) {
            return false;
        }
        int i = ri2Var.f;
        String str = ri2Var.e;
        String str2 = this.e;
        int i2 = this.f;
        if (i2 == 1 && i == 2 && str2 != null && !u11.w(str2, str)) {
            return false;
        }
        if (i2 != 2 || i != 1 || str == null || u11.w(str, str2)) {
            return (i2 == 0 || i2 != i || (str2 == null ? str == null : u11.w(str2, str))) && this.g == ri2Var.g;
        }
        return false;
    }

    public final int hashCode() {
        return (((((this.a.hashCode() * 31) + this.g) * 31) + (this.c ? 1231 : 1237)) * 31) + this.d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Column{name='");
        sb.append(this.a);
        sb.append("', type='");
        sb.append(this.b);
        sb.append("', affinity='");
        sb.append(this.g);
        sb.append("', notNull=");
        sb.append(this.c);
        sb.append(", primaryKeyPosition=");
        sb.append(this.d);
        sb.append(", defaultValue='");
        String str = this.e;
        if (str == null) {
            str = "undefined";
        }
        return u4.m(sb, str, "'}");
    }
}
