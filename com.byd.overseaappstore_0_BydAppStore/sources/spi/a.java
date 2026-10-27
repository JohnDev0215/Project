package spi;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
@JvmName(name = "-Base64")
public final class a {

    @NotNull
    public static final byte[] a;

    @NotNull
    public static final byte[] b;

    static {
        ByteString.Companion companion = ByteString.INSTANCE;
        a = companion.d("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData();
        b = companion.d("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData();
    }

    @NotNull
    public static final String a(@NotNull byte[] encodeBase64, @NotNull byte[] map) {
        Intrinsics.checkNotNullParameter(encodeBase64, "$this$encodeBase64");
        Intrinsics.checkNotNullParameter(map, "map");
        byte[] toUtf8String = new byte[((encodeBase64.length + 2) / 3) * 4];
        int length = encodeBase64.length - (encodeBase64.length % 3);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            byte b2 = encodeBase64[i];
            int i4 = i3 + 1;
            byte b3 = encodeBase64[i3];
            int i5 = i4 + 1;
            byte b4 = encodeBase64[i4];
            int i6 = i2 + 1;
            toUtf8String[i2] = map[(b2 & 255) >> 2];
            int i7 = i6 + 1;
            toUtf8String[i6] = map[((b2 & 3) << 4) | ((b3 & 255) >> 4)];
            int i8 = i7 + 1;
            toUtf8String[i7] = map[((b3 & 15) << 2) | ((b4 & 255) >> 6)];
            i2 = i8 + 1;
            toUtf8String[i8] = map[b4 & 63];
            i = i5;
        }
        int length2 = encodeBase64.length - length;
        if (length2 == 1) {
            byte b5 = encodeBase64[i];
            int i9 = i2 + 1;
            toUtf8String[i2] = map[(b5 & 255) >> 2];
            int i10 = i9 + 1;
            toUtf8String[i9] = map[(b5 & 3) << 4];
            byte b6 = (byte) 61;
            toUtf8String[i10] = b6;
            toUtf8String[i10 + 1] = b6;
        } else if (length2 == 2) {
            int i11 = i + 1;
            byte b7 = encodeBase64[i];
            byte b8 = encodeBase64[i11];
            int i12 = i2 + 1;
            toUtf8String[i2] = map[(b7 & 255) >> 2];
            int i13 = i12 + 1;
            toUtf8String[i12] = map[((b7 & 3) << 4) | ((b8 & 255) >> 4)];
            toUtf8String[i13] = map[(b8 & 15) << 2];
            toUtf8String[i13 + 1] = (byte) 61;
        }
        Intrinsics.checkNotNullParameter(toUtf8String, "$this$toUtf8String");
        return new String(toUtf8String, Charsets.UTF_8);
    }
}
