package b3;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: compiled from: IBtBondStateChangedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends a {
    default void onBtBondFailed(BluetoothDevice bluetoothDevice, int i8) {
    }

    default void onBtBondNone(BluetoothDevice bluetoothDevice) {
    }

    default void onBtBonded(BluetoothDevice bluetoothDevice) {
    }

    default void onBtBonding(BluetoothDevice bluetoothDevice) {
    }

    default void onOobBonded(String str, String str2) {
    }
}
