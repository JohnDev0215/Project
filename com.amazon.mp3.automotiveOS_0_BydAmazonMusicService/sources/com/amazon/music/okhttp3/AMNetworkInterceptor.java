package com.amazon.music.okhttp3;

import android.os.SystemClock;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AMNetworkInterceptor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016JD\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/okhttp3/AMNetworkInterceptor;", "Lokhttp3/Interceptor;", "()V", "logger", "Lorg/slf4j/Logger;", "(Lorg/slf4j/Logger;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "logForDebugging", "", "fullIntercept", "", "request", "Lokhttp3/Request;", "response", "exception", "Ljava/lang/Exception;", "requestRealtimeMs", "", "instanceLabel", "", "sn", "Annotations", "Companion", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AMNetworkInterceptor implements Interceptor {
    private static volatile boolean enabled;
    private final Logger logger;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) AMNetworkInterceptor.class);
    private static final Annotations UNKNOWN_ANNOTATIONS = new Annotations("UNKNOWN", 0, true);
    private static final ThreadLocal<Annotations> propagatedAnnotations = new ThreadLocal<>();

    public AMNetworkInterceptor(Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        if (enabled) {
            return;
        }
        logger.warn("Debug logging for network requests is enabled");
        enabled = true;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AMNetworkInterceptor() {
        Logger LOGGER2 = LOGGER;
        Intrinsics.checkNotNullExpressionValue(LOGGER2, "LOGGER");
        this(LOGGER2);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Throwable {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Exception exc = null;
        try {
            Response responseProceed = chain.proceed(request);
            Annotations annotations = INSTANCE.readAnnotations();
            logForDebugging(annotations.getFullIntercept(), request, responseProceed, null, jElapsedRealtime, annotations.getInstanceLabel(), annotations.getSn());
            return responseProceed;
        } catch (Exception e) {
            try {
                throw e;
            } catch (Throwable th) {
                exc = e;
                th = th;
                Annotations annotations2 = INSTANCE.readAnnotations();
                logForDebugging(annotations2.getFullIntercept(), request, null, exc, jElapsedRealtime, annotations2.getInstanceLabel(), annotations2.getSn());
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            Annotations annotations22 = INSTANCE.readAnnotations();
            logForDebugging(annotations22.getFullIntercept(), request, null, exc, jElapsedRealtime, annotations22.getInstanceLabel(), annotations22.getSn());
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void logForDebugging(boolean r24, okhttp3.Request r25, okhttp3.Response r26, java.lang.Exception r27, long r28, java.lang.String r30, long r31) {
        /*
            Method dump skipped, instruction units count: 396
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.okhttp3.AMNetworkInterceptor.logForDebugging(boolean, okhttp3.Request, okhttp3.Response, java.lang.Exception, long, java.lang.String, long):void");
    }

    /* JADX INFO: compiled from: AMNetworkInterceptor.kt */
    @Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J)\u0010\u0010\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/okhttp3/AMNetworkInterceptor$Companion;", "", "Lcom/amazon/music/okhttp3/AMNetworkInterceptor$Annotations;", "readAnnotations", "Lokhttp3/Protocol;", "protocol", "", "prettyProtocol", "instanceLabel", "", "sn", "", "intercepted", "", "propagateDebugAnnotations$DMMOkHttpClient_release", "(Ljava/lang/String;JZ)V", "propagateDebugAnnotations", "ELLIPSIS", "Ljava/lang/String;", "", "MAX_URL_LENGTH", "I", "UNKNOWN_ANNOTATIONS", "Lcom/amazon/music/okhttp3/AMNetworkInterceptor$Annotations;", "enabled", "Z", "Ljava/lang/ThreadLocal;", "propagatedAnnotations", "Ljava/lang/ThreadLocal;", "<init>", "()V", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {

        /* JADX INFO: compiled from: AMNetworkInterceptor.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Protocol.values().length];
                iArr[Protocol.HTTP_2.ordinal()] = 1;
                iArr[Protocol.H2_PRIOR_KNOWLEDGE.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void propagateDebugAnnotations$DMMOkHttpClient_release$default(Companion companion, String str, long j, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = true;
            }
            companion.propagateDebugAnnotations$DMMOkHttpClient_release(str, j, z);
        }

        public final /* synthetic */ void propagateDebugAnnotations$DMMOkHttpClient_release(String instanceLabel, long sn, boolean intercepted) {
            Intrinsics.checkNotNullParameter(instanceLabel, "instanceLabel");
            if (AMNetworkInterceptor.enabled) {
                AMNetworkInterceptor.propagatedAnnotations.set(new Annotations(instanceLabel, sn, intercepted));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Annotations readAnnotations() {
            Annotations annotations = (Annotations) AMNetworkInterceptor.propagatedAnnotations.get();
            return annotations == null ? AMNetworkInterceptor.UNKNOWN_ANNOTATIONS : annotations;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String prettyProtocol(Protocol protocol) {
            int i = WhenMappings.$EnumSwitchMapping$0[protocol.ordinal()];
            return (i == 1 || i == 2) ? "http/2" : protocol.getProtocol();
        }
    }

    /* JADX INFO: compiled from: AMNetworkInterceptor.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\t\u0010\b\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\u0013\u0010\f\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/okhttp3/AMNetworkInterceptor$Annotations;", "", "", "component1", "", "component2", "", "component3", "toString", "", "hashCode", "other", "equals", "instanceLabel", "Ljava/lang/String;", "getInstanceLabel", "()Ljava/lang/String;", "sn", "J", "getSn", "()J", "fullIntercept", "Z", "getFullIntercept", "()Z", "<init>", "(Ljava/lang/String;JZ)V", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0})
    private static final /* data */ class Annotations {
        private final boolean fullIntercept;
        private final String instanceLabel;
        private final long sn;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getInstanceLabel() {
            return this.instanceLabel;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getSn() {
            return this.sn;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getFullIntercept() {
            return this.fullIntercept;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Annotations)) {
                return false;
            }
            Annotations annotations = (Annotations) other;
            return Intrinsics.areEqual(this.instanceLabel, annotations.instanceLabel) && this.sn == annotations.sn && this.fullIntercept == annotations.fullIntercept;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v3, types: [int] */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v5 */
        public int hashCode() {
            int iHashCode = ((this.instanceLabel.hashCode() * 31) + Long.hashCode(this.sn)) * 31;
            boolean z = this.fullIntercept;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iHashCode + r1;
        }

        public String toString() {
            return "Annotations(instanceLabel=" + this.instanceLabel + ", sn=" + this.sn + ", fullIntercept=" + this.fullIntercept + ')';
        }

        public Annotations(String instanceLabel, long j, boolean z) {
            Intrinsics.checkNotNullParameter(instanceLabel, "instanceLabel");
            this.instanceLabel = instanceLabel;
            this.sn = j;
            this.fullIntercept = z;
        }
    }
}
