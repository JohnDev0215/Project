package com.amazon.music.okhttp3.metrics;

import android.os.SystemClock;
import com.amazon.music.okhttp3.metrics.MetricsManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: MetricsInterceptor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/music/okhttp3/metrics/MetricsInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MetricsInterceptor implements Interceptor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) MetricsInterceptor.class);
    private static volatile MetricsManager metricsManager;

    /* JADX INFO: compiled from: MetricsInterceptor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006R\u001c\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/okhttp3/metrics/MetricsInterceptor$Companion;", "", "Lcom/amazon/music/okhttp3/metrics/MetricsManager;", "manager", "", "useMetricsManager$DMMOkHttpClient_release", "(Lcom/amazon/music/okhttp3/metrics/MetricsManager;)V", "useMetricsManager", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "metricsManager", "Lcom/amazon/music/okhttp3/metrics/MetricsManager;", "<init>", "()V", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void useMetricsManager$DMMOkHttpClient_release(MetricsManager manager) {
            Intrinsics.checkNotNullParameter(manager, "manager");
            MetricsInterceptor.metricsManager = manager;
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        if (metricsManager == null) {
            return chain.proceed(request);
        }
        MetricsManager metricsManager2 = metricsManager;
        Intrinsics.checkNotNull(metricsManager2);
        MetricsManager.MetricsAccumulator metricsAccumulatorActiveAccumulator$DMMOkHttpClient_release = metricsManager2.activeAccumulator$DMMOkHttpClient_release();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        metricsAccumulatorActiveAccumulator$DMMOkHttpClient_release.startRequest();
        try {
            try {
                Response responseProceed = chain.proceed(request);
                metricsAccumulatorActiveAccumulator$DMMOkHttpClient_release.addProtocol(responseProceed.protocol());
                if (responseProceed.isSuccessful()) {
                    metricsAccumulatorActiveAccumulator$DMMOkHttpClient_release.addSuccess();
                } else {
                    metricsAccumulatorActiveAccumulator$DMMOkHttpClient_release.addFailure();
                }
                metricsAccumulatorActiveAccumulator$DMMOkHttpClient_release.endRequest(true, jElapsedRealtime);
                return responseProceed;
            } catch (Exception e) {
                metricsAccumulatorActiveAccumulator$DMMOkHttpClient_release.addException();
                throw e;
            }
        } catch (Throwable th) {
            metricsAccumulatorActiveAccumulator$DMMOkHttpClient_release.endRequest(false, jElapsedRealtime);
            throw th;
        }
    }
}
