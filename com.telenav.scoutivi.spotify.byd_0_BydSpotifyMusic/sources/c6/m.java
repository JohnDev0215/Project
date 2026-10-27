package c6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* JADX INFO: compiled from: Cookie.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3814b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f3815c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3816d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3817e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f3818f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final boolean f3819g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final boolean f3820h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final boolean f3821i;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a f3812n = new a(null);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final Pattern f3808j = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final Pattern f3809k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final Pattern f3810l = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final Pattern f3811m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: compiled from: Cookie.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        private final int a(String str, int i7, int i8, boolean z6) {
            while (i7 < i8) {
                char cCharAt = str.charAt(i7);
                if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || ('0' <= cCharAt && '9' >= cCharAt) || (('a' <= cCharAt && 'z' >= cCharAt) || (('A' <= cCharAt && 'Z' >= cCharAt) || cCharAt == ':'))) == (!z6)) {
                    return i7;
                }
                i7++;
            }
            return i8;
        }

        private final boolean b(String str, String str2) {
            if (kotlin.jvm.internal.l.a(str, str2)) {
                return true;
            }
            return y5.p.n(str, str2, false, 2, null) && str.charAt((str.length() - str2.length()) - 1) == '.' && !d6.b.f(str);
        }

        private final String f(String str) {
            if (!(!y5.p.n(str, ".", false, 2, null))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String strE = d6.a.e(y5.q.g0(str, "."));
            if (strE != null) {
                return strE;
            }
            throw new IllegalArgumentException();
        }

        private final long g(String str, int i7, int i8) {
            int iA = a(str, i7, i8, false);
            Matcher matcher = m.f3811m.matcher(str);
            int i9 = -1;
            int i10 = -1;
            int i11 = -1;
            int iQ = -1;
            int i12 = -1;
            int i13 = -1;
            while (iA < i8) {
                int iA2 = a(str, iA + 1, i8, true);
                matcher.region(iA, iA2);
                if (i10 == -1 && matcher.usePattern(m.f3811m).matches()) {
                    String strGroup = matcher.group(1);
                    kotlin.jvm.internal.l.d(strGroup, "matcher.group(1)");
                    i10 = Integer.parseInt(strGroup);
                    String strGroup2 = matcher.group(2);
                    kotlin.jvm.internal.l.d(strGroup2, "matcher.group(2)");
                    i12 = Integer.parseInt(strGroup2);
                    String strGroup3 = matcher.group(3);
                    kotlin.jvm.internal.l.d(strGroup3, "matcher.group(3)");
                    i13 = Integer.parseInt(strGroup3);
                } else if (i11 == -1 && matcher.usePattern(m.f3810l).matches()) {
                    String strGroup4 = matcher.group(1);
                    kotlin.jvm.internal.l.d(strGroup4, "matcher.group(1)");
                    i11 = Integer.parseInt(strGroup4);
                } else if (iQ == -1 && matcher.usePattern(m.f3809k).matches()) {
                    String strGroup5 = matcher.group(1);
                    kotlin.jvm.internal.l.d(strGroup5, "matcher.group(1)");
                    Locale locale = Locale.US;
                    kotlin.jvm.internal.l.d(locale, "Locale.US");
                    Objects.requireNonNull(strGroup5, "null cannot be cast to non-null type java.lang.String");
                    String lowerCase = strGroup5.toLowerCase(locale);
                    kotlin.jvm.internal.l.d(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                    String strPattern = m.f3809k.pattern();
                    kotlin.jvm.internal.l.d(strPattern, "MONTH_PATTERN.pattern()");
                    iQ = y5.q.Q(strPattern, lowerCase, 0, false, 6, null) / 4;
                } else if (i9 == -1 && matcher.usePattern(m.f3808j).matches()) {
                    String strGroup6 = matcher.group(1);
                    kotlin.jvm.internal.l.d(strGroup6, "matcher.group(1)");
                    i9 = Integer.parseInt(strGroup6);
                }
                iA = a(str, iA2 + 1, i8, false);
            }
            if (70 <= i9 && 99 >= i9) {
                i9 += 1900;
            }
            if (i9 >= 0 && 69 >= i9) {
                i9 += 2000;
            }
            if (!(i9 >= 1601)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(iQ != -1)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(1 <= i11 && 31 >= i11)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(i10 >= 0 && 23 >= i10)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(i12 >= 0 && 59 >= i12)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(i13 >= 0 && 59 >= i13)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar(d6.b.f5965f);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i9);
            gregorianCalendar.set(2, iQ - 1);
            gregorianCalendar.set(5, i11);
            gregorianCalendar.set(11, i10);
            gregorianCalendar.set(12, i12);
            gregorianCalendar.set(13, i13);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }

        private final long h(String str) {
            try {
                long j7 = Long.parseLong(str);
                if (j7 <= 0) {
                    return Long.MIN_VALUE;
                }
                return j7;
            } catch (NumberFormatException e7) {
                if (new y5.f("-?\\d+").a(str)) {
                    return y5.p.A(str, "-", false, 2, null) ? Long.MIN_VALUE : Long.MAX_VALUE;
                }
                throw e7;
            }
        }

        public final m c(u url, String setCookie) {
            kotlin.jvm.internal.l.e(url, "url");
            kotlin.jvm.internal.l.e(setCookie, "setCookie");
            return d(System.currentTimeMillis(), url, setCookie);
        }

        /* JADX WARN: Removed duplicated region for block: B:46:0x00dc A[PHI: r1
  0x00dc: PHI (r1v25 long) = (r1v8 long), (r1v12 long) binds: [B:45:0x00da, B:56:0x0102] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final c6.m d(long r26, c6.u r28, java.lang.String r29) {
            /*
                Method dump skipped, instruction units count: 373
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: c6.m.a.d(long, c6.u, java.lang.String):c6.m");
        }

        public final List<m> e(u url, t headers) {
            kotlin.jvm.internal.l.e(url, "url");
            kotlin.jvm.internal.l.e(headers, "headers");
            List<String> listG = headers.g("Set-Cookie");
            int size = listG.size();
            ArrayList arrayList = null;
            for (int i7 = 0; i7 < size; i7++) {
                m mVarC = c(url, listG.get(i7));
                if (mVarC != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(mVarC);
                }
            }
            if (arrayList == null) {
                return h5.p.h();
            }
            List<m> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            kotlin.jvm.internal.l.d(listUnmodifiableList, "Collections.unmodifiableList(cookies)");
            return listUnmodifiableList;
        }
    }

    private m(String str, String str2, long j7, String str3, String str4, boolean z6, boolean z7, boolean z8, boolean z9) {
        this.f3813a = str;
        this.f3814b = str2;
        this.f3815c = j7;
        this.f3816d = str3;
        this.f3817e = str4;
        this.f3818f = z6;
        this.f3819g = z7;
        this.f3820h = z8;
        this.f3821i = z9;
    }

    public final String e() {
        return this.f3813a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof m) {
            m mVar = (m) obj;
            if (kotlin.jvm.internal.l.a(mVar.f3813a, this.f3813a) && kotlin.jvm.internal.l.a(mVar.f3814b, this.f3814b) && mVar.f3815c == this.f3815c && kotlin.jvm.internal.l.a(mVar.f3816d, this.f3816d) && kotlin.jvm.internal.l.a(mVar.f3817e, this.f3817e) && mVar.f3818f == this.f3818f && mVar.f3819g == this.f3819g && mVar.f3820h == this.f3820h && mVar.f3821i == this.f3821i) {
                return true;
            }
        }
        return false;
    }

    public final String f(boolean z6) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3813a);
        sb.append('=');
        sb.append(this.f3814b);
        if (this.f3820h) {
            if (this.f3815c == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(i6.c.b(new Date(this.f3815c)));
            }
        }
        if (!this.f3821i) {
            sb.append("; domain=");
            if (z6) {
                sb.append(".");
            }
            sb.append(this.f3816d);
        }
        sb.append("; path=");
        sb.append(this.f3817e);
        if (this.f3818f) {
            sb.append("; secure");
        }
        if (this.f3819g) {
            sb.append("; httponly");
        }
        String string = sb.toString();
        kotlin.jvm.internal.l.d(string, "toString()");
        return string;
    }

    public final String g() {
        return this.f3814b;
    }

    @IgnoreJRERequirement
    public int hashCode() {
        return ((((((((((((((((527 + this.f3813a.hashCode()) * 31) + this.f3814b.hashCode()) * 31) + Long.hashCode(this.f3815c)) * 31) + this.f3816d.hashCode()) * 31) + this.f3817e.hashCode()) * 31) + Boolean.hashCode(this.f3818f)) * 31) + Boolean.hashCode(this.f3819g)) * 31) + Boolean.hashCode(this.f3820h)) * 31) + Boolean.hashCode(this.f3821i);
    }

    public String toString() {
        return f(false);
    }

    public /* synthetic */ m(String str, String str2, long j7, String str3, String str4, boolean z6, boolean z7, boolean z8, boolean z9, kotlin.jvm.internal.g gVar) {
        this(str, str2, j7, str3, str4, z6, z7, z8, z9);
    }
}
