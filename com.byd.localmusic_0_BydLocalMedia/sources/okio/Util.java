package okio;

import android.os.BatteryStats;
import android.util.proto.ProtoStream;
import com.bumptech.glide.load.Key;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
final class Util {
    public static final Charset UTF_8 = Charset.forName(Key.STRING_CHARSET_NAME);

    public static int reverseBytesInt(int i) {
        return ((i & 255) << 24) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    public static long reverseBytesLong(long j) {
        return ((j & 255) << 56) | ((BatteryStats.STEP_LEVEL_MODIFIED_MODE_MASK & j) >>> 56) | ((BatteryStats.STEP_LEVEL_INITIAL_MODE_MASK & j) >>> 40) | ((BatteryStats.STEP_LEVEL_LEVEL_MASK & j) >>> 24) | ((ProtoStream.FIELD_TYPE_MASK & j) >>> 8) | ((4278190080L & j) << 8) | ((16711680 & j) << 24) | ((65280 & j) << 40);
    }

    public static short reverseBytesShort(short s) {
        int i = s & 65535;
        return (short) (((i & 255) << 8) | ((65280 & i) >>> 8));
    }

    private Util() {
    }

    public static void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    public static void sneakyRethrow(Throwable th) throws Throwable {
        sneakyThrow2(th);
    }

    private static <T extends Throwable> void sneakyThrow2(Throwable th) throws Throwable {
        throw th;
    }

    public static boolean arrayRangeEquals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }
}
