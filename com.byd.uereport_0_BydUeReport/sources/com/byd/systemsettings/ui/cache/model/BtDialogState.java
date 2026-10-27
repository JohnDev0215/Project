package com.byd.systemsettings.ui.cache.model;

import com.byd.bt.model.BTDevice;
import com.byd.bt.model.BondDevice;
import com.byd.bt.model.BtPagerType;
import com.byd.mvp.model.BydData;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BtDialogState extends DialogState {
    private BydData curBydData;
    private BtPagerType curPagerType;
    private List<BTDevice> discoverDevices;
    private int firstItemIndex = 0;
    private boolean unpairDialogShowing = false;
    private BondDevice unpairDevice = null;

    public BtDialogState(List<BTDevice> list, BtPagerType btPagerType, BydData bydData) {
        this.discoverDevices = null;
        this.curPagerType = null;
        this.curBydData = null;
        this.discoverDevices = list;
        this.curPagerType = btPagerType;
        this.curBydData = bydData;
    }

    public BydData getCurBydData() {
        return this.curBydData;
    }

    public BtPagerType getCurPagerType() {
        return this.curPagerType;
    }

    public List<BTDevice> getDiscoverDevices() {
        return this.discoverDevices;
    }

    public int getFirstItemIndex() {
        return this.firstItemIndex;
    }

    public BondDevice getUnpairDevice() {
        return this.unpairDevice;
    }

    public boolean isUnpairDialogShowing() {
        return this.unpairDialogShowing;
    }

    public void setCurBydData(BydData bydData) {
        this.curBydData = bydData;
    }

    public void setCurPagerType(BtPagerType btPagerType) {
        this.curPagerType = btPagerType;
    }

    public void setDiscoverDevices(List<BTDevice> list) {
        this.discoverDevices = list;
    }

    public void setFirstItemIndex(int i8) {
        this.firstItemIndex = i8;
    }

    public void setUnpairDevice(BondDevice bondDevice) {
        this.unpairDevice = bondDevice;
    }

    public void setUnpairDialogShowing(boolean z7) {
        this.unpairDialogShowing = z7;
    }

    public String toString() {
        return "BtDialogState{curPagerType=" + this.curPagerType + ", curBydData=" + this.curBydData + ", firstItemIndex=" + this.firstItemIndex + ", unpairDialogShowing=" + this.unpairDialogShowing + ", unpairDevice=" + this.unpairDevice + ", discoverDevices=" + this.discoverDevices + '}';
    }
}
