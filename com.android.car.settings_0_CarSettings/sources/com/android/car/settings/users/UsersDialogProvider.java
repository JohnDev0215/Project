package com.android.car.settings.users;

import android.content.Context;
import android.content.pm.UserInfo;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;

/* JADX INFO: loaded from: classes5.dex */
public final class UsersDialogProvider {
    static final String ANY_USER = "ANY_USER";
    static final String KEY_USER_TO_MAKE_ADMIN = "USER_TO_MAKE_ADMIN";
    static final String KEY_USER_TYPE = "USER_TYPE";
    static final String LAST_ADMIN = "LAST_ADMIN";
    static final String LAST_USER = "LAST_USER";

    private UsersDialogProvider() {
    }

    public static ConfirmationDialogFragment getConfirmCreateNewUserDialogFragment(Context context, ConfirmationDialogFragment.ConfirmListener confirmListener, ConfirmationDialogFragment.RejectListener rejectListener) {
        String message = context.getString(R.string.user_add_user_message_setup).concat(System.lineSeparator()).concat(System.lineSeparator()).concat(context.getString(R.string.user_add_user_message_update));
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(context).setTitle(R.string.user_add_user_title).setMessage(message).setPositiveButton(android.R.string.ok, confirmListener).setNegativeButton(android.R.string.cancel, rejectListener).build();
        return dialogFragment;
    }

    public static ConfirmationDialogFragment getConfirmExitRetailModeDialogFragment(Context context, ConfirmationDialogFragment.ConfirmListener confirmListener, ConfirmationDialogFragment.RejectListener rejectListener) {
        return new ConfirmationDialogFragment.Builder(context).setTitle(R.string.exit_retail_mode_dialog_title).setMessage(R.string.exit_retail_mode_dialog_body).setPositiveButton(R.string.exit_retail_mode_dialog_confirmation_button_text, confirmListener).setNegativeButton(android.R.string.cancel, rejectListener).build();
    }

    public static ConfirmationDialogFragment getMaxUsersLimitReachedDialogFragment(Context context, int maxUserLimit) {
        return new ConfirmationDialogFragment.Builder(context).setTitle(R.string.user_limit_reached_title).setMessage(context.getResources().getQuantityString(R.plurals.user_limit_reached_message, maxUserLimit, Integer.valueOf(maxUserLimit))).setPositiveButton(android.R.string.ok, (ConfirmationDialogFragment.ConfirmListener) null).build();
    }

    public static ConfirmationDialogFragment getConfirmGrantAdminDialogFragment(Context context, ConfirmationDialogFragment.ConfirmListener confirmListener, ConfirmationDialogFragment.RejectListener rejectListener, UserInfo userToMakeAdmin) {
        String message = context.getString(R.string.grant_admin_permissions_message).concat(System.lineSeparator()).concat(System.lineSeparator()).concat(context.getString(R.string.action_not_reversible_message));
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(context).setTitle(R.string.grant_admin_permissions_title).setMessage(message).setPositiveButton(R.string.confirm_grant_admin, confirmListener).setNegativeButton(android.R.string.cancel, rejectListener).addArgumentParcelable(KEY_USER_TO_MAKE_ADMIN, userToMakeAdmin).build();
        return dialogFragment;
    }

    public static ConfirmationDialogFragment getConfirmRemoveLastUserDialogFragment(Context context, ConfirmationDialogFragment.ConfirmListener confirmListener, ConfirmationDialogFragment.RejectListener rejectListener) {
        String message = context.getString(R.string.delete_last_user_admin_created_message).concat(System.lineSeparator()).concat(System.lineSeparator()).concat(context.getString(R.string.delete_last_user_system_setup_required_message));
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(context).setTitle(R.string.delete_last_user_dialog_title).setMessage(message).setPositiveButton(R.string.delete_button, confirmListener).setNegativeButton(android.R.string.cancel, rejectListener).addArgumentString(KEY_USER_TYPE, LAST_USER).build();
        return dialogFragment;
    }

    public static ConfirmationDialogFragment getConfirmRemoveLastAdminDialogFragment(Context context, ConfirmationDialogFragment.ConfirmListener confirmListener, ConfirmationDialogFragment.RejectListener rejectListener) {
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(context).setTitle(R.string.choose_new_admin_title).setMessage(R.string.choose_new_admin_message).setPositiveButton(R.string.choose_new_admin_label, confirmListener).setNegativeButton(android.R.string.cancel, rejectListener).addArgumentString(KEY_USER_TYPE, LAST_ADMIN).build();
        return dialogFragment;
    }

    public static ConfirmationDialogFragment getConfirmRemoveUserDialogFragment(Context context, ConfirmationDialogFragment.ConfirmListener confirmListener, ConfirmationDialogFragment.RejectListener rejectListener) {
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(context).setTitle(R.string.delete_user_dialog_title).setMessage(R.string.delete_user_dialog_message).setPositiveButton(R.string.delete_button, confirmListener).setNegativeButton(android.R.string.cancel, rejectListener).addArgumentString(KEY_USER_TYPE, ANY_USER).build();
        return dialogFragment;
    }
}
