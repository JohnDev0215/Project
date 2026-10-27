package com.android.car.developeroptions.accounts;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.search.SearchIndexableRaw;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyInfoPreferenceController extends BasePreferenceController {
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

    public static String getIntentAction(Context context) {
        return context.getResources().getString(R.string.config_emergency_intent_action);
    }

    public EmergencyInfoPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public void updateRawDataToIndex(List<SearchIndexableRaw> list) {
        if (isAvailable()) {
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(this.mContext);
            Resources resources = this.mContext.getResources();
            resources.getString(R.string.emergency_info_title);
            resources.getString(R.string.emergency_info_title);
            list.add(searchIndexableRaw);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setSummary(this.mContext.getString(R.string.emergency_info_summary, ((UserManager) this.mContext.getSystemService(UserManager.class)).getUserInfo(UserHandle.myUserId()).name));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(getPreferenceKey(), preference.getKey())) {
            return false;
        }
        Intent intent = new Intent(getIntentAction(this.mContext));
        intent.setFlags(67108864);
        this.mContext.startActivity(intent);
        return true;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (!this.mContext.getResources().getBoolean(R.bool.config_show_emergency_info_in_device_info)) {
            return 3;
        }
        List<ResolveInfo> listQueryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(new Intent(getIntentAction(this.mContext)).setPackage(getPackageName(this.mContext)), 0);
        return (listQueryIntentActivities == null || listQueryIntentActivities.isEmpty()) ? 3 : 0;
    }

    private static String getPackageName(Context context) {
        return context.getResources().getString(R.string.config_emergency_package_name);
    }
}
