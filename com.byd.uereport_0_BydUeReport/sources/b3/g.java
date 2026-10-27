package b3;

import android.bluetooth.BluetoothDevice;
import com.byd.bt.model.BTDevice;

/* JADX INFO: compiled from: IBtFoundCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface g extends a {
    default void onDeviceFound(BTDevice bTDevice) {
    }

    default void onDiscoveryFinished() {
    }

    default void onTargetNotFound(BluetoothDevice bluetoothDevice) {
    }
}
