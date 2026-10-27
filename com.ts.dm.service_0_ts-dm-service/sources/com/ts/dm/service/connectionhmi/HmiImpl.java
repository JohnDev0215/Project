package com.ts.dm.service.connectionhmi;

import android.content.Context;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class HmiImpl implements IHmiFactory {
    private static final String TAG = "HmiImpl";
    private ConnectionFailedHmi mFailedHmi;
    private IHmiListener mListener;
    private ConnectionLoadingHmi mLoadingHmi;
    private ConnectionCarPlaySwitchClosedHmi mSwitchClosedHmi;
    private ConnectionSwitchHmi mSwitchHmi;
    private ConnectionWirelessHmi mWirelessHmi;

    public HmiImpl(Context context, IHmiListener listener) {
        LogUtil.info(TAG, "create");
        this.mLoadingHmi = new ConnectionLoadingHmi(context, this);
        this.mSwitchHmi = new ConnectionSwitchHmi(context, this);
        this.mSwitchClosedHmi = new ConnectionCarPlaySwitchClosedHmi(context, this);
        this.mWirelessHmi = new ConnectionWirelessHmi(context, this);
        this.mFailedHmi = new ConnectionFailedHmi(context, this);
        this.mListener = listener;
    }

    @Override // com.ts.dm.service.connectionhmi.IHmiFactory
    public void changeHmi(int what, ConnectionInfoBean info) {
        switch (what) {
            case 1:
                this.mSwitchHmi.showView(info);
                break;
            case 2:
                this.mSwitchHmi.hideView(info);
                break;
            case 3:
                this.mSwitchClosedHmi.showView(info);
                break;
            case 4:
                this.mSwitchClosedHmi.hideView(info);
                break;
            case 5:
                this.mWirelessHmi.showView(info);
                break;
            case 6:
                this.mWirelessHmi.hideView(info);
                break;
            case 7:
                this.mFailedHmi.showView(info);
                break;
            case 8:
                this.mLoadingHmi.showView(info);
                break;
            case 9:
                this.mLoadingHmi.hideView(info);
                break;
            case 10:
                this.mLoadingHmi.updateView(info);
                break;
            case 11:
                this.mFailedHmi.hideView(info);
                break;
        }
    }

    @Override // com.ts.dm.service.connectionhmi.IHmiFactory
    public void onClickHmiForConnect(ConnectionInfoBean info) {
        this.mListener.onConnect(info);
    }

    @Override // com.ts.dm.service.connectionhmi.IHmiFactory
    public void onCheckConnectionResult(ConnectionInfoBean oldInfoBean, ConnectionInfoBean infoBean) {
        this.mListener.onCheckConnectionResult(oldInfoBean, infoBean);
    }

    public ConnectionInfoBean getLoadingHmiTag() {
        return this.mLoadingHmi.getTag();
    }

    public ConnectionInfoBean getWirelessHmiTag() {
        return this.mWirelessHmi.getTag();
    }

    public ConnectionInfoBean getSwitchConnectionHmiTag() {
        return this.mSwitchHmi.getTag();
    }

    public ConnectionInfoBean getNotOpenCarPlayTag() {
        return this.mSwitchClosedHmi.getTag();
    }
}
