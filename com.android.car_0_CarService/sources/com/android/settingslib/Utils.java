package com.android.settingslib;

import android.content.ContentResolver;
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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
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
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.android.internal.util.UserIcons;
import com.android.launcher3.icons.IconFactory;
import com.android.settingslib.drawable.UserIconDrawable;
import com.android.settingslib.fuelgauge.BatteryStatus;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes3.dex */
public class Utils {
    static final String STORAGE_MANAGER_ENABLED_PROPERTY = "ro.storage_manager.enabled";
    private static String sPermissionControllerPackageName;
    private static String sServicesSystemSharedLibPackageName;
    private static String sSharedSystemSharedLibPackageName;
    private static Signature[] sSystemSignature;
    static final int[] WIFI_PIE = {android.R.drawable.ic_menu_help_holo_light, android.R.drawable.ic_menu_home, android.R.drawable.ic_menu_invite, android.R.drawable.ic_menu_login, android.R.drawable.ic_menu_mark};
    static final int[] SHOW_X_WIFI_PIE = {R.drawable.ic_show_x_wifi_signal_0, R.drawable.ic_show_x_wifi_signal_1, R.drawable.ic_show_x_wifi_signal_2, R.drawable.ic_show_x_wifi_signal_3, R.drawable.ic_show_x_wifi_signal_4};

    public static void updateLocationEnabled(Context context, boolean enabled, int userId, int source) {
        Settings.Secure.putIntForUser(context.getContentResolver(), "location_changer", source, userId);
        LocationManager locationManager = (LocationManager) context.getSystemService(LocationManager.class);
        locationManager.setLocationEnabledForUser(enabled, UserHandle.of(userId));
    }

    public static int getTetheringLabel(ConnectivityManager cm) {
        String[] usbRegexs = cm.getTetherableUsbRegexs();
        String[] wifiRegexs = cm.getTetherableWifiRegexs();
        String[] bluetoothRegexs = cm.getTetherableBluetoothRegexs();
        boolean usbAvailable = usbRegexs.length != 0;
        boolean wifiAvailable = wifiRegexs.length != 0;
        boolean bluetoothAvailable = bluetoothRegexs.length != 0;
        if (wifiAvailable && usbAvailable && bluetoothAvailable) {
            return R.string.tether_settings_title_all;
        }
        if (wifiAvailable && usbAvailable) {
            return R.string.tether_settings_title_all;
        }
        if (wifiAvailable && bluetoothAvailable) {
            return R.string.tether_settings_title_all;
        }
        if (wifiAvailable) {
            return R.string.tether_settings_title_wifi;
        }
        if (usbAvailable && bluetoothAvailable) {
            return R.string.tether_settings_title_usb_bluetooth;
        }
        if (usbAvailable) {
            return R.string.tether_settings_title_usb;
        }
        return R.string.tether_settings_title_bluetooth;
    }

    public static String getUserLabel(Context context, UserInfo info) {
        String name = info != null ? info.name : null;
        if (info.isManagedProfile()) {
            return context.getString(R.string.managed_user_title);
        }
        if (info.isGuest()) {
            name = context.getString(R.string.user_guest);
        }
        if (name == null && info != null) {
            name = Integer.toString(info.id);
        } else if (info == null) {
            name = context.getString(R.string.unknown);
        }
        return context.getResources().getString(R.string.running_process_item_user_label, name);
    }

    public static Drawable getUserIcon(Context context, UserManager um, UserInfo user) {
        Bitmap icon;
        int iconSize = UserIconDrawable.getSizeForList(context);
        if (user.isManagedProfile()) {
            Drawable drawable = UserIconDrawable.getManagedUserDrawable(context);
            drawable.setBounds(0, 0, iconSize, iconSize);
            return drawable;
        }
        if (user.iconPath != null && (icon = um.getUserIcon(user.id)) != null) {
            return new UserIconDrawable(iconSize).setIcon(icon).bake();
        }
        return new UserIconDrawable(iconSize).setIconDrawable(UserIcons.getDefaultUserIcon(context.getResources(), user.id, false)).bake();
    }

    public static String formatPercentage(double percentage, boolean round) {
        int localPercentage = round ? Math.round((float) percentage) : (int) percentage;
        return formatPercentage(localPercentage);
    }

    public static String formatPercentage(long amount, long total) {
        return formatPercentage(amount / total);
    }

    public static String formatPercentage(int percentage) {
        return formatPercentage(((double) percentage) / 100.0d);
    }

    public static String formatPercentage(double percentage) {
        return NumberFormat.getPercentInstance().format(percentage);
    }

    public static int getBatteryLevel(Intent batteryChangedIntent) {
        int level = batteryChangedIntent.getIntExtra("level", 0);
        int scale = batteryChangedIntent.getIntExtra("scale", 100);
        return (level * 100) / scale;
    }

