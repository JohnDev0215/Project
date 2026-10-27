package org.chromium.device.bluetooth;

import J.N;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Log;
import defpackage.fa1;
import defpackage.n3;
import defpackage.um0;
import defpackage.vd1;
import defpackage.xz;
import defpackage.zt3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeBluetoothDevice {
    public long a;
    public final Wrappers$BluetoothDeviceWrapper b;
    public um0 c;
    public final a d = new a();
    public final HashMap<Wrappers$BluetoothGattCharacteristicWrapper, ChromeBluetoothRemoteGattCharacteristic> e = new HashMap<>();
    public final HashMap<Wrappers$BluetoothGattDescriptorWrapper, ChromeBluetoothRemoteGattDescriptor> f = new HashMap<>();

    public class a extends vd1 {

        /* JADX INFO: renamed from: org.chromium.device.bluetooth.ChromeBluetoothDevice$a$a, reason: collision with other inner class name */
        public class RunnableC0154a implements Runnable {
            public final /* synthetic */ int e;
            public final /* synthetic */ int f;

            public RunnableC0154a(int i, int i2) {
                this.e = i;
                this.f = i2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                um0 um0Var;
                a aVar = a.this;
                int i = this.e;
                if (i == 2) {
                    if (!((BluetoothGatt) ChromeBluetoothDevice.this.c.a).requestMtu(517)) {
                        ((BluetoothGatt) ChromeBluetoothDevice.this.c.a).discoverServices();
                    }
                } else if (i == 0 && (um0Var = ChromeBluetoothDevice.this.c) != null) {
                    ((BluetoothGatt) um0Var.a).close();
                    ChromeBluetoothDevice.this.c = null;
                }
                ChromeBluetoothDevice chromeBluetoothDevice = ChromeBluetoothDevice.this;
                long j = chromeBluetoothDevice.a;
                if (j != 0) {
                    N.MmnW7gQC(j, chromeBluetoothDevice, this.f, i == 2);
                }
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                um0 um0Var;
                ChromeBluetoothDevice chromeBluetoothDevice = ChromeBluetoothDevice.this;
                if (chromeBluetoothDevice.a == 0 || (um0Var = chromeBluetoothDevice.c) == null) {
                    return;
                }
                ((BluetoothGatt) um0Var.a).discoverServices();
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                um0 um0Var;
                a aVar = a.this;
                ChromeBluetoothDevice chromeBluetoothDevice = ChromeBluetoothDevice.this;
                if (chromeBluetoothDevice.a == 0 || (um0Var = chromeBluetoothDevice.c) == null) {
                    return;
                }
                List<BluetoothGattService> services = ((BluetoothGatt) um0Var.a).getServices();
                ArrayList arrayList = new ArrayList(services.size());
                Iterator<BluetoothGattService> it = services.iterator();
                while (it.hasNext()) {
                    arrayList.add(new Wrappers$BluetoothGattServiceWrapper(it.next(), (Wrappers$BluetoothDeviceWrapper) um0Var.b));
                }
                Iterator it2 = arrayList.iterator();
                while (true) {
                    boolean zHasNext = it2.hasNext();
                    ChromeBluetoothDevice chromeBluetoothDevice2 = ChromeBluetoothDevice.this;
                    if (!zHasNext) {
                        N.M9HSgyay(chromeBluetoothDevice2.a, chromeBluetoothDevice2);
                        return;
                    }
                    Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper = (Wrappers$BluetoothGattServiceWrapper) it2.next();
                    N.MAoRk69U(chromeBluetoothDevice2.a, chromeBluetoothDevice2, chromeBluetoothDevice2.getAddress() + "/" + wrappers$BluetoothGattServiceWrapper.a.getUuid().toString() + "," + wrappers$BluetoothGattServiceWrapper.a.getInstanceId(), wrappers$BluetoothGattServiceWrapper);
                }
            }
        }

        public class d implements Runnable {
            public final /* synthetic */ Wrappers$BluetoothGattCharacteristicWrapper e;
            public final /* synthetic */ byte[] f;

            public d(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, byte[] bArr) {
                this.e = wrappers$BluetoothGattCharacteristicWrapper;
                this.f = bArr;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = ChromeBluetoothDevice.this.e.get(this.e);
                if (chromeBluetoothRemoteGattCharacteristic == null) {
                    return;
                }
                Log.i("cr_Bluetooth", "onCharacteristicChanged");
                long j = chromeBluetoothRemoteGattCharacteristic.a;
                if (j != 0) {
                    N.MZCKcdTH(j, chromeBluetoothRemoteGattCharacteristic, this.f);
                }
            }
        }

        public class e implements Runnable {
            public final /* synthetic */ Wrappers$BluetoothGattCharacteristicWrapper e;
            public final /* synthetic */ int f;

            public e(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, int i) {
                this.e = wrappers$BluetoothGattCharacteristicWrapper;
                this.f = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = ChromeBluetoothDevice.this.e.get(this.e);
                if (chromeBluetoothRemoteGattCharacteristic == null) {
                    return;
                }
                int i = this.f;
                fa1.h("Bluetooth", "onCharacteristicRead status:%d==%s", Integer.valueOf(i), i == 0 ? "OK" : "Error");
                long j = chromeBluetoothRemoteGattCharacteristic.a;
                if (j != 0) {
                    N.Mn9Gzi$d(j, chromeBluetoothRemoteGattCharacteristic, i, chromeBluetoothRemoteGattCharacteristic.b.a.getValue());
                }
            }
        }

        public class f implements Runnable {
            public final /* synthetic */ Wrappers$BluetoothGattCharacteristicWrapper e;
            public final /* synthetic */ int f;

            public f(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, int i) {
                this.e = wrappers$BluetoothGattCharacteristicWrapper;
                this.f = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = ChromeBluetoothDevice.this.e.get(this.e);
                if (chromeBluetoothRemoteGattCharacteristic == null) {
                    return;
                }
                int i = this.f;
                fa1.h("Bluetooth", "onCharacteristicWrite status:%d==%s", Integer.valueOf(i), i == 0 ? "OK" : "Error");
                long j = chromeBluetoothRemoteGattCharacteristic.a;
                if (j != 0) {
                    N.M3TCxDs5(j, chromeBluetoothRemoteGattCharacteristic, i);
                }
            }
        }

        public class g implements Runnable {
            public final /* synthetic */ Wrappers$BluetoothGattDescriptorWrapper e;
            public final /* synthetic */ int f;

            public g(Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, int i) {
                this.e = wrappers$BluetoothGattDescriptorWrapper;
                this.f = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ChromeBluetoothRemoteGattDescriptor chromeBluetoothRemoteGattDescriptor = ChromeBluetoothDevice.this.f.get(this.e);
                if (chromeBluetoothRemoteGattDescriptor == null) {
                    return;
                }
                int i = this.f;
                fa1.h("Bluetooth", "onDescriptorRead status:%d==%s", Integer.valueOf(i), i == 0 ? "OK" : "Error");
                long j = chromeBluetoothRemoteGattDescriptor.a;
                if (j != 0) {
                    N.MaKNZo8k(j, chromeBluetoothRemoteGattDescriptor, i, chromeBluetoothRemoteGattDescriptor.b.a.getValue());
                }
            }
        }

        public class h implements Runnable {
            public final /* synthetic */ Wrappers$BluetoothGattDescriptorWrapper e;
            public final /* synthetic */ int f;

            public h(Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, int i) {
                this.e = wrappers$BluetoothGattDescriptorWrapper;
                this.f = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ChromeBluetoothRemoteGattDescriptor chromeBluetoothRemoteGattDescriptor = ChromeBluetoothDevice.this.f.get(this.e);
                if (chromeBluetoothRemoteGattDescriptor == null) {
                    return;
                }
                int i = this.f;
                fa1.h("Bluetooth", "onDescriptorWrite status:%d==%s", Integer.valueOf(i), i == 0 ? "OK" : "Error");
                long j = chromeBluetoothRemoteGattDescriptor.a;
                if (j != 0) {
                    N.M4SmPJiR(j, chromeBluetoothRemoteGattDescriptor, i);
                }
            }
        }

        public a() {
        }

        @Override // defpackage.vd1
        public final void B(int i) {
            fa1.h("Bluetooth", "onServicesDiscovered status:%d==%s", Integer.valueOf(i), i == 0 ? "OK" : "Error");
            n3 n3VarB0 = n3.b0();
            c cVar = new c();
            n3VarB0.getClass();
            ThreadUtils.f(cVar);
        }

        @Override // defpackage.vd1
        public final void k(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper) {
            Log.i("cr_Bluetooth", "device onCharacteristicChanged.");
            byte[] value = wrappers$BluetoothGattCharacteristicWrapper.a.getValue();
            n3 n3VarB0 = n3.b0();
            d dVar = new d(wrappers$BluetoothGattCharacteristicWrapper, value);
            n3VarB0.getClass();
            ThreadUtils.f(dVar);
        }

        @Override // defpackage.vd1
        public final void l(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, int i) {
            n3 n3VarB0 = n3.b0();
            e eVar = new e(wrappers$BluetoothGattCharacteristicWrapper, i);
            n3VarB0.getClass();
            ThreadUtils.f(eVar);
        }

        @Override // defpackage.vd1
        public final void m(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, int i) {
            n3 n3VarB0 = n3.b0();
            f fVar = new f(wrappers$BluetoothGattCharacteristicWrapper, i);
            n3VarB0.getClass();
            ThreadUtils.f(fVar);
        }

        @Override // defpackage.vd1
        public final void n(int i, int i2) {
            fa1.h("Bluetooth", "onConnectionStateChange status:%d newState:%s", Integer.valueOf(i), i2 == 2 ? "Connected" : "Disconnected");
            n3 n3VarB0 = n3.b0();
            RunnableC0154a runnableC0154a = new RunnableC0154a(i2, i);
            n3VarB0.getClass();
            ThreadUtils.f(runnableC0154a);
        }

        @Override // defpackage.vd1
        public final void o(Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, int i) {
            n3 n3VarB0 = n3.b0();
            g gVar = new g(wrappers$BluetoothGattDescriptorWrapper, i);
            n3VarB0.getClass();
            ThreadUtils.f(gVar);
        }

        @Override // defpackage.vd1
        public final void p(Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, int i) {
            n3 n3VarB0 = n3.b0();
            h hVar = new h(wrappers$BluetoothGattDescriptorWrapper, i);
            n3VarB0.getClass();
            ThreadUtils.f(hVar);
        }

        @Override // defpackage.vd1
        public final void y(int i, int i2) {
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(i);
            objArr[1] = Integer.valueOf(i2);
            objArr[2] = i2 == 0 ? "OK" : "Error";
            fa1.i("Bluetooth", "onMtuChanged mtu:%d status:%d==%s", objArr);
            n3 n3VarB0 = n3.b0();
            b bVar = new b();
            n3VarB0.getClass();
            ThreadUtils.f(bVar);
        }
    }

    public ChromeBluetoothDevice(long j, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        this.a = j;
        this.b = wrappers$BluetoothDeviceWrapper;
    }

    @CalledByNative
    public static ChromeBluetoothDevice create(long j, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        return new ChromeBluetoothDevice(j, wrappers$BluetoothDeviceWrapper);
    }

    @CalledByNative
    public final void createGattConnectionImpl() {
        Log.i("cr_Bluetooth", "connectGatt");
        um0 um0Var = this.c;
        if (um0Var != null) {
            ((BluetoothGatt) um0Var.a).close();
        }
        Context context = xz.a;
        Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = this.b;
        wrappers$BluetoothDeviceWrapper.getClass();
        this.c = new um0(wrappers$BluetoothDeviceWrapper.a.connectGatt(context, false, new zt3(this.d, wrappers$BluetoothDeviceWrapper), 2), wrappers$BluetoothDeviceWrapper);
    }

    @CalledByNative
    public final void disconnectGatt() {
        Log.i("cr_Bluetooth", "BluetoothGatt.disconnect");
        um0 um0Var = this.c;
        if (um0Var != null) {
            ((BluetoothGatt) um0Var.a).disconnect();
        }
    }

    @CalledByNative
    public final String getAddress() {
        return this.b.a.getAddress();
    }

    @CalledByNative
    public final int getBluetoothClass() {
        BluetoothDevice bluetoothDevice = this.b.a;
        if (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null) {
            return 7936;
        }
        return bluetoothDevice.getBluetoothClass().getDeviceClass();
    }

    @CalledByNative
    public final String getName() {
        return this.b.a.getName();
    }

    @CalledByNative
    public final boolean isPaired() {
        return this.b.a.getBondState() == 12;
    }

    @CalledByNative
    public final void onBluetoothDeviceAndroidDestruction() {
        um0 um0Var = this.c;
        if (um0Var != null) {
            ((BluetoothGatt) um0Var.a).close();
            this.c = null;
        }
        this.a = 0L;
    }
}
