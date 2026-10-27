package okhttp3.logging;

import java.io.EOFException;
import kotlin.jvm.internal.i;
import kotlin.o.f;
import okio.Buffer;

/* JADX INFO: compiled from: utf8.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Utf8Kt {
    public static final boolean isProbablyUtf8(Buffer buffer) {
        i.c(buffer, "$this$isProbablyUtf8");
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, f.d(buffer.size(), 64L));
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int utf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(utf8CodePoint) && !Character.isWhitespace(utf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
