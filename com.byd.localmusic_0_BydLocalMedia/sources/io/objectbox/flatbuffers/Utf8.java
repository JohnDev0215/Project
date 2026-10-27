package io.objectbox.flatbuffers;

import com.android.internal.midi.MidiConstants;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Utf8 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Utf8 DEFAULT;

    public abstract String decodeUtf8(ByteBuffer byteBuffer, int i, int i2);

    public abstract void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer);

    public abstract int encodedLength(CharSequence charSequence);

    public static Utf8 getDefault() {
        if (DEFAULT == null) {
            DEFAULT = new Utf8Safe();
        }
        return DEFAULT;
    }

    public static void setDefault(Utf8 utf8) {
        DEFAULT = utf8;
    }

    public static int encodeUtf8CodePoint(CharSequence charSequence, int i, byte[] bArr) {
        int length = charSequence.length();
        if (i >= length) {
            return 0;
        }
        char cCharAt = charSequence.charAt(i);
        if (cCharAt < 128) {
            bArr[0] = (byte) cCharAt;
            return 1;
        }
        if (cCharAt < 2048) {
            bArr[0] = (byte) ((cCharAt >>> 6) | 192);
            bArr[1] = (byte) ((cCharAt & '?') | 128);
            return 2;
        }
        if (cCharAt < 55296 || 57343 < cCharAt) {
            bArr[0] = (byte) ((cCharAt >>> '\f') | 224);
            bArr[1] = (byte) (((cCharAt >>> 6) & 63) | 128);
            bArr[2] = (byte) ((cCharAt & '?') | 128);
            return 3;
        }
        int i2 = i + 1;
        if (i2 != length) {
            char cCharAt2 = charSequence.charAt(i2);
            if (Character.isSurrogatePair(cCharAt, cCharAt2)) {
                int codePoint = Character.toCodePoint(cCharAt, cCharAt2);
                bArr[0] = (byte) ((codePoint >>> 18) | 240);
                bArr[1] = (byte) (((codePoint >>> 12) & 63) | 128);
                bArr[2] = (byte) (((codePoint >>> 6) & 63) | 128);
                bArr[3] = (byte) ((codePoint & 63) | 128);
                return 4;
            }
        }
        throw new UnpairedSurrogateException(i, length);
    }

    static class DecodeUtil {
        private static char highSurrogate(int i) {
            return (char) ((i >>> 10) + 55232);
        }

        private static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        static boolean isOneByte(byte b) {
            return b >= 0;
        }

        static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        static boolean isTwoBytes(byte b) {
            return b < -32;
        }

        private static char lowSurrogate(int i) {
            return (char) ((i & 1023) + 56320);
        }

        private static int trailingByteValue(byte b) {
            return b & 63;
        }

        DecodeUtil() {
        }

        static void handleOneByte(byte b, char[] cArr, int i) {
            cArr[i] = (char) b;
        }

        static void handleTwoBytes(byte b, byte b2, char[] cArr, int i) throws IllegalArgumentException {
            if (b < -62) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
            }
            if (isNotTrailingByte(b2)) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
            }
            cArr[i] = (char) (((b & 31) << 6) | trailingByteValue(b2));
        }

        static void handleThreeBytes(byte b, byte b2, byte b3, char[] cArr, int i) throws IllegalArgumentException {
            if (isNotTrailingByte(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || isNotTrailingByte(b3)))) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            cArr[i] = (char) (((b & MidiConstants.STATUS_CHANNEL_MASK) << 12) | (trailingByteValue(b2) << 6) | trailingByteValue(b3));
        }

        static void handleFourBytes(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws IllegalArgumentException {
            if (isNotTrailingByte(b2) || (((b << 28) + (b2 + 112)) >> 30) != 0 || isNotTrailingByte(b3) || isNotTrailingByte(b4)) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            int iTrailingByteValue = ((b & 7) << 18) | (trailingByteValue(b2) << 12) | (trailingByteValue(b3) << 6) | trailingByteValue(b4);
            cArr[i] = highSurrogate(iTrailingByteValue);
            cArr[i + 1] = lowSurrogate(iTrailingByteValue);
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }
}
