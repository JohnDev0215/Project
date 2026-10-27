package okhttp3.internal.http2;

import java.io.IOException;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: StreamResetException.kt */
/* JADX INFO: loaded from: classes.dex */
public final class StreamResetException extends IOException {
    public final ErrorCode errorCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamResetException(ErrorCode errorCode) {
        super("stream was reset: " + errorCode);
        i.c(errorCode, "errorCode");
        this.errorCode = errorCode;
    }
}
