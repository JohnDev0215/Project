package com.byd.repository.bean;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EcuOtaResult implements Serializable {
    public String downloadBackup;
    public List<EcuUpdateInfos> ecuUpdateInfos;

    public static class EcuUpdateInfos implements Serializable {
        public String count;
        public String ecuId;
        public String ecuTime;
        public String errorCode;
        public String operate;
        public String status;
    }
}
