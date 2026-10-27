package com.byd.repository.bean;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UpdateResultInfo implements Serializable {
    public String normalOriginVer;
    public String normalVersion;
    public List<UpdateResultEcuInfo> rollbackFailEcuInfoList;
    public int updateMode;
    public List<UpdateResultEcuInfo> updateResultEcuInfoList;

    public static class UpdateResultEcuInfo implements Serializable {
        public String ecuConfigCode;
        public String ecuConfigVer;
        public String ecuHardVer;
        public String ecuId;
        public String ecuOriginSVer;
        public String ecuSoftCode;
        public String ecuSoftVer;
        public String ecuTime;
        public String errorCode;
        public boolean isRollbackFail;
        public boolean isUpdateSuccess;
    }
}
