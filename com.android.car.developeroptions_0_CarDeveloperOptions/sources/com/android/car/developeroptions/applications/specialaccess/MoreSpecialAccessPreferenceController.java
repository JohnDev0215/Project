package com.android.car.developeroptions.applications.specialaccess;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class MoreSpecialAccessPreferenceController extends BasePreferenceController {
    private final Intent mIntent;

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

    public MoreSpecialAccessPreferenceController(Context context, String str) {
        super(context, str);
        PackageManager packageManager = context.getPackageManager();
        String permissionControllerPackageName = packageManager.getPermissionControllerPackageName();
        if (permissionControllerPackageName != null) {
            Intent intent = new Intent("android.intent.action.MANAGE_SPECIAL_APP_ACCESSES").setPackage(permissionControllerPackageName);
            this.mIntent = packageManager.resolveActivity(intent, 65536) != null ? intent : null;
        } else {
            this.mIntent = null;
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mIntent != null ? 1 : 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), this.mPreferenceKey)) {
            return false;
        }
        Intent intent = this.mIntent;
        if (intent == null) {
            return true;
        }
        this.mContext.startActivity(intent);
        return true;
    }
}
