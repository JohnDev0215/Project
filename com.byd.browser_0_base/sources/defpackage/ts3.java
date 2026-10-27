package defpackage;

import J.N;
import android.net.Uri;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: compiled from: WebsiteAddress.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ts3 implements Comparable<ts3>, Serializable {
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final boolean i;
    public String j;

    public ts3(String str, String str2, String str3, String str4, boolean z) {
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.h = str4;
        this.i = z;
    }

    public static ts3 e(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.startsWith("[*.]")) {
            return new ts3(str, null, null, str.substring(4), true);
        }
        if (str.indexOf("://") == -1) {
            return new ts3(str, null, null, str, true);
        }
        Uri uri = Uri.parse(str);
        return new ts3(str, str.endsWith("/") ? str.substring(0, str.length() - 1) : str, uri.getScheme(), uri.getHost(), "http".equals(uri.getScheme()) && (uri.getPort() == -1 || uri.getPort() == 80));
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(ts3 ts3Var) {
        int iCompareTo;
        if (this == ts3Var) {
            return 0;
        }
        int iCompareTo2 = f().compareTo(ts3Var.f());
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        String str = this.g;
        boolean z = str == null;
        String str2 = ts3Var.g;
        if (z != (str2 == null)) {
            return str == null ? -1 : 1;
        }
        if (str != null && (iCompareTo = str.compareTo(str2)) != 0) {
            return iCompareTo;
        }
        String[] strArrK = k();
        String[] strArrK2 = ts3Var.k();
        int length = strArrK.length - 1;
        int length2 = strArrK2.length - 1;
        while (length >= 0 && length2 >= 0) {
            int i = length - 1;
            int i2 = length2 - 1;
            int iCompareTo3 = strArrK[length].compareTo(strArrK2[length2]);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
            length = i;
            length2 = i2;
        }
        return length - length2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ts3)) {
            return false;
        }
        ts3 ts3Var = (ts3) obj;
        return Objects.equals(this.f, ts3Var.f) && Objects.equals(this.g, ts3Var.g) && Objects.equals(this.h, ts3Var.h);
    }

    public final String f() {
        if (this.j == null) {
            String str = this.h;
            String str2 = this.f;
            if (str2 == null) {
                str2 = "http://" + str;
            }
            String strB = bk3.b(str2, false);
            this.j = strB;
            if (strB == null || strB.isEmpty()) {
                this.j = str;
            }
        }
        return this.j;
    }

    public final String h() {
        String str = this.h;
        if (str == null || !this.i) {
            return this.f;
        }
        return "http://" + str;
    }

    public final int hashCode() {
        String str = this.f;
        int iHashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.g;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.h;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String[] k() {
        int i;
        String str = this.f;
        if (str != null) {
            int iIndexOf = str.indexOf("://");
            if (iIndexOf == -1) {
                return new String[0];
            }
            i = iIndexOf + 3;
        } else {
            str = this.h;
            i = 0;
        }
        int iIndexOf2 = str.indexOf(f()) - 1;
        return iIndexOf2 > i ? str.substring(i, iIndexOf2).split("\\.") : new String[0];
    }

    public final String l() {
        String str = this.f;
        return str == null ? this.h : N.MR6Af3ZS(str, this.i ? 1 : 0);
    }
}
