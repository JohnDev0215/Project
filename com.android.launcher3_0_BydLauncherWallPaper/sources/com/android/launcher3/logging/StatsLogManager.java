package com.android.launcher3.logging;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.android.launcher3.R;
import com.android.launcher3.logging.StatsLogUtils;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.ResourceBasedOverride;

/* JADX INFO: loaded from: classes4.dex */
public class StatsLogManager implements ResourceBasedOverride {
    protected StatsLogUtils.LogStateProvider mStateProvider;

    public void logAppLaunch(View v, Intent intent) {
    }

    public void logTaskLaunch(View v, ComponentKey key) {
    }

    public void verify() {
    }

    public static StatsLogManager newInstance(Context context, StatsLogUtils.LogStateProvider stateProvider) {
        StatsLogManager statsLogManager = (StatsLogManager) ResourceBasedOverride.Overrides.getObject(StatsLogManager.class, context.getApplicationContext(), R.string.stats_log_manager_class);
        statsLogManager.mStateProvider = stateProvider;
        statsLogManager.verify();
        return statsLogManager;
    }
}
