package com.byd.dilink.content.pm;

import android.content.pm.PackageInstaller;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkPackageManager {
    PackageInstaller getPackageInstaller(int i);

    IDiLinkParceledListSlice<?> getPersistentApplicationsAsUser(int i, int i2);

    boolean isPackageInstalling(String str);
}
