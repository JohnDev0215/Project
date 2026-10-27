package defpackage;

import J.N;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.ParcelUuid;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.device.bluetooth.ChromeBluetoothAdapter;
import org.chromium.device.bluetooth.Wrappers$BluetoothDeviceWrapper;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GE extends ScanCallback {
    public final HE a;

    public GE(HE he) {
        this.a = he;
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onBatchScanResults(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new IE((ScanResult) it.next()));
        }
        Objects.requireNonNull((C1481sE) this.a);
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanFailed(int i) {
        C1481sE c1481sE = (C1481sE) this.a;
        Objects.requireNonNull(c1481sE);
        Ts.f("Bluetooth", "onScanFailed: %d", Integer.valueOf(i));
        ChromeBluetoothAdapter chromeBluetoothAdapter = c1481sE.a;
        N.Mq3WoOFf(chromeBluetoothAdapter.a, chromeBluetoothAdapter);
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, ScanResult scanResult) {
        String[] strArr;
        String[] strArr2;
        byte[][] bArr;
        int[] iArr;
        byte[][] bArr2;
        C1481sE c1481sE = (C1481sE) this.a;
        Objects.requireNonNull(c1481sE);
        new Wrappers$BluetoothDeviceWrapper(scanResult.getDevice()).a();
        new Wrappers$BluetoothDeviceWrapper(scanResult.getDevice()).a.getName();
        List<ParcelUuid> serviceUuids = scanResult.getScanRecord().getServiceUuids();
        if (serviceUuids == null) {
            strArr = new String[0];
        } else {
            String[] strArr3 = new String[serviceUuids.size()];
            for (int i2 = 0; i2 < serviceUuids.size(); i2++) {
                strArr3[i2] = serviceUuids.get(i2).toString();
            }
            strArr = strArr3;
        }
        Map<ParcelUuid, byte[]> serviceData = scanResult.getScanRecord().getServiceData();
        if (serviceData == null) {
            strArr2 = new String[0];
            bArr = new byte[0][];
        } else {
            String[] strArr4 = new String[serviceData.size()];
            byte[][] bArr3 = new byte[serviceData.size()][];
            int i3 = 0;
            for (Map.Entry<ParcelUuid, byte[]> entry : serviceData.entrySet()) {
                strArr4[i3] = entry.getKey().toString();
                bArr3[i3] = entry.getValue();
                i3++;
            }
            strArr2 = strArr4;
            bArr = bArr3;
        }
        SparseArray<byte[]> manufacturerSpecificData = scanResult.getScanRecord().getManufacturerSpecificData();
        if (manufacturerSpecificData == null) {
            iArr = new int[0];
            bArr2 = new byte[0][];
        } else {
            int[] iArr2 = new int[manufacturerSpecificData.size()];
            byte[][] bArr4 = new byte[manufacturerSpecificData.size()][];
            for (int i4 = 0; i4 < manufacturerSpecificData.size(); i4++) {
                iArr2[i4] = manufacturerSpecificData.keyAt(i4);
                bArr4[i4] = manufacturerSpecificData.valueAt(i4);
            }
            iArr = iArr2;
            bArr2 = bArr4;
        }
        ChromeBluetoothAdapter chromeBluetoothAdapter = c1481sE.a;
        long j = chromeBluetoothAdapter.a;
        if (j != 0) {
            N.MOuw3NGo(j, chromeBluetoothAdapter, new Wrappers$BluetoothDeviceWrapper(scanResult.getDevice()).a(), new Wrappers$BluetoothDeviceWrapper(scanResult.getDevice()), scanResult.getScanRecord().getDeviceName(), scanResult.getRssi(), strArr, scanResult.getScanRecord().getTxPowerLevel(), strArr2, bArr, iArr, bArr2);
        }
    }
}
