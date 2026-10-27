package com.android.car.settings.users;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.ui.preference.CarUiTwoActionTextPreference;

/* JADX INFO: loaded from: classes5.dex */
public class MakeAdminPreferenceController extends UserDetailsBasePreferenceController<CarUiTwoActionTextPreference> {
    final ConfirmationDialogFragment.ConfirmListener mConfirmListener;

    public /* synthetic */ void lambda$new$0$MakeAdminPreferenceController(Bundle arguments) {
        UserInfo userToMakeAdmin = (UserInfo) arguments.get("USER_TO_MAKE_ADMIN");
        getCarUserManagerHelper().grantAdminPermissions(userToMakeAdmin);
        getFragmentController().goBack();
    }

    public MakeAdminPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.users.-$$Lambda$MakeAdminPreferenceController$NnRmTYuAs5g_gwyhnJ8i5U8-DMI
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$MakeAdminPreferenceController(bundle);
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<CarUiTwoActionTextPreference> getPreferenceType() {
        return CarUiTwoActionTextPreference.class;
    }

    @Override // com.android.car.settings.users.UserDetailsBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        ConfirmationDialogFragment dialog = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(ConfirmationDialogFragment.TAG);
        ConfirmationDialogFragment.resetListeners(dialog, this.mConfirmListener, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(CarUiTwoActionTextPreference preference) {
        preference.setOnSecondaryActionClickListener(new Runnable() { // from class: com.android.car.settings.users.-$$Lambda$MakeAdminPreferenceController$LbNAn2Ah38dbP2IfVhsv-A_02ZU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateState$1$MakeAdminPreferenceController();
            }
        });
        Drawable icon = new UserIconProvider().getRoundedUserIcon(getUserInfo(), getContext());
        preference.setIcon(icon);
    }

    public /* synthetic */ void lambda$updateState$1$MakeAdminPreferenceController() {
        ConfirmationDialogFragment dialogFragment = UsersDialogProvider.getConfirmGrantAdminDialogFragment(getContext(), this.mConfirmListener, null, getUserInfo());
        getFragmentController().showDialog(dialogFragment, ConfirmationDialogFragment.TAG);
    }
}
