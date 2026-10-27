package ts.car.service.dcm;

import android.net.wifi.WifiConfiguration;
import ts.car.service.dcm.data.AbnormalDevice;
import ts.car.service.dcm.data.ConnectionDevice;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceConnectionListener {
    default void onDeviceAccepted(ConnectionDevice device) {
    }

    default void onDeviceStatusChanged(ConnectionDevice device) {
    }

    default void onDevicesMerged(String oneDevId, String anotherDevId, ConnectionDevice device) {
    }

    default void onBtProfileChanged(String mac, int profile, int connstate) {
    }

    default void onDeviceFriendlyNameUpdated(ConnectionDevice device, String name) {
    }

    default void onProjectionServiceStateChanged(ConnectionDevice device, int capa, int status) {
    }

    default void onProjectionServiceStateChanged(ConnectionDevice device, int capa, int status, int reason) {
    }

    default void onMessageSend(int msgId, String msg) {
    }

    default void onProjectionServiceRequested(ConnectionDevice device) {
    }

    default void onProjectionRequested(ConnectionDevice device, int capa) {
    }

    default void onWirelessCarplayDetected(String mac, int status) {
    }

    default void onWirelessProjectionFound(ConnectionDevice device) {
    }

    default void onWifiApInfoChanged(WifiConfiguration config, int status) {
    }

    default void onDeviceAttach(ConnectionDevice device, int connType) {
    }

    default void onDeviceDetach(ConnectionDevice device, int connType) {
    }

    default void onAbnormalDeviceStatusChanged(AbnormalDevice device, int status) {
    }

    default void onDeviceCapabilityChecked(String devId, int capability, boolean isSupport, int reason) {
    }

    default void onBtOobPairCompleted(String mac, int status) {
    }

    default void onProjectionServiceStatus(int serviceType, boolean status) {
    }
}
