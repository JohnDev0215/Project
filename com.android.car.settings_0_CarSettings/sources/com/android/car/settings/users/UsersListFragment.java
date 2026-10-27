package com.android.car.settings.users;

import android.car.Car;
import android.car.user.CarUserManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserManager;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.ErrorDialog;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.car.settings.users.AddNewUserTask;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.ProgressBarController;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UsersListFragment extends SettingsFragment implements AddNewUserTask.AddNewUserListener {
    static final String CONFIRM_CREATE_NEW_USER_DIALOG_TAG = "com.android.car.settings.users.ConfirmCreateNewUserDialog";
    static final String CONFIRM_EXIT_RETAIL_MODE_DIALOG_TAG = "com.android.car.settings.users.ConfirmExitRetailModeDialog";
    private static final String FACTORY_RESET_PACKAGE_NAME = "android";
    private static final String FACTORY_RESET_REASON = "ExitRetailModeConfirmed";
    static final String MAX_USERS_LIMIT_REACHED_DIALOG_TAG = "com.android.car.settings.users.MaxUsersLimitReachedDialog";
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.users_list_fragment, "android.settings.USER_SETTINGS");
    private AsyncTask mAddNewUserTask;
    private MenuItem mAddUserButton;
    private Car mCar;
    private CarUserManager mCarUserManager;
    final ConfirmationDialogFragment.ConfirmListener mConfirmCreateNewUserListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.users.-$$Lambda$UsersListFragment$329Y9jGcRpmu09ydt4ZtljjkOX0
        @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
        public final void onConfirm(Bundle bundle) {
            this.f$0.lambda$new$0$UsersListFragment(bundle);
        }
    };
    final ConfirmationDialogFragment.ConfirmListener mConfirmExitRetailModeListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.users.-$$Lambda$UsersListFragment$MkyHZPTGR_zYgvtftNUy6Iz3c4g
        @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
        public final void onConfirm(Bundle bundle) {
            this.f$0.lambda$new$1$UsersListFragment(bundle);
        }
    };
    private boolean mIsBusy;
    private ProgressBarController mProgressBar;
    private UserManager mUserManager;

    public /* synthetic */ void lambda$new$0$UsersListFragment(Bundle arguments) {
        this.mAddNewUserTask = new AddNewUserTask(getContext(), this.mCarUserManager, this).execute(getContext().getString(R.string.user_new_user_name));
        this.mIsBusy = true;
        updateUi();
    }

    public /* synthetic */ void lambda$new$1$UsersListFragment(Bundle arguments) {
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.REASON", FACTORY_RESET_REASON);
        intent.putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", true);
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", true);
        getActivity().sendBroadcast(intent);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mAddUserButton);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.users_list_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Car carCreateCar = Car.createCar(context);
        this.mCar = carCreateCar;
        this.mCarUserManager = (CarUserManager) carCreateCar.getCarManager("car_user_service");
        this.mUserManager = UserManager.get(getContext());
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) findDialogByTag(CONFIRM_CREATE_NEW_USER_DIALOG_TAG), this.mConfirmCreateNewUserListener, null, null);
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) findDialogByTag(CONFIRM_EXIT_RETAIL_MODE_DIALOG_TAG), this.mConfirmExitRetailModeListener, null, null);
        this.mAddUserButton = new MenuItem.Builder(getContext()).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$UsersListFragment$Z0cWiR-xDO1vCkC9ECdMUcL3EwU
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$2$UsersListFragment(menuItem);
            }
        }).setTitle(this.mUserManager.isDemoUser() ? R.string.exit_retail_button_text : R.string.user_add_user_menu).setVisible(this.mUserManager.isDemoUser() || canCurrentProcessAddUsers()).setUxRestrictions(64).build();
    }

    public /* synthetic */ void lambda$onCreate$2$UsersListFragment(MenuItem i) {
        handleAddUserClicked();
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mProgressBar = getToolbar().getProgressBar();
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        updateUi();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mProgressBar.setVisible(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        AsyncTask asyncTask = this.mAddNewUserTask;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        Car car = this.mCar;
        if (car != null) {
            car.disconnect();
        }
    }

    @Override // com.android.car.settings.users.AddNewUserTask.AddNewUserListener
    public void onUserAddedSuccess() {
        this.mIsBusy = false;
        updateUi();
    }

    @Override // com.android.car.settings.users.AddNewUserTask.AddNewUserListener
    public void onUserAddedFailure() {
        this.mIsBusy = false;
        updateUi();
        ErrorDialog.show(this, R.string.add_user_error_title);
    }

    void setCarUserManager(CarUserManager carUserManager) {
        this.mCarUserManager = carUserManager;
    }

    private void updateUi() {
        this.mAddUserButton.setEnabled(!this.mIsBusy);
        this.mProgressBar.setVisible(this.mIsBusy);
    }

    private void handleAddUserClicked() {
        if (this.mUserManager.isDemoUser()) {
            ConfirmationDialogFragment dialogFragment = UsersDialogProvider.getConfirmExitRetailModeDialogFragment(getContext(), this.mConfirmExitRetailModeListener, null);
            dialogFragment.show(getFragmentManager(), CONFIRM_EXIT_RETAIL_MODE_DIALOG_TAG);
        } else if (!this.mUserManager.canAddMoreUsers()) {
            ConfirmationDialogFragment dialogFragment2 = UsersDialogProvider.getMaxUsersLimitReachedDialogFragment(getContext(), UserHelper.getInstance(getContext()).getMaxSupportedRealUsers());
            dialogFragment2.show(getFragmentManager(), MAX_USERS_LIMIT_REACHED_DIALOG_TAG);
        } else if (canCurrentProcessAddUsers()) {
            ConfirmationDialogFragment dialogFragment3 = UsersDialogProvider.getConfirmCreateNewUserDialogFragment(getContext(), this.mConfirmCreateNewUserListener, null);
            dialogFragment3.show(getFragmentManager(), CONFIRM_CREATE_NEW_USER_DIALOG_TAG);
        }
    }

    private boolean canCurrentProcessAddUsers() {
        return !this.mUserManager.hasUserRestriction("no_add_user");
    }
}
