package com.ts.androidauto.aap.common;

import com.ts.androidauto.aap.common.StatusConst;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes.dex */
public class DeviceStatus {
    private StatusConst.AoaStatus mAoaStatus;
    private AndroidAutoConst.LinkStatus mLinkStatus;

    public AndroidAutoConst.LinkStatus getLinkStatus() {
        return this.mLinkStatus;
    }

    public void setLinkStatus(AndroidAutoConst.LinkStatus linkStatus) {
        this.mLinkStatus = linkStatus;
    }

    public StatusConst.AoaStatus getAoaStatus() {
        return this.mAoaStatus;
    }

    public void setAoaStatus(StatusConst.AoaStatus aoaStatus) {
        this.mAoaStatus = aoaStatus;
    }
}
