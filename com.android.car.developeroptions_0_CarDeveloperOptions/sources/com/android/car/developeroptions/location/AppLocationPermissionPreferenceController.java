package com.android.car.developeroptions.location;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Handler;
import android.permission.PermissionControllerManager;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class AppLocationPermissionPreferenceController extends LocationBasePreferenceController implements PreferenceControllerMixin {
    private final LocationManager mLocationManager;
    int mNumBackground;
    int mNumTotal;
    private Preference mPreference;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "app_level_permissions";
    }

    public AppLocationPermissionPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
        this.mNumTotal = -1;
        this.mNumBackground = -1;
        this.mLocationManager = (LocationManager) context.getSystemService("location");
    }

    @Override // com.android.car.developeroptions.location.LocationBasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "location_settings_link_to_permissions_enabled", 1) == 1;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (this.mLocationManager.isLocationEnabled()) {
            if (this.mNumTotal == -1 || this.mNumBackground == -1) {
                return this.mContext.getString(R.string.location_settings_loading_app_permission_stats);
            }
            Resources resources = this.mContext.getResources();
            int i = this.mNumBackground;
            return resources.getQuantityString(R.plurals.location_app_permission_summary_location_on, i, Integer.valueOf(i), Integer.valueOf(this.mNumTotal));
        }
        return this.mContext.getString(R.string.location_app_permission_summary_location_off);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(final Preference preference) {
        super.updateState(preference);
        this.mPreference = preference;
        final AtomicInteger atomicInteger = new AtomicInteger(2);
        refreshSummary(preference);
        if (this.mLocationManager.isLocationEnabled()) {
            PermissionControllerManager permissionControllerManager = (PermissionControllerManager) this.mContext.getSystemService(PermissionControllerManager.class);
            permissionControllerManager.countPermissionApps(Arrays.asList("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"), 0, new PermissionControllerManager.OnCountPermissionAppsResultCallback() { // from class: com.android.car.developeroptions.location.-$$Lambda$AppLocationPermissionPreferenceController$62FrpjNn__hEGGU-_WL0qsJRQhg
                public final void onCountPermissionApps(int i) {
                    this.f$0.lambda$updateState$0$AppLocationPermissionPreferenceController(atomicInteger, preference, i);
                }
            }, (Handler) null);
            permissionControllerManager.countPermissionApps(Collections.singletonList("android.permission.ACCESS_BACKGROUND_LOCATION"), 1, new PermissionControllerManager.OnCountPermissionAppsResultCallback() { // from class: com.android.car.developeroptions.location.-$$Lambda$AppLocationPermissionPreferenceController$4_DIJE8SSbGLgoBq6mNeYTH8KhQ
                public final void onCountPermissionApps(int i) {
                    this.f$0.lambda$updateState$1$AppLocationPermissionPreferenceController(atomicInteger, preference, i);
                }
            }, (Handler) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateState$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateState$0$AppLocationPermissionPreferenceController(AtomicInteger atomicInteger, Preference preference, int i) {
        this.mNumTotal = i;
        if (atomicInteger.decrementAndGet() == 0) {
            refreshSummary(preference);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateState$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateState$1$AppLocationPermissionPreferenceController(AtomicInteger atomicInteger, Preference preference, int i) {
        this.mNumBackground = i;
        if (atomicInteger.decrementAndGet() == 0) {
            refreshSummary(preference);
        }
    }

    @Override // com.android.car.developeroptions.location.LocationEnabler.LocationModeChangeListener
    public void onLocationModeChanged(int i, boolean z) {
        updateState(this.mPreference);
    }
}
