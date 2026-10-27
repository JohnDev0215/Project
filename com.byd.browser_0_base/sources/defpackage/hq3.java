package defpackage;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: WebApkVerifySignature.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hq3 {
    public static final Pattern g = Pattern.compile("webapk:\\d+:([a-fA-F0-9]+)");
    public final ByteBuffer a;
    public int b;
    public int c;
    public int d;
    public String e;
    public ArrayList<a> f;

    /* JADX INFO: compiled from: WebApkVerifySignature.java */
    public static class a implements Comparable<a> {
        public static final C0057a i = new C0057a();
        public final String e;
        public final int f;
        public int g = 0;
        public final int h;

        /* JADX INFO: renamed from: hq3$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: WebApkVerifySignature.java */
        public class C0057a implements Comparator<a> {
            @Override // java.util.Comparator
            public final int compare(a aVar, a aVar2) {
                return aVar.f - aVar2.f;
            }
        }

        public a(String str, int i2, int i3) {
            this.e = str;
            this.f = i2;
            this.h = i3;
        }

        @Override // java.lang.Comparable
        public final int compareTo(a aVar) {
            return this.e.compareTo(aVar.e);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            return this.e.equals(((a) obj).e);
        }

        public final int hashCode() {
            return this.e.hashCode();
        }
    }

    public hq3(MappedByteBuffer mappedByteBuffer) {
        this.a = mappedByteBuffer;
        mappedByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] b(String str) {
        Matcher matcher = g.matcher(str);
        byte[] bArr = null;
        if (!matcher.find()) {
            return null;
        }
        String strGroup = matcher.group(1);
        int length = strGroup.length();
        if (length % 2 == 0) {
            bArr = new byte[length / 2];
            for (int i = 0; i < length; i += 2) {
                bArr[i / 2] = (byte) (Character.digit(strGroup.charAt(i + 1), 16) + (Character.digit(strGroup.charAt(i), 16) << 4));
            }
        }
        return bArr;
    }

    public final int a(Signature signature) throws SignatureException {
        Collections.sort(this.f);
        int i = 0;
        for (a aVar : this.f) {
            if (aVar.e.indexOf("META-INF/") == 0) {
                i++;
                if (i > 5) {
                    return 6;
                }
            } else {
                byte[] bytes = aVar.e.getBytes();
                int length = bytes.length;
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
                ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                byteBufferAllocate.order(byteOrder);
                byteBufferAllocate.putInt(length);
                signature.update(byteBufferAllocate.array());
                signature.update(bytes);
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(4);
                byteBufferAllocate2.order(byteOrder);
                int i2 = aVar.h;
                byteBufferAllocate2.putInt(i2);
                signature.update(byteBufferAllocate2.array());
                h(aVar.f + aVar.g);
                ByteBuffer byteBufferSlice = this.a.slice();
                byteBufferSlice.limit(i2);
                signature.update(byteBufferSlice);
            }
        }
        return 0;
    }

    public final int c() {
        return this.a.getShort();
    }

    public final int d() {
        return this.a.getInt();
    }

    public final int e() {
        this.f = new ArrayList<>(this.b);
        h(this.c);
        for (int i = 0; i < this.b; i++) {
            if (d() != 33639248) {
                return 1;
            }
            i(16);
            int iD = d();
            i(4);
            int iC = c();
            int iC2 = c();
            int iC3 = c();
            i(8);
            int iD2 = d();
            String strG = g(iC);
            i(iC2 + iC3);
            if (iC2 > 4160) {
                return 2;
            }
            if (iC3 > 0) {
                return 3;
            }
            this.f.add(new a(strG, iD2, iD));
        }
        ByteBuffer byteBuffer = this.a;
        if (byteBuffer.position() != this.d) {
            return 7;
        }
        Collections.sort(this.f, a.i);
        int i2 = 0;
        for (a aVar : this.f) {
            int i3 = aVar.f;
            if (i3 != i2) {
                return 7;
            }
            h(i3);
            if (d() != 67324752) {
                return 1;
            }
            i(2);
            int iC4 = c();
            i(18);
            int iC5 = c();
            int iC6 = c();
            if (iC6 > 4160) {
                return 2;
            }
            int iPosition = byteBuffer.position();
            int i4 = aVar.f;
            int i5 = (iPosition - i4) + iC5 + iC6;
            aVar.g = i5;
            int i6 = i4 + i5 + aVar.h;
            if ((iC4 & 8) != 0) {
                h(i6);
                i6 = ((long) d()) == 134695760 ? i6 + 16 : i6 + 12;
            }
            i2 = i6;
        }
        int i7 = this.c;
        if (i2 != i7) {
            h(i7 - 16);
            if (!"APK Sig Block 42".equals(g(16))) {
                return 7;
            }
            if (this.c - i2 > 24576) {
                return 8;
            }
        }
        return 0;
    }

    public final int f() {
        ByteBuffer byteBuffer = this.a;
        int iLimit = byteBuffer.limit() - 22;
        int iMax = Math.max(0, iLimit - 65536);
        while (true) {
            if (iLimit < iMax) {
                iLimit = -1;
                break;
            }
            h(iLimit);
            if (d() == 101010256) {
                break;
            }
            iLimit--;
        }
        if (iLimit < 0) {
            return 1;
        }
        this.d = iLimit;
        h(iLimit + 10);
        this.b = c();
        i(4);
        this.c = d();
        this.e = g(c());
        return byteBuffer.position() < byteBuffer.limit() ? 7 : 0;
    }

    public final String g(int i) {
        if (i <= 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        this.a.get(bArr);
        return new String(bArr);
    }

    public final void h(int i) {
        this.a.position(i);
    }

    public final void i(int i) {
        ByteBuffer byteBuffer = this.a;
        byteBuffer.position(byteBuffer.position() + i);
    }

    public final int j(PublicKey publicKey) {
        byte[] bArrB = b(this.e);
        if (bArrB == null || bArrB.length == 0) {
            return 5;
        }
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initVerify(publicKey);
            int iA = a(signature);
            return iA != 0 ? iA : signature.verify(bArrB) ? 0 : 4;
        } catch (Exception e) {
            Log.e("cr_WebApkVerifySignature", "Exception calculating signature", e);
            return 4;
        }
    }
}
