package com.android.settingslib;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.UserInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.ServiceState;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.UserIcons;
import com.android.launcher3.icons.IconFactory;
import com.android.settingslib.drawable.UserIconDrawable;
import com.android.settingslib.fuelgauge.BatteryStatus;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes.dex */
public class Utils {

    @VisibleForTesting
    static final String STORAGE_MANAGER_ENABLED_PROPERTY = "ro.storage_manager.enabled";
    private static String sPermissionControllerPackageName;
    private static String sServicesSystemSharedLibPackageName;
    private static String sSharedSystemSharedLibPackageName;
    private static Signature[] sSystemSignature;
    static final int[] WIFI_PIE = {R.drawable.ic_menu_help_holo_light, R.drawable.ic_menu_home, R.drawable.ic_menu_invite, R.drawable.ic_menu_login, R.drawable.ic_menu_mark};
    static final int[] SHOW_X_WIFI_PIE = {R$drawable.ic_show_x_wifi_signal_0, R$drawable.ic_show_x_wifi_signal_1, R$drawable.ic_show_x_wifi_signal_2, R$drawable.ic_show_x_wifi_signal_3, R$drawable.ic_show_x_wifi_signal_4};

    public static void updateLocationEnabled(Context context, boolean z, int i, int i2) {
        Settings.Secure.putIntForUser(context.getContentResolver(), "location_changer", i2, i);
        ((LocationManager) context.getSystemService(LocationManager.class)).setLocationEnabledForUser(z, UserHandle.of(i));
    }

    public static int getTetheringLabel(ConnectivityManager connectivityManager) {
        String[] tetherableUsbRegexs = connectivityManager.getTetherableUsbRegexs();
        String[] tetherableWifiRegexs = connectivityManager.getTetherableWifiRegexs();
        String[] tetherableBluetoothRegexs = connectivityManager.getTetherableBluetoothRegexs();
        boolean z = tetherableUsbRegexs.length != 0;
        boolean z2 = tetherableWifiRegexs.length != 0;
        boolean z3 = tetherableBluetoothRegexs.length != 0;
        if (z2 && z && z3) {
            return R$string.tether_settings_title_all;
        }
        if (z2 && z) {
            return R$string.tether_settings_title_all;
        }
        if (z2 && z3) {
            return R$string.tether_settings_title_all;
        }
        if (z2) {
            return R$string.tether_settings_title_wifi;
        }
        if (z && z3) {
            return R$string.tether_settings_title_usb_bluetooth;
        }
        if (z) {
            return R$string.tether_settings_title_usb;
        }
        return R$string.tether_settings_title_bluetooth;
    }

    public static String getUserLabel(Context context, UserInfo userInfo) {
        String string = userInfo != null ? userInfo.name : null;
        if (userInfo.isManagedProfile()) {
            return context.getString(R$string.managed_user_title);
        }
        if (userInfo.isGuest()) {
            string = context.getString(R$string.user_guest);
        }
        if (string == null && userInfo != null) {
            string = Integer.toString(userInfo.id);
        } else if (userInfo == null) {
            string = context.getString(R$string.unknown);
        }
        return context.getResources().getString(R$string.running_process_item_user_label, string);
    }

    public static Drawable getUserIcon(Context context, UserManager userManager, UserInfo userInfo) {
        Bitmap userIcon;
        int sizeForList = UserIconDrawable.getSizeForList(context);
        if (userInfo.isManagedProfile()) {
            Drawable managedUserDrawable = UserIconDrawable.getManagedUserDrawable(context);
            managedUserDrawable.setBounds(0, 0, sizeForList, sizeForList);
            return managedUserDrawable;
        }
        if (userInfo.iconPath != null && (userIcon = userManager.getUserIcon(userInfo.id)) != null) {
            UserIconDrawable userIconDrawable = new UserIconDrawable(sizeForList);
            userIconDrawable.setIcon(userIcon);
            userIconDrawable.bake();
            return userIconDrawable;
        }
        UserIconDrawable userIconDrawable2 = new UserIconDrawable(sizeForList);
        userIconDrawable2.setIconDrawable(UserIcons.getDefaultUserIcon(context.getResources(), userInfo.id, false));
        userIconDrawable2.bake();
        return userIconDrawable2;
    }

