package com.android.car.developeroptions.fuelgauge.batterytip.actions;

import android.content.Context;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public abstract class BatteryTipAction {
    protected Context mContext;
    protected MetricsFeatureProvider mMetricsFeatureProvider;

    public abstract void handlePositiveAction(int i);

    public BatteryTipAction(Context context) {
        this.mContext = context;
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }
}
