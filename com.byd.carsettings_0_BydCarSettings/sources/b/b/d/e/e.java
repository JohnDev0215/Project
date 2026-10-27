package b.b.d.e;

import android.bluetooth.BluetoothDevice;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.ConnectState;

/* JADX INFO: compiled from: IBtConnectCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends a {
    void k(BluetoothDevice bluetoothDevice, ConnectState connectState, boolean z);

    default void n(BTDevice bTDevice) {
    }
}
