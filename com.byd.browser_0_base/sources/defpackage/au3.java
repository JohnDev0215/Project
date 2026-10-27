package defpackage;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.device.bluetooth.ChromeBluetoothAdapter;

/* JADX INFO: compiled from: Wrappers.java */
/* JADX INFO: loaded from: classes3.dex */
public final class au3 extends ScanCallback {
    public final vd1 a;

    public au3(ChromeBluetoothAdapter.a aVar) {
        this.a = aVar;
    }

    @Override // android.bluetooth.le.ScanCallback
    public final void onBatchScanResults(List<ScanResult> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<ScanResult> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ph1(it.next()));
        }
        this.a.j();
    }

    @Override // android.bluetooth.le.ScanCallback
    public final void onScanFailed(int i) {
        this.a.z(i);
    }

    @Override // android.bluetooth.le.ScanCallback
    public final void onScanResult(int i, ScanResult scanResult) {
        this.a.A(new ph1(scanResult));
    }
}
