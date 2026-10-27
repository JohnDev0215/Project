package com.ts.installer;

import android.os.Build;
import android.os.SystemProperties;
import com.ts.installer.bean.InstallConfig;
import com.ts.installer.manage.ConfigManager;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CarInstallerManagerImpl extends CarInstallerManager {
    private static final String CONFIG_INSTALL_ENABLE_ADB = "persist.sys.install.enableadb";
    private ConfigManager mConfigManager;
    private InstallConfig mInstallConfig;

    protected CarInstallerManagerImpl() {
        ConfigManager configManager = ConfigManager.getInstance();
        this.mConfigManager = configManager;
        this.mInstallConfig = configManager.readInstallConfig();
    }

    @Override // com.ts.installer.CarInstallerManager
    public boolean canAdbInstall() {
        InstallConfig installConfig = this.mInstallConfig;
        return installConfig == null || installConfig.isAdbEnabled() || isAdbInstallHideSwitchEnabled();
    }

    @Override // com.ts.installer.CarInstallerManager
    public int canInstall(String installerPackageName) {
        List<String> whiteList;
        InstallConfig installConfig = this.mInstallConfig;
        if (installConfig == null) {
            return 1;
        }
        if (installConfig.isInstallEnabled()) {
            return 2;
        }
        return (installerPackageName == null || (whiteList = this.mInstallConfig.getWhiteApps()) == null || whiteList.size() <= 0 || !whiteList.contains(installerPackageName)) ? 3 : 2;
    }

    private boolean isAdbInstallHideSwitchEnabled() {
        return Build.IS_DEBUGGABLE && SystemProperties.getBoolean(CONFIG_INSTALL_ENABLE_ADB, false);
    }
}
