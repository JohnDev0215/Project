package com.google.protobuf;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import p.au;
import p.hf2;
import p.p02;
import p.r6;
import p.u4;
import p.w1;
import p.w51;
import p.yo;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public abstract class ByteString implements Iterable<Byte>, Serializable {
    public static final ByteString c = new LiteralByteString(w51.b);
    public static final w1 e;
    private int hash = 0;

    public static final class BoundedByteString extends LiteralByteString {
        private static final long serialVersionUID = 1;
        private final int bytesLength;
        private final int bytesOffset;

        public BoundedByteString(byte[] bArr, int i, int i2) {
            super(bArr);
            ByteString.b(i, i + i2, bArr.length);
            this.bytesOffset = i;
            this.bytesLength = i2;
        }

        private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
            throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public final byte a(int i) {
            int i2 = this.bytesLength;
            if (((i2 - (i + 1)) | i) >= 0) {
                return this.bytes[this.bytesOffset + i];
            }
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException(u4.e("Index < 0: ", i));
            }
            throw new ArrayIndexOutOfBoundsException(hf2.c("Index > length: ", i, ", ", i2));
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public final void e(int i, byte[] bArr) {
            System.arraycopy(this.bytes, this.bytesOffset, bArr, 0, i);
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public final byte h(int i) {
            return this.bytes[this.bytesOffset + i];
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public final int size() {
            return this.bytesLength;
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString
        public final int u() {
            return this.bytesOffset;
        }

        public Object writeReplace() {
            return new LiteralByteString(q());
        }
    }

    public static abstract class LeafByteString extends ByteString {
        @Override // java.lang.Iterable
        public final Iterator<Byte> iterator() {
            return new yo(this);
        }
    }

    public static class LiteralByteString extends LeafByteString {
        private static final long serialVersionUID = 1;
        protected final byte[] bytes;

        public LiteralByteString(byte[] bArr) {
            bArr.getClass();
            this.bytes = bArr;
        }

        @Override // com.google.protobuf.ByteString
        public byte a(int i) {
            return this.bytes[i];
        }

        @Override // com.google.protobuf.ByteString
        public void e(int i, byte[] bArr) {
            System.arraycopy(this.bytes, 0, bArr, 0, i);
        }

        @Override // com.google.protobuf.ByteString
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ByteString) || size() != ((ByteString) obj).size()) {
                return false;
            }
            if (size() == 0) {
                return true;
            }
            if (!(obj instanceof LiteralByteString)) {
                return obj.equals(this);
            }
            LiteralByteString literalByteString = (LiteralByteString) obj;
            int iO = o();
            int iO2 = literalByteString.o();
            if (iO != 0 && iO2 != 0 && iO != iO2) {
                return false;
            }
            int size = size();
            if (size > literalByteString.size()) {
                throw new IllegalArgumentException("Length too large: " + size + size());
            }
            if (size > literalByteString.size()) {
                StringBuilder sbO = u4.o("Ran off end of other: 0, ", size, ", ");
                sbO.append(literalByteString.size());
                throw new IllegalArgumentException(sbO.toString());
            }
            byte[] bArr = this.bytes;
            byte[] bArr2 = literalByteString.bytes;
            int iU = u() + size;
            int iU2 = u();
            int iU3 = literalByteString.u();
            while (iU2 < iU) {
                if (bArr[iU2] != bArr2[iU3]) {
                    return false;
                }
                iU2++;
                iU3++;
            }
            return true;
        }

        @Override // com.google.protobuf.ByteString
        public byte h(int i) {
            return this.bytes[i];
        }

        @Override // com.google.protobuf.ByteString
        public final boolean i() {
            int iU = u();
            return r.a.x(iU, size() + iU, this.bytes) == 0;
        }

        @Override // com.google.protobuf.ByteString
        public final au m() {
            return au.f(this.bytes, u(), size(), true);
        }

        @Override // com.google.protobuf.ByteString
        public final int n(int i, int i2) {
            byte[] bArr = this.bytes;
            int iU = u();
            Charset charset = w51.a;
            for (int i3 = iU; i3 < iU + i2; i3++) {
                i = (i * 31) + bArr[i3];
            }
            return i;
        }

        @Override // com.google.protobuf.ByteString
        public final ByteString p(int i) {
            int iB = ByteString.b(0, i, size());
            return iB == 0 ? ByteString.c : new BoundedByteString(this.bytes, u(), iB);
        }

        @Override // com.google.protobuf.ByteString
        public final String r(Charset charset) {
            return new String(this.bytes, u(), size(), charset);
        }

        @Override // com.google.protobuf.ByteString
        public int size() {
            return this.bytes.length;
        }

        @Override // com.google.protobuf.ByteString
        public final void t(p02 p02Var) {
            p02Var.t(this.bytes, u(), size());
        }

        public int u() {
            return 0;
        }
    }

    static {
        int i = 0;
        e = r6.a() ? new w1(1, i) : new w1(i, i);
    }

    public static int b(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException(u4.f("Beginning index: ", i, " < 0"));
        }
        if (i2 < i) {
            throw new IndexOutOfBoundsException(hf2.c("Beginning index larger than ending index: ", i, ", ", i2));
        }
        throw new IndexOutOfBoundsException(hf2.c("End index: ", i2, " >= ", i3));
    }

    public static ByteString c(byte[] bArr, int i, int i2) {
        byte[] bArrCopyOfRange;
        int i3 = i + i2;
        b(i, i3, bArr.length);
        switch (e.c) {
            case 0:
                bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i3);
                break;
            default:
                bArrCopyOfRange = new byte[i2];
                System.arraycopy(bArr, i, bArrCopyOfRange, 0, i2);
                break;
        }
        return new LiteralByteString(bArrCopyOfRange);
    }

    public static ByteString d(String str) {
        return new LiteralByteString(str.getBytes(w51.a));
    }

    public abstract byte a(int i);

    public abstract void e(int i, byte[] bArr);

    public abstract boolean equals(Object obj);

    public abstract byte h(int i);

    public final int hashCode() {
        int iN = this.hash;
        if (iN == 0) {
            int size = size();
            iN = n(size, size);
            if (iN == 0) {
                iN = 1;
            }
            this.hash = iN;
        }
        return iN;
    }

    public abstract boolean i();

    public abstract au m();

    public abstract int n(int i, int i2);

    public final int o() {
        return this.hash;
    }

    public abstract ByteString p(int i);

    public final byte[] q() {
        int size = size();
        if (size == 0) {
            return w51.b;
        }
        byte[] bArr = new byte[size];
        e(size, bArr);
        return bArr;
    }

    public abstract String r(Charset charset);

    public final String s() {
        return size() == 0 ? "" : r(w51.a);
    }

    public abstract int size();

    public abstract void t(p02 p02Var);

    public final String toString() {
        String strB0;
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(size());
        if (size() <= 50) {
            strB0 = zu.b0(this);
        } else {
            strB0 = zu.b0(p(47)) + "...";
        }
        objArr[2] = strB0;
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }
}
