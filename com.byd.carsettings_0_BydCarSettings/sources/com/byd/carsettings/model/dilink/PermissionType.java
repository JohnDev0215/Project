package com.byd.carsettings.model.dilink;

import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public enum PermissionType {
    METER(R.drawable.icon_meter_48),
    LOCATION(R.drawable.icon_place_48),
    USAGE_ACCESS(R.drawable.icon_information_48),
    WRITE_SETTINGS(R.drawable.icon_setting_48),
    CHARGE_INFO(R.drawable.icon_card_battery_charging_48),
    PANORAMIC_CAMERA(R.drawable.icon_information_48),
    ENGINE_INFO(R.drawable.icon_information_48),
    TRANSMISSION_INFO(R.drawable.icon_information_48),
    SYSTEM_ALERT_WINDOW(R.drawable.icon_information_48),
    STORAGE(R.drawable.icon_storage_48),
    SAFETY_BELT(R.drawable.icon_information_48),
    CALENDAR(R.drawable.icon_calendar_48),
    TIME(R.drawable.icon_set_time_48),
    CAR_INFO(R.drawable.icon_information_48),
    PHONE(R.drawable.icon_phone_48),
    CAMERA(R.drawable.icon_camera_camera_48),
    SMS(R.drawable.icon_sms_48),
    AC_INFO(R.drawable.icon_information_48),
    OIL_POWER_INFO(R.drawable.icon_statistics_48),
    BODY_SENSOR(R.drawable.icon_body_sensors_48),
    CAR_BODY_SENSOR(R.drawable.icon_information_48),
    CAR_LIGHTING(R.drawable.icon_information_48),
    CAR_SETTINGS(R.drawable.icon_car_setting_48),
    SPEED(R.drawable.icon_information_48),
    CONTACTS(R.drawable.icon_contacts_48),
    DOOR_LOCK(R.drawable.icon_lock_48),
    RECORD_AUDIO(R.drawable.icon_mike),
    STATISTIC(R.drawable.icon_information_48),
    YUN(R.drawable.icon_information_48),
    OTHER(R.drawable.icon_information_48);

    private int imgId;

    PermissionType(int i) {
        this.imgId = 0;
        this.imgId = i;
    }

    public int getImgId() {
        return this.imgId;
    }
}
