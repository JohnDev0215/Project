package com.android.car.settings.accounts;

import android.accounts.Account;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.accounts.AuthenticatorHelper;

/* JADX INFO: loaded from: classes5.dex */
public class AccountDetailsPreferenceController extends PreferenceController<Preference> {
    private static final Logger LOG = new Logger((Class<?>) AccountDetailsPreferenceController.class);
    private Account mAccount;
    private UserHandle mUserHandle;

    public AccountDetailsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    public void setAccount(Account account) {
        this.mAccount = account;
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public void setUserHandle(UserHandle userHandle) {
        this.mUserHandle = userHandle;
    }

    public UserHandle getUserHandle() {
        return this.mUserHandle;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        LOG.v("checkInitialized");
        if (this.mAccount == null) {
            throw new IllegalStateException("AccountDetailsPreferenceController must be initialized by calling setAccount(Account)");
        }
        if (this.mUserHandle == null) {
            throw new IllegalStateException("AccountDetailsPreferenceController must be initialized by calling setUserHandle(UserHandle)");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setTitle(this.mAccount.name);
        AuthenticatorHelper helper = new AuthenticatorHelper(getContext(), this.mUserHandle, null);
        preference.setIcon(helper.getDrawableForType(getContext(), this.mAccount.type));
    }
}
