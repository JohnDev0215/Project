package com.android.car.settings.users;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.ErrorDialog;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class ChooseNewAdminPreferenceController extends UsersBasePreferenceController {
    private UserInfo mAdminInfo;
    private final ConfirmationDialogFragment.ConfirmListener mConfirmListener;

    public /* synthetic */ void lambda$new$0$ChooseNewAdminPreferenceController(Bundle arguments) {
        UserInfo userToMakeAdmin = (UserInfo) arguments.get("USER_TO_MAKE_ADMIN");
        assignNewAdminAndRemoveOldAdmin(userToMakeAdmin);
        getFragmentController().goBack();
    }

    public ChooseNewAdminPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.users.-$$Lambda$ChooseNewAdminPreferenceController$h_l8_n5k5cwMCMbN1pwUTU-MIM8
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$ChooseNewAdminPreferenceController(bundle);
            }
        };
        getPreferenceProvider().setIncludeCurrentUser(false);
        getPreferenceProvider().setIncludeGuest(false);
    }

    public void setAdminInfo(UserInfo adminInfo) {
        this.mAdminInfo = adminInfo;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mAdminInfo == null) {
            throw new IllegalStateException("Admin info should be set by this point");
        }
    }

    @Override // com.android.car.settings.users.UsersBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        ConfirmationDialogFragment dialogFragment = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(ConfirmationDialogFragment.TAG);
        ConfirmationDialogFragment.resetListeners(dialogFragment, this.mConfirmListener, null, null);
    }

    @Override // com.android.car.settings.users.UsersBasePreferenceController
    protected void userClicked(UserInfo userToMakeAdmin) {
        ConfirmationDialogFragment dialogFragment = UsersDialogProvider.getConfirmGrantAdminDialogFragment(getContext(), this.mConfirmListener, null, userToMakeAdmin);
        getFragmentController().showDialog(dialogFragment, ConfirmationDialogFragment.TAG);
    }

    void assignNewAdminAndRemoveOldAdmin(UserInfo userToMakeAdmin) {
        getCarUserManagerHelper().grantAdminPermissions(userToMakeAdmin);
        removeOldAdmin();
    }

    private void removeOldAdmin() {
        Context context = getContext();
        if (!UserHelper.getInstance(context).removeUser(context, this.mAdminInfo)) {
            getFragmentController().showDialog(ErrorDialog.newInstance(R.string.delete_user_error_title), null);
        }
    }
}