    public static String getBatteryStatus(Context context, Intent batteryChangedIntent) {
        int status = batteryChangedIntent.getIntExtra(NotificationCompat.CATEGORY_STATUS, 1);
        Resources res = context.getResources();
        String statusString = res.getString(R.string.battery_info_status_unknown);
        BatteryStatus batteryStatus = new BatteryStatus(batteryChangedIntent);
        if (batteryStatus.isCharged()) {
            String statusString2 = res.getString(R.string.battery_info_status_full);
            return statusString2;
        }
        if (status == 2) {
            int chargingSpeed = batteryStatus.getChargingSpeed(context);
            if (chargingSpeed != 0) {
                if (chargingSpeed == 2) {
                    String statusString3 = res.getString(R.string.battery_info_status_charging_fast);
                    return statusString3;
                }
                String statusString4 = res.getString(R.string.battery_info_status_charging);
                return statusString4;
            }
            String statusString5 = res.getString(R.string.battery_info_status_charging_slow);
            return statusString5;
        }
        if (status == 3) {
            String statusString6 = res.getString(R.string.battery_info_status_discharging);
            return statusString6;
        }
        if (status == 4) {
            String statusString7 = res.getString(R.string.battery_info_status_not_charging);
            return statusString7;
        }
        return statusString;
    }

    public static ColorStateList getColorAccent(Context context) {
        return getColorAttr(context, android.R.attr.colorAccent);
    }

    public static ColorStateList getColorError(Context context) {
        return getColorAttr(context, android.R.attr.colorError);
    }

    public static int getColorAccentDefaultColor(Context context) {
        return getColorAttrDefaultColor(context, android.R.attr.colorAccent);
    }

    public static int getColorErrorDefaultColor(Context context) {
        return getColorAttrDefaultColor(context, android.R.attr.colorError);
    }

    public static int getColorStateListDefaultColor(Context context, int resId) {
        ColorStateList list = context.getResources().getColorStateList(resId, context.getTheme());
        return list.getDefaultColor();
    }

    public static int getDisabled(Context context, int inputColor) {
        return applyAlphaAttr(context, android.R.attr.disabledAlpha, inputColor);
    }

