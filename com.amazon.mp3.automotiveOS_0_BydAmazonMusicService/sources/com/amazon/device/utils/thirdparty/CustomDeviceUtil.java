package com.amazon.device.utils.thirdparty;

import android.content.Context;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class CustomDeviceUtil extends AbstractDeviceUtil {
    private String mCountryOfResidence;
    private String mDeviceSerialNumber;
    private String mDeviceType;
    private String mPreferredMarketplace;

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceMode() {
        return null;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchOSFileTag() {
        return null;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchOTAGroupName() {
        return null;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchRsmGroupName() {
        return null;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchWANSupportedDeviceMode() {
        return null;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public boolean isDeviceSerialNumberAnonymous() {
        return true;
    }

    public CustomDeviceUtil(Context context) {
        super(context);
    }

    public void setDeviceType(String str) {
        this.mDeviceType = str;
    }

    public void setDeviceSerialNumber(String str) {
        this.mDeviceSerialNumber = str;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceType() {
        return this.mDeviceType;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceSerialNumber() {
        return this.mDeviceSerialNumber;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceSerialNumberOrAnonymous() {
        return this.mDeviceSerialNumber;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchCountryOfResidence() {
        String str = this.mCountryOfResidence;
        return str == null ? Locale.getDefault().getCountry() : str;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchPreferredMarketplace() {
        String str = this.mPreferredMarketplace;
        return str == null ? "UNKNOWN" : str;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceLanguage() {
        return Locale.getDefault().getLanguage();
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchStaticCredential() {
        return fetchDeviceType();
    }
}
