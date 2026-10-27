package com.ts.dm.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.ts.dm.service.DeviceManagerService;
import com.ts.dm.service.connectionhmi.HmiImpl;
import com.ts.dm.service.connectionhmi.IHmiListener;
import com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionPopupHmi implements DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener, IHmiListener {
    private static final String PARAMENTER_CONNECTIONINFO = "connectionInfo";
    private static final String TAG = "ConnectionPopupHmi";
    public static final int VIEW_HIDE_FAILED_POP = 11;
    public static final int VIEW_HIDE_LOADING = 9;
    public static final int VIEW_HIDE_NOTOPENCARPLAY = 4;
    public static final int VIEW_HIDE_SWITCHCONNECTION = 2;
    public static final int VIEW_HIDE_WIRELESSANDROIDAUTO = 6;
    public static final int VIEW_SHOW_CONNECTIONFAILED = 7;
    public static final int VIEW_SHOW_LOADING = 8;
    public static final int VIEW_SHOW_NOTOPENCARPLAY = 3;
    public static final int VIEW_SHOW_SWITCHCONNECTION = 1;
    public static final int VIEW_SHOW_WIRELESSANDROIDAUTO = 5;
    public static final int VIEW_UPDATE_LOADING = 10;
    private static volatile ConnectionPopupHmi mConnectionPopupHmi;
    private DeviceConnectionManagerListenerImpl mDeviceConnectionManagerListenerImpl;
    private HmiHandler mHmiHandler;
    private HmiImpl mHmiImpl;

    public static ConnectionPopupHmi getInstance() {
        LogUtil.info(TAG, "getInstance");
        if (mConnectionPopupHmi == null) {
            synchronized (ConnectionPopupHmi.class) {
                if (mConnectionPopupHmi == null) {
                    mConnectionPopupHmi = new ConnectionPopupHmi();
                }
            }
        }
        return mConnectionPopupHmi;
    }

    private ConnectionPopupHmi() {
        LogUtil.info(TAG, "create");
        this.mHmiHandler = new HmiHandler();
    }

    public void init(Context context, DeviceManagerService.DeviceManagerBinder binder) {
        LogUtil.info(TAG, "init");
        this.mHmiImpl = new HmiImpl(context, this);
        this.mDeviceConnectionManagerListenerImpl = new DeviceConnectionManagerListenerImpl(context, binder, this);
    }

    private void updateHmi(ConnectionInfoBean info, int type) {
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onShowLoading(ConnectionInfoBean info) {
        updateHmi(info, 8);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onShowWirelessPrompt(ConnectionInfoBean info) {
        updateHmi(info, 5);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onShowSwitchConnectionPrompt(ConnectionInfoBean info) {
        updateHmi(info, 1);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onShowNotOpenCarPlayPrompt(ConnectionInfoBean info) {
        updateHmi(info, 3);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onShowConnectFailedPrompt(ConnectionInfoBean info) {
        updateHmi(info, 7);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onHideLoading(ConnectionInfoBean info) {
        updateHmi(info, 9);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onHideWirelessPrompt(ConnectionInfoBean info) {
        updateHmi(info, 6);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onHideSwitchConnectionPrompt(ConnectionInfoBean info) {
        updateHmi(info, 2);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onHideNotOpenCarPlayPrompt(ConnectionInfoBean info) {
        updateHmi(info, 4);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onHideFailedPop(ConnectionInfoBean info) {
        updateHmi(info, 11);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public void onUpdateLoading(ConnectionInfoBean info) {
        updateHmi(info, 10);
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public ConnectionInfoBean getLoadingTag() {
        HmiImpl hmiImpl = this.mHmiImpl;
        if (hmiImpl == null) {
            return null;
        }
        ConnectionInfoBean infoBean = hmiImpl.getLoadingHmiTag();
        LogUtil.debug(TAG, "getLoadingTag infoBean: " + infoBean);
        return infoBean;
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public ConnectionInfoBean getWirelessTag() {
        HmiImpl hmiImpl = this.mHmiImpl;
        if (hmiImpl == null) {
            return null;
        }
        ConnectionInfoBean infoBean = hmiImpl.getWirelessHmiTag();
        LogUtil.debug(TAG, "getWirelessTag infoBean: " + infoBean);
        return infoBean;
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public ConnectionInfoBean getSwitchConnectionTag() {
        HmiImpl hmiImpl = this.mHmiImpl;
        if (hmiImpl == null) {
            return null;
        }
        ConnectionInfoBean infoBean = hmiImpl.getSwitchConnectionHmiTag();
        LogUtil.debug(TAG, "getSwitchConnectionTag infoBean: " + infoBean);
        return infoBean;
    }

    @Override // com.ts.dm.service.connectionmanager.DeviceConnectionManagerListenerImpl.IConnectionPopHmiListener
    public ConnectionInfoBean getNotOpenCarPlayTag() {
        HmiImpl hmiImpl = this.mHmiImpl;
        if (hmiImpl == null) {
            return null;
        }
        ConnectionInfoBean infoBean = hmiImpl.getNotOpenCarPlayTag();
        LogUtil.debug(TAG, "getNotOpenCarPlayTag infoBean: " + infoBean);
        return infoBean;
    }

    @Override // com.ts.dm.service.connectionhmi.IHmiListener
    public void onConnect(ConnectionInfoBean info) {
        DeviceConnectionManagerListenerImpl deviceConnectionManagerListenerImpl = this.mDeviceConnectionManagerListenerImpl;
        if (deviceConnectionManagerListenerImpl == null) {
            return;
        }
        deviceConnectionManagerListenerImpl.connectProjection(info);
    }

    @Override // com.ts.dm.service.connectionhmi.IHmiListener
    public void onCheckConnectionResult(ConnectionInfoBean oldInfoBean, ConnectionInfoBean info) {
        LogUtil.info(TAG, "onCheckConnectionResult info: " + info);
        DeviceConnectionManagerListenerImpl deviceConnectionManagerListenerImpl = this.mDeviceConnectionManagerListenerImpl;
        if (deviceConnectionManagerListenerImpl == null) {
            return;
        }
        deviceConnectionManagerListenerImpl.checkConnectionResult(oldInfoBean, info);
    }

    private class HmiHandler extends Handler {
        private HmiHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LogUtil.info(ConnectionPopupHmi.TAG, "handleMessage msg: " + msg);
            if (ConnectionPopupHmi.this.mHmiImpl == null) {
                return;
            }
            ConnectionInfoBean info = (ConnectionInfoBean) msg.getData().getSerializable(ConnectionPopupHmi.PARAMENTER_CONNECTIONINFO);
            ConnectionPopupHmi.this.mHmiImpl.changeHmi(msg.what, info);
        }
    }
}
