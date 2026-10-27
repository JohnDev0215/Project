package h.a.a.a.a;

import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;

/* JADX INFO: compiled from: BaseNCodec.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CodecPolicy f5510a = CodecPolicy.LENIENT;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f5511b = {13, 10};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public final byte f5512c = 61;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte f5513d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5514e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5515f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5516g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f5517h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CodecPolicy f5518i;

    /* JADX INFO: compiled from: BaseNCodec.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5519a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f5520b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public byte[] f5521c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5522d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5523e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f5524f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5525g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f5526h;

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", a.class.getSimpleName(), Arrays.toString(this.f5521c), Integer.valueOf(this.f5525g), Boolean.valueOf(this.f5524f), Integer.valueOf(this.f5519a), Long.valueOf(this.f5520b), Integer.valueOf(this.f5526h), Integer.valueOf(this.f5522d), Integer.valueOf(this.f5523e));
        }
    }

    public b(int i2, int i3, int i4, int i5, byte b2, CodecPolicy codecPolicy) {
        this.f5514e = i2;
        this.f5515f = i3;
        this.f5516g = i4 > 0 && i5 > 0 ? (i4 / i3) * i3 : 0;
        this.f5517h = i5;
        this.f5513d = b2;
        Objects.requireNonNull(codecPolicy, "codecPolicy");
        this.f5518i = codecPolicy;
    }

    public static int b(int i2, int i3) {
        return Integer.compare(i2 - Integer.MIN_VALUE, i3 - Integer.MIN_VALUE);
    }

    public static int d(int i2) {
        if (i2 >= 0) {
            if (i2 > 2147483639) {
                return i2;
            }
            return 2147483639;
        }
        throw new OutOfMemoryError("Unable to allocate array size: " + (((long) i2) & 4294967295L));
    }

    public static byte[] m(a aVar, int i2) {
        int length = aVar.f5521c.length * 2;
        if (b(length, i2) < 0) {
            length = i2;
        }
        if (b(length, 2147483639) > 0) {
            length = d(i2);
        }
        byte[] bArr = new byte[length];
        byte[] bArr2 = aVar.f5521c;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        aVar.f5521c = bArr;
        return bArr;
    }

    public int a(a aVar) {
        if (aVar.f5521c != null) {
            return aVar.f5522d - aVar.f5523e;
        }
        return 0;
    }

    public boolean c(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b2 : bArr) {
            if (this.f5513d == b2 || k(b2)) {
                return true;
            }
        }
        return false;
    }

    public abstract void e(byte[] bArr, int i2, int i3, a aVar);

    public byte[] f(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? bArr : g(bArr, 0, bArr.length);
    }

    public byte[] g(byte[] bArr, int i2, int i3) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        e(bArr, i2, i3, aVar);
        e(bArr, i2, -1, aVar);
        int i4 = aVar.f5522d - aVar.f5523e;
        byte[] bArr2 = new byte[i4];
        l(bArr2, 0, i4, aVar);
        return bArr2;
    }

    public byte[] h(int i2, a aVar) {
        byte[] bArr = aVar.f5521c;
        if (bArr == null) {
            aVar.f5521c = new byte[Math.max(i2, i())];
            aVar.f5522d = 0;
            aVar.f5523e = 0;
        } else {
            int i3 = aVar.f5522d;
            if ((i3 + i2) - bArr.length > 0) {
                return m(aVar, i3 + i2);
            }
        }
        return aVar.f5521c;
    }

    public int i() {
        return 8192;
    }

    public long j(byte[] bArr) {
        int length = bArr.length;
        long j = ((long) (((length + r0) - 1) / this.f5514e)) * ((long) this.f5515f);
        int i2 = this.f5516g;
        return i2 > 0 ? j + ((((((long) i2) + j) - 1) / ((long) i2)) * ((long) this.f5517h)) : j;
    }

    public abstract boolean k(byte b2);

    public int l(byte[] bArr, int i2, int i3, a aVar) {
        if (aVar.f5521c == null) {
            return aVar.f5524f ? -1 : 0;
        }
        int iMin = Math.min(a(aVar), i3);
        System.arraycopy(aVar.f5521c, aVar.f5523e, bArr, i2, iMin);
        int i4 = aVar.f5523e + iMin;
        aVar.f5523e = i4;
        if (i4 >= aVar.f5522d) {
            aVar.f5521c = null;
        }
        return iMin;
    }
}
