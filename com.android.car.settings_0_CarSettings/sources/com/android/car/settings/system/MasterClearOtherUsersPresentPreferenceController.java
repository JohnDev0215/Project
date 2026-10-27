package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.users.UserHelper;

/* JADX INFO: loaded from: classes5.dex */
public class MasterClearOtherUsersPresentPreferenceController extends PreferenceController<Preference> {
    public MasterClearOtherUsersPresentPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        UserHelper userHelper = UserHelper.getInstance(getContext());
        preference.setVisible(!userHelper.getAllSwitchableUsers().isEmpty());
    }
}
