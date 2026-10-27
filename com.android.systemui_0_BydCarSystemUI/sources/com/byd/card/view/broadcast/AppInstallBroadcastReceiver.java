package com.byd.card.view.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.R;
import com.android.systemui.stackdivider.DividerUtils;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.db.AppInfoDao;
import com.byd.card.db.ApplicationDao;
import com.byd.card.db.CardDatabase;
import com.byd.card.drag.ApplicationInfo;
import com.byd.card.floatcard.Executors;
import com.byd.card.forbidden.ForbiddenManager;
import com.byd.card.provider.ShortcutProvider;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.views.window.ToastUtils;
import com.google.common.base.Ascii;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class AppInstallBroadcastReceiver extends BroadcastReceiver {
    public static int ADD_APP = 2;
    public static int REMOVED_APP = 1;
    public static int REMOVED_SHORTCUTS = 3;
    private static final String TAG = "AppInstallBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        final String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
        Log.w(TAG, "onReceive action = " + action + ", packageName = " + schemeSpecificPart);
        if (TextUtils.isEmpty(schemeSpecificPart) || schemeSpecificPart == null) {
            Log.d(TAG, "packageName is null");
            return;
        }
        action.hashCode();
        if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
            Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.broadcast.AppInstallBroadcastReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    AppInfoDao appInfoDao = CardDatabase.getInstance().getAppInfoDao();
                    AppInfo appInfoByPackage = appInfoDao.getAppInfoByPackage(schemeSpecificPart);
                    if (appInfoByPackage != null) {
                        appInfoDao.delete(appInfoByPackage);
                    }
                    String str = schemeSpecificPart;
                    str.hashCode();
                    switch (str) {
                        case "com.byd.automap":
                        case "com.byd.mycar.ts":
                        case "com.byd.mycar":
                        case "net.zmap.znav.byd8155":
                        case "com.neusoft.na.navigation":
                        case "com.huawei.maps.auto.app":
                        case "com.tmap.auto.byd":
                            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.REMOVE_WIDGET_CARD, schemeSpecificPart));
                            break;
                        default:
                            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.REMOVE_APP_CARD, schemeSpecificPart));
                            break;
                    }
                    if (ShortcutProvider.SHORTCUT_WHITELIST.contains(schemeSpecificPart)) {
                        AppUtils.removeShortcuts(schemeSpecificPart);
                    }
                    ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                    ApplicationInfo appByPackageName = workSpaceItemDao.getAppByPackageName(schemeSpecificPart);
                    if (appByPackageName != null) {
                        workSpaceItemDao.delete(appByPackageName);
                        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.ACTION_PACKAGE_REMOVED, appByPackageName));
                        Executors.MAIN_EXECUTOR.post(new Runnable() { // from class: com.byd.card.view.broadcast.AppInstallBroadcastReceiver.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (!AppInstallBroadcastReceiver.this.showUninstallToast(schemeSpecificPart) || AppUtils.isSystemApp(context, schemeSpecificPart)) {
                                    return;
                                }
                                ToastUtils.show(context, context.getString(R.string.uninstall_finish));
                            }
                        });
                    }
                    Log.w(AppInstallBroadcastReceiver.TAG, "onReceive ACTION_PACKAGE_REMOVED applicationInfo = " + appByPackageName + ", appInfo = " + appInfoByPackage);
                    if (appInfoByPackage != null) {
                        if (DividerUtils.getInstance().removeSplitScreenAppInfo(appInfoByPackage)) {
                            DividerUtils.getInstance().notifyAppListChanged();
                        }
                        ForbiddenManager.removeForbiddenAppListOnNonP(appInfoByPackage.packageName);
                    }
                }
            });
        } else if (action.equals("android.intent.action.PACKAGE_ADDED")) {
            Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.broadcast.AppInstallBroadcastReceiver.1
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                @Override // java.lang.Runnable
                public void run() {
                    AppInfo workSpaceAppInfo = AppUtils.getWorkSpaceAppInfo(context, schemeSpecificPart);
                    if (CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage(schemeSpecificPart) == null && workSpaceAppInfo != null) {
                        CardDatabase.getInstance().getAppInfoDao().insert(workSpaceAppInfo);
                    }
                    ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                    ApplicationInfo appByPackageName = workSpaceItemDao.getAppByPackageName(schemeSpecificPart);
                    ApplicationInfo workSpaceApplicationInfo = AppUtils.getWorkSpaceApplicationInfo(context, schemeSpecificPart, workSpaceItemDao.getMaxPosition());
                    Log.i(AppInstallBroadcastReceiver.TAG, "onReceive databaseInfo = " + appByPackageName + ", packageInfo = " + workSpaceApplicationInfo);
                    if (appByPackageName != null) {
                        if (workSpaceApplicationInfo != null) {
                            workSpaceItemDao.update(workSpaceApplicationInfo);
                            String packageName = workSpaceApplicationInfo.getPackageName();
                            packageName.hashCode();
                            switch (packageName) {
                                case "com.byd.automap":
                                case "com.byd.mycar.ts":
                                case "com.byd.mycar":
                                case "net.zmap.znav.byd8155":
                                case "com.neusoft.na.navigation":
                                case "com.huawei.maps.auto.app":
                                case "com.tmap.auto.byd":
                                    EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.ADD_NAVI, schemeSpecificPart));
                                    break;
                            }
                        }
                    } else if (workSpaceApplicationInfo != null) {
                        workSpaceItemDao.insert(workSpaceApplicationInfo);
                        String packageName2 = workSpaceApplicationInfo.getPackageName();
                        packageName2.hashCode();
                        switch (packageName2.hashCode()) {
                            case -2086107553:
                                if (!packageName2.equals(Constant.PackageName.AUTO_MAP_PKG)) {
                                }
                                break;
                            case -2050414413:
                                if (!packageName2.equals(Constant.PackageName.MY_CAR_PKG_TS)) {
                                }
                                break;
                            case -1215565766:
                                if (!packageName2.equals("com.byd.mycar")) {
                                }
                                break;
                            case -809562372:
                                if (!packageName2.equals(Constant.PackageName.ZNAV_MAP_PKG)) {
                                }
                                break;
                            case -46040100:
                                if (!packageName2.equals("com.neusoft.na.navigation")) {
                                }
                                break;
                            case -1476257:
                                if (!packageName2.equals("com.huawei.maps.auto.app")) {
                                }
                                break;
                            case 1996350279:
                                if (!packageName2.equals(Constant.PackageName.T_MAP_PKG)) {
                                }
                                break;
                            default:
                                break;
                        }
                        /*  JADX ERROR: Method code generation error
                            java.lang.NullPointerException: Switch insn not found in header
                            	at java.base/java.util.Objects.requireNonNull(Objects.java:259)
                            	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:246)
                            	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:88)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                            	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:157)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:136)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                            */
                        /*
                            Method dump skipped, instruction units count: 480
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.view.broadcast.AppInstallBroadcastReceiver.AnonymousClass1.run():void");
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public boolean showUninstallToast(String pkg) {
            if (TextUtils.isEmpty(pkg)) {
                return false;
            }
            pkg.hashCode();
            byte b = -1;
            switch (pkg.hashCode()) {
                case -2086107553:
                    if (pkg.equals(Constant.PackageName.AUTO_MAP_PKG)) {
                        b = 0;
                    }
                    break;
                case -2050414413:
                    if (pkg.equals(Constant.PackageName.MY_CAR_PKG_TS)) {
                        b = 1;
                    }
                    break;
                case -2029002628:
                    if (pkg.equals("com.stingray.karaoke.car.byd8155f")) {
                        b = 2;
                    }
                    break;
                case -1798611618:
                    if (pkg.equals("com.android.car.media")) {
                        b = 3;
                    }
                    break;
                case -1701008145:
                    if (pkg.equals("com.ts.androidauto.app")) {
                        b = 4;
                    }
                    break;
                case -1467358375:
                    if (pkg.equals("com.ts.carplay.app")) {
                        b = 5;
                    }
                    break;
                case -1374912664:
                    if (pkg.equals("com.byd.vrassistant.xf")) {
                        b = 6;
                    }
                    break;
                case -1215565766:
                    if (pkg.equals("com.byd.mycar")) {
                        b = 7;
                    }
                    break;
                case -809562372:
                    if (pkg.equals(Constant.PackageName.ZNAV_MAP_PKG)) {
                        b = 8;
                    }
                    break;
                case -239089708:
                    if (pkg.equals(Constant.PackageName.AUTO_SERVICE)) {
                        b = 9;
                    }
                    break;
                case -211654713:
                    if (pkg.equals("com.byd.auto_camera")) {
                        b = 10;
                    }
                    break;
                case -158582775:
                    if (pkg.equals("com.byd.auto_emanual")) {
                        b = Ascii.VT;
                    }
                    break;
                case -46040100:
                    if (pkg.equals("com.neusoft.na.navigation")) {
                        b = Ascii.FF;
                    }
                    break;
                case -1476257:
                    if (pkg.equals("com.huawei.maps.auto.app")) {
                        b = Ascii.CR;
                    }
                    break;
                case 27393645:
                    if (pkg.equals(Constant.PackageName.OVER_SEA_APP_STORE)) {
                        b = Ascii.SO;
                    }
                    break;
                case 432180365:
                    if (pkg.equals("com.byd.vrsettings")) {
                        b = Ascii.SI;
                    }
                    break;
                case 514648213:
                    if (pkg.equals(Constant.PackageName.LATIN)) {
                        b = Ascii.DLE;
                    }
                    break;
                case 613209156:
                    if (pkg.equals("com.spotify.music")) {
                        b = 17;
                    }
                    break;
                case 1069617143:
                    if (pkg.equals(Constant.PackageName.IQQIJNI_INPUT)) {
                        b = Ascii.DC2;
                    }
                    break;
                case 1435822868:
                    if (pkg.equals("com.amazon.mp3.automotiveOS")) {
                        b = 19;
                    }
                    break;
                case 1589842891:
                    if (pkg.equals("com.telenav.scoutivi.spotify.byd")) {
                        b = Ascii.DC4;
                    }
                    break;
                case 1996350279:
                    if (pkg.equals(Constant.PackageName.T_MAP_PKG)) {
                        b = Ascii.NAK;
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
                    return false;
                default:
                    return true;
            }
        }
    }
