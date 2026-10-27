package org.chromium.device.bluetooth;

import J.N;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import android.util.Log;
import android.util.SparseArray;
import defpackage.fa1;
import defpackage.gq2;
import defpackage.ph1;
import defpackage.vd1;
import defpackage.xz;
import defpackage.yt3;
import java.util.List;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeBluetoothAdapter extends BroadcastReceiver {
    public long a;
    public final Wrappers$BluetoothAdapterWrapper b;
    public a c;

    public ChromeBluetoothAdapter(long j, Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper) {
        this.a = j;
        this.b = wrappers$BluetoothAdapterWrapper;
        if (wrappers$BluetoothAdapterWrapper != null) {
            xz.g(wrappers$BluetoothAdapterWrapper.b, this, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        }
        if (wrappers$BluetoothAdapterWrapper == null) {
            Log.i("cr_Bluetooth", "ChromeBluetoothAdapter created with no adapterWrapper.");
        } else {
            Log.i("cr_Bluetooth", "ChromeBluetoothAdapter created with provided adapterWrapper.");
        }
    }

    @CalledByNative
    public static ChromeBluetoothAdapter create(long j, Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper) {
        return new ChromeBluetoothAdapter(j, wrappers$BluetoothAdapterWrapper);
    }

    @CalledByNative
    public final String getAddress() {
        return isPresent() ? this.b.a.getAddress() : "";
    }

    @CalledByNative
    public final String getName() {
        return isPresent() ? this.b.a.getName() : "";
    }

    @CalledByNative
    public final boolean isDiscoverable() {
        return isPresent() && this.b.a.getScanMode() == 23;
    }

    @CalledByNative
    public final boolean isDiscovering() {
        return isPresent() && (this.b.a.isDiscovering() || this.c != null);
    }

    @CalledByNative
    public final boolean isPowered() {
        return isPresent() && this.b.a.isEnabled();
    }

    @CalledByNative
    public final boolean isPresent() {
        return this.b != null;
    }

    @CalledByNative
    public final void onBluetoothAdapterAndroidDestruction() {
        stopScan();
        this.a = 0L;
        Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper = this.b;
        if (wrappers$BluetoothAdapterWrapper != null) {
            wrappers$BluetoothAdapterWrapper.b.unregisterReceiver(this);
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String strE;
        String action = intent.getAction();
        if (isPresent() && "android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            switch (intExtra) {
                case 10:
                    strE = "STATE_OFF";
                    break;
                case 11:
                    strE = "STATE_TURNING_ON";
                    break;
                case 12:
                    strE = "STATE_ON";
                    break;
                case 13:
                    strE = "STATE_TURNING_OFF";
                    break;
                default:
                    strE = gq2.e("illegal state: ", intExtra);
                    break;
            }
            fa1.k("Bluetooth", "onReceive: BluetoothAdapter.ACTION_STATE_CHANGED: %s", strE);
            if (intExtra == 10) {
                N.MGGbKqrZ(this.a, this, false);
            } else {
                if (intExtra != 12) {
                    return;
                }
                N.MGGbKqrZ(this.a, this, true);
            }
        }
    }

    @CalledByNative
    public final boolean setPowered(boolean z) {
        Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper = this.b;
        return z ? isPresent() && wrappers$BluetoothAdapterWrapper.a.enable() : isPresent() && wrappers$BluetoothAdapterWrapper.a.disable();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0052  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean startScan(java.util.List<android.bluetooth.le.ScanFilter> r9) {
        /*
            r8 = this;
            java.lang.String r0 = "cr_Bluetooth"
            org.chromium.device.bluetooth.Wrappers$BluetoothAdapterWrapper r1 = r8.b
            android.bluetooth.BluetoothAdapter r2 = r1.a
            android.bluetooth.le.BluetoothLeScanner r2 = r2.getBluetoothLeScanner()
            r3 = 0
            if (r2 != 0) goto Lf
            r2 = r3
            goto L1c
        Lf:
            yt3 r4 = r1.c
            if (r4 != 0) goto L1a
            yt3 r4 = new yt3
            r4.<init>(r2)
            r1.c = r4
        L1a:
            yt3 r2 = r1.c
        L1c:
            r4 = 0
            if (r2 != 0) goto L20
            return r4
        L20:
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 1
            r7 = 31
            android.content.Context r1 = r1.b
            if (r5 < r7) goto L3a
            java.lang.String r5 = "android.permission.BLUETOOTH_SCAN"
            int r5 = r1.checkCallingOrSelfPermission(r5)
            if (r5 != 0) goto L52
            java.lang.String r5 = "android.permission.BLUETOOTH_CONNECT"
            int r1 = r1.checkCallingOrSelfPermission(r5)
            if (r1 != 0) goto L52
            goto L50
        L3a:
            ba1 r5 = defpackage.ba1.a()
            r5.getClass()
            boolean r5 = defpackage.ba1.b()
            if (r5 != 0) goto L48
            goto L52
        L48:
            java.lang.String r5 = "android.permission.ACCESS_FINE_LOCATION"
            int r1 = r1.checkCallingOrSelfPermission(r5)
            if (r1 != 0) goto L52
        L50:
            r1 = r6
            goto L53
        L52:
            r1 = r4
        L53:
            if (r1 != 0) goto L56
            return r4
        L56:
            org.chromium.device.bluetooth.ChromeBluetoothAdapter$a r1 = new org.chromium.device.bluetooth.ChromeBluetoothAdapter$a
            r1.<init>()
            r8.c = r1
            r2.a(r9, r1)     // Catch: java.lang.IllegalStateException -> L61 java.lang.IllegalArgumentException -> L76
            return r6
        L61:
            r9 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Adapter is off. Cannot start scan: "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            android.util.Log.e(r0, r9)
            r8.c = r3
            return r4
        L76:
            r9 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Cannot start scan: "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            android.util.Log.e(r0, r9)
            r8.c = r3
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.device.bluetooth.ChromeBluetoothAdapter.startScan(java.util.List):boolean");
    }

    @CalledByNative
    public final boolean stopScan() {
        yt3 yt3Var;
        if (this.c == null) {
            return false;
        }
        try {
            Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper = this.b;
            BluetoothLeScanner bluetoothLeScanner = wrappers$BluetoothAdapterWrapper.a.getBluetoothLeScanner();
            if (bluetoothLeScanner == null) {
                yt3Var = null;
            } else {
                if (wrappers$BluetoothAdapterWrapper.c == null) {
                    wrappers$BluetoothAdapterWrapper.c = new yt3(bluetoothLeScanner);
                }
                yt3Var = wrappers$BluetoothAdapterWrapper.c;
            }
            if (yt3Var != null) {
                yt3Var.a.stopScan(yt3Var.b.remove(this.c));
            }
        } catch (IllegalArgumentException e) {
            Log.e("cr_Bluetooth", "Cannot stop scan: " + e);
        } catch (IllegalStateException e2) {
            Log.e("cr_Bluetooth", "Adapter is off. Cannot stop scan: " + e2);
        }
        this.c = null;
        return true;
    }

    public class a extends vd1 {
        public a() {
        }

        @Override // defpackage.vd1
        public final void A(ph1 ph1Var) {
            String[] strArr;
            String[] strArr2;
            byte[][] bArr;
            a aVar;
            int[] iArr;
            byte[][] bArr2;
            ph1Var.c().a.getAddress();
            ph1Var.c().a.getName();
            List<ParcelUuid> serviceUuids = ((ScanResult) ph1Var.e).getScanRecord().getServiceUuids();
            if (serviceUuids == null) {
                strArr = new String[0];
            } else {
                String[] strArr3 = new String[serviceUuids.size()];
                for (int i = 0; i < serviceUuids.size(); i++) {
                    strArr3[i] = serviceUuids.get(i).toString();
                }
                strArr = strArr3;
            }
            Map<ParcelUuid, byte[]> serviceData = ((ScanResult) ph1Var.e).getScanRecord().getServiceData();
            if (serviceData == null) {
                strArr2 = new String[0];
                bArr = new byte[0][];
            } else {
                String[] strArr4 = new String[serviceData.size()];
                byte[][] bArr3 = new byte[serviceData.size()][];
                int i2 = 0;
                for (Map.Entry<ParcelUuid, byte[]> entry : serviceData.entrySet()) {
                    strArr4[i2] = entry.getKey().toString();
                    bArr3[i2] = entry.getValue();
                    i2++;
                }
                strArr2 = strArr4;
                bArr = bArr3;
            }
            SparseArray<byte[]> manufacturerSpecificData = ((ScanResult) ph1Var.e).getScanRecord().getManufacturerSpecificData();
            if (manufacturerSpecificData == null) {
                iArr = new int[0];
                bArr2 = new byte[0][];
                aVar = this;
            } else {
                int[] iArr2 = new int[manufacturerSpecificData.size()];
                byte[][] bArr4 = new byte[manufacturerSpecificData.size()][];
                for (int i3 = 0; i3 < manufacturerSpecificData.size(); i3++) {
                    iArr2[i3] = manufacturerSpecificData.keyAt(i3);
                    bArr4[i3] = manufacturerSpecificData.valueAt(i3);
                }
                aVar = this;
                iArr = iArr2;
                bArr2 = bArr4;
            }
            ChromeBluetoothAdapter chromeBluetoothAdapter = ChromeBluetoothAdapter.this;
            long j = chromeBluetoothAdapter.a;
            if (j != 0) {
                N.MOuw3NGo(j, chromeBluetoothAdapter, ph1Var.c().a.getAddress(), ph1Var.c(), ((ScanResult) ph1Var.e).getScanRecord().getDeviceName(), ((ScanResult) ph1Var.e).getRssi(), strArr, ((ScanResult) ph1Var.e).getScanRecord().getTxPowerLevel(), strArr2, bArr, iArr, bArr2, ((ScanResult) ph1Var.e).getScanRecord().getAdvertiseFlags());
            }
        }

        @Override // defpackage.vd1
        public final void z(int i) {
            fa1.k("Bluetooth", "onScanFailed: %d", Integer.valueOf(i));
            ChromeBluetoothAdapter chromeBluetoothAdapter = ChromeBluetoothAdapter.this;
            N.Mq3WoOFf(chromeBluetoothAdapter.a, chromeBluetoothAdapter);
        }

        @Override // defpackage.vd1
        public final void j() {
        }
    }
}
