package com.amazon.music.okhttp3.policy;

import com.amazon.music.okhttp3.policy.AMRetryPolicy;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: NoRetry.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/okhttp3/policy/NoRetry;", "Lcom/amazon/music/okhttp3/policy/AMRetryPolicy;", "()V", "retryer", "Lcom/amazon/music/okhttp3/policy/AMRetryPolicy$Retryer;", "request", "Lokhttp3/Request;", "response", "Lokhttp3/Response;", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "priorAttempts", "", "Companion", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NoRetry extends AMRetryPolicy {

    @JvmField
    public static final NoRetry INSTANCE = new NoRetry();
    private static final AMRetryPolicy.Retryer DISABLED_RETRYER = new AMRetryPolicy.Retryer() { // from class: com.amazon.music.okhttp3.policy.NoRetry$Companion$DISABLED_RETRYER$1
        @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy.Retryer
        public void blockUntilAllowed() throws InterruptedException {
            AMRetryPolicy.Retryer.DefaultImpls.blockUntilAllowed(this);
        }

        @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy.Retryer
        public Exception exception() {
            return AMRetryPolicy.Retryer.DefaultImpls.exception(this);
        }

        @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy.Retryer
        public Response response() {
            return AMRetryPolicy.Retryer.DefaultImpls.response(this);
        }

        @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy.Retryer
        public AMRetryPolicy.Retryer.Advice advice() {
            return AMRetryPolicy.Retryer.Advice.DENY_DISABLED;
        }
    };
    private static final AMRetryPolicy.Retryer SUCCESS_RETRYER = new AMRetryPolicy.Retryer() { // from class: com.amazon.music.okhttp3.policy.NoRetry$Companion$SUCCESS_RETRYER$1
        @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy.Retryer
        public void blockUntilAllowed() throws InterruptedException {
            AMRetryPolicy.Retryer.DefaultImpls.blockUntilAllowed(this);
        }

        @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy.Retryer
        public Exception exception() {
            return AMRetryPolicy.Retryer.DefaultImpls.exception(this);
        }

        @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy.Retryer
        public Response response() {
            return AMRetryPolicy.Retryer.DefaultImpls.response(this);
        }

        @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy.Retryer
        public AMRetryPolicy.Retryer.Advice advice() {
            return AMRetryPolicy.Retryer.Advice.DENY_SUCCESS;
        }
    };

    private NoRetry() {
    }

    @Override // com.amazon.music.okhttp3.policy.AMRetryPolicy
    public AMRetryPolicy.Retryer retryer(Request request, Response response, Exception exception, int priorAttempts) {
        Intrinsics.checkNotNullParameter(request, "request");
        boolean z = false;
        if (response != null && response.isSuccessful()) {
            z = true;
        }
        return z ? SUCCESS_RETRYER : DISABLED_RETRYER;
    }
}
