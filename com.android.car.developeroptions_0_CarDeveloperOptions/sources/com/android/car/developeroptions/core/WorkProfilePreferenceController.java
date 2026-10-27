package com.android.car.developeroptions.core;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public abstract class WorkProfilePreferenceController extends BasePreferenceController {
    private final UserHandle mWorkProfileUser;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    protected abstract int getSourceMetricsCategory();

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

    public WorkProfilePreferenceController(Context context, String str) {
        super(context, str);
        this.mWorkProfileUser = Utils.getManagedProfile(UserManager.get(context));
    }

    protected UserHandle getWorkProfileUser() {
        return this.mWorkProfileUser;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mWorkProfileUser != null ? 0 : 4;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return super.handlePreferenceTreeClick(preference);
        }
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(preference.getContext());
        subSettingLauncher.setDestination(preference.getFragment());
        subSettingLauncher.setSourceMetricsCategory(getSourceMetricsCategory());
        subSettingLauncher.setArguments(preference.getExtras());
        subSettingLauncher.setUserHandle(this.mWorkProfileUser);
        subSettingLauncher.launch();
        return true;
    }
}
