package com.android.car.settings.accounts;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.users.UserHelper;

/* JADX INFO: loaded from: classes5.dex */
public class AccountsEntryPreferenceController extends PreferenceController<Preference> {
    public AccountsEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    public int getAvailabilityStatus() {
        boolean canModifyAccounts = UserHelper.getInstance(getContext()).canCurrentProcessModifyAccounts();
        return canModifyAccounts ? 0 : 3;
    }
}
