package com.android.car.settings.accounts;

import android.accounts.Account;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import com.android.car.settings.common.ExtraSettingsPreferenceController;
import com.android.car.settings.common.FragmentController;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AccountDetailsSettingController extends ExtraSettingsPreferenceController {
    private static final String METADATA_IA_ACCOUNT = "com.android.settings.ia.account";
    private Account mAccount;

    public AccountDetailsSettingController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions restrictionInfo) {
        super(context, preferenceKey, fragmentController, restrictionInfo);
    }

    public void setAccount(Account account) {
        this.mAccount = account;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mAccount == null) {
            throw new IllegalStateException("AccountDetailsSettingController must be initialized by calling setAccount(Account)");
        }
    }

    @Override // com.android.car.settings.common.ExtraSettingsPreferenceController
    protected void addExtraSettings(Map<Preference, Bundle> preferenceBundleMap) {
        for (Preference setting : preferenceBundleMap.keySet()) {
            Account account = this.mAccount;
            if (account == null || account.type.equals(preferenceBundleMap.get(setting).getString(METADATA_IA_ACCOUNT))) {
                getPreference().addPreference(setting);
            }
        }
    }
}
