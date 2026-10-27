package com.android.car.developeroptions.deviceinfo.legal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class LegalPreferenceController extends BasePreferenceController {
    private final PackageManager mPackageManager;
    private Preference mPreference;

    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    protected abstract Intent getIntent();

    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public LegalPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = this.mContext.getPackageManager();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return findMatchingSpecificActivity() != null ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        super.displayPreference(preferenceScreen);
        if (getAvailabilityStatus() == 0) {
            replacePreferenceIntent();
        }
    }

    private ResolveInfo findMatchingSpecificActivity() {
        List<ResolveInfo> listQueryIntentActivities;
        Intent intent = getIntent();
        if (intent == null || (listQueryIntentActivities = this.mPackageManager.queryIntentActivities(intent, 0)) == null) {
            return null;
        }
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                return resolveInfo;
            }
        }
        return null;
    }

    private void replacePreferenceIntent() {
        ResolveInfo resolveInfoFindMatchingSpecificActivity = findMatchingSpecificActivity();
        if (resolveInfoFindMatchingSpecificActivity == null) {
            return;
        }
        Preference preference = this.mPreference;
        Intent intent = new Intent();
        ActivityInfo activityInfo = resolveInfoFindMatchingSpecificActivity.activityInfo;
        preference.setIntent(intent.setClassName(activityInfo.packageName, activityInfo.name));
        this.mPreference.setTitle(resolveInfoFindMatchingSpecificActivity.loadLabel(this.mPackageManager));
    }
}
