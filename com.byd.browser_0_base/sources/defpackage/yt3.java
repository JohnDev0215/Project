package defpackage;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import java.util.HashMap;
import java.util.List;
import org.chromium.device.bluetooth.ChromeBluetoothAdapter;

/* JADX INFO: compiled from: Wrappers.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yt3 {
    public final BluetoothLeScanner a;
    public final HashMap<vd1, au3> b = new HashMap<>();

    public yt3(BluetoothLeScanner bluetoothLeScanner) {
        this.a = bluetoothLeScanner;
    }

    public final void a(List list, ChromeBluetoothAdapter.a aVar) {
        ScanSettings scanSettingsBuild = new ScanSettings.Builder().setScanMode(2).build();
        au3 au3Var = new au3(aVar);
        this.b.put(aVar, au3Var);
        this.a.startScan((List<ScanFilter>) list, scanSettingsBuild, au3Var);
    }
}
