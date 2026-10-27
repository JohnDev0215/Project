package ts.car.dcm.fsdk.interfaces;

import android.net.wifi.WifiConfiguration;
import com.ts.car.core.annotation.FoundationInterface;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;

/* JADX INFO: loaded from: classes.dex */
@FoundationInterface
public interface IProjectionConnectionListener {
    default void connectProjection(CmDevice device, ProjectionLinkParameter plp) {
    }

    default void disconnectProjection(String devId, int capa) {
    }

    default void disconnectProjection(String devId, int capa, int reason) {
    }

    default void deleteDevice(String devId, int srvType) {
    }

    default void showProjection(boolean flg, int srvType) {
    }

    default void onDeviceConnectionUpdated(CmDevice device, int connType, int reason, int srvType) {
    }

    default void onUsbRoleSwitchStarting(String devId, int mode, int srvType) {
    }

    default void onUsbRoleSwitchFinished(CmDevice dev, int mode, boolean result, int srvType) {
    }

    default void onEnableUsbAoaStarting(CmDevice dev, int mode, int srvType) {
    }

    default void onEnableUsbAoaFinished(CmDevice dev, int mode, boolean result, int srvType) {
    }

    default void onResetUsbBusStarting(String devId, int srvType) {
    }

    default void onResetUsbFinished(String devId, boolean result, int srvType) {
    }

    @Deprecated
    default void onBtStateChanged(int state, int srvType) {
    }

    @Deprecated
    default void onBtConnected(String mac, int result, int srvType) {
    }

    @Deprecated
    default void onBtDisconnected(String mac, int result, int srvType) {
    }

    @Deprecated
    default void onBtProfileChanged(String mac, int profile, int state, int srvType) {
    }

    @Deprecated
    default void onOtherBtDevicesDisconnected(String mac, int result, int srvType) {
    }

    default void onIap2BtDeviceConnected(CmDevice device, int reason, int srvType) {
    }

    default void onBtOobPairCompleted(String mac, int status, int srvType) {
    }

    @Deprecated
    default void onWifiApInfoChanged(WifiConfiguration config, int status, int srvType) {
    }

    default void onWifiConnectStatusChanged(String mac, int status, int srvType) {
    }

    @Deprecated
    default void switchAaConnection(CmDevice device, CmDevice device2) {
    }

    @Deprecated
    default void switchConnection(CmDevice device, int capa) {
    }

    default void checkUsbDeviceName(CmDevice device, int srvType) {
    }

    default void disconnectIapSession(String devId, int srvType) {
    }
}
