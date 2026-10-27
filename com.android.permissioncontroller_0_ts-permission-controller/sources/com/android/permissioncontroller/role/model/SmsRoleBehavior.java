package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.telephony.TelephonyManager;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.CollectionUtils;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class SmsRoleBehavior implements RoleBehavior {
    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isAvailableAsUser(Role role, UserHandle user, Context context) {
        if (UserUtils.isWorkProfile(user, context)) {
            return false;
        }
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        if (userManager.isRestrictedProfile(user)) {
            return false;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        return telephonyManager.isSmsCapable() || !role.getDefaultHolders(context).isEmpty();
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public String getFallbackHolder(Role role, Context context) {
        List<String> defaultPackageNames = role.getDefaultHolders(context);
        if (!defaultPackageNames.isEmpty()) {
            return defaultPackageNames.get(0);
        }
        List<String> qualifyingPackageNames = role.getQualifyingPackagesAsUser(Process.myUserHandle(), context);
        return (String) CollectionUtils.firstOrNull(qualifyingPackageNames);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public CharSequence getConfirmationMessage(Role role, String packageName, Context context) {
        return EncryptionUnawareConfirmationMixin.getConfirmationMessage(role, packageName, context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isVisibleAsUser(Role role, UserHandle user, Context context) {
        return context.getResources().getBoolean(R.bool.config_showSmsRole);
    }
}
