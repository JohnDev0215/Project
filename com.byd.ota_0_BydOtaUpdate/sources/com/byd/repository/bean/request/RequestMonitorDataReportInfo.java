package com.byd.repository.bean.request;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class RequestMonitorDataReportInfo implements Serializable {
    public static final int FUNCTION_CURRENT_ECU_DOWNLOAD_TIME = 5;
    public static final int FUNCTION_DIALOG_COUNT_AND_USER_SELECT = 10;
    public static final int FUNCTION_IS_SUPPORT_OTA = 6;
    public static final int FUNCTION_OFF_INTERRUPT_DOWNLOAD = 9;
    public static final int FUNCTION_TIME_ZONE_CHANGE = 11;
    public static final int FUNCTION_TRANSLATE_ERROR = 12;
    public static final int FUNCTION_UPGRADE_APPOINTMENT_TIME = 4;
    public static final int FUNCTION_UPGRADE_CANCEL = 3;
    public static final int FUNCTION_UPGRADE_COUNTDOWN_STATUS = 8;
    public static final int FUNCTION_UPGRADE_TRIGGER_CONFIRM = 7;
    public static final int FUNCTION_UPGRADE_TYPE_AND_TIME = 1;
    public static final int FUNCTION_UPGRADE_VERSION_STATUS = 2;
    public String batchId;
    public Data data;
    public int fuctionId;
    public String uuid;

    public static class Data implements Serializable {
        public String acknowledgements;
        public int actionFlag;
        public String actionIndex;
        public String actionTime;
        public int actionType;
        public int actionWay;
        public String appointmnet;
        public int cancelUpgrade;
        public String countryCode;
        public String description;
        public int downloadIndex;
        public int downloadReason;
        public String downloadTime;
        public int downloadType;
        public String ecuId;
        public String ecuSoftCode;
        public String ecuSoftVer;
        public String estimate;
        public int exitUpgrade;
        public String functionAddition;
        public String functionOptimization;
        public int isSupportOta;
        public String language;
        public int languageCode;
        public int operationType;
        public String strategyVer;
        public String timeZone;
        public int translateResult;
        public String triggerTime;
        public String updateEndTime;
        public int updatePath;
        public String updateStartTime;
        public String updateTime;
        public int updateType;
        public String version;
    }
}
