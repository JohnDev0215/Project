package com.byd.repository.bean.request;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RequestDownloadReportInfo implements Serializable {
    public String batchId;
    public List<EcuModel> ecuModel;
    public String uuid;

    public static class EcuModel implements Serializable {
        public String ecuDestReason;
        public String ecuDestStatus;
        public String ecuDestTime;
        public String ecuId;
        public String ecuOriginReason;
        public String ecuOriginSVer;
        public String ecuOriginStatus;
        public String ecuOriginTime;
        public String ecuSoftCode;
        public String ecuSoftVer;
    }
}
