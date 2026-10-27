package com.android.providers.contacts.enterprise;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.net.Uri;
import android.os.UserHandle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import com.android.providers.contacts.AbstractContactsProvider;
import com.android.providers.contacts.ContactsProvider2;
import com.android.providers.contacts.ProfileAwareUriMatcher;
import com.android.providers.contacts.util.UserUtils;

/* JADX INFO: loaded from: classes.dex */
public class EnterprisePolicyGuard {
    private static final boolean VERBOSE_LOGGING = AbstractContactsProvider.VERBOSE_LOGGING;
    private static final ProfileAwareUriMatcher sUriMatcher = ContactsProvider2.sUriMatcher;
    private final Context mContext;
    private final DevicePolicyManager mDpm;

    private static boolean isBluetoothContactSharing(int i) {
        return i == 3002 || i == 15001;
    }

    private static boolean isCallerIdGuarded(int i) {
        return i == 1009 || i == 1012 || i == 3007 || i == 4000 || i == 24000 || i == 17001 || i == 17002;
    }

    private static boolean isContactsSearchGuarded(int i) {
        return i == 1005 || i == 1009 || i == 1012 || i == 3004 || i == 3008 || i == 3013 || i == 24000 || i == 17001 || i == 17002;
    }

    private static boolean isDirectorySupported(int i) {
        return i == 1005 || i == 3004 || i == 3013 || i == 4000 || i == 24000 || i == 3007 || i == 3008;
    }

    private boolean isUriWhitelisted(int i) {
        return i == 1015 || i == 1016 || i == 19004;
    }

    private static boolean isValidEnterpriseUri(int i) {
        if (i == 1029 || i == 4001 || i == 24000 || i == 17003 || i == 17004) {
            return true;
        }
        switch (i) {
            case 3017:
            case 3018:
            case 3019:
            case 3020:
                return true;
            default:
                return false;
        }
    }

    public EnterprisePolicyGuard(Context context) {
        this.mContext = context;
        this.mDpm = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
    }

    public boolean isCrossProfileAllowed(Uri uri) {
        int iMatch = sUriMatcher.match(uri);
        UserHandle userHandle = new UserHandle(UserUtils.getCurrentUserHandle(this.mContext));
        if (iMatch == -1) {
            return false;
        }
        if (isUriWhitelisted(iMatch)) {
            return true;
        }
        boolean z = !this.mDpm.getCrossProfileCallerIdDisabled(userHandle);
        boolean z2 = !this.mDpm.getCrossProfileContactsSearchDisabled(userHandle);
        boolean z3 = !this.mDpm.getBluetoothContactSharingDisabled(userHandle);
        boolean zIsContactRemoteSearchUserSettingEnabled = isContactRemoteSearchUserSettingEnabled();
        String queryParameter = uri.getQueryParameter("directory");
        if (VERBOSE_LOGGING) {
            Log.v("ContactsProvider", "isCallerIdEnabled: " + z);
            Log.v("ContactsProvider", "isContactsSearchPolicyEnabled: " + z2);
            Log.v("ContactsProvider", "isBluetoothContactSharingEnabled: " + z3);
            Log.v("ContactsProvider", "isContactRemoteSearchUserEnabled: " + zIsContactRemoteSearchUserSettingEnabled);
        }
        if (queryParameter == null || !ContactsContract.Directory.isRemoteDirectoryId(Long.parseLong(queryParameter)) || (isCrossProfileDirectorySupported(uri) && zIsContactRemoteSearchUserSettingEnabled)) {
            return (isCallerIdGuarded(iMatch) && z) || (isContactsSearchGuarded(iMatch) && z2) || (isBluetoothContactSharing(iMatch) && z3);
        }
        return false;
    }

    protected boolean isCrossProfileDirectorySupported(Uri uri) {
        return isDirectorySupported(sUriMatcher.match(uri));
    }

    public boolean isValidEnterpriseUri(Uri uri) {
        return isValidEnterpriseUri(sUriMatcher.match(uri));
    }

    protected boolean isContactRemoteSearchUserSettingEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "managed_profile_contact_remote_search", 0) == 1;
    }
}
