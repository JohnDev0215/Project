package com.amazon.music.okhttp3.metrics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import androidx.core.os.EnvironmentCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.music.okhttp3.metrics.MetricsManager;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.providers.MetricsProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.concurrent.TimersKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: MetricsManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0003\u001a\u001b\u001cB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u0016H\u0001¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0019R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/okhttp3/metrics/MetricsManager;", "", "context", "Landroid/content/Context;", "appStartRealtime", "", "metricsProvider", "Lkotlin/Function0;", "Lcom/amazon/music/platform/providers/MetricsProvider;", "(Landroid/content/Context;JLkotlin/jvm/functions/Function0;)V", "activeAccumulator", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/amazon/music/okhttp3/metrics/MetricsManager$MetricsAccumulator;", "completedAccumulators", "", "connectivityManager", "Landroid/net/ConnectivityManager;", "kotlin.jvm.PlatformType", "metricsEmitter", "Ljava/util/Timer;", "activeAccumulator$DMMOkHttpClient_release", "emitIntervalFlexEvents", "", "emitIntervalFlexEvents$DMMOkHttpClient_release", "enableIntervalMetrics", "enableIntervalMetrics$DMMOkHttpClient_release", "Companion", "IntervalDefinition", "MetricsAccumulator", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MetricsManager {
    private static final List<IntervalDefinition> INTERVAL_DEFINITIONS;
    private static final String MODEL_NAME;
    private final AtomicReference<MetricsAccumulator> activeAccumulator;
    private final long appStartRealtime;
    private final Collection<MetricsAccumulator> completedAccumulators;
    private final ConnectivityManager connectivityManager;
    private Timer metricsEmitter;
    private final Function0<MetricsProvider> metricsProvider;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) MetricsManager.class);

    /* JADX WARN: Multi-variable type inference failed */
    public MetricsManager(Context context, long j, Function0<? extends MetricsProvider> metricsProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
        this.appStartRealtime = j;
        this.metricsProvider = metricsProvider;
        this.connectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        this.activeAccumulator = new AtomicReference<>(new MetricsAccumulator("n/a", "n/a", -1L, -1L, j));
        this.completedAccumulators = new ConcurrentLinkedQueue();
    }

    /* JADX INFO: compiled from: MetricsManager.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\u0015\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0001¢\u0006\u0002\b\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/okhttp3/metrics/MetricsManager$Companion;", "", "()V", "ACCUMULATOR_BATCH_SIZE", "", "ACCUMULATOR_LIMIT", "INTERVAL_DEFINITIONS", "", "Lcom/amazon/music/okhttp3/metrics/MetricsManager$IntervalDefinition;", "LOGGER", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "MAX_CONCURRENT_COUNT_TRIES", "MODEL_NAME", "", "SEND_INTERVAL_MS", "", "UNKNOWN", "VERSION", "intervalDefinition", "nowMs", "networkInfo", "connectivityManager", "Landroid/net/ConnectivityManager;", "networkInfo$DMMOkHttpClient_release", "transportName", "capabilities", "Landroid/net/NetworkCapabilities;", "transportName$DMMOkHttpClient_release", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @VisibleForTesting
        public final String networkInfo$DMMOkHttpClient_release(ConnectivityManager connectivityManager) {
            NetworkCapabilities networkCapabilities;
            Intrinsics.checkNotNullParameter(connectivityManager, "connectivityManager");
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
                return "unknown,unknown,unknown";
            }
            String str = MetricsManager.INSTANCE.transportName$DMMOkHttpClient_release(networkCapabilities) + ',' + networkCapabilities.getLinkDownstreamBandwidthKbps() + ',' + networkCapabilities.getLinkUpstreamBandwidthKbps();
            return str == null ? "unknown,unknown,unknown" : str;
        }

        @VisibleForTesting
        public final String transportName$DMMOkHttpClient_release(NetworkCapabilities capabilities) {
            Intrinsics.checkNotNullParameter(capabilities, "capabilities");
            return capabilities.hasTransport(0) ? "cellular" : capabilities.hasTransport(1) ? "wifi" : capabilities.hasTransport(3) ? "ethernet" : capabilities.hasTransport(2) ? "bluetooth" : capabilities.hasTransport(8) ? "usb" : capabilities.hasTransport(6) ? "lowpan" : capabilities.hasTransport(4) ? "vpn" : capabilities.hasTransport(5) ? "wifi-aware" : EnvironmentCompat.MEDIA_UNKNOWN;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final IntervalDefinition intervalDefinition(long nowMs) {
            for (IntervalDefinition intervalDefinition : MetricsManager.INTERVAL_DEFINITIONS) {
                if (nowMs < intervalDefinition.getEndMs()) {
                    return intervalDefinition;
                }
            }
            return (IntervalDefinition) CollectionsKt___CollectionsKt.last(MetricsManager.INTERVAL_DEFINITIONS);
        }
    }

    static {
        String str = Build.MANUFACTURER;
        String str2 = EnvironmentCompat.MEDIA_UNKNOWN;
        if (str == null) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        String str3 = Build.MODEL;
        if (str3 != null) {
            str2 = str3;
        }
        int i = 0;
        if (!StringsKt__StringsJVMKt.startsWith$default(str2, str, false, 2, null)) {
            str2 = str + ' ' + str2;
        }
        MODEL_NAME = str2;
        long j = 0;
        DefaultConstructorMarker defaultConstructorMarker = null;
        List<IntervalDefinition> listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new IntervalDefinition[]{new IntervalDefinition("PbxUncStats_v2_2s", "v2-2s-", 2000L, 5, j, 16, defaultConstructorMarker), new IntervalDefinition("PbxUncStats_v2_5s", "v2-5s-", CoroutineLiveDataKt.DEFAULT_TIMEOUT, 36, 0L, 16, null), new IntervalDefinition("PbxUncStats_v2_30s", "v2-30s-", 30000L, 0, j, 24, defaultConstructorMarker)});
        ArrayList arrayList = new ArrayList(listListOf.size());
        for (IntervalDefinition intervalDefinition : listListOf) {
            int i2 = i + 1;
            arrayList.add(i, new IntervalDefinition(intervalDefinition.getEventName(), intervalDefinition.getLabelPrefix(), intervalDefinition.getDurationMs(), intervalDefinition.getLastIdNumber() > 0 ? intervalDefinition.getLastIdNumber() : Integer.MAX_VALUE, intervalDefinition.getLastIdNumber() > 0 ? intervalDefinition.getDurationMs() * ((long) intervalDefinition.getLastIdNumber()) : Long.MAX_VALUE));
            i = i2;
        }
        INTERVAL_DEFINITIONS = arrayList;
    }

    public final void enableIntervalMetrics$DMMOkHttpClient_release() {
        Timer timer = this.metricsEmitter;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = TimersKt.timer("DMMOkHttpClientMetrics", true);
        timer2.schedule(new TimerTask() { // from class: com.amazon.music.okhttp3.metrics.MetricsManager$enableIntervalMetrics$$inlined$timer$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                this.this$0.emitIntervalFlexEvents$DMMOkHttpClient_release();
            }
        }, 30000L, 30000L);
        this.metricsEmitter = timer2;
    }

    public final MetricsAccumulator activeAccumulator$DMMOkHttpClient_release() {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.appStartRealtime;
        IntervalDefinition intervalDefinition = INSTANCE.intervalDefinition(jElapsedRealtime);
        long durationMs = jElapsedRealtime / intervalDefinition.getDurationMs();
        long durationMs2 = durationMs * intervalDefinition.getDurationMs();
        MetricsAccumulator metricsAccumulator = this.activeAccumulator.get();
        Intrinsics.checkNotNullExpressionValue(metricsAccumulator, "activeAccumulator.get()");
        MetricsAccumulator metricsAccumulator2 = metricsAccumulator;
        if (metricsAccumulator2.getPeriodStartMs() == durationMs2) {
            return metricsAccumulator2;
        }
        if (CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.activeAccumulator, metricsAccumulator2, new MetricsAccumulator(intervalDefinition.getEventName(), Intrinsics.stringPlus(intervalDefinition.getLabelPrefix(), Long.valueOf(durationMs + 1)), durationMs2, durationMs2 + intervalDefinition.getDurationMs(), this.appStartRealtime))) {
            if (this.completedAccumulators.size() < 100) {
                this.completedAccumulators.add(metricsAccumulator2);
            } else {
                LOGGER.error("completedAccumulators are not being consumed; collection was stopped to limit memory use");
            }
        }
        MetricsAccumulator metricsAccumulator3 = this.activeAccumulator.get();
        Intrinsics.checkNotNullExpressionValue(metricsAccumulator3, "activeAccumulator.get()");
        return metricsAccumulator3;
    }

    @VisibleForTesting
    public final void emitIntervalFlexEvents$DMMOkHttpClient_release() {
        Unit unit;
        MetricsProvider metricsProviderInvoke = this.metricsProvider.invoke();
        if (metricsProviderInvoke == null) {
            unit = null;
        } else {
            for (MetricsAccumulator metricsAccumulator : CollectionsKt___CollectionsKt.take(this.completedAccumulators, 4)) {
                if (!metricsAccumulator.hasActiveRequests() && !metricsAccumulator.getEmitted()) {
                    metricsAccumulator.markEmitted();
                    if (metricsAccumulator.requestsStarted() > 0) {
                        int iSuccesses = metricsAccumulator.successes();
                        int iFailures = metricsAccumulator.failures();
                        int iExceptions = metricsAccumulator.exceptions();
                        FlexWrapper.Builder builderWithHcStr2 = new FlexWrapper.Builder().withName(metricsAccumulator.getEventName()).withNum1(Float.valueOf(metricsAccumulator.requestsStarted())).withNum2(Float.valueOf(metricsAccumulator.maxConcurrentRequests())).withNum3(Float.valueOf((iFailures + iExceptions) / ((iSuccesses + iFailures) + iExceptions))).withNum4(Float.valueOf(metricsAccumulator.responseReadyAvgMs())).withHcStr1(metricsAccumulator.getPeriodLabel()).withHcStr2(iSuccesses + ',' + iFailures + ',' + iExceptions + ", " + metricsAccumulator.responsesHttp2() + ',' + metricsAccumulator.responsesHttp11() + ',' + metricsAccumulator.responsesOther() + ", " + metricsAccumulator.requestOverrunCount() + ',' + metricsAccumulator.requestOverrunTotalMs());
                        Companion companion = INSTANCE;
                        ConnectivityManager connectivityManager = this.connectivityManager;
                        Intrinsics.checkNotNullExpressionValue(connectivityManager, "connectivityManager");
                        FlexWrapper flexWrapperBuild = builderWithHcStr2.withHcStr3(companion.networkInfo$DMMOkHttpClient_release(connectivityManager)).withHcStr4(MODEL_NAME).build();
                        metricsProviderInvoke.sendMetricsEvent(flexWrapperBuild);
                        LOGGER.debug(Intrinsics.stringPlus("Sent unified client metrics: ", flexWrapperBuild));
                    }
                }
            }
            this.completedAccumulators.removeIf(new Predicate() { // from class: com.amazon.music.okhttp3.metrics.MetricsManager$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MetricsManager.m2177emitIntervalFlexEvents$lambda3$lambda2((MetricsManager.MetricsAccumulator) obj);
                }
            });
            if (!this.completedAccumulators.isEmpty()) {
                LOGGER.debug("Queued accumulators were not fully consumed, " + this.completedAccumulators.size() + " remaining");
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            LOGGER.error("Interval metrics not sent because metrics provider is not available");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: emitIntervalFlexEvents$lambda-3$lambda-2, reason: not valid java name */
    public static final boolean m2177emitIntervalFlexEvents$lambda3$lambda2(MetricsAccumulator it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getEmitted();
    }

    /* JADX INFO: compiled from: MetricsManager.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/okhttp3/metrics/MetricsManager$IntervalDefinition;", "", "eventName", "", "labelPrefix", "durationMs", "", "lastIdNumber", "", "endMs", "(Ljava/lang/String;Ljava/lang/String;JIJ)V", "getDurationMs", "()J", "getEndMs", "getEventName", "()Ljava/lang/String;", "getLabelPrefix", "getLastIdNumber", "()I", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class IntervalDefinition {
        private final long durationMs;
        private final long endMs;
        private final String eventName;
        private final String labelPrefix;
        private final int lastIdNumber;

        public IntervalDefinition(String eventName, String labelPrefix, long j, int i, long j2) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            Intrinsics.checkNotNullParameter(labelPrefix, "labelPrefix");
            this.eventName = eventName;
            this.labelPrefix = labelPrefix;
            this.durationMs = j;
            this.lastIdNumber = i;
            this.endMs = j2;
        }

        public /* synthetic */ IntervalDefinition(String str, String str2, long j, int i, long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, j, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? 0L : j2);
        }

        public final String getEventName() {
            return this.eventName;
        }

        public final String getLabelPrefix() {
            return this.labelPrefix;
        }

        public final long getDurationMs() {
            return this.durationMs;
        }

        public final int getLastIdNumber() {
            return this.lastIdNumber;
        }

        public final long getEndMs() {
            return this.endMs;
        }
    }

    /* JADX INFO: compiled from: MetricsManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\u0006\u0010$\u001a\u00020\u001f\u0012\u0006\u0010&\u001a\u00020\t\u0012\u0006\u0010*\u001a\u00020\t\u0012\u0006\u0010,\u001a\u00020\t¢\u0006\u0004\b<\u0010=J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012J\u0006\u0010\u0016\u001a\u00020\u0012J\u0006\u0010\u0017\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\u0012J\u0006\u0010\u0019\u001a\u00020\u0012J\u0006\u0010\u001a\u001a\u00020\u0012J\u0006\u0010\u001c\u001a\u00020\u001bJ\u0006\u0010\u001d\u001a\u00020\u0012J\u0006\u0010\u001e\u001a\u00020\tR\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010$\u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b$\u0010!\u001a\u0004\b%\u0010#R\u0017\u0010&\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\t8\u0001X\u0080\u0004¢\u0006\f\n\u0004\b*\u0010'\u001a\u0004\b+\u0010)R\u001a\u0010,\u001a\u00020\t8\u0001X\u0080\u0004¢\u0006\f\n\u0004\b,\u0010'\u001a\u0004\b-\u0010)R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0014\u0010\u0013\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u00100R\u0014\u00103\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00100R\u0014\u00104\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00100R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u0010\u001d\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u00100R\u0014\u0010\u001e\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u00107R\u0014\u00108\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00100R\u0014\u0010\u0014\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u00100R\u0014\u0010\u0015\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u00100R\u0014\u0010\u0016\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u00100R\u0014\u0010\u0017\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u00100R\u0014\u00109\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u00100R\u0014\u0010:\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00100R\u0014\u0010;\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u00100¨\u0006>"}, d2 = {"Lcom/amazon/music/okhttp3/metrics/MetricsManager$MetricsAccumulator;", "", "", "hasActiveRequests", "", "markEmitted", "isEmitted", "startRequest", "didReceiveResponse", "", "startRealtime", "endRequest", "addSuccess", "addFailure", "addException", "Lokhttp3/Protocol;", "protocol", "addProtocol", "", "requestsStarted", "maxConcurrentRequests", "successes", "failures", "exceptions", "responsesHttp2", "responsesHttp11", "responsesOther", "", "responseReadyAvgMs", "requestOverrunCount", "requestOverrunTotalMs", "", "eventName", "Ljava/lang/String;", "getEventName", "()Ljava/lang/String;", "periodLabel", "getPeriodLabel", "periodStartMs", "J", "getPeriodStartMs", "()J", "periodEndMs", "getPeriodEndMs$DMMOkHttpClient_release", "appStartRealtime", "getAppStartRealtime$DMMOkHttpClient_release", "Ljava/util/concurrent/atomic/AtomicInteger;", "activeRequestCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "emitted", "Z", "requestsEnded", "responseReadyCount", "Ljava/util/concurrent/atomic/AtomicLong;", "responseReadyTotalMs", "Ljava/util/concurrent/atomic/AtomicLong;", "concurrentRequests", "http2", "http11", "httpOther", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJJ)V", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0})
    public static final class MetricsAccumulator {
        private final AtomicInteger activeRequestCount;
        private final long appStartRealtime;
        private final AtomicInteger concurrentRequests;
        private boolean emitted;
        private final String eventName;
        private final AtomicInteger exceptions;
        private final AtomicInteger failures;
        private final AtomicInteger http11;
        private final AtomicInteger http2;
        private final AtomicInteger httpOther;
        private final AtomicInteger maxConcurrentRequests;
        private final long periodEndMs;
        private final String periodLabel;
        private final long periodStartMs;
        private final AtomicInteger requestOverrunCount;
        private final AtomicLong requestOverrunTotalMs;
        private final AtomicInteger requestsEnded;
        private final AtomicInteger requestsStarted;
        private final AtomicInteger responseReadyCount;
        private final AtomicLong responseReadyTotalMs;
        private final AtomicInteger successes;

        /* JADX INFO: compiled from: MetricsManager.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Protocol.values().length];
                iArr[Protocol.HTTP_2.ordinal()] = 1;
                iArr[Protocol.HTTP_1_1.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public MetricsAccumulator(String eventName, String periodLabel, long j, long j2, long j3) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            Intrinsics.checkNotNullParameter(periodLabel, "periodLabel");
            this.eventName = eventName;
            this.periodLabel = periodLabel;
            this.periodStartMs = j;
            this.periodEndMs = j2;
            this.appStartRealtime = j3;
            this.activeRequestCount = new AtomicInteger();
            this.requestsStarted = new AtomicInteger();
            this.requestsEnded = new AtomicInteger();
            this.responseReadyCount = new AtomicInteger();
            this.responseReadyTotalMs = new AtomicLong();
            this.requestOverrunCount = new AtomicInteger();
            this.requestOverrunTotalMs = new AtomicLong();
            this.concurrentRequests = new AtomicInteger();
            this.maxConcurrentRequests = new AtomicInteger();
            this.successes = new AtomicInteger();
            this.failures = new AtomicInteger();
            this.exceptions = new AtomicInteger();
            this.http2 = new AtomicInteger();
            this.http11 = new AtomicInteger();
            this.httpOther = new AtomicInteger();
        }

        public final String getEventName() {
            return this.eventName;
        }

        public final String getPeriodLabel() {
            return this.periodLabel;
        }

        public final long getPeriodStartMs() {
            return this.periodStartMs;
        }

        public final boolean hasActiveRequests() {
            return this.activeRequestCount.get() > 0;
        }

        public final void markEmitted() {
            this.emitted = true;
        }

        /* JADX INFO: renamed from: isEmitted, reason: from getter */
        public final boolean getEmitted() {
            return this.emitted;
        }

        public final void startRequest() {
            int i;
            this.activeRequestCount.incrementAndGet();
            this.requestsStarted.incrementAndGet();
            int iIncrementAndGet = this.concurrentRequests.incrementAndGet();
            int i2 = 3;
            while (true) {
                int i3 = i2 - 1;
                if (i2 <= 0 || iIncrementAndGet <= (i = this.maxConcurrentRequests.get()) || this.maxConcurrentRequests.compareAndSet(i, iIncrementAndGet)) {
                    return;
                } else {
                    i2 = i3;
                }
            }
        }

        public final void endRequest(boolean didReceiveResponse, long startRealtime) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = jElapsedRealtime - this.appStartRealtime;
            if (didReceiveResponse) {
                this.responseReadyCount.incrementAndGet();
                this.responseReadyTotalMs.addAndGet(jElapsedRealtime - startRealtime);
            }
            if (j > this.periodEndMs) {
                this.requestOverrunCount.incrementAndGet();
                this.requestOverrunTotalMs.addAndGet(j - this.periodEndMs);
            }
            this.requestsEnded.incrementAndGet();
            this.concurrentRequests.decrementAndGet();
            this.activeRequestCount.decrementAndGet();
        }

        public final void addSuccess() {
            this.successes.incrementAndGet();
        }

        public final void addFailure() {
            this.failures.incrementAndGet();
        }

        public final void addException() {
            this.exceptions.incrementAndGet();
        }

        public final void addProtocol(Protocol protocol) {
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            int i = WhenMappings.$EnumSwitchMapping$0[protocol.ordinal()];
            if (i == 1) {
                this.http2.incrementAndGet();
            } else if (i == 2) {
                this.http11.incrementAndGet();
            } else {
                this.httpOther.incrementAndGet();
            }
        }

        public final int requestsStarted() {
            return this.requestsStarted.get();
        }

        public final int maxConcurrentRequests() {
            return this.maxConcurrentRequests.get();
        }

        public final int successes() {
            return this.successes.get();
        }

        public final int failures() {
            return this.failures.get();
        }

        public final int exceptions() {
            return this.exceptions.get();
        }

        public final int responsesHttp2() {
            return this.http2.get();
        }

        public final int responsesHttp11() {
            return this.http11.get();
        }

        public final int responsesOther() {
            return this.httpOther.get();
        }

        public final float responseReadyAvgMs() {
            return this.responseReadyTotalMs.get() / this.responseReadyCount.get();
        }

        public final int requestOverrunCount() {
            return this.requestOverrunCount.get();
        }

        public final long requestOverrunTotalMs() {
            return this.requestOverrunTotalMs.get();
        }
    }
}
