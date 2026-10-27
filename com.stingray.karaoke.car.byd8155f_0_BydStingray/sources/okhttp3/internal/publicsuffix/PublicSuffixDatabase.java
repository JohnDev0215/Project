package okhttp3.internal.publicsuffix;

import A7.m;
import P8.k;
import Q8.h;
import h8.AbstractC1387d;
import io.sentry.config.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import l9.o;
import m6.b;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.u;
import r9.AbstractC2312b;
import r9.D;
import r9.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "<init>", "()V", "m6/b", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PublicSuffixDatabase {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f20596e = {42};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final List f20597f = AbstractC1387d.j("*");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final PublicSuffixDatabase f20598g = new PublicSuffixDatabase();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f20599a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CountDownLatch f20600b = new CountDownLatch(1);
    public byte[] c;
    public byte[] d;

    public static List c(String str) {
        List listW0 = h.W0(str, new char[]{'.'});
        return m.a(AbstractC1998m.h0(listW0), "") ? AbstractC1998m.W(listW0) : listW0;
    }

    public final String a(String str) {
        String strA;
        String strA2;
        String strA3;
        List listW0;
        int size;
        int size2;
        int i7 = 0;
        String unicode = IDN.toUnicode(str);
        m.e(unicode, "unicodeDomain");
        List listC = c(unicode);
        if (this.f20599a.get() || !this.f20599a.compareAndSet(false, true)) {
            try {
                this.f20600b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            boolean z6 = false;
            while (true) {
                try {
                    try {
                        b();
                        break;
                    } catch (InterruptedIOException unused2) {
                        Thread.interrupted();
                        z6 = true;
                    } catch (IOException e10) {
                        o oVar = o.f18874a;
                        o.f18874a.getClass();
                        o.i("Failed to read public suffix list", 5, e10);
                        if (z6) {
                        }
                    }
                } finally {
                    if (z6) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        if (this.c == null) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
        }
        int size3 = listC.size();
        byte[][] bArr = new byte[size3][];
        for (int i10 = 0; i10 < size3; i10++) {
            String str2 = (String) listC.get(i10);
            Charset charset = StandardCharsets.UTF_8;
            m.e(charset, "UTF_8");
            byte[] bytes = str2.getBytes(charset);
            m.e(bytes, "this as java.lang.String).getBytes(charset)");
            bArr[i10] = bytes;
        }
        int i11 = 0;
        while (true) {
            if (i11 >= size3) {
                strA = null;
                break;
            }
            byte[] bArr2 = this.c;
            if (bArr2 == null) {
                m.j("publicSuffixListBytes");
                throw null;
            }
            strA = b.a(bArr2, bArr, i11);
            if (strA != null) {
                break;
            }
            i11++;
        }
        if (size3 > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i12 = 0; i12 < length; i12++) {
                bArr3[i12] = f20596e;
                byte[] bArr4 = this.c;
                if (bArr4 == null) {
                    m.j("publicSuffixListBytes");
                    throw null;
                }
                strA2 = b.a(bArr4, bArr3, i12);
                if (strA2 != null) {
                    break;
                }
            }
            strA2 = null;
        } else {
            strA2 = null;
        }
        if (strA2 != null) {
            int i13 = size3 - 1;
            for (int i14 = 0; i14 < i13; i14++) {
                byte[] bArr5 = this.d;
                if (bArr5 == null) {
                    m.j("publicSuffixExceptionListBytes");
                    throw null;
                }
                strA3 = b.a(bArr5, bArr, i14);
                if (strA3 != null) {
                    break;
                }
            }
            strA3 = null;
        } else {
            strA3 = null;
        }
        if (strA3 != null) {
            listW0 = h.W0("!".concat(strA3), new char[]{'.'});
        } else if (strA == null && strA2 == null) {
            listW0 = f20597f;
        } else {
            List listW02 = u.l;
            List listW03 = strA != null ? h.W0(strA, new char[]{'.'}) : listW02;
            if (strA2 != null) {
                listW02 = h.W0(strA2, new char[]{'.'});
            }
            listW0 = listW03.size() > listW02.size() ? listW03 : listW02;
        }
        if (listC.size() == listW0.size() && ((String) listW0.get(0)).charAt(0) != '!') {
            return null;
        }
        if (((String) listW0.get(0)).charAt(0) == '!') {
            size = listC.size();
            size2 = listW0.size();
        } else {
            size = listC.size();
            size2 = listW0.size() + 1;
        }
        k kVarA0 = P8.m.a0(AbstractC1998m.S(c(str)), size - size2);
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        for (Object obj : kVarA0) {
            i7++;
            if (i7 > 1) {
                sb.append((CharSequence) ".");
            }
            a.K(sb, obj, null);
        }
        sb.append((CharSequence) "");
        return sb.toString();
    }

    public final void b() {
        try {
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
            if (resourceAsStream == null) {
                return;
            }
            D dC = AbstractC2312b.c(new s(AbstractC2312b.j(resourceAsStream)));
            try {
                long jQ = dC.q();
                dC.I(jQ);
                byte[] bArrC = dC.f21897m.C(jQ);
                long jQ2 = dC.q();
                dC.I(jQ2);
                byte[] bArrC2 = dC.f21897m.C(jQ2);
                AbstractC1985B.c(dC, null);
                synchronized (this) {
                    this.c = bArrC;
                    this.d = bArrC2;
                }
            } finally {
            }
        } finally {
            this.f20600b.countDown();
        }
    }
}
