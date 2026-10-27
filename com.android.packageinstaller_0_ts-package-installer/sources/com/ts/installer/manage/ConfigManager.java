package com.ts.installer.manage;

import com.ts.installer.bean.InstallConfig;
import java.io.File;
import ts.car.utils.CarGson;

/* JADX INFO: loaded from: classes2.dex */
public class ConfigManager {
    private static final String INSTALL_CONFIG_DIR = "/vendor/etc/data/install_config.json";
    private static final String TS_INSTALL_CONFIG_DIR = "/vendor/etc/data/ts_install_config.json";
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

    public InstallConfig readInstallConfig() {
        CarGson carGson = new CarGson();
        String configFilePath = getConfigFilePath();
        InstallConfig installConfig = (InstallConfig) carGson.getObjectFromJsonFile(configFilePath, InstallConfig.class);
        return installConfig;
    }

    private String getConfigFilePath() {
        File file = new File(INSTALL_CONFIG_DIR);
        return (file.exists() && file.isFile()) ? INSTALL_CONFIG_DIR : TS_INSTALL_CONFIG_DIR;
    }
}
