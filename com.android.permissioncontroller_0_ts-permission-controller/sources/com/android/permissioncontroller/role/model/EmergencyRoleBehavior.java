package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.os.UserHandle;
import android.telephony.TelephonyManager;
import com.android.permissioncontroller.role.utils.PackageUtils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class EmergencyRoleBehavior implements RoleBehavior {
    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isAvailableAsUser(Role role, UserHandle user, Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        return telephonyManager.isEmergencyAssistanceEnabled() && telephonyManager.isVoiceCapable();
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public String getFallbackHolder(Role role, Context context) {
        List<String> packageNames = role.getQualifyingPackagesAsUser(Process.myUserHandle(), context);
        PackageInfo fallbackPackageInfo = null;
        int packageNamesSize = packageNames.size();
        for (int i = 0; i < packageNamesSize; i++) {
            String packageName = packageNames.get(i);
            PackageInfo packageInfo = PackageUtils.getPackageInfo(packageName, 0, context);
            if (packageInfo != null && (fallbackPackageInfo == null || packageInfo.firstInstallTime < fallbackPackageInfo.firstInstallTime)) {
                fallbackPackageInfo = packageInfo;
            }
        }
        if (fallbackPackageInfo != null) {
            return fallbackPackageInfo.packageName;
        }
        return null;
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isVisibleAsUser(Role role, UserHandle user, Context context) {
        return VisibilityMixin.isVisible("config_showDefaultEmergency", context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public CharSequence getConfirmationMessage(Role role, String packageName, Context context) {
        return EncryptionUnawareConfirmationMixin.getConfirmationMessage(role, packageName, context);
    }
}
