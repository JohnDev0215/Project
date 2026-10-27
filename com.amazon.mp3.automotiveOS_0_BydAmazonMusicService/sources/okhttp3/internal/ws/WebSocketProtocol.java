package okhttp3.internal.ws;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;

/* JADX INFO: compiled from: WebSocketProtocol.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/ws/WebSocketProtocol;", "", "Lokio/Buffer$UnsafeCursor;", "cursor", "", "key", "", "toggleMask", "", "code", "", "closeCodeExceptionMessage", "validateCloseCode", "<init>", "()V", "okhttp"}, k = 1, mv = {1, 6, 0})
public final class WebSocketProtocol {
    public static final WebSocketProtocol INSTANCE = new WebSocketProtocol();

    private WebSocketProtocol() {
    }

    public final void toggleMask(Buffer.UnsafeCursor cursor, byte[] key) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Intrinsics.checkNotNullParameter(key, "key");
        int length = key.length;
        int i = 0;
        do {
            byte[] bArr = cursor.data;
            int i2 = cursor.start;
            int i3 = cursor.end;
            if (bArr != null) {
                while (i2 < i3) {
                    int i4 = i % length;
                    bArr[i2] = (byte) (bArr[i2] ^ key[i4]);
                    i2++;
                    i = i4 + 1;
                }
            }
        } while (cursor.next() != -1);
    }

    public final String closeCodeExceptionMessage(int code) {
        if (code < 1000 || code >= 5000) {
            return Intrinsics.stringPlus("Code must be in range [1000,5000): ", Integer.valueOf(code));
        }
        if (!(1004 <= code && code < 1007)) {
            if (!(1015 <= code && code < 3000)) {
                return null;
            }
        }
        return "Code " + code + " is reserved and may not be used.";
    }

    public final void validateCloseCode(int code) {
        String strCloseCodeExceptionMessage = closeCodeExceptionMessage(code);
        if (strCloseCodeExceptionMessage == null) {
            return;
        }
        Intrinsics.checkNotNull(strCloseCodeExceptionMessage);
        throw new IllegalArgumentException(strCloseCodeExceptionMessage.toString());
    }
}
