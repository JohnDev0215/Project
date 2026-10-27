package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final char[] f5780b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ByteString f5781c = m(new byte[0]);
    private static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    transient int f5782d;
    final byte[] data;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    transient String f5783e;

    ByteString(byte[] bArr) {
        this.data = bArr;
    }

    static int b(String str, int i2) {
        int length = str.length();
        int iCharCount = 0;
        int i3 = 0;
        while (iCharCount < length) {
            if (i3 == i2) {
                return iCharCount;
            }
            int iCodePointAt = str.codePointAt(iCharCount);
            if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                return -1;
            }
            i3++;
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.length();
    }

    public static ByteString e(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) ((g(str.charAt(i3)) << 4) + g(str.charAt(i3 + 1)));
        }
        return m(bArr);
    }

    private static int g(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c2);
            }
        }
        return (c2 - c3) + 10;
    }

    private ByteString h(String str) {
        try {
            return m(MessageDigest.getInstance(str).digest(this.data));
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    public static ByteString i(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(str.getBytes(Util.f5857a));
        byteString.f5783e = str;
        return byteString;
    }

    public static ByteString m(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString p(InputStream inputStream, int i2) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i2);
        }
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i3, i2 - i3);
            if (i4 == -1) {
                throw new EOFException();
            }
            i3 += i4;
        }
        return new ByteString(bArr);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        ByteString byteStringP = p(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, byteStringP.data);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (NoSuchFieldException unused2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    public String a() {
        return Base64.a(this.data);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(ByteString byteString) {
        int iS = s();
        int iS2 = byteString.s();
        int iMin = Math.min(iS, iS2);
        for (int i2 = 0; i2 < iMin; i2++) {
            int iJ = j(i2) & 255;
            int iJ2 = byteString.j(i2) & 255;
            if (iJ != iJ2) {
                return iJ < iJ2 ? -1 : 1;
            }
        }
        if (iS == iS2) {
            return 0;
        }
        return iS < iS2 ? -1 : 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int iS = byteString.s();
            byte[] bArr = this.data;
            if (iS == bArr.length && byteString.o(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.f5782d;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = Arrays.hashCode(this.data);
        this.f5782d = iHashCode;
        return iHashCode;
    }

    public byte j(int i2) {
        return this.data[i2];
    }

    public String k() {
        byte[] bArr = this.data;
        char[] cArr = new char[bArr.length * 2];
        int i2 = 0;
        for (byte b2 : bArr) {
            int i3 = i2 + 1;
            char[] cArr2 = f5780b;
            cArr[i2] = cArr2[(b2 >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public boolean n(int i2, ByteString byteString, int i3, int i4) {
        return byteString.o(i3, this.data, i2, i4);
    }

    public boolean o(int i2, byte[] bArr, int i3, int i4) {
        if (i2 >= 0) {
            byte[] bArr2 = this.data;
            if (i2 <= bArr2.length - i4 && i3 >= 0 && i3 <= bArr.length - i4 && Util.a(bArr2, i2, bArr, i3, i4)) {
                return true;
            }
        }
        return false;
    }

    public ByteString q() {
        return h("SHA-1");
    }

    public ByteString r() {
        return h("SHA-256");
    }

    public int s() {
        return this.data.length;
    }

    public final boolean t(ByteString byteString) {
        return n(0, byteString, 0, byteString.s());
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String strX = x();
        int iB = b(strX, 64);
        if (iB == -1) {
            if (this.data.length <= 64) {
                return "[hex=" + k() + "]";
            }
            return "[size=" + this.data.length + " hex=" + u(0, 64).k() + "…]";
        }
        String strReplace = strX.substring(0, iB).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        if (iB >= strX.length()) {
            return "[text=" + strReplace + "]";
        }
        return "[size=" + this.data.length + " text=" + strReplace + "…]";
    }

    public ByteString u(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.data;
        if (i3 > bArr.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        }
        int i4 = i3 - i2;
        if (i4 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i2 == 0 && i3 == bArr.length) {
            return this;
        }
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i2, bArr2, 0, i4);
        return new ByteString(bArr2);
    }

    public ByteString v() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i2 >= bArr.length) {
                return this;
            }
            byte b2 = bArr[i2];
            if (b2 >= 65 && b2 <= 90) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i2] = (byte) (b2 + 32);
                for (int i3 = i2 + 1; i3 < bArr2.length; i3++) {
                    byte b3 = bArr2[i3];
                    if (b3 >= 65 && b3 <= 90) {
                        bArr2[i3] = (byte) (b3 + 32);
                    }
                }
                return new ByteString(bArr2);
            }
            i2++;
        }
    }

    public byte[] w() {
        return (byte[]) this.data.clone();
    }

    public String x() {
        String str = this.f5783e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, Util.f5857a);
        this.f5783e = str2;
        return str2;
    }

    void y(Buffer buffer) {
        byte[] bArr = this.data;
        buffer.g(bArr, 0, bArr.length);
    }
}
