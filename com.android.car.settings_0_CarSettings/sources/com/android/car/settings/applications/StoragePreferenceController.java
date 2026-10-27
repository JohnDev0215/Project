package com.android.car.settings.applications;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.storage.AppStorageSettingsDetailsFragment;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes5.dex */
public class StoragePreferenceController extends PreferenceController<Preference> {
    private ApplicationsState.AppEntry mAppEntry;
    private String mPackageName;

    public StoragePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    public StoragePreferenceController setAppEntry(ApplicationsState.AppEntry appEntry) {
        this.mAppEntry = appEntry;
        return this;
    }

    public StoragePreferenceController setPackageName(String packageName) {
        this.mPackageName = packageName;
        return this;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mAppEntry == null || this.mPackageName == null) {
            throw new IllegalStateException("AppEntry and PackageName should be set before calling this function");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(getContext().getString(R.string.storage_type_internal, this.mAppEntry.sizeStr));
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        getFragmentController().launchFragment(AppStorageSettingsDetailsFragment.getInstance(this.mPackageName));
        return true;
    }
}
