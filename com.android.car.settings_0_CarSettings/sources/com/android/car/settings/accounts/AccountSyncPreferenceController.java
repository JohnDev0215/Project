package com.android.car.settings.accounts;

import android.accounts.Account;
import android.car.drivingstate.CarUxRestrictions;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncAdapterType;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class AccountSyncPreferenceController extends PreferenceController<Preference> {
    private static final Logger LOG = new Logger((Class<?>) AccountSyncPreferenceController.class);
    private Account mAccount;
    private UserHandle mUserHandle;

    public AccountSyncPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    public void setAccount(Account account) {
        this.mAccount = account;
    }

    public void setUserHandle(UserHandle userHandle) {
        this.mUserHandle = userHandle;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        LOG.v("checkInitialized");
        if (this.mAccount == null) {
            throw new IllegalStateException("AccountSyncPreferenceController must be initialized by calling setAccount(Account)");
        }
        if (this.mUserHandle == null) {
            throw new IllegalStateException("AccountSyncPreferenceController must be initialized by calling setUserHandle(UserHandle)");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(getSummary());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        getFragmentController().launchFragment(AccountSyncDetailsFragment.newInstance(this.mAccount, this.mUserHandle));
        return true;
    }

    private CharSequence getSummary() {
        int userId = this.mUserHandle.getIdentifier();
        SyncAdapterType[] syncAdapters = ContentResolver.getSyncAdapterTypesAsUser(userId);
        int total = 0;
        int enabled = 0;
        if (syncAdapters != null) {
            for (SyncAdapterType sa : syncAdapters) {
                if (sa.accountType.equals(this.mAccount.type) && sa.isUserVisible()) {
                    int syncState = ContentResolver.getIsSyncableAsUser(this.mAccount, sa.authority, userId);
                    if (syncState > 0) {
                        total++;
                        boolean syncEnabled = ContentResolver.getSyncAutomaticallyAsUser(this.mAccount, sa.authority, userId);
                        boolean oneTimeSyncMode = !ContentResolver.getMasterSyncAutomaticallyAsUser(userId);
                        if (oneTimeSyncMode || syncEnabled) {
                            enabled++;
                        }
                    }
                }
            }
        }
        if (enabled == 0) {
            return getContext().getText(R.string.account_sync_summary_all_off);
        }
        return enabled == total ? getContext().getText(R.string.account_sync_summary_all_on) : getContext().getString(R.string.account_sync_summary_some_on, Integer.valueOf(enabled), Integer.valueOf(total));
    }
}
