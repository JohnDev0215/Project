package com.amazon.appmanager.lib.metrics;

import android.content.Context;
import android.util.Log;
import com.amazon.maft.metrics.PreloadMetricsFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class MaftMetricsFactory {
    private static PreloadMetricsFactory instance;
    private static PreloadMetricsFactory noOpInstance;

    private MaftMetricsFactory() {
    }

    public static synchronized PreloadMetricsFactory getInstance(Context context) {
        try {
            if (instance == null) {
                instance = new MaftPreloadMetricsFactory(context);
                Log.d("MaftPreloadManagerAndroidLibLogs", "minerva client built successsfully");
            }
        } catch (Exception e) {
            Log.d("MaftPreloadManagerAndroidLibLogs", e.getClass().getSimpleName() + " this Exception occurred while creating minerva client, creating no-op metric-client instance");
            return getNoOpInstance();
        }
        return instance;
    }

    public static synchronized PreloadMetricsFactory getNoOpInstance() {
        if (noOpInstance == null) {
            noOpInstance = new NoOpMaftPreloadMetricsFactory();
        }
        Log.d("MaftPreloadManagerAndroidLibLogs", "No-Op metrics factory object created");
        return noOpInstance;
    }
}
