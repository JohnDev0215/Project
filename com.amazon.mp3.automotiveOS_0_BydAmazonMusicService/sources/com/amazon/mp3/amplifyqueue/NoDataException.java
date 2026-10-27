package com.amazon.mp3.amplifyqueue;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoDataException.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000b2\u00060\u0001j\u0002`\u0002:\u0001\u000bB\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0013\b\u0016\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/NoDataException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "()V", NotificationCompat.CATEGORY_MESSAGE, "", "(Ljava/lang/String;)V", "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "Companion", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NoDataException extends RuntimeException {
    public NoDataException() {
        super("No data return");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoDataException(String msg) {
        super(Intrinsics.stringPlus("No data return:", msg));
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoDataException(Exception ex) {
        super("No data return", ex);
        Intrinsics.checkNotNullParameter(ex, "ex");
    }
}
