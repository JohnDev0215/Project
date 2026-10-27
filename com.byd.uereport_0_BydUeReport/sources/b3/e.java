package b3;

import android.bluetooth.BluetoothDevice;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.ConnectState;

/* JADX INFO: compiled from: IBtConnectCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends a {
    default void onConnectFailed(BluetoothDevice bluetoothDevice) {
    }

    void onConnectStateChanged(BluetoothDevice bluetoothDevice, ConnectState connectState);

    default void onConnectedBtNameChanged(BTDevice bTDevice) {
    }
}
