package com.byd.repository.bean.request;

import android.hardware.bydauto.ota.DiagnosticInfo;
import android.hardware.bydauto.ota.ReadDTCInfoType;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class RequestGetDTCInfo extends DiagnosticInfo implements Serializable {
    public RequestGetDTCInfo(String str, String str2) {
        super(str, str2, null, ReadDTCInfoType.DTC_INFO_STATUS_MASK, null, null, "09", null);
    }
}
