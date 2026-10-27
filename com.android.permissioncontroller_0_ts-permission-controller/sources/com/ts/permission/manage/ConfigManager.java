package com.ts.permission.manage;

import android.util.Log;
import com.ts.permission.bean.PermissionConfig;
import java.io.File;
import ts.car.utils.CarGson;

/* JADX INFO: loaded from: classes8.dex */
public class ConfigManager {
    private static final String PERMISSION_CONFIG_DIR = "/vendor/etc/data/ts_permission_config.json";
    private static final String PERMISSION_CONFIG_DIR_PRIMARY = "/vendor/etc/data/permission_config.json";
    private static final String TAG = "TSPS_ConfigManager";
    private static volatile ConfigManager mInstance;

    public static ConfigManager getInstance() {
        if (mInstance == null) {
            synchronized (ConfigManager.class) {
                if (mInstance == null) {
                    mInstance = new ConfigManager();
                }
            }
        }
        return mInstance;
    }

    private ConfigManager() {
    }

    public PermissionConfig readPermissionConfig() {
        CarGson carGson = new CarGson();
        File configFile = new File(PERMISSION_CONFIG_DIR_PRIMARY);
        Log.d(TAG, "readPermissionConfig");
        if (configFile.exists() && configFile.isFile()) {
            Log.d(TAG, "permission_config.json exists");
            return (PermissionConfig) carGson.getObjectFromJsonFile(PERMISSION_CONFIG_DIR_PRIMARY, PermissionConfig.class);
        }
        return (PermissionConfig) carGson.getObjectFromJsonFile(PERMISSION_CONFIG_DIR, PermissionConfig.class);
    }
}
