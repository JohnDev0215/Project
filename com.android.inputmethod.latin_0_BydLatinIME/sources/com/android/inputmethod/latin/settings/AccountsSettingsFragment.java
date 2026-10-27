package com.android.inputmethod.latin.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.TwoStatePreference;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.ListView;
import android.widget.TextView;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.accounts.AccountStateChangedListener;
import com.android.inputmethod.latin.accounts.LoginAccountUtils;
import com.android.inputmethod.latin.utils.ManagedProfileUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class AccountsSettingsFragment extends SubScreenFragment {
    static final String PREF_ACCCOUNT_SWITCHER = "account_switcher";
    private static final String PREF_CLEAR_SYNC_DATA = "pref_clear_sync_data";
    private static final String PREF_ENABLE_SYNC_NOW = "pref_enable_cloud_sync";
    private static final String PREF_SYNC_NOW = "pref_sync_now";
    private Preference mAccountSwitcher;
    private Preference mClearSyncDataPreference;
    private TwoStatePreference mEnableSyncPreference;
    private Preference mSyncNowPreference;
    private final Preference.OnPreferenceClickListener mSyncNowListener = new SyncNowListener();
    private final Preference.OnPreferenceClickListener mDeleteSyncDataListener = new DeleteSyncDataListener();
    private final Preference.OnPreferenceClickListener mEnableSyncClickListener = new EnableSyncClickListener();
    private AtomicBoolean mManagedProfileBeingDetected = new AtomicBoolean(true);
    private AtomicBoolean mHasManagedProfile = new AtomicBoolean(false);

    private void disableSyncPreferences() {
    }

    private void enableSyncPreferences(final String[] accountsForLogin, final String currentAccountName) {
    }

    private void refreshSyncSettingsMessaging(boolean hasAccountsPermission, boolean managedProfileBeingDetected, boolean hasManagedProfile, boolean hasAccountsForLogin, String currentAccount) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSyncSettingsUI() {
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_accounts);
        this.mAccountSwitcher = findPreference(PREF_ACCCOUNT_SWITCHER);
        this.mEnableSyncPreference = (TwoStatePreference) findPreference("pref_enable_cloud_sync");
        this.mSyncNowPreference = findPreference(PREF_SYNC_NOW);
        this.mClearSyncDataPreference = findPreference(PREF_CLEAR_SYNC_DATA);
        removePreference(Settings.PREF_ENABLE_METRICS_LOGGING);
        removeSyncPreferences();
    }

    private static class ManagedProfileCheckerTask extends AsyncTask<Void, Void, Boolean> {
        private final AccountsSettingsFragment mFragment;

        private ManagedProfileCheckerTask(final AccountsSettingsFragment fragment) {
            this.mFragment = fragment;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.mFragment.mManagedProfileBeingDetected.set(true);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... params) {
            return Boolean.valueOf(ManagedProfileUtils.getInstance().hasWorkProfile(this.mFragment.getActivity()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Boolean hasWorkProfile) {
            this.mFragment.mHasManagedProfile.set(hasWorkProfile.booleanValue());
            this.mFragment.mManagedProfileBeingDetected.set(false);
            this.mFragment.refreshSyncSettingsUI();
        }
    }

    /* JADX INFO: renamed from: com.android.inputmethod.latin.settings.AccountsSettingsFragment$1, reason: invalid class name */
    class AnonymousClass1 implements Preference.OnPreferenceClickListener {
        final /* synthetic */ String[] val$accountsForLogin;

        AnonymousClass1(final String[] val$accountsForLogin) {
            this.val$accountsForLogin = val$accountsForLogin;
        }

        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(final Preference preference) {
            String[] strArr = this.val$accountsForLogin;
            if (strArr.length <= 0) {
                return true;
            }
            AccountsSettingsFragment accountsSettingsFragment = AccountsSettingsFragment.this;
            accountsSettingsFragment.createAccountPicker(strArr, accountsSettingsFragment.getSignedInAccountName(), AccountsSettingsFragment.this.new AccountChangedListener(null)).show();
            return true;
        }
    }

    private void removeSyncPreferences() {
        removePreference(PREF_ACCCOUNT_SWITCHER);
        removePreference("pref_enable_cloud_sync");
        removePreference(PREF_SYNC_NOW);
        removePreference(PREF_CLEAR_SYNC_DATA);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        refreshSyncSettingsUI();
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
        if (TextUtils.equals(key, LocalSettingsConstants.PREF_ACCOUNT_NAME)) {
            refreshSyncSettingsUI();
            return;
        }
        if (TextUtils.equals(key, "pref_enable_cloud_sync")) {
            this.mEnableSyncPreference = (TwoStatePreference) findPreference("pref_enable_cloud_sync");
            boolean z = prefs.getBoolean("pref_enable_cloud_sync", false);
            if (isSyncEnabled()) {
                this.mEnableSyncPreference.setSummary(getString(R.string.cloud_sync_summary));
            } else {
                this.mEnableSyncPreference.setSummary(getString(R.string.cloud_sync_summary_disabled));
            }
            AccountStateChangedListener.onSyncPreferenceChanged(getSignedInAccountName(), z);
        }
    }

    @Nullable
    String getSignedInAccountName() {
        return getSharedPreferences().getString(LocalSettingsConstants.PREF_ACCOUNT_NAME, null);
    }

    boolean isSyncEnabled() {
        return getSharedPreferences().getBoolean("pref_enable_cloud_sync", false);
    }

    AlertDialog createAccountPicker(final String[] accounts, final String selectedAccount, final DialogInterface.OnClickListener positiveButtonClickListener) {
        boolean z;
        if (accounts == null || accounts.length == 0) {
            throw new IllegalArgumentException("List of accounts must not be empty");
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= accounts.length) {
                z = false;
                break;
            }
            if (TextUtils.equals(accounts[i2], selectedAccount)) {
                z = true;
                i = i2;
                break;
            }
            i2++;
        }
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(getActivity()).setTitle(R.string.account_select_title).setSingleChoiceItems(accounts, i, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.account_select_ok, positiveButtonClickListener).setNegativeButton(R.string.account_select_cancel, (DialogInterface.OnClickListener) null);
        if (z) {
            negativeButton.setNeutralButton(R.string.account_select_sign_out, positiveButtonClickListener);
        }
        return negativeButton.create();
    }

    class AccountChangedListener implements DialogInterface.OnClickListener {
        private TwoStatePreference mDependentPreference;

        AccountChangedListener(final TwoStatePreference dependentPreference) {
            this.mDependentPreference = dependentPreference;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int which) {
            String signedInAccountName = AccountsSettingsFragment.this.getSignedInAccountName();
            if (which == -3) {
                AccountStateChangedListener.onAccountSignedOut(signedInAccountName);
                AccountsSettingsFragment.this.getSharedPreferences().edit().remove(LocalSettingsConstants.PREF_ACCOUNT_NAME).apply();
            } else {
                if (which != -1) {
                    return;
                }
                ListView listView = ((AlertDialog) dialog).getListView();
                String str = (String) listView.getItemAtPosition(listView.getCheckedItemPosition());
                AccountsSettingsFragment.this.getSharedPreferences().edit().putString(LocalSettingsConstants.PREF_ACCOUNT_NAME, str).apply();
                AccountStateChangedListener.onAccountSignedIn(signedInAccountName, str);
                TwoStatePreference twoStatePreference = this.mDependentPreference;
                if (twoStatePreference != null) {
                    twoStatePreference.setChecked(true);
                }
            }
        }
    }

    class SyncNowListener implements Preference.OnPreferenceClickListener {
        SyncNowListener() {
        }

        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(final Preference preference) {
            AccountStateChangedListener.forceSync(AccountsSettingsFragment.this.getSignedInAccountName());
            return true;
        }
    }

    class DeleteSyncDataListener implements Preference.OnPreferenceClickListener {
        DeleteSyncDataListener() {
        }

        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(final Preference preference) {
            new AlertDialog.Builder(AccountsSettingsFragment.this.getActivity()).setTitle(R.string.clear_sync_data_title).setMessage(R.string.clear_sync_data_confirmation).setPositiveButton(R.string.clear_sync_data_ok, new DialogInterface.OnClickListener() { // from class: com.android.inputmethod.latin.settings.AccountsSettingsFragment.DeleteSyncDataListener.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialog, final int which) {
                    if (which == -1) {
                        AccountStateChangedListener.forceDelete(AccountsSettingsFragment.this.getSignedInAccountName());
                    }
                }
            }).setNegativeButton(R.string.cloud_sync_cancel, (DialogInterface.OnClickListener) null).create().show();
            return true;
        }
    }

    class EnableSyncClickListener implements DialogInterface.OnShowListener, Preference.OnPreferenceClickListener {
        EnableSyncClickListener() {
        }

        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(final Preference preference) {
            final TwoStatePreference twoStatePreference = (TwoStatePreference) preference;
            if (!twoStatePreference.isChecked()) {
                return true;
            }
            twoStatePreference.setChecked(false);
            AlertDialog alertDialogCreate = new AlertDialog.Builder(AccountsSettingsFragment.this.getActivity()).setTitle(R.string.cloud_sync_title).setMessage(R.string.cloud_sync_opt_in_text).setPositiveButton(R.string.account_select_ok, new DialogInterface.OnClickListener() { // from class: com.android.inputmethod.latin.settings.AccountsSettingsFragment.EnableSyncClickListener.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialog, final int which) {
                    if (which == -1) {
                        AccountsSettingsFragment.this.createAccountPicker(LoginAccountUtils.getAccountsForLogin(AccountsSettingsFragment.this.getActivity()), AccountsSettingsFragment.this.getSignedInAccountName(), AccountsSettingsFragment.this.new AccountChangedListener(twoStatePreference)).show();
                    }
                }
            }).setNegativeButton(R.string.cloud_sync_cancel, (DialogInterface.OnClickListener) null).create();
            alertDialogCreate.setOnShowListener(this);
            alertDialogCreate.show();
            return true;
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialog) {
            TextView textView = (TextView) ((AlertDialog) dialog).findViewById(android.R.id.message);
            if (textView != null) {
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
    }
}
