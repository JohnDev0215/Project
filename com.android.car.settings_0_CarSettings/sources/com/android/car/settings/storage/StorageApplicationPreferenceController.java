package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.applications.ApplicationPreferenceController;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class StorageApplicationPreferenceController extends ApplicationPreferenceController {
    public StorageApplicationPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.applications.ApplicationPreferenceController, com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setTitle(getAppName());
        preference.setIcon(getAppIcon());
        preference.setSummary(getAppVersion());
    }
}
