package com.android.car.settings.users;

import android.R;
import android.app.ActivityManager;
import android.car.Car;
import android.car.user.CarUserManager;
import android.car.user.UserCreationResult;
import android.car.user.UserRemovalResult;
import android.car.user.UserSwitchResult;
import android.content.Context;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.os.UserHandle;
import android.os.UserManager;
import android.sysprop.CarProperties;
import android.util.Log;
import com.android.internal.infra.AndroidFuture;
import com.android.wifitrackerlib.WifiEntry;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes5.dex */
public class UserHelper {
    private static final String TAG = "UserHelper";
    private static final int TIMEOUT_MS = ((Integer) CarProperties.user_hal_timeout().orElse(Integer.valueOf(WifiEntry.FREQUENCY_5_GHZ))).intValue() + 500;
    private static UserHelper sInstance;
    private final CarUserManager mCarUserManager;
    private final String mDefaultAdminName;
    private final String mDefaultGuestName;
    private final Resources mResources;
    private final UserManager mUserManager;

    public static UserHelper getInstance(Context context) {
        if (sInstance == null) {
            Context appContext = context.getApplicationContext();
            Resources resources = appContext.getResources();
            sInstance = new UserHelper(UserManager.get(appContext), resources, resources.getString(R.string.keyguard_password_wrong_pin_code), resources.getString(com.android.car.settings.R.string.user_guest), getCarUserManager(appContext));
        }
        return sInstance;
    }

    UserHelper(UserManager userManager, Resources resources, String defaultAdminName, String defaultGuestName, CarUserManager carUserManager) {
        this.mUserManager = userManager;
        this.mResources = resources;
        this.mDefaultAdminName = defaultAdminName;
        this.mDefaultGuestName = defaultGuestName;
        this.mCarUserManager = carUserManager;
    }

    private static CarUserManager getCarUserManager(Context context) {
        Car car = Car.createCar(context);
        CarUserManager carUserManager = (CarUserManager) car.getCarManager("car_user_service");
        return carUserManager;
    }

    public boolean removeUser(Context context, UserInfo userInfo) {
        if (userInfo.id == 0) {
            Log.w(TAG, "User " + userInfo.id + " is system user, could not be removed.");
            return false;
        }
        if (userInfo.isAdmin() && getAllAdminUsers().size() <= 1) {
            return removeLastAdmin(userInfo);
        }
        if (!this.mUserManager.isAdminUser() && !isCurrentProcessUser(userInfo)) {
            Log.e(TAG, "Non-admins cannot remove other users.");
            return false;
        }
        if (userInfo.id == ActivityManager.getCurrentUser()) {
            if (this.mUserManager.getUserSwitchability() != 0) {
                Log.w(TAG, "User switching is not allowed. Current user cannot be deleted");
                return false;
            }
            UserInfo guestUser = createNewOrFindExistingGuest(context);
            if (guestUser == null) {
                Log.e(TAG, "Could not create a Guest user.");
                return false;
            }
            if (!switchUser(guestUser.id)) {
                return false;
            }
        }
        return removeUser(userInfo.id);
    }

    private boolean removeUser(int userId) {
        UserRemovalResult userRemovalResult = this.mCarUserManager.removeUser(userId);
        if (userRemovalResult == null || !userRemovalResult.isSuccess()) {
            Log.w(TAG, "Could not remove user. " + userRemovalResult);
            return false;
        }
        return true;
    }

    private boolean switchUser(int userId) {
        AndroidFuture<UserSwitchResult> userSwitchResultFuture = this.mCarUserManager.switchUser(userId);
        try {
            UserSwitchResult userSwitchResult = (UserSwitchResult) userSwitchResultFuture.get(TIMEOUT_MS, TimeUnit.MILLISECONDS);
            if (userSwitchResult != null && userSwitchResult.isSuccess()) {
                return true;
            }
            Log.w(TAG, "Could not switch user. " + userSwitchResult);
            return false;
        } catch (Exception e) {
            Log.w(TAG, "Could not switch user.", e);
            return false;
        }
    }

    private UserInfo getUserInfo(AndroidFuture<UserCreationResult> future) {
        try {
            UserCreationResult userCreationResult = (UserCreationResult) future.get(TIMEOUT_MS, TimeUnit.MILLISECONDS);
            if (userCreationResult == null || !userCreationResult.isSuccess() || userCreationResult.getUser() == null) {
                Log.w(TAG, "Could not create user. " + userCreationResult);
                return null;
            }
            return userCreationResult.getUser();
        } catch (Exception e) {
            Log.w(TAG, "Could not create user.", e);
            return null;
        }
    }

