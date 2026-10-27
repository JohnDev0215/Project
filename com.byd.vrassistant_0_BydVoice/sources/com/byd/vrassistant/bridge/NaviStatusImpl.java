package com.byd.vrassistant.bridge;

import com.byd.vrassistant.conversation.NaviHelper;

/* JADX INFO: loaded from: classes.dex */
public class NaviStatusImpl implements NaviStatus {
    @Override // com.byd.vrassistant.bridge.NaviStatus
    public boolean isPoiShow() {
        int pOIDetailStatus = NaviHelper.getInstance().getPOIDetailStatus();
        return pOIDetailStatus == 1 || pOIDetailStatus == 2;
    }
}
