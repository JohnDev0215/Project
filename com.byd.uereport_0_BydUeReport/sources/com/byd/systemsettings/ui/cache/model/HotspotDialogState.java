package com.byd.systemsettings.ui.cache.model;

import com.byd.mvp.model.BydData;
import com.byd.systemsettings.hotspot.model.HotspotPagerType;

/* JADX INFO: loaded from: classes2.dex */
public class HotspotDialogState extends DialogState {
    private BydData curBydData;
    private int curItemIndex;
    private HotspotPagerType curPagerType;

    public HotspotDialogState(int i8, HotspotPagerType hotspotPagerType, BydData bydData) {
        this.curItemIndex = -1;
        this.curPagerType = null;
        this.curBydData = null;
        this.curItemIndex = i8;
        this.curPagerType = hotspotPagerType;
        this.curBydData = bydData;
    }

    public BydData getCurBydData() {
        return this.curBydData;
    }

    public int getCurItemIndex() {
        return this.curItemIndex;
    }

    public HotspotPagerType getCurPagerType() {
        return this.curPagerType;
    }

    public void setCurBydData(BydData bydData) {
        this.curBydData = bydData;
    }

    public void setCurItemIndex(int i8) {
        this.curItemIndex = i8;
    }

    public void setCurPagerType(HotspotPagerType hotspotPagerType) {
        this.curPagerType = hotspotPagerType;
    }
}
