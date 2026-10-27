package com.byd.ota.master;

import com.byd.repository.bean.EcuVersionInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VehicleVersionInfo implements Serializable {
    public static final String FORCE_UPGRADE = "1";
    public String cause;
    public List<EcuVersionInfo> configPackageInfo;
    public List<EcuVersionInfo> configSourceInfo;
    public List<EcuVersionInfo> sourcePackageInfo;
    public String status;
    public StrategyBean strategy;
    public List<EcuVersionInfo> targetPackageInfo;

    public static class StrategyBean implements Serializable {
        public String acknowledgements;
        public String batchId;
        public String countDown;
        public String date;
        public String description;
        public String failureNumber;
        public String freeBurningTime;
        public String functionAddition;
        public String functionOptimization;
        public String isForce;
        public String isFreeBurning;
        public String isSaleCar;
        public String lowestSoc;
        public String lowestVoltage;
        public int priorityType;
        public List<String> rollbackDependencies;
        public List<SequenceBean> sequence;
        public String strategyVer;
        public String temperature;

        public static class SequenceBean implements Serializable {
            public String ecuConfigDid;
            public String ecuDId;
            public String ecuDiaId;
            public String ecuId;
            public String ecuIp;
            public String ecuPort;
            public String ecuReprogramType;
            public String ecuReprogramWay;
            public String ecuUpdateStyle;
            public String hlvFlag;
            public String hvPowerFlag;
            public boolean isAES;

            public String toString() {
                return "SequenceBean{ecuId='" + this.ecuId + "', ecuDiaId='" + this.ecuDiaId + "', ecuDId='" + this.ecuDId + "', ecuConfigDid='" + this.ecuConfigDid + "', hlvFlag='" + this.hlvFlag + "', hvPowerFlag='" + this.hvPowerFlag + "', ecuReprogramType='" + this.ecuReprogramType + "', ecuReprogramWay='" + this.ecuReprogramWay + "', ecuUpdateStyle='" + this.ecuUpdateStyle + "', ecuIp='" + this.ecuIp + "', ecuPort='" + this.ecuPort + "', isAES=" + this.isAES + '}';
            }
        }

        public List<String> getRollbackDependencies() {
            List<String> list = this.rollbackDependencies;
            return list == null ? new ArrayList() : list;
        }

        public List<SequenceBean> getSequence() {
            List<SequenceBean> list = this.sequence;
            return list == null ? new ArrayList() : list;
        }
    }

    public List<EcuVersionInfo> getSourcePackageInfo() {
        List<EcuVersionInfo> list = this.sourcePackageInfo;
        return list == null ? new ArrayList() : list;
    }

    public StrategyBean getStrategy() {
        return this.strategy;
    }

    public List<EcuVersionInfo> getTargetPackageInfo() {
        List<EcuVersionInfo> list = this.targetPackageInfo;
        return list == null ? new ArrayList() : list;
    }
}
