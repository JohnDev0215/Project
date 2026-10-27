package okio;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import i7.a;
import i7.b;
import i7.l;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    private static final long serialVersionUID = 1;
    public final byte[] data;
    public transient int hashCode;
    public transient String utf8;
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final ByteString EMPTY = of(new byte[0]);

    public ByteString(byte[] bArr) {
        this.data = bArr;
    }

    public static int a(char c8) {
        if (c8 >= '0' && c8 <= '9') {
            return c8 - '0';
        }
        char c9 = 'a';
        if (c8 < 'a' || c8 > 'f') {
            c9 = 'A';
            if (c8 < 'A' || c8 > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c8);
            }
        }
        return (c8 - c9) + 10;
    }

    public static int codePointIndexToCharIndex(String str, int i8) {
        int length = str.length();
        int iCharCount = 0;
        int i9 = 0;
        while (iCharCount < length) {
            if (i9 == i8) {
                return iCharCount;
            }
            int iCodePointAt = str.codePointAt(iCharCount);
            if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                return -1;
            }
            i9++;
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.length();
    }

    @Nullable
    public static ByteString decodeBase64(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] bArrA = a.a(str);
        if (bArrA != null) {
            return new ByteString(bArrA);
        }
        return null;
    }

    public static ByteString decodeHex(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i8 = 0; i8 < length; i8++) {
            int i9 = i8 * 2;
            bArr[i8] = (byte) ((a(str.charAt(i9)) << 4) + a(str.charAt(i9 + 1)));
        }
        return of(bArr);
    }

    public static ByteString encodeString(String str, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        if (charset != null) {
            return new ByteString(str.getBytes(charset));
        }
        throw new IllegalArgumentException("charset == null");
    }

    public static ByteString encodeUtf8(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(str.getBytes(l.f5534a));
        byteString.utf8 = str;
        return byteString;
    }

    public static ByteString of(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString read(InputStream inputStream, int i8) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (i8 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i8);
        }
        byte[] bArr = new byte[i8];
        int i9 = 0;
        while (i9 < i8) {
            int i10 = inputStream.read(bArr, i9, i8 - i9);
            if (i10 == -1) {
                throw new EOFException();
            }
            i9 += i10;
        }
        return new ByteString(bArr);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        ByteString byteString = read(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, byteString.data);
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

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(this.data).asReadOnlyBuffer();
    }

    public final ByteString b(String str) {
        try {
            return of(MessageDigest.getInstance(str).digest(this.data));
        } catch (NoSuchAlgorithmException e8) {
            throw new AssertionError(e8);
        }
    }

    public String base64() {
        return a.b(this.data);
    }

    public String base64Url() {
        return a.d(this.data);
    }

    public final ByteString c(String str, ByteString byteString) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            return of(mac.doFinal(this.data));
        } catch (InvalidKeyException e8) {
            throw new IllegalArgumentException(e8);
        } catch (NoSuchAlgorithmException e9) {
            throw new AssertionError(e9);
        }
    }

    public final boolean endsWith(ByteString byteString) {
        return rangeEquals(size() - byteString.size(), byteString, 0, byteString.size());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int size = byteString.size();
            byte[] bArr = this.data;
            if (size == bArr.length && byteString.rangeEquals(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public byte getByte(int i8) {
        return this.data[i8];
    }

    public int hashCode() {
        int i8 = this.hashCode;
        if (i8 != 0) {
            return i8;
        }
        int iHashCode = Arrays.hashCode(this.data);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    public String hex() {
        byte[] bArr = this.data;
        char[] cArr = new char[bArr.length * 2];
        int i8 = 0;
        for (byte b8 : bArr) {
            int i9 = i8 + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i8] = cArr2[(b8 >> 4) & 15];
            i8 = i9 + 1;
            cArr[i9] = cArr2[b8 & 15];
        }
        return new String(cArr);
    }

    public ByteString hmacSha1(ByteString byteString) {
        return c("HmacSHA1", byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return c("HmacSHA256", byteString);
    }

    public ByteString hmacSha512(ByteString byteString) {
        return c("HmacSHA512", byteString);
    }

    public final int indexOf(ByteString byteString) {
        return indexOf(byteString.internalArray(), 0);
    }

    public byte[] internalArray() {
        return this.data;
    }

    public final int lastIndexOf(ByteString byteString) {
        return lastIndexOf(byteString.internalArray(), size());
    }

    public ByteString md5() {
        return b("MD5");
    }

    public boolean rangeEquals(int i8, ByteString byteString, int i9, int i10) {
        return byteString.rangeEquals(i9, this.data, i8, i10);
    }

    public ByteString sha1() {
        return b("SHA-1");
    }

    public ByteString sha256() {
        return b("SHA-256");
    }

    public ByteString sha512() {
        return b("SHA-512");
    }

    public int size() {
        return this.data.length;
    }

    public final boolean startsWith(ByteString byteString) {
        return rangeEquals(0, byteString, 0, byteString.size());
    }

    public String string(Charset charset) {
        if (charset != null) {
            return new String(this.data, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    public ByteString substring(int i8) {
        return substring(i8, this.data.length);
    }

    public ByteString toAsciiLowercase() {
        int i8 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i8 >= bArr.length) {
                return this;
            }
            byte b8 = bArr[i8];
            if (b8 >= 65 && b8 <= 90) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i8] = (byte) (b8 + 32);
                for (int i9 = i8 + 1; i9 < bArr2.length; i9++) {
                    byte b9 = bArr2[i9];
                    if (b9 >= 65 && b9 <= 90) {
                        bArr2[i9] = (byte) (b9 + 32);
                    }
                }
                return new ByteString(bArr2);
            }
            i8++;
        }
    }

    public ByteString toAsciiUppercase() {
        int i8 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i8 >= bArr.length) {
                return this;
            }
            byte b8 = bArr[i8];
            if (b8 >= 97 && b8 <= 122) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i8] = (byte) (b8 - 32);
                for (int i9 = i8 + 1; i9 < bArr2.length; i9++) {
                    byte b9 = bArr2[i9];
                    if (b9 >= 97 && b9 <= 122) {
                        bArr2[i9] = (byte) (b9 - 32);
                    }
                }
                return new ByteString(bArr2);
            }
            i8++;
        }
    }

    public byte[] toByteArray() {
        return (byte[]) this.data.clone();
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String strUtf8 = utf8();
        int iCodePointIndexToCharIndex = codePointIndexToCharIndex(strUtf8, 64);
        if (iCodePointIndexToCharIndex == -1) {
            if (this.data.length <= 64) {
                return "[hex=" + hex() + "]";
            }
            return "[size=" + this.data.length + " hex=" + substring(0, 64).hex() + "…]";
        }
        String strReplace = strUtf8.substring(0, iCodePointIndexToCharIndex).replace("\\", "\\\\").replace(BydAdbHelper.COMMAND_LINE_END, "\\n").replace("\r", "\\r");
        if (iCodePointIndexToCharIndex >= strUtf8.length()) {
            return "[text=" + strReplace + "]";
        }
        return "[size=" + this.data.length + " text=" + strReplace + "…]";
    }

    public String utf8() {
        String str = this.utf8;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, l.f5534a);
        this.utf8 = str2;
        return str2;
    }

    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.data);
    }

    @Override // java.lang.Comparable
    public int compareTo(ByteString byteString) {
        int size = size();
        int size2 = byteString.size();
        int iMin = Math.min(size, size2);
        for (int i8 = 0; i8 < iMin; i8++) {
            int i9 = getByte(i8) & 255;
            int i10 = byteString.getByte(i8) & 255;
            if (i9 != i10) {
                return i9 < i10 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public final boolean endsWith(byte[] bArr) {
        return rangeEquals(size() - bArr.length, bArr, 0, bArr.length);
    }

    public final int indexOf(ByteString byteString, int i8) {
        return indexOf(byteString.internalArray(), i8);
    }

    public final int lastIndexOf(ByteString byteString, int i8) {
        return lastIndexOf(byteString.internalArray(), i8);
    }

    public boolean rangeEquals(int i8, byte[] bArr, int i9, int i10) {
        if (i8 >= 0) {
            byte[] bArr2 = this.data;
            if (i8 <= bArr2.length - i10 && i9 >= 0 && i9 <= bArr.length - i10 && l.a(bArr2, i8, bArr, i9, i10)) {
                return true;
            }
        }
        return false;
    }

    public final boolean startsWith(byte[] bArr) {
        return rangeEquals(0, bArr, 0, bArr.length);
    }

    public ByteString substring(int i8, int i9) {
        if (i8 < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.data;
        if (i9 > bArr.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        }
        int i10 = i9 - i8;
        if (i10 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i8 == 0 && i9 == bArr.length) {
            return this;
        }
        byte[] bArr2 = new byte[i10];
        System.arraycopy(bArr, i8, bArr2, 0, i10);
        return new ByteString(bArr2);
    }

    public static ByteString of(byte[] bArr, int i8, int i9) {
        if (bArr != null) {
            l.b(bArr.length, i8, i9);
            byte[] bArr2 = new byte[i9];
            System.arraycopy(bArr, i8, bArr2, 0, i9);
            return new ByteString(bArr2);
        }
        throw new IllegalArgumentException("data == null");
    }

    public final int indexOf(byte[] bArr) {
        return indexOf(bArr, 0);
    }

    public final int lastIndexOf(byte[] bArr) {
        return lastIndexOf(bArr, size());
    }

    public void write(b bVar) {
        byte[] bArr = this.data;
        bVar.b(bArr, 0, bArr.length);
    }

    public int indexOf(byte[] bArr, int i8) {
        int length = this.data.length - bArr.length;
        for (int iMax = Math.max(i8, 0); iMax <= length; iMax++) {
            if (l.a(this.data, iMax, bArr, 0, bArr.length)) {
                return iMax;
            }
        }
        return -1;
    }

    public int lastIndexOf(byte[] bArr, int i8) {
        for (int iMin = Math.min(i8, this.data.length - bArr.length); iMin >= 0; iMin--) {
            if (l.a(this.data, iMin, bArr, 0, bArr.length)) {
                return iMin;
            }
        }
        return -1;
    }

    public static ByteString of(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return new ByteString(bArr);
        }
        throw new IllegalArgumentException("data == null");
    }
}
