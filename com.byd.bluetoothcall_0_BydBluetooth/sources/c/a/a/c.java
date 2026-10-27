package c.a.a;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: compiled from: UIListener_Connect.java */
/* JADX INFO: loaded from: classes.dex */
public interface c {
    void connectStateChange(BluetoothDevice bluetoothDevice, int i);

    void initFinished();

    void update_BatLevel(int i, BluetoothDevice bluetoothDevice);
}
