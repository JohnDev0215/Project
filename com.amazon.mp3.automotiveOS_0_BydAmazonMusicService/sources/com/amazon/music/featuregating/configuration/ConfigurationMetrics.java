package com.amazon.music.featuregating.configuration;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ConfigurationMetrics.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0007¢\u0006\u0004\b!\u0010 J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\t\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\n\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u000b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0006J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0010\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0011\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012J\u0010\u0010\u0015\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0016\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006R0\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00178\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0019\u0010\u001a\u0012\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006#"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "metricsWrapper", "", "track", "", "error", "remoteConfigurationCreationFailed", "localConfigurationCreationFailed", "arcusConfigIdNotAvailable", "decodingErrorNoValueForConfigKey", "data", "unexpectedConfigFormat", "key", "failedToReadConfigurationItem", "failedToCreateOrOpenArcusAttribute", "failedToOpenCachedArcusConfiguration", "", "waitPeriodInSeconds", "arcusConfigFetchThrottled", "failedToReadConfiguration", "arcusConfigFetchFailed", "Lkotlin/Function0;", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "Lkotlin/jvm/functions/Function0;", "getMetricsProvider", "()Lkotlin/jvm/functions/Function0;", "setMetricsProvider", "(Lkotlin/jvm/functions/Function0;)V", "getMetricsProvider$annotations", "()V", "<init>", "Companion", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class ConfigurationMetrics {
    private Function0<? extends MetricsProvider> metricsProvider = new Function0<MetricsProvider>() { // from class: com.amazon.music.featuregating.configuration.ConfigurationMetrics$metricsProvider$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MetricsProvider invoke() {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            if (!(interfaceProvider instanceof MetricsProvider)) {
                interfaceProvider = null;
            }
            return (MetricsProvider) interfaceProvider;
        }
    };

    public final void track(MetricsWrapper metricsWrapper) {
        Intrinsics.checkNotNullParameter(metricsWrapper, "metricsWrapper");
        MetricsProvider metricsProviderInvoke = this.metricsProvider.invoke();
        if (metricsProviderInvoke != null) {
            metricsProviderInvoke.sendMetricsEvent(metricsWrapper);
        }
    }

    public final void remoteConfigurationCreationFailed(String error) {
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.remoteConfigurationCreationFailed(error));
    }

    public final void localConfigurationCreationFailed(String error) {
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.localConfigurationCreationFailed(error));
    }

    public final void arcusConfigIdNotAvailable(String error) {
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.arcusConfigIdNotAvailable(error));
    }

    public final void decodingErrorNoValueForConfigKey(String error) {
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.decodingErrorNoValueForConfigKey(error));
    }

    public final void unexpectedConfigFormat(String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        track(ConfigurationMetricsWrapper.INSTANCE.unexpectedConfigFormat(data));
    }

    public final void failedToReadConfigurationItem(String key, String error) {
        Intrinsics.checkNotNullParameter(key, "key");
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.failedToReadConfigurationItem(key, error));
    }

    public final void failedToCreateOrOpenArcusAttribute(String error) {
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.failedToCreateOrOpenArcusAttribute(error));
    }

    public final void failedToOpenCachedArcusConfiguration(String error) {
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.failedToOpenCachedArcusConfiguration(error));
    }

    public final void arcusConfigFetchThrottled(float waitPeriodInSeconds) {
        track(ConfigurationMetricsWrapper.INSTANCE.arcusConfigFetchThrottled(waitPeriodInSeconds));
    }

    public final void failedToReadConfiguration(String error) {
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.failedToReadConfiguration(error));
    }

    public final void arcusConfigFetchFailed(String error) {
        ConfigurationMetricsWrapper configurationMetricsWrapper = ConfigurationMetricsWrapper.INSTANCE;
        if (error == null) {
            error = "Unknown Error";
        }
        track(configurationMetricsWrapper.arcusConfigFetchFailed(error));
    }
}
