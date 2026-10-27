package com.byd.music.utils.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.os.storage.VolumeInfo;
import com.byd.music.utils.log.SdkSharePreference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LogControlerUtil {
    public static final int LOG_CLOSE = -1;
    private static boolean LOG_COPY_TEST = false;
    public static final String LOG_DOCUMENT = "log_document";
    public static final int LOG_H = 1;
    public static final int LOG_HP = 2;
    public static final int LOG_L = 0;
    public static final String LOG_LEVEL = "log_level";
    public static final int LOG_OPEN = 0;
    public static final String LOG_OPEN_TIME = "log_open_time";
    public static final String LOG_STATUS = "log_mode";
    private static LogControlerUtil ourInstance;
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences preference;
    private final String TAG = LogControlerUtil.class.getSimpleName();
    private int logStatus = -1;
    private int logLevel = 0;

    public static synchronized LogControlerUtil getInstance() {
        if (ourInstance == null) {
            ourInstance = new LogControlerUtil();
        }
        return ourInstance;
    }

    public void init(Context context) {
        this.context = context;
        getSharedPreferences(context);
        init();
        setTestLogLevelForRelease();
        String str = getLogLevel() == 0 ? "低频" : "";
        if (getLogLevel() == 1) {
            str = "高频";
        }
        if (getLogLevel() == 2) {
            str = "超高频";
        }
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("当前日志状态： ");
        sb.append(getLogStatus() == -1 ? "关闭" : "打开, ");
        sb.append("级别: ");
        sb.append(str);
        Logger.e(str2, sb.toString(), null, new Object[0]);
    }

    private void setTestLogLevelForRelease() {
        if (!isApkInDebug(this.context)) {
            if (getLogStatus() == -1) {
                setLogStatus(true);
            }
            Logger.e(this.TAG, "测试阶段release版本默认开打低频日志（每次重启应用后都会设置为打开），如果级别不为 低频，说明日志设置里修改了默认值，根据需要在设置里修改", null, new Object[0]);
            return;
        }
        Logger.e(this.TAG, "debug版本默认开打超高频日志，如果日志状态为 关闭，或者级别不为 超高频，说明日志设置里修改了默认值，根据需要在设置里修改", null, new Object[0]);
    }

    private void getSharedPreferences(Context context) {
        SharedPreferences sharedPreferencesSharedPrefs = new SdkSharePreference(context, SdkSharePreference.SharePreferenceName.logInfoStorage).sharedPrefs();
        this.preference = sharedPreferencesSharedPrefs;
        this.editor = sharedPreferencesSharedPrefs.edit();
    }

    private long getSPLogOpenTime() {
        return this.preference.getLong(LOG_OPEN_TIME, System.currentTimeMillis() / 1000);
    }

    private void setSPLogOpenTime(long j) {
        this.editor.putLong(LOG_OPEN_TIME, j);
        this.editor.apply();
    }

    private int getSPLogStatus() {
        return this.preference.getInt(LOG_STATUS, isApkInDebug(this.context) ? 0 : -1);
    }

    private void setSPLogMode(int i) {
        this.editor.putInt(LOG_STATUS, i);
        this.editor.apply();
    }

    private int getSPLogLevel() {
        return this.preference.getInt(LOG_LEVEL, isApkInDebug(this.context) ? 2 : 0);
    }

    private void setSPLogLevel(int i) {
        this.editor.putInt(LOG_LEVEL, i);
        this.editor.apply();
    }

    private void init() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        long sPLogOpenTime = getSPLogOpenTime();
        this.logStatus = getSPLogStatus();
        this.logLevel = getSPLogLevel();
        if (this.logStatus != -1 && jCurrentTimeMillis - sPLogOpenTime > 86400 && isApkInDebug(this.context)) {
            this.logStatus = -1;
            setSPLogMode(-1);
        }
    }

    public String getDocumentSP(String str, String str2) {
        return this.preference.getString(str, str2);
    }

    public void setDocumentSP(String str, String str2) {
        this.editor.putString(str, str2);
        this.editor.apply();
    }

    public int getLogStatus() {
        return this.logStatus;
    }

    public void setLogStatus(boolean z) {
        if (z) {
            this.logStatus = -1;
        } else {
            this.logStatus = 0;
            setSPLogOpenTime(System.currentTimeMillis() / 1000);
        }
        setSPLogMode(this.logStatus);
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(int i) {
        this.logLevel = i;
        setSPLogLevel(i);
    }

    public boolean isLogLow() {
        return this.logStatus != -1 && this.logLevel == 0;
    }

    public boolean isLogHigh() {
        return this.logStatus != -1 && this.logLevel == 1;
    }

    public boolean isLogHighPlus() {
        return this.logStatus != -1 && this.logLevel == 2;
    }

    public static boolean isApkInDebug(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public List<String> getPath(Context context) {
        if (Build.VERSION.SDK_INT < 24) {
            return getUSBPaths(context);
        }
        return getExternalPath(context, "");
    }

    public static List<String> getUSBPaths(Context context) {
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageManager");
            Method method = cls.getMethod("getVolumePaths", new Class[0]);
            Method method2 = cls.getMethod("getVolumeState", String.class);
            for (String str : (String[]) method.invoke(storageManager, new Object[0])) {
                if (((String) method2.invoke(storageManager, str)).equals(Environment.MEDIA_MOUNTED) && !str.contains(VolumeInfo.ID_EMULATED_INTERNAL)) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public List<String> getStoragePath(Context context) {
        ArrayList arrayList = new ArrayList();
        List<StorageVolume> storageVolumes = ((StorageManager) context.getSystemService("storage")).getStorageVolumes();
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = cls.getMethod("getPath", new Class[0]);
            cls.getMethod("isRemovable", new Class[0]);
            for (int i = 0; i < storageVolumes.size(); i++) {
                arrayList.add((String) method.invoke(storageVolumes.get(i), new Object[0]));
            }
        } catch (Exception e) {
            Logger.d(this.TAG, "getExternalPath-- e:" + e, new Object[0]);
        }
        return arrayList;
    }

    public List<String> getExternalPath(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT < 24) {
            Logger.d(this.TAG, "系统版本低于Android7.0", new Object[0]);
            return arrayList;
        }
        List<StorageVolume> storageVolumes = ((StorageManager) context.getSystemService("storage")).getStorageVolumes();
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = cls.getMethod("getPath", new Class[0]);
            Method method2 = cls.getMethod("isRemovable", new Class[0]);
            for (int i = 0; i < storageVolumes.size(); i++) {
                StorageVolume storageVolume = storageVolumes.get(i);
                String str2 = (String) method.invoke(storageVolume, new Object[0]);
                boolean zBooleanValue = ((Boolean) method2.invoke(storageVolume, new Object[0])).booleanValue();
                storageVolume.getDescription(context);
                if (zBooleanValue) {
                    arrayList.add(str2);
                }
            }
        } catch (Exception e) {
            Logger.d(this.TAG, "getExternalPath-- e:" + e, new Object[0]);
        }
        return arrayList;
    }
}
