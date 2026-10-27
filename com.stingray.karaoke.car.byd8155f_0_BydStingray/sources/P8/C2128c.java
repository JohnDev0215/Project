package p8;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: p8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2128c extends OutputStream {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final byte[] f20965q = new byte[0];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20967n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f20968p;
    public final int l = 128;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f20966m = new ArrayList();
    public byte[] o = new byte[128];

    public final void b(int i7) {
        this.f20966m.add(new t(this.o));
        int length = this.f20967n + this.o.length;
        this.f20967n = length;
        this.o = new byte[Math.max(this.l, Math.max(i7, length >>> 1))];
        this.f20968p = 0;
    }

    public final void g() {
        int i7 = this.f20968p;
        byte[] bArr = this.o;
        int length = bArr.length;
        ArrayList arrayList = this.f20966m;
        if (i7 >= length) {
            arrayList.add(new t(this.o));
            this.o = f20965q;
        } else if (i7 > 0) {
            byte[] bArr2 = new byte[i7];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i7));
            arrayList.add(new t(bArr2));
        }
        this.f20967n += this.f20968p;
        this.f20968p = 0;
    }

    public final synchronized AbstractC2129d i() {
        ArrayList arrayList;
        g();
        arrayList = this.f20966m;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add((AbstractC2129d) it.next());
            }
            arrayList = arrayList2;
        }
        return arrayList.isEmpty() ? AbstractC2129d.l : AbstractC2129d.a(arrayList.iterator(), arrayList.size());
    }

    public final String toString() {
        int i7;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        synchronized (this) {
            i7 = this.f20967n + this.f20968p;
        }
        return String.format("<ByteString.Output@%s size=%d>", hexString, Integer.valueOf(i7));
    }

    @Override // java.io.OutputStream
    public final synchronized void write(int i7) {
        try {
            if (this.f20968p == this.o.length) {
                b(1);
            }
            byte[] bArr = this.o;
            int i10 = this.f20968p;
            this.f20968p = i10 + 1;
            bArr[i10] = (byte) i7;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.OutputStream
    public final synchronized void write(byte[] bArr, int i7, int i10) {
        try {
            byte[] bArr2 = this.o;
            int length = bArr2.length;
            int i11 = this.f20968p;
            if (i10 <= length - i11) {
                System.arraycopy(bArr, i7, bArr2, i11, i10);
                this.f20968p += i10;
            } else {
                int length2 = bArr2.length - i11;
                System.arraycopy(bArr, i7, bArr2, i11, length2);
                int i12 = i10 - length2;
                b(i12);
                System.arraycopy(bArr, i7 + length2, this.o, 0, i12);
                this.f20968p = i12;
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
