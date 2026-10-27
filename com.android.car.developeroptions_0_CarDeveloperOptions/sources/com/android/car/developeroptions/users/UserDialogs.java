package com.android.car.developeroptions.users;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;

/* JADX INFO: loaded from: classes.dex */
public final class UserDialogs {
    public static Dialog createRemoveDialog(Context context, int i, DialogInterface.OnClickListener onClickListener) {
        UserInfo userInfo = ((UserManager) context.getSystemService("user")).getUserInfo(i);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(R.string.user_delete_button, onClickListener);
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
        if (userInfo.isManagedProfile()) {
            builder.setTitle(R.string.work_profile_confirm_remove_title);
            View viewCreateRemoveManagedUserDialogView = createRemoveManagedUserDialogView(context, i);
            if (viewCreateRemoveManagedUserDialogView != null) {
                builder.setView(viewCreateRemoveManagedUserDialogView);
            } else {
                builder.setMessage(R.string.work_profile_confirm_remove_message);
            }
        } else if (UserHandle.myUserId() == i) {
            builder.setTitle(R.string.user_confirm_remove_self_title);
            builder.setMessage(R.string.user_confirm_remove_self_message);
        } else if (userInfo.isRestricted()) {
            builder.setTitle(R.string.user_profile_confirm_remove_title);
            builder.setMessage(R.string.user_profile_confirm_remove_message);
        } else {
            builder.setTitle(R.string.user_confirm_remove_title);
            builder.setMessage(R.string.user_confirm_remove_message);
        }
        return builder.create();
    }

    private static View createRemoveManagedUserDialogView(Context context, int i) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo adminApplicationInfo = Utils.getAdminApplicationInfo(context, i);
        if (adminApplicationInfo == null) {
            return null;
        }
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.delete_managed_profile_dialog, (ViewGroup) null);
        ((ImageView) viewInflate.findViewById(R.id.delete_managed_profile_mdm_icon_view)).setImageDrawable(packageManager.getUserBadgedIcon(packageManager.getApplicationIcon(adminApplicationInfo), new UserHandle(i)));
        CharSequence applicationLabel = packageManager.getApplicationLabel(adminApplicationInfo);
        CharSequence userBadgedLabel = packageManager.getUserBadgedLabel(applicationLabel, new UserHandle(i));
        TextView textView = (TextView) viewInflate.findViewById(R.id.delete_managed_profile_device_manager_name);
        textView.setText(applicationLabel);
        if (!applicationLabel.toString().contentEquals(userBadgedLabel)) {
            textView.setContentDescription(userBadgedLabel);
        }
        return viewInflate;
    }

    public static Dialog createEnablePhoneCallsAndSmsDialog(Context context, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.user_enable_calling_and_sms_confirm_title);
        builder.setMessage(R.string.user_enable_calling_and_sms_confirm_message);
        builder.setPositiveButton(R.string.okay, onClickListener);
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    public static Dialog createEnablePhoneCallsDialog(Context context, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.user_enable_calling_confirm_title);
        builder.setMessage(R.string.user_enable_calling_confirm_message);
        builder.setPositiveButton(R.string.okay, onClickListener);
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }
}
