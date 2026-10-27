package android.car.userlib;

import android.app.ActivityManager;
import android.content.pm.UserInfo;
import android.hardware.automotive.vehicle.V2_0.CreateUserRequest;
import android.hardware.automotive.vehicle.V2_0.InitialUserInfoResponse;
import android.hardware.automotive.vehicle.V2_0.RemoveUserRequest;
import android.hardware.automotive.vehicle.V2_0.SwitchUserRequest;
import android.hardware.automotive.vehicle.V2_0.UserFlags;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationAssociation;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationGetRequest;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationResponse;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationSetAssociation;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationSetRequest;
import android.hardware.automotive.vehicle.V2_0.UsersInfo;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.os.SystemClock;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.DebugUtils;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class UserHalHelper {
    public static final int CREATE_USER_PROPERTY = 299896585;
    private static final boolean DEBUG = false;
    public static final int INITIAL_USER_INFO_PROPERTY = 299896583;
    public static final int REMOVE_USER_PROPERTY = 299896586;
    private static final String STRING_SEPARATOR = "\\|\\|";
    public static final int SWITCH_USER_PROPERTY = 299896584;
    private static final String TAG = UserHalHelper.class.getSimpleName();
    public static final int USER_IDENTIFICATION_ASSOCIATION_PROPERTY = 299896587;

    public static String halCallbackStatusToString(int status) {
        if (status == 1) {
            return "OK";
        }
        if (status == 2) {
            return "HAL_SET_TIMEOUT";
        }
        if (status == 3) {
            return "HAL_RESPONSE_TIMEOUT";
        }
        if (status == 4) {
            return "WRONG_HAL_RESPONSE";
        }
        if (status == 5) {
            return "CONCURRENT_OPERATION";
        }
        return "UNKNOWN-" + status;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int parseInitialUserInfoRequestType(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -1881097171: goto L29;
                case 170062701: goto L1f;
                case 278133371: goto L15;
                case 353315649: goto Lb;
                default: goto La;
            }
        La:
            goto L33
        Lb:
            java.lang.String r0 = "FIRST_BOOT"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto La
            r0 = 0
            goto L34
        L15:
            java.lang.String r0 = "FIRST_BOOT_AFTER_OTA"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto La
            r0 = r3
            goto L34
        L1f:
            java.lang.String r0 = "COLD_BOOT"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto La
            r0 = r2
            goto L34
        L29:
            java.lang.String r0 = "RESUME"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto La
            r0 = r1
            goto L34
        L33:
            r0 = -1
        L34:
            if (r0 == 0) goto L5d
            if (r0 == r3) goto L5c
            if (r0 == r2) goto L5b
            if (r0 == r1) goto L59
            int r0 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L41
            return r0
        L41:
            r0 = move-exception
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "invalid type: "
            r2.append(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L59:
            r0 = 4
            return r0
        L5b:
            return r1
        L5c:
            return r2
        L5d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.car.userlib.UserHalHelper.parseInitialUserInfoRequestType(java.lang.String):int");
    }

    public static int convertFlags(UserInfo user) {
        Preconditions.checkArgument(user != null, "user cannot be null");
        int flags = 0;
        if (user.id == 0) {
            flags = 0 | 1;
        }
        if (user.isAdmin()) {
            flags |= 8;
        }
        if (user.isGuest()) {
            flags |= 2;
        }
        if (user.isEphemeral()) {
            flags |= 4;
        }
        if (!user.isEnabled()) {
            flags |= 16;
        }
        if (user.isProfile()) {
            return flags | 32;
        }
        return flags;
    }

    public static int getFlags(UserManager um, int userId) {
        Preconditions.checkArgument(um != null, "UserManager cannot be null");
        UserInfo user = um.getUserInfo(userId);
        Preconditions.checkArgument(user != null, "No user with id %d", new Object[]{Integer.valueOf(userId)});
        return convertFlags(user);
    }

    public static boolean isSystem(int flags) {
        return (flags & 1) != 0;
    }

    public static boolean isGuest(int flags) {
        return (flags & 2) != 0;
    }

    public static boolean isEphemeral(int flags) {
        return (flags & 4) != 0;
    }

    public static boolean isAdmin(int flags) {
        return (flags & 8) != 0;
    }

    public static boolean isDisabled(int flags) {
        return (flags & 16) != 0;
    }

    public static boolean isProfile(int flags) {
        return (flags & 32) != 0;
    }

    public static int toUserInfoFlags(int halFlags) {
        int flags = 0;
        if (isEphemeral(halFlags)) {
            flags = 0 | 256;
        }
        if (isAdmin(halFlags)) {
            return flags | 2;
        }
        return flags;
    }

    public static String userFlagsToString(int flags) {
        return DebugUtils.flagsToString(UserFlags.class, "", flags);
    }

    public static VehiclePropValue createPropRequest(int prop, int requestId, int requestType) {
        VehiclePropValue propRequest = createPropRequest(prop, requestId);
        propRequest.value.int32Values.add(Integer.valueOf(requestType));
        return propRequest;
    }

    public static VehiclePropValue createPropRequest(int prop, int requestId) {
        VehiclePropValue propRequest = new VehiclePropValue();
        propRequest.prop = prop;
        propRequest.timestamp = SystemClock.elapsedRealtime();
        propRequest.value.int32Values.add(Integer.valueOf(requestId));
        return propRequest;
    }

    public static void addUsersInfo(VehiclePropValue propRequest, UsersInfo usersInfo) {
        Objects.requireNonNull(propRequest, "VehiclePropValue cannot be null");
        Objects.requireNonNull(usersInfo.currentUser, "Current user cannot be null");
        Preconditions.checkArgument(usersInfo.numberUsers == usersInfo.existingUsers.size(), "Number of existing users info does not match numberUsers");
        addUserInfo(propRequest, usersInfo.currentUser);
        propRequest.value.int32Values.add(Integer.valueOf(usersInfo.numberUsers));
        for (int i = 0; i < usersInfo.numberUsers; i++) {
            android.hardware.automotive.vehicle.V2_0.UserInfo userInfo = (android.hardware.automotive.vehicle.V2_0.UserInfo) usersInfo.existingUsers.get(i);
            addUserInfo(propRequest, userInfo);
        }
    }

    public static void addUserInfo(VehiclePropValue propRequest, android.hardware.automotive.vehicle.V2_0.UserInfo userInfo) {
        Objects.requireNonNull(propRequest, "VehiclePropValue cannot be null");
        Objects.requireNonNull(userInfo, "UserInfo cannot be null");
        propRequest.value.int32Values.add(Integer.valueOf(userInfo.userId));
        propRequest.value.int32Values.add(Integer.valueOf(userInfo.flags));
    }

    public static boolean isValidUserIdentificationAssociationType(int type) {
        if (type != 1) {
            switch (type) {
                case 101:
                case 102:
                case 103:
                case 104:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static boolean isValidUserIdentificationAssociationValue(int value) {
        if (value == 1 || value == 2 || value == 3 || value == 4) {
            return true;
        }
        return false;
    }

    public static boolean isValidUserIdentificationAssociationSetValue(int value) {
        if (value == 1 || value == 2 || value == 3) {
            return true;
        }
        return false;
    }

    public static UserIdentificationResponse toUserIdentificationResponse(VehiclePropValue prop) {
        Objects.requireNonNull(prop, "prop cannot be null");
        Preconditions.checkArgument(prop.prop == 299896587, "invalid prop on %s", new Object[]{prop});
        assertMinimumSize(prop, 4);
        int requestId = ((Integer) prop.value.int32Values.get(0)).intValue();
        Preconditions.checkArgument(requestId > 0, "invalid request id (%d) on %s", new Object[]{Integer.valueOf(requestId), prop});
        int numberAssociations = ((Integer) prop.value.int32Values.get(1)).intValue();
        Preconditions.checkArgument(numberAssociations >= 1, "invalid number of items on %s", new Object[]{prop});
        int numberItems = prop.value.int32Values.size() - 2;
        Preconditions.checkArgument(numberItems == numberAssociations * 2, "number of items mismatch on %s", new Object[]{prop});
        UserIdentificationResponse response = new UserIdentificationResponse();
        response.requestId = requestId;
        response.errorMessage = prop.value.stringValue;
        response.numberAssociation = numberAssociations;
        int i = 2;
        int a = 0;
        while (a < numberAssociations) {
            UserIdentificationAssociation association = new UserIdentificationAssociation();
            int i2 = i + 1;
            association.type = ((Integer) prop.value.int32Values.get(i)).intValue();
            Preconditions.checkArgument(isValidUserIdentificationAssociationType(association.type), "invalid type at index %d on %s", new Object[]{Integer.valueOf(i), prop});
            association.value = ((Integer) prop.value.int32Values.get(i2)).intValue();
            Preconditions.checkArgument(isValidUserIdentificationAssociationValue(association.value), "invalid value at index %d on %s", new Object[]{Integer.valueOf(i2), prop});
            response.associations.add(association);
            a++;
            i = i2 + 1;
        }
        return response;
    }

    public static InitialUserInfoResponse toInitialUserInfoResponse(VehiclePropValue prop) {
        Objects.requireNonNull(prop, "prop cannot be null");
        Preconditions.checkArgument(prop.prop == 299896583, "invalid prop on %s", new Object[]{prop});
        assertMinimumSize(prop, 2);
        int requestId = ((Integer) prop.value.int32Values.get(0)).intValue();
        Preconditions.checkArgument(requestId > 0, "invalid request id (%d) on %s", new Object[]{Integer.valueOf(requestId), prop});
        InitialUserInfoResponse response = new InitialUserInfoResponse();
        response.requestId = requestId;
        response.action = ((Integer) prop.value.int32Values.get(1)).intValue();
        String[] stringValues = null;
        if (!TextUtils.isEmpty(prop.value.stringValue)) {
            stringValues = TextUtils.split(prop.value.stringValue, STRING_SEPARATOR);
        }
        if (stringValues != null && stringValues.length > 0) {
            response.userLocales = stringValues[0];
        }
        int i = response.action;
        if (i == 0) {
            response.userToSwitchOrCreate.userId = -10000;
            response.userToSwitchOrCreate.flags = 0;
        } else if (i != 1) {
            if (i == 2) {
                assertMinimumSize(prop, 4);
                response.userToSwitchOrCreate.userId = -10000;
                response.userToSwitchOrCreate.flags = ((Integer) prop.value.int32Values.get(3)).intValue();
                if (stringValues.length > 1) {
                    response.userNameToCreate = stringValues[1];
                }
            } else {
                throw new IllegalArgumentException("Invalid response action (" + response.action + " on " + prop);
            }
        } else {
            assertMinimumSize(prop, 3);
            response.userToSwitchOrCreate.userId = ((Integer) prop.value.int32Values.get(2)).intValue();
            response.userToSwitchOrCreate.flags = 0;
        }
        return response;
    }

    public static VehiclePropValue toVehiclePropValue(UserIdentificationGetRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        Preconditions.checkArgument(request.numberAssociationTypes > 0, "invalid number of association types mismatch on %s", new Object[]{request});
        Preconditions.checkArgument(request.numberAssociationTypes == request.associationTypes.size(), "number of association types mismatch on %s", new Object[]{request});
        Preconditions.checkArgument(request.requestId > 0, "invalid requestId on %s", new Object[]{request});
        VehiclePropValue propValue = createPropRequest(USER_IDENTIFICATION_ASSOCIATION_PROPERTY, request.requestId);
        addUserInfo(propValue, request.userInfo);
        propValue.value.int32Values.add(Integer.valueOf(request.numberAssociationTypes));
        for (int i = 0; i < request.numberAssociationTypes; i++) {
            int type = ((Integer) request.associationTypes.get(i)).intValue();
            Preconditions.checkArgument(isValidUserIdentificationAssociationType(type), "invalid type at index %d on %s", new Object[]{Integer.valueOf(i), request});
            propValue.value.int32Values.add(Integer.valueOf(type));
        }
        return propValue;
    }

    public static VehiclePropValue toVehiclePropValue(UserIdentificationSetRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        Preconditions.checkArgument(request.numberAssociations > 0, "invalid number of associations  mismatch on %s", new Object[]{request});
        Preconditions.checkArgument(request.numberAssociations == request.associations.size(), "number of associations mismatch on %s", new Object[]{request});
        Preconditions.checkArgument(request.requestId > 0, "invalid requestId on %s", new Object[]{request});
        VehiclePropValue propValue = createPropRequest(USER_IDENTIFICATION_ASSOCIATION_PROPERTY, request.requestId);
        addUserInfo(propValue, request.userInfo);
        propValue.value.int32Values.add(Integer.valueOf(request.numberAssociations));
        for (int i = 0; i < request.numberAssociations; i++) {
            UserIdentificationSetAssociation association = (UserIdentificationSetAssociation) request.associations.get(i);
            Preconditions.checkArgument(isValidUserIdentificationAssociationType(association.type), "invalid type at index %d on %s", new Object[]{Integer.valueOf(i), request});
            propValue.value.int32Values.add(Integer.valueOf(association.type));
            Preconditions.checkArgument(isValidUserIdentificationAssociationSetValue(association.value), "invalid value at index %d on %s", new Object[]{Integer.valueOf(i), request});
            propValue.value.int32Values.add(Integer.valueOf(association.value));
        }
        return propValue;
    }

    public static VehiclePropValue toVehiclePropValue(CreateUserRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        Preconditions.checkArgument(request.requestId > 0, "invalid requestId on %s", new Object[]{request});
        checkValid(request.usersInfo);
        Preconditions.checkArgument(request.newUserName != null, "newUserName cannot be null (should be empty instead) on %s", new Object[]{request});
        boolean hasNewUser = false;
        int newUserFlags = 0;
        int i = 0;
        while (true) {
            if (i >= request.usersInfo.existingUsers.size()) {
                break;
            }
            android.hardware.automotive.vehicle.V2_0.UserInfo user = (android.hardware.automotive.vehicle.V2_0.UserInfo) request.usersInfo.existingUsers.get(i);
            if (user.userId != request.newUserInfo.userId) {
                i++;
            } else {
                hasNewUser = true;
                newUserFlags = user.flags;
                break;
            }
        }
        Preconditions.checkArgument(hasNewUser, "new user's id not present on existing users on request %s", new Object[]{request});
        Preconditions.checkArgument(request.newUserInfo.flags == newUserFlags, "new user flags mismatch on existing users on %s", new Object[]{request});
        VehiclePropValue propValue = createPropRequest(CREATE_USER_PROPERTY, request.requestId);
        propValue.value.stringValue = request.newUserName;
        addUserInfo(propValue, request.newUserInfo);
        addUsersInfo(propValue, request.usersInfo);
        return propValue;
    }

    public static VehiclePropValue toVehiclePropValue(SwitchUserRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        Preconditions.checkArgument(request.messageType > 0, "invalid messageType on %s", new Object[]{request});
        android.hardware.automotive.vehicle.V2_0.UserInfo targetInfo = request.targetUser;
        UsersInfo usersInfo = request.usersInfo;
        Objects.requireNonNull(targetInfo);
        checkValid(usersInfo);
        VehiclePropValue propValue = createPropRequest(SWITCH_USER_PROPERTY, request.requestId, request.messageType);
        addUserInfo(propValue, targetInfo);
        addUsersInfo(propValue, usersInfo);
        return propValue;
    }

    public static VehiclePropValue toVehiclePropValue(RemoveUserRequest request) {
        Preconditions.checkArgument(request.requestId > 0, "invalid requestId on %s", new Object[]{request});
        android.hardware.automotive.vehicle.V2_0.UserInfo removedUserInfo = request.removedUserInfo;
        Objects.requireNonNull(removedUserInfo);
        UsersInfo usersInfo = request.usersInfo;
        checkValid(usersInfo);
        VehiclePropValue propValue = createPropRequest(REMOVE_USER_PROPERTY, request.requestId);
        addUserInfo(propValue, removedUserInfo);
        addUsersInfo(propValue, usersInfo);
        return propValue;
    }

    public static UsersInfo newUsersInfo(UserManager um) {
        return newUsersInfo(um, ActivityManager.getCurrentUser());
    }

    public static UsersInfo newUsersInfo(UserManager um, int userId) {
        Preconditions.checkArgument(um != null, "UserManager cannot be null");
        List<UserInfo> users = um.getUsers(false, false, true);
        if (users == null || users.isEmpty()) {
            Log.w(TAG, "newUsersInfo(): no users");
            return emptyUsersInfo();
        }
        UsersInfo usersInfo = new UsersInfo();
        usersInfo.currentUser.userId = userId;
        UserInfo currentUser = null;
        usersInfo.numberUsers = users.size();
        for (int i = 0; i < usersInfo.numberUsers; i++) {
            UserInfo user = users.get(i);
            if (user.id == usersInfo.currentUser.userId) {
                currentUser = user;
            }
            android.hardware.automotive.vehicle.V2_0.UserInfo halUser = new android.hardware.automotive.vehicle.V2_0.UserInfo();
            halUser.userId = user.id;
            halUser.flags = convertFlags(user);
            usersInfo.existingUsers.add(halUser);
        }
        if (currentUser != null) {
            usersInfo.currentUser.flags = convertFlags(currentUser);
        } else {
            Log.wtf(TAG, "Current user is not part of existing users. usersInfo: " + usersInfo);
        }
        return usersInfo;
    }

    public static void checkValid(UsersInfo usersInfo) {
        Preconditions.checkArgument(usersInfo != null);
        Preconditions.checkArgument(usersInfo.numberUsers == usersInfo.existingUsers.size(), "sizes mismatch: numberUsers=%d, existingUsers.size=%d", new Object[]{Integer.valueOf(usersInfo.numberUsers), Integer.valueOf(usersInfo.existingUsers.size())});
        boolean hasCurrentUser = false;
        int currentUserFlags = 0;
        int i = 0;
        while (true) {
            if (i >= usersInfo.numberUsers) {
                break;
            }
            android.hardware.automotive.vehicle.V2_0.UserInfo user = (android.hardware.automotive.vehicle.V2_0.UserInfo) usersInfo.existingUsers.get(i);
            if (user.userId != usersInfo.currentUser.userId) {
                i++;
            } else {
                hasCurrentUser = true;
                currentUserFlags = user.flags;
                break;
            }
        }
        Preconditions.checkArgument(hasCurrentUser, "current user not found on existing users on %s", new Object[]{usersInfo});
        Preconditions.checkArgument(usersInfo.currentUser.flags == currentUserFlags, "current user flags mismatch on existing users on %s", new Object[]{usersInfo});
    }

    private static UsersInfo emptyUsersInfo() {
        UsersInfo usersInfo = new UsersInfo();
        usersInfo.currentUser.userId = -10000;
        usersInfo.currentUser.flags = 0;
        return usersInfo;
    }

    private static void assertMinimumSize(VehiclePropValue prop, int minSize) {
        Preconditions.checkArgument(prop.value.int32Values.size() >= minSize, "not enough int32Values (minimum is %d) on %s", new Object[]{Integer.valueOf(minSize), prop});
    }

    private UserHalHelper() {
        throw new UnsupportedOperationException("contains only static methods");
    }
}
