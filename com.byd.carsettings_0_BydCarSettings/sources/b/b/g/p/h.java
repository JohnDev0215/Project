package b.b.g.p;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ApplicationPackageManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.provider.Settings;
import b.b.g.m.d.i;
import b.b.g.q.f.b.t;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.carsettings.model.dilink.PermissionType;
import com.byd.carsettings.system.BydStorageManager;
import com.bydux.api.common.views.skin.loader.SkinManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: compiled from: SystemOperator.java */
/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f1402a = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.WRITE_SETTINGS", "android.permission.CALL_PHONE", "android.permission.CAMERA", "android.permission.READ_SMS", "android.permission.READ_CONTACTS"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1403b = {R.drawable.icon_storage_48, R.drawable.icon_place_48, R.drawable.icon_setting_48, R.drawable.icon_phone_48, R.drawable.icon_camera_camera_48, R.drawable.icon_sms_48, R.drawable.icon_contacts_48};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final List<String> f1404c = Arrays.asList(b.b.f.b.f651a, b.b.f.b.f653c, "com.byd.datashared", "vendor.qti.hardware.cacert.server", "com.byd.uereport", "android", "com.byd.data.collect", "com.byd.vrassistant", "com.byd.autoservice", "com.qualcomm.location", "com.byd.ota", "com.byd.card", "com.byd.sing", "com.byd.water.wallpaper", "com.byd.logtools", "com.byd.secretcode", "com.spotify.music", "com.byd.engineermode", "com.byd.globalnotification", "com.byd.cleardata", "com.idd.hidps", "com.desay.engmode", "com.desaysv.ivi.vds.log", "com.desaysv.ivi.vds.upgrade", "com.amazon.mp3.automotiveOS", "skplanet.musicmate", "deezer.android.app", "com.aspiro.tidal", "com.iloen.melon", "tunein.player", "de.ard.audiothek", "au.com.shiftyjelly.pocketcasts", "com.clearchannel.iheartradio.controller", "com.audials", "com.radioline.android.radioline.auto", "com.overdrive.mobile.android.automotive.libby", "com.audible.application", "com.radiofrance.radio.radiofrance.android", "de.ard.sportschau.automotive", "de.heute.mobile", "com.bloomberg.android.plus");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final List<String> f1405d = Arrays.asList("com.byd.filemanager", "com.byd.hvac", "com.byd.carsettings", "com.byd.mycar", "com.byd.mycar.ts", "com.byd.mycar.wc", "com.byd.vrsettings", "com.byd.trafficmonitor", "com.byd.automultipletheme", "com.byd.bluetoothcall", "com.byd.localmusic", "com.byd.smarttravel", "com.byd.vrassistant.xf.image", "com.byd.vrassistant.xf");

    /* JADX INFO: compiled from: SystemOperator.java */
    public static class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            b.b.m.f.d.d.a(t.class);
        }
    }

    /* JADX INFO: compiled from: SystemOperator.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1406a;

        static {
            int[] iArr = new int[PermissionType.values().length];
            f1406a = iArr;
            try {
                iArr[PermissionType.STORAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1406a[PermissionType.PHONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1406a[PermissionType.CONTACTS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1406a[PermissionType.LOCATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1406a[PermissionType.CAMERA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1406a[PermissionType.RECORD_AUDIO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1406a[PermissionType.CAR_INFO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1406a[PermissionType.METER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1406a[PermissionType.USAGE_ACCESS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1406a[PermissionType.WRITE_SETTINGS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1406a[PermissionType.CHARGE_INFO.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1406a[PermissionType.PANORAMIC_CAMERA.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1406a[PermissionType.ENGINE_INFO.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1406a[PermissionType.TRANSMISSION_INFO.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f1406a[PermissionType.SYSTEM_ALERT_WINDOW.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f1406a[PermissionType.SAFETY_BELT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f1406a[PermissionType.CALENDAR.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f1406a[PermissionType.TIME.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f1406a[PermissionType.SMS.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f1406a[PermissionType.AC_INFO.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f1406a[PermissionType.OIL_POWER_INFO.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f1406a[PermissionType.BODY_SENSOR.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f1406a[PermissionType.CAR_BODY_SENSOR.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f1406a[PermissionType.CAR_LIGHTING.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f1406a[PermissionType.CAR_SETTINGS.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f1406a[PermissionType.SPEED.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f1406a[PermissionType.DOOR_LOCK.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f1406a[PermissionType.STATISTIC.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f1406a[PermissionType.YUN.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    public static int a(Context context, List<b.b.g.m.d.c> list) {
        if (context == null || list == null || list.isEmpty()) {
            return 0;
        }
        Iterator<b.b.g.m.d.c> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().f(context)) {
                i++;
            }
        }
        b.b.m.c.a.a("SystemOperator", "---checkPermissionsCount--count: " + i + ", size: " + list.size());
        return i;
    }

    public static void b(Context context, String str, b.b.g.p.j.a aVar) {
        PackageManager packageManager = context.getPackageManager();
        try {
            b.b.m.c.a.a("SystemOperator", "-----clearAppCache-----start clear pkg:" + str);
            packageManager.getPackageInfo(str, 0);
            packageManager.deleteApplicationCacheFiles(str, aVar);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("SystemOperator", "------clearAppCache-----e: " + e2);
        }
        b.b.m.c.a.a("SystemOperator", "------clearAppCache-----clear end.");
    }

    public static void c(Context context, String str, b.b.g.p.j.a aVar) {
        PackageManager packageManager = context.getPackageManager();
        try {
            b.b.m.c.a.a("SystemOperator", "-----clearAppData-----start clear.");
            packageManager.clearApplicationUserData(packageManager.getPackageInfo(str, 0).packageName, aVar);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("SystemOperator", "------clearAppData-----e: " + e2);
        }
        b.b.m.c.a.a("SystemOperator", "------clearAppData-----clear end.");
    }

    public static void d() {
        b.b.m.c.a.a("SystemOperator", "---closeMeterNaviMap----");
        b.b.g.n.q.h hVarM = b.b.g.n.q.h.m();
        int i = hVarM.i();
        if (i == 0 || i == 1) {
            hVarM.L(1, 1);
        }
    }

    public static i e(List<b.b.g.m.d.c> list, PermissionType permissionType) {
        if (list != null && !list.isEmpty()) {
            for (b.b.g.m.d.c cVar : list) {
                if (cVar.e() == permissionType && (cVar instanceof i)) {
                    return (i) cVar;
                }
            }
        }
        return null;
    }

    public static Drawable f(String str, PackageInfo packageInfo, PackageManager packageManager) {
        str.hashCode();
        switch (str) {
            case "com.byd.mycar.ts":
            case "com.byd.mycar.wc":
            case "com.byd.mycar":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mycar);
            case "com.byd.appstartmanagement":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_appstartmanagement);
            case "com.byd.filemanager":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_filemanager);
            case "com.byd.hvac":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_airconditioning);
            case "com.byd.vrassistant.xf":
            case "com.byd.vrsettings":
            case "com.byd.vrdownload.xf":
                return b.b.a.i.a.f533c == 1 ? SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice_deza) : SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice);
            case "com.byd.carsettings":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_carsettings);
            case "com.byd.auto_camera":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_camera);
            case "com.byd.auto_emanual":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_emanual);
            case "com.byd.trafficmonitor":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_trafficmonitor);
            case "com.byd.auto_photo":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_photo);
            case "com.byd.automultipletheme":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_multipletheme);
            case "com.byd.bluetoothcall":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_bluetoothcall);
            case "com.byd.avm":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_avc);
            case "com.byd.localmusic":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mediacenter);
            case "com.byd.smarttravel":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_smarttravel);
            default:
                return packageInfo.applicationInfo.loadIcon(packageManager);
        }
    }

    public static List<b.b.g.m.d.g> g(Context context, boolean z) {
        String string;
        b.b.m.c.a.a("SystemOperator", "----getAllInstalledApp---containsSystemApp = " + z);
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(4096);
        b.b.m.c.a.a("SystemOperator", "----getAllInstalledApp---packages.size = " + installedPackages.size());
        for (PackageInfo packageInfo : installedPackages) {
            boolean z2 = (packageInfo.applicationInfo.flags & 1) > 0;
            if (!f1404c.contains(packageInfo.packageName) && ((string = packageInfo.applicationInfo.loadLabel(packageManager).toString()) == null || (!string.contains("com.") && !string.contains("ts.")))) {
                if ((!packageInfo.packageName.contains("com.ts.") && !packageInfo.packageName.contains("com.android.") && !packageInfo.packageName.contains("com.core.") && !packageInfo.packageName.contains("com.google.android.") && !packageInfo.packageName.contains("android.ext.") && !packageInfo.packageName.contains("com.qualcomm.")) || packageInfo.packageName.equals("com.android.car.media")) {
                    b.b.g.m.d.g gVar = new b.b.g.m.d.g(packageInfo, f(packageInfo.packageName, packageInfo, packageManager), packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    gVar.m(packageInfo.versionName);
                    gVar.j(packageInfo.firstInstallTime);
                    gVar.k(m(context, packageInfo.packageName, packageInfo.requestedPermissions, false));
                    gVar.l(z2);
                    gVar.i(a(context, gVar.f()));
                    arrayList.add(gVar);
                }
            }
        }
        b.b.m.c.a.a("SystemOperator", "----getInstalledPermissionApps---list.size = " + arrayList.size());
        return arrayList;
    }

    @SuppressLint({"NewApi"})
    public static List<b.b.g.m.d.c> h(Context context, String str) {
        String[] strArr;
        b.b.m.c.a.a("SystemOperator", "---getAppPermissionTypes--pkgName: " + str);
        try {
            strArr = context.getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e2) {
            b.b.m.c.a.a("SystemOperator", "---getAppPermissionTypes--e: " + e2.toString());
            strArr = null;
        }
        return m(context, str, strArr, false);
    }

    public static long[] i(Context context, String str) {
        try {
            return BydStorageManager.h(context, context.getPackageManager().getPackageInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return new long[]{0, 0, 0};
        }
    }

    public static int j(Context context, String str) {
        int i;
        int i2 = 0;
        try {
            i = context.getPackageManager().getApplicationInfo(str, 0).uid;
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
        }
        try {
            b.b.m.c.a.a("SystemOperator", "packageName:" + str + ",uid:" + i);
            return i;
        } catch (PackageManager.NameNotFoundException e3) {
            e = e3;
            i2 = i;
            e.printStackTrace();
            return i2;
        }
    }

    public static BydAppInfo k(Context context, String str) {
        b.b.m.c.a.a("SystemOperator", "----getInstalledAppInfo---context = " + context);
        BydAppInfo bydAppInfo = null;
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        b.b.m.c.a.a("SystemOperator", "----getInstalledApps---packages.size = " + installedPackages.size());
        for (PackageInfo packageInfo : installedPackages) {
            if (packageInfo.packageName.equals(str)) {
                String string = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                Drawable drawableF = f(packageInfo.packageName, packageInfo, packageManager);
                BydAppInfo bydAppInfo2 = (packageInfo.applicationInfo.flags & 1) > 0 ? new BydAppInfo(packageInfo, drawableF, string, true) : new BydAppInfo(packageInfo, drawableF, string, false);
                bydAppInfo2.setVersion(packageInfo.versionName);
                bydAppInfo2.setInstallTime(packageInfo.firstInstallTime);
                long[] jArrH = BydStorageManager.h(context, packageInfo);
                bydAppInfo2.setCodeBytes(jArrH[0]);
                bydAppInfo2.setCacheBytes(jArrH[1]);
                bydAppInfo2.setDataBytes(jArrH[2]);
                bydAppInfo2.setDisable(packageManager.getApplicationEnabledSetting(packageInfo.packageName) == 2);
                bydAppInfo = bydAppInfo2;
            }
        }
        return bydAppInfo;
    }

    public static List<BydAppInfo> l(Context context, boolean z) {
        String string;
        b.b.m.c.a.a("SystemOperator", "----getInstalledApps---context = " + context);
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        b.b.m.c.a.a("SystemOperator", "----getInstalledApps---packages.size = " + installedPackages.size());
        for (PackageInfo packageInfo : installedPackages) {
            boolean z2 = (packageInfo.applicationInfo.flags & 1) > 0;
            if (!f1404c.contains(packageInfo.packageName) && ((string = packageInfo.applicationInfo.loadLabel(packageManager).toString()) == null || (!string.contains("com.") && !string.contains("ts.")))) {
                if ((!packageInfo.packageName.contains("com.ts.") && !packageInfo.packageName.contains("com.android.") && !packageInfo.packageName.contains("com.core.") && !packageInfo.packageName.contains("com.google.android.") && !packageInfo.packageName.contains("android.ext.") && !packageInfo.packageName.contains("com.qualcomm.")) || packageInfo.packageName.equals("com.android.car.media")) {
                    b.b.m.c.a.a("SystemOperator", "----packageName----" + packageInfo.packageName);
                    BydAppInfo bydAppInfo = new BydAppInfo(packageInfo, f(packageInfo.packageName, packageInfo, packageManager), string, false);
                    bydAppInfo.setVersion(packageInfo.versionName);
                    bydAppInfo.setInstallTime(packageInfo.firstInstallTime);
                    long[] jArrH = BydStorageManager.h(context, packageInfo);
                    bydAppInfo.setCodeBytes(jArrH[0]);
                    bydAppInfo.setCacheBytes(jArrH[1]);
                    bydAppInfo.setDataBytes(jArrH[2]);
                    bydAppInfo.setSystemApp(z2);
                    bydAppInfo.setDisable(packageManager.getApplicationEnabledSetting(packageInfo.packageName) == 2);
                    arrayList.add(bydAppInfo);
                }
            }
        }
        b.b.m.c.a.a("SystemOperator", "----getInstalledApps---list.size = " + arrayList.size());
        return arrayList;
    }

    public static List<b.b.g.m.d.c> m(Context context, String str, String[] strArr, boolean z) {
        if (f1405d.contains(str) || strArr == null || strArr.length <= 0) {
            return null;
        }
        HashMap map = new HashMap();
        for (String str2 : strArr) {
            PermissionType permissionTypeP = p(str2);
            if (permissionTypeP != null && permissionTypeP != PermissionType.OTHER) {
                List arrayList = (List) map.get(permissionTypeP);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    map.put(permissionTypeP, arrayList);
                }
                arrayList.add(str2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (PermissionType permissionType : PermissionType.values()) {
            List list = (List) map.get(permissionType);
            if (list != null) {
                b.b.g.m.d.c iVar = z ? new i(permissionType, str, o(context, permissionType), list) : new b.b.g.m.d.c(permissionType, str, o(context, permissionType), list);
                iVar.j(o(context, permissionType));
                arrayList2.add(iVar);
            }
        }
        b.b.m.c.a.a("SystemOperator", "pkgName:" + str + "---getAppPermissionList--list.size: " + arrayList2.size());
        return arrayList2;
    }

    public static List<b.b.g.m.d.d<i>> n(Context context, boolean z) {
        String string;
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(4096);
        int i = 1;
        b.b.m.c.a.a("SystemOperator", "----getPermissionInfoList---packages.size = " + installedPackages.size());
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : installedPackages) {
            boolean z2 = (packageInfo.applicationInfo.flags & 1) > 0;
            if (!f1404c.contains(packageInfo.packageName) && ((string = packageInfo.applicationInfo.loadLabel(packageManager).toString()) == null || (!string.contains("com.") && !string.contains("ts.")))) {
                if ((!packageInfo.packageName.contains("com.ts.") && !packageInfo.packageName.contains("com.android.") && !packageInfo.packageName.contains("com.core.") && !packageInfo.packageName.contains("com.google.android.") && !packageInfo.packageName.contains("android.ext.") && !packageInfo.packageName.contains("com.qualcomm.")) || packageInfo.packageName.equals("com.android.car.media")) {
                    b.b.g.m.d.g gVar = new b.b.g.m.d.g(packageInfo, f(packageInfo.packageName, packageInfo, packageManager), packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    String[] strArr = packageInfo.requestedPermissions;
                    b.b.m.c.a.a("SystemOperator", "----getPermissionInfoList---arr: " + strArr);
                    List<b.b.g.m.d.c> listM = m(context, packageInfo.packageName, strArr, true);
                    if (listM != null && !listM.isEmpty()) {
                        for (b.b.g.m.d.c cVar : listM) {
                            if (cVar instanceof i) {
                                i iVar = (i) cVar;
                                iVar.o(f(packageInfo.packageName, packageInfo, packageManager));
                                iVar.n(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                                iVar.p(z2);
                            }
                        }
                    }
                    gVar.k(listM);
                    arrayList.add(gVar);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        PermissionType[] permissionTypeArrValues = PermissionType.values();
        int length = permissionTypeArrValues.length;
        int i2 = 0;
        while (i2 < length) {
            PermissionType permissionType = permissionTypeArrValues[i2];
            String[] strArr2 = new String[i];
            strArr2[0] = "----getPermissionInfoList---t : " + permissionType;
            b.b.m.c.a.a("SystemOperator", strArr2);
            b.b.g.m.d.d dVar = new b.b.g.m.d.d(permissionType, o(context, permissionType));
            if (permissionType == PermissionType.CAMERA) {
                dVar.g = i;
            } else if (permissionType == PermissionType.RECORD_AUDIO) {
                dVar.g = 2;
            } else if (permissionType == PermissionType.LOCATION) {
                dVar.g = 3;
            }
            String[] strArr3 = new String[i];
            strArr3[0] = "----getPermissionInfoList---info = " + dVar;
            b.b.m.c.a.a("SystemOperator", strArr3);
            ArrayList arrayList3 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i iVarE = e(((b.b.g.m.d.g) it.next()).f(), permissionType);
                String[] strArr4 = new String[i];
                strArr4[0] = "----getPermissionInfoList---spa = " + iVarE;
                b.b.m.c.a.a("SystemOperator", strArr4);
                if (iVarE != null) {
                    arrayList3.add(iVarE);
                }
                i = 1;
            }
            if (!arrayList3.isEmpty()) {
                dVar.g(arrayList3);
                arrayList2.add(dVar);
            }
            i2++;
            i = 1;
        }
        return arrayList2;
    }

    public static String o(Context context, PermissionType permissionType) {
        if (permissionType == null) {
            return "";
        }
        String string = context.getResources().getString(R.string.other_app_manage);
        switch (b.f1406a[permissionType.ordinal()]) {
            case 1:
                return context.getResources().getString(R.string.storage_permission_app_details);
            case 2:
                return context.getResources().getString(R.string.phone_permission_app_details);
            case 3:
                return context.getResources().getString(R.string.contacts_permission_app_details);
            case 4:
                return context.getResources().getString(R.string.location_permission_app_details);
            case 5:
                return context.getResources().getString(R.string.camera_permission_app_details);
            case 6:
                return context.getResources().getString(R.string.mic_permission_app_details);
            case 7:
                return context.getResources().getString(R.string.car_info_permission_app_details);
            case 8:
                return context.getResources().getString(R.string.meter_permission_app_details);
            case 9:
                return context.getResources().getString(R.string.usage_access_permission_app_details);
            case 10:
                return context.getResources().getString(R.string.write_settings_permission_app_details);
            case 11:
                return context.getResources().getString(R.string.charge_info_permission_app_details);
            case 12:
                return context.getResources().getString(R.string.panorama_permission_app_details);
            case 13:
                return context.getResources().getString(R.string.engine_info_permission_app_details);
            case 14:
                return context.getResources().getString(R.string.transmission_info_permission_app_details);
            case 15:
                return context.getResources().getString(R.string.top_then_other_app_permission_app_details);
            case 16:
                return context.getResources().getString(R.string.safety_belt_permission_app_details);
            case 17:
                return context.getResources().getString(R.string.calendar_permission_app_details);
            case 18:
                return context.getResources().getString(R.string.time_info_permission_app_details);
            case 19:
                return context.getResources().getString(R.string.sms_permission_app_details);
            case 20:
                return context.getResources().getString(R.string.ac_info_permission_app_details);
            case 21:
                return context.getResources().getString(R.string.oil_power_info_permission_app_details);
            case 22:
                return context.getResources().getString(R.string.body_sensor_permission_app_details);
            case 23:
                return context.getResources().getString(R.string.car_body_sensor_permission_app_details);
            case 24:
                return context.getResources().getString(R.string.car_lighting_permission_app_details);
            case 25:
                return context.getResources().getString(R.string.car_settings_permission_app_details);
            case 26:
                return context.getResources().getString(R.string.car_speed_permission_app_details);
            case 27:
                return context.getResources().getString(R.string.door_lock_permission_app_details);
            case 28:
                return context.getResources().getString(R.string.statistic_permission_app_details);
            case 29:
                return context.getResources().getString(R.string.yun_permission_app_details);
            default:
                return string;
        }
    }

    public static PermissionType p(String str) {
        return str == null ? PermissionType.OTHER : (str.contains("WRITE_EXTERNAL_STORAGE") || str.contains("READ_EXTERNAL_STORAGE")) ? PermissionType.STORAGE : (str.contains("CALL_PHONE") || str.contains("READ_CALL_LOG") || str.contains("READ_PHONE_STATE")) ? PermissionType.PHONE : (str.contains("READ_CONTACTS") || str.contains("WRITE_CONTACTS")) ? PermissionType.CONTACTS : (str.contains("ACCESS_COARSE_LOCATION") || str.contains("ACCESS_FINE_LOCATION")) ? PermissionType.LOCATION : str.contains("CAMERA") ? PermissionType.CAMERA : str.contains("RECORD_AUDIO") ? PermissionType.RECORD_AUDIO : str.contains("WRITE_SETTINGS") ? PermissionType.WRITE_SETTINGS : str.contains("BYDAUTO_AC_") ? PermissionType.AC_INFO : str.contains("BYDAUTO_SETTING_") ? PermissionType.CAR_SETTINGS : str.contains("BYDAUTO_INSTRUMENT_") ? PermissionType.METER : str.contains("BYDAUTO_DOOR_LOCK_") ? PermissionType.DOOR_LOCK : str.contains("BYDAUTO_LIGHT_") ? PermissionType.CAR_LIGHTING : str.contains("BYDAUTO_SPEED_") ? PermissionType.SPEED : str.contains("BYDAUTO_SAFETY_BELT_") ? PermissionType.SAFETY_BELT : str.contains("BYDAUTO_PANORAMA_") ? PermissionType.PANORAMIC_CAMERA : str.contains("BYDAUTO_GEARBOX_") ? PermissionType.TRANSMISSION_INFO : str.contains("BYDAUTO_ENGINE_") ? PermissionType.ENGINE_INFO : str.contains("WRITE_CARSETTINGS_") ? PermissionType.CAR_SETTINGS : str.contains("BYDAUTO_BODYWORK_") ? PermissionType.CAR_INFO : str.contains("SYSTEM_ALERT_WINDOW") ? PermissionType.SYSTEM_ALERT_WINDOW : str.contains("USAGE_ACCESS") ? PermissionType.USAGE_ACCESS : str.contains("BYDAUTO_CHARGING_") ? PermissionType.CHARGE_INFO : (str.contains("READ_CALENDAR") || str.contains("WRITE_CALENDAR")) ? PermissionType.CALENDAR : (str.contains("SET_TIME") || str.contains("SET_TIME_ZONE")) ? PermissionType.TIME : (str.contains("READ_SMS") || str.contains("WRITE_SMS") || str.contains("RECEIVE_SMS") || str.contains("SEND_SMS")) ? PermissionType.SMS : str.contains("UTO_CHARGING_") ? PermissionType.OIL_POWER_INFO : str.contains("BODY_SENSORS") ? PermissionType.BODY_SENSOR : str.contains("BYDAUTO_STATISTIC_") ? PermissionType.STATISTIC : str.contains("BYDAUTO_YUN_") ? PermissionType.YUN : PermissionType.OTHER;
    }

    public static boolean q(Context context, String str, String str2) {
        int i;
        PackageManager packageManager = context.getPackageManager();
        try {
            i = packageManager.getApplicationInfo(str, 0).uid;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            i = 0;
        }
        return (packageManager.getPermissionFlags(str2, str, UserHandle.getUserHandleForUid(i)) & 16) != 0;
    }

    public static boolean r(Context context) {
        int i = Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0);
        b.b.m.c.a.a("SystemOperator", "---isUserSetupCompleted---value: " + i);
        return i != 0;
    }

    public static void s(String str, PermissionType permissionType, List<String> list) {
        if ("com.neusoft.na.navigation".equals(str) || "com.huawei.maps.auto.app".equals(str) || "com.byd.automap".equals(str) || "net.zmap.znav.byd8155".equals(str) || "com.mappls.auto.bydznav8155F".equals(str)) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (permissionType != PermissionType.METER) {
                    if (permissionType != PermissionType.CAR_INFO) {
                        if (permissionType != PermissionType.STATISTIC) {
                            if (permissionType != PermissionType.YUN) {
                                if (permissionType == PermissionType.TRANSMISSION_INFO && "BYDAUTO_GEARBOX_COMMON".contains(next)) {
                                    arrayList.add(next);
                                    break;
                                }
                            } else if ("BYDAUTO_YUN_COMMON".contains(next)) {
                                arrayList.add(next);
                                break;
                            }
                        } else if ("BYDAUTO_STATISTIC_COMMON".contains(next)) {
                            arrayList.add(next);
                            break;
                        }
                    } else if ("BYDAUTO_BODYWORK_COMMON".contains(next)) {
                        arrayList.add(next);
                        break;
                    }
                } else if ("BYDAUTO_INSTRUMENT_COMMON".contains(next)) {
                    arrayList.add(next);
                    break;
                }
            }
            if (arrayList.size() != 0) {
                list.clear();
                list.addAll(arrayList);
            }
        }
    }

    public static void t(Context context) {
        u(context);
    }

    public static void u(Context context) {
        if (context == null) {
            return;
        }
        if (ActivityManager.isUserAMonkey()) {
            b.b.a.f.h.a().d(new a());
            b.b.m.c.a.c("SystemOperator", "---restoreFactorySettingsByBroadcast----Monkey is running.");
            return;
        }
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("android.intent.extra.REASON", "MasterClearConfirm");
        intent.putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", false);
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", true);
        context.sendBroadcast(intent);
        b.b.m.c.a.g("SystemOperator", "---restoreFactorySettings----excute completed.");
    }

    @SuppressLint({"NewApi"})
    public static boolean v(Context context, String str, PermissionType permissionType, List<String> list, boolean z) {
        UserHandle userHandleForUid;
        b.b.m.c.a.a("SystemOperator", "---setPermissionEnable--pkgName: " + str + ", enable: " + z);
        s(str, permissionType, list);
        ApplicationPackageManager packageManager = context.getPackageManager();
        PermissionInfo permissionInfo = null;
        try {
            userHandleForUid = UserHandle.getUserHandleForUid(packageManager.getPackageInfo(str, 0).applicationInfo.uid);
        } catch (PackageManager.NameNotFoundException e2) {
            b.b.m.c.a.c("SystemOperator", "---setPermissionEnable--0--e: " + e2.toString());
            userHandleForUid = null;
        }
        if (userHandleForUid == null || list == null || list.isEmpty()) {
            return false;
        }
        int i = 0;
        for (String str2 : list) {
            try {
                permissionInfo = packageManager.getPermissionInfo(str2, 128);
            } catch (PackageManager.NameNotFoundException e3) {
                b.b.m.c.a.c("SystemOperator", "---setPermissionEnable--1--e: " + e3.toString());
            }
            if (permissionInfo == null) {
                b.b.m.c.a.a("SystemOperator", "---setPermissionEnable continue ---pi: " + permissionInfo);
            } else {
                b.b.m.c.a.a("SystemOperator", "---setPermissionEnable---p: " + str2 + ",enable:" + z);
                if (z) {
                    try {
                        packageManager.grantRuntimePermission(str, str2, userHandleForUid);
                        b.b.m.c.a.a("SystemOperator", "---grantRuntimePermission---");
                    } catch (Exception e4) {
                        b.b.m.c.a.c("SystemOperator", "---setPermissionEnable: " + e4.toString());
                    }
                } else {
                    packageManager.revokeRuntimePermission(str, str2, userHandleForUid);
                    b.b.m.c.a.a("SystemOperator", "---revokeRuntimePermission---");
                }
                i++;
            }
        }
        return i >= list.size();
    }
}
