package com.android.car.developeroptions.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Handler;
import android.permission.PermissionControllerManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TopLevelLocationPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final IntentFilter INTENT_FILTER_LOCATION_MODE_CHANGED = new IntentFilter("android.location.MODE_CHANGED");
    private final LocationManager mLocationManager;
    private int mNumTotal;
    private Preference mPreference;
    private BroadcastReceiver mReceiver;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TopLevelLocationPreferenceController(Context context, String str) {
        super(context, str);
        this.mNumTotal = -1;
        this.mLocationManager = (LocationManager) context.getSystemService("location");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (this.mLocationManager.isLocationEnabled()) {
            if (this.mNumTotal == -1) {
                return this.mContext.getString(R.string.location_settings_loading_app_permission_stats);
            }
            Resources resources = this.mContext.getResources();
            int i = this.mNumTotal;
            return resources.getQuantityString(R.plurals.location_settings_summary_location_on, i, Integer.valueOf(i));
        }
        return this.mContext.getString(R.string.location_settings_summary_location_off);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: setLocationAppCount, reason: merged with bridge method [inline-methods] */
    public void lambda$updateState$0$TopLevelLocationPreferenceController(int i) {
        this.mNumTotal = i;
        refreshSummary(this.mPreference);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mPreference = preference;
        refreshSummary(preference);
        if (this.mLocationManager.isLocationEnabled()) {
            ((PermissionControllerManager) this.mContext.getSystemService(PermissionControllerManager.class)).countPermissionApps(Arrays.asList("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"), 0, new PermissionControllerManager.OnCountPermissionAppsResultCallback() { // from class: com.android.car.developeroptions.location.-$$Lambda$TopLevelLocationPreferenceController$m9nZEAOwzf8W8RMe00087QwZX4M
                public final void onCountPermissionApps(int i) {
                    this.f$0.lambda$updateState$0$TopLevelLocationPreferenceController(i);
                }
            }, (Handler) null);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        if (this.mReceiver == null) {
            this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.location.TopLevelLocationPreferenceController.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    TopLevelLocationPreferenceController.this.refreshLocationMode();
                }
            };
        }
        this.mContext.registerReceiver(this.mReceiver, INTENT_FILTER_LOCATION_MODE_CHANGED);
        refreshLocationMode();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshLocationMode() {
        updateState(this.mPreference);
    }
}
