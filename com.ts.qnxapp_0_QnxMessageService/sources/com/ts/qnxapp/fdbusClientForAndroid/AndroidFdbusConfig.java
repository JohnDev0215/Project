package com.ts.qnxapp.fdbusClientForAndroid;

import bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr;

/* JADX INFO: loaded from: classes.dex */
public class AndroidFdbusConfig {
    public static final String ANIMATION_SERVER_NAME = "ANIMATION.server_android";
    public static final String ANIMATION_SERVER_URL = "svc://com.ts.animation.ivi";
    public static final String AVM_CLIENT_URL = "xxx";
    public static final String AVM_SERVER_URL = "xxx";
    public static final String BLUE_TOOTH_CLIENT_URL = "xxx";
    public static final String BLUE_TOOTH_SERVER_URL = "xxx";
    public static final String BOOT_ANIMATION_CLIENT_URL = "xxx";
    public static final String BOOT_ANIMATION_SERVER_URL = "xxx";
    public static final String CLUSTER_CLIENT_URL = "CLUSTER.client_android";
    public static final String CLUSTER_SERVER_NAME = "CLUSTER.server_android";
    public static final String CLUSTER_SERVER_URL = "svc://com.ts.interaction.ivi";
    public static final String ENGINEER_MODE_CLIENT_URL = "xxx";
    public static final String ENGINEER_MODE_SERVER_URL = "xxx";
    public static final String MEDIA_CLIENT_URL = "xxx";
    public static final String MEDIA_SERVER_URL = "xxx";
    public static final String NAVI_CLIENT_URL = "xxx";
    public static final String NAVI_SERVER_URL = "xxx";
    public static final String OTA_CLIENT_URL = "OTA.client_android";
    public static final String OTA_SERVER_NAME = "OTA.server_android";
    public static final String OTA_SERVER_URL = "svc://com.autolink.ota.upgradeagent";
    public static final String OTA_TOPIC = "topic_ota";
    public static final String POWER_CLIENT_NAME = "POWERR.client_android";
    public static final String POWER_CLIENT_URL = "xxx";
    public static final String POWER_SERVER_URL = "svc://com.bosch.cm.lcm";
    public static final String TEMPERATURE_CLIENT_NAME = "TEMPERATURE.client_android";
    public static final String TEMPERATURE_SERVER_URL = "svc://com.bosch.cm.qnx.notification-center";
    public static final int TYPE_AVM = 7;
    public static final int TYPE_BLUE_TOOTH = 2;
    public static final int TYPE_BOOT_ANIMATION = 6;
    public static final int TYPE_CLUSTER = 9;
    public static final int TYPE_ENGINEER_MODE = 5;
    public static final int TYPE_MEDIA = 1;
    public static final int TYPE_NAVI = 3;
    public static final int TYPE_OTA = 8;
    public static final int TYPE_POWER = 4;
    public static final int TYPE_TEMPERATURE = 10;
    public static final Integer[] mClusterSubIdsArr = {0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1};
    public static final Integer[] mOTASubIdsArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    public static final Integer[] mTemperatureSubIdsArr = {1, Integer.valueOf(ComBoschCmPlatformNotifCtr.FdbusEventId.EVT_THERMAL_INFO_VALUE)};
}