    public static int applyAlphaAttr(Context context, int attr, int inputColor) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{attr});
        float alpha = ta.getFloat(0, 0.0f);
        ta.recycle();
        return applyAlpha(alpha, inputColor);
    }

    public static int applyAlpha(float alpha, int inputColor) {
        return Color.argb((int) (alpha * Color.alpha(inputColor)), Color.red(inputColor), Color.green(inputColor), Color.blue(inputColor));
    }

    public static int getColorAttrDefaultColor(Context context, int attr) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{attr});
        int colorAccent = ta.getColor(0, 0);
        ta.recycle();
        return colorAccent;
    }

    public static ColorStateList getColorAttr(Context context, int attr) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{attr});
        try {
            ColorStateList stateList = ta.getColorStateList(0);
            return stateList;
        } finally {
            ta.recycle();
        }
    }

    public static int getThemeAttr(Context context, int attr) {
        return getThemeAttr(context, attr, 0);
    }

    public static int getThemeAttr(Context context, int attr, int defaultValue) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{attr});
        int theme = ta.getResourceId(0, defaultValue);
        ta.recycle();
        return theme;
    }

    public static Drawable getDrawable(Context context, int attr) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{attr});
        Drawable drawable = ta.getDrawable(0);
        ta.recycle();
        return drawable;
    }

    public static ColorMatrix getAlphaInvariantColorMatrixForColor(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        ColorMatrix cm = new ColorMatrix(new float[]{0.0f, 0.0f, 0.0f, 0.0f, r, 0.0f, 0.0f, 0.0f, 0.0f, g, 0.0f, 0.0f, 0.0f, 0.0f, b, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        return cm;
    }

    public static ColorFilter getAlphaInvariantColorFilterForColor(int color) {
        return new ColorMatrixColorFilter(getAlphaInvariantColorMatrixForColor(color));
    }

    public static boolean isSystemPackage(Resources resources, PackageManager pm, PackageInfo pkg) {
        if (sSystemSignature == null) {
            sSystemSignature = new Signature[]{getSystemSignature(pm)};
        }
        if (sPermissionControllerPackageName == null) {
            sPermissionControllerPackageName = pm.getPermissionControllerPackageName();
        }
        if (sServicesSystemSharedLibPackageName == null) {
            sServicesSystemSharedLibPackageName = pm.getServicesSystemSharedLibraryPackageName();
        }
        if (sSharedSystemSharedLibPackageName == null) {
            sSharedSystemSharedLibPackageName = pm.getSharedSystemSharedLibraryPackageName();
        }
        Signature[] signatureArr = sSystemSignature;
        return (signatureArr[0] != null && signatureArr[0].equals(getFirstSignature(pkg))) || pkg.packageName.equals(sPermissionControllerPackageName) || pkg.packageName.equals(sServicesSystemSharedLibPackageName) || pkg.packageName.equals(sSharedSystemSharedLibPackageName) || pkg.packageName.equals("com.android.printspooler") || isDeviceProvisioningPackage(resources, pkg.packageName);
    }

    private static Signature getFirstSignature(PackageInfo pkg) {
        if (pkg != null && pkg.signatures != null && pkg.signatures.length > 0) {
            return pkg.signatures[0];
        }
        return null;
    }

    private static Signature getSystemSignature(PackageManager pm) {
        try {
            PackageInfo sys = pm.getPackageInfo(SystemMediaRouteProvider.PACKAGE_NAME, 64);
            return getFirstSignature(sys);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static boolean isDeviceProvisioningPackage(Resources resources, String packageName) {
        String deviceProvisioningPackage = resources.getString(android.R.string.config_bandwidthEstimateSource);
        return deviceProvisioningPackage != null && deviceProvisioningPackage.equals(packageName);
    }

    public static int getWifiIconResource(int level) {
        return getWifiIconResource(false, level);
    }

    public static int getWifiIconResource(boolean showX, int level) {
        if (level >= 0) {
            int[] iArr = WIFI_PIE;
            if (level < iArr.length) {
                return showX ? SHOW_X_WIFI_PIE[level] : iArr[level];
            }
        }
        throw new IllegalArgumentException("No Wifi icon found for level: " + level);
    }

    public static int getDefaultStorageManagerDaysToRetain(Resources resources) {
        try {
            int defaultDays = resources.getInteger(android.R.integer.config_longPressOnBackBehavior);
            return defaultDays;
        } catch (Resources.NotFoundException e) {
            return 90;
        }
    }

    public static boolean isWifiOnly(Context context) {
        return !((ConnectivityManager) context.getSystemService(ConnectivityManager.class)).isNetworkSupported(0);
    }

    public static boolean isStorageManagerEnabled(Context context) {
        boolean isDefaultOn;
        int i;
        try {
            isDefaultOn = SystemProperties.getBoolean(STORAGE_MANAGER_ENABLED_PROPERTY, false);
        } catch (Resources.NotFoundException e) {
            isDefaultOn = false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (!isDefaultOn) {
            i = 0;
        } else {
            i = 1;
        }
        if (Settings.Secure.getInt(contentResolver, "automatic_storage_manager_enabled", i) == 0) {
            return false;
        }
        return true;
    }

    public static boolean isAudioModeOngoingCall(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(AudioManager.class);
        int audioMode = audioManager.getMode();
        return audioMode == 1 || audioMode == 2 || audioMode == 3;
    }

    public static boolean isInService(ServiceState serviceState) {
        int state;
        return (serviceState == null || (state = getCombinedServiceState(serviceState)) == 3 || state == 1 || state == 2) ? false : true;
    }

    public static int getCombinedServiceState(ServiceState serviceState) {
        if (serviceState == null) {
            return 1;
        }
        int state = serviceState.getState();
        int dataState = serviceState.getDataRegistrationState();
        if ((state == 1 || state == 2) && dataState == 0 && isNotInIwlan(serviceState)) {
            return 0;
        }
        return state;
    }

    public static Drawable getBadgedIcon(Context context, Drawable icon, UserHandle user) {
        IconFactory iconFactory = IconFactory.obtain(context);
        try {
            Bitmap iconBmp = iconFactory.createBadgedIconBitmap(icon, user, true).icon;
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

    public static Drawable getBadgedIcon(Context context, ApplicationInfo appInfo) {
        return getBadgedIcon(context, appInfo.loadUnbadgedIcon(context.getPackageManager()), UserHandle.getUserHandleForUid(appInfo.uid));
    }

    private static boolean isNotInIwlan(ServiceState serviceState) {
        NetworkRegistrationInfo networkRegWlan = serviceState.getNetworkRegistrationInfo(2, 2);
        if (networkRegWlan == null) {
            return true;
        }
        boolean isInIwlan = networkRegWlan.getRegistrationState() == 1 || networkRegWlan.getRegistrationState() == 5;
        return !isInIwlan;
    }

    public static Bitmap convertCornerRadiusBitmap(Context context, Bitmap source, float cornerRadius) {
        Bitmap roundedBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), source);
        drawable.setAntiAlias(true);
        drawable.setCornerRadius(cornerRadius);
        Canvas canvas = new Canvas(roundedBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return roundedBitmap;
    }
}
