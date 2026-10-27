package com.android.permissioncontroller.permission.utils;

import android.app.Application;
import android.app.role.RoleManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.text.BidiFormatter;
import androidx.core.util.Preconditions;
import com.android.launcher3.icons.IconFactory;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/* JADX INFO: loaded from: classes8.dex */
public final class Utils {
    public static final float DEFAULT_MAX_LABEL_SIZE_PX = 500.0f;
    public static final int FLAGS_ALWAYS_USER_SENSITIVE = 768;
    public static final int FLAGS_PERMISSION_RESTRICTION_ANY_EXEMPT = 14336;
    public static final int FLAGS_PERMISSION_WHITELIST_ALL = 7;
    private static final String LOG_TAG = "Utils";
    public static final long ONE_TIME_PERMISSIONS_TIMEOUT_MILLIS = 60000;
    private static final ArraySet<String> ONE_TIME_PERMISSION_GROUPS;
    public static final String OS_PKG = "android";
    private static final ArrayMap<String, Integer> PERM_GROUP_BACKGROUND_REQUEST_DETAIL_RES;
    private static final ArrayMap<String, Integer> PERM_GROUP_BACKGROUND_REQUEST_RES;
    private static final ArrayMap<String, Integer> PERM_GROUP_REQUEST_DETAIL_RES;
    private static final ArrayMap<String, Integer> PERM_GROUP_REQUEST_RES;
    private static final ArrayMap<String, Integer> PERM_GROUP_UPGRADE_REQUEST_DETAIL_RES;
    private static final ArrayMap<String, Integer> PERM_GROUP_UPGRADE_REQUEST_RES;
    private static final ArrayMap<String, String> PLATFORM_PERMISSIONS;
    private static final ArrayMap<String, ArrayList<String>> PLATFORM_PERMISSION_GROUPS;
    public static final String PROPERTY_AUTO_REVOKE_CHECK_FREQUENCY_MILLIS = "auto_revoke_check_frequency_millis";
    public static final String PROPERTY_AUTO_REVOKE_UNUSED_THRESHOLD_MILLIS = "auto_revoke_unused_threshold_millis2";
    private static final String PROPERTY_LOCATION_ACCESS_CHECK_ENABLED = "location_access_check_enabled";
    private static final String PROPERTY_ONE_TIME_PERMISSIONS_TIMEOUT_MILLIS = "one_time_permissions_timeout_millis";
    private static final String PROPERTY_PERMISSIONS_HUB_ENABLED = "permissions_hub_enabled";
    private static ArrayMap<UserHandle, Context> sUserContexts;

    public enum ForegroundCapableType {
        SOUND_TRIGGER,
        ASSISTANT,
        VOICE_INTERACTION,
        CARRIER_SERVICE,
        NONE
    }

