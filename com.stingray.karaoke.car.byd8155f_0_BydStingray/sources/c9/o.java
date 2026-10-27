package c9;

import h2.AbstractC1347d;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Pattern f12593j = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Pattern f12594k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    public static final Pattern l = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f12595m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12596a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f12597b;
    public final long c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f12598e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f12599f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f12600g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f12601h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f12602i;

    public o(String str, String str2, long j3, String str3, String str4, boolean z6, boolean z10, boolean z11, boolean z12) {
        this.f12596a = str;
        this.f12597b = str2;
        this.c = j3;
        this.d = str3;
        this.f12598e = str4;
        this.f12599f = z6;
        this.f12600g = z10;
        this.f12601h = z11;
        this.f12602i = z12;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o) {
            o oVar = (o) obj;
            if (A7.m.a(oVar.f12596a, this.f12596a) && A7.m.a(oVar.f12597b, this.f12597b) && oVar.c == this.c && A7.m.a(oVar.d, this.d) && A7.m.a(oVar.f12598e, this.f12598e) && oVar.f12599f == this.f12599f && oVar.f12600g == this.f12600g && oVar.f12601h == this.f12601h && oVar.f12602i == this.f12602i) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f12602i) + AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.d(M5.b.c(this.f12598e, M5.b.c(this.d, AbstractC1347d.c(M5.b.c(this.f12597b, M5.b.c(this.f12596a, 527, 31), 31), 31, this.c), 31), 31), 31, this.f12599f), 31, this.f12600g), 31, this.f12601h);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f12596a);
        sb.append('=');
        sb.append(this.f12597b);
        if (this.f12601h) {
            long j3 = this.c;
            if (j3 == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                String str = ((DateFormat) h9.b.f15634a.get()).format(new Date(j3));
                A7.m.e(str, "STANDARD_DATE_FORMAT.get().format(this)");
                sb.append(str);
            }
        }
        if (!this.f12602i) {
            sb.append("; domain=");
            sb.append(this.d);
        }
        sb.append("; path=");
        sb.append(this.f12598e);
        if (this.f12599f) {
            sb.append("; secure");
        }
        if (this.f12600g) {
            sb.append("; httponly");
        }
        String string = sb.toString();
        A7.m.e(string, "toString()");
        return string;
    }
}
