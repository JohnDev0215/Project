package com.android.car.developeroptions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.Settings;
import android.sysprop.TelephonyProperties;
import com.android.settingslib.WirelessUtils;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public class AirplaneModeEnabler extends BroadcastReceiver {
    private final Context mContext;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private OnAirplaneModeChangedListener mOnAirplaneModeChangedListener;
    private ContentObserver mAirplaneModeObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.developeroptions.AirplaneModeEnabler.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AirplaneModeEnabler.this.onAirplaneModeChanged();
        }
    };
    private IntentFilter mFilter = new IntentFilter("android.intent.action.SERVICE_STATE");

    public interface OnAirplaneModeChangedListener {
        void onAirplaneModeChanged(boolean z);
    }

    public AirplaneModeEnabler(Context context, MetricsFeatureProvider metricsFeatureProvider, OnAirplaneModeChangedListener onAirplaneModeChangedListener) {
        this.mContext = context;
        this.mMetricsFeatureProvider = metricsFeatureProvider;
        this.mOnAirplaneModeChangedListener = onAirplaneModeChangedListener;
    }

    public void resume() {
        this.mContext.registerReceiver(this, this.mFilter);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("airplane_mode_on"), true, this.mAirplaneModeObserver);
    }

    public void pause() {
        this.mContext.unregisterReceiver(this);
        this.mContext.getContentResolver().unregisterContentObserver(this.mAirplaneModeObserver);
    }

    private void setAirplaneModeOn(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        OnAirplaneModeChangedListener onAirplaneModeChangedListener = this.mOnAirplaneModeChangedListener;
        if (onAirplaneModeChangedListener != null) {
            onAirplaneModeChangedListener.onAirplaneModeChanged(z);
        }
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra("state", z);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAirplaneModeChanged() {
        OnAirplaneModeChangedListener onAirplaneModeChangedListener = this.mOnAirplaneModeChangedListener;
        if (onAirplaneModeChangedListener != null) {
            onAirplaneModeChangedListener.onAirplaneModeChanged(isAirplaneModeOn());
        }
    }

    public void setAirplaneMode(boolean z) {
        if (((Boolean) TelephonyProperties.in_ecm_mode().orElse(Boolean.FALSE)).booleanValue()) {
            return;
        }
        this.mMetricsFeatureProvider.action(this.mContext, 177, z);
        setAirplaneModeOn(z);
    }

    public void setAirplaneModeInECM(boolean z, boolean z2) {
        if (z) {
            setAirplaneModeOn(z2);
        } else {
            onAirplaneModeChanged();
        }
    }

    public boolean isAirplaneModeOn() {
        return WirelessUtils.isAirplaneModeOn(this.mContext);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.SERVICE_STATE".equals(intent.getAction())) {
            onAirplaneModeChanged();
        }
    }
}
