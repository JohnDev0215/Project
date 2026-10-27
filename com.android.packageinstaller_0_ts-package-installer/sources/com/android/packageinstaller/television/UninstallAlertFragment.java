package com.android.packageinstaller.television;

import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.Process;
import android.os.UserManager;
import androidx.leanback.app.GuidedStepFragment;
import androidx.leanback.widget.GuidanceStylist;
import androidx.leanback.widget.GuidedAction;
import com.android.packageinstaller.R;
import com.android.packageinstaller.UninstallerActivity;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UninstallAlertFragment extends GuidedStepFragment {
    @Override // androidx.leanback.app.GuidedStepFragment
    public int onProvideTheme() {
        return 2131689787;
    }

    @Override // androidx.leanback.app.GuidedStepFragment
    public GuidanceStylist.Guidance onCreateGuidance(Bundle savedInstanceState) {
        PackageManager pm = getActivity().getPackageManager();
        UninstallerActivity.DialogInfo dialogInfo = ((UninstallerActivity) getActivity()).getDialogInfo();
        CharSequence appLabel = dialogInfo.appInfo.loadSafeLabel(pm);
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
        } else if (!dialogInfo.user.equals(Process.myUserHandle())) {
            UserInfo userInfo = userManager.getUserInfo(dialogInfo.user.getIdentifier());
            messageBuilder.append(getString(R.string.uninstall_application_text_user, new Object[]{userInfo.name}));
        } else {
            messageBuilder.append(getString(R.string.uninstall_application_text));
        }
        return new GuidanceStylist.Guidance(appLabel.toString(), messageBuilder.toString(), null, dialogInfo.appInfo.loadIcon(pm));
    }

    @Override // androidx.leanback.app.GuidedStepFragment
    public void onCreateActions(List<GuidedAction> actions, Bundle savedInstanceState) {
        actions.add(new GuidedAction.Builder(getContext()).clickAction(-4L).build());
        actions.add(new GuidedAction.Builder(getContext()).clickAction(-5L).build());
    }

    @Override // androidx.leanback.app.GuidedStepFragment
    public void onGuidedActionClicked(GuidedAction action) {
        if (isAdded()) {
            if (action.getId() == -4) {
                ((UninstallerActivity) getActivity()).startUninstallProgress(false);
                getActivity().finish();
            } else {
                ((UninstallerActivity) getActivity()).dispatchAborted();
                getActivity().setResult(1);
                getActivity().finish();
            }
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
