package d6;

import c6.a0;
import c6.b0;
import c6.c0;
import c6.e;
import c6.r;
import c6.t;
import c6.u;
import c6.x;
import h5.d0;
import h5.h0;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import k6.c;
import kotlin.jvm.internal.l;
import p6.g;
import p6.h;
import p6.r;
import y5.f;
import y5.p;
import y5.q;

/* JADX INFO: compiled from: Util.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f5960a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final t f5961b = t.f3843g.g(new String[0]);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c0 f5962c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a0 f5963d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final r f5964e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final TimeZone f5965f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final f f5966g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f5967h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f5968i;

    /* JADX INFO: compiled from: Util.kt */
    static final class a implements r.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c6.r f5969a;

        a(c6.r rVar) {
            this.f5969a = rVar;
        }

        @Override // c6.r.c
        public final c6.r a(e it) {
            l.e(it, "it");
            return this.f5969a;
        }
    }

    /* JADX INFO: renamed from: d6.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Util.kt */
    static final class ThreadFactoryC0062b implements ThreadFactory {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f5970e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ boolean f5971g;

        ThreadFactoryC0062b(String str, boolean z6) {
            this.f5970e = str;
            this.f5971g = z6;
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.f5970e);
            thread.setDaemon(this.f5971g);
            return thread;
        }
    }

    static {
        byte[] bArr = new byte[0];
        f5960a = bArr;
        f5962c = c0.a.c(c0.f3675e, bArr, null, 1, null);
        f5963d = a0.a.b(a0.f3640a, bArr, null, 0, 0, 7, null);
        r.a aVar = p6.r.f8889i;
        h.a aVar2 = h.f8870j;
        f5964e = aVar.d(aVar2.a("efbbbf"), aVar2.a("feff"), aVar2.a("fffe"), aVar2.a("0000ffff"), aVar2.a("ffff0000"));
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        l.c(timeZone);
        f5965f = timeZone;
        f5966g = new f("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
        f5967h = false;
        String name = x.class.getName();
        l.d(name, "OkHttpClient::class.java.name");
        f5968i = q.h0(q.g0(name, "okhttp3."), "Client");
    }

    public static final String[] A(String[] intersect, String[] other, Comparator<? super String> comparator) {
        l.e(intersect, "$this$intersect");
        l.e(other, "other");
        l.e(comparator, "comparator");
        ArrayList arrayList = new ArrayList();
        for (String str : intersect) {
            int length = other.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    break;
                }
                if (comparator.compare(str, other[i7]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i7++;
            }
        }
        Object[] array = arrayList.toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        return (String[]) array;
    }

    public static final boolean B(Socket isHealthy, g source) {
        l.e(isHealthy, "$this$isHealthy");
        l.e(source, "source");
        try {
            int soTimeout = isHealthy.getSoTimeout();
            try {
                isHealthy.setSoTimeout(1);
                boolean z6 = !source.J();
                isHealthy.setSoTimeout(soTimeout);
                return z6;
            } catch (Throwable th) {
                isHealthy.setSoTimeout(soTimeout);
                throw th;
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public static final boolean C(String name) {
        l.e(name, "name");
        return p.o(name, "Authorization", true) || p.o(name, "Cookie", true) || p.o(name, "Proxy-Authorization", true) || p.o(name, "Set-Cookie", true);
    }

    public static final int D(char c7) {
        if ('0' <= c7 && '9' >= c7) {
            return c7 - '0';
        }
        char c8 = 'a';
        if ('a' > c7 || 'f' < c7) {
            c8 = 'A';
            if ('A' > c7 || 'F' < c7) {
                return -1;
            }
        }
        return (c7 - c8) + 10;
    }

    public static final int E(g readMedium) {
        l.e(readMedium, "$this$readMedium");
        return b(readMedium.Y(), 255) | (b(readMedium.Y(), 255) << 16) | (b(readMedium.Y(), 255) << 8);
    }

    public static final int F(p6.e skipAll, byte b7) throws EOFException {
        l.e(skipAll, "$this$skipAll");
        int i7 = 0;
        while (!skipAll.J() && skipAll.N(0L) == b7) {
            i7++;
            skipAll.Y();
        }
        return i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005b A[PHI: r12
  0x005b: PHI (r12v6 boolean) = (r12v5 boolean), (r12v10 boolean) binds: [B:23:0x007d, B:13:0x0051] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean G(p6.a0 r11, int r12, java.util.concurrent.TimeUnit r13) {
        /*
            java.lang.String r0 = "$this$skipAll"
            kotlin.jvm.internal.l.e(r11, r0)
            java.lang.String r0 = "timeUnit"
            kotlin.jvm.internal.l.e(r13, r0)
            long r0 = java.lang.System.nanoTime()
            p6.b0 r2 = r11.c()
            boolean r2 = r2.e()
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r2 == 0) goto L27
            p6.b0 r2 = r11.c()
            long r5 = r2.c()
            long r5 = r5 - r0
            goto L28
        L27:
            r5 = r3
        L28:
            p6.b0 r2 = r11.c()
            long r7 = (long) r12
            long r12 = r13.toNanos(r7)
            long r12 = java.lang.Math.min(r5, r12)
            long r12 = r12 + r0
            r2.d(r12)
            p6.e r12 = new p6.e     // Catch: java.lang.Throwable -> L64 java.io.InterruptedIOException -> L7a
            r12.<init>()     // Catch: java.lang.Throwable -> L64 java.io.InterruptedIOException -> L7a
        L3e:
            r7 = 8192(0x2000, double:4.0474E-320)
            long r7 = r11.i(r12, r7)     // Catch: java.lang.Throwable -> L64 java.io.InterruptedIOException -> L7a
            r9 = -1
            int r13 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r13 == 0) goto L4e
            r12.z()     // Catch: java.lang.Throwable -> L64 java.io.InterruptedIOException -> L7a
            goto L3e
        L4e:
            r12 = 1
            int r13 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r13 != 0) goto L5b
        L53:
            p6.b0 r11 = r11.c()
            r11.a()
            goto L80
        L5b:
            p6.b0 r11 = r11.c()
            long r0 = r0 + r5
            r11.d(r0)
            goto L80
        L64:
            r12 = move-exception
            int r13 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r13 != 0) goto L71
            p6.b0 r11 = r11.c()
            r11.a()
            goto L79
        L71:
            p6.b0 r11 = r11.c()
            long r0 = r0 + r5
            r11.d(r0)
        L79:
            throw r12
        L7a:
            r12 = 0
            int r13 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r13 != 0) goto L5b
            goto L53
        L80:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: d6.b.G(p6.a0, int, java.util.concurrent.TimeUnit):boolean");
    }

    public static final ThreadFactory H(String name, boolean z6) {
        l.e(name, "name");
        return new ThreadFactoryC0062b(name, z6);
    }

    public static final List<c> I(t toHeaderList) {
        l.e(toHeaderList, "$this$toHeaderList");
        v5.c cVarI = v5.h.i(0, toHeaderList.size());
        ArrayList arrayList = new ArrayList(h5.q.p(cVarI, 10));
        Iterator<Integer> it = cVarI.iterator();
        while (it.hasNext()) {
            int iA = ((d0) it).a();
            arrayList.add(new c(toHeaderList.d(iA), toHeaderList.f(iA)));
        }
        return arrayList;
    }

    public static final t J(List<c> toHeaders) {
        l.e(toHeaders, "$this$toHeaders");
        t.a aVar = new t.a();
        for (c cVar : toHeaders) {
            aVar.c(cVar.a().u(), cVar.b().u());
        }
        return aVar.d();
    }

    public static final String K(u toHostHeader, boolean z6) {
        String strH;
        l.e(toHostHeader, "$this$toHostHeader");
        if (q.F(toHostHeader.h(), ":", false, 2, null)) {
            strH = '[' + toHostHeader.h() + ']';
        } else {
            strH = toHostHeader.h();
        }
        if (!z6 && toHostHeader.l() == u.f3847l.c(toHostHeader.p())) {
            return strH;
        }
        return strH + ':' + toHostHeader.l();
    }

    public static /* synthetic */ String L(u uVar, boolean z6, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            z6 = false;
        }
        return K(uVar, z6);
    }

    public static final <T> List<T> M(List<? extends T> toImmutableList) {
        l.e(toImmutableList, "$this$toImmutableList");
        List<T> listUnmodifiableList = Collections.unmodifiableList(h5.x.L(toImmutableList));
        l.d(listUnmodifiableList, "Collections.unmodifiableList(toMutableList())");
        return listUnmodifiableList;
    }

    public static final <K, V> Map<K, V> N(Map<K, ? extends V> toImmutableMap) {
        l.e(toImmutableMap, "$this$toImmutableMap");
        if (toImmutableMap.isEmpty()) {
            return h0.f();
        }
        Map<K, V> mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(toImmutableMap));
        l.d(mapUnmodifiableMap, "Collections.unmodifiableMap(LinkedHashMap(this))");
        return mapUnmodifiableMap;
    }

    public static final long O(String toLongOrDefault, long j7) {
        l.e(toLongOrDefault, "$this$toLongOrDefault");
        try {
            return Long.parseLong(toLongOrDefault);
        } catch (NumberFormatException unused) {
            return j7;
        }
    }

    public static final int P(String str, int i7) {
        if (str != null) {
            try {
                long j7 = Long.parseLong(str);
                if (j7 > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (j7 < 0) {
                    return 0;
                }
                return (int) j7;
            } catch (NumberFormatException unused) {
            }
        }
        return i7;
    }

    public static final String Q(String trimSubstring, int i7, int i8) {
        l.e(trimSubstring, "$this$trimSubstring");
        int iV = v(trimSubstring, i7, i8);
        String strSubstring = trimSubstring.substring(iV, x(trimSubstring, iV, i8));
        l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String R(String str, int i7, int i8, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i7 = 0;
        }
        if ((i9 & 2) != 0) {
            i8 = str.length();
        }
        return Q(str, i7, i8);
    }

    public static final Throwable S(Exception withSuppressed, List<? extends Exception> suppressed) {
        l.e(withSuppressed, "$this$withSuppressed");
        l.e(suppressed, "suppressed");
        if (suppressed.size() > 1) {
            System.out.println(suppressed);
        }
        Iterator<? extends Exception> it = suppressed.iterator();
        while (it.hasNext()) {
            g5.b.a(withSuppressed, it.next());
        }
        return withSuppressed;
    }

    public static final void T(p6.f writeMedium, int i7) {
        l.e(writeMedium, "$this$writeMedium");
        writeMedium.M((i7 >>> 16) & 255);
        writeMedium.M((i7 >>> 8) & 255);
        writeMedium.M(i7 & 255);
    }

    public static final <E> void a(List<E> addIfAbsent, E e7) {
        l.e(addIfAbsent, "$this$addIfAbsent");
        if (addIfAbsent.contains(e7)) {
            return;
        }
        addIfAbsent.add(e7);
    }

    public static final int b(byte b7, int i7) {
        return b7 & i7;
    }

    public static final int c(short s6, int i7) {
        return s6 & i7;
    }

    public static final long d(int i7, long j7) {
        return ((long) i7) & j7;
    }

    public static final r.c e(c6.r asFactory) {
        l.e(asFactory, "$this$asFactory");
        return new a(asFactory);
    }

    public static final boolean f(String canParseAsIpAddress) {
        l.e(canParseAsIpAddress, "$this$canParseAsIpAddress");
        return f5966g.a(canParseAsIpAddress);
    }

    public static final boolean g(u canReuseConnectionFor, u other) {
        l.e(canReuseConnectionFor, "$this$canReuseConnectionFor");
        l.e(other, "other");
        return l.a(canReuseConnectionFor.h(), other.h()) && canReuseConnectionFor.l() == other.l() && l.a(canReuseConnectionFor.p(), other.p());
    }

    public static final void h(long j7, long j8, long j9) {
        if ((j8 | j9) < 0 || j8 > j7 || j7 - j8 < j9) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static final void i(Closeable closeQuietly) {
        l.e(closeQuietly, "$this$closeQuietly");
        try {
            closeQuietly.close();
        } catch (RuntimeException e7) {
            throw e7;
        } catch (Exception unused) {
        }
    }

    public static final void j(Socket closeQuietly) {
        l.e(closeQuietly, "$this$closeQuietly");
        try {
            closeQuietly.close();
        } catch (AssertionError e7) {
            throw e7;
        } catch (RuntimeException e8) {
            if (!l.a(e8.getMessage(), "bio == null")) {
                throw e8;
            }
        } catch (Exception unused) {
        }
    }

    public static final String[] k(String[] concat, String value) {
        l.e(concat, "$this$concat");
        l.e(value, "value");
        Object[] objArrCopyOf = Arrays.copyOf(concat, concat.length + 1);
        l.d(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        String[] strArr = (String[]) objArrCopyOf;
        strArr[h5.l.r(strArr)] = value;
        return strArr;
    }

    public static final int l(String delimiterOffset, char c7, int i7, int i8) {
        l.e(delimiterOffset, "$this$delimiterOffset");
        while (i7 < i8) {
            if (delimiterOffset.charAt(i7) == c7) {
                return i7;
            }
            i7++;
        }
        return i8;
    }

    public static final int m(String delimiterOffset, String delimiters, int i7, int i8) {
        l.e(delimiterOffset, "$this$delimiterOffset");
        l.e(delimiters, "delimiters");
        while (i7 < i8) {
            if (q.E(delimiters, delimiterOffset.charAt(i7), false, 2, null)) {
                return i7;
            }
            i7++;
        }
        return i8;
    }

    public static /* synthetic */ int n(String str, char c7, int i7, int i8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            i7 = 0;
        }
        if ((i9 & 4) != 0) {
            i8 = str.length();
        }
        return l(str, c7, i7, i8);
    }

    public static final boolean o(p6.a0 discard, int i7, TimeUnit timeUnit) {
        l.e(discard, "$this$discard");
        l.e(timeUnit, "timeUnit");
        try {
            return G(discard, i7, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static final String p(String format, Object... args) {
        l.e(format, "format");
        l.e(args, "args");
        kotlin.jvm.internal.u uVar = kotlin.jvm.internal.u.f7511a;
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(args, args.length);
        String str = String.format(locale, format, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        l.d(str, "java.lang.String.format(locale, format, *args)");
        return str;
    }

    public static final boolean q(String[] hasIntersection, String[] strArr, Comparator<? super String> comparator) {
        l.e(hasIntersection, "$this$hasIntersection");
        l.e(comparator, "comparator");
        if (!(hasIntersection.length == 0) && strArr != null) {
            if (!(strArr.length == 0)) {
                for (String str : hasIntersection) {
                    for (String str2 : strArr) {
                        if (comparator.compare(str, str2) == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static final long r(b0 headersContentLength) {
        l.e(headersContentLength, "$this$headersContentLength");
        String strC = headersContentLength.V().c("Content-Length");
        if (strC != null) {
            return O(strC, -1L);
        }
        return -1L;
    }

    @SafeVarargs
    public static final <T> List<T> s(T... elements) {
        l.e(elements, "elements");
        Object[] objArr = (Object[]) elements.clone();
        List<T> listUnmodifiableList = Collections.unmodifiableList(h5.p.j(Arrays.copyOf(objArr, objArr.length)));
        l.d(listUnmodifiableList, "Collections.unmodifiable…istOf(*elements.clone()))");
        return listUnmodifiableList;
    }

    public static final int t(String[] indexOf, String value, Comparator<String> comparator) {
        l.e(indexOf, "$this$indexOf");
        l.e(value, "value");
        l.e(comparator, "comparator");
        int length = indexOf.length;
        for (int i7 = 0; i7 < length; i7++) {
            if (comparator.compare(indexOf[i7], value) == 0) {
                return i7;
            }
        }
        return -1;
    }

    public static final int u(String indexOfControlOrNonAscii) {
        l.e(indexOfControlOrNonAscii, "$this$indexOfControlOrNonAscii");
        int length = indexOfControlOrNonAscii.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = indexOfControlOrNonAscii.charAt(i7);
            if (l.g(cCharAt, 31) <= 0 || l.g(cCharAt, 127) >= 0) {
                return i7;
            }
        }
        return -1;
    }

    public static final int v(String indexOfFirstNonAsciiWhitespace, int i7, int i8) {
        l.e(indexOfFirstNonAsciiWhitespace, "$this$indexOfFirstNonAsciiWhitespace");
        while (i7 < i8) {
            char cCharAt = indexOfFirstNonAsciiWhitespace.charAt(i7);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i7;
            }
            i7++;
        }
        return i8;
    }

    public static /* synthetic */ int w(String str, int i7, int i8, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i7 = 0;
        }
        if ((i9 & 2) != 0) {
            i8 = str.length();
        }
        return v(str, i7, i8);
    }

    public static final int x(String indexOfLastNonAsciiWhitespace, int i7, int i8) {
        l.e(indexOfLastNonAsciiWhitespace, "$this$indexOfLastNonAsciiWhitespace");
        int i9 = i8 - 1;
        if (i9 >= i7) {
            while (true) {
                char cCharAt = indexOfLastNonAsciiWhitespace.charAt(i9);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                    return i9 + 1;
                }
                if (i9 == i7) {
                    break;
                }
                i9--;
            }
        }
        return i7;
    }

    public static /* synthetic */ int y(String str, int i7, int i8, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i7 = 0;
        }
        if ((i9 & 2) != 0) {
            i8 = str.length();
        }
        return x(str, i7, i8);
    }

    public static final int z(String indexOfNonWhitespace, int i7) {
        l.e(indexOfNonWhitespace, "$this$indexOfNonWhitespace");
        int length = indexOfNonWhitespace.length();
        while (i7 < length) {
            char cCharAt = indexOfNonWhitespace.charAt(i7);
            if (cCharAt != ' ' && cCharAt != '\t') {
                return i7;
            }
            i7++;
        }
        return indexOfNonWhitespace.length();
    }
}
