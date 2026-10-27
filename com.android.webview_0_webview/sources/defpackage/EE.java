package defpackage;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class EE {
    public final BluetoothLeScanner a;
    public final HashMap b = new HashMap();

    public EE(BluetoothLeScanner bluetoothLeScanner) {
        this.a = bluetoothLeScanner;
    }

    public void a(List list, int i, HE he) {
        ScanSettings scanSettingsBuild = new ScanSettings.Builder().setScanMode(i).build();
        GE ge = new GE(he);
        this.b.put(he, ge);
        this.a.startScan((List<ScanFilter>) list, scanSettingsBuild, ge);
    }
}
