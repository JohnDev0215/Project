package com.byd.repository.bean.response;

import com.byd.repository.bean.EcuVersionInfo;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ResponseCheckVersionInfo implements Serializable {
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
        public List<?> functionInfos;
        public String functionOptimization;
        public String isForce;
        public String isFreeBurning;
        public String isSaleCar;
        public String lowestSoc;
        public String lowestVoltage;
        public String msgreportPath;
        public int priorityType;
        public List<String> rollbackDependencies;
        public List<SequenceBean> sequence;
        public String strategyType;
        public String strategyVer;
        public String temperature;
        public String translateEncZipSHA256;
        public String translatePath;
        public String translateSecretKey;

        public static class SequenceBean implements Serializable {
            public String checkConfig;
            public String configSourceFlag;
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
            public String isIDrive;
            public String logicAddress;
            public String needSourcePkg;
        }
    }
}
