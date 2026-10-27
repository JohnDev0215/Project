package e.d0.k;

import e.d0.c;
import f.e;
import f.i;
import f.k;
import java.io.IOException;
import java.io.InputStream;
import java.net.IDN;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: PublicSuffixDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f3548a = {42};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f3549b = new String[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f3550c = {"*"};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f3551d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicBoolean f3552e = new AtomicBoolean(false);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CountDownLatch f3553f = new CountDownLatch(1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f3554g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f3555h;

    public static String a(byte[] bArr, byte[][] bArr2, int i2) {
        int i3;
        boolean z;
        int i4;
        int i5;
        int length = bArr.length;
        int i6 = 0;
        while (i6 < length) {
            int i7 = (i6 + length) / 2;
            while (i7 > -1 && bArr[i7] != 10) {
                i7--;
            }
            int i8 = i7 + 1;
            int i9 = 1;
            while (true) {
                i3 = i8 + i9;
                if (bArr[i3] == 10) {
                    break;
                }
                i9++;
            }
            int i10 = i3 - i8;
            int i11 = i2;
            boolean z2 = false;
            int i12 = 0;
            int i13 = 0;
            while (true) {
                if (z2) {
                    i4 = 46;
                    z = false;
                } else {
                    z = z2;
                    i4 = bArr2[i11][i12] & 255;
                }
                i5 = i4 - (bArr[i8 + i13] & 255);
                if (i5 == 0) {
                    i13++;
                    i12++;
                    if (i13 == i10) {
                        break;
                    }
                    if (bArr2[i11].length != i12) {
                        z2 = z;
                    } else {
                        if (i11 == bArr2.length - 1) {
                            break;
                        }
                        i11++;
                        i12 = -1;
                        z2 = true;
                    }
                } else {
                    break;
                }
            }
            if (i5 >= 0) {
                if (i5 <= 0) {
                    int i14 = i10 - i13;
                    int length2 = bArr2[i11].length - i12;
                    while (true) {
                        i11++;
                        if (i11 >= bArr2.length) {
                            break;
                        }
                        length2 += bArr2[i11].length;
                    }
                    if (length2 >= i14) {
                        if (length2 <= i14) {
                            return new String(bArr, i8, i10, c.j);
                        }
                    }
                }
                i6 = i3 + 1;
            }
            length = i8 - 1;
        }
        return null;
    }

    public static a c() {
        return f3551d;
    }

    public final String[] b(String[] strArr) {
        String str;
        String strA;
        String strA2;
        int i2 = 0;
        if (this.f3552e.get() || !this.f3552e.compareAndSet(false, true)) {
            try {
                this.f3553f.await();
            } catch (InterruptedException unused) {
            }
        } else {
            e();
        }
        synchronized (this) {
            if (this.f3554g == null) {
                throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
            }
        }
        int length = strArr.length;
        byte[][] bArr = new byte[length][];
        for (int i3 = 0; i3 < strArr.length; i3++) {
            bArr[i3] = strArr[i3].getBytes(c.j);
        }
        int i4 = 0;
        while (true) {
            str = null;
            if (i4 >= length) {
                strA = null;
                break;
            }
            strA = a(this.f3554g, bArr, i4);
            if (strA != null) {
                break;
            }
            i4++;
        }
        if (length > 1) {
            byte[][] bArr2 = (byte[][]) bArr.clone();
            for (int i5 = 0; i5 < bArr2.length - 1; i5++) {
                bArr2[i5] = f3548a;
                strA2 = a(this.f3554g, bArr2, i5);
                if (strA2 != null) {
                    break;
                }
            }
            strA2 = null;
        } else {
            strA2 = null;
        }
        if (strA2 != null) {
            while (true) {
                if (i2 >= length - 1) {
                    break;
                }
                String strA3 = a(this.f3555h, bArr, i2);
                if (strA3 != null) {
                    str = strA3;
                    break;
                }
                i2++;
            }
        }
        if (str != null) {
            return ("!" + str).split("\\.");
        }
        if (strA == null && strA2 == null) {
            return f3550c;
        }
        String[] strArrSplit = strA != null ? strA.split("\\.") : f3549b;
        String[] strArrSplit2 = strA2 != null ? strA2.split("\\.") : f3549b;
        return strArrSplit.length > strArrSplit2.length ? strArrSplit : strArrSplit2;
    }

    public String d(String str) {
        int length;
        int length2;
        Objects.requireNonNull(str, "domain == null");
        String[] strArrSplit = IDN.toUnicode(str).split("\\.");
        String[] strArrB = b(strArrSplit);
        if (strArrSplit.length == strArrB.length && strArrB[0].charAt(0) != '!') {
            return null;
        }
        if (strArrB[0].charAt(0) == '!') {
            length = strArrSplit.length;
            length2 = strArrB.length;
        } else {
            length = strArrSplit.length;
            length2 = strArrB.length + 1;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit2 = str.split("\\.");
        for (int i2 = length - length2; i2 < strArrSplit2.length; i2++) {
            sb.append(strArrSplit2[i2]);
            sb.append('.');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public final void e() {
        byte[] bArr;
        InputStream resourceAsStream = a.class.getClassLoader().getResourceAsStream("publicsuffixes.gz");
        byte[] bArr2 = null;
        if (resourceAsStream != null) {
            e eVarB = k.b(new i(k.g(resourceAsStream)));
            try {
                try {
                    byte[] bArr3 = new byte[eVarB.readInt()];
                    eVarB.readFully(bArr3);
                    bArr = new byte[eVarB.readInt()];
                    eVarB.readFully(bArr);
                    c.c(eVarB);
                    bArr2 = bArr3;
                } catch (IOException e2) {
                    e.d0.j.e.h().l(5, "Failed to read public suffix list", e2);
                    c.c(eVarB);
                    bArr = null;
                }
            } catch (Throwable th) {
                c.c(eVarB);
                throw th;
            }
        } else {
            bArr = null;
        }
        synchronized (this) {
            this.f3554g = bArr2;
            this.f3555h = bArr;
        }
        this.f3553f.countDown();
    }
}
