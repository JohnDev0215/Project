package com.byd.car.iot.converter;

import com.byd.car.iot.converter.AutoiotSettingConstants;

/* JADX INFO: loaded from: classes.dex */
public enum DeviceTypeEnum {
    AUTOIOT_SEAT(AutoiotSettingConstants.DeviceIds.AUTOIOT_SEAT),
    AUTOIOT_LOCK(AutoiotSettingConstants.DeviceIds.AUTOIOT_LOCK),
    AUTOIOT_EYEMASSAGER(AutoiotSettingConstants.DeviceIds.AUTOIOT_EYEMASSAGER),
    UN_KNOWN_DEVICE("unknownDevice");

    public final String name;

    DeviceTypeEnum(String str) {
        this.name = str;
    }

    public static DeviceTypeEnum findByName(String str) {
        for (DeviceTypeEnum deviceTypeEnum : values()) {
            if (deviceTypeEnum.name.equals(str)) {
                return deviceTypeEnum;
            }
        }
        return UN_KNOWN_DEVICE;
    }
}
