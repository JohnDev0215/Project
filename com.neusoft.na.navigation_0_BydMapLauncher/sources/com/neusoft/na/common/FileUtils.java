package com.neusoft.na.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.bydux.api.common.tools.util.ListUtils;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class FileUtils {
    public static final String DEFAULT_VERSION = "0";
    private static final int FAILED = 0;
    public static final String KEY_PERMISSION = "privacy";
    public static final String KEY_VERSION = "version";
    public static final String KEY_VERSION_NEW = "version_new";
    private static final String NAME = "xj_privacy";
    private static final int SUCCESS = 1;
    private static final String TAG = "FileUtils";
    private static FileUtils instance;
    private FileOperateCallback callback;
    private Context context;
    private String errorStr;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.neusoft.na.common.FileUtils.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (FileUtils.this.callback != null) {
                if (message.what == 1) {
                    FileUtils.this.callback.onSuccess();
                }
                if (message.what == 0) {
                    FileUtils.this.callback.onFailed(message.obj.toString());
                }
            }
        }
    };
    private volatile boolean isSuccess;

    public interface FileOperateCallback {
        void onFailed(String str);

        void onSuccess();
    }

    private boolean is2Path() {
        return false;
    }

    public static FileUtils getInstance(Context context) {
        if (instance == null) {
            instance = new FileUtils(context);
        }
        return instance;
    }

    private FileUtils(Context context) {
        this.context = context;
    }

    public FileUtils copyAssetsToSD(final String str, final String str2) {
        new Thread(new Runnable() { // from class: com.neusoft.na.common.FileUtils.2
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                FileUtils fileUtils = FileUtils.this;
                fileUtils.copyAssetsToDst(fileUtils.context, str, str2);
                if (FileUtils.this.isSuccess) {
                    FileUtils.this.handler.obtainMessage(1).sendToTarget();
                } else {
                    FileUtils.this.handler.obtainMessage(0, FileUtils.this.errorStr).sendToTarget();
                }
            }
        }).start();
        return this;
    }

    public FileUtils copyAssetsToPath(final String str, final String str2) {
        new Thread(new Runnable() { // from class: com.neusoft.na.common.FileUtils.3
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                FileUtils fileUtils = FileUtils.this;
                fileUtils.copyAssetsToAny(fileUtils.context, str, str2);
                if (FileUtils.this.isSuccess) {
                    FileUtils.this.handler.obtainMessage(1).sendToTarget();
                } else {
                    FileUtils.this.handler.obtainMessage(0, FileUtils.this.errorStr).sendToTarget();
                }
            }
        }).start();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c2 A[Catch: Exception -> 0x00cb, TryCatch #1 {Exception -> 0x00cb, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x0017, B:8:0x001a, B:10:0x001e, B:12:0x0028, B:14:0x0074, B:13:0x005a, B:39:0x00bb, B:15:0x0077, B:25:0x009e, B:26:0x00a1, B:36:0x00b5, B:42:0x00c2, B:44:0x00c7, B:45:0x00ca), top: B:51:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c7 A[Catch: Exception -> 0x00cb, TryCatch #1 {Exception -> 0x00cb, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x0017, B:8:0x001a, B:10:0x001e, B:12:0x0028, B:14:0x0074, B:13:0x005a, B:39:0x00bb, B:15:0x0077, B:25:0x009e, B:26:0x00a1, B:36:0x00b5, B:42:0x00c2, B:44:0x00c7, B:45:0x00ca), top: B:51:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void copyAssetsToAny(android.content.Context r10, java.lang.String r11, java.lang.String r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.common.FileUtils.copyAssetsToAny(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public void setFileOperateCallback(FileOperateCallback fileOperateCallback) {
        this.callback = fileOperateCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d1 A[Catch: Exception -> 0x00da, TryCatch #1 {Exception -> 0x00da, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x001b, B:8:0x001e, B:10:0x0022, B:12:0x002c, B:14:0x0078, B:13:0x005e, B:39:0x00ca, B:15:0x007b, B:25:0x00ad, B:26:0x00b0, B:36:0x00c4, B:42:0x00d1, B:44:0x00d6, B:45:0x00d9), top: B:49:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d6 A[Catch: Exception -> 0x00da, TryCatch #1 {Exception -> 0x00da, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x001b, B:8:0x001e, B:10:0x0022, B:12:0x002c, B:14:0x0078, B:13:0x005e, B:39:0x00ca, B:15:0x007b, B:25:0x00ad, B:26:0x00b0, B:36:0x00c4, B:42:0x00d1, B:44:0x00d6, B:45:0x00d9), top: B:49:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void copyAssetsToDst(android.content.Context r10, java.lang.String r11, java.lang.String r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.common.FileUtils.copyAssetsToDst(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0131 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void copyFolder(java.lang.String r12, java.lang.String r13) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.common.FileUtils.copyFolder(java.lang.String, java.lang.String):void");
    }

    public static boolean deleteDirectory(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        boolean zDeleteDirectory = true;
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].isFile()) {
                zDeleteDirectory = Tool.deleteFile(fileArrListFiles[i].getAbsolutePath());
                if (!zDeleteDirectory) {
                    break;
                }
            } else {
                if (fileArrListFiles[i].isDirectory() && !(zDeleteDirectory = deleteDirectory(fileArrListFiles[i].getAbsolutePath()))) {
                    break;
                }
            }
        }
        return zDeleteDirectory;
    }

    public String getDbuIniPath() {
        return (!is2Path() || isFileExists()) ? "ocrsc/etc/mots/na_dbu.ini" : "ocrsc/etc/mots/na_dbu_sdcard.ini";
    }

    public String getOneCoreIniPath() {
        return (!is2Path() || isFileExists()) ? "ocrsc/etc/mots/na_onecore.ini" : "ocrsc/etc/mots/na_onecore_sdcard.ini";
    }

    public String getAreaPath() {
        return is2Path() ? getDbPath() + "db/Area.txt" : "";
    }

    public String getDbPath() {
        return is2Path() ? isFileExists() ? "/navi/neusoft/" : "/sdcard/Android/data/neusoft/" : "";
    }

    private boolean isFileExists() {
        return new File("/navi/BYDAssistant_CR").exists();
    }

    public boolean isAgreePrivacy() {
        SharedPreferences sharedPreferences = this.context.createDeviceProtectedStorageContext().getSharedPreferences(NAME, 0);
        boolean z = sharedPreferences.getBoolean(KEY_PERMISSION, false);
        String string = sharedPreferences.getString("version", DEFAULT_VERSION);
        String str = "9.0";
        String string2 = sharedPreferences.getString(KEY_VERSION_NEW, "9.0");
        if (Float.parseFloat(string2) < Float.parseFloat("9.0")) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(KEY_VERSION_NEW, "9.0");
            editorEdit.commit();
        } else {
            str = string2;
        }
        Log.i(TAG, "isAgreePrivacy: " + z + ListUtils.DEFAULT_JOIN_SEPARATOR + string);
        return z && string.equals(str);
    }

    public boolean isAgree() {
        boolean z = this.context.createDeviceProtectedStorageContext().getSharedPreferences(NAME, 0).getBoolean(KEY_PERMISSION, false);
        Log.i(TAG, "isAgree: " + z);
        return z;
    }

    public boolean isNewPrivacy() {
        SharedPreferences sharedPreferences = this.context.createDeviceProtectedStorageContext().getSharedPreferences(NAME, 0);
        Log.i(TAG, "isNewPrivacy: " + sharedPreferences.getString("version", DEFAULT_VERSION) + ListUtils.DEFAULT_JOIN_SEPARATOR + sharedPreferences.getString(KEY_VERSION_NEW, "9.0"));
        return !r1.equals(r0);
    }

    public void updatePrivacyVersion(String str) {
        Log.i(TAG, "updatePrivacyVersion " + str);
        SharedPreferences.Editor editorEdit = this.context.createDeviceProtectedStorageContext().getSharedPreferences(NAME, 0).edit();
        editorEdit.putString(KEY_VERSION_NEW, str);
        editorEdit.commit();
    }

    public void updatePrivacyStatus(boolean z) {
        Log.i(TAG, "updatePrivacyStatus " + z);
        SharedPreferences sharedPreferences = this.context.createDeviceProtectedStorageContext().getSharedPreferences(NAME, 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean(KEY_PERMISSION, z);
        editorEdit.putString("version", sharedPreferences.getString(KEY_VERSION_NEW, "9.0"));
        editorEdit.commit();
    }

    public boolean isBydHasAll() {
        return isAgreePrivacy() && isHasPermissionByd();
    }

    public boolean isHasPermissionByd() {
        ArrayList arrayList = new ArrayList();
        if (this.context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if (Build.VERSION.SDK_INT < 30) {
            if (this.context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
            }
            if (this.context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
        }
        if (this.context.checkSelfPermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON) != 0) {
            arrayList.add(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        }
        if (this.context.checkSelfPermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
            arrayList.add(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        }
        if (this.context.checkSelfPermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON) != 0) {
            arrayList.add(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        }
        if (this.context.checkSelfPermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON) != 0) {
            arrayList.add(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        }
        if (this.context.checkSelfPermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON) != 0) {
            arrayList.add(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        }
        if (this.context.checkSelfPermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON) != 0) {
            arrayList.add(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
        }
        Log.i(TAG, "isHasPermissionByd: " + Build.VERSION.SDK_INT + ListUtils.DEFAULT_JOIN_SEPARATOR + arrayList.size());
        return arrayList.size() == 0;
    }
}
