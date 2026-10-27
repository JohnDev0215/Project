package com.amazon.avod.qos.reporter.internal;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.avod.qos.EventThrottle;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.sequence.ConstantIntSequence;
import com.amazon.cloud9.android.knobs.Knobs;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class PlatformMetricsEventSender {
    private final PlatformMetricsConfig mConfig;
    private final Context mContext;
    private final EventThrottle mThrottle;

    public PlatformMetricsEventSender(@Nonnull Context context) {
        this(context, PlatformMetricsConfig.getInstance());
    }

    private PlatformMetricsEventSender(@Nonnull Context context, @Nonnull PlatformMetricsConfig platformMetricsConfig) {
        this(context, platformMetricsConfig, new EventThrottle(new ConstantIntSequence(platformMetricsConfig.getThrottlePeriodSeconds()), TimeUnit.SECONDS));
    }

    PlatformMetricsEventSender(@Nonnull Context context, @Nonnull PlatformMetricsConfig platformMetricsConfig, @Nonnull EventThrottle eventThrottle) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mConfig = (PlatformMetricsConfig) Preconditions.checkNotNull(platformMetricsConfig, "config");
        this.mThrottle = (EventThrottle) Preconditions.checkNotNull(eventThrottle, "throttle");
    }

    public boolean canReportNow() {
        return this.mConfig.isPlatformMetricsEnabled() && this.mThrottle.shouldSendEvent();
    }

    public void reportBufferingMetric(MetricsBuilder metricsBuilder) {
        Bundle bundle = new Bundle();
        bundle.putString("bufferCount", nullSafeToString(metricsBuilder.bufferCount));
        bundle.putString("timeSpentBuffering", nullSafeToString(metricsBuilder.timeSpentBuffering));
        bundle.putString("avgBandwidth", nullSafeToString(metricsBuilder.avgBandwidth));
        bundle.putString("stddevBandwidth", nullSafeToString(metricsBuilder.stddevBandwidth));
        bundle.putString("lastBandwidth", nullSafeToString(metricsBuilder.downloadBandwidth));
        bundle.putString("avgFragmentBitrate", nullSafeToString(metricsBuilder.avgFragmentBitrate));
        bundle.putString("stddevFragmentBitrate", nullSafeToString(metricsBuilder.stddevFragmentBitrate));
        bundle.putString("lastFragmentBitrate", nullSafeToString(metricsBuilder.streamingBitRate));
        bundle.putString("connectionType", nullSafeToString(metricsBuilder.connectionType));
        bundle.putString("signalStrength", nullSafeToString(metricsBuilder.signalStrength));
        sendMetricsIntent("immediate", "recordVideoRebuffer", bundle);
    }

    @Nonnull
    private static String nullSafeToString(@Nullable Object obj) {
        return obj == null ? "null" : obj.toString();
    }

    private void sendMetricsIntent(String str, String str2, Bundle bundle) {
        if (canReportNow()) {
            bundle.putString("runtime", str);
            bundle.putString("methodName", str2);
            Intent intent = new Intent("com.amazon.intent.action.RECORD_METRICS");
            intent.putExtras(bundle);
            DLog.logf("Broadcasting metrics intent %s with runtime=%s, methodName=%s, extras=%s", intent.getAction(), str, str2, intent.getExtras());
            this.mContext.sendBroadcast(intent);
            this.mThrottle.onEvent();
        }
    }

    static class PlatformMetricsConfig extends ServerConfigBase {
        private final ConfigurationValue<Boolean> mIsPlatformMetricsEnabled;
        private final ConfigurationValue<Integer> mPlatformMetricsThrottlePeriodSeconds;

        private static final class SingletonHolder {
            private static final PlatformMetricsConfig INSTANCE = new PlatformMetricsConfig();

            private SingletonHolder() {
            }
        }

        public static final PlatformMetricsConfig getInstance() {
            return SingletonHolder.INSTANCE;
        }

        private PlatformMetricsConfig() {
            this.mIsPlatformMetricsEnabled = newBooleanConfigValue("platformMetrics_isEnabled", false);
            this.mPlatformMetricsThrottlePeriodSeconds = newIntConfigValue("platformMetrics_throttlePeriodSeconds", 120);
        }

        public boolean isPlatformMetricsEnabled() {
            return this.mIsPlatformMetricsEnabled.getValue().booleanValue() || Knobs.get("platformMetrics_isEnabled", false);
        }

        public int getThrottlePeriodSeconds() {
            return this.mPlatformMetricsThrottlePeriodSeconds.getValue().intValue();
        }
    }
}
