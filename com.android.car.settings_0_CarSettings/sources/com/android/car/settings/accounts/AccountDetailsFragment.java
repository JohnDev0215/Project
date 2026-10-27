package com.android.car.settings.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.fragment.app.Fragment;
import com.android.car.settings.R;
import com.android.car.settings.common.ErrorDialog;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.users.UserHelper;
import com.android.car.ui.AlertDialogBuilder;
import com.android.car.ui.preference.CarUiDialogFragment;
import com.android.car.ui.toolbar.MenuItem;
import com.android.settingslib.accounts.AuthenticatorHelper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AccountDetailsFragment extends SettingsFragment implements AuthenticatorHelper.OnAccountsUpdateListener {
    public static final String EXTRA_ACCOUNT = "extra_account";
    public static final String EXTRA_ACCOUNT_LABEL = "extra_account_label";
    public static final String EXTRA_USER_INFO = "extra_user_info";
    private Account mAccount;
    private AuthenticatorHelper mAuthenticatorHelper;
    private MenuItem mRemoveButton;
    private UserInfo mUserInfo;

    public static AccountDetailsFragment newInstance(Account account, CharSequence label, UserInfo userInfo) {
        AccountDetailsFragment accountDetailsFragment = new AccountDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ACCOUNT, account);
        bundle.putCharSequence(EXTRA_ACCOUNT_LABEL, label);
        bundle.putParcelable(EXTRA_USER_INFO, userInfo);
        accountDetailsFragment.setArguments(bundle);
        return accountDetailsFragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.account_details_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mRemoveButton);
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAccount = (Account) getArguments().getParcelable(EXTRA_ACCOUNT);
        this.mUserInfo = getArguments().getParcelable(EXTRA_USER_INFO);
        ((AccountDetailsPreferenceController) use(AccountDetailsPreferenceController.class, R.string.pk_account_details)).setAccount(this.mAccount);
        ((AccountDetailsPreferenceController) use(AccountDetailsPreferenceController.class, R.string.pk_account_details)).setUserHandle(this.mUserInfo.getUserHandle());
        ((AccountSyncPreferenceController) use(AccountSyncPreferenceController.class, R.string.pk_account_sync)).setAccount(this.mAccount);
        ((AccountDetailsSettingController) use(AccountDetailsSettingController.class, R.string.pk_account_settings)).setAccount(this.mAccount);
        ((AccountSyncPreferenceController) use(AccountSyncPreferenceController.class, R.string.pk_account_sync)).setUserHandle(this.mUserInfo.getUserHandle());
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean canModifyAccount = UserHelper.getInstance(getContext()).canCurrentProcessModifyAccounts();
        this.mRemoveButton = new MenuItem.Builder(getContext()).setTitle(R.string.remove_button).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountDetailsFragment$uIjfwXjfRs7trRd3LjcOedYxnLw
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$AccountDetailsFragment(menuItem);
            }
        }).setVisible(canModifyAccount).build();
    }

    public /* synthetic */ void lambda$onCreate$0$AccountDetailsFragment(MenuItem i) {
        onRemoveAccountClicked();
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getToolbar().setTitle(getArguments().getCharSequence(EXTRA_ACCOUNT_LABEL));
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AuthenticatorHelper authenticatorHelper = new AuthenticatorHelper(getContext(), this.mUserInfo.getUserHandle(), this);
        this.mAuthenticatorHelper = authenticatorHelper;
        authenticatorHelper.listenToAccountUpdates();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mAuthenticatorHelper.stopListeningToAccountUpdates();
    }

    @Override // com.android.settingslib.accounts.AuthenticatorHelper.OnAccountsUpdateListener
    public void onAccountsUpdate(UserHandle userHandle) {
        if (!accountExists()) {
            goBack();
        }
    }

    boolean accountExists() {
        if (this.mAccount == null) {
            return false;
        }
        Account[] accounts = AccountManager.get(getContext()).getAccountsByTypeAsUser(this.mAccount.type, this.mUserInfo.getUserHandle());
        return Arrays.asList(accounts).contains(this.mAccount);
    }

    private void onRemoveAccountClicked() {
        ConfirmRemoveAccountDialogFragment.show(this, this.mAccount, this.mUserInfo.getUserHandle());
    }

    public static class ConfirmRemoveAccountDialogFragment extends CarUiDialogFragment {
        private static final String DIALOG_TAG = "confirmRemoveAccount";
        private static final String KEY_ACCOUNT = "account";
        private static final Logger LOG = new Logger((Class<?>) ConfirmRemoveAccountDialogFragment.class);
        private Account mAccount;
        private final AccountManagerCallback<Bundle> mCallback = new AccountManagerCallback() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountDetailsFragment$ConfirmRemoveAccountDialogFragment$f5RW8aKebtURqHZpDM_2fjNSmmU
            @Override // android.accounts.AccountManagerCallback
            public final void run(AccountManagerFuture accountManagerFuture) {
                this.f$0.lambda$new$0$AccountDetailsFragment$ConfirmRemoveAccountDialogFragment(accountManagerFuture);
            }
        };
        private UserHandle mUserHandle;

        public /* synthetic */ void lambda$new$0$AccountDetailsFragment$ConfirmRemoveAccountDialogFragment(AccountManagerFuture future) {
            if (!getTargetFragment().isResumed()) {
                return;
            }
            boolean success = false;
            try {
                success = ((Bundle) future.getResult()).getBoolean("booleanResult");
            } catch (AuthenticatorException | OperationCanceledException | IOException e) {
                LOG.v("removeAccount error: " + e);
            }
            Activity activity = getTargetFragment().getActivity();
            if (!success && activity != null && !activity.isFinishing()) {
                ErrorDialog.show(getTargetFragment(), R.string.remove_account_error_title);
            } else {
                getTargetFragment().getFragmentManager().popBackStack();
            }
        }

        public static void show(Fragment parent, Account account, UserHandle userHandle) {
            ConfirmRemoveAccountDialogFragment dialog = new ConfirmRemoveAccountDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_ACCOUNT, account);
            bundle.putParcelable("android.intent.extra.USER", userHandle);
            dialog.setArguments(bundle);
            dialog.setTargetFragment(parent, 0);
            dialog.show(parent.getFragmentManager(), DIALOG_TAG);
        }

        @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle arguments = getArguments();
            this.mAccount = (Account) arguments.getParcelable(KEY_ACCOUNT);
            this.mUserHandle = (UserHandle) arguments.getParcelable("android.intent.extra.USER");
        }

        @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialogBuilder(getContext()).setTitle(R.string.really_remove_account_title).setMessage(R.string.really_remove_account_message).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.remove_account_title, this).create();
        }

        @Override // com.android.car.ui.preference.CarUiDialogFragment, android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            Activity activity = getTargetFragment().getActivity();
            AccountManager.get(activity).removeAccountAsUser(this.mAccount, activity, this.mCallback, null, this.mUserHandle);
            dialog.dismiss();
        }

        @Override // com.android.car.ui.preference.CarUiDialogFragment
        public void onDialogClosed(boolean positiveResult) {
        }
    }
}
