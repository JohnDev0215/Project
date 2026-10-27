package com.android.packageinstaller.handheld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.android.packageinstaller.R;
import com.android.packageinstaller.UninstallerActivity;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UninstallAlertDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String LOG_TAG = UninstallAlertDialogFragment.class.getSimpleName();
    private CheckBox mKeepData;

    private long getAppDataSizeForUser(String pkg, UserHandle user) {
        StorageStatsManager storageStatsManager = (StorageStatsManager) getContext().getSystemService(StorageStatsManager.class);
        try {
            StorageStats stats = storageStatsManager.queryStatsForPackage(getContext().getPackageManager().getApplicationInfo(pkg, 0).storageUuid, pkg, user);
            return stats.getDataBytes();
        } catch (PackageManager.NameNotFoundException | IOException e) {
            Log.e(LOG_TAG, "Cannot determine amount of app data for " + pkg, e);
            return 0L;
        }
    }

    private long getAppDataSize(String pkg, UserHandle user) {
        UserManager userManager = (UserManager) getContext().getSystemService(UserManager.class);
        long appDataSize = 0;
        if (user == null) {
            List<UserInfo> users = userManager.getUsers();
            int numUsers = users.size();
            for (int i = 0; i < numUsers; i++) {
                appDataSize += getAppDataSizeForUser(pkg, UserHandle.of(users.get(i).id));
            }
            return appDataSize;
        }
        long appDataSize2 = getAppDataSizeForUser(pkg, user);
        return appDataSize2;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        boolean suggestToKeepAppData;
        PackageManager pm = getActivity().getPackageManager();
        UninstallerActivity.DialogInfo dialogInfo = ((UninstallerActivity) getActivity()).getDialogInfo();
        CharSequence appLabel = dialogInfo.appInfo.loadSafeLabel(pm);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        StringBuilder messageBuilder = new StringBuilder();
        if (dialogInfo.activityInfo != null) {
            Object activityLabel = dialogInfo.activityInfo.loadSafeLabel(pm);
            if (!activityLabel.equals(appLabel)) {
                messageBuilder.append(getString(R.string.uninstall_activity_text, new Object[]{activityLabel}));
                messageBuilder.append(" ");
                messageBuilder.append(appLabel);
                messageBuilder.append(".\n\n");
            }
        }
        boolean isUpdate = (dialogInfo.appInfo.flags & 128) != 0;
        UserManager userManager = UserManager.get(getActivity());
        if (isUpdate) {
            if (isSingleUser(userManager)) {
                messageBuilder.append(getString(R.string.uninstall_update_text));
            } else {
                messageBuilder.append(getString(R.string.uninstall_update_text_multiuser));
            }
        } else if (dialogInfo.allUsers && !isSingleUser(userManager)) {
            messageBuilder.append(getString(R.string.uninstall_application_text_all_users));
        } else if (dialogInfo.user.equals(Process.myUserHandle())) {
            messageBuilder.append(getString(R.string.uninstall_application_text));
        } else {
            UserInfo userInfo = userManager.getUserInfo(dialogInfo.user.getIdentifier());
            messageBuilder.append(getString(R.string.uninstall_application_text_user, new Object[]{userInfo.name}));
        }
        dialogBuilder.setTitle(appLabel);
        dialogBuilder.setPositiveButton(android.R.string.ok, this);
        dialogBuilder.setNegativeButton(android.R.string.cancel, this);
        String pkg = dialogInfo.appInfo.packageName;
        try {
            PackageInfo pkgInfo = pm.getPackageInfo(pkg, 0);
            suggestToKeepAppData = pkgInfo.applicationInfo.hasFragileUserData();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Cannot check hasFragileUserData for " + pkg, e);
            suggestToKeepAppData = false;
        }
        long appDataSize = 0;
        if (suggestToKeepAppData) {
            appDataSize = getAppDataSize(pkg, dialogInfo.allUsers ? null : dialogInfo.user);
        }
        if (appDataSize == 0) {
            dialogBuilder.setMessage(messageBuilder.toString());
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LayoutInflater.class);
            ViewGroup content = (ViewGroup) inflater.inflate(R.layout.uninstall_content_view, (ViewGroup) null);
            ((TextView) content.requireViewById(R.id.message)).setText(messageBuilder.toString());
            CheckBox checkBox = (CheckBox) content.requireViewById(R.id.keepData);
            this.mKeepData = checkBox;
            checkBox.setVisibility(0);
            this.mKeepData.setText(getString(R.string.uninstall_keep_data, new Object[]{Formatter.formatFileSize(getContext(), appDataSize)}));
            dialogBuilder.setView(content);
        }
        return dialogBuilder.create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            UninstallerActivity uninstallerActivity = (UninstallerActivity) getActivity();
            CheckBox checkBox = this.mKeepData;
            uninstallerActivity.startUninstallProgress(checkBox != null && checkBox.isChecked());
            return;
        }
        ((UninstallerActivity) getActivity()).dispatchAborted();
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (isAdded()) {
            getActivity().finish();
        }
    }

    private boolean isSingleUser(UserManager userManager) {
        int userCount = userManager.getUserCount();
        if (userCount != 1) {
            return UserManager.isSplitSystemUser() && userCount == 2;
        }
        return true;
    }
}
