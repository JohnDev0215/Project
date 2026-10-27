package org.chromium.device.bluetooth;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import defpackage.C1481sE;
import defpackage.EE;
import defpackage.GE;
import defpackage.Mx;
import defpackage.Ts;
import defpackage.Z7;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeBluetoothAdapter extends BroadcastReceiver {
    public long a;
    public final Wrappers$BluetoothAdapterWrapper b;
    public C1481sE c;

    public ChromeBluetoothAdapter(long j, Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper) {
        this.a = j;
        this.b = wrappers$BluetoothAdapterWrapper;
        if (wrappers$BluetoothAdapterWrapper != null) {
            wrappers$BluetoothAdapterWrapper.b.registerReceiver(this, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        }
        if (wrappers$BluetoothAdapterWrapper == null) {
            Ts.d("Bluetooth", "ChromeBluetoothAdapter created with no adapterWrapper.", new Object[0]);
        } else {
            Ts.d("Bluetooth", "ChromeBluetoothAdapter created with provided adapterWrapper.", new Object[0]);
        }
    }

    public static ChromeBluetoothAdapter create(long j, Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper) {
        return new ChromeBluetoothAdapter(j, wrappers$BluetoothAdapterWrapper);
    }

    private String getAddress() {
        return isPresent() ? this.b.a.getAddress() : "";
    }

    private String getName() {
        return isPresent() ? this.b.a.getName() : "";
    }

    private boolean isDiscoverable() {
        return isPresent() && this.b.a.getScanMode() == 23;
    }

    private boolean isDiscovering() {
        return isPresent() && (this.b.a.isDiscovering() || this.c != null);
    }

    private boolean isPowered() {
        return isPresent() && this.b.a.isEnabled();
    }

    private boolean isPresent() {
        return this.b != null;
    }

    private void onBluetoothAdapterAndroidDestruction() {
        stopScan();
        this.a = 0L;
        Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper = this.b;
        if (wrappers$BluetoothAdapterWrapper != null) {
            wrappers$BluetoothAdapterWrapper.b.unregisterReceiver(this);
        }
    }

    private boolean setPowered(boolean z) {
        return z ? isPresent() && this.b.a.enable() : isPresent() && this.b.a.disable();
    }

    private boolean startScan(List list) {
        EE eeA = this.b.a();
        if (eeA == null) {
            return false;
        }
        Mx mxA = Mx.a();
        if (!(mxA.b() && mxA.d())) {
            return false;
        }
        C1481sE c1481sE = new C1481sE(this, null);
        this.c = c1481sE;
        try {
            eeA.a(list, 2, c1481sE);
            return true;
        } catch (IllegalArgumentException e) {
            Ts.a("Bluetooth", "Cannot start scan: " + e, new Object[0]);
            this.c = null;
            return false;
        } catch (IllegalStateException e2) {
            Ts.a("Bluetooth", "Adapter is off. Cannot start scan: " + e2, new Object[0]);
            this.c = null;
            return false;
        }
    }

    private boolean stopScan() {
        if (this.c == null) {
            return false;
        }
        try {
            EE eeA = this.b.a();
            if (eeA != null) {
                eeA.a.stopScan((GE) eeA.b.remove(this.c));
            }
        } catch (IllegalArgumentException e) {
            Ts.a("Bluetooth", "Cannot stop scan: " + e, new Object[0]);
        } catch (IllegalStateException e2) {
            Ts.a("Bluetooth", "Adapter is off. Cannot stop scan: " + e2, new Object[0]);
        }
        this.c = null;
        return true;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String strA;
        String action = intent.getAction();
        if (isPresent() && "android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            Object[] objArr = new Object[1];
            switch (intExtra) {
                case 10:
                    strA = "STATE_OFF";
                    break;
                case 11:
                    strA = "STATE_TURNING_ON";
                    break;
                case 12:
                    strA = "STATE_ON";
                    break;
                case 13:
                    strA = "STATE_TURNING_OFF";
                    break;
                default:
                    strA = Z7.a("illegal state: ", intExtra);
                    break;
            }
            objArr[0] = strA;
            Ts.f("Bluetooth", "onReceive: BluetoothAdapter.ACTION_STATE_CHANGED: %s", objArr);
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
}
