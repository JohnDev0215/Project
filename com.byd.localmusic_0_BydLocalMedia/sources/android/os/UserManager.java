package android.os;

import android.accounts.AccountManager;
import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.AndroidException;
import com.android.internal.R;
import com.android.internal.os.RoSystemProperties;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class UserManager {
    private static final String ACTION_CREATE_USER = "android.os.action.CREATE_USER";

    @SystemApi
    public static final String ACTION_USER_RESTRICTIONS_CHANGED = "android.os.action.USER_RESTRICTIONS_CHANGED";
    public static final String ALLOW_PARENT_PROFILE_APP_LINKING = "allow_parent_profile_app_linking";
    private static final String CACHE_KEY_IS_USER_UNLOCKED_PROPERTY = "cache_key.is_user_unlocked";

    @Deprecated
    public static final String DISALLOW_ADD_MANAGED_PROFILE = "no_add_managed_profile";
    public static final String DISALLOW_ADD_USER = "no_add_user";
    public static final String DISALLOW_ADJUST_VOLUME = "no_adjust_volume";
    public static final String DISALLOW_AIRPLANE_MODE = "no_airplane_mode";
    public static final String DISALLOW_AMBIENT_DISPLAY = "no_ambient_display";
    public static final String DISALLOW_APPS_CONTROL = "no_control_apps";
    public static final String DISALLOW_AUTOFILL = "no_autofill";
    public static final String DISALLOW_BLUETOOTH = "no_bluetooth";
    public static final String DISALLOW_BLUETOOTH_SHARING = "no_bluetooth_sharing";
    public static final String DISALLOW_CAMERA = "no_camera";
    public static final String DISALLOW_CONFIG_BLUETOOTH = "no_config_bluetooth";
    public static final String DISALLOW_CONFIG_BRIGHTNESS = "no_config_brightness";
    public static final String DISALLOW_CONFIG_CELL_BROADCASTS = "no_config_cell_broadcasts";
    public static final String DISALLOW_CONFIG_CREDENTIALS = "no_config_credentials";
    public static final String DISALLOW_CONFIG_DATE_TIME = "no_config_date_time";
    public static final String DISALLOW_CONFIG_LOCALE = "no_config_locale";
    public static final String DISALLOW_CONFIG_LOCATION = "no_config_location";
    public static final String DISALLOW_CONFIG_MOBILE_NETWORKS = "no_config_mobile_networks";
    public static final String DISALLOW_CONFIG_PRIVATE_DNS = "disallow_config_private_dns";
    public static final String DISALLOW_CONFIG_SCREEN_TIMEOUT = "no_config_screen_timeout";
    public static final String DISALLOW_CONFIG_TETHERING = "no_config_tethering";
    public static final String DISALLOW_CONFIG_VPN = "no_config_vpn";
    public static final String DISALLOW_CONFIG_WIFI = "no_config_wifi";
    public static final String DISALLOW_CONTENT_CAPTURE = "no_content_capture";
    public static final String DISALLOW_CONTENT_SUGGESTIONS = "no_content_suggestions";
    public static final String DISALLOW_CREATE_WINDOWS = "no_create_windows";
    public static final String DISALLOW_CROSS_PROFILE_COPY_PASTE = "no_cross_profile_copy_paste";
    public static final String DISALLOW_DATA_ROAMING = "no_data_roaming";
    public static final String DISALLOW_DEBUGGING_FEATURES = "no_debugging_features";
    public static final String DISALLOW_FACTORY_RESET = "no_factory_reset";
    public static final String DISALLOW_FUN = "no_fun";
    public static final String DISALLOW_INSTALL_APPS = "no_install_apps";
    public static final String DISALLOW_INSTALL_UNKNOWN_SOURCES = "no_install_unknown_sources";
    public static final String DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY = "no_install_unknown_sources_globally";
    public static final String DISALLOW_MODIFY_ACCOUNTS = "no_modify_accounts";
    public static final String DISALLOW_MOUNT_PHYSICAL_MEDIA = "no_physical_media";
    public static final String DISALLOW_NETWORK_RESET = "no_network_reset";

    @SystemApi
    @Deprecated
    public static final String DISALLOW_OEM_UNLOCK = "no_oem_unlock";
    public static final String DISALLOW_OUTGOING_BEAM = "no_outgoing_beam";
    public static final String DISALLOW_OUTGOING_CALLS = "no_outgoing_calls";
    public static final String DISALLOW_PRINTING = "no_printing";
    public static final String DISALLOW_RECORD_AUDIO = "no_record_audio";

    @Deprecated
    public static final String DISALLOW_REMOVE_MANAGED_PROFILE = "no_remove_managed_profile";
    public static final String DISALLOW_REMOVE_USER = "no_remove_user";

    @SystemApi
    public static final String DISALLOW_RUN_IN_BACKGROUND = "no_run_in_background";
    public static final String DISALLOW_SAFE_BOOT = "no_safe_boot";
    public static final String DISALLOW_SET_USER_ICON = "no_set_user_icon";
    public static final String DISALLOW_SET_WALLPAPER = "no_set_wallpaper";
    public static final String DISALLOW_SHARE_INTO_MANAGED_PROFILE = "no_sharing_into_profile";
    public static final String DISALLOW_SHARE_LOCATION = "no_share_location";
    public static final String DISALLOW_SMS = "no_sms";
    public static final String DISALLOW_SYSTEM_ERROR_DIALOGS = "no_system_error_dialogs";
    public static final String DISALLOW_UNIFIED_PASSWORD = "no_unified_password";
    public static final String DISALLOW_UNINSTALL_APPS = "no_uninstall_apps";
    public static final String DISALLOW_UNMUTE_DEVICE = "disallow_unmute_device";
    public static final String DISALLOW_UNMUTE_MICROPHONE = "no_unmute_microphone";
    public static final String DISALLOW_USB_FILE_TRANSFER = "no_usb_file_transfer";
    public static final String DISALLOW_USER_SWITCH = "no_user_switch";
    public static final String DISALLOW_WALLPAPER = "no_wallpaper";
    public static final String ENSURE_VERIFY_APPS = "ensure_verify_apps";
    public static final String EXTRA_USER_ACCOUNT_NAME = "android.os.extra.USER_ACCOUNT_NAME";
    public static final String EXTRA_USER_ACCOUNT_OPTIONS = "android.os.extra.USER_ACCOUNT_OPTIONS";
    public static final String EXTRA_USER_ACCOUNT_TYPE = "android.os.extra.USER_ACCOUNT_TYPE";
    public static final String EXTRA_USER_NAME = "android.os.extra.USER_NAME";
    public static final String KEY_RESTRICTIONS_PENDING = "restrictions_pending";
    public static final int PIN_VERIFICATION_FAILED_INCORRECT = -3;
    public static final int PIN_VERIFICATION_FAILED_NOT_SET = -2;
    public static final int PIN_VERIFICATION_SUCCESS = -1;
    public static final int QUIET_MODE_DISABLE_DONT_ASK_CREDENTIAL = 2;
    public static final int QUIET_MODE_DISABLE_ONLY_IF_CREDENTIAL_NOT_REQUIRED = 1;

    @SystemApi
    public static final int RESTRICTION_NOT_SET = 0;

    @SystemApi
    public static final int RESTRICTION_SOURCE_DEVICE_OWNER = 2;

    @SystemApi
    public static final int RESTRICTION_SOURCE_PROFILE_OWNER = 4;

    @SystemApi
    public static final int RESTRICTION_SOURCE_SYSTEM = 1;

    @SystemApi
    public static final int SWITCHABILITY_STATUS_OK = 0;

    @SystemApi
    public static final int SWITCHABILITY_STATUS_SYSTEM_USER_LOCKED = 4;

    @SystemApi
    public static final int SWITCHABILITY_STATUS_USER_IN_CALL = 1;

    @SystemApi
    public static final int SWITCHABILITY_STATUS_USER_SWITCH_DISALLOWED = 2;
    private static final String TAG = "UserManager";
    public static final int USER_CREATION_FAILED_NOT_PERMITTED = 1;
    public static final int USER_CREATION_FAILED_NO_MORE_USERS = 2;
    public static final int USER_OPERATION_ERROR_CURRENT_USER = 4;
    public static final int USER_OPERATION_ERROR_LOW_STORAGE = 5;
    public static final int USER_OPERATION_ERROR_MANAGED_PROFILE = 2;
    public static final int USER_OPERATION_ERROR_MAX_RUNNING_USERS = 3;
    public static final int USER_OPERATION_ERROR_MAX_USERS = 6;
    public static final int USER_OPERATION_ERROR_UNKNOWN = 1;
    public static final int USER_OPERATION_SUCCESS = 0;
    public static final String USER_TYPE_FULL_DEMO = "android.os.usertype.full.DEMO";
    public static final String USER_TYPE_FULL_GUEST = "android.os.usertype.full.GUEST";
    public static final String USER_TYPE_FULL_RESTRICTED = "android.os.usertype.full.RESTRICTED";

    @SystemApi
    public static final String USER_TYPE_FULL_SECONDARY = "android.os.usertype.full.SECONDARY";

    @SystemApi
    public static final String USER_TYPE_FULL_SYSTEM = "android.os.usertype.full.SYSTEM";

    @SystemApi
    public static final String USER_TYPE_PROFILE_MANAGED = "android.os.usertype.profile.MANAGED";

    @SystemApi
    public static final String USER_TYPE_SYSTEM_HEADLESS = "android.os.usertype.system.HEADLESS";
    private final Context mContext;
    private Boolean mIsManagedProfileCached;
    private Boolean mIsProfileCached;
    private final PropertyInvalidatedCache<Integer, Boolean> mIsUserUnlockedCache;
    private final PropertyInvalidatedCache<Integer, Boolean> mIsUserUnlockingOrUnlockedCache;
    private final IUserManager mService;
    private final int mUserId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface QuietModeFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UserOperationResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UserRestrictionKey {
    }

    @SystemApi
    @Retention(RetentionPolicy.SOURCE)
    public @interface UserRestrictionSource {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UserSwitchabilityResult {
    }

    @Deprecated
    public boolean setRestrictionsChallenge(String str) {
        return false;
    }

    public static class UserOperationException extends RuntimeException {
        private final int mUserOperationResult;

        public UserOperationException(String str, int i) {
            super(str);
            this.mUserOperationResult = i;
        }

        public int getUserOperationResult() {
            return this.mUserOperationResult;
        }

        public static UserOperationException from(ServiceSpecificException serviceSpecificException) {
            return new UserOperationException(serviceSpecificException.getMessage(), serviceSpecificException.errorCode);
        }
    }

    private <T> T returnNullOrThrowUserOperationException(ServiceSpecificException serviceSpecificException, boolean z) throws UserOperationException {
        if (z) {
            throw UserOperationException.from(serviceSpecificException);
        }
        return null;
    }

    public static class CheckedUserOperationException extends AndroidException {
        private final int mUserOperationResult;

        public CheckedUserOperationException(String str, int i) {
            super(str);
            this.mUserOperationResult = i;
        }

        public int getUserOperationResult() {
            return this.mUserOperationResult;
        }

        public ServiceSpecificException toServiceSpecificException() {
            return new ServiceSpecificException(this.mUserOperationResult, getMessage());
        }
    }

    public static UserManager get(Context context) {
        return (UserManager) context.getSystemService("user");
    }

    public UserManager(Context context, IUserManager iUserManager) {
        int i = 32;
        String str = CACHE_KEY_IS_USER_UNLOCKED_PROPERTY;
        this.mIsUserUnlockedCache = new PropertyInvalidatedCache<Integer, Boolean>(i, str) { // from class: android.os.UserManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Boolean recompute(Integer num) {
                try {
                    return Boolean.valueOf(UserManager.this.mService.isUserUnlocked(num.intValue()));
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        };
        this.mIsUserUnlockingOrUnlockedCache = new PropertyInvalidatedCache<Integer, Boolean>(i, str) { // from class: android.os.UserManager.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Boolean recompute(Integer num) {
                try {
                    return Boolean.valueOf(UserManager.this.mService.isUserUnlockingOrUnlocked(num.intValue()));
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        };
        this.mService = iUserManager;
        this.mContext = context.getApplicationContext();
        this.mUserId = context.getUserId();
    }

    public static boolean supportsMultipleUsers() {
        return getMaxSupportedUsers() > 1 && SystemProperties.getBoolean("fw.show_multiuserui", Resources.getSystem().getBoolean(R.bool.config_enableMultiUserUI));
    }

    public static boolean isSplitSystemUser() {
        return RoSystemProperties.FW_SYSTEM_USER_SPLIT;
    }

    public static boolean isGuestUserEphemeral() {
        return Resources.getSystem().getBoolean(R.bool.config_guestUserEphemeral);
    }

    public static boolean isHeadlessSystemUserMode() {
        return RoSystemProperties.MULTIUSER_HEADLESS_SYSTEM_USER;
    }

    @Deprecated
    public boolean canSwitchUsers() {
        boolean z = Settings.Global.getInt(this.mContext.getContentResolver(), Settings.Global.ALLOW_USER_SWITCHING_WHEN_SYSTEM_USER_LOCKED, 0) != 0;
        boolean zIsUserUnlocked = isUserUnlocked(UserHandle.SYSTEM);
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
        return ((!z && !zIsUserUnlocked) || (telephonyManager != null && telephonyManager.getCallState() != 0) || hasUserRestriction(DISALLOW_USER_SWITCH)) ? false : true;
    }

    @SystemApi
    public int getUserSwitchability() {
        return getUserSwitchability(Process.myUserHandle());
    }

    public int getUserSwitchability(UserHandle userHandle) {
        int i = ((TelephonyManager) this.mContext.getSystemService("phone")).getCallState() != 0 ? 1 : 0;
        if (hasUserRestriction(DISALLOW_USER_SWITCH, userHandle)) {
            i |= 2;
        }
        if (isHeadlessSystemUserMode()) {
            return i;
        }
        return ((Settings.Global.getInt(this.mContext.getContentResolver(), Settings.Global.ALLOW_USER_SWITCHING_WHEN_SYSTEM_USER_LOCKED, 0) != 0) || isUserUnlocked(UserHandle.SYSTEM)) ? i : i | 4;
    }

    public int getUserHandle() {
        return UserHandle.myUserId();
    }

    public String getUserName() {
        int iMyUserId = UserHandle.myUserId();
        int i = this.mUserId;
        if (iMyUserId == i) {
            try {
                return this.mService.getUserName();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        UserInfo userInfo = getUserInfo(i);
        return userInfo == null ? "" : userInfo.name;
    }

    @SystemApi
    public boolean isUserNameSet() {
        try {
            return this.mService.isUserNameSet(getUserHandle());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isUserAGoat() {
        if (this.mContext.getApplicationInfo().targetSdkVersion >= 30) {
            return false;
        }
        return this.mContext.getPackageManager().isPackageAvailable("com.coffeestainstudios.goatsimulator");
    }

    @SystemApi
    public boolean isPrimaryUser() {
        UserInfo userInfo = getUserInfo(UserHandle.myUserId());
        return userInfo != null && userInfo.isPrimary();
    }

    public boolean isSystemUser() {
        return UserHandle.myUserId() == 0;
    }

    @SystemApi
    public boolean isAdminUser() {
        return isUserAdmin(UserHandle.myUserId());
    }

    public boolean isUserAdmin(int i) {
        UserInfo userInfo = getUserInfo(i);
        return userInfo != null && userInfo.isAdmin();
    }

    @SystemApi
    public boolean isUserOfType(String str) {
        try {
            return this.mService.isUserOfType(this.mUserId, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static boolean isUserTypeManagedProfile(String str) {
        return USER_TYPE_PROFILE_MANAGED.equals(str);
    }

    public static boolean isUserTypeGuest(String str) {
        return USER_TYPE_FULL_GUEST.equals(str);
    }

    public static boolean isUserTypeRestricted(String str) {
        return USER_TYPE_FULL_RESTRICTED.equals(str);
    }

    public static boolean isUserTypeDemo(String str) {
        return USER_TYPE_FULL_DEMO.equals(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getUserTypeForStatsd(java.lang.String r8) {
        /*
            int r0 = r8.hashCode()
            r1 = 0
            r2 = 6
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            switch(r0) {
                case -1103927049: goto L4b;
                case -159818852: goto L41;
                case 34001850: goto L37;
                case 485661392: goto L2d;
                case 942013715: goto L23;
                case 1711075452: goto L19;
                case 1765400260: goto Lf;
                default: goto Le;
            }
        Le:
            goto L55
        Lf:
            java.lang.String r0 = "android.os.usertype.full.DEMO"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L55
            r8 = r5
            goto L56
        L19:
            java.lang.String r0 = "android.os.usertype.full.RESTRICTED"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L55
            r8 = r4
            goto L56
        L23:
            java.lang.String r0 = "android.os.usertype.full.SECONDARY"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L55
            r8 = r7
            goto L56
        L2d:
            java.lang.String r0 = "android.os.usertype.full.SYSTEM"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L55
            r8 = r1
            goto L56
        L37:
            java.lang.String r0 = "android.os.usertype.system.HEADLESS"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L55
            r8 = r2
            goto L56
        L41:
            java.lang.String r0 = "android.os.usertype.profile.MANAGED"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L55
            r8 = r3
            goto L56
        L4b:
            java.lang.String r0 = "android.os.usertype.full.GUEST"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L55
            r8 = r6
            goto L56
        L55:
            r8 = -1
        L56:
            switch(r8) {
                case 0: goto L61;
                case 1: goto L60;
                case 2: goto L5f;
                case 3: goto L5e;
                case 4: goto L5d;
                case 5: goto L5c;
                case 6: goto L5a;
                default: goto L59;
            }
        L59:
            return r1
        L5a:
            r8 = 7
            return r8
        L5c:
            return r2
        L5d:
            return r3
        L5e:
            return r4
        L5f:
            return r5
        L60:
            return r6
        L61:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.UserManager.getUserTypeForStatsd(java.lang.String):int");
    }

    @Deprecated
    public boolean isLinkedUser() {
        return isRestrictedProfile();
    }

    @SystemApi
    public boolean isRestrictedProfile() {
        try {
            return this.mService.isRestricted();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isRestrictedProfile(UserHandle userHandle) {
        try {
            return this.mService.getUserInfo(userHandle.getIdentifier()).isRestricted();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean canHaveRestrictedProfile(int i) {
        try {
            return this.mService.canHaveRestrictedProfile(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean hasRestrictedProfiles() {
        try {
            return this.mService.hasRestrictedProfiles();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isGuestUser(int i) {
        UserInfo userInfo = getUserInfo(i);
        return userInfo != null && userInfo.isGuest();
    }

    @SystemApi
    public boolean isGuestUser() {
        UserInfo userInfo = getUserInfo(UserHandle.myUserId());
        return userInfo != null && userInfo.isGuest();
    }

    public boolean isDemoUser() {
        try {
            return this.mService.isDemoUser(UserHandle.myUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isProfile() {
        return isProfile(this.mUserId);
    }

    private boolean isProfile(int i) {
        if (i == UserHandle.myUserId()) {
            Boolean bool = this.mIsProfileCached;
            if (bool != null) {
                return bool.booleanValue();
            }
            try {
                Boolean boolValueOf = Boolean.valueOf(this.mService.isProfile(i));
                this.mIsProfileCached = boolValueOf;
                return boolValueOf.booleanValue();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        try {
            return this.mService.isProfile(i);
        } catch (RemoteException e2) {
            throw e2.rethrowFromSystemServer();
        }
    }

    public boolean isManagedProfile() {
        Boolean bool = this.mIsManagedProfileCached;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            Boolean boolValueOf = Boolean.valueOf(this.mService.isManagedProfile(UserHandle.myUserId()));
            this.mIsManagedProfileCached = boolValueOf;
            return boolValueOf.booleanValue();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isManagedProfile(int i) {
        if (i == UserHandle.myUserId()) {
            return isManagedProfile();
        }
        try {
            return this.mService.isManagedProfile(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isEphemeralUser() {
        return isUserEphemeral(UserHandle.myUserId());
    }

    public boolean isUserEphemeral(int i) {
        UserInfo userInfo = getUserInfo(i);
        return userInfo != null && userInfo.isEphemeral();
    }

    public boolean isUserRunning(UserHandle userHandle) {
        return isUserRunning(userHandle.getIdentifier());
    }

    public boolean isUserRunning(int i) {
        try {
            return this.mService.isUserRunning(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isUserRunningOrStopping(UserHandle userHandle) {
        try {
            return ActivityManager.getService().isUserRunning(userHandle.getIdentifier(), 1);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isUserUnlocked() {
        return isUserUnlocked(Process.myUserHandle());
    }

    public boolean isUserUnlocked(UserHandle userHandle) {
        return isUserUnlocked(userHandle.getIdentifier());
    }

    public boolean isUserUnlocked(int i) {
        return this.mIsUserUnlockedCache.query(Integer.valueOf(i)).booleanValue();
    }

    public void disableIsUserUnlockedCache() {
        this.mIsUserUnlockedCache.disableLocal();
        this.mIsUserUnlockingOrUnlockedCache.disableLocal();
    }

    public static final void invalidateIsUserUnlockedCache() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_IS_USER_UNLOCKED_PROPERTY);
    }

    @SystemApi
    public boolean isUserUnlockingOrUnlocked(UserHandle userHandle) {
        return isUserUnlockingOrUnlocked(userHandle.getIdentifier());
    }

    public boolean isUserUnlockingOrUnlocked(int i) {
        return this.mIsUserUnlockingOrUnlockedCache.query(Integer.valueOf(i)).booleanValue();
    }

    public long getUserStartRealtime() {
        try {
            return this.mService.getUserStartRealtime();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public long getUserUnlockRealtime() {
        try {
            return this.mService.getUserUnlockRealtime();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public UserInfo getUserInfo(int i) {
        try {
            return this.mService.getUserInfo(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public int getUserRestrictionSource(String str, UserHandle userHandle) {
        try {
            return this.mService.getUserRestrictionSource(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<EnforcingUser> getUserRestrictionSources(String str, UserHandle userHandle) {
        try {
            return this.mService.getUserRestrictionSources(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Bundle getUserRestrictions() {
        return getUserRestrictions(Process.myUserHandle());
    }

    public Bundle getUserRestrictions(UserHandle userHandle) {
        try {
            return this.mService.getUserRestrictions(userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean hasBaseUserRestriction(String str, UserHandle userHandle) {
        try {
            return this.mService.hasBaseUserRestriction(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void setUserRestrictions(Bundle bundle) {
        throw new UnsupportedOperationException("This method is no longer supported");
    }

    @Deprecated
    public void setUserRestrictions(Bundle bundle, UserHandle userHandle) {
        throw new UnsupportedOperationException("This method is no longer supported");
    }

    @Deprecated
    public void setUserRestriction(String str, boolean z) {
        setUserRestriction(str, z, Process.myUserHandle());
    }

    @Deprecated
    public void setUserRestriction(String str, boolean z, UserHandle userHandle) {
        try {
            this.mService.setUserRestriction(str, z, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean hasUserRestriction(String str) {
        return hasUserRestrictionForUser(str, Process.myUserHandle());
    }

    public boolean hasUserRestriction(String str, UserHandle userHandle) {
        return hasUserRestrictionForUser(str, userHandle);
    }

    @SystemApi
    public boolean hasUserRestrictionForUser(String str, UserHandle userHandle) {
        try {
            return this.mService.hasUserRestriction(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean hasUserRestrictionOnAnyUser(String str) {
        try {
            return this.mService.hasUserRestrictionOnAnyUser(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isSettingRestrictedForUser(String str, int i, String str2, int i2) {
        try {
            return this.mService.isSettingRestrictedForUser(str, i, str2, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addUserRestrictionsListener(IUserRestrictionsListener iUserRestrictionsListener) {
        try {
            this.mService.addUserRestrictionsListener(iUserRestrictionsListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public long getSerialNumberForUser(UserHandle userHandle) {
        return getUserSerialNumber(userHandle.getIdentifier());
    }

    public UserHandle getUserForSerialNumber(long j) {
        int userHandle = getUserHandle((int) j);
        if (userHandle >= 0) {
            return new UserHandle(userHandle);
        }
        return null;
    }

    @Deprecated
    public UserInfo createUser(String str, int i) {
        return createUser(str, UserInfo.getDefaultUserType(i), i);
    }

    public UserInfo createUser(String str, String str2, int i) {
        try {
            return this.mService.createUserWithThrow(str, str2, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException unused) {
            return null;
        }
    }

    public UserInfo preCreateUser(String str) throws UserOperationException {
        try {
            return this.mService.preCreateUserWithThrow(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw UserOperationException.from(e2);
        }
    }

    public UserInfo createGuest(Context context, String str) {
        try {
            UserInfo userInfoCreateUserWithThrow = this.mService.createUserWithThrow(str, USER_TYPE_FULL_GUEST, 0);
            if (userInfoCreateUserWithThrow != null) {
                Settings.Secure.putStringForUser(context.getContentResolver(), Settings.Secure.SKIP_FIRST_USE_HINTS, "1", userInfoCreateUserWithThrow.id);
            }
            return userInfoCreateUserWithThrow;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException unused) {
            return null;
        }
    }

    public UserInfo findCurrentGuestUser() {
        try {
            return this.mService.findCurrentGuestUser();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public UserHandle createProfile(String str, String str2, Set<String> set) throws UserOperationException {
        try {
            return this.mService.createProfileForUserWithThrow(str, str2, 0, this.mUserId, (String[]) set.toArray(new String[set.size()])).getUserHandle();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            return (UserHandle) returnNullOrThrowUserOperationException(e2, this.mContext.getApplicationInfo().targetSdkVersion >= 30);
        }
    }

    @Deprecated
    public UserInfo createProfileForUser(String str, int i, int i2) {
        return createProfileForUser(str, UserInfo.getDefaultUserType(i), i, i2, null);
    }

    public UserInfo createProfileForUser(String str, String str2, int i, int i2) {
        return createProfileForUser(str, str2, i, i2, null);
    }

    public UserInfo createProfileForUser(String str, String str2, int i, int i2, String[] strArr) {
        try {
            return this.mService.createProfileForUserWithThrow(str, str2, i, i2, strArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException unused) {
            return null;
        }
    }

    public UserInfo createProfileForUserEvenWhenDisallowed(String str, String str2, int i, int i2, String[] strArr) {
        try {
            return this.mService.createProfileForUserEvenWhenDisallowedWithThrow(str, str2, i, i2, strArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException unused) {
            return null;
        }
    }

    public UserInfo createRestrictedProfile(String str) {
        try {
            UserHandle userHandleMyUserHandle = Process.myUserHandle();
            UserInfo userInfoCreateRestrictedProfileWithThrow = this.mService.createRestrictedProfileWithThrow(str, userHandleMyUserHandle.getIdentifier());
            if (userInfoCreateRestrictedProfileWithThrow != null) {
                AccountManager.get(this.mContext).addSharedAccountsFromParentUser(userHandleMyUserHandle, UserHandle.of(userInfoCreateRestrictedProfileWithThrow.id));
            }
            return userInfoCreateRestrictedProfileWithThrow;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException unused) {
            return null;
        }
    }

    public static Intent createUserCreationIntent(String str, String str2, String str3, PersistableBundle persistableBundle) {
        Intent intent = new Intent(ACTION_CREATE_USER);
        if (str != null) {
            intent.putExtra(EXTRA_USER_NAME, str);
        }
        if (str2 != null && str3 == null) {
            throw new IllegalArgumentException("accountType must be specified if accountName is specified");
        }
        if (str2 != null) {
            intent.putExtra(EXTRA_USER_ACCOUNT_NAME, str2);
        }
        if (str3 != null) {
            intent.putExtra(EXTRA_USER_ACCOUNT_TYPE, str3);
        }
        if (persistableBundle != null) {
            intent.putExtra(EXTRA_USER_ACCOUNT_OPTIONS, persistableBundle);
        }
        return intent;
    }

    @SystemApi
    public String getSeedAccountName() {
        try {
            return this.mService.getSeedAccountName();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public String getSeedAccountType() {
        try {
            return this.mService.getSeedAccountType();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public PersistableBundle getSeedAccountOptions() {
        try {
            return this.mService.getSeedAccountOptions();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setSeedAccountData(int i, String str, String str2, PersistableBundle persistableBundle) {
        try {
            this.mService.setSeedAccountData(i, str, str2, persistableBundle, true);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void clearSeedAccountData() {
        try {
            this.mService.clearSeedAccountData();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean markGuestForDeletion(int i) {
        try {
            return this.mService.markGuestForDeletion(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setUserEnabled(int i) {
        try {
            this.mService.setUserEnabled(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setUserAdmin(int i) {
        try {
            this.mService.setUserAdmin(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void evictCredentialEncryptionKey(int i) {
        try {
            this.mService.evictCredentialEncryptionKey(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getUserCount() {
        List<UserInfo> users = getUsers();
        if (users != null) {
            return users.size();
        }
        return 1;
    }

    public List<UserInfo> getUsers() {
        return getUsers(false);
    }

    public List<UserInfo> getUsers(boolean z) {
        return getUsers(true, z, true);
    }

    public List<UserInfo> getUsers(boolean z, boolean z2, boolean z3) {
        try {
            return this.mService.getUsers(z, z2, z3);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<UserHandle> getUserHandles(boolean z) {
        List<UserInfo> users = getUsers(z);
        ArrayList arrayList = new ArrayList(users.size());
        Iterator<UserInfo> it = users.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getUserHandle());
        }
        return arrayList;
    }

    @SystemApi
    public long[] getSerialNumbersOfUsers(boolean z) {
        int size = getUsers(z).size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = r6.get(i).serialNumber;
        }
        return jArr;
    }

    public String getUserAccount(int i) {
        try {
            return this.mService.getUserAccount(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setUserAccount(int i, String str) {
        try {
            this.mService.setUserAccount(i, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public UserInfo getPrimaryUser() {
        try {
            return this.mService.getPrimaryUser();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean canAddMoreUsers() {
        List<UserInfo> users = getUsers(true);
        int size = users.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (!users.get(i2).isGuest()) {
                i++;
            }
        }
        return i < getMaxSupportedUsers();
    }

    public boolean canAddMoreManagedProfiles(int i, boolean z) {
        try {
            return this.mService.canAddMoreManagedProfiles(i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean canAddMoreProfilesToUser(String str, int i) {
        try {
            return this.mService.canAddMoreProfilesToUser(str, i, false);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<UserInfo> getProfiles(int i) {
        try {
            return this.mService.getProfiles(i, false);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isSameProfileGroup(UserHandle userHandle, UserHandle userHandle2) {
        return isSameProfileGroup(userHandle.getIdentifier(), userHandle2.getIdentifier());
    }

    public boolean isSameProfileGroup(int i, int i2) {
        try {
            return this.mService.isSameProfileGroup(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<UserInfo> getEnabledProfiles(int i) {
        try {
            return this.mService.getProfiles(i, true);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<UserHandle> getUserProfiles() {
        int[] profileIds = getProfileIds(UserHandle.myUserId(), true);
        ArrayList arrayList = new ArrayList(profileIds.length);
        for (int i : profileIds) {
            arrayList.add(UserHandle.of(i));
        }
        return arrayList;
    }

    @SystemApi
    public List<UserHandle> getEnabledProfiles() {
        return getProfiles(true);
    }

    @SystemApi
    public List<UserHandle> getAllProfiles() {
        return getProfiles(false);
    }

    private List<UserHandle> getProfiles(boolean z) {
        int[] profileIds = getProfileIds(this.mUserId, z);
        ArrayList arrayList = new ArrayList(profileIds.length);
        for (int i : profileIds) {
            arrayList.add(UserHandle.of(i));
        }
        return arrayList;
    }

    public int[] getProfileIds(int i, boolean z) {
        try {
            return this.mService.getProfileIds(i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int[] getProfileIdsWithDisabled(int i) {
        return getProfileIds(i, false);
    }

    public int[] getEnabledProfileIds(int i) {
        return getProfileIds(i, true);
    }

    public int getCredentialOwnerProfile(int i) {
        try {
            return this.mService.getCredentialOwnerProfile(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public UserInfo getProfileParent(int i) {
        try {
            return this.mService.getProfileParent(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public UserHandle getProfileParent(UserHandle userHandle) {
        UserInfo profileParent = getProfileParent(userHandle.getIdentifier());
        if (profileParent == null) {
            return null;
        }
        return UserHandle.of(profileParent.id);
    }

    public boolean requestQuietModeEnabled(boolean z, UserHandle userHandle) {
        return requestQuietModeEnabled(z, userHandle, (IntentSender) null);
    }

    public boolean requestQuietModeEnabled(boolean z, UserHandle userHandle, int i) {
        return requestQuietModeEnabled(z, userHandle, null, i);
    }

    public boolean requestQuietModeEnabled(boolean z, UserHandle userHandle, IntentSender intentSender) {
        return requestQuietModeEnabled(z, userHandle, intentSender, 0);
    }

    public boolean requestQuietModeEnabled(boolean z, UserHandle userHandle, IntentSender intentSender, int i) {
        try {
            return this.mService.requestQuietModeEnabled(this.mContext.getPackageName(), z, userHandle.getIdentifier(), intentSender, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isQuietModeEnabled(UserHandle userHandle) {
        try {
            return this.mService.isQuietModeEnabled(userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean hasBadge(int i) {
        if (!isProfile(i)) {
            return false;
        }
        try {
            return this.mService.hasBadge(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean hasBadge() {
        return hasBadge(this.mUserId);
    }

    public int getUserBadgeColor(int i) {
        try {
            return Resources.getSystem().getColor(this.mService.getUserBadgeColorResId(i), null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getUserBadgeDarkColor(int i) {
        try {
            return Resources.getSystem().getColor(this.mService.getUserBadgeDarkColorResId(i), null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getUserIconBadgeResId(int i) {
        try {
            return this.mService.getUserIconBadgeResId(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getUserBadgeResId(int i) {
        try {
            return this.mService.getUserBadgeResId(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getUserBadgeNoBackgroundResId(int i) {
        try {
            return this.mService.getUserBadgeNoBackgroundResId(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Drawable getBadgedIconForUser(Drawable drawable, UserHandle userHandle) {
        return this.mContext.getPackageManager().getUserBadgedIcon(drawable, userHandle);
    }

    public Drawable getBadgedDrawableForUser(Drawable drawable, UserHandle userHandle, Rect rect, int i) {
        return this.mContext.getPackageManager().getUserBadgedDrawableForDensity(drawable, userHandle, rect, i);
    }

    public CharSequence getBadgedLabelForUser(CharSequence charSequence, UserHandle userHandle) {
        int identifier = userHandle.getIdentifier();
        if (!hasBadge(identifier)) {
            return charSequence;
        }
        try {
            return Resources.getSystem().getString(this.mService.getUserBadgeLabelResId(identifier), charSequence);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean removeUser(int i) {
        try {
            return this.mService.removeUser(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean removeUser(UserHandle userHandle) {
        if (userHandle == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        return removeUser(userHandle.getIdentifier());
    }

    public boolean removeUserEvenWhenDisallowed(int i) {
        try {
            return this.mService.removeUserEvenWhenDisallowed(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setUserName(int i, String str) {
        try {
            this.mService.setUserName(i, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setUserName(String str) {
        setUserName(this.mUserId, str);
    }

    public void setUserIcon(int i, Bitmap bitmap) {
        try {
            this.mService.setUserIcon(i, bitmap);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException unused) {
        }
    }

    @SystemApi
    public void setUserIcon(Bitmap bitmap) throws UserOperationException {
        setUserIcon(this.mUserId, bitmap);
    }

    public Bitmap getUserIcon(int i) {
        try {
            ParcelFileDescriptor userIcon = this.mService.getUserIcon(i);
            if (userIcon == null) {
                return null;
            }
            try {
                return BitmapFactory.decodeFileDescriptor(userIcon.getFileDescriptor());
            } finally {
                try {
                    userIcon.close();
                } catch (IOException unused) {
                }
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public Bitmap getUserIcon() {
        return getUserIcon(this.mUserId);
    }

    public static int getMaxSupportedUsers() {
        if (Build.ID.startsWith("JVP")) {
            return 1;
        }
        return SystemProperties.getInt("fw.max_users", Resources.getSystem().getInteger(R.integer.config_multiuserMaximumUsers));
    }

    public boolean isUserSwitcherEnabled() {
        return isUserSwitcherEnabled(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isUserSwitcherEnabled(boolean z) {
        if (!supportsMultipleUsers() || hasUserRestriction(DISALLOW_USER_SWITCH) || isDeviceInDemoMode(this.mContext)) {
            return false;
        }
        if ((Settings.Global.getInt(this.mContext.getContentResolver(), Settings.Global.USER_SWITCHER_ENABLED, Resources.getSystem().getBoolean(R.bool.config_showUserSwitcherByDefault) ? 1 : 0) != 0) == true) {
            return z || areThereUsersToWhichToSwitch() || !hasUserRestriction(DISALLOW_ADD_USER);
        }
        return false;
    }

    private boolean areThereUsersToWhichToSwitch() {
        List<UserInfo> users = getUsers(true);
        if (users == null) {
            return false;
        }
        Iterator<UserInfo> it = users.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().supportsSwitchToByUser()) {
                i++;
            }
        }
        return i > 1;
    }

    public static boolean isDeviceInDemoMode(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Settings.Global.DEVICE_DEMO_MODE, 0) > 0;
    }

    public int getUserSerialNumber(int i) {
        try {
            return this.mService.getUserSerialNumber(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getUserHandle(int i) {
        try {
            return this.mService.getUserHandle(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Bundle getApplicationRestrictions(String str) {
        try {
            return this.mService.getApplicationRestrictions(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Bundle getApplicationRestrictions(String str, UserHandle userHandle) {
        try {
            return this.mService.getApplicationRestrictionsForUser(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setApplicationRestrictions(String str, Bundle bundle, UserHandle userHandle) {
        try {
            this.mService.setApplicationRestrictions(str, bundle, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setDefaultGuestRestrictions(Bundle bundle) {
        try {
            this.mService.setDefaultGuestRestrictions(bundle);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Bundle getDefaultGuestRestrictions() {
        try {
            return this.mService.getDefaultGuestRestrictions();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public long getUserCreationTime(UserHandle userHandle) {
        try {
            return this.mService.getUserCreationTime(userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean someUserHasSeedAccount(String str, String str2) {
        try {
            return this.mService.someUserHasSeedAccount(str, str2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public static final class EnforcingUser implements Parcelable {
        public static final Parcelable.Creator<EnforcingUser> CREATOR = new Parcelable.Creator<EnforcingUser>() { // from class: android.os.UserManager.EnforcingUser.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public EnforcingUser createFromParcel(Parcel parcel) {
                return new EnforcingUser(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public EnforcingUser[] newArray(int i) {
                return new EnforcingUser[i];
            }
        };
        private final int userId;
        private final int userRestrictionSource;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public EnforcingUser(int i, int i2) {
            this.userId = i;
            this.userRestrictionSource = i2;
        }

        private EnforcingUser(Parcel parcel) {
            this.userId = parcel.readInt();
            this.userRestrictionSource = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.userId);
            parcel.writeInt(this.userRestrictionSource);
        }

        public UserHandle getUserHandle() {
            return UserHandle.of(this.userId);
        }

        public int getUserRestrictionSource() {
            return this.userRestrictionSource;
        }
    }
}
