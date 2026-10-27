package com.amazon.avod.playback.core;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.core.PlayerApplicationComponents;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidDeviceIdentity implements DeviceIdentity {
    private String mClientVersionName;
    private String mDeviceId;
    private String mDeviceTypeId;
    private final InitializationLatch mInitializationLatch;
    private boolean mIsPositanoClient;
    private boolean mIsThirdParty;
    private PackageInfo mPackageInfo;

    public static AndroidDeviceIdentity getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final AndroidDeviceIdentity INSTANCE = new AndroidDeviceIdentity();

        private SingletonHolder() {
        }
    }

    private AndroidDeviceIdentity() {
        this.mInitializationLatch = new InitializationLatch(this);
    }

    public void initialize(@Nonnull PlayerApplicationComponents.InitParams initParams) throws InitializationException {
        String str;
        Preconditions.checkNotNull(initParams, "initParams");
        this.mInitializationLatch.start(120L, TimeUnit.SECONDS);
        String packageName = initParams.getContext().getPackageName();
        if (packageName == null) {
            throw new InitializationException(PlayerInitializationErrorCode.NULL_PACKAGE_NAME, "Client PackageName is null. Unable to continue with device initialization.");
        }
        DLog.logf("Client PackageName: %s", packageName);
        try {
            this.mPackageInfo = initParams.getContext().getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            DLog.warnf("Couldn't read package version");
        }
        PackageInfo packageInfo = this.mPackageInfo;
        if (packageInfo == null || (str = packageInfo.versionName) == null) {
            str = "1.01.000";
        }
        this.mClientVersionName = str;
        DLog.warnf("Client Version: %s", str);
        this.mDeviceId = initParams.getDeviceId();
        String deviceTypeId = initParams.getDeviceTypeId();
        this.mDeviceTypeId = deviceTypeId;
        this.mIsThirdParty = "A43PXU4ZN2AL1".equalsIgnoreCase(deviceTypeId) || "A1MPSLFC7L5AFK".equalsIgnoreCase(this.mDeviceTypeId);
        this.mIsPositanoClient = "A2Z1NVLU6LCAUO".equalsIgnoreCase(this.mDeviceTypeId);
        this.mInitializationLatch.complete();
        verifyOnlyOneDeviceTypeIsSet(isAmazonDevice(), isThirdParty(), isPositanoClient());
    }

    private void verifyOnlyOneDeviceTypeIsSet(boolean... zArr) {
        int i = 0;
        for (boolean z : zArr) {
            if (z) {
                i++;
            }
            if (i > 1) {
                Preconditions2.fail("More than one device type boolean was set to true in the top level client's config.xml", new Object[0]);
            }
        }
        if (i == 0) {
            Preconditions2.fail("No device type boolean was set in the top level client's config.xml", new Object[0]);
        }
    }

    @Override // com.amazon.avod.playback.capability.DeviceIdentity
    public void waitOnInitialized() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
    }

    @Override // com.amazon.avod.playback.capability.DeviceIdentity
    @Nonnull
    public String getDeviceId() {
        this.mInitializationLatch.checkInitialized();
        return this.mDeviceId;
    }

    @Override // com.amazon.avod.playback.capability.DeviceIdentity
    @Nonnull
    public String getDeviceTypeId() {
        this.mInitializationLatch.checkInitialized();
        return this.mDeviceTypeId;
    }

    @Override // com.amazon.avod.playback.capability.DeviceIdentity
    @Nonnull
    public String getAppPackageName() {
        this.mInitializationLatch.checkInitialized();
        return this.mPackageInfo.packageName;
    }

    @Override // com.amazon.avod.playback.capability.DeviceIdentity
    @Nonnull
    public String getAppVersionName() {
        this.mInitializationLatch.checkInitialized();
        return this.mClientVersionName;
    }

    @Override // com.amazon.avod.playback.capability.DeviceIdentity
    public int getAppMajorVersion() {
        this.mInitializationLatch.checkInitialized();
        PackageInfo packageInfo = this.mPackageInfo;
        if (packageInfo == null) {
            return 1;
        }
        return packageInfo.versionCode;
    }

    @Override // com.amazon.avod.playback.capability.DeviceIdentity
    public boolean isAmazonDevice() {
        this.mInitializationLatch.checkInitialized();
        return (isThirdParty() || isPositanoClient()) ? false : true;
    }

    @Override // com.amazon.avod.playback.capability.DeviceIdentity
    public boolean isThirdParty() {
        this.mInitializationLatch.checkInitialized();
        return this.mIsThirdParty;
    }

    public boolean isPositanoClient() {
        this.mInitializationLatch.checkInitialized();
        return this.mIsPositanoClient;
    }
}
