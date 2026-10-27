package com.android.car.settings.users;

import android.content.Context;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserManager;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.ErrorDialog;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class UserDetailsBaseFragment extends SettingsFragment {
    private final ConfirmationDialogFragment.ConfirmListener mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.users.-$$Lambda$UserDetailsBaseFragment$_zkMMT_eRuRqvQW9LQ_UhqahA6o
        @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
        public final void onConfirm(Bundle bundle) {
            this.f$0.lambda$new$0$UserDetailsBaseFragment(bundle);
        }
    };
    private MenuItem mDeleteButton;
    private UserInfo mUserInfo;
    private UserManager mUserManager;

    protected abstract String getTitleText();

    public /* synthetic */ void lambda$new$0$UserDetailsBaseFragment(Bundle arguments) {
        String userType = arguments.getString("USER_TYPE");
        if (userType.equals("LAST_ADMIN")) {
            launchFragment(ChooseNewAdminFragment.newInstance(this.mUserInfo));
            return;
        }
        Context context = getContext();
        if (UserHelper.getInstance(context).removeUser(context, this.mUserInfo)) {
            getActivity().onBackPressed();
        } else {
            ErrorDialog.show(this, R.string.delete_user_error_title);
        }
    }

    protected static UserDetailsBaseFragment addUserIdToFragmentArguments(UserDetailsBaseFragment fragment, int userId) {
        Bundle bundle = new Bundle();
        bundle.putInt("android.intent.extra.USER_ID", userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mDeleteButton);
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        int userId = getArguments().getInt("android.intent.extra.USER_ID");
        this.mUserManager = UserManager.get(getContext());
        this.mUserInfo = UserUtils.getUserInfo(getContext(), userId);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConfirmationDialogFragment dialogFragment = (ConfirmationDialogFragment) findDialogByTag(ConfirmationDialogFragment.TAG);
        ConfirmationDialogFragment.resetListeners(dialogFragment, this.mConfirmListener, null, null);
        boolean isVisible = (this.mUserManager.hasUserRestriction("no_remove_user") || this.mUserInfo.id == 0 || this.mUserManager.isDemoUser()) ? false : true;
        this.mDeleteButton = new MenuItem.Builder(getContext()).setTitle(R.string.delete_button).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$UserDetailsBaseFragment$PKTDuPRyseVpIHFG2_IRODblhpw
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$1$UserDetailsBaseFragment(menuItem);
            }
        }).setVisible(isVisible).build();
    }

    public /* synthetic */ void lambda$onCreate$1$UserDetailsBaseFragment(MenuItem i) {
        showConfirmRemoveUserDialog();
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getToolbar().setTitle(getTitleText());
    }

    protected UserInfo getUserInfo() {
        return this.mUserInfo;
    }

    protected void refreshUserInfo() {
        this.mUserInfo = UserUtils.getUserInfo(getContext(), this.mUserInfo.id);
    }

    private void showConfirmRemoveUserDialog() {
        ConfirmationDialogFragment dialogFragment;
        UserHelper userHelper = UserHelper.getInstance(getContext());
        boolean isLastAdmin = false;
        boolean isLastUser = userHelper.getAllPersistentUsers().size() == 1;
        if (this.mUserInfo.isAdmin() && userHelper.getAllAdminUsers().size() == 1) {
            isLastAdmin = true;
        }
        if (isLastUser) {
            dialogFragment = UsersDialogProvider.getConfirmRemoveLastUserDialogFragment(getContext(), this.mConfirmListener, null);
        } else if (!isLastAdmin) {
            dialogFragment = UsersDialogProvider.getConfirmRemoveUserDialogFragment(getContext(), this.mConfirmListener, null);
        } else {
            dialogFragment = UsersDialogProvider.getConfirmRemoveLastAdminDialogFragment(getContext(), this.mConfirmListener, null);
        }
        dialogFragment.show(getFragmentManager(), ConfirmationDialogFragment.TAG);
    }
}
