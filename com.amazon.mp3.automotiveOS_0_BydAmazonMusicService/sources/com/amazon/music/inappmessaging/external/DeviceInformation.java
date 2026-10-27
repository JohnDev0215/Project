package com.amazon.music.inappmessaging.external;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceInformation {
    public static final String PARAMS_DEVICE_BRAND = "deviceBrand";
    public static final String PARAMS_DEVICE_LOCALE = "deviceLocale";
    public static final String PARAMS_DEVICE_MODEL = "deviceModel";
    public static final String PARAMS_TELECOM_CARRIER = "telecomCarrier";
    private final String deviceBrand;
    private final String deviceLocale;
    private final String deviceModel;
    private final String telecomCarrier;

    public DeviceInformation(final String deviceModel, final String deviceBrand, final String telecomCarrier, final String deviceLocale) {
        this.deviceModel = deviceModel;
        this.deviceBrand = deviceBrand;
        this.telecomCarrier = telecomCarrier;
        this.deviceLocale = deviceLocale;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getDeviceBrand() {
        return this.deviceBrand;
    }

    public String getTelecomCarrier() {
        return this.telecomCarrier;
    }

    public String getDeviceLocale() {
        return this.deviceLocale;
    }

    public String toString() {
        return "DeviceInformation{deviceModel='" + this.deviceModel + "', deviceBrand='" + this.deviceBrand + "', telecomCarrier='" + this.telecomCarrier + "', deviceLocale='" + this.deviceLocale + "'}";
    }

    public List<String> findMissingDeviceInfo() {
        ArrayList arrayList = new ArrayList();
        if (StringUtils.isBlank(getDeviceModel())) {
            arrayList.add(PARAMS_DEVICE_MODEL);
        }
        if (StringUtils.isBlank(getDeviceBrand())) {
            arrayList.add(PARAMS_DEVICE_BRAND);
        }
        if (StringUtils.isBlank(getTelecomCarrier())) {
            arrayList.add(PARAMS_TELECOM_CARRIER);
        }
        if (StringUtils.isBlank(getDeviceLocale())) {
            arrayList.add(PARAMS_DEVICE_LOCALE);
        }
        return arrayList;
    }
}
