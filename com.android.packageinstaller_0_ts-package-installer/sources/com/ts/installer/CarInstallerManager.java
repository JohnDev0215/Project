package com.ts.installer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CarInstallerManager {
    private static final CarInstallerManagerImpl CAR_INSTALLER_MANAGER = new CarInstallerManagerImpl();
    public static final int INSTALL_POLICY_ALLOW = 2;
    public static final int INSTALL_POLICY_DEFAULT = 1;
    public static final int INSTALL_POLICY_DENY = 3;

    public abstract boolean canAdbInstall();

    public abstract int canInstall(String str);

    public static CarInstallerManager getInstallerManager() {
        return CAR_INSTALLER_MANAGER;
    }
}
