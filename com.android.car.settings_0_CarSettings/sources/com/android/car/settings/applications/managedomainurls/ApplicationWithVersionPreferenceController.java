package com.android.car.settings.applications.managedomainurls;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.applications.ApplicationPreferenceController;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationWithVersionPreferenceController extends ApplicationPreferenceController {
    public ApplicationWithVersionPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.applications.ApplicationPreferenceController, com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        super.updateState(preference);
        preference.setSummary(getAppVersion());
    }
}
