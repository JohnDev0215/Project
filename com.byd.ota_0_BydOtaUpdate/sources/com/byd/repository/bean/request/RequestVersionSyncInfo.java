package com.byd.repository.bean.request;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RequestVersionSyncInfo implements Serializable {
    public List<EcuInfo> ecuInfo;
    public String uuid;
    public String version;

    public static class EcuInfo implements Serializable {
        public String ecuId;
        public String ecuSoftCode;
        public String ecuSoftVer;
    }
}
