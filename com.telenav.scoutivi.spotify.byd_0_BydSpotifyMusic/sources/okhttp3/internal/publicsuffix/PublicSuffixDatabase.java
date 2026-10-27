package okhttp3.internal.publicsuffix;

import d6.b;
import g5.r;
import h5.o;
import h5.p;
import h5.x;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;
import l6.h;
import y5.q;

/* JADX INFO: compiled from: PublicSuffixDatabase.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PublicSuffixDatabase {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f8677a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final CountDownLatch f8678b = new CountDownLatch(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f8679c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private byte[] f8680d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a f8676h = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final byte[] f8673e = {(byte) 42};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final List<String> f8674f = o.d("*");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final PublicSuffixDatabase f8675g = new PublicSuffixDatabase();

    /* JADX INFO: compiled from: PublicSuffixDatabase.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String b(byte[] bArr, byte[][] bArr2, int i7) {
            int i8;
            boolean z6;
            int iB;
            int iB2;
            int length = bArr.length;
            int i9 = 0;
            while (i9 < length) {
                int i10 = (i9 + length) / 2;
                while (i10 > -1 && bArr[i10] != ((byte) 10)) {
                    i10--;
                }
                int i11 = i10 + 1;
                int i12 = 1;
                while (true) {
                    i8 = i11 + i12;
                    if (bArr[i8] == ((byte) 10)) {
                        break;
                    }
                    i12++;
                }
                int i13 = i8 - i11;
                int i14 = i7;
                boolean z7 = false;
                int i15 = 0;
                int i16 = 0;
                while (true) {
                    if (z7) {
                        iB = 46;
                        z6 = false;
                    } else {
                        z6 = z7;
                        iB = b.b(bArr2[i14][i15], 255);
                    }
                    iB2 = iB - b.b(bArr[i11 + i16], 255);
                    if (iB2 != 0) {
                        break;
                    }
                    i16++;
                    i15++;
                    if (i16 == i13) {
                        break;
                    }
                    if (bArr2[i14].length != i15) {
                        z7 = z6;
                    } else {
                        if (i14 == bArr2.length - 1) {
                            break;
                        }
                        i14++;
                        i15 = -1;
                        z7 = true;
                    }
                }
                if (iB2 >= 0) {
                    if (iB2 <= 0) {
                        int i17 = i13 - i16;
                        int length2 = bArr2[i14].length - i15;
                        int length3 = bArr2.length;
                        for (int i18 = i14 + 1; i18 < length3; i18++) {
                            length2 += bArr2[i18].length;
                        }
                        if (length2 >= i17) {
                            if (length2 <= i17) {
                                Charset UTF_8 = StandardCharsets.UTF_8;
                                l.d(UTF_8, "UTF_8");
                                return new String(bArr, i11, i13, UTF_8);
                            }
                        }
                    }
                    i9 = i8 + 1;
                }
                length = i11 - 1;
            }
            return null;
        }

        public final PublicSuffixDatabase c() {
            return PublicSuffixDatabase.f8675g;
        }
    }

    private final List<String> b(List<String> list) {
        String str;
        String strB;
        String str2;
        List<String> listH;
        List<String> listH2;
        if (this.f8677a.get() || !this.f8677a.compareAndSet(false, true)) {
            try {
                this.f8678b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            e();
        }
        if (!(this.f8679c != null)) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.".toString());
        }
        int size = list.size();
        byte[][] bArr = new byte[size][];
        for (int i7 = 0; i7 < size; i7++) {
            String str3 = list.get(i7);
            Charset UTF_8 = StandardCharsets.UTF_8;
            l.d(UTF_8, "UTF_8");
            Objects.requireNonNull(str3, "null cannot be cast to non-null type java.lang.String");
            byte[] bytes = str3.getBytes(UTF_8);
            l.d(bytes, "(this as java.lang.String).getBytes(charset)");
            bArr[i7] = bytes;
        }
        int i8 = 0;
        while (true) {
            str = null;
            if (i8 >= size) {
                strB = null;
                break;
            }
            a aVar = f8676h;
            byte[] bArr2 = this.f8679c;
            if (bArr2 == null) {
                l.u("publicSuffixListBytes");
            }
            strB = aVar.b(bArr2, bArr, i8);
            if (strB != null) {
                break;
            }
            i8++;
        }
        if (size > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i9 = 0; i9 < length; i9++) {
                bArr3[i9] = f8673e;
                a aVar2 = f8676h;
                byte[] bArr4 = this.f8679c;
                if (bArr4 == null) {
                    l.u("publicSuffixListBytes");
                }
                String strB2 = aVar2.b(bArr4, bArr3, i9);
                if (strB2 != null) {
                    str2 = strB2;
                    break;
                }
            }
            str2 = null;
        } else {
            str2 = null;
        }
        if (str2 != null) {
            int i10 = size - 1;
            int i11 = 0;
            while (true) {
                if (i11 >= i10) {
                    break;
                }
                a aVar3 = f8676h;
                byte[] bArr5 = this.f8680d;
                if (bArr5 == null) {
                    l.u("publicSuffixExceptionListBytes");
                }
                String strB3 = aVar3.b(bArr5, bArr, i11);
                if (strB3 != null) {
                    str = strB3;
                    break;
                }
                i11++;
            }
        }
        if (str != null) {
            return q.l0('!' + str, new char[]{'.'}, false, 0, 6, null);
        }
        if (strB == null && str2 == null) {
            return f8674f;
        }
        if (strB == null || (listH = q.l0(strB, new char[]{'.'}, false, 0, 6, null)) == null) {
            listH = p.h();
        }
        if (str2 == null || (listH2 = q.l0(str2, new char[]{'.'}, false, 0, 6, null)) == null) {
            listH2 = p.h();
        }
        return listH.size() > listH2.size() ? listH : listH2;
    }

    private final void d() throws IOException {
        InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
        if (resourceAsStream == null) {
            return;
        }
        p6.g gVarB = p6.o.b(new p6.l(p6.o.e(resourceAsStream)));
        try {
            byte[] bArrS = gVarB.S(gVarB.x());
            byte[] bArrS2 = gVarB.S(gVarB.x());
            r rVar = r.f6563a;
            p5.a.a(gVarB, null);
            synchronized (this) {
                l.c(bArrS);
                this.f8679c = bArrS;
                l.c(bArrS2);
                this.f8680d = bArrS2;
            }
            this.f8678b.countDown();
        } finally {
        }
    }

    private final void e() {
        boolean z6 = false;
        while (true) {
            try {
                try {
                    d();
                    break;
                } catch (InterruptedIOException unused) {
                    Thread.interrupted();
                    z6 = true;
                } catch (IOException e7) {
                    h.f8004c.g().j("Failed to read public suffix list", 5, e7);
                    if (z6) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z6) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z6) {
            Thread.currentThread().interrupt();
        }
    }

    private final List<String> f(String str) {
        List<String> listL0 = q.l0(str, new char[]{'.'}, false, 0, 6, null);
        return l.a((String) x.B(listL0), "") ? x.u(listL0, 1) : listL0;
    }

    public final String c(String domain) {
        int size;
        int size2;
        l.e(domain, "domain");
        String unicodeDomain = IDN.toUnicode(domain);
        l.d(unicodeDomain, "unicodeDomain");
        List<String> listF = f(unicodeDomain);
        List<String> listB = b(listF);
        if (listF.size() == listB.size() && listB.get(0).charAt(0) != '!') {
            return null;
        }
        if (listB.get(0).charAt(0) == '!') {
            size = listF.size();
            size2 = listB.size();
        } else {
            size = listF.size();
            size2 = listB.size() + 1;
        }
        return x5.l.i(x5.l.f(x.t(f(domain)), size - size2), ".", null, null, 0, null, null, 62, null);
    }
}
