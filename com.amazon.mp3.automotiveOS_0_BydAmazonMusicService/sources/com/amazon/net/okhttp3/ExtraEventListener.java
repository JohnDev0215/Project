package com.amazon.net.okhttp3;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Response;

/* JADX INFO: compiled from: ExtraEventListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J,\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J,\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u0013"}, d2 = {"Lcom/amazon/net/okhttp3/ExtraEventListener;", "", "()V", "callDeferred", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "callNotRetried", "restriction", "Lcom/amazon/net/okhttp3/ExtraEventListener$RetryRestriction;", "response", "Lokhttp3/Response;", "ioe", "Ljava/io/IOException;", "callRetried", "retryNumber", "", "Companion", "RetryRestriction", "UnifiedOkHttpClient"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ExtraEventListener {

    @JvmField
    public static final ExtraEventListener EMPTY_LISTENER = new ExtraEventListener() { // from class: com.amazon.net.okhttp3.ExtraEventListener$Companion$EMPTY_LISTENER$1
    };

    public void callDeferred(Call call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }

    public void callNotRetried(Call call, RetryRestriction restriction, Response response, IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(restriction, "restriction");
    }

    public void callRetried(Call call, int retryNumber, Response response, IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
    }

    /* JADX INFO: compiled from: ExtraEventListener.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/net/okhttp3/ExtraEventListener$RetryRestriction;", "", "text", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "CAPACITY_LIMIT", "COUNT_LIMIT", "DISABLED", "INELIGIBLE", "TIME_LIMIT", "UnifiedOkHttpClient"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum RetryRestriction {
        CAPACITY_LIMIT("retry capacity is not available"),
        COUNT_LIMIT("max retry count is exceeded"),
        DISABLED("retries are disabled"),
        INELIGIBLE("response or exception is not eligible to retry"),
        TIME_LIMIT("retry would exceed a time limit");

        private final String text;

        RetryRestriction(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }
}
