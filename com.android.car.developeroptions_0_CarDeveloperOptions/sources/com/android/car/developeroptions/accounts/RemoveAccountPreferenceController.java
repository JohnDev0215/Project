package com.android.car.developeroptions.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.widget.LayoutPreference;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class RemoveAccountPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, View.OnClickListener {
    private Account mAccount;
    private Fragment mParentFragment;
    private UserHandle mUserHandle;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "remove_account";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public RemoveAccountPreferenceController(Context context, Fragment fragment) {
        super(context);
        this.mParentFragment = fragment;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ((Button) ((LayoutPreference) preferenceScreen.findPreference("remove_account")).findViewById(R.id.button)).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced;
        UserHandle userHandle = this.mUserHandle;
        if (userHandle != null && (enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "no_modify_accounts", userHandle.getIdentifier())) != null) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this.mContext, enforcedAdminCheckIfRestrictionEnforced);
        } else {
            ConfirmRemoveAccountDialog.show(this.mParentFragment, this.mAccount, this.mUserHandle);
        }
    }

    public void init(Account account, UserHandle userHandle) {
        this.mAccount = account;
        this.mUserHandle = userHandle;
    }

    public static class ConfirmRemoveAccountDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        private Account mAccount;
        private UserHandle mUserHandle;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 585;
        }

        public static ConfirmRemoveAccountDialog show(Fragment fragment, Account account, UserHandle userHandle) {
            if (!fragment.isAdded()) {
                return null;
            }
            ConfirmRemoveAccountDialog confirmRemoveAccountDialog = new ConfirmRemoveAccountDialog();
            Bundle bundle = new Bundle();
            bundle.putParcelable("account", account);
            bundle.putParcelable("android.intent.extra.USER", userHandle);
            confirmRemoveAccountDialog.setArguments(bundle);
            confirmRemoveAccountDialog.setTargetFragment(fragment, 0);
            confirmRemoveAccountDialog.show(fragment.getFragmentManager(), "confirmRemoveAccount");
            return confirmRemoveAccountDialog;
        }

        @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            Bundle arguments = getArguments();
            this.mAccount = (Account) arguments.getParcelable("account");
            this.mUserHandle = (UserHandle) arguments.getParcelable("android.intent.extra.USER");
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.really_remove_account_title);
            builder.setMessage(R.string.really_remove_account_message);
            builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
            builder.setPositiveButton(R.string.remove_account_label, this);
            return builder.create();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            FragmentActivity activity = getTargetFragment().getActivity();
            AccountManager.get(activity).removeAccountAsUser(this.mAccount, activity, new AccountManagerCallback<Bundle>() { // from class: com.android.car.developeroptions.accounts.RemoveAccountPreferenceController.ConfirmRemoveAccountDialog.1
                @Override // android.accounts.AccountManagerCallback
                public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                    boolean z = true;
                    try {
                        z = true ^ accountManagerFuture.getResult().getBoolean("booleanResult");
                    } catch (AuthenticatorException | OperationCanceledException | IOException unused) {
                    }
                    FragmentActivity activity2 = ConfirmRemoveAccountDialog.this.getTargetFragment().getActivity();
                    if (z && activity2 != null && !activity2.isFinishing()) {
                        RemoveAccountFailureDialog.show(ConfirmRemoveAccountDialog.this.getTargetFragment());
                    } else {
                        activity2.finish();
                    }
                }
            }, null, this.mUserHandle);
        }
    }

    public static class RemoveAccountFailureDialog extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 586;
        }

        public static void show(Fragment fragment) {
            if (fragment.isAdded()) {
                RemoveAccountFailureDialog removeAccountFailureDialog = new RemoveAccountFailureDialog();
                removeAccountFailureDialog.setTargetFragment(fragment, 0);
                removeAccountFailureDialog.show(fragment.getFragmentManager(), "removeAccountFailed");
            }
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.really_remove_account_title);
            builder.setMessage(R.string.remove_account_failed);
            builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
    }
}
