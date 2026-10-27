package p6;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: compiled from: ByteString.kt */
/* JADX INFO: loaded from: classes.dex */
public class h implements Serializable, Comparable<h> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private transient int f8871e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private transient String f8872g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final byte[] f8873h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a f8870j = new a(null);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f8869i = new h(new byte[0]);

    /* JADX INFO: compiled from: ByteString.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public static /* synthetic */ h e(a aVar, byte[] bArr, int i7, int i8, int i9, Object obj) {
            if ((i9 & 1) != 0) {
                i7 = 0;
            }
            if ((i9 & 2) != 0) {
                i8 = bArr.length;
            }
            return aVar.d(bArr, i7, i8);
        }

        public final h a(String decodeHex) {
            kotlin.jvm.internal.l.e(decodeHex, "$this$decodeHex");
            if (!(decodeHex.length() % 2 == 0)) {
                throw new IllegalArgumentException(("Unexpected hex string: " + decodeHex).toString());
            }
            int length = decodeHex.length() / 2;
            byte[] bArr = new byte[length];
            for (int i7 = 0; i7 < length; i7++) {
                int i8 = i7 * 2;
                bArr[i7] = (byte) ((q6.b.e(decodeHex.charAt(i8)) << 4) + q6.b.e(decodeHex.charAt(i8 + 1)));
            }
            return new h(bArr);
        }

        public final h b(String encode, Charset charset) {
            kotlin.jvm.internal.l.e(encode, "$this$encode");
            kotlin.jvm.internal.l.e(charset, "charset");
            byte[] bytes = encode.getBytes(charset);
            kotlin.jvm.internal.l.d(bytes, "(this as java.lang.String).getBytes(charset)");
            return new h(bytes);
        }

        public final h c(String encodeUtf8) {
            kotlin.jvm.internal.l.e(encodeUtf8, "$this$encodeUtf8");
            h hVar = new h(b.a(encodeUtf8));
            hVar.o(encodeUtf8);
            return hVar;
        }

        public final h d(byte[] toByteString, int i7, int i8) {
            kotlin.jvm.internal.l.e(toByteString, "$this$toByteString");
            c.b(toByteString.length, i7, i8);
            return new h(h5.k.g(toByteString, i7, i8 + i7));
        }

        public final h f(InputStream readByteString, int i7) throws IOException {
            kotlin.jvm.internal.l.e(readByteString, "$this$readByteString");
            int i8 = 0;
            if (!(i7 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + i7).toString());
            }
            byte[] bArr = new byte[i7];
            while (i8 < i7) {
                int i9 = readByteString.read(bArr, i8, i7 - i8);
                if (i9 == -1) {
                    throw new EOFException();
                }
                i8 += i9;
            }
            return new h(bArr);
        }
    }

    public h(byte[] data) {
        kotlin.jvm.internal.l.e(data, "data");
        this.f8873h = data;
    }

    private final void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, NoSuchFieldException, IOException {
        h hVarF = f8870j.f(objectInputStream, objectInputStream.readInt());
        Field field = h.class.getDeclaredField("h");
        kotlin.jvm.internal.l.d(field, "field");
        field.setAccessible(true);
        field.set(this, hVarF.f8873h);
    }

    private final void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.f8873h.length);
        objectOutputStream.write(this.f8873h);
    }

    public String a() {
        return p6.a.b(e(), null, 1, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r0 < r1) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0028, code lost:
    
        if (r7 < r8) goto L13;
     */
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int compareTo(p6.h r10) {
        /*
            r9 = this;
            java.lang.String r0 = "other"
            kotlin.jvm.internal.l.e(r10, r0)
            int r0 = r9.r()
            int r1 = r10.r()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L13:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L2b
            byte r7 = r9.d(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.d(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L28
            int r4 = r4 + 1
            goto L13
        L28:
            if (r7 >= r8) goto L32
            goto L30
        L2b:
            if (r0 != r1) goto L2e
            goto L33
        L2e:
            if (r0 >= r1) goto L32
        L30:
            r3 = r5
            goto L33
        L32:
            r3 = r6
        L33:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p6.h.compareTo(p6.h):int");
    }

    public h c(String algorithm) {
        kotlin.jvm.internal.l.e(algorithm, "algorithm");
        byte[] bArrDigest = MessageDigest.getInstance(algorithm).digest(this.f8873h);
        kotlin.jvm.internal.l.d(bArrDigest, "MessageDigest.getInstance(algorithm).digest(data)");
        return new h(bArrDigest);
    }

    public final byte d(int i7) {
        return k(i7);
    }

    public final byte[] e() {
        return this.f8873h;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            if (hVar.r() == e().length && hVar.m(0, e(), 0, e().length)) {
                return true;
            }
        }
        return false;
    }

    public final int f() {
        return this.f8871e;
    }

    public int g() {
        return e().length;
    }

    public final String h() {
        return this.f8872g;
    }

    public int hashCode() {
        int iF = f();
        if (iF != 0) {
            return iF;
        }
        int iHashCode = Arrays.hashCode(e());
        n(iHashCode);
        return iHashCode;
    }

    public String i() {
        char[] cArr = new char[e().length * 2];
        int i7 = 0;
        for (byte b7 : e()) {
            int i8 = i7 + 1;
            cArr[i7] = q6.b.f()[(b7 >> 4) & 15];
            i7 = i8 + 1;
            cArr[i8] = q6.b.f()[b7 & 15];
        }
        return new String(cArr);
    }

    public byte[] j() {
        return e();
    }

    public byte k(int i7) {
        return e()[i7];
    }

    public boolean l(int i7, h other, int i8, int i9) {
        kotlin.jvm.internal.l.e(other, "other");
        return other.m(i8, e(), i7, i9);
    }

    public boolean m(int i7, byte[] other, int i8, int i9) {
        kotlin.jvm.internal.l.e(other, "other");
        return i7 >= 0 && i7 <= e().length - i9 && i8 >= 0 && i8 <= other.length - i9 && c.a(e(), i7, other, i8, i9);
    }

    public final void n(int i7) {
        this.f8871e = i7;
    }

    public final void o(String str) {
        this.f8872g = str;
    }

    public h p() {
        return c("SHA-1");
    }

    public h q() {
        return c("SHA-256");
    }

    public final int r() {
        return g();
    }

    public final boolean s(h prefix) {
        kotlin.jvm.internal.l.e(prefix, "prefix");
        return l(0, prefix, 0, prefix.r());
    }

    public h t() {
        byte b7;
        for (int i7 = 0; i7 < e().length; i7++) {
            byte b8 = e()[i7];
            byte b9 = (byte) 65;
            if (b8 >= b9 && b8 <= (b7 = (byte) 90)) {
                byte[] bArrE = e();
                byte[] bArrCopyOf = Arrays.copyOf(bArrE, bArrE.length);
                kotlin.jvm.internal.l.d(bArrCopyOf, "java.util.Arrays.copyOf(this, size)");
                bArrCopyOf[i7] = (byte) (b8 + 32);
                for (int i8 = i7 + 1; i8 < bArrCopyOf.length; i8++) {
                    byte b10 = bArrCopyOf[i8];
                    if (b10 >= b9 && b10 <= b7) {
                        bArrCopyOf[i8] = (byte) (b10 + 32);
                    }
                }
                return new h(bArrCopyOf);
            }
        }
        return this;
    }

    public String toString() {
        if (e().length == 0) {
            return "[size=0]";
        }
        int iC = q6.b.c(e(), 64);
        if (iC != -1) {
            String strU = u();
            Objects.requireNonNull(strU, "null cannot be cast to non-null type java.lang.String");
            String strSubstring = strU.substring(0, iC);
            kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String strW = y5.p.w(y5.p.w(y5.p.w(strSubstring, "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null), "\r", "\\r", false, 4, null);
            if (iC >= strU.length()) {
                return "[text=" + strW + ']';
            }
            return "[size=" + e().length + " text=" + strW + "…]";
        }
        if (e().length <= 64) {
            return "[hex=" + i() + ']';
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[size=");
        sb.append(e().length);
        sb.append(" hex=");
        if (64 <= e().length) {
            sb.append((64 == e().length ? this : new h(h5.k.g(e(), 0, 64))).i());
            sb.append("…]");
            return sb.toString();
        }
        throw new IllegalArgumentException(("endIndex > length(" + e().length + ')').toString());
    }

    public String u() {
        String strH = h();
        if (strH != null) {
            return strH;
        }
        String strB = b.b(j());
        o(strB);
        return strB;
    }

    public void v(e buffer, int i7, int i8) {
        kotlin.jvm.internal.l.e(buffer, "buffer");
        q6.b.d(this, buffer, i7, i8);
    }
}
