package com.android.car.developeroptions.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.accessibility.AccessibilityUtils;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilitySlicePreferenceController extends TogglePreferenceController {
    private final int OFF;
    private final int ON;
    private final ComponentName mComponentName;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AccessibilitySlicePreferenceController(Context context, String str) {
        super(context, str);
        this.ON = 1;
        this.OFF = 0;
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(getPreferenceKey());
        this.mComponentName = componentNameUnflattenFromString;
        if (componentNameUnflattenFromString != null) {
            return;
        }
        throw new IllegalArgumentException("Illegal Component Name from: " + str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        AccessibilityServiceInfo accessibilityServiceInfo = getAccessibilityServiceInfo();
        return accessibilityServiceInfo == null ? "" : AccessibilitySettings.getServiceSummary(this.mContext, accessibilityServiceInfo, isChecked());
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "accessibility_enabled", 0) == 1) {
            return AccessibilityUtils.getEnabledServicesFromSettings(this.mContext).contains(this.mComponentName);
        }
        return false;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        if (getAccessibilityServiceInfo() == null) {
            return false;
        }
        AccessibilityUtils.setAccessibilityServiceState(this.mContext, this.mComponentName, z);
        return z == isChecked();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return getAccessibilityServiceInfo() == null ? 3 : 0;
    }

    private AccessibilityServiceInfo getAccessibilityServiceInfo() {
        for (AccessibilityServiceInfo accessibilityServiceInfo : ((AccessibilityManager) this.mContext.getSystemService(AccessibilityManager.class)).getInstalledAccessibilityServiceList()) {
            if (this.mComponentName.equals(accessibilityServiceInfo.getComponentName())) {
                return accessibilityServiceInfo;
            }
        }
        return null;
    }
}
