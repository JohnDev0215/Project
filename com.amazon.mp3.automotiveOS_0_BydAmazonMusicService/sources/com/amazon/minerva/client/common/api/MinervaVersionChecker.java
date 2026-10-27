package com.amazon.minerva.client.common.api;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.minerva.client.common.internal.util.DevicePlatformIdentifierUtil;

/* JADX INFO: loaded from: classes3.dex */
public class MinervaVersionChecker {
    private static volatile MinervaVersionChecker sMinervaVersionCheckerInstance;
    private DevicePlatformIdentifierUtil mDevicePlatformIdentifierUtil = DevicePlatformIdentifierUtil.getInstance();
    private PackageManager mPackageManager;

    private MinervaVersionChecker(Context context) {
        this.mPackageManager = context.getPackageManager();
    }

    public static MinervaVersionChecker getInstance(Context context) {
        if (sMinervaVersionCheckerInstance == null) {
            synchronized (MinervaVersionChecker.class) {
                if (sMinervaVersionCheckerInstance == null) {
                    sMinervaVersionCheckerInstance = new MinervaVersionChecker(context);
                }
            }
        }
        return sMinervaVersionCheckerInstance;
    }

    public boolean isVersionSupported(MinervaVersion minervaVersion) {
        return !this.mDevicePlatformIdentifierUtil.isDevicePlatformFireOS() || (this.mPackageManager.hasSystemFeature("com.fireos.sdk.minerva") && this.mPackageManager.hasSystemFeature("com.fireos.sdk.minerva", minervaVersion.getVersionNumber()));
    }
}
