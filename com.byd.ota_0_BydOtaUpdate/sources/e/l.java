package e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: Cookie.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f3605a = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Pattern f3606b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Pattern f3607c = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Pattern f3608d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3609e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f3610f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f3611g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f3612h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f3613i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;

    public l(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f3609e = str;
        this.f3610f = str2;
        this.f3611g = j;
        this.f3612h = str3;
        this.f3613i = str4;
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

    public static boolean b(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !e.d0.c.z(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c0 A[PHI: r0
  0x00c0: PHI (r0v16 long) = (r0v2 long), (r0v6 long) binds: [B:42:0x00be, B:53:0x00e1] A[DONT_GENERATE, DONT_INLINE]] */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static e.l d(long r23, okhttp3.HttpUrl r25, java.lang.String r26) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e.l.d(long, okhttp3.HttpUrl, java.lang.String):e.l");
    }

    @Nullable
    public static l e(HttpUrl httpUrl, String str) {
        return d(System.currentTimeMillis(), httpUrl, str);
    }

    public static List<l> f(HttpUrl httpUrl, s sVar) {
        List<String> listH = sVar.h("Set-Cookie");
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
        String strJ = e.d0.c.j(str);
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
        throw new UnsupportedOperationException("Method not decompiled: e.l.h(java.lang.String, int, int):long");
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
        return this.f3609e;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return lVar.f3609e.equals(this.f3609e) && lVar.f3610f.equals(this.f3610f) && lVar.f3612h.equals(this.f3612h) && lVar.f3613i.equals(this.f3613i) && lVar.f3611g == this.f3611g && lVar.j == this.j && lVar.k == this.k && lVar.l == this.l && lVar.m == this.m;
    }

    public int hashCode() {
        int iHashCode = (((((((527 + this.f3609e.hashCode()) * 31) + this.f3610f.hashCode()) * 31) + this.f3612h.hashCode()) * 31) + this.f3613i.hashCode()) * 31;
        long j = this.f3611g;
        return ((((((((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + (!this.j ? 1 : 0)) * 31) + (!this.k ? 1 : 0)) * 31) + (!this.l ? 1 : 0)) * 31) + (!this.m ? 1 : 0);
    }

    public String j(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3609e);
        sb.append('=');
        sb.append(this.f3610f);
        if (this.l) {
            if (this.f3611g == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(e.d0.g.d.a(new Date(this.f3611g)));
            }
        }
        if (!this.m) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.f3612h);
        }
        sb.append("; path=");
        sb.append(this.f3613i);
        if (this.j) {
            sb.append("; secure");
        }
        if (this.k) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public String k() {
        return this.f3610f;
    }

    public String toString() {
        return j(false);
    }
}
