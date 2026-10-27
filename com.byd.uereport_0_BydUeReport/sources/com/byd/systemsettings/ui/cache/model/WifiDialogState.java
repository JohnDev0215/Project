package com.byd.systemsettings.ui.cache.model;

import android.net.wifi.SupplicantState;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.model.WifiPagerType;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WifiDialogState extends DialogState {
    private BydData curBydData;
    private WifiPagerType curPagerType;
    private boolean doingConnect;
    private SupplicantState preConnectState;
    private List<WifiScanResult> scanList;
    private WifiPagerType prePagerType = null;
    private BydData preBydData = null;
    private int firstItemIndex = 0;
    private int checkItemIndex = 0;

    public WifiDialogState(WifiPagerType wifiPagerType, BydData bydData) {
        this.curPagerType = null;
        this.curBydData = null;
        this.curPagerType = wifiPagerType;
        this.curBydData = bydData;
    }

    public int getCheckItemIndex() {
        return this.checkItemIndex;
    }

    public BydData getCurBydData() {
        return this.curBydData;
    }

    public WifiPagerType getCurPagerType() {
        return this.curPagerType;
    }

    public int getFirstItemIndex() {
        return this.firstItemIndex;
    }

    public BydData getPreBydData() {
        return this.preBydData;
    }

    public SupplicantState getPreConnectState() {
        return this.preConnectState;
    }

    public WifiPagerType getPrePagerType() {
        return this.prePagerType;
    }

    public List<WifiScanResult> getScanList() {
        return this.scanList;
    }

    public boolean isDoingConnect() {
        return this.doingConnect;
    }

    public void setCheckItemIndex(int i8) {
        this.checkItemIndex = i8;
    }

    public void setCurBydData(BydData bydData) {
        this.curBydData = bydData;
    }

    public void setCurPagerType(WifiPagerType wifiPagerType) {
        this.curPagerType = wifiPagerType;
    }

    public void setDoingConnect(boolean z7) {
        this.doingConnect = z7;
    }

    public void setFirstItemIndex(int i8) {
        this.firstItemIndex = i8;
    }

    public void setPreBydData(BydData bydData) {
        this.preBydData = bydData;
    }

    public void setPreConnectState(SupplicantState supplicantState) {
        this.preConnectState = supplicantState;
    }

    public void setPrePagerType(WifiPagerType wifiPagerType) {
        this.prePagerType = wifiPagerType;
    }

    public void setScanList(List<WifiScanResult> list) {
        this.scanList = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WifiDialogState{curPagerType=");
        sb.append(this.curPagerType);
        sb.append(", curBydData=");
        sb.append(this.curBydData);
        sb.append(", prePagerType=");
        sb.append(this.prePagerType);
        sb.append(", preBydData=");
        sb.append(this.preBydData);
        sb.append(", firstItemIndex=");
        sb.append(this.firstItemIndex);
        sb.append(", preConnectState=");
        sb.append(this.preConnectState);
        sb.append(", doingConnect=");
        sb.append(this.doingConnect);
        sb.append(", scanList=");
        List<WifiScanResult> list = this.scanList;
        sb.append(list != null ? Integer.valueOf(list.size()) : "null");
        sb.append(", checkItemIndex=");
        sb.append(this.checkItemIndex);
        sb.append('}');
        return sb.toString();
    }
}
