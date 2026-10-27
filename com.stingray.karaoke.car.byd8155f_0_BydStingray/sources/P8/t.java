package p8;

import androidx.datastore.preferences.protobuf.C0941d;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class t extends AbstractC2129d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final byte[] f20991m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20992n = 0;

    public t(byte[] bArr) {
        this.f20991m = bArr;
    }

    public final boolean A(t tVar, int i7, int i10) {
        byte[] bArr = tVar.f20991m;
        int length = bArr.length;
        byte[] bArr2 = this.f20991m;
        if (i10 > length) {
            int length2 = bArr2.length;
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i10);
            sb.append(length2);
            throw new IllegalArgumentException(sb.toString());
        }
        int i11 = i7 + i10;
        int length3 = bArr.length;
        byte[] bArr3 = tVar.f20991m;
        if (i11 <= length3) {
            int i12 = 0;
            while (i12 < i10) {
                if (bArr2[i12] != bArr3[i7]) {
                    return false;
                }
                i12++;
                i7++;
            }
            return true;
        }
        int length4 = bArr3.length;
        StringBuilder sb2 = new StringBuilder(59);
        sb2.append("Ran off end of other: ");
        sb2.append(i7);
        sb2.append(", ");
        sb2.append(i10);
        sb2.append(", ");
        sb2.append(length4);
        throw new IllegalArgumentException(sb2.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC2129d) || size() != ((AbstractC2129d) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof t) {
            return A((t) obj, 0, size());
        }
        if (obj instanceof y) {
            return obj.equals(this);
        }
        String strValueOf = String.valueOf(obj.getClass());
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(strValueOf.length() + 49), "Has a new type of ByteString been created? Found ", strValueOf));
    }

    public final int hashCode() {
        int iU = this.f20992n;
        if (iU == 0) {
            int size = size();
            iU = u(size, 0, size);
            if (iU == 0) {
                iU = 1;
            }
            this.f20992n = iU;
        }
        return iU;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new C0941d(this);
    }

    @Override // p8.AbstractC2129d
    public void m(int i7, int i10, int i11, byte[] bArr) {
        System.arraycopy(this.f20991m, i7, bArr, i10, i11);
    }

    @Override // p8.AbstractC2129d
    public final int q() {
        return 0;
    }

    @Override // p8.AbstractC2129d
    public final boolean r() {
        return true;
    }

    @Override // p8.AbstractC2129d
    public final boolean s() {
        byte[] bArr = this.f20991m;
        return AbstractC2115C.c(0, bArr.length, bArr) == 0;
    }

    @Override // p8.AbstractC2129d
    public int size() {
        return this.f20991m.length;
    }

    @Override // p8.AbstractC2129d
    public final int u(int i7, int i10, int i11) {
        for (int i12 = i10; i12 < i10 + i11; i12++) {
            i7 = (i7 * 31) + this.f20991m[i12];
        }
        return i7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0019, code lost:
    
        if (r6[r8] > (-65)) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001c, code lost:
    
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004d, code lost:
    
        if (r6[r8] > (-65)) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0096, code lost:
    
        if (r6[r7] > (-65)) goto L14;
     */
    @Override // p8.AbstractC2129d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int v(int r7, int r8, int r9) {
        /*
            r6 = this;
            int r9 = r9 + r8
            byte[] r6 = r6.f20991m
            if (r7 == 0) goto L99
            if (r8 < r9) goto L9
            goto L9d
        L9:
            byte r0 = (byte) r7
            r1 = -65
            r2 = -1
            r3 = -32
            if (r0 >= r3) goto L22
            r7 = -62
            if (r0 < r7) goto L1f
            int r7 = r8 + 1
            r8 = r6[r8]
            if (r8 <= r1) goto L1c
            goto L1f
        L1c:
            r8 = r7
            goto L99
        L1f:
            r7 = r2
            goto L9d
        L22:
            r4 = -16
            if (r0 >= r4) goto L50
            int r7 = r7 >> 8
            int r7 = ~r7
            byte r7 = (byte) r7
            if (r7 != 0) goto L3b
            int r7 = r8 + 1
            r8 = r6[r8]
            if (r7 < r9) goto L38
            int r7 = p8.AbstractC2115C.a(r0, r8)
            goto L9d
        L38:
            r5 = r8
            r8 = r7
            r7 = r5
        L3b:
            if (r7 > r1) goto L1f
            r4 = -96
            if (r0 != r3) goto L43
            if (r7 < r4) goto L1f
        L43:
            r3 = -19
            if (r0 != r3) goto L49
            if (r7 >= r4) goto L1f
        L49:
            int r7 = r8 + 1
            r8 = r6[r8]
            if (r8 <= r1) goto L1c
            goto L1f
        L50:
            int r3 = r7 >> 8
            int r3 = ~r3
            byte r3 = (byte) r3
            if (r3 != 0) goto L63
            int r7 = r8 + 1
            r3 = r6[r8]
            if (r7 < r9) goto L61
            int r7 = p8.AbstractC2115C.a(r0, r3)
            goto L9d
        L61:
            r8 = 0
            goto L69
        L63:
            int r7 = r7 >> 16
            byte r7 = (byte) r7
            r5 = r8
            r8 = r7
            r7 = r5
        L69:
            if (r8 != 0) goto L85
            int r8 = r7 + 1
            r7 = r6[r7]
            if (r8 < r9) goto L82
            r6 = -12
            if (r0 > r6) goto L1f
            if (r3 > r1) goto L1f
            if (r7 <= r1) goto L7a
            goto L1f
        L7a:
            int r6 = r3 << 8
            r6 = r6 ^ r0
            int r7 = r7 << 16
            r6 = r6 ^ r7
            r7 = r6
            goto L9d
        L82:
            r5 = r8
            r8 = r7
            r7 = r5
        L85:
            if (r3 > r1) goto L1f
            int r0 = r0 << 28
            int r3 = r3 + 112
            int r3 = r3 + r0
            int r0 = r3 >> 30
            if (r0 != 0) goto L1f
            if (r8 > r1) goto L1f
            int r8 = r7 + 1
            r7 = r6[r7]
            if (r7 <= r1) goto L99
            goto L1f
        L99:
            int r7 = p8.AbstractC2115C.c(r8, r9, r6)
        L9d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p8.t.v(int, int, int):int");
    }

    @Override // p8.AbstractC2129d
    public final int w() {
        return this.f20992n;
    }

    @Override // p8.AbstractC2129d
    public final String x() {
        byte[] bArr = this.f20991m;
        return new String(bArr, 0, bArr.length, "UTF-8");
    }

    @Override // p8.AbstractC2129d
    public final void z(OutputStream outputStream, int i7, int i10) throws IOException {
        outputStream.write(this.f20991m, i7, i10);
    }
}
