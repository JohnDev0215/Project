package com.amazon.music.okhttp3;

import com.amazon.music.okhttp3.AMNetworkInterceptor;
import com.amazon.music.okhttp3.policy.AMPriorityPolicy;
import com.amazon.music.okhttp3.policy.AMRetryPolicy;
import com.amazon.music.okhttp3.response.AMResponseRewriter;
import com.amazon.net.okhttp3.ExtraEventListener;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AMInterceptor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/okhttp3/AMInterceptor;", "Lokhttp3/Interceptor;", "eventListener", "Lcom/amazon/net/okhttp3/ExtraEventListener;", "priorityPolicy", "Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy;", "retryPolicy", "Lcom/amazon/music/okhttp3/policy/AMRetryPolicy;", "responseRewriter", "Lcom/amazon/music/okhttp3/response/AMResponseRewriter;", "instanceLabel", "", "(Lcom/amazon/net/okhttp3/ExtraEventListener;Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy;Lcom/amazon/music/okhttp3/policy/AMRetryPolicy;Lcom/amazon/music/okhttp3/response/AMResponseRewriter;Ljava/lang/String;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "noOpIntercept", "sn", "", "unifiedIntercept", "Companion", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AMInterceptor implements Interceptor {
    private final ExtraEventListener eventListener;
    private final String instanceLabel;
    private final AMPriorityPolicy priorityPolicy;
    private final AMResponseRewriter responseRewriter;
    private final AMRetryPolicy retryPolicy;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) AMInterceptor.class);
    private static final AtomicLong counter = new AtomicLong();
    private static final AMInterceptor$Companion$NULL_RETRYER$1 NULL_RETRYER = new AMRetryPolicy.Retryer() { // from class: com.amazon.music.okhttp3.AMInterceptor$Companion$NULL_RETRYER$1
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
            return AMRetryPolicy.Retryer.Advice.NOT_APPLICABLE;
        }
    };

    public AMInterceptor(ExtraEventListener eventListener, AMPriorityPolicy priorityPolicy, AMRetryPolicy retryPolicy, AMResponseRewriter responseRewriter, String instanceLabel) {
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(priorityPolicy, "priorityPolicy");
        Intrinsics.checkNotNullParameter(retryPolicy, "retryPolicy");
        Intrinsics.checkNotNullParameter(responseRewriter, "responseRewriter");
        Intrinsics.checkNotNullParameter(instanceLabel, "instanceLabel");
        this.eventListener = eventListener;
        this.priorityPolicy = priorityPolicy;
        this.retryPolicy = retryPolicy;
        this.responseRewriter = responseRewriter;
        this.instanceLabel = instanceLabel;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        long jIncrementAndGet = counter.incrementAndGet();
        if (AMOkHttpClient.useUnifiedInterceptor()) {
            return unifiedIntercept(chain, jIncrementAndGet);
        }
        return noOpIntercept(chain, jIncrementAndGet);
    }

    private final Response noOpIntercept(Interceptor.Chain chain, long sn) {
        AMNetworkInterceptor.INSTANCE.propagateDebugAnnotations$DMMOkHttpClient_release(this.instanceLabel, sn, false);
        return chain.proceed(chain.request());
    }

    private final Response unifiedIntercept(Interceptor.Chain chain, long sn) throws IOException {
        AMNetworkInterceptor.Companion.propagateDebugAnnotations$DMMOkHttpClient_release$default(AMNetworkInterceptor.INSTANCE, this.instanceLabel, sn, false, 4, null);
        Request request = chain.request();
        AMPriorityPolicy.Prioritizer prioritizer = this.priorityPolicy.prioritizer(request);
        AMRetryPolicy.Retryer retryer = NULL_RETRYER;
        boolean z = false;
        Response responseProceed = null;
        int i = 0;
        Exception exc = null;
        do {
            try {
                prioritizer.releaseCapacity();
                if (retryer.advice() == AMRetryPolicy.Retryer.Advice.ALLOW) {
                    retryer.blockUntilAllowed();
                    this.eventListener.callRetried(chain.call(), i, retryer.response(), INSTANCE.ioException(retryer.exception()));
                }
                if (prioritizer.blockUntilAllowed() == AMPriorityPolicy.Prioritizer.BlockResult.BLOCKED) {
                    this.eventListener.callDeferred(chain.call());
                }
                i++;
                responseProceed = chain.proceed(request);
            } catch (Exception e) {
                i++;
                exc = e;
            }
            retryer = this.retryPolicy.retryer(request, responseProceed, exc, i);
        } while (retryer.advice() == AMRetryPolicy.Retryer.Advice.ALLOW);
        if (this.eventListener != ExtraEventListener.EMPTY_LISTENER) {
            IOException ioException = INSTANCE.ioException(retryer.exception());
            ExtraEventListener.RetryRestriction restriction$DMMOkHttpClient_release = AMRetryPolicy.Retryer.INSTANCE.getRestriction$DMMOkHttpClient_release(retryer.advice());
            if (restriction$DMMOkHttpClient_release != null) {
                this.eventListener.callNotRetried(chain.call(), restriction$DMMOkHttpClient_release, retryer.response(), ioException);
            } else {
                Timeout timeout = chain.call().timeout();
                if ((ioException instanceof InterruptedIOException) && timeout.getHasDeadline() && timeout.deadlineNanoTime() - System.nanoTime() <= 0) {
                    z = true;
                }
                if (z) {
                    this.eventListener.callNotRetried(chain.call(), ExtraEventListener.RetryRestriction.TIME_LIMIT, retryer.response(), ioException);
                }
            }
        }
        return this.responseRewriter.rewrite(request, prioritizer, responseProceed, exc);
    }

    /* JADX INFO: compiled from: AMInterceptor.kt */
    @Metadata(bv = {}, d1 = {"\u0000+\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002R\u001c\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/okhttp3/AMInterceptor$Companion;", "", "Ljava/lang/Exception;", "exception", "Ljava/io/IOException;", "ioException", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "com/amazon/music/okhttp3/AMInterceptor$Companion$NULL_RETRYER$1", "NULL_RETRYER", "Lcom/amazon/music/okhttp3/AMInterceptor$Companion$NULL_RETRYER$1;", "Ljava/util/concurrent/atomic/AtomicLong;", "counter", "Ljava/util/concurrent/atomic/AtomicLong;", "<init>", "()V", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final IOException ioException(Exception exception) {
            if (exception instanceof IOException) {
                return (IOException) exception;
            }
            return null;
        }
    }
}
