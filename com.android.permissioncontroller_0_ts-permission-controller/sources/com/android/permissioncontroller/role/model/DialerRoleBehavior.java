package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.UserHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import com.android.permissioncontroller.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class DialerRoleBehavior implements RoleBehavior {
    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isAvailableAsUser(Role role, UserHandle user, Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        return telephonyManager.isVoiceCapable();
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public void prepareApplicationPreferenceAsUser(Role role, Preference preference, ApplicationInfo applicationInfo, UserHandle user, Context context) {
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        String systemPackageName = telecomManager.getSystemDialerPackage();
        if (Objects.equals(applicationInfo.packageName, systemPackageName)) {
            preference.setSummary(R.string.default_app_system_default);
        } else {
            preference.setSummary((CharSequence) null);
        }
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public CharSequence getConfirmationMessage(Role role, String packageName, Context context) {
        return EncryptionUnawareConfirmationMixin.getConfirmationMessage(role, packageName, context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isVisibleAsUser(Role role, UserHandle user, Context context) {
        return context.getResources().getBoolean(R.bool.config_showDialerRole);
    }
}
