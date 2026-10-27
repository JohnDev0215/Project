package c;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class d implements Serializable, Comparable<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final char[] f628a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f629b = g(new byte[0]);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final byte[] f630c;
    transient int d;
    transient String e;

    d(byte[] bArr) {
        this.f630c = bArr;
    }

    static int a(String str, int i) {
        int length = str.length();
        int iCharCount = 0;
        int i2 = 0;
        while (iCharCount < length) {
            if (i2 == i) {
                return iCharCount;
            }
            int iCodePointAt = str.codePointAt(iCharCount);
            if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                return -1;
            }
            i2++;
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.length();
    }

    public static d c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        d dVar = new d(str.getBytes(m.f647a));
        dVar.e = str;
        return dVar;
    }

    public static d g(byte... bArr) {
        if (bArr != null) {
            return new d((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(d dVar) {
        int iJ = j();
        int iJ2 = dVar.j();
        int iMin = Math.min(iJ, iJ2);
        for (int i = 0; i < iMin; i++) {
            int iD = d(i) & 255;
            int iD2 = dVar.d(i) & 255;
            if (iD != iD2) {
                return iD < iD2 ? -1 : 1;
            }
        }
        if (iJ == iJ2) {
            return 0;
        }
        return iJ < iJ2 ? -1 : 1;
    }

    public byte d(int i) {
        return this.f630c[i];
    }

    public String e() {
        byte[] bArr = this.f630c;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            char[] cArr2 = f628a;
            cArr[i] = cArr2[(b2 >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            int iJ = dVar.j();
            byte[] bArr = this.f630c;
            if (iJ == bArr.length && dVar.i(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    byte[] f() {
        return this.f630c;
    }

    public boolean h(int i, d dVar, int i2, int i3) {
        return dVar.i(i2, this.f630c, i, i3);
    }

    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.f630c);
        this.d = iHashCode;
        return iHashCode;
    }

    public boolean i(int i, byte[] bArr, int i2, int i3) {
        if (i >= 0) {
            byte[] bArr2 = this.f630c;
            if (i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && m.a(bArr2, i, bArr, i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public int j() {
        return this.f630c.length;
    }

    public final boolean k(d dVar) {
        return h(0, dVar, 0, dVar.j());
    }

    public d l(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.f630c;
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.f630c.length + ")");
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i == 0 && i2 == bArr.length) {
            return this;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return new d(bArr2);
    }

    public String m() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f630c, m.f647a);
        this.e = str2;
        return str2;
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        if (this.f630c.length == 0) {
            return "[size=0]";
        }
        String strM = m();
        int iA = a(strM, 64);
        if (iA == -1) {
            if (this.f630c.length <= 64) {
                sb2 = new StringBuilder();
                sb2.append("[hex=");
                sb2.append(e());
                sb2.append("]");
            } else {
                sb2 = new StringBuilder();
                sb2.append("[size=");
                sb2.append(this.f630c.length);
                sb2.append(" hex=");
                sb2.append(l(0, 64).e());
                sb2.append("…]");
            }
            return sb2.toString();
        }
        String strReplace = strM.substring(0, iA).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        if (iA < strM.length()) {
            sb = new StringBuilder();
            sb.append("[size=");
            sb.append(this.f630c.length);
            sb.append(" text=");
            sb.append(strReplace);
            sb.append("…]");
        } else {
            sb = new StringBuilder();
            sb.append("[text=");
            sb.append(strReplace);
            sb.append("]");
        }
        return sb.toString();
    }
}
