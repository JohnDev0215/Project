package com.amazon.music.featuregating.weblabs;

import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: WeblabMetrics.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002J$\u0010\u000f\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\u00022\n\u0010\u000e\u001a\u00060\fj\u0002`\rR\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabMetrics;", "", "", "reason", "", "clientInitFailed", "weblab", "noTreatmentSnapshotReturned", "noTreatmentReturned", "trackUnregisteredWeblab", ParserUtil.TAG_QUERY_PARAM_NAME, "method", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "trackException", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProviderInternal", "Lcom/amazon/music/platform/providers/MetricsProvider;", "getMetricsProvider", "()Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "<init>", "()V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class WeblabMetrics {
    private final String TAG = WeblabMetrics.class.getSimpleName();
    private MetricsProvider metricsProviderInternal;

    private final MetricsProvider getMetricsProvider() {
        if (this.metricsProviderInternal == null) {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            if (!(interfaceProvider instanceof MetricsProvider)) {
                interfaceProvider = null;
            }
            this.metricsProviderInternal = (MetricsProvider) interfaceProvider;
        }
        return this.metricsProviderInternal;
    }

    public final void clientInitFailed(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider != null) {
            metricsProvider.sendMetricsEvent(WeblabMetricsWrapper.INSTANCE.clientInitFailed(reason));
        }
        Log.e(this.TAG, "Weblab init failed reason=" + reason);
    }

    public final void noTreatmentSnapshotReturned(String weblab) {
        Intrinsics.checkNotNullParameter(weblab, "weblab");
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider != null) {
            metricsProvider.sendMetricsEvent(WeblabMetricsWrapper.INSTANCE.noTreatmentSnapshotReturned(weblab));
        }
        Log.e(this.TAG, "SDK did not return a treatment snapshot for weblab " + weblab);
    }

    public final void noTreatmentReturned(String weblab) {
        Intrinsics.checkNotNullParameter(weblab, "weblab");
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider != null) {
            metricsProvider.sendMetricsEvent(WeblabMetricsWrapper.INSTANCE.noTreatmentReturned(weblab));
        }
        Log.e(this.TAG, "SDK did not return any treatment for weblab " + weblab);
    }

    public final void trackUnregisteredWeblab(String weblab) {
        Intrinsics.checkNotNullParameter(weblab, "weblab");
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider != null) {
            metricsProvider.sendMetricsEvent(WeblabMetricsWrapper.INSTANCE.trackUnregisteredWeblab(weblab));
        }
        Log.e(this.TAG, "Weblab treatment requested for " + weblab + " must be registered first");
    }

    public final void trackException(String tag, String method, Exception exception) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(exception, "exception");
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider != null) {
            metricsProvider.sendMetricsEvent(WeblabMetricsWrapper.INSTANCE.trackException(tag, method, exception));
        }
        Log.e(this.TAG, tag + ':' + method + " failed", exception);
    }
}
