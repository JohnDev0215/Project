package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TimeSpentInAppPreferenceController extends BasePreferenceController {
    static final Intent SEE_TIME_IN_APP_TEMPLATE = new Intent("android.settings.action.APP_USAGE_SETTINGS");
    private Intent mIntent;
    private final PackageManager mPackageManager;
    private String mPackageName;

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

    public TimeSpentInAppPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
        this.mIntent = new Intent(SEE_TIME_IN_APP_TEMPLATE).putExtra("android.intent.extra.PACKAGE_NAME", this.mPackageName);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        List<ResolveInfo> listQueryIntentActivities;
        if (!TextUtils.isEmpty(this.mPackageName) && (listQueryIntentActivities = this.mPackageManager.queryIntentActivities(this.mIntent, 0)) != null && !listQueryIntentActivities.isEmpty()) {
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (it.hasNext()) {
                if (isSystemApp(it.next())) {
                    return 0;
                }
            }
        }
        return 3;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (preferenceFindPreference != null) {
            preferenceFindPreference.setIntent(this.mIntent);
        }
    }

    private boolean isSystemApp(ResolveInfo resolveInfo) {
        ActivityInfo activityInfo;
        ApplicationInfo applicationInfo;
        return (resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null || (applicationInfo = activityInfo.applicationInfo) == null || (applicationInfo.flags & 1) == 0) ? false : true;
    }
}
