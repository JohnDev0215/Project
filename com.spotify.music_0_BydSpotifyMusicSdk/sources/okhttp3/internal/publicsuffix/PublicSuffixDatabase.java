package okhttp3.internal.publicsuffix;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.text.b;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import p.f11;
import p.jb0;
import p.jb2;
import p.kb0;
import p.ou;
import p.u4;
import p.ux2;
import p.v03;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
public final class PublicSuffixDatabase {
    public static final Companion e = new Companion(0);
    public static final byte[] f = {42};
    public static final List g = v03.B("*");
    public static final PublicSuffixDatabase h = new PublicSuffixDatabase();
    public final AtomicBoolean a = new AtomicBoolean(false);
    public final CountDownLatch b = new CountDownLatch(1);
    public byte[] c;
    public byte[] d;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        public static final String a(Companion companion, byte[] bArr, byte[][] bArr2, int i) {
            int i2;
            boolean z;
            int i3;
            int i4;
            companion.getClass();
            int length = bArr.length;
            int i5 = 0;
            while (i5 < length) {
                int i6 = (i5 + length) / 2;
                while (i6 > -1 && bArr[i6] != 10) {
                    i6--;
                }
                int i7 = i6 + 1;
                int i8 = 1;
                while (true) {
                    i2 = i7 + i8;
                    if (bArr[i2] == 10) {
                        break;
                    }
                    i8++;
                }
                int i9 = i2 - i7;
                int i10 = i;
                boolean z2 = false;
                int i11 = 0;
                int i12 = 0;
                while (true) {
                    if (z2) {
                        i3 = 46;
                        z = false;
                    } else {
                        byte b = bArr2[i10][i11];
                        byte[] bArr3 = Util.a;
                        int i13 = b & 255;
                        z = z2;
                        i3 = i13;
                    }
                    byte b2 = bArr[i7 + i12];
                    byte[] bArr4 = Util.a;
                    i4 = i3 - (b2 & 255);
                    if (i4 != 0) {
                        break;
                    }
                    i12++;
                    i11++;
                    if (i12 == i9) {
                        break;
                    }
                    if (bArr2[i10].length != i11) {
                        z2 = z;
                    } else {
                        if (i10 == bArr2.length - 1) {
                            break;
                        }
                        i10++;
                        i11 = -1;
                        z2 = true;
                    }
                }
                if (i4 >= 0) {
                    if (i4 <= 0) {
                        int i14 = i9 - i12;
                        int length2 = bArr2[i10].length - i11;
                        int length3 = bArr2.length;
                        for (int i15 = i10 + 1; i15 < length3; i15++) {
                            length2 += bArr2[i15].length;
                        }
                        if (length2 >= i14) {
                            if (length2 <= i14) {
                                Charset charset = StandardCharsets.UTF_8;
                                v03.i(charset, "UTF_8");
                                return new String(bArr, i7, i9, charset);
                            }
                        }
                    }
                    i5 = i2 + 1;
                }
                length = i6;
            }
            return null;
        }
    }

    public static List c(String str) {
        List listY0 = b.y0(str, new char[]{'.'});
        if (!v03.b(ou.i0(listY0), "")) {
            return listY0;
        }
        int size = listY0.size() - 1;
        return ou.l0(listY0, size >= 0 ? size : 0);
    }

    public final String a(String str) {
        String strA;
        String strA2;
        String strA3;
        List listY0;
        int size;
        int size2;
        String unicode = IDN.toUnicode(str);
        v03.i(unicode, "unicodeDomain");
        List listC = c(unicode);
        int i = 0;
        if (this.a.get() || !this.a.compareAndSet(false, true)) {
            try {
                this.b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            boolean z = false;
            while (true) {
                try {
                    try {
                        b();
                        break;
                    } catch (InterruptedIOException unused2) {
                        Thread.interrupted();
                        z = true;
                    } catch (IOException e2) {
                        Platform.a.getClass();
                        Platform.b.getClass();
                        Platform.i(5, "Failed to read public suffix list", e2);
                        if (z) {
                        }
                    }
                } finally {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        if (this.c == null) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.".toString());
        }
        int size3 = listC.size();
        byte[][] bArr = new byte[size3][];
        for (int i2 = 0; i2 < size3; i2++) {
            String str2 = (String) listC.get(i2);
            Charset charset = StandardCharsets.UTF_8;
            v03.i(charset, "UTF_8");
            byte[] bytes = str2.getBytes(charset);
            v03.i(bytes, "this as java.lang.String).getBytes(charset)");
            bArr[i2] = bytes;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= size3) {
                strA = null;
                break;
            }
            Companion companion = e;
            byte[] bArr2 = this.c;
            if (bArr2 == null) {
                v03.Q("publicSuffixListBytes");
                throw null;
            }
            strA = Companion.a(companion, bArr2, bArr, i3);
            if (strA != null) {
                break;
            }
            i3++;
        }
        if (size3 > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i4 = 0; i4 < length; i4++) {
                bArr3[i4] = f;
                Companion companion2 = e;
                byte[] bArr4 = this.c;
                if (bArr4 == null) {
                    v03.Q("publicSuffixListBytes");
                    throw null;
                }
                strA2 = Companion.a(companion2, bArr4, bArr3, i4);
                if (strA2 != null) {
                    break;
                }
            }
            strA2 = null;
        } else {
            strA2 = null;
        }
        if (strA2 != null) {
            int i5 = size3 - 1;
            for (int i6 = 0; i6 < i5; i6++) {
                Companion companion3 = e;
                byte[] bArr5 = this.d;
                if (bArr5 == null) {
                    v03.Q("publicSuffixExceptionListBytes");
                    throw null;
                }
                strA3 = Companion.a(companion3, bArr5, bArr, i6);
                if (strA3 != null) {
                    break;
                }
            }
            strA3 = null;
        } else {
            strA3 = null;
        }
        if (strA3 != null) {
            listY0 = b.y0("!".concat(strA3), new char[]{'.'});
        } else if (strA == null && strA2 == null) {
            listY0 = g;
        } else {
            List listY02 = strA != null ? b.y0(strA, new char[]{'.'}) : EmptyList.c;
            listY0 = strA2 != null ? b.y0(strA2, new char[]{'.'}) : EmptyList.c;
            if (listY02.size() > listY0.size()) {
                listY0 = listY02;
            }
        }
        if (listC.size() == listY0.size() && ((String) listY0.get(0)).charAt(0) != '!') {
            return null;
        }
        if (((String) listY0.get(0)).charAt(0) == '!') {
            size = listC.size();
            size2 = listY0.size();
        } else {
            size = listC.size();
            size2 = listY0.size() + 1;
        }
        int i7 = size - size2;
        jb2 jb2VarY = ou.Y(c(str));
        if (i7 < 0) {
            throw new IllegalArgumentException(u4.f("Requested element count ", i7, " is less than zero.").toString());
        }
        if (i7 != 0) {
            jb2VarY = jb2VarY instanceof kb0 ? ((kb0) jb2VarY).a(i7) : new jb0(jb2VarY, i7);
        }
        v03.j(jb2VarY, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        for (Object obj : jb2VarY) {
            i++;
            if (i > 1) {
                sb.append((CharSequence) ".");
            }
            v03.a(sb, obj, null);
        }
        sb.append((CharSequence) "");
        String string = sb.toString();
        v03.i(string, "joinTo(StringBuilder(), …ed, transform).toString()");
        return string;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, byte[]] */
    public final void b() {
        try {
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
            if (resourceAsStream == null) {
                return;
            }
            w02 w02VarD = v03.d(new f11(v03.L(resourceAsStream)));
            try {
                long j = w02VarD.readInt();
                w02VarD.q0(j);
                ref$ObjectRef.element = w02VarD.e.M0(j);
                long j2 = w02VarD.readInt();
                w02VarD.q0(j2);
                ref$ObjectRef2.element = w02VarD.e.M0(j2);
                ux2.e(w02VarD, null);
                synchronized (this) {
                    T t = ref$ObjectRef.element;
                    v03.h(t);
                    this.c = (byte[]) t;
                    T t2 = ref$ObjectRef2.element;
                    v03.h(t2);
                    this.d = (byte[]) t2;
                }
            } finally {
            }
        } finally {
            this.b.countDown();
        }
    }
}
