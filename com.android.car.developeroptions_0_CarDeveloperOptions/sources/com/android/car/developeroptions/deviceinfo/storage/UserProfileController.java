package com.android.car.developeroptions.deviceinfo.storage;

import android.R;
import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.deviceinfo.StorageItemPreference;
import com.android.car.developeroptions.deviceinfo.StorageProfileFragment;
import com.android.car.developeroptions.deviceinfo.storage.StorageAsyncLoader;
import com.android.car.developeroptions.deviceinfo.storage.UserIconLoader;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class UserProfileController extends AbstractPreferenceController implements PreferenceControllerMixin, StorageAsyncLoader.ResultHandler, UserIconLoader.UserIconHandler {
    private final int mPreferenceOrder;
    private StorageItemPreference mStoragePreference;
    private long mTotalSizeBytes;
    private UserInfo mUser;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public UserProfileController(Context context, UserInfo userInfo, int i) {
        super(context);
        Objects.requireNonNull(userInfo);
        this.mUser = userInfo;
        this.mPreferenceOrder = i;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "pref_profile_" + this.mUser.id;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        StorageItemPreference storageItemPreference = new StorageItemPreference(preferenceScreen.getContext());
        this.mStoragePreference = storageItemPreference;
        storageItemPreference.setOrder(this.mPreferenceOrder);
        this.mStoragePreference.setKey("pref_profile_" + this.mUser.id);
        this.mStoragePreference.setTitle(this.mUser.name);
        preferenceScreen.addPreference(this.mStoragePreference);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (preference == null || this.mStoragePreference != preference) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("userId", this.mUser.id);
        bundle.putString("android.os.storage.extra.VOLUME_ID", "private");
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(StorageProfileFragment.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleText(this.mUser.name);
        subSettingLauncher.setSourceMetricsCategory(42);
        subSettingLauncher.launch();
        return true;
    }

    @Override // com.android.car.developeroptions.deviceinfo.storage.StorageAsyncLoader.ResultHandler
    public void handleResult(SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        Objects.requireNonNull(sparseArray);
        StorageAsyncLoader.AppsStorageResult appsStorageResult = sparseArray.get(this.mUser.id);
        if (appsStorageResult != null) {
            setSize(appsStorageResult.externalStats.totalBytes + appsStorageResult.otherAppsSize + appsStorageResult.videoAppsSize + appsStorageResult.musicAppsSize + appsStorageResult.gamesSize, this.mTotalSizeBytes);
        }
    }

    public void setSize(long j, long j2) {
        StorageItemPreference storageItemPreference = this.mStoragePreference;
        if (storageItemPreference != null) {
            storageItemPreference.setStorageSize(j, j2);
        }
    }

    @Override // com.android.car.developeroptions.deviceinfo.storage.UserIconLoader.UserIconHandler
    public void handleUserIcons(SparseArray<Drawable> sparseArray) {
        Drawable drawable = sparseArray.get(this.mUser.id);
        if (drawable != null) {
            this.mStoragePreference.setIcon(applyTint(this.mContext, drawable));
        }
    }

    private static Drawable applyTint(Context context, Drawable drawable) {
        Drawable drawableMutate = drawable.mutate();
        drawableMutate.setTintList(Utils.getColorAttr(context, R.attr.colorControlNormal));
        return drawableMutate;
    }
}
