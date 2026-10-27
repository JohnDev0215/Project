package com.ts.installer.bean;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class InstallConfig {
    private List<String> whiteApps;
    private boolean installEnabled = true;
    private boolean adbEnabled = true;

    public boolean isInstallEnabled() {
        return this.installEnabled;
    }

    public void setInstallEnabled(boolean installEnabled) {
        this.installEnabled = installEnabled;
    }

    public boolean isAdbEnabled() {
        return this.adbEnabled;
    }

    public void setAdbEnabled(boolean adbEnabled) {
        this.adbEnabled = adbEnabled;
    }

    public List<String> getWhiteApps() {
        return this.whiteApps;
    }

    public void setWhiteApps(List<String> whiteApps) {
        this.whiteApps = whiteApps;
    }
}
