package f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Cookie.java */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f5483a = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Pattern f5484b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Pattern f5485c = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Pattern f5486d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f5487e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f5488f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final long f5489g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f5490h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f5491i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final boolean m;

    private p(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f5487e = str;
        this.f5488f = str2;
        this.f5489g = j;
        this.f5490h = str3;
        this.f5491i = str4;
        this.j = z;
        this.k = z2;
        this.m = z3;
        this.l = z4;
    }

    private static int a(String str, int i2, int i3, boolean z) {
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || (cCharAt >= '0' && cCharAt <= '9') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == ':'))) == (!z)) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    private static boolean b(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !f.k0.e.L(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c0 A[PHI: r0
  0x00c0: PHI (r0v16 long) = (r0v2 long), (r0v6 long) binds: [B:42:0x00be, B:53:0x00e1] A[DONT_GENERATE, DONT_INLINE]] */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static f.p d(long r23, f.x r25, java.lang.String r26) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f.p.d(long, f.x, java.lang.String):f.p");
    }

    @Nullable
    public static p e(x xVar, String str) {
        return d(System.currentTimeMillis(), xVar, str);
    }

    public static List<p> f(x xVar, w wVar) {
        List<String> listJ = wVar.j("Set-Cookie");
        int size = listJ.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            p pVarE = e(xVar, listJ.get(i2));
            if (pVarE != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(pVarE);
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    private static String g(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String strC = f.k0.e.c(str);
        if (strC != null) {
            return strC;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static long h(java.lang.String r12, int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f.p.h(java.lang.String, int, int):long");
    }

    private static long i(String str) {
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
        return this.f5487e;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return pVar.f5487e.equals(this.f5487e) && pVar.f5488f.equals(this.f5488f) && pVar.f5490h.equals(this.f5490h) && pVar.f5491i.equals(this.f5491i) && pVar.f5489g == this.f5489g && pVar.j == this.j && pVar.k == this.k && pVar.l == this.l && pVar.m == this.m;
    }

    public int hashCode() {
        int iHashCode = (((((((527 + this.f5487e.hashCode()) * 31) + this.f5488f.hashCode()) * 31) + this.f5490h.hashCode()) * 31) + this.f5491i.hashCode()) * 31;
        long j = this.f5489g;
        return ((((((((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + (!this.j ? 1 : 0)) * 31) + (!this.k ? 1 : 0)) * 31) + (!this.l ? 1 : 0)) * 31) + (!this.m ? 1 : 0);
    }

    String j(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5487e);
        sb.append('=');
        sb.append(this.f5488f);
        if (this.l) {
            if (this.f5489g == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(f.k0.i.d.a(new Date(this.f5489g)));
            }
        }
        if (!this.m) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.f5490h);
        }
        sb.append("; path=");
        sb.append(this.f5491i);
        if (this.j) {
            sb.append("; secure");
        }
        if (this.k) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public String k() {
        return this.f5488f;
    }

    public String toString() {
        return j(false);
    }
}
