package b3;

import android.bluetooth.BluetoothDevice;
import com.byd.bt.model.ConnectState;

/* JADX INFO: compiled from: IBtProfileConnectCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface i extends a {
    void onConnectedChanged(int i8, BluetoothDevice bluetoothDevice, ConnectState connectState);

    default void onServiceConnected(int i8) {
    }

    default void onServiceDisconnected(int i8) {
    }
}
