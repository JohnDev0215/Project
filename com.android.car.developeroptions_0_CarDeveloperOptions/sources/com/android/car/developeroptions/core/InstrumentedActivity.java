package com.android.car.developeroptions.core;

import android.os.Bundle;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.Instrumentable;
import com.android.settingslib.core.instrumentation.VisibilityLoggerMixin;
import com.android.settingslib.core.lifecycle.ObservableActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class InstrumentedActivity extends ObservableActivity implements Instrumentable {
    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSettingsLifecycle().addObserver(new VisibilityLoggerMixin(getMetricsCategory(), FeatureFactory.getFactory(this).getMetricsFeatureProvider()));
    }
}
