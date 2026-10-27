package com.amazon.avod.qahooks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.avod.core.Framework;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QAAutomationReceiver extends BroadcastReceiver {
    private final Map<String, Class<? extends QATestFeature>> FEATURE_ACTION_MAP;
    private boolean mIsQABroadcastsEnabled;

    private static class SingletonHolder {
        private static final QAAutomationReceiver INSTANCE = new QAAutomationReceiver();

        private SingletonHolder() {
        }
    }

    public static QAAutomationReceiver getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QAAutomationReceiver() {
        this.FEATURE_ACTION_MAP = new HashMap();
    }

    public void setEnabled(boolean z) {
        this.mIsQABroadcastsEnabled = z;
    }

    public void registerFeatureActions(@Nonnull Map<String, Class<? extends QATestFeature>> map) {
        Preconditions.checkNotNull(map, "featureActions");
        this.FEATURE_ACTION_MAP.putAll(map);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!this.mIsQABroadcastsEnabled || !Framework.isDebugConfigurationEnabled()) {
            DLog.logf("The broadcast that was sent with action %s cannot be passed to the corresponding feature because the current device has not been enabled for QA testing", action);
            return;
        }
        Class<? extends QATestFeature> cls = this.FEATURE_ACTION_MAP.get(action);
        Preconditions.checkState(cls != null, "Action %s is not mapped to a feature.", action);
        QATestFeature feature = QAAutomationTestHooks.getInstance().getFeature(cls);
        if (feature == null) {
            DLog.logf("The broadcast that was sent with action %s cannot be passed to the corresponding feature because the feature is still inactive.", action);
            return;
        }
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "QAAutomationHooks: handleBroadcastIntent with action %s", action);
        feature.handleIntent(intent);
        Profiler.endTrace(traceKeyBeginTrace);
    }

    @Nonnull
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        Iterator<String> it = this.FEATURE_ACTION_MAP.keySet().iterator();
        while (it.hasNext()) {
            intentFilter.addAction(it.next());
        }
        return intentFilter;
    }
}
