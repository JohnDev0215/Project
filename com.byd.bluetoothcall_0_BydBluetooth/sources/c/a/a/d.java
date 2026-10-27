package c.a.a;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: compiled from: UIListener_Muisc.java */
/* JADX INFO: loaded from: classes.dex */
public interface d {
    void updateA2dpConnectState(BluetoothDevice bluetoothDevice, int i);

    void updateA2dpConnectStateShowToast(BluetoothDevice bluetoothDevice, int i);

    void updateBattery(int i);

    void updateCurrentTime(long j);

    void updatePlayState(int i);

    void updateTitle(String str, String str2, String str3, String str4);

    void updateTotalTime(long j);
}
