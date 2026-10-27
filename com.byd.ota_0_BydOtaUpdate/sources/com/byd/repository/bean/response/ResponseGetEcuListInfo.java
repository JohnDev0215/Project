package com.byd.repository.bean.response;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ResponseGetEcuListInfo implements Serializable {
    public List<EcuInfo> ecuList;
    public String status;

    public static class EcuInfo implements Serializable {
        public String ecuConfigDid;
        public String ecuDId;
        public String ecuDiaId;
        public String ecuHardDid;
        public String ecuId;
        public String ecuIp;
        public String ecuPort;
        public String ecuReprogramType;
        public String ecuReprogramWay;
        public String ecuUpdateStyle;
        public String logicAddress;
    }
}
