package p8;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/* JADX INFO: renamed from: p8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2129d implements Iterable {
    public static final t l = new t(new byte[0]);

    public static AbstractC2129d a(Iterator it, int i7) {
        if (i7 == 1) {
            return (AbstractC2129d) it.next();
        }
        int i10 = i7 >>> 1;
        return a(it, i10).d(a(it, i7 - i10));
    }

    public static C2128c t() {
        return new C2128c();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2129d d(p8.AbstractC2129d r8) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p8.AbstractC2129d.d(p8.d):p8.d");
    }

    public final void l(int i7, int i10, int i11, byte[] bArr) {
        if (i7 < 0) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Source offset < 0: ");
            sb.append(i7);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i10 < 0) {
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Target offset < 0: ");
            sb2.append(i10);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        if (i11 < 0) {
            StringBuilder sb3 = new StringBuilder(23);
            sb3.append("Length < 0: ");
            sb3.append(i11);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
        int i12 = i7 + i11;
        if (i12 > size()) {
            StringBuilder sb4 = new StringBuilder(34);
            sb4.append("Source end offset < 0: ");
            sb4.append(i12);
            throw new IndexOutOfBoundsException(sb4.toString());
        }
        int i13 = i10 + i11;
        if (i13 <= bArr.length) {
            if (i11 > 0) {
                m(i7, i10, i11, bArr);
            }
        } else {
            StringBuilder sb5 = new StringBuilder(34);
            sb5.append("Target end offset < 0: ");
            sb5.append(i13);
            throw new IndexOutOfBoundsException(sb5.toString());
        }
    }

    public abstract void m(int i7, int i10, int i11, byte[] bArr);

    public abstract int q();

    public abstract boolean r();

    public abstract boolean s();

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    public abstract int u(int i7, int i10, int i11);

    public abstract int v(int i7, int i10, int i11);

    public abstract int w();

    public abstract String x();

    public final String y() {
        try {
            return x();
        } catch (UnsupportedEncodingException e10) {
            throw new RuntimeException("UTF-8 not supported?", e10);
        }
    }

    public abstract void z(OutputStream outputStream, int i7, int i10);
}
