package com.android.car.developeroptions.display;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WallpaperPreferenceController extends BasePreferenceController {
    private static final String TAG = "WallpaperPrefController";
    private final String mStylesAndWallpaperClass;
    private final String mWallpaperClass;
    private final String mWallpaperPackage;

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

    public WallpaperPreferenceController(Context context, String str) {
        super(context, str);
        this.mWallpaperPackage = this.mContext.getString(R.string.config_wallpaper_picker_package);
        this.mWallpaperClass = this.mContext.getString(R.string.config_wallpaper_picker_class);
        this.mStylesAndWallpaperClass = this.mContext.getString(R.string.config_styles_and_wallpaper_picker_class);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        preferenceScreen.findPreference(getPreferenceKey()).setTitle(this.mContext.getString(areStylesAvailable() ? R.string.style_and_wallpaper_settings_title : R.string.wallpaper_settings_title));
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (!TextUtils.isEmpty(this.mWallpaperPackage) && !TextUtils.isEmpty(this.mWallpaperClass)) {
            return canResolveWallpaperComponent(this.mWallpaperClass) ? 1 : 2;
        }
        Log.e(TAG, "No Wallpaper picker specified!");
        return 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        disablePreferenceIfManaged((RestrictedPreference) preference);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (getPreferenceKey().equals(preference.getKey())) {
            preference.getContext().startActivity(new Intent().setComponent(new ComponentName(this.mWallpaperPackage, areStylesAvailable() ? this.mStylesAndWallpaperClass : this.mWallpaperClass)));
            return true;
        }
        return super.handlePreferenceTreeClick(preference);
    }

    public boolean areStylesAvailable() {
        return !TextUtils.isEmpty(this.mStylesAndWallpaperClass) && canResolveWallpaperComponent(this.mStylesAndWallpaperClass);
    }

    private boolean canResolveWallpaperComponent(String str) {
        List<ResolveInfo> listQueryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(new Intent().setComponent(new ComponentName(this.mWallpaperPackage, str)), 0);
        return (listQueryIntentActivities == null || listQueryIntentActivities.isEmpty()) ? false : true;
    }

    private void disablePreferenceIfManaged(RestrictedPreference restrictedPreference) {
        if (restrictedPreference != null) {
            restrictedPreference.setDisabledByAdmin(null);
            if (RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, "no_set_wallpaper", UserHandle.myUserId())) {
                restrictedPreference.setEnabled(false);
            } else {
                restrictedPreference.checkRestrictionAndSetDisabled("no_set_wallpaper");
            }
        }
    }
}
