package com.amazon.music.featuregating.configuration;

import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConfigurationMetricsWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002J\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ConfigurationMetricsWrapper;", "", "", "error", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "remoteConfigurationCreationFailed", "localConfigurationCreationFailed", "arcusConfigIdNotAvailable", "decodingErrorNoValueForConfigKey", "data", "unexpectedConfigFormat", "key", "failedToReadConfigurationItem", "failedToCreateOrOpenArcusAttribute", "failedToOpenCachedArcusConfiguration", "", "waitPeriodInSeconds", "arcusConfigFetchThrottled", "failedToReadConfiguration", "arcusConfigFetchFailed", "<init>", "()V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class ConfigurationMetricsWrapper {
    public static final ConfigurationMetricsWrapper INSTANCE = new ConfigurationMetricsWrapper();

    private ConfigurationMetricsWrapper() {
    }

    public final MetricsWrapper remoteConfigurationCreationFailed(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("remoteConfigurationCreationFailed").withStr1(error).build();
    }

    public final MetricsWrapper localConfigurationCreationFailed(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("localConfigurationCreationFailed").withStr1(error).build();
    }

    public final MetricsWrapper arcusConfigIdNotAvailable(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("arcusConfigIdNotAvailable").withStr1(error).build();
    }

    public final MetricsWrapper decodingErrorNoValueForConfigKey(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("decodingErrorNoValueForConfigKey").withStr1(error).build();
    }

    public final MetricsWrapper unexpectedConfigFormat(String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new FlexWrapper.Builder().withName("unexpectedConfigFormat").withStr1(data).build();
    }

    public final MetricsWrapper failedToReadConfigurationItem(String key, String error) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("failedToReadConfigurationItem").withStr1(key).withStr2(error).build();
    }

    public final MetricsWrapper failedToCreateOrOpenArcusAttribute(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("failedToCreateOrOpenArcusAttribute").withStr1(error).build();
    }

    public final MetricsWrapper failedToOpenCachedArcusConfiguration(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("failedToOpenCachedArcusConfiguration").withStr1(error).build();
    }

    public final MetricsWrapper arcusConfigFetchThrottled(float waitPeriodInSeconds) {
        return new FlexWrapper.Builder().withName("arcusConfigFetchThrottled").withNum1(Float.valueOf(waitPeriodInSeconds)).build();
    }

    public final MetricsWrapper failedToReadConfiguration(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("failedToReadConfiguration").withStr1(error).build();
    }

    public final MetricsWrapper arcusConfigFetchFailed(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new FlexWrapper.Builder().withName("arcusConfigFetchFailed").withStr1(error).build();
    }
}
