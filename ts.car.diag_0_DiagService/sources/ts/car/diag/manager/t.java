package ts.car.diag.manager;

import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static t f1631a;

    public static synchronized t f() {
        if (f1631a == null) {
            f1631a = new t();
        }
        return f1631a;
    }

    public byte a() {
        q.b("MainSecondaryBluetoothManager", "checkConnectResult has not bt ");
        return (byte) -1;
    }

    public ArrayList<Byte> b() {
        q.b("MainSecondaryBluetoothManager", "checkDiscoveryResult has not bt ");
        return ts.car.diag.d.a.a(null);
    }

    public byte c(String str) {
        q.b("MainSecondaryBluetoothManager", "discoveryBluetooth has not bt ");
        return (byte) -1;
    }

    public byte d() {
        q.b("MainSecondaryBluetoothManager", "getSecondaryBtConnectionStatus has not bt ");
        return (byte) 0;
    }

    public byte e() {
        q.b("MainSecondaryBluetoothManager", "Secondary_BT_OnOff_off has not bt");
        return (byte) -1;
    }

    public String g() {
        q.b("MainSecondaryBluetoothManager", "getLocalMac has not bt ");
        return "                 ";
    }

    public void h(Context context) {
    }

    public byte i(String str) {
        q.b("MainSecondaryBluetoothManager", "secondaryBluetoothAutoPairing has not bt ");
        return (byte) -1;
    }

    public boolean j(Byte b2) {
        q.b("MainSecondaryBluetoothManager", "setBtOnOff has not bt ");
        return true;
    }
}
