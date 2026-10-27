package com.android.settingslib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes6.dex */
public class TetherUtil {
    public static boolean isTetherAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        boolean tetherConfigDisallowed = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(context, "no_config_tethering", UserHandle.myUserId()) != null;
        boolean hasBaseUserRestriction = RestrictedLockUtilsInternal.hasBaseUserRestriction(context, "no_config_tethering", UserHandle.myUserId());
        return (cm.isTetheringSupported() || tetherConfigDisallowed) && !hasBaseUserRestriction;
    }
}