    public static String formatPercentage(double d, boolean z) {
        return formatPercentage(z ? Math.round((float) d) : (int) d);
    }

    public static String formatPercentage(long j, long j2) {
        return formatPercentage(j / j2);
    }

    public static String formatPercentage(int i) {
        return formatPercentage(((double) i) / 100.0d);
    }

    public static String formatPercentage(double d) {
        return NumberFormat.getPercentInstance().format(d);
    }

    public static int getBatteryLevel(Intent intent) {
        return (intent.getIntExtra("level", 0) * 100) / intent.getIntExtra("scale", 100);
    }

    public static String getBatteryStatus(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("status", 1);
        Resources resources = context.getResources();
        String string = resources.getString(R$string.battery_info_status_unknown);
        BatteryStatus batteryStatus = new BatteryStatus(intent);
        if (batteryStatus.isCharged()) {
            return resources.getString(R$string.battery_info_status_full);
        }
        if (intExtra != 2) {
            if (intExtra == 3) {
                return resources.getString(R$string.battery_info_status_discharging);
            }
            return intExtra == 4 ? resources.getString(R$string.battery_info_status_not_charging) : string;
        }
        int chargingSpeed = batteryStatus.getChargingSpeed(context);
        if (chargingSpeed == 0) {
            return resources.getString(R$string.battery_info_status_charging_slow);
        }
        if (chargingSpeed == 2) {
            return resources.getString(R$string.battery_info_status_charging_fast);
        }
        return resources.getString(R$string.battery_info_status_charging);
    }

    public static ColorStateList getColorAccent(Context context) {
        return getColorAttr(context, R.attr.colorAccent);
    }

    public static int getColorAccentDefaultColor(Context context) {
        return getColorAttrDefaultColor(context, R.attr.colorAccent);
    }

    public static int getColorStateListDefaultColor(Context context, int i) {
        return context.getResources().getColorStateList(i, context.getTheme()).getDefaultColor();
    }

    public static int getDisabled(Context context, int i) {
        return applyAlphaAttr(context, R.attr.disabledAlpha, i);
    }

