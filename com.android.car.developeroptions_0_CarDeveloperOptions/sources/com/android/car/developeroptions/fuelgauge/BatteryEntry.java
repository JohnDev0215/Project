package com.android.car.developeroptions.fuelgauge;

import android.app.AppGlobals;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.BatteryStats;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.internal.os.BatterySipper;
import com.android.settingslib.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class BatteryEntry {
    private static NameAndIconLoader mRequestThread;
    static Handler sHandler;
    public final Context context;
    public String defaultPackageName;
    public Drawable icon;
    public int iconId;
    public String name;
    public final BatterySipper sipper;
    static final HashMap<String, UidToDetail> sUidCache = new HashMap<>();
    static final ArrayList<BatteryEntry> mRequestQueue = new ArrayList<>();
    static Locale sCurrentLocale = null;

    private static class NameAndIconLoader extends Thread {
        private boolean mAbort;

        public NameAndIconLoader() {
            super("BatteryUsage Icon Loader");
            this.mAbort = false;
        }

        public void abort() {
            this.mAbort = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BatteryEntry batteryEntryRemove;
            while (true) {
                synchronized (BatteryEntry.mRequestQueue) {
                    if (BatteryEntry.mRequestQueue.isEmpty() || this.mAbort) {
                        break;
                    } else {
                        batteryEntryRemove = BatteryEntry.mRequestQueue.remove(0);
                    }
                }
                batteryEntryRemove.loadNameAndIcon();
            }
            if (BatteryEntry.sHandler != null) {
                BatteryEntry.sHandler.sendEmptyMessage(2);
            }
            BatteryEntry.mRequestQueue.clear();
        }
    }

    public static void startRequestQueue() {
        if (sHandler != null) {
            synchronized (mRequestQueue) {
                if (!mRequestQueue.isEmpty()) {
                    if (mRequestThread != null) {
                        mRequestThread.abort();
                    }
                    NameAndIconLoader nameAndIconLoader = new NameAndIconLoader();
                    mRequestThread = nameAndIconLoader;
                    nameAndIconLoader.setPriority(1);
                    mRequestThread.start();
                    mRequestQueue.notify();
                }
            }
        }
    }

    public static void stopRequestQueue() {
        synchronized (mRequestQueue) {
            if (mRequestThread != null) {
                mRequestThread.abort();
                mRequestThread = null;
                sHandler = null;
            }
        }
    }

    public static void clearUidCache() {
        sUidCache.clear();
    }

    static class UidToDetail {
        Drawable icon;
        String name;
        String packageName;

        UidToDetail() {
        }
    }

    public BatteryEntry(Context context, Handler handler, UserManager userManager, BatterySipper batterySipper) {
        BatteryStats.Uid uid;
        sHandler = handler;
        this.context = context;
        this.sipper = batterySipper;
        switch (AnonymousClass1.$SwitchMap$com$android$internal$os$BatterySipper$DrainType[batterySipper.drainType.ordinal()]) {
            case 1:
                this.name = context.getResources().getString(R.string.power_idle);
                this.iconId = R.drawable.ic_settings_phone_idle;
                break;
            case 2:
                this.name = context.getResources().getString(R.string.power_cell);
                this.iconId = R.drawable.ic_cellular_1_bar;
                break;
            case 3:
                this.name = context.getResources().getString(R.string.power_phone);
                this.iconId = R.drawable.ic_settings_voice_calls;
                break;
            case 4:
                this.name = context.getResources().getString(R.string.power_wifi);
                this.iconId = R.drawable.ic_settings_wireless;
                break;
            case 5:
                this.name = context.getResources().getString(R.string.power_bluetooth);
                this.iconId = android.R.drawable.ic_media_route_holo_dark;
                break;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                this.name = context.getResources().getString(R.string.power_screen);
                this.iconId = R.drawable.ic_settings_display;
                break;
            case 7:
                this.name = context.getResources().getString(R.string.power_flashlight);
                this.iconId = R.drawable.ic_settings_display;
                break;
            case 8:
                PackageManager packageManager = context.getPackageManager();
                String[] packagesForUid = packageManager.getPackagesForUid(batterySipper.uidObj.getUid());
                batterySipper.mPackages = packagesForUid;
                if (packagesForUid == null || packagesForUid.length != 1) {
                    this.name = batterySipper.packageWithHighestDrain;
                } else {
                    String str = packageManager.getPackagesForUid(batterySipper.uidObj.getUid())[0];
                    this.defaultPackageName = str;
                    try {
                        this.name = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString();
                    } catch (PackageManager.NameNotFoundException unused) {
                        Log.d("BatteryEntry", "PackageManager failed to retrieve ApplicationInfo for: " + this.defaultPackageName);
                        this.name = this.defaultPackageName;
                    }
                }
                break;
            case 9:
                UserInfo userInfo = userManager.getUserInfo(batterySipper.userId);
                if (userInfo != null) {
                    this.icon = Utils.getUserIcon(context, userManager, userInfo);
                    this.name = Utils.getUserLabel(context, userInfo);
                } else {
                    this.icon = null;
                    this.name = context.getResources().getString(R.string.running_process_item_removed_user_label);
                }
                break;
            case 10:
                this.name = context.getResources().getString(R.string.power_unaccounted);
                this.iconId = R.drawable.ic_android;
                break;
            case 11:
                this.name = context.getResources().getString(R.string.power_overcounted);
                this.iconId = R.drawable.ic_android;
                break;
            case 12:
                this.name = context.getResources().getString(R.string.power_camera);
                this.iconId = R.drawable.ic_settings_camera;
                break;
            case 13:
                this.name = context.getResources().getString(R.string.ambient_display_screen_title);
                this.iconId = R.drawable.ic_settings_aod;
                break;
        }
        int i = this.iconId;
        if (i > 0) {
            this.icon = context.getDrawable(i);
        }
        if ((this.name == null || this.iconId == 0) && (uid = this.sipper.uidObj) != null) {
            getQuickNameIconForUid(uid.getUid());
        }
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.fuelgauge.BatteryEntry$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$os$BatterySipper$DrainType;

        static {
            int[] iArr = new int[BatterySipper.DrainType.values().length];
            $SwitchMap$com$android$internal$os$BatterySipper$DrainType = iArr;
            try {
                iArr[BatterySipper.DrainType.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.CELL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.WIFI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.BLUETOOTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.SCREEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.FLASHLIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.APP.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.USER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.UNACCOUNTED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.OVERCOUNTED.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.CAMERA.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$android$internal$os$BatterySipper$DrainType[BatterySipper.DrainType.AMBIENT_DISPLAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public String getLabel() {
        return this.name;
    }

    void getQuickNameIconForUid(int i) {
        Locale locale = Locale.getDefault();
        if (sCurrentLocale != locale) {
            clearUidCache();
            sCurrentLocale = locale;
        }
        String string = Integer.toString(i);
        if (sUidCache.containsKey(string)) {
            UidToDetail uidToDetail = sUidCache.get(string);
            this.defaultPackageName = uidToDetail.packageName;
            this.name = uidToDetail.name;
            this.icon = uidToDetail.icon;
            return;
        }
        PackageManager packageManager = this.context.getPackageManager();
        this.icon = packageManager.getDefaultActivityIcon();
        if (packageManager.getPackagesForUid(i) == null) {
            if (i == 0) {
                this.name = this.context.getResources().getString(R.string.process_kernel_label);
            } else if ("mediaserver".equals(this.name)) {
                this.name = this.context.getResources().getString(R.string.process_mediaserver_label);
            } else if ("dex2oat".equals(this.name)) {
                this.name = this.context.getResources().getString(R.string.process_dex2oat_label);
            }
            this.iconId = R.drawable.ic_power_system;
            this.icon = this.context.getDrawable(R.drawable.ic_power_system);
        }
        if (sHandler != null) {
            synchronized (mRequestQueue) {
                mRequestQueue.add(this);
            }
        }
    }

    public void loadNameAndIcon() {
        CharSequence text;
        if (this.sipper.uidObj == null) {
            return;
        }
        PackageManager packageManager = this.context.getPackageManager();
        int uid = this.sipper.uidObj.getUid();
        BatterySipper batterySipper = this.sipper;
        if (batterySipper.mPackages == null) {
            batterySipper.mPackages = packageManager.getPackagesForUid(uid);
        }
        String[] strArrExtractPackagesFromSipper = extractPackagesFromSipper(this.sipper);
        if (strArrExtractPackagesFromSipper != null) {
            int length = strArrExtractPackagesFromSipper.length;
            String[] strArr = new String[length];
            System.arraycopy(strArrExtractPackagesFromSipper, 0, strArr, 0, strArrExtractPackagesFromSipper.length);
            IPackageManager packageManager2 = AppGlobals.getPackageManager();
            int userId = UserHandle.getUserId(uid);
            for (int i = 0; i < length; i++) {
                try {
                    ApplicationInfo applicationInfo = packageManager2.getApplicationInfo(strArr[i], 0, userId);
                    if (applicationInfo == null) {
                        Log.d("BatteryEntry", "Retrieving null app info for package " + strArr[i] + ", user " + userId);
                    } else {
                        CharSequence charSequenceLoadLabel = applicationInfo.loadLabel(packageManager);
                        if (charSequenceLoadLabel != null) {
                            strArr[i] = charSequenceLoadLabel.toString();
                        }
                        if (applicationInfo.icon != 0) {
                            this.defaultPackageName = strArrExtractPackagesFromSipper[i];
                            this.icon = applicationInfo.loadIcon(packageManager);
                            break;
                        }
                        continue;
                    }
                } catch (RemoteException e) {
                    Log.d("BatteryEntry", "Error while retrieving app info for package " + strArr[i] + ", user " + userId, e);
                }
            }
            if (length == 1) {
                this.name = strArr[0];
            } else {
                for (String str : strArrExtractPackagesFromSipper) {
                    try {
                        PackageInfo packageInfo = packageManager2.getPackageInfo(str, 0, userId);
                        if (packageInfo != null) {
                            if (packageInfo.sharedUserLabel != 0 && (text = packageManager.getText(str, packageInfo.sharedUserLabel, packageInfo.applicationInfo)) != null) {
                                this.name = text.toString();
                                if (packageInfo.applicationInfo.icon == 0) {
                                    break;
                                }
                                this.defaultPackageName = str;
                                this.icon = packageInfo.applicationInfo.loadIcon(packageManager);
                                break;
                            }
                        } else {
                            Log.d("BatteryEntry", "Retrieving null package info for package " + str + ", user " + userId);
                        }
                    } catch (RemoteException e2) {
                        Log.d("BatteryEntry", "Error while retrieving package info for package " + str + ", user " + userId, e2);
                    }
                }
            }
        }
        String string = Integer.toString(uid);
        if (this.name == null) {
            this.name = string;
        }
        if (this.icon == null) {
            this.icon = packageManager.getDefaultActivityIcon();
        }
        UidToDetail uidToDetail = new UidToDetail();
        uidToDetail.name = this.name;
        uidToDetail.icon = this.icon;
        uidToDetail.packageName = this.defaultPackageName;
        sUidCache.put(string, uidToDetail);
        Handler handler = sHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(1, this));
        }
    }

    String[] extractPackagesFromSipper(BatterySipper batterySipper) {
        if (batterySipper.getUid() == 1000) {
            return new String[]{"android"};
        }
        return batterySipper.mPackages;
    }
}
