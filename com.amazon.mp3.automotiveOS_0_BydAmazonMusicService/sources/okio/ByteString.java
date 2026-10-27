package okio;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.internal._ByteStringKt;

/* JADX INFO: compiled from: ByteString.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0015\b\u0016\u0018\u0000 B2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001BB\u0011\b\u0000\u0012\u0006\u00103\u001a\u00020\u001b¢\u0006\u0004\b@\u0010AJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\u0006\u0010\u0006\u001a\u00020\u0000J\u0006\u0010\u0007\u001a\u00020\u0000J\u0006\u0010\b\u001a\u00020\u0000J\u0017\u0010\f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0003H\u0010¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u0017\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b\u0016\u0010\u0013J\u000f\u0010\u001a\u001a\u00020\u000fH\u0010¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\u000f\u0010\u001f\u001a\u00020\u001bH\u0010¢\u0006\u0004\b\u001d\u0010\u001eJ'\u0010'\u001a\u00020$2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0010¢\u0006\u0004\b%\u0010&J(\u0010+\u001a\u00020*2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0016J(\u0010+\u001a\u00020*2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0016J\u000e\u0010-\u001a\u00020*2\u0006\u0010,\u001a\u00020\u0000J\u0013\u0010/\u001a\u00020*2\b\u0010(\u001a\u0004\u0018\u00010.H\u0096\u0002J\b\u00100\u001a\u00020\u000fH\u0016J\u0011\u00101\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u0000H\u0096\u0002J\b\u00102\u001a\u00020\u0003H\u0016R\u001a\u00103\u001a\u00020\u001b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u0010\u001eR\"\u00100\u001a\u00020\u000f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b0\u00106\u001a\u0004\b7\u0010\u0019\"\u0004\b8\u00109R$\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0011\u0010?\u001a\u00020\u000f8G¢\u0006\u0006\u001a\u0004\b?\u0010\u0019¨\u0006C"}, d2 = {"Lokio/ByteString;", "Ljava/io/Serializable;", "", "", "utf8", "base64", "md5", "sha1", "sha256", "algorithm", "digest$okio", "(Ljava/lang/String;)Lokio/ByteString;", "digest", "hex", "toAsciiLowercase", "", "pos", "", "internalGet$okio", "(I)B", "internalGet", "index", "getByte", "get", "getSize$okio", "()I", "getSize", "", "toByteArray", "internalArray$okio", "()[B", "internalArray", "Lokio/Buffer;", "buffer", "offset", "byteCount", "", "write$okio", "(Lokio/Buffer;II)V", "write", "other", "otherOffset", "", "rangeEquals", "prefix", "startsWith", "", "equals", "hashCode", "compareTo", "toString", "data", "[B", "getData$okio", "I", "getHashCode$okio", "setHashCode$okio", "(I)V", "Ljava/lang/String;", "getUtf8$okio", "()Ljava/lang/String;", "setUtf8$okio", "(Ljava/lang/String;)V", "size", "<init>", "([B)V", "Companion", "okio"}, k = 1, mv = {1, 5, 1})
public class ByteString implements Serializable, Comparable<ByteString> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmField
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private final byte[] data;
    private transient int hashCode;
    private transient String utf8;

    @JvmStatic
    public static final ByteString decodeBase64(String str) {
        return INSTANCE.decodeBase64(str);
    }

    @JvmStatic
    public static final ByteString encodeUtf8(String str) {
        return INSTANCE.encodeUtf8(str);
    }

    @JvmStatic
    public static final ByteString of(byte... bArr) {
        return INSTANCE.of(bArr);
    }

    public String utf8() {
        String utf8 = getUtf8();
        if (utf8 != null) {
            return utf8;
        }
        String utf8String = _JvmPlatformKt.toUtf8String(internalArray$okio());
        setUtf8$okio(utf8String);
        return utf8String;
    }

    public String base64() {
        return _Base64Kt.encodeBase64$default(getData(), null, 1, null);
    }

    public ByteString(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    /* JADX INFO: renamed from: getData$okio, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }

    public String hex() {
        char[] cArr = new char[getData().length * 2];
        byte[] data = getData();
        int length = data.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = data[i];
            i++;
            int i3 = i2 + 1;
            cArr[i2] = _ByteStringKt.getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = _ByteStringKt.getHEX_DIGIT_CHARS()[b & 15];
        }
        return StringsKt__StringsJVMKt.concatToString(cArr);
    }

    /* JADX INFO: renamed from: getHashCode$okio, reason: from getter */
    public final int getHashCode() {
        return this.hashCode;
    }

    public final void setHashCode$okio(int i) {
        this.hashCode = i;
    }

    /* JADX INFO: renamed from: getUtf8$okio, reason: from getter */
    public final String getUtf8() {
        return this.utf8;
    }

    public final void setUtf8$okio(String str) {
        this.utf8 = str;
    }

    public final ByteString md5() {
        return digest$okio("MD5");
    }

    public final ByteString sha1() {
        return digest$okio("SHA-1");
    }

    public ByteString toAsciiLowercase() {
        byte b;
        for (int i = 0; i < getData().length; i++) {
            byte b2 = getData()[i];
            byte b3 = (byte) 65;
            if (b2 >= b3 && b2 <= (b = (byte) 90)) {
                byte[] data = getData();
                byte[] bArrCopyOf = Arrays.copyOf(data, data.length);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, size)");
                bArrCopyOf[i] = (byte) (b2 + 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b4 = bArrCopyOf[i2];
                    if (b4 >= b3 && b4 <= b) {
                        bArrCopyOf[i2] = (byte) (b4 + 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }

    public final ByteString sha256() {
        return digest$okio("SHA-256");
    }

    public ByteString digest$okio(String algorithm) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(getData(), 0, size());
        byte[] digestBytes = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(digestBytes, "digestBytes");
        return new ByteString(digestBytes);
    }

    @JvmName(name = "getByte")
    public final byte getByte(int index) {
        return internalGet$okio(index);
    }

    @JvmName(name = "size")
    public final int size() {
        return getSize$okio();
    }

    public byte internalGet$okio(int pos) {
        return getData()[pos];
    }

    public void write$okio(Buffer buffer, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        _ByteStringKt.commonWrite(this, buffer, offset, byteCount);
    }

    public int getSize$okio() {
        return getData().length;
    }

    public byte[] toByteArray() {
        byte[] data = getData();
        byte[] bArrCopyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return bArrCopyOf;
    }

    public byte[] internalArray$okio() {
        return getData();
    }

    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(other, "other");
        return other.rangeEquals(otherOffset, getData(), offset, byteCount);
    }

    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(other, "other");
        return offset >= 0 && offset <= getData().length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && _UtilKt.arrayRangeEquals(getData(), offset, other, otherOffset, byteCount);
    }

    public final boolean startsWith(ByteString prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return rangeEquals(0, prefix, 0, prefix.size());
    }

    /* JADX INFO: compiled from: ByteString.kt */
    @Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0014\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u00020\u0002\"\u00020\u0003H\u0007J'\u0010\u000b\u001a\u00020\u0005*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0006\u0010\nJ\f\u0010\r\u001a\u00020\u0005*\u00020\fH\u0007J\u001d\u0010\u0012\u001a\u00020\u0005*\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0013\u001a\u0004\u0018\u00010\u0005*\u00020\fH\u0007J\f\u0010\u0014\u001a\u00020\u0005*\u00020\fH\u0007R\u0014\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lokio/ByteString$Companion;", "", "", "", "data", "Lokio/ByteString;", "of", "", "offset", "byteCount", "([BII)Lokio/ByteString;", "toByteString", "", "encodeUtf8", "Ljava/nio/charset/Charset;", "charset", "encodeString", "(Ljava/lang/String;Ljava/nio/charset/Charset;)Lokio/ByteString;", "encode", "decodeBase64", "decodeHex", "EMPTY", "Lokio/ByteString;", "", "serialVersionUID", "J", "<init>", "()V", "okio"}, k = 1, mv = {1, 5, 1})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ ByteString of$default(Companion companion, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = bArr.length;
            }
            return companion.of(bArr, i, i2);
        }

        private Companion() {
        }

        @JvmStatic
        @JvmName(name = "encodeString")
        public final ByteString encodeString(String str, Charset charset) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            Intrinsics.checkNotNullParameter(charset, "charset");
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return new ByteString(bytes);
        }

        @JvmStatic
        public final ByteString of(byte... data) {
            Intrinsics.checkNotNullParameter(data, "data");
            byte[] bArrCopyOf = Arrays.copyOf(data, data.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, size)");
            return new ByteString(bArrCopyOf);
        }

        @JvmStatic
        @JvmName(name = "of")
        public final ByteString of(byte[] bArr, int i, int i2) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            _UtilKt.checkOffsetAndCount(bArr.length, i, i2);
            return new ByteString(ArraysKt___ArraysJvmKt.copyOfRange(bArr, i, i2 + i));
        }

        @JvmStatic
        public final ByteString encodeUtf8(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            ByteString byteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
            byteString.setUtf8$okio(str);
            return byteString;
        }

        @JvmStatic
        public final ByteString decodeBase64(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            byte[] bArrDecodeBase64ToArray = _Base64Kt.decodeBase64ToArray(str);
            if (bArrDecodeBase64ToArray != null) {
                return new ByteString(bArrDecodeBase64ToArray);
            }
            return null;
        }

        @JvmStatic
        public final ByteString decodeHex(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            int i = 0;
            if (!(str.length() % 2 == 0)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected hex string: ", str).toString());
            }
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            int i2 = length - 1;
            if (i2 >= 0) {
                while (true) {
                    int i3 = i + 1;
                    int i4 = i * 2;
                    bArr[i] = (byte) ((_ByteStringKt.decodeHexDigit(str.charAt(i4)) << 4) + _ByteStringKt.decodeHexDigit(str.charAt(i4 + 1)));
                    if (i3 > i2) {
                        break;
                    }
                    i = i3;
                }
            }
            return new ByteString(bArr);
        }
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ByteString) {
            ByteString byteString = (ByteString) other;
            if (byteString.size() == getData().length && byteString.rangeEquals(0, getData(), 0, getData().length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = getHashCode();
        if (hashCode != 0) {
            return hashCode;
        }
        int iHashCode = Arrays.hashCode(getData());
        setHashCode$okio(iHashCode);
        return iHashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r0 < r1) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0028, code lost:
    
        if (r7 < r8) goto L13;
     */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int compareTo(okio.ByteString r10) {
        /*
            r9 = this;
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            int r0 = r9.size()
            int r1 = r10.size()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L13:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L2b
            byte r7 = r9.getByte(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.getByte(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L28
            int r4 = r4 + 1
            goto L13
        L28:
            if (r7 >= r8) goto L32
            goto L30
        L2b:
            if (r0 != r1) goto L2e
            goto L33
        L2e:
            if (r0 >= r1) goto L32
        L30:
            r3 = r5
            goto L33
        L32:
            r3 = r6
        L33:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.compareTo(okio.ByteString):int");
    }

    public String toString() {
        String str;
        if (getData().length == 0) {
            str = "[size=0]";
        } else {
            int iCodePointIndexToCharIndex = _ByteStringKt.codePointIndexToCharIndex(getData(), 64);
            if (iCodePointIndexToCharIndex == -1) {
                if (getData().length <= 64) {
                    str = "[hex=" + hex() + ']';
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[size=");
                    sb.append(getData().length);
                    sb.append(" hex=");
                    int iResolveDefaultParameter = _UtilKt.resolveDefaultParameter(this, 64);
                    if (iResolveDefaultParameter <= getData().length) {
                        if (!(iResolveDefaultParameter + 0 >= 0)) {
                            throw new IllegalArgumentException("endIndex < beginIndex".toString());
                        }
                        sb.append((iResolveDefaultParameter == getData().length ? this : new ByteString(ArraysKt___ArraysJvmKt.copyOfRange(getData(), 0, iResolveDefaultParameter))).hex());
                        sb.append("…]");
                        return sb.toString();
                    }
                    throw new IllegalArgumentException(("endIndex > length(" + getData().length + ')').toString());
                }
            } else {
                String strUtf8 = utf8();
                if (strUtf8 != null) {
                    String strSubstring = strUtf8.substring(0, iCodePointIndexToCharIndex);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    String strReplace$default = StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(strSubstring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
                    if (iCodePointIndexToCharIndex < strUtf8.length()) {
                        return "[size=" + getData().length + " text=" + strReplace$default + "…]";
                    }
                    return "[text=" + strReplace$default + ']';
                }
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
        }
        return str;
    }
}
