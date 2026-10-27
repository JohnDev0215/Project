package com.amazon.avod.qos.model.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.purchase.AssociateTagProviderProxy;
import com.amazon.avod.purchase.PlayerAssociateTagRequestSource;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.session.ServiceSessionManager;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class QosCommonMetricsContext {
    private static final String[] BUILD_EXTRA_PROPERTIES;
    private static final String CHIPSET;
    private static final String EXTRA_LOGGING_INFO;
    private static final String FIRMWARE;
    private static final String MANUFACTURER = Build.MANUFACTURER;
    private static final String MODEL = Build.MODEL;
    private static final String OS_VERSION = Build.VERSION.RELEASE;
    private String mATVClientVersion;
    private final Context mContext;
    private final DeviceConfiguration mDeviceConfiguration;
    private final DeviceIdentity mDeviceIdentity;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final ServiceSessionManager mServiceSessionManager;

    static {
        String str = Build.FINGERPRINT;
        FIRMWARE = str;
        CHIPSET = Build.BOARD;
        String[] strArr = {Build.BOOTLOADER, Build.CPU_ABI, Build.CPU_ABI2, str, Build.HARDWARE, Build.HOST, Build.RADIO};
        BUILD_EXTRA_PROPERTIES = strArr;
        EXTRA_LOGGING_INFO = Joiner.on(", ").skipNulls().join(strArr);
    }

    public QosCommonMetricsContext(@Nonnull Context context, @Nonnull DeviceConfiguration deviceConfiguration, @Nonnull DeviceIdentity deviceIdentity, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull ServiceSessionManager serviceSessionManager) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "appContext");
        this.mDeviceConfiguration = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration, "deviceConfiguration");
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mServiceSessionManager = (ServiceSessionManager) Preconditions.checkNotNull(serviceSessionManager, "serviceSessionManager");
    }

    @Nullable
    public String getApplicationSessionId() {
        this.mServiceSessionManager.waitOnInitialized();
        return this.mServiceSessionManager.getSessionId();
    }

    @Nullable
    public String getAdditionalDeviceProperties() {
        return EXTRA_LOGGING_INFO;
    }

    public void buildCommonMetrics(@Nonnull MetricsBuilder metricsBuilder) {
        metricsBuilder.chipset(CHIPSET).clientVersion(getATVClientVersion()).firmwareVersion(FIRMWARE).manufacturer(MANUFACTURER).modelName(MODEL).operatingSystem("Android" + OS_VERSION).sourceSystem(this.mDeviceIdentity.getDeviceTypeId()).attributionTag(AssociateTagProviderProxy.getInstance().getAssociateTag(PlayerAssociateTagRequestSource.QOS).or("")).clientMode(this.mNetworkConnectionManager.hasDataConnection() ? "online" : "offline").signalStrength(this.mNetworkConnectionManager.getSignalStrength()).connectionType(this.mNetworkConnectionManager.getConnectionType()).screenResolutionHeight(Integer.valueOf(this.mDeviceConfiguration.getScreenHeight())).screenResolutionWidth(Integer.valueOf(this.mDeviceConfiguration.getScreenWidth()));
    }

    private String getATVClientVersion() {
        PackageInfo packageInfo;
        String str;
        if (this.mATVClientVersion == null) {
            try {
                String packageName = this.mContext.getPackageName();
                if (packageName != null && (packageInfo = this.mContext.getPackageManager().getPackageInfo(packageName, 0)) != null && (str = packageInfo.versionName) != null) {
                    this.mATVClientVersion = str;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                DLog.warnf("Couldn't read package version, returning default version name");
                this.mATVClientVersion = "1.01.000";
            }
        }
        return this.mATVClientVersion;
    }
}
