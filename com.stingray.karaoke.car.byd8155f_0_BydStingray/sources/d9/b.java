package d9;

import A7.C0054b;
import A7.m;
import Q8.g;
import Q8.h;
import a.AbstractC0904a;
import c9.C;
import c9.F;
import c9.H;
import c9.I;
import c9.u;
import c9.w;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import r9.AbstractC2312b;
import r9.C2319i;
import r9.C2322l;
import r9.D;
import r9.InterfaceC2321k;
import r9.J;
import r9.y;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f13645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final u f13646b = AbstractC0904a.M(new String[0]);
    public static final I c;
    public static final F d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final y f13647e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final TimeZone f13648f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g f13649g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f13650h;

    static {
        byte[] bArr = new byte[0];
        f13645a = bArr;
        C2319i c2319i = new C2319i();
        c2319i.V(bArr);
        long j3 = 0;
        c = new I(null, j3, c2319i, 0);
        b(j3, j3, j3);
        d = new F(null, 0, bArr, 0);
        C2322l c2322l = C2322l.o;
        f13647e = AbstractC2312b.g(m6.b.o("efbbbf"), m6.b.o("feff"), m6.b.o("fffe"), m6.b.o("0000ffff"), m6.b.o("ffff0000"));
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        m.c(timeZone);
        f13648f = timeZone;
        f13649g = new g("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
        String strS0 = h.S0(C.class.getName(), "okhttp3.");
        if (h.E0(strS0, "Client")) {
            strS0 = strS0.substring(0, strS0.length() - 6);
            m.e(strS0, "substring(...)");
        }
        f13650h = strS0;
    }

    public static final boolean a(w wVar, w wVar2) {
        m.f(wVar, "<this>");
        m.f(wVar2, "other");
        return m.a(wVar.d, wVar2.d) && wVar.f12621e == wVar2.f12621e && m.a(wVar.f12619a, wVar2.f12619a);
    }

    public static final void b(long j3, long j10, long j11) {
        if ((j10 | j11) < 0 || j10 > j3 || j3 - j10 < j11) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static final void c(Closeable closeable) {
        m.f(closeable, "<this>");
        try {
            closeable.close();
        } catch (RuntimeException e10) {
            throw e10;
        } catch (Exception unused) {
        }
    }

    public static final void d(Socket socket) {
        m.f(socket, "<this>");
        try {
            socket.close();
        } catch (AssertionError e10) {
            throw e10;
        } catch (RuntimeException e11) {
            if (!m.a(e11.getMessage(), "bio == null")) {
                throw e11;
            }
        } catch (Exception unused) {
        }
    }

    public static final int e(String str, char c10, int i7, int i10) {
        m.f(str, "<this>");
        while (i7 < i10) {
            if (str.charAt(i7) == c10) {
                return i7;
            }
            i7++;
        }
        return i10;
    }

    public static final int f(String str, String str2, int i7, int i10) {
        m.f(str, "<this>");
        while (i7 < i10) {
            if (h.D0(str2, str.charAt(i7))) {
                return i7;
            }
            i7++;
        }
        return i10;
    }

    public static /* synthetic */ int g(String str, char c10, int i7, int i10, int i11) {
        if ((i11 & 2) != 0) {
            i7 = 0;
        }
        if ((i11 & 4) != 0) {
            i10 = str.length();
        }
        return e(str, c10, i7, i10);
    }

    public static final boolean h(J j3, TimeUnit timeUnit) {
        m.f(timeUnit, "timeUnit");
        try {
            return u(j3, 100, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static final String i(String str, Object... objArr) {
        m.f(str, "format");
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        return String.format(locale, str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
    }

    public static final boolean j(String[] strArr, String[] strArr2, Comparator comparator) {
        m.f(strArr, "<this>");
        if (strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            for (String str : strArr) {
                C0054b c0054bH = m.h(strArr2);
                while (c0054bH.hasNext()) {
                    if (comparator.compare(str, (String) c0054bH.next()) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final long k(H h10) {
        String strD = h10.f12517q.d("Content-Length");
        if (strD == null) {
            return -1L;
        }
        try {
            return Long.parseLong(strD);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static final List l(Object... objArr) {
        m.f(objArr, "elements");
        Object[] objArr2 = (Object[]) objArr.clone();
        List listUnmodifiableList = Collections.unmodifiableList(AbstractC1999n.D(Arrays.copyOf(objArr2, objArr2.length)));
        m.e(listUnmodifiableList, "unmodifiableList(listOf(*elements.clone()))");
        return listUnmodifiableList;
    }

    public static final int m(String str) {
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (m.g(cCharAt, 31) <= 0 || m.g(cCharAt, 127) >= 0) {
                return i7;
            }
        }
        return -1;
    }

    public static final int n(String str, int i7, int i10) {
        m.f(str, "<this>");
        while (i7 < i10) {
            char cCharAt = str.charAt(i7);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i7;
            }
            i7++;
        }
        return i10;
    }

    public static final int o(String str, int i7, int i10) {
        m.f(str, "<this>");
        int i11 = i10 - 1;
        if (i7 <= i11) {
            while (true) {
                char cCharAt = str.charAt(i11);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                    return i11 + 1;
                }
                if (i11 == i7) {
                    break;
                }
                i11--;
            }
        }
        return i7;
    }

    public static final String[] p(String[] strArr, String[] strArr2, Comparator comparator) {
        m.f(strArr2, "other");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    break;
                }
                if (comparator.compare(str, strArr2[i7]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i7++;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final boolean q(String str) {
        m.f(str, "name");
        return str.equalsIgnoreCase("Authorization") || str.equalsIgnoreCase("Cookie") || str.equalsIgnoreCase("Proxy-Authorization") || str.equalsIgnoreCase("Set-Cookie");
    }

    public static final int r(char c10) {
        if ('0' <= c10 && c10 < ':') {
            return c10 - '0';
        }
        if ('a' <= c10 && c10 < 'g') {
            return c10 - 'W';
        }
        if ('A' > c10 || c10 >= 'G') {
            return -1;
        }
        return c10 - '7';
    }

    public static final Charset s(InterfaceC2321k interfaceC2321k, Charset charset) {
        Charset charsetForName;
        m.f(interfaceC2321k, "<this>");
        m.f(charset, "default");
        int iX = interfaceC2321k.x(f13647e);
        if (iX == -1) {
            return charset;
        }
        if (iX == 0) {
            Charset charset2 = StandardCharsets.UTF_8;
            m.e(charset2, "UTF_8");
            return charset2;
        }
        if (iX == 1) {
            Charset charset3 = StandardCharsets.UTF_16BE;
            m.e(charset3, "UTF_16BE");
            return charset3;
        }
        if (iX == 2) {
            Charset charset4 = StandardCharsets.UTF_16LE;
            m.e(charset4, "UTF_16LE");
            return charset4;
        }
        if (iX == 3) {
            Charset charset5 = Q8.a.f7211a;
            charsetForName = Q8.a.c;
            if (charsetForName == null) {
                charsetForName = Charset.forName("UTF-32BE");
                m.e(charsetForName, "forName(...)");
                Q8.a.c = charsetForName;
            }
        } else {
            if (iX != 4) {
                throw new AssertionError();
            }
            Charset charset6 = Q8.a.f7211a;
            charsetForName = Q8.a.f7212b;
            if (charsetForName == null) {
                charsetForName = Charset.forName("UTF-32LE");
                m.e(charsetForName, "forName(...)");
                Q8.a.f7212b = charsetForName;
            }
        }
        return charsetForName;
    }

    public static final int t(D d10) {
        m.f(d10, "<this>");
        return (d10.k() & 255) | ((d10.k() & 255) << 16) | ((d10.k() & 255) << 8);
    }

    public static final boolean u(J j3, int i7, TimeUnit timeUnit) {
        m.f(timeUnit, "timeUnit");
        long jNanoTime = System.nanoTime();
        long jC = j3.e().e() ? j3.e().c() - jNanoTime : Long.MAX_VALUE;
        j3.e().d(Math.min(jC, timeUnit.toNanos(i7)) + jNanoTime);
        try {
            C2319i c2319i = new C2319i();
            while (j3.p(c2319i, 8192L) != -1) {
                c2319i.b();
            }
            if (jC == Long.MAX_VALUE) {
                j3.e().a();
            } else {
                j3.e().d(jNanoTime + jC);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (jC == Long.MAX_VALUE) {
                j3.e().a();
            } else {
                j3.e().d(jNanoTime + jC);
            }
            return false;
        } catch (Throwable th) {
            if (jC == Long.MAX_VALUE) {
                j3.e().a();
            } else {
                j3.e().d(jNanoTime + jC);
            }
            throw th;
        }
    }

    public static final u v(List list) {
        ArrayList arrayList = new ArrayList(20);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            j9.b bVar = (j9.b) it.next();
            String strR = bVar.f17507a.r();
            String strR2 = bVar.f17508b.r();
            arrayList.add(strR);
            arrayList.add(h.g1(strR2).toString());
        }
        return new u((String[]) arrayList.toArray(new String[0]));
    }

    public static final String w(w wVar, boolean z6) {
        m.f(wVar, "<this>");
        String str = wVar.d;
        if (h.C0(str, ":", false)) {
            str = "[" + str + ']';
        }
        int i7 = wVar.f12621e;
        if (!z6) {
            String str2 = wVar.f12619a;
            m.f(str2, "scheme");
            if (i7 == (str2.equals("http") ? 80 : str2.equals("https") ? 443 : -1)) {
                return str;
            }
        }
        return str + ':' + i7;
    }

    public static final List x(List list) {
        m.f(list, "<this>");
        List listUnmodifiableList = Collections.unmodifiableList(AbstractC1998m.A0(list));
        m.e(listUnmodifiableList, "unmodifiableList(toMutableList())");
        return listUnmodifiableList;
    }

    public static final int y(int i7, String str) {
        if (str == null) {
            return i7;
        }
        try {
            long j3 = Long.parseLong(str);
            if (j3 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j3 < 0) {
                return 0;
            }
            return (int) j3;
        } catch (NumberFormatException unused) {
            return i7;
        }
    }

    public static final String z(String str, int i7, int i10) {
        int iN = n(str, i7, i10);
        String strSubstring = str.substring(iN, o(str, iN, i10));
        m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