    public static int applyAlphaAttr(Context context, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        float f = typedArrayObtainStyledAttributes.getFloat(0, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        return applyAlpha(f, i2);
    }

    public static int applyAlpha(float f, int i) {
        return Color.argb((int) (f * Color.alpha(i)), Color.red(i), Color.green(i), Color.blue(i));
    }

    public static int getColorAttrDefaultColor(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    public static ColorStateList getColorAttr(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        try {
            return typedArrayObtainStyledAttributes.getColorStateList(0);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static boolean isSystemPackage(Resources resources, PackageManager packageManager, PackageInfo packageInfo) {
        if (sSystemSignature == null) {
            sSystemSignature = new Signature[]{getSystemSignature(packageManager)};
        }
        if (sPermissionControllerPackageName == null) {
            sPermissionControllerPackageName = packageManager.getPermissionControllerPackageName();
        }
        if (sServicesSystemSharedLibPackageName == null) {
            sServicesSystemSharedLibPackageName = packageManager.getServicesSystemSharedLibraryPackageName();
        }
        if (sSharedSystemSharedLibPackageName == null) {
            sSharedSystemSharedLibPackageName = packageManager.getSharedSystemSharedLibraryPackageName();
        }
        Signature[] signatureArr = sSystemSignature;
        return (signatureArr[0] != null && signatureArr[0].equals(getFirstSignature(packageInfo))) || packageInfo.packageName.equals(sPermissionControllerPackageName) || packageInfo.packageName.equals(sServicesSystemSharedLibPackageName) || packageInfo.packageName.equals(sSharedSystemSharedLibPackageName) || packageInfo.packageName.equals("com.android.printspooler") || isDeviceProvisioningPackage(resources, packageInfo.packageName);
    }

    private static Signature getFirstSignature(PackageInfo packageInfo) {
        Signature[] signatureArr;
        if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
            return null;
        }
        return signatureArr[0];
    }

    private static Signature getSystemSignature(PackageManager packageManager) {
        try {
            return getFirstSignature(packageManager.getPackageInfo("android", 64));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static boolean isDeviceProvisioningPackage(Resources resources, String str) {
        String string = resources.getString(R.string.config_bandwidthEstimateSource);
        return string != null && string.equals(str);
    }

    public static int getWifiIconResource(int i) {
        return getWifiIconResource(false, i);
    }

    public static int getWifiIconResource(boolean z, int i) {
        if (i >= 0) {
            int[] iArr = WIFI_PIE;
            if (i < iArr.length) {
                return z ? SHOW_X_WIFI_PIE[i] : iArr[i];
            }
        }
        throw new IllegalArgumentException("No Wifi icon found for level: " + i);
    }

    public static int getDefaultStorageManagerDaysToRetain(Resources resources) {
        try {
            return resources.getInteger(R.integer.config_longPressOnBackBehavior);
        } catch (Resources.NotFoundException unused) {
            return 90;
        }
    }

    public static boolean isWifiOnly(Context context) {
        return !((ConnectivityManager) context.getSystemService(ConnectivityManager.class)).isNetworkSupported(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    public static boolean isStorageManagerEnabled(Context context) {
        ?? r1;
        try {
            r1 = SystemProperties.getBoolean(STORAGE_MANAGER_ENABLED_PROPERTY, false);
        } catch (Resources.NotFoundException unused) {
            r1 = 0;
        }
        return Settings.Secure.getInt(context.getContentResolver(), "automatic_storage_manager_enabled", r1) != 0;
    }

    public static boolean isAudioModeOngoingCall(Context context) {
        int mode = ((AudioManager) context.getSystemService(AudioManager.class)).getMode();
        return mode == 1 || mode == 2 || mode == 3;
    }

    public static boolean isInService(ServiceState serviceState) {
        int combinedServiceState;
        return (serviceState == null || (combinedServiceState = getCombinedServiceState(serviceState)) == 3 || combinedServiceState == 1 || combinedServiceState == 2) ? false : true;
    }

    public static int getCombinedServiceState(ServiceState serviceState) {
        if (serviceState == null) {
            return 1;
        }
        int state = serviceState.getState();
        int dataRegistrationState = serviceState.getDataRegistrationState();
        if ((state == 1 || state == 2) && dataRegistrationState == 0 && isNotInIwlan(serviceState)) {
            return 0;
        }
        return state;
    }

    public static Drawable getBadgedIcon(Context context, Drawable drawable, UserHandle userHandle) {
        IconFactory iconFactoryObtain = IconFactory.obtain(context);
        try {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), iconFactoryObtain.createBadgedIconBitmap(drawable, userHandle, true).icon);
            if (iconFactoryObtain != null) {
                iconFactoryObtain.close();
            }
            return bitmapDrawable;
        } catch (Throwable th) {
            if (iconFactoryObtain != null) {
                try {
                    iconFactoryObtain.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static Drawable getBadgedIcon(Context context, ApplicationInfo applicationInfo) {
        return getBadgedIcon(context, applicationInfo.loadUnbadgedIcon(context.getPackageManager()), UserHandle.getUserHandleForUid(applicationInfo.uid));
    }

    private static boolean isNotInIwlan(ServiceState serviceState) {
        NetworkRegistrationInfo networkRegistrationInfo = serviceState.getNetworkRegistrationInfo(2, 2);
        if (networkRegistrationInfo == null) {
            return true;
        }
        return !(networkRegistrationInfo.getRegistrationState() == 1 || networkRegistrationInfo.getRegistrationState() == 5);
    }
}
