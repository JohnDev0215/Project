package defpackage;

import com.google.protobuf.c0;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ByteString.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class lk implements Iterable<Byte>, Serializable {
    public static final f f = new f(c0.c);
    public static final g g;
    public int e = 0;

    /* JADX INFO: compiled from: ByteString.java */
    public class a extends b {
        public int e = 0;
        public final int f;

        public a() {
            this.f = lk.this.size();
        }

        @Override // lk.d
        public final byte a() {
            int i = this.e;
            if (i >= this.f) {
                throw new NoSuchElementException();
            }
            this.e = i + 1;
            return lk.this.p(i);
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.e < this.f;
        }
    }

    /* JADX INFO: compiled from: ByteString.java */
    public static abstract class b implements d {
        @Override // java.util.Iterator
        public final Byte next() {
            return Byte.valueOf(a());
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: ByteString.java */
    public static final class c extends f {
        private static final long serialVersionUID = 1;
        public final int i;
        public final int j;

        public c(byte[] bArr, int i, int i2) {
            super(bArr);
            lk.c(i, i + i2, bArr.length);
            this.i = i;
            this.j = i2;
        }

        private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
            throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
        }

        @Override // lk.f
        public final int D() {
            return this.i;
        }

        @Override // lk.f, defpackage.lk
        public final byte a(int i) {
            lk.b(i, this.j);
            return this.h[this.i + i];
        }

        @Override // lk.f, defpackage.lk
        public final void f(int i, int i2, int i3, byte[] bArr) {
            System.arraycopy(this.h, this.i + i, bArr, i2, i3);
        }

        @Override // lk.f, defpackage.lk
        public final byte p(int i) {
            return this.h[this.i + i];
        }

        @Override // lk.f, defpackage.lk
        public final int size() {
            return this.j;
        }

        public Object writeReplace() {
            return new f(x());
        }
    }

    /* JADX INFO: compiled from: ByteString.java */
    public interface d extends Iterator<Byte> {
        byte a();
    }

    /* JADX INFO: compiled from: ByteString.java */
    public static abstract class e extends lk {
        @Override // defpackage.lk, java.lang.Iterable
        public final Iterator<Byte> iterator() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: ByteString.java */
    public static class f extends e {
        private static final long serialVersionUID = 1;
        public final byte[] h;

        public f(byte[] bArr) {
            bArr.getClass();
            this.h = bArr;
        }

        @Override // defpackage.lk
        public final void A(vd1 vd1Var) {
            vd1Var.I(this.h, D(), size());
        }

        public final ByteBuffer B() {
            return ByteBuffer.wrap(this.h, D(), size()).asReadOnlyBuffer();
        }

        public final boolean C(lk lkVar, int i, int i2) {
            if (i2 > lkVar.size()) {
                throw new IllegalArgumentException("Length too large: " + i2 + size());
            }
            int i3 = i + i2;
            if (i3 > lkVar.size()) {
                throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + lkVar.size());
            }
            if (!(lkVar instanceof f)) {
                return lkVar.w(i, i3).equals(w(0, i2));
            }
            f fVar = (f) lkVar;
            int iD = D() + i2;
            int iD2 = D();
            int iD3 = fVar.D() + i;
            while (iD2 < iD) {
                if (this.h[iD2] != fVar.h[iD3]) {
                    return false;
                }
                iD2++;
                iD3++;
            }
            return true;
        }

        public int D() {
            return 0;
        }

        @Override // defpackage.lk
        public byte a(int i) {
            return this.h[i];
        }

        @Override // defpackage.lk
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof lk) || size() != ((lk) obj).size()) {
                return false;
            }
            if (size() == 0) {
                return true;
            }
            if (!(obj instanceof f)) {
                return obj.equals(this);
            }
            f fVar = (f) obj;
            int i = this.e;
            int i2 = fVar.e;
            if (i == 0 || i2 == 0 || i == i2) {
                return C(fVar, 0, size());
            }
            return false;
        }

        @Override // defpackage.lk
        public void f(int i, int i2, int i3, byte[] bArr) {
            System.arraycopy(this.h, i, bArr, i2, i3);
        }

        @Override // defpackage.lk
        public byte p(int i) {
            return this.h[i];
        }

        @Override // defpackage.lk
        public final boolean q() {
            int iD = D();
            return sk3.g(this.h, iD, size() + iD);
        }

        @Override // defpackage.lk
        public int size() {
            return this.h.length;
        }

        @Override // defpackage.lk
        public final com.google.protobuf.g t() {
            return com.google.protobuf.g.h(this.h, D(), size(), true);
        }

        @Override // defpackage.lk
        public final int u(int i, int i2, int i3) {
            int iD = D() + i2;
            Charset charset = c0.a;
            for (int i4 = iD; i4 < iD + i3; i4++) {
                i = (i * 31) + this.h[i4];
            }
            return i;
        }

        @Override // defpackage.lk
        public final lk w(int i, int i2) {
            int iC = lk.c(i, i2, size());
            if (iC == 0) {
                return lk.f;
            }
            return new c(this.h, D() + i, iC);
        }

        @Override // defpackage.lk
        public final String y(Charset charset) {
            return new String(this.h, D(), size(), charset);
        }
    }

    /* JADX INFO: compiled from: ByteString.java */
    public static final class g {
    }

    static {
        Class<?> cls = q4.a;
        g = new g();
    }

    public static void b(int i, int i2) {
        if (((i2 - (i + 1)) | i) < 0) {
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException(gq2.e("Index < 0: ", i));
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
        }
    }

    public static int c(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        }
        if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        }
        throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
    }

    public static f d(byte[] bArr, int i, int i2) {
        c(i, i + i2, bArr.length);
        g.getClass();
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new f(bArr2);
    }

    public abstract void A(vd1 vd1Var);

    public abstract byte a(int i);

    public abstract boolean equals(Object obj);

    public abstract void f(int i, int i2, int i3, byte[] bArr);

    public final int hashCode() {
        int iU = this.e;
        if (iU == 0) {
            int size = size();
            iU = u(size, 0, size);
            if (iU == 0) {
                iU = 1;
            }
            this.e = iU;
        }
        return iU;
    }

    public abstract byte p(int i);

    public abstract boolean q();

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public d iterator() {
        return new a();
    }

    public abstract int size();

    public abstract com.google.protobuf.g t();

    public final String toString() {
        String strA;
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(size());
        if (size() <= 50) {
            strA = d83.a(this);
        } else {
            strA = d83.a(w(0, 47)) + "...";
        }
        objArr[2] = strA;
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract int u(int i, int i2, int i3);

    public abstract lk w(int i, int i2);

    public final byte[] x() {
        int size = size();
        if (size == 0) {
            return c0.c;
        }
        byte[] bArr = new byte[size];
        f(0, 0, size, bArr);
        return bArr;
    }

    public abstract String y(Charset charset);

    public final String z() {
        return size() == 0 ? "" : y(c0.a);
    }
}
