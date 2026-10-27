package com.ecarx.xui.adaptapi.wpc.impl;

import android.content.Context;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.wpc.WPC;
import com.ecarx.xui.adaptapi.wpc.specific.WPCBean;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;

/* JADX INFO: loaded from: classes2.dex */
public class WPCImpl extends WPC {
    private Context mContext;
    private WPCBean mWPCBean;

    public WPCImpl(Context context) {
        this.mWPCBean = new WPCBean(context);
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.wpc.WPC
    public FunctionStatus isWPCSupported() {
        return 1 == OfflineConfig.getE01OfflineConfig(108) ? FunctionStatus.active : FunctionStatus.error;
    }

    @Override // com.ecarx.xui.adaptapi.wpc.WPC
    public int setWorkingMode(int i) {
        return this.mWPCBean.setWorkingMode(i);
    }

    @Override // com.ecarx.xui.adaptapi.wpc.WPC
    public int getWorkingMode() {
        return this.mWPCBean.getWorkingMode();
    }

    @Override // com.ecarx.xui.adaptapi.wpc.WPC
    public int getChargingStatus() {
        return this.mWPCBean.getChargingStatus();
    }

    @Override // com.ecarx.xui.adaptapi.wpc.WPC
    public void setStateListener(WPC.StateListener stateListener) {
        this.mWPCBean.setStateListener(stateListener);
    }
}
