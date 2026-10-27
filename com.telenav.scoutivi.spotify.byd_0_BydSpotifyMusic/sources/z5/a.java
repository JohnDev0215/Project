package z5;

import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;
import y5.q;

/* JADX INFO: compiled from: Duration.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Comparable<a> {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0182a f10049g = new C0182a(null);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final long f10050h = d(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final long f10051i = c.e(4611686018427387903L);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final long f10052j = c.e(-4611686018427387903L);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final long f10053e;

    /* JADX INFO: renamed from: z5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Duration.kt */
    public static final class C0182a {
        private C0182a() {
        }

        public /* synthetic */ C0182a(g gVar) {
            this();
        }
    }

    private static final void a(long j7, StringBuilder sb, int i7, int i8, int i9, String str, boolean z6) {
        sb.append(i7);
        if (i8 != 0) {
            sb.append('.');
            String strA0 = q.a0(String.valueOf(i8), i9, '0');
            int i10 = -1;
            int length = strA0.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i11 = length - 1;
                    if (strA0.charAt(length) != '0') {
                        i10 = length;
                        break;
                    } else if (i11 < 0) {
                        break;
                    } else {
                        length = i11;
                    }
                }
            }
            int i12 = i10 + 1;
            if (z6 || i12 >= 3) {
                sb.append((CharSequence) strA0, 0, ((i12 + 2) / 3) * 3);
                l.d(sb, "this.append(value, startIndex, endIndex)");
            } else {
                sb.append((CharSequence) strA0, 0, i12);
                l.d(sb, "this.append(value, startIndex, endIndex)");
            }
        }
        sb.append(str);
    }

    public static int c(long j7, long j8) {
        long j9 = j7 ^ j8;
        if (j9 < 0 || (((int) j9) & 1) == 0) {
            return l.h(j7, j8);
        }
        int i7 = (((int) j7) & 1) - (((int) j8) & 1);
        return u(j7) ? -i7 : i7;
    }

    public static long d(long j7) {
        if (b.a()) {
            if (s(j7)) {
                long jP = p(j7);
                if (!(-4611686018426999999L <= jP && jP < 4611686018427000000L)) {
                    throw new AssertionError(p(j7) + " ns is out of nanoseconds range");
                }
            } else {
                long jP2 = p(j7);
                if (!(-4611686018427387903L <= jP2 && jP2 < 4611686018427387904L)) {
                    throw new AssertionError(p(j7) + " ms is out of milliseconds range");
                }
                long jP3 = p(j7);
                if (-4611686018426L <= jP3 && jP3 < 4611686018427L) {
                    throw new AssertionError(p(j7) + " ms is denormalized");
                }
            }
        }
        return j7;
    }

    public static boolean e(long j7, Object obj) {
        return (obj instanceof a) && j7 == ((a) obj).y();
    }

    public static final long f(long j7) {
        return u(j7) ? x(j7) : j7;
    }

    public static final int g(long j7) {
        if (t(j7)) {
            return 0;
        }
        return (int) (i(j7) % ((long) 24));
    }

    public static final long h(long j7) {
        return v(j7, d.DAYS);
    }

    public static final long i(long j7) {
        return v(j7, d.HOURS);
    }

    public static final long j(long j7) {
        return v(j7, d.MINUTES);
    }

    public static final long k(long j7) {
        return v(j7, d.SECONDS);
    }

    public static final int l(long j7) {
        if (t(j7)) {
            return 0;
        }
        return (int) (j(j7) % ((long) 60));
    }

    public static final int m(long j7) {
        if (t(j7)) {
            return 0;
        }
        return (int) (r(j7) ? c.f(p(j7) % ((long) 1000)) : p(j7) % ((long) 1000000000));
    }

    public static final int n(long j7) {
        if (t(j7)) {
            return 0;
        }
        return (int) (k(j7) % ((long) 60));
    }

    private static final d o(long j7) {
        return s(j7) ? d.NANOSECONDS : d.MILLISECONDS;
    }

    private static final long p(long j7) {
        return j7 >> 1;
    }

    public static int q(long j7) {
        return (int) (j7 ^ (j7 >>> 32));
    }

    private static final boolean r(long j7) {
        return (((int) j7) & 1) == 1;
    }

    private static final boolean s(long j7) {
        return (((int) j7) & 1) == 0;
    }

    public static final boolean t(long j7) {
        return j7 == f10051i || j7 == f10052j;
    }

    public static final boolean u(long j7) {
        return j7 < 0;
    }

    public static final long v(long j7, d unit) {
        l.e(unit, "unit");
        if (j7 == f10051i) {
            return Long.MAX_VALUE;
        }
        if (j7 == f10052j) {
            return Long.MIN_VALUE;
        }
        return e.a(p(j7), o(j7), unit);
    }

    public static String w(long j7) {
        if (j7 == 0) {
            return "0s";
        }
        if (j7 == f10051i) {
            return "Infinity";
        }
        if (j7 == f10052j) {
            return "-Infinity";
        }
        boolean zU = u(j7);
        StringBuilder sb = new StringBuilder();
        if (zU) {
            sb.append('-');
        }
        long jF = f(j7);
        long jH = h(jF);
        int iG = g(jF);
        int iL = l(jF);
        int iN = n(jF);
        int iM = m(jF);
        int i7 = 0;
        boolean z6 = jH != 0;
        boolean z7 = iG != 0;
        boolean z8 = iL != 0;
        boolean z9 = (iN == 0 && iM == 0) ? false : true;
        if (z6) {
            sb.append(jH);
            sb.append('d');
            i7 = 1;
        }
        if (z7 || (z6 && (z8 || z9))) {
            int i8 = i7 + 1;
            if (i7 > 0) {
                sb.append(' ');
            }
            sb.append(iG);
            sb.append('h');
            i7 = i8;
        }
        if (z8 || (z9 && (z7 || z6))) {
            int i9 = i7 + 1;
            if (i7 > 0) {
                sb.append(' ');
            }
            sb.append(iL);
            sb.append('m');
            i7 = i9;
        }
        if (z9) {
            int i10 = i7 + 1;
            if (i7 > 0) {
                sb.append(' ');
            }
            if (iN != 0 || z6 || z7 || z8) {
                a(j7, sb, iN, iM, 9, "s", false);
            } else if (iM >= 1000000) {
                a(j7, sb, iM / 1000000, iM % 1000000, 6, "ms", false);
            } else if (iM >= 1000) {
                a(j7, sb, iM / 1000, iM % 1000, 3, "us", false);
            } else {
                sb.append(iM);
                sb.append("ns");
            }
            i7 = i10;
        }
        if (zU && i7 > 1) {
            sb.insert(1, '(').append(')');
        }
        String string = sb.toString();
        l.d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final long x(long j7) {
        return c.d(-p(j7), ((int) j7) & 1);
    }

    public int b(long j7) {
        return c(this.f10053e, j7);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(a aVar) {
        return b(aVar.y());
    }

    public boolean equals(Object obj) {
        return e(this.f10053e, obj);
    }

    public int hashCode() {
        return q(this.f10053e);
    }

    public String toString() {
        return w(this.f10053e);
    }

    public final /* synthetic */ long y() {
        return this.f10053e;
    }
}
