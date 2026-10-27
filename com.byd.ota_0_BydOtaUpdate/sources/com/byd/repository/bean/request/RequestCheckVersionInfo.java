package com.byd.repository.bean.request;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RequestCheckVersionInfo implements Serializable {
    public List<EcuInfo> ecuInfo;
    public String latitude;
    public String longitude;
    public String uuid;
    public String version;

    public static class EcuInfo implements Serializable {
        public String ecuConfigCode;
        public String ecuConfigVer;
        public String ecuHardVer;
        public String ecuId;
        public String ecuSoftCode;
        public String ecuSoftVer;
    }
}
