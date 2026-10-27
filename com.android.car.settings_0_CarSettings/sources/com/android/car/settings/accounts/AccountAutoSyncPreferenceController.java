package com.android.car.settings.accounts;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class AccountAutoSyncPreferenceController extends PreferenceController<TwoStatePreference> {
    static final String KEY_ENABLING = "ENABLING";
    static final String KEY_USER_HANDLE = "USER_HANDLE";
    final ConfirmationDialogFragment.ConfirmListener mConfirmListener;
    private final UserHandle mUserHandle;

    public /* synthetic */ void lambda$new$0$AccountAutoSyncPreferenceController(Bundle arguments) {
        boolean enabling = arguments.getBoolean(KEY_ENABLING);
        UserHandle userHandle = (UserHandle) arguments.getParcelable(KEY_USER_HANDLE);
        ContentResolver.setMasterSyncAutomaticallyAsUser(enabling, userHandle.getIdentifier());
        getPreference().setChecked(enabling);
    }

    public AccountAutoSyncPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountAutoSyncPreferenceController$3Jau9KkE8XLP3Cr32Jo7Q06UCVA
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$AccountAutoSyncPreferenceController(bundle);
            }
        };
        this.mUserHandle = UserHandle.of(UserHandle.myUserId());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setChecked(ContentResolver.getMasterSyncAutomaticallyAsUser(this.mUserHandle.getIdentifier()));
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        ConfirmationDialogFragment dialog = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(ConfirmationDialogFragment.TAG);
        ConfirmationDialogFragment.resetListeners(dialog, this.mConfirmListener, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference preference, Object checked) {
        getFragmentController().showDialog(getAutoSyncChangeConfirmationDialogFragment(((Boolean) checked).booleanValue()), ConfirmationDialogFragment.TAG);
        return false;
    }

    private ConfirmationDialogFragment getAutoSyncChangeConfirmationDialogFragment(boolean enabling) {
        int dialogTitle;
        int dialogMessage;
        if (enabling) {
            dialogTitle = R.string.data_usage_auto_sync_on_dialog_title;
            dialogMessage = R.string.data_usage_auto_sync_on_dialog;
        } else {
            dialogTitle = R.string.data_usage_auto_sync_off_dialog_title;
            dialogMessage = R.string.data_usage_auto_sync_off_dialog;
        }
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(getContext()).setTitle(dialogTitle).setMessage(dialogMessage).setPositiveButton(android.R.string.ok, this.mConfirmListener).setNegativeButton(android.R.string.cancel, (ConfirmationDialogFragment.RejectListener) null).addArgumentBoolean(KEY_ENABLING, enabling).addArgumentParcelable(KEY_USER_HANDLE, this.mUserHandle).build();
        return dialogFragment;
    }
}
