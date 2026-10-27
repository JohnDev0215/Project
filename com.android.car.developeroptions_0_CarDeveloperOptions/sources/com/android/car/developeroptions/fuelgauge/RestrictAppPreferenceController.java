package com.android.car.developeroptions.fuelgauge;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.fuelgauge.batterytip.AppInfo;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipUtils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RestrictAppPreferenceController extends BasePreferenceController {
    static final String KEY_RESTRICT_APP = "restricted_app";
    List<AppInfo> mAppInfos;
    private AppOpsManager mAppOpsManager;
    private InstrumentedPreferenceFragment mPreferenceFragment;
    private UserManager mUserManager;

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

    public RestrictAppPreferenceController(Context context) {
        super(context, KEY_RESTRICT_APP);
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
    }

    public RestrictAppPreferenceController(InstrumentedPreferenceFragment instrumentedPreferenceFragment) {
        this(instrumentedPreferenceFragment.getContext());
        this.mPreferenceFragment = instrumentedPreferenceFragment;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        List<AppInfo> restrictedAppsList = BatteryTipUtils.getRestrictedAppsList(this.mAppOpsManager, this.mUserManager);
        this.mAppInfos = restrictedAppsList;
        int size = restrictedAppsList.size();
        preference.setVisible(size > 0);
        preference.setSummary(this.mContext.getResources().getQuantityString(R.plurals.restricted_app_summary, size, Integer.valueOf(size)));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (getPreferenceKey().equals(preference.getKey())) {
            RestrictedAppDetails.startRestrictedAppDetails(this.mPreferenceFragment, this.mAppInfos);
            return true;
        }
        return super.handlePreferenceTreeClick(preference);
    }
}
