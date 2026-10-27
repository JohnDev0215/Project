package com.byd.repository.bean;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RepairUpgradeInfo implements Serializable {
    public boolean isNeedRepairUpgrade;
    public List<RepairUpgradeEcuInfo> repairUpgradeEcuInfoList;
    public String time;

    public static class RepairUpgradeEcuInfo implements Serializable {
        public String ecuConfigCode;
        public String ecuConfigVer;
        public String ecuHardVer;
        public String ecuId;
        public String ecuOriginSVer;
        public String ecuSoftCode;
        public String ecuSoftVer;
        public String errorCode;
    }
}
