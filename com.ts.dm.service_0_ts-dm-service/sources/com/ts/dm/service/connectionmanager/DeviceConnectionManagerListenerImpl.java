package com.ts.dm.service.connectionmanager;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.service.DeviceManagerService;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeviceConnectionManagerListenerImpl implements IDeviceConnectionManagerListener {
    private static final String TAG = "DeviceConnectionManagerListenerImpl";
    private Context mContext;
    private DeviceConnectionManager mDeviceConnectionManager;
    private Handler mHandler;
    private IConnectionPopHmiListener mIConnectionPopHmiListener;

    public interface IConnectionPopHmiListener {
        ConnectionInfoBean getLoadingTag();

        ConnectionInfoBean getNotOpenCarPlayTag();

        ConnectionInfoBean getSwitchConnectionTag();

        ConnectionInfoBean getWirelessTag();

        void onHideFailedPop(ConnectionInfoBean connectionInfoBean);

        void onHideLoading(ConnectionInfoBean connectionInfoBean);

        void onHideNotOpenCarPlayPrompt(ConnectionInfoBean connectionInfoBean);

        void onHideSwitchConnectionPrompt(ConnectionInfoBean connectionInfoBean);

        void onHideWirelessPrompt(ConnectionInfoBean connectionInfoBean);

        void onShowConnectFailedPrompt(ConnectionInfoBean connectionInfoBean);

        void onShowLoading(ConnectionInfoBean connectionInfoBean);

        void onShowNotOpenCarPlayPrompt(ConnectionInfoBean connectionInfoBean);

        void onShowSwitchConnectionPrompt(ConnectionInfoBean connectionInfoBean);

        void onShowWirelessPrompt(ConnectionInfoBean connectionInfoBean);

        void onUpdateLoading(ConnectionInfoBean connectionInfoBean);
    }

    public DeviceConnectionManagerListenerImpl(Context context, DeviceManagerService.DeviceManagerBinder controller, IConnectionPopHmiListener listener) {
        LogUtil.info(TAG, "create");
        this.mContext = context;
        this.mIConnectionPopHmiListener = listener;
        DeviceConnectionManager deviceConnectionManager = new DeviceConnectionManager(controller);
        this.mDeviceConnectionManager = deviceConnectionManager;
        deviceConnectionManager.registerListener(this);
        initHandler();
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    @Override // com.ts.dm.service.connectionmanager.IDeviceConnectionManagerListener
    public void onDeviceChanged(final ConnectionInfoBean infoBean) {
        if (this.mIConnectionPopHmiListener == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.service.connectionmanager.-$$Lambda$DeviceConnectionManagerListenerImpl$Bco6gScnZwZwWOUvrBVU7RJXeL0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeviceChanged$0$DeviceConnectionManagerListenerImpl(infoBean);
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceChanged$0$DeviceConnectionManagerListenerImpl(ConnectionInfoBean infoBean) {
        checkConnectionStateInvalid(infoBean);
        checkConnectionStateChanged(this.mIConnectionPopHmiListener.getLoadingTag(), infoBean);
    }

    @Override // com.ts.dm.service.connectionmanager.IDeviceConnectionManagerListener
    public void onDeviceDetached(final ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "onDeviceDetached infoBean: " + infoBean);
        if (this.mIConnectionPopHmiListener == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.service.connectionmanager.-$$Lambda$DeviceConnectionManagerListenerImpl$KjOc2D8jusc7L3r6DuQLpeYPibE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeviceDetached$1$DeviceConnectionManagerListenerImpl(infoBean);
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceDetached$1$DeviceConnectionManagerListenerImpl(ConnectionInfoBean infoBean) {
        this.mIConnectionPopHmiListener.onHideSwitchConnectionPrompt(infoBean);
        hideLoading(infoBean);
        this.mIConnectionPopHmiListener.onHideNotOpenCarPlayPrompt(infoBean);
        this.mIConnectionPopHmiListener.onHideWirelessPrompt(infoBean);
    }

    @Override // com.ts.dm.service.connectionmanager.IDeviceConnectionManagerListener
    public void onDeviceProjectionRequested(final boolean supported, final ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "deviceProjectionRequested supported: " + supported + ", infoBean: " + infoBean);
        if (this.mIConnectionPopHmiListener == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.service.connectionmanager.-$$Lambda$DeviceConnectionManagerListenerImpl$iYMfsLF2NxOUZrdbRfw8qByj8SM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeviceProjectionRequested$2$DeviceConnectionManagerListenerImpl(supported, infoBean);
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceProjectionRequested$2$DeviceConnectionManagerListenerImpl(boolean supported, ConnectionInfoBean infoBean) {
        if (!supported) {
            this.mIConnectionPopHmiListener.onShowNotOpenCarPlayPrompt(infoBean);
        } else if (infoBean.getConnectRequest().getConnectionType() != 1 && infoBean.getConnectRequest().getConnectionType() == 2) {
            connectProjectionForWireless(infoBean);
        }
    }

    private void checkConnectionStateInvalid(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "checkConnectionStateInvalid infoBean: " + infoBean);
        ConnectionInfoBean switchConnectionTag = this.mIConnectionPopHmiListener.getSwitchConnectionTag();
        if (switchConnectionTag != null && switchConnectionTag.getConnectRequest() != null && this.mDeviceConnectionManager.isCapabilityInvalid(switchConnectionTag.getConnectRequest().getCapabilityType(), infoBean)) {
            this.mIConnectionPopHmiListener.onHideSwitchConnectionPrompt(infoBean);
        }
        ConnectionInfoBean wirelessTag = this.mIConnectionPopHmiListener.getWirelessTag();
        if (wirelessTag != null && wirelessTag.getConnectRequest() != null && this.mDeviceConnectionManager.isCapabilityInvalid(wirelessTag.getConnectRequest().getCapabilityType(), infoBean)) {
            this.mIConnectionPopHmiListener.onHideWirelessPrompt(infoBean);
        }
        ConnectionInfoBean loadingTag = this.mIConnectionPopHmiListener.getLoadingTag();
        if (loadingTag != null && loadingTag.getConnectRequest() != null && this.mDeviceConnectionManager.isCapabilityInvalid(loadingTag.getConnectRequest().getCapabilityType(), infoBean)) {
            hideLoading(infoBean);
        }
    }

    private void checkConnectionStateChanged(ConnectionInfoBean infoBean, ConnectionInfoBean newInfoBean) {
        LogUtil.info(TAG, "checkConnectionStateChanged infoBean: " + infoBean + ", newInfoBean: " + newInfoBean);
        if (newInfoBean != null && newInfoBean.getConnectRequest().getDevice().isConnectingProjection()) {
            hideFailedPop(newInfoBean);
        }
        if (newInfoBean != null && newInfoBean.getConnectRequest().getDevice().isConnectingProjection()) {
            this.mIConnectionPopHmiListener.onHideWirelessPrompt(newInfoBean);
        }
        if (infoBean == null || newInfoBean == null || !infoBean.getConnectRequest().getDevice().equals(newInfoBean.getConnectRequest().getDevice())) {
            return;
        }
        ConnectionInfoBean newInfoBean2 = updateDeviceConnectInfo(infoBean, newInfoBean);
        boolean isStateChanged = this.mDeviceConnectionManager.isStateChanged(infoBean, newInfoBean2);
        if (!isStateChanged) {
            return;
        }
        boolean isTransitionState = this.mDeviceConnectionManager.isTransitionState(infoBean, newInfoBean2);
        boolean isLoadingTransitionState = this.mDeviceConnectionManager.isTransitionState(infoBean, infoBean);
        if (isLoadingTransitionState && isTransitionState) {
            hideLoading(newInfoBean2);
        } else if (isTransitionState) {
            updateLoading(newInfoBean2);
        } else if (isLoadingTransitionState) {
            hideLoading(newInfoBean2);
        }
    }

    public ConnectionInfoBean updateDeviceConnectInfo(ConnectionInfoBean infoBean, ConnectionInfoBean newInfoBean) {
        ConnectRequest newRequest = newInfoBean.getConnectRequest();
        newRequest.setConnectType(infoBean.getConnectRequest().getConnectionType());
        newRequest.setCapabilityType(infoBean.getConnectRequest().getCapabilityType());
        newInfoBean.setConnectRequest(newRequest);
        return newInfoBean;
    }

    private void connectProjectionForUsb(ConnectionInfoBean infoBean) {
        ConnectionInfoBean hideWirelessConnectionInfo = infoBean.copy();
        hideWirelessConnectionInfo.setIgnoreCheckSameDevice(true);
        this.mIConnectionPopHmiListener.onHideWirelessPrompt(hideWirelessConnectionInfo);
        List<DmDevice> list = this.mDeviceConnectionManager.getOnlineDeviceList();
        if (list != null && !list.isEmpty()) {
            DmDevice onlineDevice = list.get(0);
            LogUtil.info(TAG, "connectProjectionForUsb onlineDeviceList size: " + list.size() + ", onlineDevice: " + onlineDevice);
            if (onlineDevice.equals(infoBean.getConnectRequest().getDevice())) {
                return;
            }
            promptSwitchConnection(infoBean, onlineDevice);
            return;
        }
        connectProjection(infoBean);
    }

    private void connectProjectionForWireless(ConnectionInfoBean infoBean) {
        if (infoBean.getConnectRequest().getCapabilityType() != 2) {
            return;
        }
        this.mIConnectionPopHmiListener.onShowWirelessPrompt(infoBean);
    }

    public void connectProjection(final ConnectionInfoBean infoBean) {
        DeviceConnectionManager deviceConnectionManager = this.mDeviceConnectionManager;
        if (deviceConnectionManager == null) {
            return;
        }
        boolean ret = deviceConnectionManager.connect(infoBean);
        if (ret) {
            if (Looper.myLooper() != null && this.mHandler.getLooper() == Looper.myLooper()) {
                lambda$connectProjection$3$DeviceConnectionManagerListenerImpl(infoBean);
            } else {
                this.mHandler.post(new Runnable() { // from class: com.ts.dm.service.connectionmanager.-$$Lambda$DeviceConnectionManagerListenerImpl$mwCZwc1Jf1hYZinAC_PlnclmUi8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$connectProjection$3$DeviceConnectionManagerListenerImpl(infoBean);
                    }
                });
            }
        }
    }

    private void promptSwitchConnection(ConnectionInfoBean infoBean, DmDevice onlineDevice) {
        infoBean.setOnlineDevice(onlineDevice);
        this.mIConnectionPopHmiListener.onShowSwitchConnectionPrompt(infoBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showLoading, reason: merged with bridge method [inline-methods] */
    public void lambda$connectProjection$3$DeviceConnectionManagerListenerImpl(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "showLoading infoBean:" + infoBean);
        this.mIConnectionPopHmiListener.onShowLoading(infoBean);
    }

    private void updateLoading(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "updateLoading infoBean:" + infoBean);
        this.mIConnectionPopHmiListener.onUpdateLoading(infoBean);
    }

    private void hideLoading(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "hideLoading infoBean:" + infoBean);
        this.mIConnectionPopHmiListener.onHideLoading(infoBean);
    }

    private void hideFailedPop(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "hideFailedPop infoBean:" + infoBean);
        this.mIConnectionPopHmiListener.onHideFailedPop(infoBean);
    }

    public void checkConnectionResult(ConnectionInfoBean infoBean, ConnectionInfoBean newInfoBean) {
        ConnectionInfoBean newInfoBean2 = updateDeviceConnectInfo(infoBean, newInfoBean);
        if (infoBean.getConnectRequest().getCapabilityType() == 2) {
            return;
        }
        boolean isConnected = newInfoBean2.getConnectRequest().getDevice().isCapabilityConnected(infoBean.getConnectRequest().getCapabilityType());
        if (isConnected) {
            return;
        }
        boolean isLoadingTransitionState = this.mDeviceConnectionManager.isTransitionState(infoBean, infoBean);
        boolean isTransitionState = this.mDeviceConnectionManager.isTransitionState(infoBean, newInfoBean2);
        boolean isStateChanged = this.mDeviceConnectionManager.isStateChanged(infoBean, newInfoBean2);
        if ((isTransitionState && !isStateChanged) || !isLoadingTransitionState) {
            return;
        }
        this.mIConnectionPopHmiListener.onShowConnectFailedPrompt(newInfoBean2);
    }
}
