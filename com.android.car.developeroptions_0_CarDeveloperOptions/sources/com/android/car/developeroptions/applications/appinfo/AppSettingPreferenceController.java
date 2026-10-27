package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class AppSettingPreferenceController extends AppInfoPreferenceControllerBase {
    private String mPackageName;

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppSettingPreferenceController(Context context, String str) {
        super(context, str);
    }

    public AppSettingPreferenceController setPackageName(String str) {
        this.mPackageName = str;
        return this;
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (TextUtils.isEmpty(this.mPackageName) || this.mParent == null || resolveIntent(new Intent("android.intent.action.APPLICATION_PREFERENCES").setPackage(this.mPackageName)) == null) ? 2 : 0;
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        Intent intentResolveIntent;
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey()) || (intentResolveIntent = resolveIntent(new Intent("android.intent.action.APPLICATION_PREFERENCES").setPackage(this.mPackageName))) == null) {
            return false;
        }
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(0, 1017, this.mParent.getMetricsCategory(), null, 0);
        this.mContext.startActivity(intentResolveIntent);
        return true;
    }

    private Intent resolveIntent(Intent intent) {
        ResolveInfo resolveInfoResolveActivity = this.mContext.getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return null;
        }
        Intent intent2 = new Intent(intent.getAction());
        ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
        return intent2.setClassName(activityInfo.packageName, activityInfo.name);
    }
}
