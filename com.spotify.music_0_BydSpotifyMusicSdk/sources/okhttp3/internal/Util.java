package okhttp3.internal;

import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;
import java.io.Closeable;
import java.io.EOFException;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.collections.c;
import kotlin.text.Regex;
import kotlin.text.b;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ResponseBody$Companion$asResponseBody$1;
import okhttp3.internal.http2.Header;
import okio.ByteString;
import p.ar1;
import p.bk0;
import p.cs;
import p.de2;
import p.fo;
import p.ko;
import p.m1;
import p.ng2;
import p.ou;
import p.qo;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Util {
    public static final byte[] a;
    public static final Headers b;
    public static final ResponseBody$Companion$asResponseBody$1 c;
    public static final ar1 d;
    public static final TimeZone e;
    public static final Regex f;
    public static final String g;

    static {
        byte[] bArr = new byte[0];
        a = bArr;
        Headers.e.getClass();
        b = Headers.Companion.c(new String[0]);
        ResponseBody.e.getClass();
        fo foVar = new fo();
        foVar.U0(bArr);
        c = new ResponseBody$Companion$asResponseBody$1(null, 0, foVar);
        RequestBody.a.getClass();
        RequestBody.Companion.a(bArr, null, 0, 0);
        ByteString byteString = ByteString.k;
        d = qo.r(qo.o("efbbbf"), qo.o("feff"), qo.o("fffe"), qo.o("0000ffff"), qo.o("ffff0000"));
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        v03.h(timeZone);
        e = timeZone;
        f = new Regex("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
        String strV0 = b.v0("okhttp3.", OkHttpClient.class.getName());
        if (ng2.a0(strV0, "Client", false)) {
            strV0 = strV0.substring(0, strV0.length() - "Client".length());
            v03.i(strV0, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        g = strV0;
    }

    public static final int A(int i, String str) {
        if (str == null) {
            return i;
        }
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static final String B(String str, int i, int i2) {
        int iN = n(str, i, i2);
        String strSubstring = str.substring(iN, o(str, iN, i2));
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final boolean a(HttpUrl httpUrl, HttpUrl httpUrl2) {
        v03.j(httpUrl, "<this>");
        v03.j(httpUrl2, "other");
        return v03.b(httpUrl.d, httpUrl2.d) && httpUrl.e == httpUrl2.e && v03.b(httpUrl.a, httpUrl2.a);
    }

    public static final int b(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalStateException("timeout".concat(" < 0").toString());
        }
        if (timeUnit == null) {
            throw new IllegalStateException("unit == null".toString());
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("timeout".concat(" too large.").toString());
        }
        if (millis != 0 || j <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException("timeout".concat(" too small.").toString());
    }

    public static final void c(Closeable closeable) {
        v03.j(closeable, "<this>");
        try {
            closeable.close();
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception unused) {
        }
    }

    public static final void d(Socket socket) {
        try {
            socket.close();
        } catch (AssertionError e2) {
            throw e2;
        } catch (RuntimeException e3) {
            if (!v03.b(e3.getMessage(), "bio == null")) {
                throw e3;
            }
        } catch (Exception unused) {
        }
    }

    public static final int e(int i, String str, int i2, String str2) {
        v03.j(str, "<this>");
        while (i < i2) {
            if (b.h0(str2, str.charAt(i))) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final int f(String str, char c2, int i, int i2) {
        v03.j(str, "<this>");
        while (i < i2) {
            if (str.charAt(i) == c2) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static /* synthetic */ int g(String str, char c2, int i, int i2, int i3) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return f(str, c2, i, i2);
    }

    public static final boolean h(de2 de2Var, TimeUnit timeUnit) {
        v03.j(de2Var, "<this>");
        v03.j(timeUnit, "timeUnit");
        try {
            return v(de2Var, 100, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static final String i(String str, Object... objArr) {
        v03.j(str, "format");
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        String str2 = String.format(locale, str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        v03.i(str2, "format(locale, format, *args)");
        return str2;
    }

    public static final boolean j(String[] strArr, String[] strArr2, Comparator comparator) {
        v03.j(strArr, "<this>");
        v03.j(comparator, "comparator");
        if (strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            for (String str : strArr) {
                m1 m1VarK = bk0.K(strArr2);
                while (m1VarK.hasNext()) {
                    if (comparator.compare(str, (String) m1VarK.next()) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final long k(Response response) {
        String strA = response.n.a("Content-Length");
        if (strA == null) {
            return -1L;
        }
        try {
            return Long.parseLong(strA);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static final List l(Object... objArr) {
        v03.j(objArr, "elements");
        Object[] objArr2 = (Object[]) objArr.clone();
        List listUnmodifiableList = Collections.unmodifiableList(v03.C(Arrays.copyOf(objArr2, objArr2.length)));
        v03.i(listUnmodifiableList, "unmodifiableList(listOf(*elements.clone()))");
        return listUnmodifiableList;
    }

    public static final int m(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (v03.n(cCharAt, 31) <= 0 || v03.n(cCharAt, 127) >= 0) {
                return i;
            }
        }
        return -1;
    }

    public static final int n(String str, int i, int i2) {
        v03.j(str, "<this>");
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final int o(String str, int i, int i2) {
        v03.j(str, "<this>");
        int i3 = i2 - 1;
        if (i <= i3) {
            while (true) {
                char cCharAt = str.charAt(i3);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                    return i3 + 1;
                }
                if (i3 == i) {
                    break;
                }
                i3--;
            }
        }
        return i;
    }

    public static final String[] p(String[] strArr, String[] strArr2, Comparator comparator) {
        v03.j(strArr2, "other");
        v03.j(comparator, "comparator");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (comparator.compare(str, strArr2[i]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i++;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final boolean q(String str) {
        v03.j(str, "name");
        return ng2.b0(str, WebgateAuthorizer.AUTHORIZATION_HEADER, true) || ng2.b0(str, "Cookie", true) || ng2.b0(str, "Proxy-Authorization", true) || ng2.b0(str, "Set-Cookie", true);
    }

    public static final int r(char c2) {
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        if ('a' <= c2 && c2 < 'g') {
            return c2 - 'W';
        }
        if ('A' > c2 || c2 >= 'G') {
            return -1;
        }
        return c2 - '7';
    }

    public static final Charset s(ko koVar, Charset charset) {
        Charset charsetForName;
        v03.j(koVar, "<this>");
        v03.j(charset, "default");
        int iP0 = koVar.p0(d);
        if (iP0 == -1) {
            return charset;
        }
        if (iP0 == 0) {
            Charset charset2 = StandardCharsets.UTF_8;
            v03.i(charset2, "UTF_8");
            return charset2;
        }
        if (iP0 == 1) {
            Charset charset3 = StandardCharsets.UTF_16BE;
            v03.i(charset3, "UTF_16BE");
            return charset3;
        }
        if (iP0 == 2) {
            Charset charset4 = StandardCharsets.UTF_16LE;
            v03.i(charset4, "UTF_16LE");
            return charset4;
        }
        if (iP0 == 3) {
            Charset charset5 = cs.a;
            charsetForName = cs.c;
            if (charsetForName == null) {
                charsetForName = Charset.forName("UTF-32BE");
                v03.i(charsetForName, "forName(\"UTF-32BE\")");
                cs.c = charsetForName;
            }
        } else {
            if (iP0 != 4) {
                throw new AssertionError();
            }
            Charset charset6 = cs.a;
            charsetForName = cs.b;
            if (charsetForName == null) {
                charsetForName = Charset.forName("UTF-32LE");
                v03.i(charsetForName, "forName(\"UTF-32LE\")");
                cs.b = charsetForName;
            }
        }
        return charsetForName;
    }

    public static final int t(ko koVar) {
        v03.j(koVar, "<this>");
        return (koVar.readByte() & 255) | ((koVar.readByte() & 255) << 16) | ((koVar.readByte() & 255) << 8);
    }

    public static final int u(fo foVar) throws EOFException {
        int i = 0;
        while (!foVar.Q() && foVar.G0(0L) == 61) {
            i++;
            foVar.readByte();
        }
        return i;
    }

    public static final boolean v(de2 de2Var, int i, TimeUnit timeUnit) {
        v03.j(de2Var, "<this>");
        v03.j(timeUnit, "timeUnit");
        long jNanoTime = System.nanoTime();
        long jC = de2Var.e().e() ? de2Var.e().c() - jNanoTime : Long.MAX_VALUE;
        de2Var.e().d(Math.min(jC, timeUnit.toNanos(i)) + jNanoTime);
        try {
            fo foVar = new fo();
            while (de2Var.t(foVar, 8192L) != -1) {
                foVar.X();
            }
            if (jC == Long.MAX_VALUE) {
                de2Var.e().a();
            } else {
                de2Var.e().d(jNanoTime + jC);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (jC == Long.MAX_VALUE) {
                de2Var.e().a();
            } else {
                de2Var.e().d(jNanoTime + jC);
            }
            return false;
        } catch (Throwable th) {
            if (jC == Long.MAX_VALUE) {
                de2Var.e().a();
            } else {
                de2Var.e().d(jNanoTime + jC);
            }
            throw th;
        }
    }

    public static final Headers w(List list) {
        v03.j(list, "<this>");
        Headers.Builder builder = new Headers.Builder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Header header = (Header) it.next();
            builder.c(header.a.s(), header.b.s());
        }
        return builder.e();
    }

    public static final String x(HttpUrl httpUrl, boolean z) {
        v03.j(httpUrl, "<this>");
        String str = httpUrl.d;
        if (b.i0(str, ":")) {
            str = "[" + str + ']';
        }
        int i = httpUrl.e;
        if (!z) {
            HttpUrl.k.getClass();
            if (i == HttpUrl.Companion.b(httpUrl.a)) {
                return str;
            }
        }
        return str + ':' + i;
    }

    public static final List y(List list) {
        v03.j(list, "<this>");
        List listUnmodifiableList = Collections.unmodifiableList(ou.o0(list));
        v03.i(listUnmodifiableList, "unmodifiableList(toMutableList())");
        return listUnmodifiableList;
    }

    public static final Map z(Map map) {
        v03.j(map, "<this>");
        if (map.isEmpty()) {
            return c.h0();
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(map));
        v03.i(mapUnmodifiableMap, "{\n    Collections.unmodi…(LinkedHashMap(this))\n  }");
        return mapUnmodifiableMap;
    }
}
