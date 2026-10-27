package f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: Cookie.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f5351a = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Pattern f5352b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Pattern f5353c = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Pattern f5354d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f5355e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f5356f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f5357g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f5358h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f5359i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;

    public l(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f5355e = str;
        this.f5356f = str2;
        this.f5357g = j;
        this.f5358h = str3;
        this.f5359i = str4;
        this.j = z;
        this.k = z2;
        this.m = z3;
        this.l = z4;
    }

    public static int a(String str, int i2, int i3, boolean z) {
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || (cCharAt >= '0' && cCharAt <= '9') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == ':'))) == (!z)) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static boolean b(HttpUrl httpUrl, String str) {
        String strL = httpUrl.l();
        if (strL.equals(str)) {
            return true;
        }
        return strL.endsWith(str) && strL.charAt((strL.length() - str.length()) - 1) == '.' && !f.b0.c.z(strL);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c0 A[PHI: r0
  0x00c0: PHI (r0v13 long) = (r0v2 long), (r0v6 long) binds: [B:42:0x00be, B:53:0x00e1] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static f.l d(long r23, okhttp3.HttpUrl r25, java.lang.String r26) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f.l.d(long, okhttp3.HttpUrl, java.lang.String):f.l");
    }

    public static l e(HttpUrl httpUrl, String str) {
        return d(System.currentTimeMillis(), httpUrl, str);
    }

    public static List<l> f(HttpUrl httpUrl, q qVar) {
        List<String> listH = qVar.h("Set-Cookie");
        int size = listH.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            l lVarE = e(httpUrl, listH.get(i2));
            if (lVarE != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lVarE);
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public static String g(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String strJ = f.b0.c.j(str);
        if (strJ != null) {
            return strJ;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long h(java.lang.String r12, int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f.l.h(java.lang.String, int, int):long");
    }

    public static long i(String str) {
        try {
            long j = Long.parseLong(str);
            if (j <= 0) {
                return Long.MIN_VALUE;
            }
            return j;
        } catch (NumberFormatException e2) {
            if (str.matches("-?\\d+")) {
                return str.startsWith("-") ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            throw e2;
        }
    }

    public String c() {
        return this.f5355e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return lVar.f5355e.equals(this.f5355e) && lVar.f5356f.equals(this.f5356f) && lVar.f5358h.equals(this.f5358h) && lVar.f5359i.equals(this.f5359i) && lVar.f5357g == this.f5357g && lVar.j == this.j && lVar.k == this.k && lVar.l == this.l && lVar.m == this.m;
    }

    public int hashCode() {
        int iHashCode = (((((((527 + this.f5355e.hashCode()) * 31) + this.f5356f.hashCode()) * 31) + this.f5358h.hashCode()) * 31) + this.f5359i.hashCode()) * 31;
        long j = this.f5357g;
        return ((((((((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + (!this.j ? 1 : 0)) * 31) + (!this.k ? 1 : 0)) * 31) + (!this.l ? 1 : 0)) * 31) + (!this.m ? 1 : 0);
    }

    public String j(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5355e);
        sb.append('=');
        sb.append(this.f5356f);
        if (this.l) {
            if (this.f5357g == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(f.b0.g.d.a(new Date(this.f5357g)));
            }
        }
        if (!this.m) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.f5358h);
        }
        sb.append("; path=");
        sb.append(this.f5359i);
        if (this.j) {
            sb.append("; secure");
        }
        if (this.k) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public String k() {
        return this.f5356f;
    }

    public String toString() {
        return j(false);
    }
}
