package com.byd.repository.bean.request;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RequestSubmitReportInfo implements Serializable {
    public String batchId;
    public List<EcuModel> ecuModel;
    public NormalModel normalModel;
    public int priorityType;
    public int updateMode;
    public String uuid;

    public static class EcuModel implements Serializable {
        public String diagnosticTroubleCode;
        public String ecuId;
        public String ecuOriginSVer;
        public String ecuPreflashStatus;
        public String ecuPreflashTime;
        public String ecuReason;
        public String ecuSoftCode;
        public String ecuSoftVer;
        public String ecuStatus;
        public String ecuTime;
        public String ecuUpdateFlag;
        public List<SubModel> subModel;

        public static class SubModel implements Serializable {
            public String ecuOriginSVer;
            public String ecuSoftCode;
            public String ecuSoftVer;
            public String ecuTime;
            public String subModelId;
            public String subModelStatus;
            public String subModelUpdateFlag;

            public String toString() {
                return "SubModel{subModelId='" + this.subModelId + "', ecuSoftVer='" + this.ecuSoftVer + "', ecuSoftCode='" + this.ecuSoftCode + "', ecuTime='" + this.ecuTime + "', ecuOriginSVer='" + this.ecuOriginSVer + "', subModelStatus='" + this.subModelStatus + "', subModelUpdateFlag='" + this.subModelUpdateFlag + "'}";
            }
        }
    }

    public static class NormalModel implements Serializable {
        public String normalOriginVer;
        public String normalReason;
        public String normalStatus;
        public String normalStrategyVersion;
        public String normalTime;
        public String normalUiInfo;
        public String normalVersion;
        public String strategyType;
    }
}
