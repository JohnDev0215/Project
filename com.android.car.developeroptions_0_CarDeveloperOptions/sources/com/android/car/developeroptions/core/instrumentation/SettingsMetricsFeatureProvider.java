package com.android.car.developeroptions.core.instrumentation;

import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public class SettingsMetricsFeatureProvider extends MetricsFeatureProvider {
    @Override // com.android.settingslib.core.instrumentation.MetricsFeatureProvider
    protected void installLogWriters() {
        super.installLogWriters();
        this.mLoggerWriters.add(new StatsLogWriter());
        this.mLoggerWriters.add(new SettingsIntelligenceLogWriter());
    }
}
