package com.amazon.music.okhttp3.policy;

import com.amazon.net.okhttp3.ExtraEventListener;
import com.amazon.net.okhttp3.RetryPolicy;
import java.util.EnumMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: AMRetryPolicy.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0000¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/okhttp3/policy/AMRetryPolicy;", "Lcom/amazon/net/okhttp3/RetryPolicy;", "()V", "retryer", "Lcom/amazon/music/okhttp3/policy/AMRetryPolicy$Retryer;", "request", "Lokhttp3/Request;", "response", "Lokhttp3/Response;", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "priorAttempts", "", "Retryer", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AMRetryPolicy implements RetryPolicy {
    public abstract Retryer retryer(Request request, Response response, Exception exception, int priorAttempts);

    /* JADX INFO: compiled from: AMRetryPolicy.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \f2\u00020\u0001:\u0002\u000b\fJ\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/music/okhttp3/policy/AMRetryPolicy$Retryer;", "", "advice", "Lcom/amazon/music/okhttp3/policy/AMRetryPolicy$Retryer$Advice;", "blockUntilAllowed", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "response", "Lokhttp3/Response;", "Advice", "Companion", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Retryer {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: AMRetryPolicy.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public static final class DefaultImpls {
            public static void blockUntilAllowed(Retryer retryer) throws InterruptedException {
                Intrinsics.checkNotNullParameter(retryer, "this");
            }

            public static Exception exception(Retryer retryer) {
                Intrinsics.checkNotNullParameter(retryer, "this");
                return null;
            }

            public static Response response(Retryer retryer) {
                Intrinsics.checkNotNullParameter(retryer, "this");
                return null;
            }
        }

        Advice advice();

        void blockUntilAllowed() throws InterruptedException;

        Exception exception();

        Response response();

        /* JADX INFO: compiled from: AMRetryPolicy.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/okhttp3/policy/AMRetryPolicy$Retryer$Advice;", "", "explanation", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "ALLOW", "DENY_CAPACITY", "DENY_COUNT", "DENY_DISABLED", "DENY_EXCEPTION", "DENY_INTERRUPTED", "DENY_STATUS", "DENY_SUCCESS", "NOT_APPLICABLE", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public enum Advice {
            ALLOW("retry allowed"),
            DENY_CAPACITY("retry denied: capacity not available"),
            DENY_COUNT("retry denied: max retry count exceeded"),
            DENY_DISABLED("retry denied: retry disabled"),
            DENY_EXCEPTION("retry denied: exception not eligible"),
            DENY_INTERRUPTED("retry denied: call was interrupted"),
            DENY_STATUS("retry denied: response status not eligible"),
            DENY_SUCCESS("retry denied: response is successful"),
            NOT_APPLICABLE("retry not applicable");

            private final String explanation;

            Advice(String str) {
                this.explanation = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.explanation;
            }
        }

        /* JADX INFO: compiled from: AMRetryPolicy.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\tR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/okhttp3/policy/AMRetryPolicy$Retryer$Companion;", "", "()V", "RESTRICTION_FROM_ADVICE", "Ljava/util/EnumMap;", "Lcom/amazon/music/okhttp3/policy/AMRetryPolicy$Retryer$Advice;", "Lcom/amazon/net/okhttp3/ExtraEventListener$RetryRestriction;", "getRestriction", "advice", "getRestriction$DMMOkHttpClient_release", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final EnumMap<Advice, ExtraEventListener.RetryRestriction> RESTRICTION_FROM_ADVICE;

            private Companion() {
            }

            static {
                EnumMap<Advice, ExtraEventListener.RetryRestriction> enumMap = new EnumMap<>(Advice.class);
                RESTRICTION_FROM_ADVICE = enumMap;
                enumMap.put(Advice.DENY_CAPACITY, ExtraEventListener.RetryRestriction.CAPACITY_LIMIT);
                enumMap.put(Advice.DENY_COUNT, ExtraEventListener.RetryRestriction.COUNT_LIMIT);
                enumMap.put(Advice.DENY_DISABLED, ExtraEventListener.RetryRestriction.DISABLED);
                Advice advice = Advice.DENY_EXCEPTION;
                ExtraEventListener.RetryRestriction retryRestriction = ExtraEventListener.RetryRestriction.INELIGIBLE;
                enumMap.put(advice, retryRestriction);
                enumMap.put(Advice.DENY_STATUS, retryRestriction);
            }

            public final ExtraEventListener.RetryRestriction getRestriction$DMMOkHttpClient_release(Advice advice) {
                Intrinsics.checkNotNullParameter(advice, "advice");
                return RESTRICTION_FROM_ADVICE.get(advice);
            }
        }
    }
}
