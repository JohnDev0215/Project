package com.android.providers.contacts;

import android.content.Context;
import android.os.Binder;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.providers.contacts.util.ContactsPermissions;

/* JADX INFO: loaded from: classes.dex */
public class VoicemailPermissions {
    private final Context mContext;

    public VoicemailPermissions(Context context) {
        this.mContext = context;
    }

    public boolean callerHasOwnVoicemailAccess() {
        return callerHasPermission("com.android.voicemail.permission.ADD_VOICEMAIL") || callerHasCarrierPrivileges();
    }

    private boolean isDefaultOrSystemDialer(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        TelecomManager telecomManager = (TelecomManager) this.mContext.getSystemService(TelecomManager.class);
        return str.equals(telecomManager.getDefaultDialerPackage()) || str.equals(telecomManager.getSystemDialerPackage());
    }

    public boolean callerHasReadAccess(String str) {
        if (isDefaultOrSystemDialer(str)) {
            return true;
        }
        return callerHasPermission("com.android.voicemail.permission.READ_VOICEMAIL");
    }

    public boolean callerHasWriteAccess(String str) {
        if (isDefaultOrSystemDialer(str)) {
            return true;
        }
        return callerHasPermission("com.android.voicemail.permission.WRITE_VOICEMAIL");
    }

    public void checkCallerHasOwnVoicemailAccess() {
        if (!callerHasOwnVoicemailAccess()) {
            throw new SecurityException("The caller must have permission: com.android.voicemail.permission.ADD_VOICEMAIL or carrier privileges");
        }
    }

    public void checkCallerHasReadAccess(String str) {
        if (!callerHasReadAccess(str)) {
            throw new SecurityException(String.format("The caller must be the default or system dialer, or have the system-only %s permission: ", "com.android.voicemail.permission.READ_VOICEMAIL"));
        }
    }

    public void checkCallerHasWriteAccess(String str) {
        if (!callerHasWriteAccess(str)) {
            throw new SecurityException(String.format("The caller must be the default or system dialer, or have the system-only %s permission: ", "com.android.voicemail.permission.WRITE_VOICEMAIL"));
        }
    }

    public boolean packageHasOwnVoicemailAccess(String str) {
        return packageHasPermission(str, "com.android.voicemail.permission.ADD_VOICEMAIL") || packageHasCarrierPrivileges(str);
    }

    public boolean packageHasReadAccess(String str) {
        return packageHasPermission(str, "com.android.voicemail.permission.READ_VOICEMAIL");
    }

    private boolean packageHasPermission(String str, String str2) {
        return ContactsPermissions.hasPackagePermission(this.mContext, str2, str);
    }

    private boolean callerHasPermission(String str) {
        return ContactsPermissions.hasCallerOrSelfPermission(this.mContext, str);
    }

    public boolean callerHasCarrierPrivileges() {
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        for (String str : this.mContext.getPackageManager().getPackagesForUid(Binder.getCallingUid())) {
            if (packageHasCarrierPrivileges(telephonyManager, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean packageHasCarrierPrivileges(String str) {
        return packageHasCarrierPrivileges((TelephonyManager) this.mContext.getSystemService("phone"), str);
    }

    private static boolean packageHasCarrierPrivileges(TelephonyManager telephonyManager, String str) {
        return telephonyManager.checkCarrierPrivilegesForPackageAnyPhone(str) == 1;
    }
}
