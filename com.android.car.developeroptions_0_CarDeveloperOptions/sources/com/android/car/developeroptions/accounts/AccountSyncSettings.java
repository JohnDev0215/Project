package com.android.car.developeroptions.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SyncAdapterType;
import android.content.SyncInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.UserInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.widget.LayoutPreference;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AccountSyncSettings extends AccountPreferenceBase {
    private Account mAccount;
    private ArrayList<SyncAdapterType> mInvisibleAdapters = Lists.newArrayList();

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        return i != 102 ? 0 : 587;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_accounts;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 9;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (i != 102) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.cant_sync_dialog_title);
        builder.setMessage(R.string.cant_sync_dialog_message);
        builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    @Override // com.android.car.developeroptions.accounts.AccountPreferenceBase, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.account_sync_settings);
        getPreferenceScreen().setOrderingAsAdded(false);
        setAccessibilityTitle();
    }

    @Override // com.android.car.developeroptions.accounts.AccountPreferenceBase, com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            Log.e("AccountPreferenceBase", "No arguments provided when starting intent. ACCOUNT_KEY needed.");
            finish();
            return;
        }
        Account account = (Account) arguments.getParcelable("account");
        this.mAccount = account;
        if (!accountExists(account)) {
            Log.e("AccountPreferenceBase", "Account provided does not exist: " + this.mAccount);
            finish();
            return;
        }
        if (Log.isLoggable("AccountPreferenceBase", 2)) {
            Log.v("AccountPreferenceBase", "Got account: " + this.mAccount);
        }
        FragmentActivity activity = getActivity();
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(activity, this, null);
        entityHeaderControllerNewInstance.setRecyclerView(getListView(), getSettingsLifecycle());
        entityHeaderControllerNewInstance.setIcon(getDrawableForType(this.mAccount.type));
        entityHeaderControllerNewInstance.setLabel(this.mAccount.name);
        entityHeaderControllerNewInstance.setSummary(getLabelForType(this.mAccount.type));
        LayoutPreference layoutPreferenceDone = entityHeaderControllerNewInstance.done(activity, getPrefContext());
        layoutPreferenceDone.setOrder(0);
        getPreferenceScreen().addPreference(layoutPreferenceDone);
    }

    private void setAccessibilityTitle() {
        UserInfo userInfo = ((UserManager) getSystemService("user")).getUserInfo(this.mUserHandle.getIdentifier());
        boolean zIsManagedProfile = userInfo != null ? userInfo.isManagedProfile() : false;
        CharSequence title = getActivity().getTitle();
        getActivity().setTitle(Utils.createAccessibleSequence(title, getString(zIsManagedProfile ? R.string.accessibility_work_account_title : R.string.accessibility_personal_account_title, title)));
    }

    @Override // com.android.car.developeroptions.accounts.AccountPreferenceBase, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        this.mAuthenticatorHelper.listenToAccountUpdates();
        updateAuthDescriptions();
        onAccountsUpdate(Binder.getCallingUserHandle());
        super.onResume();
    }

    @Override // com.android.car.developeroptions.accounts.AccountPreferenceBase, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mAuthenticatorHelper.stopListeningToAccountUpdates();
    }

    private void addSyncStateSwitch(Account account, String str, String str2, int i) {
        SyncStateSwitchPreference syncStateSwitchPreference = (SyncStateSwitchPreference) getCachedPreference(str);
        if (syncStateSwitchPreference == null) {
            syncStateSwitchPreference = new SyncStateSwitchPreference(getPrefContext(), account, str, str2, i);
            getPreferenceScreen().addPreference(syncStateSwitchPreference);
        } else {
            syncStateSwitchPreference.setup(account, str, str2, i);
        }
        PackageManager packageManager = getPackageManager();
        syncStateSwitchPreference.setPersistent(false);
        ProviderInfo providerInfoResolveContentProviderAsUser = packageManager.resolveContentProviderAsUser(str, 0, this.mUserHandle.getIdentifier());
        if (providerInfoResolveContentProviderAsUser == null) {
            return;
        }
        CharSequence charSequenceLoadLabel = providerInfoResolveContentProviderAsUser.loadLabel(packageManager);
        if (TextUtils.isEmpty(charSequenceLoadLabel)) {
            Log.e("AccountPreferenceBase", "Provider needs a label for authority '" + str + "'");
            return;
        }
        syncStateSwitchPreference.setTitle(charSequenceLoadLabel);
        syncStateSwitchPreference.setKey(str);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem icon = menu.add(0, 1, 0, getString(R.string.sync_menu_sync_now)).setIcon(R.drawable.ic_menu_refresh_holo_dark);
        MenuItem icon2 = menu.add(0, 2, 0, getString(R.string.sync_menu_sync_cancel)).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        icon.setShowAsAction(4);
        icon2.setShowAsAction(4);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        boolean z = !ContentResolver.getCurrentSyncsAsUser(this.mUserHandle.getIdentifier()).isEmpty();
        menu.findItem(1).setVisible(!z);
        menu.findItem(2).setVisible(z);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            startSyncForEnabledProviders();
            return true;
        }
        if (itemId == 2) {
            cancelSyncForEnabledProviders();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            int preferenceCount = getPreferenceScreen().getPreferenceCount();
            for (int i3 = 0; i3 < preferenceCount; i3++) {
                Preference preference = getPreferenceScreen().getPreference(i3);
                if (preference instanceof SyncStateSwitchPreference) {
                    SyncStateSwitchPreference syncStateSwitchPreference = (SyncStateSwitchPreference) preference;
                    if (syncStateSwitchPreference.getUid() == i) {
                        onPreferenceTreeClick(syncStateSwitchPreference);
                        return;
                    }
                }
            }
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (getActivity() == null) {
            return false;
        }
        if (preference instanceof SyncStateSwitchPreference) {
            SyncStateSwitchPreference syncStateSwitchPreference = (SyncStateSwitchPreference) preference;
            String authority = syncStateSwitchPreference.getAuthority();
            if (TextUtils.isEmpty(authority)) {
                return false;
            }
            Account account = syncStateSwitchPreference.getAccount();
            int identifier = this.mUserHandle.getIdentifier();
            String packageName = syncStateSwitchPreference.getPackageName();
            boolean syncAutomaticallyAsUser = ContentResolver.getSyncAutomaticallyAsUser(account, authority, identifier);
            if (syncStateSwitchPreference.isOneTimeSyncMode()) {
                if (requestAccountAccessIfNeeded(packageName)) {
                    return true;
                }
                requestOrCancelSync(account, authority, true);
            } else {
                boolean zIsChecked = syncStateSwitchPreference.isChecked();
                if (zIsChecked == syncAutomaticallyAsUser || (zIsChecked && requestAccountAccessIfNeeded(packageName))) {
                    return true;
                }
                ContentResolver.setSyncAutomaticallyAsUser(account, authority, zIsChecked, identifier);
                if (!ContentResolver.getMasterSyncAutomaticallyAsUser(identifier) || !zIsChecked) {
                    requestOrCancelSync(account, authority, zIsChecked);
                }
            }
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    private boolean requestAccountAccessIfNeeded(String str) {
        IntentSender intentSenderCreateRequestAccountAccessIntentSenderAsUser;
        if (str == null) {
            return false;
        }
        try {
            int packageUidAsUser = getContext().getPackageManager().getPackageUidAsUser(str, this.mUserHandle.getIdentifier());
            AccountManager accountManager = (AccountManager) getContext().getSystemService(AccountManager.class);
            if (!accountManager.hasAccountAccess(this.mAccount, str, this.mUserHandle) && (intentSenderCreateRequestAccountAccessIntentSenderAsUser = accountManager.createRequestAccountAccessIntentSenderAsUser(this.mAccount, str, this.mUserHandle)) != null) {
                try {
                    startIntentSenderForResult(intentSenderCreateRequestAccountAccessIntentSenderAsUser, packageUidAsUser, null, 0, 0, 0, null);
                    return true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("AccountPreferenceBase", "Error requesting account access", e);
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("AccountPreferenceBase", "Invalid sync ", e2);
            return false;
        }
    }

    private void startSyncForEnabledProviders() {
        requestOrCancelSyncForEnabledProviders(true);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    private void cancelSyncForEnabledProviders() {
        requestOrCancelSyncForEnabledProviders(false);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    private void requestOrCancelSyncForEnabledProviders(boolean z) {
        int preferenceCount = getPreferenceScreen().getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = getPreferenceScreen().getPreference(i);
            if (preference instanceof SyncStateSwitchPreference) {
                SyncStateSwitchPreference syncStateSwitchPreference = (SyncStateSwitchPreference) preference;
                if (syncStateSwitchPreference.isChecked()) {
                    requestOrCancelSync(syncStateSwitchPreference.getAccount(), syncStateSwitchPreference.getAuthority(), z);
                }
            }
        }
        if (this.mAccount != null) {
            Iterator<SyncAdapterType> it = this.mInvisibleAdapters.iterator();
            while (it.hasNext()) {
                requestOrCancelSync(this.mAccount, it.next().authority, z);
            }
        }
    }

    private void requestOrCancelSync(Account account, String str, boolean z) {
        if (z) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("force", true);
            ContentResolver.requestSyncAsUser(account, str, this.mUserHandle.getIdentifier(), bundle);
            return;
        }
        ContentResolver.cancelSyncAsUser(account, str, this.mUserHandle.getIdentifier());
    }

    private boolean isSyncing(List<SyncInfo> list, Account account, String str) {
        for (SyncInfo syncInfo : list) {
            if (syncInfo.account.equals(account) && syncInfo.authority.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.developeroptions.accounts.AccountPreferenceBase
    /* JADX INFO: renamed from: onSyncStateUpdated */
    public void lambda$new$0() {
        if (isResumed()) {
            setFeedsState();
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.invalidateOptionsMenu();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x012c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setFeedsState() {
        /*
            Method dump skipped, instruction units count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.accounts.AccountSyncSettings.setFeedsState():void");
    }

    @Override // com.android.car.developeroptions.accounts.AccountPreferenceBase, com.android.settingslib.accounts.AuthenticatorHelper.OnAccountsUpdateListener
    public void onAccountsUpdate(UserHandle userHandle) {
        super.onAccountsUpdate(userHandle);
        if (!accountExists(this.mAccount)) {
            finish();
        } else {
            updateAccountSwitches();
            lambda$new$0();
        }
    }

    private boolean accountExists(Account account) {
        if (account == null) {
            return false;
        }
        for (Account account2 : AccountManager.get(getActivity()).getAccountsByTypeAsUser(account.type, this.mUserHandle)) {
            if (account2.equals(account)) {
                return true;
            }
        }
        return false;
    }

    private void updateAccountSwitches() {
        this.mInvisibleAdapters.clear();
        SyncAdapterType[] syncAdapterTypesAsUser = ContentResolver.getSyncAdapterTypesAsUser(this.mUserHandle.getIdentifier());
        ArrayList arrayList = new ArrayList();
        for (SyncAdapterType syncAdapterType : syncAdapterTypesAsUser) {
            if (syncAdapterType.accountType.equals(this.mAccount.type)) {
                if (syncAdapterType.isUserVisible()) {
                    if (Log.isLoggable("AccountPreferenceBase", 3)) {
                        Log.d("AccountPreferenceBase", "updateAccountSwitches: added authority " + syncAdapterType.authority + " to accountType " + syncAdapterType.accountType);
                    }
                    arrayList.add(syncAdapterType);
                } else {
                    this.mInvisibleAdapters.add(syncAdapterType);
                }
            }
        }
        if (Log.isLoggable("AccountPreferenceBase", 3)) {
            Log.d("AccountPreferenceBase", "looking for sync adapters that match account " + this.mAccount);
        }
        cacheRemoveAllPrefs(getPreferenceScreen());
        getCachedPreference("pref_app_header");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            SyncAdapterType syncAdapterType2 = (SyncAdapterType) arrayList.get(i);
            int isSyncableAsUser = ContentResolver.getIsSyncableAsUser(this.mAccount, syncAdapterType2.authority, this.mUserHandle.getIdentifier());
            if (Log.isLoggable("AccountPreferenceBase", 3)) {
                Log.d("AccountPreferenceBase", "  found authority " + syncAdapterType2.authority + " " + isSyncableAsUser);
            }
            if (isSyncableAsUser > 0) {
                try {
                    addSyncStateSwitch(this.mAccount, syncAdapterType2.authority, syncAdapterType2.getPackageName(), getContext().getPackageManager().getPackageUidAsUser(syncAdapterType2.getPackageName(), this.mUserHandle.getIdentifier()));
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("AccountPreferenceBase", "No uid for package" + syncAdapterType2.getPackageName(), e);
                }
            }
        }
        removeCachedPrefs(getPreferenceScreen());
    }

    private static String formatSyncDate(Context context, Date date) {
        return DateUtils.formatDateTime(context, date.getTime(), 21);
    }
}
