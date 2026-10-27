package ts.car.diag.manager;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class GwBtManagerImpl extends i {
    private static volatile GwBtManagerImpl k = null;
    static boolean l = false;
    static CountDownTimer m = new b(10000, 5000);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private t f1584b;
    private String d;
    private BluetoothA2dpSink g;
    private BluetoothHeadsetClient h;
    private BluetoothProfile.ServiceListener i;
    private String j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BluetoothAdapter f1583a = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<String> f1585c = new ArrayList();
    public boolean e = false;
    private byte f = 0;

    /* JADX INFO: compiled from: caradapter-bug */
    private enum ConnectStates {
        disconnect,
        connecting,
        connected,
        pairing,
        paired
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class GwBtDeviceReciver extends BroadcastReceiver {
        private void a(GwBtManagerImpl gwBtManagerImpl, Intent intent) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice.getBondState() == 10) {
                gwBtManagerImpl.f = (byte) ConnectStates.disconnect.ordinal();
                q.h("GwBtManagerImpl", "getBondState : BluetoothDevice.BOND_NONE");
            } else if (bluetoothDevice.getBondState() == 12) {
                gwBtManagerImpl.f = (byte) ConnectStates.paired.ordinal();
                q.h("GwBtManagerImpl", "getBondState : BluetoothDevice.BOND_BONDED");
            } else if (bluetoothDevice.getBondState() == 11) {
                gwBtManagerImpl.f = (byte) ConnectStates.pairing.ordinal();
                q.h("GwBtManagerImpl", "getBondState : BluetoothDevice.BOND_BONDING");
            }
        }

        private void b(GwBtManagerImpl gwBtManagerImpl, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", -1);
            q.h("GwBtManagerImpl", "STATE_CHANGED " + intExtra);
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (intExtra == 2) {
                gwBtManagerImpl.f = (byte) ConnectStates.connected.ordinal();
                q.h("GwBtManagerImpl", "BluetoothAdapter.EXTRA_CONNECTION_STATE : BluetoothDevice.STATE_CONNECTED");
                return;
            }
            if (intExtra != 0) {
                if (intExtra == 1) {
                    gwBtManagerImpl.f = (byte) ConnectStates.connecting.ordinal();
                    q.h("GwBtManagerImpl", "BluetoothAdapter.EXTRA_CONNECTION_STATE : BluetoothDevice.STATE_CONNECTING");
                    return;
                }
                return;
            }
            q.h("GwBtManagerImpl", "BluetoothAdapter.EXTRA_CONNECTION_STATE : BluetoothDevice.STATE_DISCONNECTED");
            if (bluetoothDevice.getBondState() != 12) {
                gwBtManagerImpl.f = (byte) ConnectStates.disconnect.ordinal();
            } else {
                gwBtManagerImpl.f = (byte) ConnectStates.paired.ordinal();
                q.h("GwBtManagerImpl", "getBondState : BluetoothDevice.BOND_BONDED");
            }
        }

        private void c(Intent intent) {
            if (GwBtManagerImpl.l) {
                GwBtManagerImpl gwBtManagerImplW = GwBtManagerImpl.w();
                String action = intent.getAction();
                byte b2 = -1;
                int iHashCode = action.hashCode();
                if (iHashCode != -1780914469) {
                    if (iHashCode != 6759640) {
                        if (iHashCode == 1167529923 && action.equals("android.bluetooth.device.action.FOUND")) {
                            b2 = 1;
                        }
                    } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                        b2 = 0;
                    }
                } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                    b2 = 2;
                }
                if (b2 == 0) {
                    q.h("GwBtManagerImpl", "GwBTDeviceBluetoothAdapter.ACTION_DISCOVERY_STARTED");
                    gwBtManagerImplW.f1585c.clear();
                    GwBtManagerImpl.m.cancel();
                    GwBtManagerImpl.m.start();
                    return;
                }
                if (b2 != 1) {
                    if (b2 != 2) {
                        return;
                    }
                    GwBtManagerImpl.m.cancel();
                    q.h("GwBtManagerImpl", "GwBTDeviceBluetoothAdapter.ACTION_DISCOVERY_FINISHED");
                    gwBtManagerImplW.e = gwBtManagerImplW.x();
                    q.h("GwBtManagerImpl", "GwBtDeviceReciver  GwBtDeviceReciver :" + gwBtManagerImplW.e);
                    return;
                }
                GwBtManagerImpl.m.cancel();
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                q.i("GwBtManagerImpl", "GwBTDeviceBluetoothDevice.ACTION_FOUND name  " + bluetoothDevice.getName() + " address" + bluetoothDevice.getAddress());
                if (gwBtManagerImplW.f1585c.contains(bluetoothDevice.getAddress())) {
                    return;
                }
                gwBtManagerImplW.f1585c.add(bluetoothDevice.getAddress());
            }
        }

        private void d(Intent intent) {
            try {
                if (!GwBtManagerImpl.l) {
                    q.b("GwBtManagerImpl", "has not isCreateBond,return ");
                    return;
                }
                q.h("GwBtManagerImpl", "ACTION_PAIRING_REQUEST");
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
                int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", -1);
                String str = String.format(Locale.US, "%04d", Integer.valueOf(intExtra));
                q.i("GwBtManagerImpl", "pin is " + intExtra + "pinStr is " + str);
                if (!bluetoothDevice.setPin(str.getBytes())) {
                    q.h("GwBtManagerImpl", "setPin false");
                }
                if (!bluetoothDevice.setPairingConfirmation(true)) {
                    q.h("GwBtManagerImpl", "setPairingConfirmation false");
                }
                q.h("GwBtManagerImpl", "pairingRequest successfully");
            } catch (Exception e) {
                q.b("GwBtManagerImpl", "Throw ACTION_PAIRING_REQUEST Exception : " + e);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                ts.car.diag.manager.GwBtManagerImpl r6 = ts.car.diag.manager.GwBtManagerImpl.w()
                r5.c(r7)
                java.lang.String r0 = r7.getAction()
                int r1 = r0.hashCode()
                r2 = -223687943(0xfffffffff2aacaf9, float:-6.7658023E30)
                r3 = 2
                r4 = 1
                if (r1 == r2) goto L35
                r2 = 1123270207(0x42f3be3f, float:121.871574)
                if (r1 == r2) goto L2b
                r2 = 2116862345(0x7e2cc189, float:5.7408027E37)
                if (r1 == r2) goto L21
                goto L3f
            L21:
                java.lang.String r1 = "android.bluetooth.device.action.BOND_STATE_CHANGED"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L3f
                r0 = 0
                goto L40
            L2b:
                java.lang.String r1 = "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L3f
                r0 = r4
                goto L40
            L35:
                java.lang.String r1 = "android.bluetooth.device.action.PAIRING_REQUEST"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L3f
                r0 = r3
                goto L40
            L3f:
                r0 = -1
            L40:
                if (r0 == 0) goto L4f
                if (r0 == r4) goto L4b
                if (r0 == r3) goto L47
                goto L52
            L47:
                r5.d(r7)
                goto L52
            L4b:
                r5.b(r6, r7)
                goto L52
            L4f:
                r5.a(r6, r7)
            L52:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: ts.car.diag.manager.GwBtManagerImpl.GwBtDeviceReciver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements BluetoothProfile.ServiceListener {
        a() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            q.h("GwBtManagerImpl", "onServiceConnected id " + i);
            if (i == 11) {
                q.a("GwBtManagerImpl", "onServiceConnected: A2DP_SINK");
                GwBtManagerImpl.this.g = (BluetoothA2dpSink) bluetoothProfile;
            }
            if (i == 16) {
                q.a("GwBtManagerImpl", "onServiceConnected: HEADSET_CLIENT");
                GwBtManagerImpl.this.h = (BluetoothHeadsetClient) bluetoothProfile;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            q.b("GwBtManagerImpl", "onServiceDisconnected " + i);
            if (i == 11) {
                q.a("GwBtManagerImpl", "onServiceDisconnected: A2DP_SINK");
                GwBtManagerImpl.this.g = null;
            }
            if (i == 16) {
                q.a("GwBtManagerImpl", "onServiceDisconnected: HEADSET_CLIENT");
                GwBtManagerImpl.this.h = null;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends CountDownTimer {
        b(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            q.h("GwBtManagerImpl", "onFinish  time out(10s)");
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            q.h("GwBtManagerImpl", "onTick  time out(5s) mIsDiagBt : " + GwBtManagerImpl.l);
            if (GwBtManagerImpl.l) {
                GwBtManagerImpl.w().f1583a.startDiscovery();
            }
        }
    }

    public GwBtManagerImpl() {
        ts.car.diag.manager.a aVar = new Object() { // from class: ts.car.diag.manager.a
        };
        this.i = new a();
        this.j = "";
    }

    private boolean A(String str) {
        l = true;
        q.i("GwBtManagerImpl", "startConnectRemoteDevice begin " + str);
        this.j = str;
        z();
        this.f1583a.enable();
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            q.b("GwBtManagerImpl", "parameter of Bluetooth Addresss is invalid");
            return false;
        }
        q.i("GwBtManagerImpl", "step 1: mac address is " + str);
        if (this.f1583a.isDiscovering()) {
            this.f1583a.cancelDiscovery();
        }
        q.h("GwBtManagerImpl", "step 2: startDiscovery");
        if (!this.f1583a.startDiscovery()) {
            q.h("GwBtManagerImpl", "step 2: startDiscovery failed");
        }
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            q.b("GwBtManagerImpl", "Throw sleep Exception : " + e);
        }
        BluetoothDevice remoteDevice = this.f1583a.getRemoteDevice(str);
        q.h("GwBtManagerImpl", "remoteDevice =" + remoteDevice);
        if (remoteDevice == null) {
            q.b("GwBtManagerImpl", "step 3: check remote device. null");
            return false;
        }
        q.i("GwBtManagerImpl", "step 3: print device name : " + remoteDevice.getName() + " address : " + remoteDevice.getAddress());
        if (remoteDevice.getBondState() == 12) {
            this.f1583a.cancelDiscovery();
            q.h("GwBtManagerImpl", "step 4: device has been bond and try to connect A2dp_Sink profile");
            q.h("GwBtManagerImpl", " mA2dpSink " + this.g);
            BluetoothA2dpSink bluetoothA2dpSink = this.g;
            if (bluetoothA2dpSink != null) {
                bluetoothA2dpSink.connect(remoteDevice);
            }
        } else {
            if (!remoteDevice.createBond()) {
                q.b("GwBtManagerImpl", "step 4: create bond failed");
                return false;
            }
            q.h("GwBtManagerImpl", "step 4: create bond successfully");
        }
        return true;
    }

    private byte r() {
        String str;
        String name;
        BluetoothAdapter bluetoothAdapter = this.f1583a;
        if (bluetoothAdapter == null) {
            q.b("GwBtManagerImpl", "checkoutConnectResult mBTadapter == null");
            return (byte) -1;
        }
        int state = bluetoothAdapter.getState();
        q.h("GwBtManagerImpl", "mBTadapter.getState state : " + state);
        q.h("GwBtManagerImpl", "mBtConnectState : " + ((int) this.f));
        if (state == 1) {
            q.h("GwBtManagerImpl", "checkoutConnectResult mBtConnectState is connecting");
            return (byte) 3;
        }
        Set<BluetoothDevice> bondedDevices = this.f1583a.getBondedDevices();
        q.h("GwBtManagerImpl", "devices size:" + bondedDevices.size());
        Iterator<BluetoothDevice> it = bondedDevices.iterator();
        while (true) {
            str = "";
            if (!it.hasNext()) {
                name = "";
                break;
            }
            BluetoothDevice next = it.next();
            boolean zIsConnected = next.isConnected();
            String address = next.getAddress();
            name = next.getName();
            if (zIsConnected) {
                str = address;
                break;
            }
        }
        q.i("GwBtManagerImpl", "checkoutConnectResult mConnectBtMac " + this.j + " hasConnectedMac:" + str + " hasConnectedName：" + name);
        if (TextUtils.isEmpty(this.j) || TextUtils.isEmpty(str) || !this.j.equals(str)) {
            q.b("GwBtManagerImpl", "checkoutConnectResult false");
            return (byte) 1;
        }
        q.h("GwBtManagerImpl", "checkoutConnectResult true");
        return (byte) 0;
    }

    private ArrayList<Byte> s() {
        q.i("GwBtManagerImpl", "checkMainBtDiscoveryResult mCurrentMac " + this.d);
        q.i("GwBtManagerImpl", "checkMainBtDiscoveryResult btMacList " + this.f1585c);
        if (!BluetoothAdapter.checkBluetoothAddress(this.d)) {
            q.i("GwBtManagerImpl", " mCurrentMac is fail : " + this.d);
            return ts.car.diag.d.a.a(this.d);
        }
        boolean zContains = this.f1585c.contains(this.d);
        q.h("GwBtManagerImpl", " result " + zContains);
        return ts.car.diag.d.a.b((byte) 0, zContains ? (byte) 2 : (byte) 1, this.d);
    }

    private byte t(String str) {
        l = true;
        this.d = str;
        BluetoothAdapter bluetoothAdapter = this.f1583a;
        if (bluetoothAdapter == null) {
            q.b("GwBtManagerImpl", "mBTadapter == null");
            return (byte) -1;
        }
        if (!bluetoothAdapter.isEnabled()) {
            this.f1583a.enable();
        }
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            q.b("GwBtManagerImpl", "discoveryBluetooth [vice]parameter of Bluetooth Addresss is invalid");
            return (byte) -1;
        }
        q.i("GwBtManagerImpl", "discoveryBluetooth [vice]mac address is " + str);
        BluetoothDevice remoteDevice = this.f1583a.getRemoteDevice(str);
        q.h("GwBtManagerImpl", "remoteDevice = " + remoteDevice);
        BluetoothHeadsetClient bluetoothHeadsetClient = this.h;
        if (bluetoothHeadsetClient != null) {
            q.h("GwBtManagerImpl", "mHeadsetClient disconnect is " + bluetoothHeadsetClient.disconnect(remoteDevice));
        } else {
            q.b("GwBtManagerImpl", "mHeadsetClient == null");
        }
        if (this.f1583a.isDiscovering()) {
            this.f1583a.cancelDiscovery();
        }
        q.h("GwBtManagerImpl", "discoveryBluetooth [vice]startDiscovery");
        int i = 0;
        if (this.f1583a.startDiscovery()) {
            q.h("GwBtManagerImpl", "discoveryBluetooth [vice]startDiscovery success");
            i = 1;
        } else {
            q.h("GwBtManagerImpl", "discoveryBluetooth [vice]startDiscovery failed");
        }
        return (i ^ 1) == true ? (byte) 1 : (byte) 0;
    }

    public static GwBtManagerImpl w() {
        if (k == null) {
            synchronized (GwBtManagerImpl.class) {
                if (k == null) {
                    k = new GwBtManagerImpl();
                }
            }
        }
        return k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean x() {
        if (this.f1585c == null || TextUtils.isEmpty(this.d)) {
            return false;
        }
        q.i("GwBtManagerImpl", "isMacContain mCurrentMac " + this.d);
        for (String str : this.f1585c) {
            q.i("GwBtManagerImpl", "mBT_MacList " + str);
            if (str.equals(this.d)) {
                return true;
            }
        }
        q.h("GwBtManagerImpl", "isMacContain " + this.f1585c.contains(this.d));
        return this.f1585c.contains(this.d);
    }

    @Override // ts.car.diag.manager.i
    public byte a(String str, byte b2) {
        if (b2 == 0) {
            return t(str);
        }
        if (b2 == 1) {
            return this.f1584b.c(str);
        }
        return (byte) 1;
    }

    @Override // ts.car.diag.manager.i
    public void b(Context context) {
        this.f1583a = BluetoothAdapter.getDefaultAdapter();
        t tVarF = t.f();
        this.f1584b = tVarF;
        tVarF.h(context);
        if (this.f1583a.getProfileProxy(context, this.i, 16)) {
            q.b("GwBtManagerImpl", "getProfileProxy HEADSET true");
        } else {
            q.b("GwBtManagerImpl", "getProfileProxy HEADSET failed");
        }
        if (this.f1583a.getProfileProxy(context, this.i, 11)) {
            return;
        }
        q.b("GwBtManagerImpl", "getProfileProxy A2DP_SINK failed");
    }

    @Override // ts.car.diag.manager.i
    public byte c(byte b2) {
        q.h("GwBtManagerImpl", "checkConnectResult deviceIndex " + ((int) b2));
        if (b2 == 0) {
            byte bR = r();
            q.h("GwBtManagerImpl", "check main bt " + ((int) bR));
            return bR;
        }
        if (b2 != 1) {
            q.b("GwBtManagerImpl", "checkConnectResult current has not bt index " + ((int) b2));
            return (byte) 1;
        }
        byte bA = this.f1584b.a();
        q.h("GwBtManagerImpl", "check secondary bt " + ((int) bA));
        return bA;
    }

    @Override // ts.car.diag.manager.i
    public ArrayList<Byte> d(byte b2) {
        q.h("GwBtManagerImpl", "checkDiscoveryResult deviceIndex " + ((int) b2));
        if (b2 == 0) {
            ArrayList<Byte> arrayListS = s();
            q.h("GwBtManagerImpl", "checkDiscoveryResult main bt " + arrayListS);
            return arrayListS;
        }
        if (b2 != 1) {
            q.b("GwBtManagerImpl", "checkDiscoveryResult current has not bt index " + ((int) b2));
            return ts.car.diag.d.a.a(null);
        }
        ArrayList<Byte> arrayListB = this.f1584b.b();
        q.h("GwBtManagerImpl", "checkDiscoveryResult secondary bt " + arrayListB);
        return arrayListB;
    }

    @Override // ts.car.diag.manager.i
    public byte e(byte b2, String str, String str2) {
        if (b2 == 0) {
            return q(str);
        }
        if (b2 == 1) {
            return this.f1584b.i(str);
        }
        q.b("GwBtManagerImpl", "connectToBtDevice current has not bt index " + ((int) b2));
        return (byte) 1;
    }

    @Override // ts.car.diag.manager.i
    public byte[] f() {
        q.h("GwBtManagerImpl", "getBtConnectionStatus: start");
        return new byte[]{u(), this.f1584b.d()};
    }

    @Override // ts.car.diag.manager.i
    public byte[] g() {
        return new byte[]{v(), this.f1584b.e()};
    }

    @Override // ts.car.diag.manager.i
    public String i() {
        q.h("GwBtManagerImpl", "readLocalBt1Mac: start");
        return this.f1583a.getAddress();
    }

    @Override // ts.car.diag.manager.i
    public String j() {
        q.h("GwBtManagerImpl", "read_LocalBt2Mac: start");
        return this.f1584b.g();
    }

    @Override // ts.car.diag.manager.i
    public byte k(byte[] bArr) {
        q.h("GwBtManagerImpl", " setBt_OnOff start");
        if (bArr == null) {
            q.b("GwBtManagerImpl", " setBt_OnOff data == null");
            return (byte) 1;
        }
        if (bArr != null && bArr.length < 2) {
            q.b("GwBtManagerImpl", " setBt_OnOff data error data.length " + bArr.length);
            return (byte) 1;
        }
        byte b2 = bArr[0];
        byte b3 = bArr[1];
        q.h("GwBtManagerImpl", " bt0State " + ((int) b2) + " bt1State " + ((int) b3));
        boolean z = y(Byte.valueOf(b2)) && this.f1584b.j(Byte.valueOf(b3));
        q.h("GwBtManagerImpl", "setAllBtOnOff result : " + z);
        return z ? (byte) 0 : (byte) 1;
    }

    public byte q(String str) {
        q.i("GwBtManagerImpl", "bluetoothAutoPairing macAddr =" + str);
        if (this.f1583a == null) {
            q.b("GwBtManagerImpl", "bluetoothAutoPairing mBTadapter == null");
            return (byte) 1;
        }
        boolean zA = A(str);
        q.h("GwBtManagerImpl", "startConnectRemoteDevice result " + zA);
        return !zA ? (byte) 1 : (byte) 0;
    }

    public byte u() {
        q.h("GwBtManagerImpl", "BTConnectionStatus mBtConnectState : " + ((int) this.f));
        return this.f;
    }

    public byte v() {
        if (this.f1583a.isEnabled()) {
            q.h("GwBtManagerImpl", "BT_OnOff_on");
            return (byte) 1;
        }
        q.h("GwBtManagerImpl", "BT_OnOff_off");
        return (byte) 2;
    }

    public boolean y(Byte b2) {
        if (b2.equals((byte) 1)) {
            if (this.f1583a.isEnabled()) {
                q.h("GwBtManagerImpl", "SetBT_OnOff_off, current bt is enable");
                return true;
            }
            boolean zEnable = this.f1583a.enable();
            q.h("GwBtManagerImpl", "SetBT_OnOff_on,ruslult is:" + zEnable);
            return zEnable;
        }
        if (!b2.equals((byte) 2)) {
            if (!b2.equals((byte) -1)) {
                return false;
            }
            q.h("GwBtManagerImpl", "BT_ON_OFF_SETTING_INVALID");
            return true;
        }
        if (!this.f1583a.isEnabled()) {
            q.h("GwBtManagerImpl", "SetBT_OnOff_off, current bt is disenable");
            return true;
        }
        boolean zDisable = this.f1583a.disable();
        q.h("GwBtManagerImpl", "SetBT_OnOff_off,ruslult is:" + zDisable);
        return zDisable;
    }

    public void z() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            defaultAdapter.setDiscoverableTimeout(0);
            defaultAdapter.setScanMode(23, 0L);
        } catch (Exception e) {
            Log.e("GwBtManagerImpl", "setDiscoverableTimeout failure:" + e.getMessage());
        }
    }
}