    private boolean removeLastAdmin(UserInfo userInfo) {
        if (Log.isLoggable(TAG, 4)) {
            Log.i(TAG, "User " + userInfo.id + " is the last admin user on device. Creating a new admin.");
        }
        UserInfo newAdmin = createNewAdminUser(this.mDefaultAdminName);
        if (newAdmin == null) {
            Log.w(TAG, "Couldn't create another admin, cannot delete current user.");
            return false;
        }
        if (!switchUser(newAdmin.id)) {
            return false;
        }
        return removeUser(userInfo.id);
    }

    private UserInfo createNewAdminUser(String userName) {
        if (!this.mUserManager.isAdminUser() && !this.mUserManager.isSystemUser()) {
            Log.e(TAG, "Only admin users and system user can create other admins.");
            return null;
        }
        AndroidFuture<UserCreationResult> future = this.mCarUserManager.createUser(userName, 2);
        UserInfo user = getUserInfo(future);
        if (user == null) {
            return null;
        }
        new UserIconProvider().assignDefaultIcon(this.mUserManager, this.mResources, user);
        return user;
    }

    public UserInfo createNewOrFindExistingGuest(Context context) {
        AndroidFuture<UserCreationResult> future = this.mCarUserManager.createGuest(this.mDefaultGuestName);
        UserInfo newGuest = getUserInfo(future);
        if (newGuest != null) {
            new UserIconProvider().assignDefaultIcon(this.mUserManager, this.mResources, newGuest);
            return newGuest;
        }
        return this.mUserManager.findCurrentGuestUser();
    }

    public boolean canCurrentProcessModifyAccounts() {
        return (this.mUserManager.hasUserRestriction("no_modify_accounts") || this.mUserManager.isDemoUser() || this.mUserManager.isGuestUser()) ? false : true;
    }

    public List<UserInfo> getAllUsers() {
        return getAllLivingUsers(null);
    }

    public List<UserInfo> getAllSwitchableUsers() {
        final int foregroundUserId = ActivityManager.getCurrentUser();
        return getAllLivingUsers(new Predicate() { // from class: com.android.car.settings.users.-$$Lambda$UserHelper$qpX05a588OGN7eTfMvVeXwb2G7g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return UserHelper.lambda$getAllSwitchableUsers$0(foregroundUserId, (UserInfo) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$getAllSwitchableUsers$0(int foregroundUserId, UserInfo userInfo) {
        return userInfo.id != foregroundUserId;
    }

    static /* synthetic */ boolean lambda$getAllPersistentUsers$1(UserInfo userInfo) {
        return !userInfo.isEphemeral();
    }

    public List<UserInfo> getAllPersistentUsers() {
        return getAllLivingUsers(new Predicate() { // from class: com.android.car.settings.users.-$$Lambda$UserHelper$tic6g0KdxTvdo284p4j-MTQUxeo
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return UserHelper.lambda$getAllPersistentUsers$1((UserInfo) obj);
            }
        });
    }

    public List<UserInfo> getAllAdminUsers() {
        return getAllLivingUsers(new Predicate() { // from class: com.android.car.settings.users.-$$Lambda$ibG9EXksDgRROWiKUtXnJ2AIXrI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((UserInfo) obj).isAdmin();
            }
        });
    }

    public List<UserInfo> getAllLivingUsers(Predicate<? super UserInfo> filter) {
        Stream<UserInfo> filteredListStream = this.mUserManager.getUsers(true).stream();
        if (filter != null) {
            filteredListStream = filteredListStream.filter(filter);
        }
        if (UserManager.isHeadlessSystemUserMode()) {
            filteredListStream = filteredListStream.filter(new Predicate() { // from class: com.android.car.settings.users.-$$Lambda$UserHelper$eE2dTOVapKajvRboOR-y_6WUcSk
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return UserHelper.lambda$getAllLivingUsers$2((UserInfo) obj);
                }
            });
        }
        return (List) filteredListStream.collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$getAllLivingUsers$2(UserInfo userInfo) {
        return userInfo.id != 0;
    }

    public boolean isCurrentProcessUser(UserInfo userInfo) {
        return UserHandle.myUserId() == userInfo.id;
    }

    public UserInfo getCurrentProcessUserInfo() {
        return this.mUserManager.getUserInfo(UserHandle.myUserId());
    }

    private int getMaxSupportedUsers() {
        int maxSupportedUsers = UserManager.getMaxSupportedUsers();
        if (UserManager.isHeadlessSystemUserMode()) {
            return maxSupportedUsers - 1;
        }
        return maxSupportedUsers;
    }

    private int getManagedProfilesCount() {
        List<UserInfo> users = getAllUsers();
        int managedProfilesCount = 0;
        for (UserInfo user : users) {
            if (user.isManagedProfile()) {
                managedProfilesCount++;
            }
        }
        return managedProfilesCount;
    }

    public int getMaxSupportedRealUsers() {
        return getMaxSupportedUsers() - getManagedProfilesCount();
    }
}
