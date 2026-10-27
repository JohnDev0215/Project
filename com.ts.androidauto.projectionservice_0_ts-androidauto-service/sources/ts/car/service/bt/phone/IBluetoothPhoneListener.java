package ts.car.service.bt.phone;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IBluetoothPhoneListener {
    default void onAudioStateChanged(String address, int state) {
    }

    default void onBatteryLevelChanged(String address, int level) {
    }

    default void onCallsChanged(String address, CallItem call) {
    }

    default void onConnectionStateChanged(String address, int state) {
    }

    default void onCurrentCallsChanged(String address, List<CallItem> calls) {
    }

    default void onNetworkRoamingChanged(String address, int state) {
    }

    default void onNetworkSignalChanged(String address, int signal) {
    }

    default void onNetworkStateChanged(String address, int state) {
    }

    default void onOperatorChanged(String address, String name) {
    }

    default void onVoiceRecognitionStateChanged(String address, int status) {
    }
}
