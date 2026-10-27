package com.amazon.mp3.service.metrics;

import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.LoginMetrics;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class LoginMetricsMarket implements LoginMetrics {
    private static final String TAG = "LoginMetricsMarket";
    private final BaseMetricsRecorder mRecorder;

    public LoginMetricsMarket(BaseMetricsRecorder recorder) {
        this.mRecorder = recorder;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.service.metrics.LoginMetricsMarket$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$service$metrics$LoginMetrics$Source;

        static {
            int[] iArr = new int[LoginMetrics.Source.values().length];
            $SwitchMap$com$amazon$mp3$service$metrics$LoginMetrics$Source = iArr;
            try {
                iArr[LoginMetrics.Source.STORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$LoginMetrics$Source[LoginMetrics.Source.CLOUD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$LoginMetrics$Source[LoginMetrics.Source.DEVICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.mp3.service.metrics.LoginMetrics
    public void recordLoginAttempt(LoginMetrics.Source source) {
        String str;
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$LoginMetrics$Source[source.ordinal()];
        if (i == 1) {
            str = "LoginAttemptFromStore";
        } else if (i == 2) {
            str = "LoginAttemptFromCloud";
        } else {
            if (i != 3) {
                throw new IllegalArgumentException("Invalid source");
            }
            str = "LoginAttemptFromDevice";
        }
        recordIncrementalMetric(str);
    }

    private BaseMetricsRecorder.MetricEventWrapper createMetricEvent() {
        return this.mRecorder.getMetricsFactory().createMetricEvent(this.mRecorder.getMetricsProgramName(), "LoginMetrics");
    }

    private boolean recordIncrementalMetric(String metricName) {
        BaseMetricsRecorder.MetricEventWrapper metricEventWrapperCreateMetricEvent = createMetricEvent();
        metricEventWrapperCreateMetricEvent.incrementCounter(metricName, 1);
        this.mRecorder.getMetricsFactory().record(metricEventWrapperCreateMetricEvent);
        Log.verbose(TAG, metricName);
        return true;
    }
}
