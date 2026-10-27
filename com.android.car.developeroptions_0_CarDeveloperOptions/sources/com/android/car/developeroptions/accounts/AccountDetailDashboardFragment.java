package com.android.car.developeroptions.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.settingslib.accounts.AuthenticatorHelper;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.drawer.Tile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AccountDetailDashboardFragment extends DashboardFragment {
    Account mAccount;
    private String mAccountLabel;
    private AccountSyncPreferenceController mAccountSynController;
    String mAccountType;
    private RemoveAccountPreferenceController mRemoveAccountController;
    UserHandle mUserHandle;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_account_detail;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AccountDetailDashboard";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 8;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.account_type_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getPreferenceManager().setPreferenceComparisonCallback(null);
        Bundle arguments = getArguments();
        FragmentActivity activity = getActivity();
        this.mUserHandle = Utils.getSecureTargetUser(activity.getActivityToken(), (UserManager) getSystemService("user"), arguments, activity.getIntent().getExtras());
        if (arguments != null) {
            if (arguments.containsKey("account")) {
                this.mAccount = (Account) arguments.getParcelable("account");
            }
            if (arguments.containsKey("account_label")) {
                this.mAccountLabel = arguments.getString("account_label");
            }
            if (arguments.containsKey("account_type")) {
                this.mAccountType = arguments.getString("account_type");
            }
        }
        this.mAccountSynController.init(this.mAccount, this.mUserHandle);
        this.mRemoveAccountController.init(this.mAccount, this.mUserHandle);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mAccountLabel != null) {
            getActivity().setTitle(this.mAccountLabel);
        }
        updateUi();
    }

    void finishIfAccountMissing() {
        Context context = getContext();
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        AccountManager accountManager = (AccountManager) context.getSystemService(AccountManager.class);
        Iterator<UserHandle> it = userManager.getUserProfiles().iterator();
        while (it.hasNext()) {
            for (Account account : accountManager.getAccountsAsUser(it.next().getIdentifier())) {
                if (account.equals(this.mAccount)) {
                    return;
                }
            }
        }
        finish();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        finishIfAccountMissing();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        AccountSyncPreferenceController accountSyncPreferenceController = new AccountSyncPreferenceController(context);
        this.mAccountSynController = accountSyncPreferenceController;
        arrayList.add(accountSyncPreferenceController);
        RemoveAccountPreferenceController removeAccountPreferenceController = new RemoveAccountPreferenceController(context, this);
        this.mRemoveAccountController = removeAccountPreferenceController;
        arrayList.add(removeAccountPreferenceController);
        arrayList.add(new AccountHeaderPreferenceController(context, getSettingsLifecycle(), getActivity(), this, getArguments()));
        return arrayList;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected boolean displayTile(Tile tile) {
        Bundle metaData;
        if (this.mAccountType == null || (metaData = tile.getMetaData()) == null) {
            return false;
        }
        boolean zEquals = this.mAccountType.equals(metaData.getString("com.android.car.developeroptions.ia.account"));
        if (zEquals) {
            Intent intent = tile.getIntent();
            intent.putExtra("extra.accountName", this.mAccount.name);
            intent.putExtra("android.intent.extra.USER", this.mUserHandle);
        }
        return zEquals;
    }

    void updateUi() {
        Context context = getContext();
        Bundle arguments = getArguments();
        UserHandle userHandle = (arguments == null || !arguments.containsKey("user_handle")) ? null : (UserHandle) arguments.getParcelable("user_handle");
        AccountTypePreferenceLoader accountTypePreferenceLoader = new AccountTypePreferenceLoader(this, new AuthenticatorHelper(context, userHandle, null), userHandle);
        PreferenceScreen preferenceScreenAddPreferencesForType = accountTypePreferenceLoader.addPreferencesForType(this.mAccountType, getPreferenceScreen());
        if (preferenceScreenAddPreferencesForType != null) {
            accountTypePreferenceLoader.updatePreferenceIntents(preferenceScreenAddPreferencesForType, this.mAccountType, this.mAccount);
        }
    }
}
