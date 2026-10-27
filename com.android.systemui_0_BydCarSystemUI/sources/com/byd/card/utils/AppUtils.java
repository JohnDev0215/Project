package com.byd.card.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import com.android.launcher3.AppTransitionManagerImpl;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SystemUIApplication;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.db.AppInfoDao;
import com.byd.card.db.CardDatabase;
import com.byd.card.db.ShortcutInfoDao;
import com.byd.card.floatcard.Executors;
import com.byd.card.floatcard.FloatCardService;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import com.bydux.api.common.views.window.ToastUtils;
import com.github.promeg.pinyinhelper.Pinyin;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppUtils {
    private static final String AUTO_TYPE = "persist.sys.AutoType";
    private static final String BOSCH_CONFIG_AREA = "persist.vendor.byd.cfg.huc.config.area";
    private static final String COUNTRY_CODE = "persist.sys.byd.countrycode";
    private static final String RUDDER_VALUE = "persist.vendor.byd.cfg.huc.steering.wheel.position";
    private static final String TAG = "AppUtils";
    private static String sEnergyType;
    private static boolean sIsShowingCarSettingsSubActivity;
    private static final String[] NO_MAP_ARRAY = {Constant.HONG_KONG_CODE, "853", "81", "82", "91"};
    private static int sCarAutoType = -1;
    private static final Set<String> mCustomMediaComponents = new HashSet();
    private static final Set<String> mHiddenApps = new HashSet();

    public static void openShortcut(Context context, ShortcutInfo shortcutInfo, Activity activity, ViewGroup viewGroup, View view, Bitmap bitmap, int width, int height, int x, int y, boolean card) {
        if (shortcutInfo.isForbidden()) {
            ToastUtils.show(context, R.string.open_app_on_p_gear);
            return;
        }
        Intent uri = null;
        try {
            uri = Intent.parseUri(shortcutInfo.intent, 1);
        } catch (URISyntaxException e) {
            Log.e(TAG, "openShortcut error! e = " + e);
        }
        Intent intent = uri;
        if (intent == null) {
            Log.w(TAG, "openShortcut error! intent is null! shortcutInfo = " + shortcutInfo);
            return;
        }
        try {
            ActivityOptions activityLaunchOptions = new AppTransitionManagerImpl(activity, viewGroup, view, generateFloatImageView(context, bitmap, width, height, null, x, y), "").getActivityLaunchOptions(width, height, 1);
            intent.addFlags(268435456);
            context.startActivity(intent, activityLaunchOptions.toBundle());
            Log.d(TAG, "openShortcut " + shortcutInfo.getItemIdentify() + " isFromCard = " + card);
            if (card) {
                return;
            }
            insertRecentShortcut(shortcutInfo.getIdentify(), shortcutInfo.getOrigin());
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(TAG, "openShortcut Exception = " + e2.getMessage());
        }
    }

    public static void openApplication(final Context context, final AppInfo appInfo, final Activity activity, final ViewGroup viewGroup, final View view, Bitmap bitmap, final int width, final int height, int x, int y, boolean card) {
        if (appInfo.isForbidden()) {
            ToastUtils.show(context, R.string.open_app_on_p_gear);
            return;
        }
        try {
            final Intent flags = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setComponent(new ComponentName(appInfo.packageName, appInfo.activity)).setFlags(270532608);
            if (flags == null) {
                Log.w(TAG, "openApplication error! intent is null! appinfo = " + appInfo);
                return;
            }
            final ImageView imageViewGenerateFloatImageView = generateFloatImageView(context, bitmap, width, height, appInfo.getIcon(), x, y);
            if (appInfo.packageName.equals("com.byd.avm")) {
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_CARD_NO_ANIMATOR));
                if (viewGroup != null) {
                    Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.utils.AppUtils.1
                        @Override // java.lang.Runnable
                        public void run() {
                            viewGroup.setVisibility(4);
                        }
                    });
                }
                context.startActivity(flags);
                if (activity != null) {
                    activity.finish();
                }
            } else if (activity != null && isShowingCarSettingsSubActivity() && appInfo.packageName.equals("com.byd.carsettings")) {
                activity.finish();
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.byd.card.utils.-$$Lambda$AppUtils$JwEenm0nPsW3fvGgRzuhfIMTNSw
                    @Override // java.lang.Runnable
                    public final void run() {
                        Activity activity2 = activity;
                        ViewGroup viewGroup2 = viewGroup;
                        View view2 = view;
                        ImageView imageView = imageViewGenerateFloatImageView;
                        AppInfo appInfo2 = appInfo;
                        context.startActivity(flags, new AppTransitionManagerImpl(activity2, viewGroup2, view2, imageView, appInfo2.packageName).getActivityLaunchOptions(width, height, 1).toBundle());
                    }
                });
            } else {
                context.startActivity(flags, new AppTransitionManagerImpl(activity, viewGroup, view, imageViewGenerateFloatImageView, appInfo.packageName).getActivityLaunchOptions(width, height, 1).toBundle());
            }
            Log.d(TAG, "openApplication " + appInfo.getPackageName() + " isFromCard = " + card);
            if (card) {
                return;
            }
            insertRecentApp(appInfo.packageName);
        } catch (Exception e) {
            e.printStackTrace();
            Log.w(TAG, "openApplication Exception = " + e.getMessage());
            openApplicationException(context, appInfo.packageName);
        }
    }

    public static ImageView generateFloatImageView(Context context, Bitmap bitmap, int width, int height, Drawable alternativeDrawable, int x, int y) {
        ViewGroup.LayoutParams layoutParams;
        ImageView imageView = new ImageView(context);
        if (bitmap != null) {
            imageView.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            layoutParams = new ViewGroup.LayoutParams(width, height);
        } else {
            imageView.setBackground(alternativeDrawable);
            layoutParams = new ViewGroup.LayoutParams(200, 200);
        }
        imageView.setX(x);
        imageView.setY(y);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    private static void openApplicationException(Context context, String pkg) {
        if (TextUtils.isEmpty(pkg) || pkg == null) {
            Log.w(TAG, "openApplicationException pkg is null");
            return;
        }
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(pkg));
        } catch (Exception e) {
            Log.w(TAG, "openApplicationException : " + e.getMessage());
        }
    }

    public static void insertRecentApp(final String packageName) {
        new Thread(new Runnable() { // from class: com.byd.card.utils.-$$Lambda$AppUtils$F2_rc7cTZsCQdokZxdtpXXUdsY8
            @Override // java.lang.Runnable
            public final void run() {
                AppUtils.lambda$insertRecentApp$1(packageName);
            }
        }).start();
    }

    static /* synthetic */ void lambda$insertRecentApp$1(String str) {
        if (TextUtils.isEmpty(str)) {
            BydLogHelper.logE("insertRecentApp app is null");
            return;
        }
        AppInfoDao appInfoDao = CardDatabase.getInstance().getAppInfoDao();
        AppInfo appInfoByPackage = appInfoDao.getAppInfoByPackage(str);
        if (appInfoByPackage == null) {
            BydLogHelper.logE("insertRecentApp appInfo1 is null");
            return;
        }
        appInfoByPackage.recent = 1;
        appInfoByPackage.recentTime = System.currentTimeMillis();
        appInfoByPackage.count++;
        appInfoDao.update(appInfoByPackage);
    }

    public static void insertRecentShortcut(final String identify, final String origin) {
        new Thread(new Runnable() { // from class: com.byd.card.utils.-$$Lambda$AppUtils$5CcyrRtE2JKIaCzI2jLjeP6QIXg
            @Override // java.lang.Runnable
            public final void run() {
                AppUtils.lambda$insertRecentShortcut$2(identify, origin);
            }
        }).start();
    }

    static /* synthetic */ void lambda$insertRecentShortcut$2(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            BydLogHelper.logE("insertRecentApp app is null");
            return;
        }
        ShortcutInfoDao shortcutInfoDao = CardDatabase.getInstance().getShortcutInfoDao();
        ShortcutInfo shortcutByIdentify = shortcutInfoDao.getShortcutByIdentify(str, str2);
        if (shortcutByIdentify == null) {
            BydLogHelper.logE("insertRecentApp appInfo1 is null");
            return;
        }
        shortcutByIdentify.recent = 1;
        shortcutByIdentify.recentTime = System.currentTimeMillis();
        shortcutByIdentify.count++;
        shortcutInfoDao.update(shortcutByIdentify);
    }

    public static boolean isLandSpace(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static List<AppInfo> getAppList(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(8192);
        ArrayList arrayList = new ArrayList();
        List<ResolveInfo> appInfoList = getAppInfoList(context, null);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            if (!packageInfo.packageName.equals("com.byd.launcherwallpaper") && !packageInfo.packageName.equals("com.android.launcher3") && !packageInfo.packageName.equals("com.byd.card") && !packageInfo.packageName.equals(Constant.PackageName.LATIN) && !packageInfo.packageName.equals(Constant.PackageName.ANDROID_SETTINGS) && !packageInfo.packageName.equals("com.byd.globalnotification")) {
                if (Constant.HONG_KONG_CODE.equals(getCountryCode()) && ("com.byd.auto_photo".equals(packageInfo.packageName) || "com.byd.auto_camera".equals(packageInfo.packageName))) {
                    Log.i(TAG, "CountryCode is HongKong");
                } else {
                    Iterator<ResolveInfo> it = appInfoList.iterator();
                    while (it.hasNext()) {
                        ActivityInfo activityInfo = it.next().activityInfo;
                        if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null) {
                            String str = applicationInfo.packageName;
                            if (!TextUtils.isEmpty(str) && str.equals(packageInfo.packageName)) {
                                String string = activityInfo.loadLabel(packageManager).toString();
                                Drawable drawableLoadIcon = activityInfo.loadIcon(packageManager);
                                AppInfo appInfo = new AppInfo();
                                if (TextUtils.isEmpty(string) || drawableLoadIcon == null) {
                                    appInfo.name = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString().trim();
                                    appInfo.icon = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
                                } else {
                                    appInfo.name = string;
                                    appInfo.icon = drawableLoadIcon;
                                }
                                appInfo.pinyin = getPinYin(appInfo.name);
                                appInfo.activity = activityInfo.name;
                                if (TextUtils.isEmpty(applicationInfo.className)) {
                                    appInfo.className = activityInfo.name;
                                } else {
                                    appInfo.className = applicationInfo.className;
                                }
                                appInfo.packageName = packageInfo.packageName;
                                appInfo.versionName = packageInfo.versionName;
                                appInfo.versionCode = packageInfo.versionCode;
                                appInfo.recent = 0;
                                appInfo.recentTime = i;
                                appInfo.resizeMode = activityInfo.resizeMode;
                                appInfo.icons = BitmapUtils.img2byte(Build.VERSION.SDK_INT >= 26 ? BitmapUtils.getIconDrawable(appInfo.icon) : null);
                                arrayList.add(appInfo);
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<AppInfo> getServiceList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        mCustomMediaComponents.addAll(Arrays.asList(context.getResources().getStringArray(R.array.custom_media_packages)));
        mHiddenApps.addAll(Arrays.asList(context.getResources().getStringArray(R.array.hidden_apps)));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(new Intent("android.media.browse.MediaBrowserService"), 64);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : listQueryIntentServices) {
            if (AppLauncherUtils.shouldAddToLaunchables(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name), mHiddenApps, mCustomMediaComponents, 3, 2)) {
                AppInfo appInfo = new AppInfo();
                appInfo.name = resolveInfo.serviceInfo.loadLabel(packageManager).toString();
                appInfo.pinyin = getPinYin(appInfo.name);
                appInfo.activity = NotificationCompat.CATEGORY_SERVICE;
                appInfo.className = resolveInfo.serviceInfo.name;
                appInfo.packageName = resolveInfo.serviceInfo.packageName;
                appInfo.icon = resolveInfo.serviceInfo.loadIcon(packageManager);
                if (Build.VERSION.SDK_INT >= 26) {
                    appInfo.icons = BitmapUtils.img2byte(BitmapUtils.getIconDrawable(appInfo.icon));
                } else {
                    appInfo.icons = BitmapUtils.img2byte(appInfo.icon);
                }
                appInfo.recent = 0;
                appInfo.recentTime = 0L;
                arrayList.add(appInfo);
            }
        }
        return arrayList;
    }

    public static AppInfo getServiceApp(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        mCustomMediaComponents.addAll(Arrays.asList(context.getResources().getStringArray(R.array.custom_media_packages)));
        mHiddenApps.addAll(Arrays.asList(context.getResources().getStringArray(R.array.hidden_apps)));
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("android.media.browse.MediaBrowserService"), 64)) {
            String str = resolveInfo.serviceInfo.packageName;
            if (packageName != null && packageName.equals(str) && AppLauncherUtils.shouldAddToLaunchables(new ComponentName(packageName, resolveInfo.serviceInfo.name), mHiddenApps, mCustomMediaComponents, 3, 2)) {
                AppInfo appInfo = new AppInfo();
                appInfo.name = resolveInfo.serviceInfo.loadLabel(packageManager).toString();
                appInfo.pinyin = getPinYin(appInfo.name);
                appInfo.activity = NotificationCompat.CATEGORY_SERVICE;
                appInfo.className = resolveInfo.serviceInfo.name;
                appInfo.packageName = resolveInfo.serviceInfo.packageName;
                appInfo.icon = resolveInfo.serviceInfo.loadIcon(packageManager);
                if (Build.VERSION.SDK_INT >= 26) {
                    appInfo.icons = BitmapUtils.img2byte(BitmapUtils.getIconDrawable(appInfo.icon));
                } else {
                    appInfo.icons = BitmapUtils.img2byte(appInfo.icon);
                }
                appInfo.recent = 0;
                appInfo.recentTime = 0L;
                return appInfo;
            }
        }
        return null;
    }

    public static com.byd.card.drag.ApplicationInfo getServiceApplicationInfo(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        mCustomMediaComponents.addAll(Arrays.asList(context.getResources().getStringArray(R.array.custom_media_packages)));
        mHiddenApps.addAll(Arrays.asList(context.getResources().getStringArray(R.array.hidden_apps)));
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("android.media.browse.MediaBrowserService"), 64)) {
            String str = resolveInfo.serviceInfo.packageName;
            if (packageName != null && packageName.equals(str) && AppLauncherUtils.shouldAddToLaunchables(new ComponentName(packageName, resolveInfo.serviceInfo.name), mHiddenApps, mCustomMediaComponents, 3, 2)) {
                com.byd.card.drag.ApplicationInfo applicationInfo = new com.byd.card.drag.ApplicationInfo();
                applicationInfo.title = resolveInfo.serviceInfo.loadLabel(packageManager).toString();
                applicationInfo.pinyin = getPinYin(applicationInfo.title);
                applicationInfo.activity = NotificationCompat.CATEGORY_SERVICE;
                applicationInfo.setPackageName(resolveInfo.serviceInfo.packageName);
                applicationInfo.setIcon(BitmapUtils.decodeBitmapFromResource(resolveInfo.serviceInfo.loadIcon(packageManager)));
                applicationInfo.iconBytes = BitmapUtils.img2byte(applicationInfo.getIcon());
                applicationInfo.position = CardDatabase.getInstance().getWorkSpaceItemDao().getMaxPosition() + 1;
                return applicationInfo;
            }
        }
        return null;
    }

    public static String getPinYin(String str) {
        String[] strArrSplit = (Pinyin.toPinyin(str, ListUtils.DEFAULT_JOIN_SEPARATOR)).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            sb.append(str2);
        }
        return sb.toString();
    }

    public static String getAppName(Context context, String packageName, String activity) {
        List<ResolveInfo> appInfoList = getAppInfoList(context, packageName);
        PackageManager packageManager = context.getPackageManager();
        Iterator<ResolveInfo> it = appInfoList.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && activityInfo.name.equals(activity)) {
                return activityInfo.loadLabel(packageManager).toString();
            }
        }
        return "";
    }

    public static List<ResolveInfo> getAppInfoList(Context context, String packageName) {
        Intent intent = new Intent();
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setAction("android.intent.action.MAIN");
        if (!TextUtils.isEmpty(packageName)) {
            intent.setPackage(packageName);
        }
        return context.getPackageManager().queryIntentActivities(intent, 0);
    }

    public static List<ComponentName> initWidgetComponentName(Context context) {
        ArrayList arrayList = new ArrayList();
        for (ComponentName componentName : initCardPackages()) {
            if (isInstalled(context, componentName.getPackageName())) {
                arrayList.add(new ComponentName(componentName.getPackageName(), componentName.getClassName()));
            }
        }
        Log.d(TAG, "initWidgetComponentName, componentName: " + arrayList);
        return arrayList;
    }

    private static List<ComponentName> initCardPackages() {
        ArrayList arrayList = new ArrayList();
        if (isInstalled(SystemUIApplication.getContext(), "com.neusoft.na.navigation")) {
            arrayList.add(new ComponentName("com.neusoft.na.navigation", Constant.WidgetName.NAVI_WIDGET));
        } else if (isInstalled(SystemUIApplication.getContext(), "com.huawei.maps.auto.app")) {
            arrayList.add(new ComponentName("com.huawei.maps.auto.app", Constant.WidgetName.HUAWEI_MAP_WIDGET));
        } else if (isInstalled(SystemUIApplication.getContext(), Constant.PackageName.AUTO_MAP_PKG) || isInstalled(SystemUIApplication.getContext(), Constant.PackageName.ZNAV_MAP_PKG) || isInstalled(SystemUIApplication.getContext(), Constant.PackageName.T_MAP_PKG)) {
            arrayList.add(new ComponentName(SystemUIApplication.getContext().getPackageName(), Constant.WidgetName.AUTO_MAP_FAKE_WIDGET));
        }
        arrayList.add(new ComponentName("com.byd.localmusic", Constant.WidgetName.MEDIA_WIDGET));
        if (isInstalled(SystemUIApplication.getContext(), "com.byd.mycar")) {
            arrayList.add(new ComponentName("com.byd.mycar", Constant.WidgetName.MY_CAR_WIDGET));
        } else if (isInstalled(SystemUIApplication.getContext(), Constant.PackageName.MY_CAR_PKG_TS)) {
            arrayList.add(new ComponentName(Constant.PackageName.MY_CAR_PKG_TS, Constant.WidgetName.MY_CAR_WIDGET));
        }
        arrayList.add(new ComponentName("com.byd.bluetoothcall", Constant.WidgetName.BLUE_WIDGET));
        Log.i(TAG, "initCardPackages listSize : " + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Log.i(TAG, "initCardPackages, package: " + ((ComponentName) it.next()).getPackageName());
        }
        return arrayList;
    }

    public static boolean isInstalled(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                Log.i(TAG, "isInstalled hasInstalled! packageName " + packageName + " packageInfo = " + packageInfo);
                return true;
            }
            Log.i(TAG, "isInstalled not install " + packageName + " packageInfo is null! ");
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(TAG, "isInstalled NameNotFoundException! not install " + packageName);
            return false;
        }
    }

    public static boolean isSystemApp(Context context, String pkgName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(pkgName, 0);
        } catch (Throwable th) {
            BydLogHelper.logE(TAG, th.getMessage());
            packageInfo = null;
        }
        return packageInfo != null && (packageInfo.applicationInfo.flags & 1) == 1;
    }

    public static boolean isSystemApp(PackageInfo pInfo) {
        return (pInfo.applicationInfo.flags & 1) != 0;
    }

    public static String getTraffics(Context context, String packageName) {
        for (ApplicationInfo applicationInfo : context.getPackageManager().getInstalledApplications(0)) {
            if (applicationInfo.packageName.equals(packageName)) {
                int i = applicationInfo.uid;
                long uidTxBytes = TrafficStats.getUidTxBytes(i) + TrafficStats.getUidRxBytes(i);
                return uidTxBytes == -2 ? "" : Formatter.formatFileSize(context, uidTxBytes);
            }
        }
        return "";
    }

    public static AppInfo getWorkSpaceAppInfo(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        AppInfo appInfo = new AppInfo();
        List<ResolveInfo> appInfoList = getAppInfoList(context, packageName);
        Log.i(TAG, "getWorkSpaceAppInfo packageName = " + packageName + ", resolveInfoList = " + appInfoList.size());
        if (appInfoList.isEmpty()) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 128);
            Iterator<ResolveInfo> it = appInfoList.iterator();
            while (it.hasNext()) {
                ActivityInfo activityInfo = it.next().activityInfo;
                if (activityInfo != null) {
                    appInfo.activity = activityInfo.name;
                    appInfo.name = activityInfo.loadLabel(packageManager).toString().trim();
                    appInfo.icon = activityInfo.loadIcon(packageManager);
                    appInfo.resizeMode = activityInfo.resizeMode;
                }
            }
            if (TextUtils.isEmpty(appInfo.name)) {
                appInfo.name = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            }
            if (appInfo.icon == null) {
                appInfo.icon = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
            }
            appInfo.pinyin = getPinYin(appInfo.name);
            appInfo.packageName = packageInfo.packageName;
            appInfo.versionName = packageInfo.versionName;
            appInfo.versionCode = packageInfo.versionCode;
            appInfo.recentTime = 0L;
            appInfo.icons = BitmapUtils.img2byte(Build.VERSION.SDK_INT >= 26 ? BitmapUtils.getIconDrawable(appInfo.icon) : null);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appInfo;
    }

    public static com.byd.card.drag.ApplicationInfo getWorkSpaceApplicationInfo(Context context, String packageName, int position) {
        PackageManager packageManager = context.getPackageManager();
        com.byd.card.drag.ApplicationInfo applicationInfo = new com.byd.card.drag.ApplicationInfo();
        List<ResolveInfo> appInfoList = getAppInfoList(context, packageName);
        Log.i(TAG, "getWorkSpaceApplicationInfo packageName = " + packageName + ", resolveInfoList = " + appInfoList.size());
        if (appInfoList.isEmpty()) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 128);
            Iterator<ResolveInfo> it = appInfoList.iterator();
            while (it.hasNext()) {
                ActivityInfo activityInfo = it.next().activityInfo;
                if (activityInfo != null) {
                    applicationInfo.activity = activityInfo.name;
                    applicationInfo.title = activityInfo.loadLabel(packageManager).toString();
                    applicationInfo.setIcon(BitmapUtils.decodeBitmapFromResource(activityInfo.loadIcon(packageManager)));
                }
            }
            if (TextUtils.isEmpty(applicationInfo.title)) {
                applicationInfo.title = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString().trim();
            }
            if (applicationInfo.getIcon() == null) {
                applicationInfo.setIcon(BitmapUtils.decodeBitmapFromResource(packageInfo.applicationInfo.loadIcon(context.getPackageManager())));
            }
            applicationInfo.mPackageName = packageInfo.packageName;
            applicationInfo.versionName = packageInfo.versionName;
            applicationInfo.versionCode = packageInfo.versionCode;
            if ("com.ts.carplay.app".equals(packageInfo.packageName)) {
                applicationInfo.position = 1;
                List<com.byd.card.drag.ApplicationInfo> allApps = CardDatabase.getInstance().getWorkSpaceItemDao().getAllApps();
                for (int i = 1; i < allApps.size(); i++) {
                    com.byd.card.drag.ApplicationInfo applicationInfo2 = allApps.get(i);
                    applicationInfo2.position++;
                    CardDatabase.getInstance().getWorkSpaceItemDao().update(applicationInfo2);
                }
            } else if ("com.ts.androidauto.app".equals(packageInfo.packageName)) {
                applicationInfo.position = 2;
                List<com.byd.card.drag.ApplicationInfo> allApps2 = CardDatabase.getInstance().getWorkSpaceItemDao().getAllApps();
                for (int i2 = 2; i2 < allApps2.size(); i2++) {
                    com.byd.card.drag.ApplicationInfo applicationInfo3 = allApps2.get(i2);
                    applicationInfo3.position++;
                    CardDatabase.getInstance().getWorkSpaceItemDao().update(applicationInfo3);
                }
            } else {
                applicationInfo.position = position + 1;
            }
            applicationInfo.iconBytes = BitmapUtils.img2byte(applicationInfo.getIcon());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return applicationInfo;
    }

    public static void uninstallApk(Context context, String packageName) {
        context.getPackageManager().getPackageInstaller().uninstall(packageName, PendingIntent.getBroadcast(context, 1, new Intent(), 134217728).getIntentSender());
    }

    public static void killBackgroundProcesses(Context context, String packageName) {
        ((ActivityManager) context.getSystemService(Constants.PackageInfo.ACTIVITY)).killBackgroundProcesses(packageName);
    }

    public static String getTopActivity(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        UsageEvents usageEventsQueryEvents = ((UsageStatsManager) context.getSystemService("usagestats")).queryEvents(jCurrentTimeMillis - 10000, jCurrentTimeMillis);
        UsageEvents.Event event = new UsageEvents.Event();
        String className = "";
        while (usageEventsQueryEvents.hasNextEvent()) {
            usageEventsQueryEvents.getNextEvent(event);
            if (event.getEventType() == 1) {
                className = event.getClassName();
            }
        }
        return className;
    }

    public static ActivityManager.RunningTaskInfo getRunningTask(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(Constants.PackageInfo.ACTIVITY)).getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() <= 0) {
            return null;
        }
        return runningTasks.get(0);
    }

    public static void jumpApplicationDetail(Context context, String packageName) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("com.byd.carsettings.ui.activity.AppDetailActivity");
        Bundle bundle = new Bundle();
        bundle.putString("app_package", packageName);
        intent.putExtras(bundle);
        context.startActivity(intent);
        Log.d(TAG, "jumpApplicationDetail packageName = " + packageName);
    }

    public static boolean isShowCamera() {
        String str = SystemProperties.get(Constant.CAMERA_ISP, "-1");
        String str2 = SystemProperties.get(Constant.CAMERA_NAME, "-1");
        Log.w(TAG, "cameraIsp = " + str + ", cameraName = " + str2);
        return ("-1".equals(str) || "-1".equals(str2) || Constant.HONG_KONG_CODE.equals(getCountryCode())) ? false : true;
    }

    public static boolean isShowAVM() {
        String str = SystemProperties.get(Constant.AVM_ISP, "-1");
        String str2 = SystemProperties.get(Constant.AVM_NAME, "-1");
        Log.w(TAG, "avmIsp = " + str + ", avmName = " + str2);
        return ("-1".equals(str) || "-1".equals(str2)) ? false : true;
    }

    public static boolean isShowSmart() {
        Context applicationContext = SystemUIApplication.getInstance().getApplicationContext();
        return "1".equals(getCarType()) ? BYDAutoBodyworkDevice.getInstance(applicationContext).hasMessage(53) == 1 : BYDAutoStudy.getInstance(applicationContext).getSupportSmartChargingPHEV() == 1;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static boolean isCarHEV() {
        String str = SystemProperties.get(AUTO_TYPE);
        Log.i(TAG, "isCarHEV carType = " + str);
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case 1572:
                if (str.equals("15")) {
                    b = 0;
                }
                break;
            case 1630:
                if (str.equals("31")) {
                    b = 1;
                }
                break;
            case 1634:
                if (str.equals("35")) {
                    b = 2;
                }
                break;
            case 1663:
                if (str.equals("43")) {
                    b = 3;
                }
                break;
            case 1666:
                if (str.equals("46")) {
                    b = 4;
                }
                break;
            case 1667:
                if (str.equals("47")) {
                    b = 5;
                }
                break;
            case 1668:
                if (str.equals("48")) {
                    b = 6;
                }
                break;
            case 1691:
                if (str.equals("50")) {
                    b = 7;
                }
                break;
            case 1693:
                if (str.equals("52")) {
                    b = 8;
                }
                break;
            case 1697:
                if (str.equals("56")) {
                    b = 9;
                }
                break;
            case 1698:
                if (str.equals("57")) {
                    b = 10;
                }
                break;
            case 1723:
                if (str.equals("61")) {
                    b = Ascii.VT;
                }
                break;
            case 1725:
                if (str.equals("63")) {
                    b = Ascii.FF;
                }
                break;
            case 1727:
                if (str.equals("65")) {
                    b = Ascii.CR;
                }
                break;
            case 1729:
                if (str.equals("67")) {
                    b = Ascii.SO;
                }
                break;
            case 1754:
                if (str.equals("71")) {
                    b = Ascii.SI;
                }
                break;
            case 1757:
                if (str.equals("74")) {
                    b = Ascii.DLE;
                }
                break;
            case 1787:
                if (str.equals("83")) {
                    b = 17;
                }
                break;
            case 1789:
                if (str.equals("85")) {
                    b = Ascii.DC2;
                }
                break;
            case 1792:
                if (str.equals("88")) {
                    b = 19;
                }
                break;
            case 1793:
                if (str.equals("89")) {
                    b = Ascii.DC4;
                }
                break;
            case 1815:
                if (str.equals("90")) {
                    b = Ascii.NAK;
                }
                break;
            case 48627:
                if (str.equals("102")) {
                    b = Ascii.SYN;
                }
                break;
            case 48657:
                if (str.equals("111")) {
                    b = Ascii.ETB;
                }
                break;
            case 48659:
                if (str.equals("113")) {
                    b = Ascii.CAN;
                }
                break;
            case 48661:
                if (str.equals("115")) {
                    b = Ascii.EM;
                }
                break;
            case 48688:
                if (str.equals("121")) {
                    b = Ascii.SUB;
                }
                break;
            case 48696:
                if (str.equals("129")) {
                    b = Ascii.ESC;
                }
                break;
            case 48724:
                if (str.equals("136")) {
                    b = Ascii.FS;
                }
                break;
            case 48749:
                if (str.equals("140")) {
                    b = Ascii.GS;
                }
                break;
            case 48750:
                if (str.equals("141")) {
                    b = Ascii.RS;
                }
                break;
            case 48752:
                if (str.equals("143")) {
                    b = Ascii.US;
                }
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
                return true;
            default:
                return false;
        }
    }

    public static String getCarType() {
        return SystemProperties.get("persist.sys.energytype");
    }

    public static void updateMediaCard(Context mContext) {
        Intent intent = new Intent();
        intent.setAction(FloatCardService.ACTION_REQUEST_FOR_MEDIA_INFO);
        mContext.sendBroadcast(intent);
    }

    private static boolean isSupportNavi() {
        String countryCode = getCountryCode();
        for (String str : SystemUIApplication.getInstance().getApplicationContext().getResources().getStringArray(R.array.navi_country_code)) {
            if (countryCode != null && countryCode.equals(str)) {
                Log.i(TAG, "isSupportNavi true");
                return true;
            }
        }
        Log.i(TAG, "isSupportNavi false");
        return false;
    }

    public static String getCountryCode() {
        String str;
        IOException e;
        JSONArray jSONArray;
        int i;
        Log.d(TAG, "getCountryCode start");
        try {
            InputStream inputStreamOpen = SystemUIApplication.getInstance().getResources().getAssets().open("countrycode.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            str = new String(bArr);
            try {
                inputStreamOpen.close();
            } catch (IOException e2) {
                e = e2;
                Log.e(TAG, "getCountryCode e = " + e);
            }
        } catch (IOException e3) {
            str = null;
            e = e3;
        }
        if (str == null) {
            Log.d(TAG, "content is null ");
            return "";
        }
        int i2 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
        BydLogHelper.logD(TAG, "parseJson codeNum =" + i2);
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e4) {
            Log.e(TAG, "getCountryCode e = " + e4);
        }
        for (i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.getString("countrycode");
            String string2 = jSONObject.getString("number");
            if (!string2.isEmpty() && string2.equals(String.valueOf(i2))) {
                Log.d(TAG, "getCountryCode countrycode = " + string);
                return string;
            }
            Log.d(TAG, "getCountryCode return empty");
            return "";
        }
        Log.d(TAG, "getCountryCode return empty");
        return "";
    }

    public static boolean isRtl(Context context) {
        String language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        return "ar".equals(language) || "iw".equals(language) || "fa".equals(language) || "ur".equals(language) || "yi".equals(language) || "dv".equals(language);
    }

    public static boolean isRightRudder() {
        String str = SystemProperties.get(RUDDER_VALUE, Constant.CarType.CAR_TYPE_FUEL);
        Log.i(TAG, "isRightRudder position = " + str);
        return Integer.parseInt(str) == 1;
    }

    public static String getAppNameByPkg(Context context, String pkg) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationInfo(pkg, 0).loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "getAppName Exception e : " + e.getMessage());
            return "";
        }
    }

    public static boolean isNoMapDesktop() {
        String countryCode = getCountryCode();
        BydLogHelper.logD("countryCode = " + countryCode);
        if (!TextUtils.isEmpty(countryCode)) {
            for (String str : NO_MAP_ARRAY) {
                if (str.equals(countryCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSupportMapDesktop() {
        PackageInfo packageInfo;
        PackageManager packageManager = SystemUIApplication.getInstance().getPackageManager();
        PackageInfo packageInfo2 = null;
        try {
            packageInfo = packageManager.getPackageInfo("com.neusoft.na.navigation", 0);
        } catch (PackageManager.NameNotFoundException e) {
            BydLogHelper.logD(TAG, "isNoMapDesktop error" + e.toString());
            packageInfo = null;
        }
        try {
            packageInfo2 = packageManager.getPackageInfo("com.huawei.maps.auto.app", 0);
        } catch (PackageManager.NameNotFoundException e2) {
            BydLogHelper.logD(TAG, "isNoMapDesktop error" + e2.toString());
        }
        return (packageInfo == null && packageInfo2 == null) ? false : true;
    }

    public static boolean isAppInstall(String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            try {
                return SystemUIApplication.getInstance().getPackageManager().getPackageInfo(packageName, 0) != null;
            } catch (PackageManager.NameNotFoundException e) {
                BydLogHelper.logD(TAG, "isAppInstall error " + e.toString());
            }
        }
        return false;
    }

    public static boolean isShowingCarSettingsSubActivity() {
        return sIsShowingCarSettingsSubActivity;
    }

    public static void setIsShowingCarSettingsSubActivity(boolean isShowingCarSettingsSubActivity) {
        sIsShowingCarSettingsSubActivity = isShowingCarSettingsSubActivity;
    }

    public static boolean isCarEV() {
        if (TextUtils.isEmpty(sEnergyType)) {
            sEnergyType = SystemProperties.get("persist.sys.energytype");
        }
        BydLogHelper.logD("isCarEv car sEnergyType = " + sEnergyType);
        if (!TextUtils.isEmpty(sEnergyType)) {
            return TextUtils.equals(sEnergyType, "1");
        }
        if (sCarAutoType <= 0) {
            sCarAutoType = BYDAutoBodyworkDevice.getInstance(SystemUIApplication.getInstance().getApplicationContext()).getAutoType();
        }
        switch (sCarAutoType) {
            case 0:
            case 14:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 123:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 182:
            case 196:
            case 208:
                BydLogHelper.logD("isCarEv car autoType = " + sCarAutoType + " return true");
                return true;
            default:
                BydLogHelper.logD("isCarEv car autoType = " + sCarAutoType + " return false");
                return false;
        }
    }

    public static boolean isP(Context context) {
        int gearboxAutoModeLevel;
        try {
            boolean zIsCarEV = isCarEV();
            if (zIsCarEV) {
                gearboxAutoModeLevel = BYDAutoGearboxDevice.getInstance(context).getGear();
            } else {
                gearboxAutoModeLevel = BYDAutoGearboxDevice.getInstance(context).getGearboxAutoModeLevel();
            }
            boolean z = true;
            if (gearboxAutoModeLevel != 1) {
                z = false;
            }
            BydLogHelper.logD("isCarEv = " + zIsCarEV + ", gear = " + gearboxAutoModeLevel + ", isP = " + z);
            return z;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static String extractShortcutIdentify(String shortcut) {
        if (TextUtils.isEmpty(shortcut)) {
            Log.w(TAG, "ExtractShortcutIdentify, param null!");
            return null;
        }
        int iIndexOf = shortcut.indexOf(ShortcutInfo.Column.SHORTCUT_CONNECTOR);
        if (iIndexOf < 0) {
            Log.w(TAG, "ExtractShortcutIdentify, error! not contains $$! shortcut = " + shortcut);
            return null;
        }
        return shortcut.substring(iIndexOf + 2);
    }

    public static void removeShortcuts(String origin) {
        CardDatabase.getInstance().getShortcutInfoDao().deleteShortcutByOrigin(origin);
        int iDeleteShortcutByOrigin = CardDatabase.getInstance().getWorkSpaceItemDao().deleteShortcutByOrigin(origin + ShortcutInfo.Column.SHORTCUT_CONNECTOR);
        Log.d(TAG, "removeShortcuts origin = " + origin + " remove = " + iDeleteShortcutByOrigin);
        if (iDeleteShortcutByOrigin > 0) {
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.ACTION_SHORTCUTS_REMOVED));
        }
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.REMOVE_SHORTCUTS_CARD, origin));
    }

    public static String extractShortcutOrigin(String shortcut) {
        if (TextUtils.isEmpty(shortcut)) {
            Log.w(TAG, "ExtractShortcutOrigin, param null!");
            return null;
        }
        int iIndexOf = shortcut.indexOf(ShortcutInfo.Column.SHORTCUT_CONNECTOR);
        if (iIndexOf < 0) {
            Log.w(TAG, "ExtractShortcutOrigin, error! not contains $$! shortcut = " + shortcut);
            return null;
        }
        return shortcut.substring(0, iIndexOf);
    }

    public static void uninstallShortcut(Context context, final com.byd.card.drag.ApplicationInfo applicationInfo) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.utils.AppUtils.2
            @Override // java.lang.Runnable
            public void run() {
                String packageName = applicationInfo.getPackageName();
                String strExtractShortcutIdentify = AppUtils.extractShortcutIdentify(packageName);
                if (TextUtils.isEmpty(strExtractShortcutIdentify)) {
                    Log.w(AppUtils.TAG, "uninstallShortcut shortcutIdentify null packageName = " + packageName);
                    return;
                }
                String strExtractShortcutOrigin = AppUtils.extractShortcutOrigin(packageName);
                if (TextUtils.isEmpty(strExtractShortcutOrigin)) {
                    Log.w(AppUtils.TAG, "uninstallShortcut shortcutOrigin null packageName = " + packageName);
                    return;
                }
                ShortcutInfo shortcutByIdentify = CardDatabase.getInstance().getShortcutInfoDao().getShortcutByIdentify(strExtractShortcutIdentify, strExtractShortcutOrigin);
                if (shortcutByIdentify == null) {
                    Log.w(AppUtils.TAG, "uninstallShortcut shortcutOrigin null packageName = " + packageName);
                    return;
                }
                CardDatabase.getInstance().getShortcutInfoDao().delete(shortcutByIdentify);
                CardDatabase.getInstance().getWorkSpaceItemDao().delete(CardDatabase.getInstance().getWorkSpaceItemDao().getAppByPackageName(packageName));
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.ACTION_PACKAGE_REMOVED, applicationInfo));
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.REMOVE_APP_CARD, packageName));
                Log.d(AppUtils.TAG, "uninstallShortcut packageName = " + packageName);
            }
        });
    }

    public static boolean isClassExist(Context context, String packageName, String className) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(packageName, className), 0);
            Log.d(TAG, "isClassExist true! packageName = " + packageName + " className = " + className);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d(TAG, "isClassExist false! packageName = " + packageName + " className = " + className);
            return false;
        }
    }
}
