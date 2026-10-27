package com.amazon.mp3.service.metrics;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.service.metrics.FirstTimeUseMetrics;
import com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class FirstTimeUseMetricsMarket implements FirstTimeUseMetrics {
    private static final String TAG = "FirstTimeUseMetricsMarket";
    private final String FIRST_APP_OPEN_TIME = "FirstAppOpenTime";
    private final Context mContext;
    private final BaseMetricsRecorder mRecorder;

    public FirstTimeUseMetricsMarket(Context context, BaseMetricsRecorder recorder) {
        this.mContext = context;
        this.mRecorder = recorder;
    }

    @Override // com.amazon.mp3.service.metrics.FirstTimeUseMetrics
    public void recordFirstAppOpen() {
        SharedPreferences sharedPref = getSharedPref(this.mContext);
        if (sharedPref.getLong("FirstAppOpen", 0L) == 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            SharedPreferences.Editor editorEdit = sharedPref.edit();
            editorEdit.putLong("FirstAppOpen", jCurrentTimeMillis);
            editorEdit.apply();
            MinervaMetricsFactory.record("vrzx4wil", "9w39/2/02330400", "MetricName", "FirstAppOpen", "Counter", 1L, "FirstAppOpenTime", Long.valueOf(jCurrentTimeMillis));
            Log.verbose(TAG, "FirstAppOpen");
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.service.metrics.FirstTimeUseMetricsMarket$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$service$metrics$FirstTimeUseMetrics$Source;

        static {
            int[] iArr = new int[FirstTimeUseMetrics.Source.values().length];
            $SwitchMap$com$amazon$mp3$service$metrics$FirstTimeUseMetrics$Source = iArr;
            try {
                iArr[FirstTimeUseMetrics.Source.STORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$FirstTimeUseMetrics$Source[FirstTimeUseMetrics.Source.CLOUD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$FirstTimeUseMetrics$Source[FirstTimeUseMetrics.Source.DEVICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.mp3.service.metrics.FirstTimeUseMetrics
    public void recordFirstSignInAttempt(FirstTimeUseMetrics.Source source) {
        String str;
        String str2;
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$FirstTimeUseMetrics$Source[source.ordinal()];
        if (i == 1) {
            str = "wy2l/2/02330400";
            str2 = "LogInAttemptFromStore";
        } else if (i == 2) {
            str = "nyhk/2/02330400";
            str2 = "LogInAttemptFromCloud";
        } else {
            if (i != 3) {
                throw new IllegalArgumentException("Invalid source");
            }
            str = "f7sa/2/02330400";
            str2 = "LogInAttemptFromDevice";
        }
        MinervaMetricsFactory.record("vrzx4wil", str, "Counter", 1L, "MetricName", str2);
    }

    @Override // com.amazon.mp3.service.metrics.FirstTimeUseMetrics
    public void recordFirstSuccessfulSignIn(FirstTimeUseMetrics.Source source) {
        String str;
        String str2;
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$FirstTimeUseMetrics$Source[source.ordinal()];
        if (i == 1) {
            str = "72ch/2/02330400";
            str2 = "FirstSuccessfulSignInFromStore";
        } else if (i == 2) {
            str = "f0h7/2/02330400";
            str2 = "FirstSuccessfulSignInFromCloud";
        } else {
            if (i != 3) {
                throw new IllegalArgumentException("Invalid source");
            }
            str = "rgbk/2/02330400";
            str2 = "FirstSuccessfulSignInFromDevice";
        }
        MinervaMetricsFactory.record("vrzx4wil", str, "Counter", 1L, "MetricName", str2);
    }

    private static SharedPreferences getSharedPref(Context context) {
        return context.getSharedPreferences("FirstTimeUseMetrics", 0);
    }
}
