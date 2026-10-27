package androidx.datastore.preferences.protobuf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0944g implements Iterable, Serializable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0944g f11844n = new C0944g(AbstractC0959w.f11891b);
    public static final C0942e o;
    public int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final byte[] f11845m;

    static {
        o = AbstractC0940c.a() ? new C0942e(1) : new C0942e(0);
    }

    public C0944g(byte[] bArr) {
        bArr.getClass();
        this.f11845m = bArr;
    }

    public static int d(int i7, int i10, int i11) {
        int i12 = i10 - i7;
        if ((i7 | i10 | i12 | (i11 - i10)) >= 0) {
            return i12;
        }
        if (i7 < 0) {
            throw new IndexOutOfBoundsException(A7.k.g("Beginning index: ", i7, " < 0"));
        }
        if (i10 < i7) {
            throw new IndexOutOfBoundsException(A7.k.i("Beginning index larger than ending index: ", ", ", i7, i10));
        }
        throw new IndexOutOfBoundsException(A7.k.i("End index: ", " >= ", i10, i11));
    }

    public static C0944g l(int i7, int i10, byte[] bArr) {
        byte[] bArrCopyOfRange;
        d(i7, i7 + i10, bArr.length);
        switch (o.f11836a) {
            case 0:
                bArrCopyOfRange = Arrays.copyOfRange(bArr, i7, i10 + i7);
                break;
            default:
                bArrCopyOfRange = new byte[i10];
                System.arraycopy(bArr, i7, bArrCopyOfRange, 0, i10);
                break;
        }
        return new C0944g(bArrCopyOfRange);
    }

    public byte a(int i7) {
        return this.f11845m[i7];
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0944g) || size() != ((C0944g) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof C0944g)) {
            return obj.equals(this);
        }
        C0944g c0944g = (C0944g) obj;
        int i7 = this.l;
        int i10 = c0944g.l;
        if (i7 != 0 && i10 != 0 && i7 != i10) {
            return false;
        }
        int size = size();
        if (size > c0944g.size()) {
            throw new IllegalArgumentException("Length too large: " + size + size());
        }
        if (size > c0944g.size()) {
            StringBuilder sbJ = M5.b.j("Ran off end of other: 0, ", size, ", ");
            sbJ.append(c0944g.size());
            throw new IllegalArgumentException(sbJ.toString());
        }
        int iQ = q() + size;
        int iQ2 = q();
        int iQ3 = c0944g.q();
        while (iQ2 < iQ) {
            if (this.f11845m[iQ2] != c0944g.f11845m[iQ3]) {
                return false;
            }
            iQ2++;
            iQ3++;
        }
        return true;
    }

    public final int hashCode() {
        int i7 = this.l;
        if (i7 == 0) {
            int size = size();
            int iQ = q();
            int i10 = size;
            for (int i11 = iQ; i11 < iQ + size; i11++) {
                i10 = (i10 * 31) + this.f11845m[i11];
            }
            i7 = i10 == 0 ? 1 : i10;
            this.l = i7;
        }
        return i7;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new C0941d(this);
    }

    public void m(int i7, byte[] bArr) {
        System.arraycopy(this.f11845m, 0, bArr, 0, i7);
    }

    public int q() {
        return 0;
    }

    public byte r(int i7) {
        return this.f11845m[i7];
    }

    public int size() {
        return this.f11845m.length;
    }

    public final String toString() {
        String string;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        int size = size();
        if (size() <= 50) {
            string = d0.c(this);
        } else {
            StringBuilder sb = new StringBuilder();
            int iD = d(0, 47, size());
            sb.append(d0.c(iD == 0 ? f11844n : new C0943f(q(), iD, this.f11845m)));
            sb.append("...");
            string = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder("<ByteString@");
        sb2.append(hexString);
        sb2.append(" size=");
        sb2.append(size);
        sb2.append(" contents=\"");
        return android.hardware.bydauto.ac.tsimpl.a.l(sb2, string, "\">");
    }
}
