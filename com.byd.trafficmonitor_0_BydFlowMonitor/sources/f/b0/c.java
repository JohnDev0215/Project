package f.b0;

import f.s;
import f.x;
import f.z;
import g.e;
import g.p;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.net.IDN;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okio.ByteString;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f5012a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final z f5014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final x f5015d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f5013b = new String[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ByteString f5016e = ByteString.decodeHex("efbbbf");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ByteString f5017f = ByteString.decodeHex("feff");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ByteString f5018g = ByteString.decodeHex("fffe");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final ByteString f5019h = ByteString.decodeHex("0000ffff");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final ByteString f5020i = ByteString.decodeHex("ffff0000");
    public static final Charset j = Charset.forName("UTF-8");
    public static final Charset k = Charset.forName("UTF-16BE");
    public static final Charset l = Charset.forName("UTF-16LE");
    public static final Charset m = Charset.forName("UTF-32BE");
    public static final Charset n = Charset.forName("UTF-32LE");
    public static final TimeZone o = TimeZone.getTimeZone("GMT");
    public static final Pattern p = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    /* JADX INFO: compiled from: Util.java */
    public static class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5021a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f5022b;

        public a(String str, boolean z) {
            this.f5021a = str;
            this.f5022b = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.f5021a);
            thread.setDaemon(this.f5022b);
            return thread;
        }
    }

    static {
        byte[] bArr = new byte[0];
        f5012a = bArr;
        f5014c = z.R(null, bArr);
        f5015d = x.create((s) null, bArr);
    }

    public static Charset a(e eVar, Charset charset) throws IOException {
        if (eVar.H(0L, f5016e)) {
            eVar.o(r0.size());
            return j;
        }
        if (eVar.H(0L, f5017f)) {
            eVar.o(r0.size());
            return k;
        }
        if (eVar.H(0L, f5018g)) {
            eVar.o(r0.size());
            return l;
        }
        if (eVar.H(0L, f5019h)) {
            eVar.o(r0.size());
            return m;
        }
        if (!eVar.H(0L, f5020i)) {
            return charset;
        }
        eVar.o(r0.size());
        return n;
    }

    public static void b(long j2, long j3, long j4) {
        if ((j3 | j4) < 0 || j3 > j2 || j2 - j3 < j4) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void c(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static void d(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e2) {
                if (!t(e2)) {
                    throw e2;
                }
            } catch (RuntimeException e3) {
                throw e3;
            } catch (Exception unused) {
            }
        }
    }

    public static String[] e(String[] strArr, String str) {
        int length = strArr.length + 1;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[length - 1] = str;
        return strArr2;
    }

    public static boolean f(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt <= 31 || cCharAt >= 127 || " #%/:?@[\\]".indexOf(cCharAt) != -1) {
                return true;
            }
        }
        return false;
    }

    public static int g(String str, int i2, int i3, char c2) {
        while (i2 < i3) {
            if (str.charAt(i2) == c2) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static int h(String str, int i2, int i3, String str2) {
        while (i2 < i3) {
            if (str2.indexOf(str.charAt(i2)) != -1) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static boolean i(p pVar, int i2, TimeUnit timeUnit) {
        try {
            return u(pVar, i2, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static String j(String str) {
        try {
            String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (lowerCase.isEmpty()) {
                return null;
            }
            if (f(lowerCase)) {
                return null;
            }
            return lowerCase;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static boolean k(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static String l(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String m(HttpUrl httpUrl, boolean z) {
        String strL;
        if (httpUrl.l().contains(":")) {
            strL = "[" + httpUrl.l() + "]";
        } else {
            strL = httpUrl.l();
        }
        if (!z && httpUrl.x() == HttpUrl.e(httpUrl.C())) {
            return strL;
        }
        return strL + ":" + httpUrl.x();
    }

    public static <T> List<T> n(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> o(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static <T> int p(T[] tArr, T t) {
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (k(tArr[i2], t)) {
                return i2;
            }
        }
        return -1;
    }

    public static int q(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt <= 31 || cCharAt >= 127) {
                return i2;
            }
        }
        return -1;
    }

    public static <T> List<T> r(T[] tArr, T[] tArr2) {
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            int length = tArr2.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    T t2 = tArr2[i2];
                    if (t.equals(t2)) {
                        arrayList.add(t2);
                        break;
                    }
                    i2++;
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T[] s(Class<T> cls, T[] tArr, T[] tArr2) {
        List listR = r(tArr, tArr2);
        return (T[]) listR.toArray((Object[]) Array.newInstance((Class<?>) cls, listR.size()));
    }

    public static boolean t(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static boolean u(p pVar, int i2, TimeUnit timeUnit) throws IOException {
        long jNanoTime = System.nanoTime();
        long jC = pVar.b().e() ? pVar.b().c() - jNanoTime : Long.MAX_VALUE;
        pVar.b().d(Math.min(jC, timeUnit.toNanos(i2)) + jNanoTime);
        try {
            g.c cVar = new g.c();
            while (pVar.m(cVar, 8192L) != -1) {
                cVar.O();
            }
            if (jC == Long.MAX_VALUE) {
                pVar.b().a();
            } else {
                pVar.b().d(jNanoTime + jC);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (jC == Long.MAX_VALUE) {
                pVar.b().a();
            } else {
                pVar.b().d(jNanoTime + jC);
            }
            return false;
        } catch (Throwable th) {
            if (jC == Long.MAX_VALUE) {
                pVar.b().a();
            } else {
                pVar.b().d(jNanoTime + jC);
            }
            throw th;
        }
    }

    public static int v(String str, int i2, int i3) {
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static int w(String str, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            char cCharAt = str.charAt(i4);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i4 + 1;
            }
        }
        return i2;
    }

    public static ThreadFactory x(String str, boolean z) {
        return new a(str, z);
    }

    public static String y(String str, int i2, int i3) {
        int iV = v(str, i2, i3);
        return str.substring(iV, w(str, iV, i3));
    }

    public static boolean z(String str) {
        return p.matcher(str).matches();
    }
}
