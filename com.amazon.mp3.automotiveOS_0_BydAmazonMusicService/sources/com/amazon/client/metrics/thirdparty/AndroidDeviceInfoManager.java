package com.amazon.client.metrics.thirdparty;

import android.os.Build;
import android.util.Base64;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.dp.logger.DPLogger;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.security.SecureRandom;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidDeviceInfoManager implements DeviceInfoManager {
    protected static final DPLogger log = new DPLogger();
    protected final DeviceUtil mDeviceUtil;
    protected final MetricsDeviceInfo mMetricsDeviceInfo;

    public AndroidDeviceInfoManager(DeviceUtil deviceUtil) {
        if (deviceUtil == null) {
            throw new IllegalArgumentException("Device Util must not be null.");
        }
        this.mDeviceUtil = deviceUtil;
        MetricsDeviceInfo metricsDeviceInfo = new MetricsDeviceInfo();
        this.mMetricsDeviceInfo = metricsDeviceInfo;
        addToMetricsDeviceInfo("softwareVersion", Build.VERSION.INCREMENTAL);
        addToMetricsDeviceInfo("hardware", Build.HARDWARE);
        addToMetricsDeviceInfo("buildType", Build.TYPE);
        addToMetricsDeviceInfo("platform", Build.DEVICE);
        addToMetricsDeviceInfo("model", Build.MODEL);
        log.verbose("Metrics.AndroidDeviceInfoManager", "deviceInfo", "softwareVersion", metricsDeviceInfo.getDeviceInfo("softwareVersion"), "hardware", metricsDeviceInfo.getDeviceInfo("hardware"), "buildType", metricsDeviceInfo.getDeviceInfo("buildType"), "platform", metricsDeviceInfo.getDeviceInfo("platform"), "model", metricsDeviceInfo.getDeviceInfo("model"));
    }

    @Override // com.amazon.client.metrics.thirdparty.DeviceInfoManager
    public void addToMetricsDeviceInfo(String str, String str2) {
        if (str == null) {
            log.error("Metrics.AndroidDeviceInfoManager", "Not adding key-value to metrics device info as key is null", new Object[0]);
        } else {
            this.mMetricsDeviceInfo.addDeviceInfoData(str, str2);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.DeviceInfoManager
    public MetricsDeviceInfo getDeviceInfo() {
        addToMetricsDeviceInfo(Splash.PARAMS_DEVICE_ID, getDeviceSerialNumber());
        addToMetricsDeviceInfo(Splash.PARAMS_DEVICE_TYPE, getDeviceType());
        addToMetricsDeviceInfo("countryOfResidence", getCountryOfResidence());
        addToMetricsDeviceInfo("MarketplaceID", getPreferredMarketplace());
        addToMetricsDeviceInfo("deviceLanguage", getDeviceLanguage());
        addToMetricsDeviceInfo("deviceMode", getDeviceMode());
        addToMetricsDeviceInfo("remoteSettingsGroup", getRsmGroup());
        addToMetricsDeviceInfo("otaGroup", getOTAGroup());
        addToMetricsDeviceInfo("osFileTag", getOSFileTag());
        addToMetricsDeviceInfo("Session", getSessionID());
        addToMetricsDeviceInfo("CustomerId", getCustomerID());
        addToMetricsDeviceInfo("REMOTE_ADDR", getIPAddress());
        return this.mMetricsDeviceInfo;
    }

    protected String getDeviceSerialNumber() {
        return this.mDeviceUtil.fetchDeviceSerialNumberOrAnonymous();
    }

    protected String getDeviceType() {
        return this.mDeviceUtil.fetchDeviceType();
    }

    private String getCountryOfResidence() {
        return this.mDeviceUtil.fetchCountryOfResidence();
    }

    private String getPreferredMarketplace() {
        return this.mDeviceUtil.fetchPreferredMarketplace();
    }

    private String getDeviceLanguage() {
        return this.mDeviceUtil.fetchDeviceLanguage();
    }

    private String getDeviceMode() {
        return this.mDeviceUtil.fetchDeviceMode();
    }

    private String getOSFileTag() {
        return this.mDeviceUtil.fetchOSFileTag();
    }

    public String getSessionID() {
        return this.mDeviceUtil.fetchSessionID();
    }

    public String getCustomerID() {
        return this.mDeviceUtil.fetchCustomerID();
    }

    public String getIPAddress() {
        return this.mDeviceUtil.fetchRemoteIP();
    }

    private String getRsmGroup() {
        return this.mDeviceUtil.fetchRsmGroupName();
    }

    private String getOTAGroup() {
        return this.mDeviceUtil.fetchOTAGroupName();
    }

    protected String createRandomDSN() {
        try {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            return Base64.encodeToString(bArr, 2);
        } catch (Exception e) {
            log.error("createRandomDSN", "Failed to create a device id based on the actual DSN. Falling back on UUID", e);
            return UUID.randomUUID().toString();
        }
    }

    protected String createCustomerID() {
        return this.mDeviceUtil.getRandomDigits(9, new SecureRandom());
    }

    protected String createSessionId() {
        SecureRandom secureRandom = new SecureRandom();
        return String.format("%s-%s-%s", this.mDeviceUtil.getRandomDigits(3, secureRandom), this.mDeviceUtil.getRandomDigits(7, secureRandom), this.mDeviceUtil.getRandomDigits(7, secureRandom));
    }
}