    static {
        int numPlatformPermissions;
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        PLATFORM_PERMISSIONS = arrayMap;
        arrayMap.put("android.permission.READ_CONTACTS", "android.permission-group.CONTACTS");
        PLATFORM_PERMISSIONS.put("android.permission.WRITE_CONTACTS", "android.permission-group.CONTACTS");
        PLATFORM_PERMISSIONS.put("android.permission.GET_ACCOUNTS", "android.permission-group.CONTACTS");
        PLATFORM_PERMISSIONS.put("android.permission.READ_CALENDAR", "android.permission-group.CALENDAR");
        PLATFORM_PERMISSIONS.put("android.permission.WRITE_CALENDAR", "android.permission-group.CALENDAR");
        PLATFORM_PERMISSIONS.put("android.permission.SEND_SMS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.RECEIVE_SMS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.READ_SMS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.RECEIVE_MMS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.RECEIVE_WAP_PUSH", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.READ_CELL_BROADCASTS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.READ_EXTERNAL_STORAGE", "android.permission-group.STORAGE");
        PLATFORM_PERMISSIONS.put("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission-group.STORAGE");
        PLATFORM_PERMISSIONS.put("android.permission.ACCESS_MEDIA_LOCATION", "android.permission-group.STORAGE");
        PLATFORM_PERMISSIONS.put("android.permission.ACCESS_FINE_LOCATION", LocationUtils.LOCATION_PERMISSION);
        PLATFORM_PERMISSIONS.put("android.permission.ACCESS_COARSE_LOCATION", LocationUtils.LOCATION_PERMISSION);
        PLATFORM_PERMISSIONS.put("android.permission.ACCESS_BACKGROUND_LOCATION", LocationUtils.LOCATION_PERMISSION);
        PLATFORM_PERMISSIONS.put("android.permission.READ_CALL_LOG", "android.permission-group.CALL_LOG");
        PLATFORM_PERMISSIONS.put("android.permission.WRITE_CALL_LOG", "android.permission-group.CALL_LOG");
        PLATFORM_PERMISSIONS.put("android.permission.PROCESS_OUTGOING_CALLS", "android.permission-group.CALL_LOG");
        PLATFORM_PERMISSIONS.put("android.permission.READ_PHONE_STATE", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.READ_PHONE_NUMBERS", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.CALL_PHONE", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.USE_SIP", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.ANSWER_PHONE_CALLS", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.ACCEPT_HANDOVER", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.RECORD_AUDIO", "android.permission-group.MICROPHONE");
        PLATFORM_PERMISSIONS.put("android.permission.ACTIVITY_RECOGNITION", "android.permission-group.ACTIVITY_RECOGNITION");
        PLATFORM_PERMISSIONS.put("android.permission.CAMERA", "android.permission-group.CAMERA");
        PLATFORM_PERMISSIONS.put("android.permission.BODY_SENSORS", "android.permission-group.SENSORS");
        PLATFORM_PERMISSION_GROUPS = new ArrayMap<>();
        int numPlatformPermissions2 = PLATFORM_PERMISSIONS.size();
        int i = 0;
        while (i < numPlatformPermissions2) {
            String permission = PLATFORM_PERMISSIONS.keyAt(i);
            String permissionGroup = PLATFORM_PERMISSIONS.valueAt(i);
            ArrayList<String> permissionsOfThisGroup = PLATFORM_PERMISSION_GROUPS.get(permissionGroup);
            if (permissionsOfThisGroup != null) {
                numPlatformPermissions = numPlatformPermissions2;
            } else {
                permissionsOfThisGroup = new ArrayList<>();
                numPlatformPermissions = numPlatformPermissions2;
                PLATFORM_PERMISSION_GROUPS.put(permissionGroup, permissionsOfThisGroup);
            }
            permissionsOfThisGroup.add(permission);
            i++;
            numPlatformPermissions2 = numPlatformPermissions;
        }
        ArraySet<String> arraySet = new ArraySet<>();
        ONE_TIME_PERMISSION_GROUPS = arraySet;
        arraySet.add(LocationUtils.LOCATION_PERMISSION);
        ONE_TIME_PERMISSION_GROUPS.add("android.permission-group.CAMERA");
        ONE_TIME_PERMISSION_GROUPS.add("android.permission-group.MICROPHONE");
        ArrayMap<String, Integer> arrayMap2 = new ArrayMap<>();
        PERM_GROUP_REQUEST_RES = arrayMap2;
        arrayMap2.put("android.permission-group.CONTACTS", Integer.valueOf(R.string.permgrouprequest_contacts));
        PERM_GROUP_REQUEST_RES.put(LocationUtils.LOCATION_PERMISSION, Integer.valueOf(R.string.permgrouprequest_location));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.CALENDAR", Integer.valueOf(R.string.permgrouprequest_calendar));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.SMS", Integer.valueOf(R.string.permgrouprequest_sms));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.STORAGE", Integer.valueOf(R.string.permgrouprequest_storage));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.MICROPHONE", Integer.valueOf(R.string.permgrouprequest_microphone));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.ACTIVITY_RECOGNITION", Integer.valueOf(R.string.permgrouprequest_activityRecognition));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.CAMERA", Integer.valueOf(R.string.permgrouprequest_camera));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.CALL_LOG", Integer.valueOf(R.string.permgrouprequest_calllog));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.PHONE", Integer.valueOf(R.string.permgrouprequest_phone));
        PERM_GROUP_REQUEST_RES.put("android.permission-group.SENSORS", Integer.valueOf(R.string.permgrouprequest_sensors));
        ArrayMap<String, Integer> arrayMap3 = new ArrayMap<>();
        PERM_GROUP_REQUEST_DETAIL_RES = arrayMap3;
        arrayMap3.put(LocationUtils.LOCATION_PERMISSION, Integer.valueOf(R.string.permgrouprequestdetail_location));
        ArrayMap<String, Integer> arrayMap4 = new ArrayMap<>();
        PERM_GROUP_BACKGROUND_REQUEST_RES = arrayMap4;
        arrayMap4.put(LocationUtils.LOCATION_PERMISSION, Integer.valueOf(R.string.permgroupbackgroundrequest_location));
        ArrayMap<String, Integer> arrayMap5 = new ArrayMap<>();
        PERM_GROUP_BACKGROUND_REQUEST_DETAIL_RES = arrayMap5;
        arrayMap5.put(LocationUtils.LOCATION_PERMISSION, Integer.valueOf(R.string.permgroupbackgroundrequestdetail_location));
        ArrayMap<String, Integer> arrayMap6 = new ArrayMap<>();
        PERM_GROUP_UPGRADE_REQUEST_RES = arrayMap6;
        arrayMap6.put(LocationUtils.LOCATION_PERMISSION, Integer.valueOf(R.string.permgroupupgraderequest_location));
        ArrayMap<String, Integer> arrayMap7 = new ArrayMap<>();
        PERM_GROUP_UPGRADE_REQUEST_DETAIL_RES = arrayMap7;
        arrayMap7.put(LocationUtils.LOCATION_PERMISSION, Integer.valueOf(R.string.permgroupupgraderequestdetail_location));
        sUserContexts = new ArrayMap<>();
    }

    private Utils() {
    }

    public static Context getUserContext(Application app, UserHandle user) throws PackageManager.NameNotFoundException {
        if (!sUserContexts.containsKey(user)) {
            sUserContexts.put(user, app.getApplicationContext().createPackageContextAsUser(app.getPackageName(), 0, user));
        }
        return sUserContexts.get(user);
    }

    public static <M> M getSystemServiceSafe(Context context, Class<M> cls) {
        return (M) Preconditions.checkNotNull(context.getSystemService(cls), "Could not resolve " + cls.getSimpleName());
    }

    public static <M> M getSystemServiceSafe(Context context, Class<M> cls, UserHandle userHandle) {
        try {
            return (M) Preconditions.checkNotNull(context.createPackageContextAsUser(context.getPackageName(), 0, userHandle).getSystemService(cls), "Could not resolve " + cls.getSimpleName());
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException();
        }
    }

    public static <T extends Parcelable> T getParcelableExtraSafe(Intent intent, String name) {
        return (T) Preconditions.checkNotNull(intent.getParcelableExtra(name), "Could not get parcelable extra for " + name);
    }

    public static String getStringExtraSafe(Intent intent, String name) {
        return (String) Preconditions.checkNotNull(intent.getStringExtra(name), "Could not get string extra for " + name);
    }

    public static boolean isPermissionDangerousInstalledNotRemoved(PermissionInfo permissionInfo) {
        return permissionInfo != null && permissionInfo.getProtection() == 1 && (permissionInfo.flags & BasicMeasure.EXACTLY) != 0 && (permissionInfo.flags & 2) == 0;
    }

    public static String getGroupOfPlatformPermission(String permission) {
        return PLATFORM_PERMISSIONS.get(permission);
    }

    public static String getGroupOfPermission(PermissionInfo permission) {
        String groupName = getGroupOfPlatformPermission(permission.name);
        if (groupName == null) {
            return permission.group;
        }
        return groupName;
    }

    public static List<String> getPlatformPermissionNamesOfGroup(String group) {
        ArrayList<String> permissions = PLATFORM_PERMISSION_GROUPS.get(group);
        return permissions != null ? permissions : Collections.emptyList();
    }

    public static List<PermissionInfo> getPlatformPermissionsOfGroup(PackageManager pm, String group) {
        ArrayList<PermissionInfo> permInfos = new ArrayList<>();
        ArrayList<String> permissions = PLATFORM_PERMISSION_GROUPS.get(group);
        if (permissions == null) {
            return Collections.emptyList();
        }
        int numPermissions = permissions.size();
        for (int i = 0; i < numPermissions; i++) {
            String permName = permissions.get(i);
            try {
                PermissionInfo permInfo = pm.getPermissionInfo(permName, 0);
                permInfos.add(permInfo);
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalStateException(permName + " not defined by platform", e);
            }
        }
        return permInfos;
    }

    public static List<PermissionInfo> getPermissionInfosForGroup(PackageManager pm, String group) throws PackageManager.NameNotFoundException {
        List<PermissionInfo> permissions = pm.queryPermissionsByGroup(group, 0);
        permissions.addAll(getPlatformPermissionsOfGroup(pm, group));
        if (group.equals("android.permission-group.UNDEFINED")) {
            List<PermissionInfo> undefinedPerms = new ArrayList<>();
            for (PermissionInfo permissionInfo : permissions) {
                String permGroup = getGroupOfPlatformPermission(permissionInfo.name);
                if (permGroup == null || permGroup.equals("android.permission-group.UNDEFINED")) {
                    undefinedPerms.add(permissionInfo);
                }
            }
            return undefinedPerms;
        }
        return permissions;
    }

    public static List<PermissionInfo> getInstalledRuntimePermissionInfosForGroup(PackageManager pm, String group) throws PackageManager.NameNotFoundException {
        List<PermissionInfo> permissions = pm.queryPermissionsByGroup(group, 0);
        permissions.addAll(getPlatformPermissionsOfGroup(pm, group));
        List<PermissionInfo> installedRuntime = new ArrayList<>();
        for (PermissionInfo permissionInfo : permissions) {
            if (permissionInfo.getProtection() == 1 && (permissionInfo.flags & BasicMeasure.EXACTLY) != 0 && (permissionInfo.flags & 2) == 0) {
                installedRuntime.add(permissionInfo);
            }
        }
        if (group.equals("android.permission-group.UNDEFINED")) {
            List<PermissionInfo> undefinedPerms = new ArrayList<>();
            for (PermissionInfo permissionInfo2 : installedRuntime) {
                String permGroup = getGroupOfPlatformPermission(permissionInfo2.name);
                if (permGroup == null || permGroup.equals("android.permission-group.UNDEFINED")) {
                    undefinedPerms.add(permissionInfo2);
                }
            }
            return undefinedPerms;
        }
        return installedRuntime;
    }

    public static PackageItemInfo getGroupInfo(String groupName, Context context) {
        try {
            return context.getPackageManager().getPermissionGroupInfo(groupName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            try {
                return context.getPackageManager().getPermissionInfo(groupName, 0);
            } catch (PackageManager.NameNotFoundException e2) {
                return null;
            }
        }
    }

    public static List<PermissionInfo> getGroupPermissionInfos(String groupName, Context context) {
        try {
            return getPermissionInfosForGroup(context.getPackageManager(), groupName);
        } catch (PackageManager.NameNotFoundException e) {
            try {
                PermissionInfo permissionInfo = context.getPackageManager().getPermissionInfo(groupName, 0);
                List<PermissionInfo> permissions = new ArrayList<>();
                permissions.add(permissionInfo);
                return permissions;
            } catch (PackageManager.NameNotFoundException e2) {
                return null;
            }
        }
    }

    public static String getAppLabel(ApplicationInfo applicationInfo, Context context) {
        return getAppLabel(applicationInfo, 500.0f, context);
    }

    public static String getFullAppLabel(ApplicationInfo applicationInfo, Context context) {
        return getAppLabel(applicationInfo, 0.0f, context);
    }

    private static String getAppLabel(ApplicationInfo applicationInfo, float ellipsizeDip, Context context) {
        return BidiFormatter.getInstance().unicodeWrap(applicationInfo.loadSafeLabel(context.getPackageManager(), ellipsizeDip, 5).toString());
    }

    public static Drawable loadDrawable(PackageManager pm, String pkg, int resId) {
        try {
            return pm.getResourcesForApplication(pkg).getDrawable(resId, null);
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
            Log.d(LOG_TAG, "Couldn't get resource", e);
            return null;
        }
    }

    public static boolean isModernPermissionGroup(String name) {
        return PLATFORM_PERMISSION_GROUPS.containsKey(name);
    }

    public static List<String> getPlatformPermissionGroups() {
        return new ArrayList(PLATFORM_PERMISSION_GROUPS.keySet());
    }

    public static List<String> getRuntimePlatformPermissionNames() {
        return new ArrayList(PLATFORM_PERMISSIONS.keySet());
    }

    public static boolean isRuntimePlatformPermission(String permission) {
        return PLATFORM_PERMISSIONS.containsKey(permission);
    }

    public static boolean shouldShowPermission(Context context, AppPermissionGroup group) {
        if (!group.isGrantingAllowed()) {
            return false;
        }
        boolean isPlatformPermission = group.getDeclaringPackage().equals(OS_PKG);
        return !isPlatformPermission || isModernPermissionGroup(group.getName());
    }

    public static Drawable applyTint(Context context, Drawable icon, int attr) {
        Resources.Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attr, typedValue, true);
        Drawable icon2 = icon.mutate();
        icon2.setTint(context.getColor(typedValue.resourceId));
        return icon2;
    }

    public static Drawable applyTint(Context context, int iconResId, int attr) {
        return applyTint(context, context.getDrawable(iconResId), attr);
    }

    public static List<ApplicationInfo> getAllInstalledApplications(Context context) {
        return context.getPackageManager().getInstalledApplications(0);
    }

    public static boolean isGroupOrBgGroupUserSensitive(AppPermissionGroup group) {
        return group.isUserSensitive() || (group.getBackgroundPermissions() != null && group.getBackgroundPermissions().isUserSensitive());
    }

    public static boolean areGroupPermissionsIndividuallyControlled(Context context, String group) {
        if (context.getPackageManager().arePermissionsIndividuallyControlled()) {
            return "android.permission-group.SMS".equals(group) || "android.permission-group.PHONE".equals(group) || "android.permission-group.CONTACTS".equals(group);
        }
        return false;
    }

    public static boolean isPermissionIndividuallyControlled(Context context, String permission) {
        if (context.getPackageManager().arePermissionsIndividuallyControlled()) {
            return "android.permission.READ_CONTACTS".equals(permission) || "android.permission.WRITE_CONTACTS".equals(permission) || "android.permission.SEND_SMS".equals(permission) || "android.permission.RECEIVE_SMS".equals(permission) || "android.permission.READ_SMS".equals(permission) || "android.permission.RECEIVE_MMS".equals(permission) || "android.permission.CALL_PHONE".equals(permission) || "android.permission.READ_CALL_LOG".equals(permission) || "android.permission.WRITE_CALL_LOG".equals(permission);
        }
        return false;
    }

    public static CharSequence getRequestMessage(CharSequence appLabel, AppPermissionGroup group, Context context, int requestRes) {
        if (!group.getName().equals("android.permission-group.STORAGE") || group.isNonIsolatedStorage()) {
            return requestRes != 0 ? Html.fromHtml(context.getResources().getString(requestRes, appLabel), 0) : Html.fromHtml(context.getString(R.string.permission_warning_template, appLabel, group.getDescription()), 0);
        }
        return Html.fromHtml(String.format(context.getResources().getConfiguration().getLocales().get(0), context.getString(R.string.permgrouprequest_storage_isolated), appLabel), 0);
    }

    public static String getAbsoluteTimeString(Context context, long lastAccessTime) {
        if (lastAccessTime == 0) {
            return null;
        }
        if (isToday(lastAccessTime)) {
            return DateFormat.getTimeFormat(context).format(Long.valueOf(lastAccessTime));
        }
        return DateFormat.getMediumDateFormat(context).format(Long.valueOf(lastAccessTime));
    }

    private static boolean isToday(long time) {
        Calendar today = Calendar.getInstance(Locale.getDefault());
        today.setTimeInMillis(System.currentTimeMillis());
        today.set(11, 0);
        today.set(12, 0);
        today.set(13, 0);
        today.set(14, 0);
        Calendar date = Calendar.getInstance(Locale.getDefault());
        date.setTimeInMillis(time);
        return !date.before(today);
    }

    public static void prepareSearchMenuItem(Menu menu, final Context context) {
        final Intent intent = new Intent("android.settings.APP_SEARCH_SETTINGS");
        if (context.getPackageManager().resolveActivity(intent, 0) == null) {
            return;
        }
        MenuItem searchItem = menu.add(0, 0, 0, R.string.search_menu);
        searchItem.setIcon(R.drawable.ic_search_24dp);
        searchItem.setShowAsAction(2);
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.android.permissioncontroller.permission.utils.-$$Lambda$Utils$nMBAn_C7PS_eSerXX52mUdM1Grk
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return Utils.lambda$prepareSearchMenuItem$0(context, intent, menuItem);
            }
        });
    }

    static /* synthetic */ boolean lambda$prepareSearchMenuItem$0(Context context, Intent intent, MenuItem item) {
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e(LOG_TAG, "Cannot start activity to search settings", e);
            return true;
        }
    }

    public static Drawable getBadgedIcon(Context context, ApplicationInfo appInfo) {
        UserHandle user = UserHandle.getUserHandleForUid(appInfo.uid);
        IconFactory iconFactory = IconFactory.obtain(context);
        try {
            Bitmap iconBmp = iconFactory.createBadgedIconBitmap(appInfo.loadUnbadgedIcon(context.getPackageManager()), user, false).icon;
            BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), iconBmp);
            if (iconFactory != null) {
                iconFactory.close();
            }
            return bitmapDrawable;
        } catch (Throwable th) {
            if (iconFactory != null) {
                try {
                    iconFactory.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getPermissionGroupDescriptionString(android.content.Context r3, java.lang.String r4, java.lang.CharSequence r5) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.utils.Utils.getPermissionGroupDescriptionString(android.content.Context, java.lang.String, java.lang.CharSequence):java.lang.String");
    }

    public static boolean isLocationAccessCheckEnabled() {
        return DeviceConfig.getBoolean("privacy", PROPERTY_LOCATION_ACCESS_CHECK_ENABLED, true);
    }

    public static SharedPreferences getDeviceProtectedSharedPreferences(Context context) {
        if (!context.isDeviceProtectedStorage()) {
            context = context.createDeviceProtectedStorageContext();
        }
        return context.getSharedPreferences(Constants.PREFERENCES_FILE, 0);
    }

    public static long getOneTimePermissionsTimeout() {
        return DeviceConfig.getLong("permissions", PROPERTY_ONE_TIME_PERMISSIONS_TIMEOUT_MILLIS, ONE_TIME_PERMISSIONS_TIMEOUT_MILLIS);
    }

    public static Context getParentUserContext(Context context) {
        UserHandle parentUser = ((UserManager) getSystemServiceSafe(context, UserManager.class)).getProfileParent(UserHandle.of(UserHandle.myUserId()));
        if (parentUser == null) {
            return context;
        }
        try {
            return context.createPackageContextAsUser(context.getPackageName(), 0, parentUser);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Could not switch to parent user " + parentUser, e);
        }
    }

    public static boolean supportsOneTimeGrant(String permissionGroup) {
        return ONE_TIME_PERMISSION_GROUPS.contains(permissionGroup);
    }

    public static int getRequest(String groupName) {
        return PERM_GROUP_REQUEST_RES.getOrDefault(groupName, 0).intValue();
    }

    public static int getRequestDetail(String groupName) {
        return PERM_GROUP_REQUEST_DETAIL_RES.getOrDefault(groupName, 0).intValue();
    }

    public static int getBackgroundRequest(String groupName) {
        return PERM_GROUP_BACKGROUND_REQUEST_RES.getOrDefault(groupName, 0).intValue();
    }

    public static int getBackgroundRequestDetail(String groupName) {
        return PERM_GROUP_BACKGROUND_REQUEST_DETAIL_RES.getOrDefault(groupName, 0).intValue();
    }

    public static int getUpgradeRequest(String groupName) {
        return PERM_GROUP_UPGRADE_REQUEST_RES.getOrDefault(groupName, 0).intValue();
    }

    public static int getUpgradeRequestDetail(String groupName) {
        return PERM_GROUP_UPGRADE_REQUEST_DETAIL_RES.getOrDefault(groupName, 0).intValue();
    }

    public static boolean hasOneTimePermissions(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            String[] permissions = pm.getPackageInfo(packageName, 4096).requestedPermissions;
            if (permissions == null) {
                return false;
            }
            for (String permissionName : permissions) {
                if ((pm.getPermissionFlags(permissionName, packageName, Process.myUserHandle()) & 65536) != 0 && pm.checkPermission(permissionName, packageName) == 0) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(LOG_TAG, "Checking for one-time permissions in nonexistent package");
            return false;
        }
    }

    public static long getValidSessionId() {
        long sessionId = 0;
        while (sessionId == 0) {
            sessionId = new Random().nextLong();
        }
        return sessionId;
    }

    public static CharSequence getSettingsLabelForNotifications(PackageManager pm) {
        Intent openSettingsIntent = new Intent("android.settings.SETTINGS");
        ResolveInfo resolveInfo = pm.resolveActivity(openSettingsIntent, 1048576);
        if (resolveInfo == null) {
            return null;
        }
        return pm.getApplicationLabel(resolveInfo.activityInfo.applicationInfo);
    }

    public static ForegroundCapableType getForegroundCapableType(Context context, String packageName) throws PackageManager.NameNotFoundException {
        ComponentName component;
        ServiceInfo[] services;
        PackageManager pm = context.getPackageManager();
        if (pm.checkPermission("android.permission.CAPTURE_AUDIO_HOTWORD", packageName) == 0 && (services = pm.getPackageInfo(packageName, 4).services) != null) {
            for (ServiceInfo service : services) {
                if ("android.permission.BIND_SOUND_TRIGGER_DETECTION_SERVICE".equals(service.permission)) {
                    return ForegroundCapableType.SOUND_TRIGGER;
                }
            }
        }
        if (((RoleManager) context.getSystemService(RoleManager.class)).getRoleHolders("android.app.role.ASSISTANT").contains(packageName)) {
            return ForegroundCapableType.ASSISTANT;
        }
        String voiceInteraction = Settings.Secure.getString(context.getContentResolver(), "voice_interaction_service");
        if (!TextUtils.isEmpty(voiceInteraction) && (component = ComponentName.unflattenFromString(voiceInteraction)) != null && TextUtils.equals(packageName, component.getPackageName())) {
            return ForegroundCapableType.VOICE_INTERACTION;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        int numPhones = telephonyManager.getActiveModemCount();
        for (int phoneId = 0; phoneId < numPhones; phoneId++) {
            List<String> packages = telephonyManager.getCarrierPackageNamesForIntentAndPhone(new Intent("android.service.carrier.CarrierService"), phoneId);
            if (packages != null && packages.contains(packageName)) {
                return ForegroundCapableType.CARRIER_SERVICE;
            }
        }
        return ForegroundCapableType.NONE;
    }

    public static boolean couldHaveForegroundCapabilities(Context context, String packageName) throws PackageManager.NameNotFoundException {
        return getForegroundCapableType(context, packageName) != ForegroundCapableType.NONE;
    }

    public static boolean isUserDisabledOrWorkProfile(UserHandle user) {
        Application app = PermissionControllerApplication.get();
        UserManager userManager = (UserManager) app.getSystemService(UserManager.class);
        return !userManager.getEnabledProfiles().contains(user) || (userManager.isManagedProfile(user.getIdentifier()) && !DeviceUtils.isTelevision(app));
    }

    public static boolean isEmergencyApp(Context context, String packageName) {
        try {
            return ((RoleManager) context.getSystemService(RoleManager.class)).getRoleHolders("android.app.role.EMERGENCY").contains(packageName);
        } catch (Throwable t) {
            Log.e(LOG_TAG, "Unable to check if " + packageName + " is an emergency app.", t);
            return false;
        }
    }
}
