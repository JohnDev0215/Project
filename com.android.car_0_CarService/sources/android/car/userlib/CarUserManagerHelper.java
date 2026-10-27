package android.car.userlib;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.os.UserManager;
import android.provider.Settings;
import android.sysprop.CarProperties;
import android.util.Log;
import com.android.internal.util.UserIcons;
import com.google.android.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class CarUserManagerHelper {
    private static final int BOOT_USER_NOT_FOUND = -1;
    private static final boolean DEBUG = false;
    private static final Set<String> DEFAULT_NON_ADMIN_RESTRICTIONS = Sets.newArraySet(new String[]{"no_factory_reset"});
    private static final Set<String> OPTIONAL_NON_ADMIN_RESTRICTIONS = Sets.newArraySet(new String[]{"no_add_user", "no_outgoing_calls", "no_sms", "no_install_apps", "no_uninstall_apps"});
    private static final String TAG = "CarUserManagerHelper";
    private final ActivityManager mActivityManager;
    private final Context mContext;
    private final UserManager mUserManager;

    public CarUserManagerHelper(Context context) {
        this.mContext = context;
        this.mUserManager = UserManager.get(context);
        this.mActivityManager = (ActivityManager) this.mContext.getSystemService("activity");
    }

    public void setLastActiveUser(int userId) {
        if (UserHelper.isHeadlessSystemUser(userId)) {
            return;
        }
        setUserIdGlobalProperty("android.car.LAST_ACTIVE_USER_ID", userId);
        UserInfo user = this.mUserManager.getUserInfo(userId);
        if (user == null) {
            Log.w(TAG, "setLastActiveUser(): user " + userId + " doesn't exist");
            return;
        }
        if (!user.isEphemeral()) {
            setUserIdGlobalProperty("android.car.LAST_ACTIVE_PERSISTENT_USER_ID", userId);
        }
    }

    private void setUserIdGlobalProperty(String name, int userId) {
        Settings.Global.putInt(this.mContext.getContentResolver(), name, userId);
    }

    private int getUserIdGlobalProperty(String name) {
        int userId = Settings.Global.getInt(this.mContext.getContentResolver(), name, -10000);
        return userId;
    }

    private void resetUserIdGlobalProperty(String name) {
        Settings.Global.putInt(this.mContext.getContentResolver(), name, -10000);
    }

    int getInitialUser(boolean usesOverrideUserIdProperty) {
        int bootUserOverride;
        List<Integer> allUsers = userInfoListToUserIdList(getAllUsers());
        if (allUsers.isEmpty()) {
            return -10000;
        }
        if (usesOverrideUserIdProperty && (bootUserOverride = ((Integer) CarProperties.boot_user_override_id().orElse(-1)).intValue()) != -1 && allUsers.contains(Integer.valueOf(bootUserOverride))) {
            Log.i(TAG, "Boot user id override found for initial user, user id: " + bootUserOverride);
            return bootUserOverride;
        }
        int lastActiveUser = getUserIdGlobalProperty("android.car.LAST_ACTIVE_USER_ID");
        if (allUsers.contains(Integer.valueOf(lastActiveUser))) {
            Log.i(TAG, "Last active user loaded for initial user: " + lastActiveUser);
            return lastActiveUser;
        }
        resetUserIdGlobalProperty("android.car.LAST_ACTIVE_USER_ID");
        int lastPersistentUser = getUserIdGlobalProperty("android.car.LAST_ACTIVE_PERSISTENT_USER_ID");
        if (allUsers.contains(Integer.valueOf(lastPersistentUser))) {
            Log.i(TAG, "Last active, persistent user loaded for initial user: " + lastPersistentUser);
            return lastPersistentUser;
        }
        resetUserIdGlobalProperty("android.car.LAST_ACTIVE_PERSISTENT_USER_ID");
        int returnId = ((Integer) Collections.min(allUsers)).intValue();
        Log.w(TAG, "Last active user (" + lastActiveUser + ") not found. Returning smallest user id instead: " + returnId);
        return returnId;
    }

    public boolean hasInitialUser() {
        List<UserInfo> allUsers = getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            UserInfo user = allUsers.get(i);
            if (!user.isManagedProfile()) {
                return true;
            }
        }
        return false;
    }

    private static List<Integer> userInfoListToUserIdList(List<UserInfo> allUsers) {
        ArrayList<Integer> list = new ArrayList<>(allUsers.size());
        for (UserInfo userInfo : allUsers) {
            list.add(Integer.valueOf(userInfo.id));
        }
        return list;
    }

    private List<UserInfo> getAllUsers() {
        if (UserManager.isHeadlessSystemUserMode()) {
            return getAllUsersExceptSystemUserAndSpecifiedUser(0);
        }
        return this.mUserManager.getUsers(true);
    }

    private List<UserInfo> getAllUsersExceptSystemUserAndSpecifiedUser(int userId) {
        List<UserInfo> users = this.mUserManager.getUsers(true);
        Iterator<UserInfo> iterator = users.iterator();
        while (iterator.hasNext()) {
            UserInfo userInfo = iterator.next();
            if (userInfo.id == userId || userInfo.id == 0) {
                iterator.remove();
            }
        }
        return users;
    }

    public void grantAdminPermissions(UserInfo user) {
        if (!this.mUserManager.isAdminUser()) {
            Log.w(TAG, "Only admin users can assign admin permissions.");
            return;
        }
        this.mUserManager.setUserAdmin(user.id);
        setDefaultNonAdminRestrictions(user, false);
        setOptionalNonAdminRestrictions(user, false);
    }

    @Deprecated
    public UserInfo createNewNonAdminUser(String userName) {
        UserInfo user = this.mUserManager.createUser(userName, 0);
        if (user == null) {
            Log.w(TAG, "can't create non-admin user.");
            return null;
        }
        setDefaultNonAdminRestrictions(user, true);
        assignDefaultIcon(user);
        return user;
    }

    @Deprecated
    public void setDefaultNonAdminRestrictions(UserInfo userInfo, boolean enable) {
        for (String restriction : DEFAULT_NON_ADMIN_RESTRICTIONS) {
            this.mUserManager.setUserRestriction(restriction, enable, userInfo.getUserHandle());
        }
    }

    private void setOptionalNonAdminRestrictions(UserInfo userInfo, boolean enable) {
        for (String restriction : OPTIONAL_NON_ADMIN_RESTRICTIONS) {
            this.mUserManager.setUserRestriction(restriction, enable, userInfo.getUserHandle());
        }
    }

    @Deprecated
    public boolean switchToUserId(int id) {
        if (UserHelper.isHeadlessSystemUser(id) || this.mUserManager.getUserSwitchability() != 0 || id == ActivityManager.getCurrentUser()) {
            return false;
        }
        return this.mActivityManager.switchUser(id);
    }

    @Deprecated
    public boolean switchToUser(UserInfo userInfo) {
        return switchToUserId(userInfo.id);
    }

    private Bitmap assignDefaultIcon(UserInfo userInfo) {
        int idForIcon = userInfo.isGuest() ? -10000 : userInfo.id;
        Bitmap bitmap = UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(this.mContext.getResources(), idForIcon, false));
        this.mUserManager.setUserIcon(userInfo.id, bitmap);
        return bitmap;
    }
}
