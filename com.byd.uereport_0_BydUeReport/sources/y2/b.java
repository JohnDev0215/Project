package y2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import c3.a;
import com.byd.base.thread.Priority;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.BTMajorType;
import com.byd.bt.model.BTPowerLevel;
import com.byd.bt.model.BondDevice;
import com.byd.bt.model.ConnectState;
import com.byd.bt.receive.BtActionReceiver;
import com.byd.bt.receive.BtScanReceiver;
import com.byd.systemsettings.cpaa.CarPlayPager;
import com.byd.systemsettings.system.utils.BydDateUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import z2.a;

/* JADX INFO: compiled from: BydBTManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9225a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BtActionReceiver f9226b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BtScanReceiver f9227c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile boolean f9228d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b3.j> f9229e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b3.g> f9230f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b3.h> f9231g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b3.d> f9232h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b3.e> f9233i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b3.i> f9234j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b3.c> f9235k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b3.b> f9236l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public y2.a f9237m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile BluetoothDevice f9238n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile BTDevice f9239o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile BluetoothDevice f9240p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ConcurrentHashMap<String, BluetoothDevice> f9241q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile boolean f9242r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public SharedPreferences f9243s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public SharedPreferences f9244t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public SharedPreferences f9245u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public volatile boolean f9246v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public volatile long f9247w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public volatile long f9248x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public volatile Timer f9249y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public volatile Timer f9250z;

    /* JADX INFO: compiled from: BydBTManager.java */
    public class a implements c3.b {
        public a() {
        }

        @Override // c3.b
        public void onServiceConnected(int i8) {
            b.this.l0(i8);
        }

        @Override // c3.b
        public void onServiceDisconnected(int i8) {
            b.this.m0(i8);
        }
    }

    /* JADX INFO: renamed from: y2.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydBTManager.java */
    public class RunnableC0146b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ BluetoothAdapter f9252d;

        /* JADX INFO: renamed from: y2.b$b$a */
        /* JADX INFO: compiled from: BydBTManager.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                x3.a.c("BydBTManager", "---enable---Before disable BT.");
                RunnableC0146b.this.f9252d.disable();
                x3.a.c("BydBTManager", "---enable---After disable BT.");
            }
        }

        public RunnableC0146b(BluetoothAdapter bluetoothAdapter) {
            this.f9252d = bluetoothAdapter;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<BluetoothDevice> listI = b.this.I();
            if (listI == null || listI.isEmpty()) {
                x3.a.c("BydBTManager", "---enable---Do disable BT.");
                this.f9252d.disable();
                return;
            }
            Iterator<BluetoothDevice> it = listI.iterator();
            while (it.hasNext()) {
                b.this.B(it.next());
            }
            x3.a.c("BydBTManager", "---enable---Delay to do disable BT.");
            p2.g.a().a(new a(), 220L, Priority.HIGH);
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class c extends TimerTask {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ BluetoothAdapter f9255d;

        public c(BluetoothAdapter bluetoothAdapter) {
            this.f9255d = bluetoothAdapter;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            BigDecimal bigDecimalSubtract = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(b.this.f9247w));
            x3.a.a("BydBTManager", "---startScan---SCAN_TIME_OUT, delta: " + bigDecimalSubtract.longValue());
            if (bigDecimalSubtract.longValue() >= 15000) {
                this.f9255d.cancelDiscovery();
            }
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class d extends TimerTask {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ BluetoothDevice f9257d;

        public d(BluetoothDevice bluetoothDevice) {
            this.f9257d = bluetoothDevice;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            BigDecimal bigDecimalSubtract = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(b.this.f9248x));
            x3.a.a("BydBTManager", "---connectDevicePaired---CONNECT_TIME_OUT, d: " + m4.d.c(this.f9257d) + ", delta: " + bigDecimalSubtract.longValue());
            if (bigDecimalSubtract.longValue() < 15000) {
                return;
            }
            x3.a.a("BydBTManager", "---connectDevicePaired---CONNECT_TIME_OUT, d: " + m4.d.c(this.f9257d));
            BluetoothDevice bluetoothDevice = this.f9257d;
            if (bluetoothDevice != null) {
                b.this.c0(bluetoothDevice);
            }
            if (b.this.f9241q != null) {
                b.this.f9241q.remove(this.f9257d.getAddress());
            }
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class e extends TimerTask {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ BluetoothDevice f9259d;

        public e(BluetoothDevice bluetoothDevice) {
            this.f9259d = bluetoothDevice;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            int i8 = b.this.f9245u.getInt(this.f9259d.getAddress().hashCode() + "", -1);
            x3.a.a("BydBTManager", "onDeviceBonded", "-----device: " + m4.d.f(this.f9259d.getName()) + ", hash: " + i8);
            if (i8 == -1) {
                b.this.L0(this.f9259d);
            }
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class f implements b3.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f9261a;

        public /* synthetic */ f(b bVar, b bVar2, a aVar) {
            this(bVar2);
        }

        @Override // b3.b
        public void onNewDevicePair(int i8) {
            x3.a.a("BydBTManager", "---onNewDevicePair---op:" + i8);
            WeakReference<b> weakReference = this.f9261a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            Iterator it = this.f9261a.get().f9236l.values().iterator();
            while (it.hasNext()) {
                ((b3.b) it.next()).onNewDevicePair(i8);
            }
        }

        public f(b bVar) {
            this.f9261a = new WeakReference<>(bVar);
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class g implements b3.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f9263a;

        public g(b bVar) {
            this.f9263a = new WeakReference<>(bVar);
        }

        @Override // b3.c
        public void onBatteryChanged(BluetoothDevice bluetoothDevice, int i8) {
            WeakReference<b> weakReference = this.f9263a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            x3.a.a("BydBTManager", ", level: " + i8 + ", device: " + m4.d.c(bluetoothDevice));
            Iterator it = this.f9263a.get().f9235k.values().iterator();
            while (it.hasNext()) {
                ((b3.c) it.next()).onBatteryChanged(bluetoothDevice, i8);
            }
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class h implements b3.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f9265a;

        public h(b bVar) {
            this.f9265a = new WeakReference<>(bVar);
        }

        @Override // b3.d
        public void onBtBondFailed(BluetoothDevice bluetoothDevice, int i8) {
            x3.a.a("BydBTManager", "---onBtBondFailed---device: " + m4.d.c(bluetoothDevice));
            WeakReference<b> weakReference = this.f9265a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9265a.get().f9242r = false;
            Iterator it = this.f9265a.get().f9232h.values().iterator();
            while (it.hasNext()) {
                ((b3.d) it.next()).onBtBondFailed(bluetoothDevice, i8);
            }
        }

        @Override // b3.d
        public void onBtBondNone(BluetoothDevice bluetoothDevice) {
            x3.a.a("BydBTManager", "---onBtBondNone---device: " + m4.d.c(bluetoothDevice));
            WeakReference<b> weakReference = this.f9265a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9265a.get().a0(bluetoothDevice);
        }

        @Override // b3.d
        public void onBtBonded(BluetoothDevice bluetoothDevice) {
            x3.a.a("BydBTManager", "---onBtBonded---device: " + m4.d.c(bluetoothDevice));
            WeakReference<b> weakReference = this.f9265a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9265a.get().e0(bluetoothDevice);
        }

        @Override // b3.d
        public void onBtBonding(BluetoothDevice bluetoothDevice) {
            x3.a.a("BydBTManager", "---onBtBonding---device: " + m4.d.c(bluetoothDevice));
            WeakReference<b> weakReference = this.f9265a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            for (b3.d dVar : this.f9265a.get().f9232h.values()) {
                x3.a.a("BydBTManager", "---onBtBonding---callback: " + dVar);
                dVar.onBtBonding(bluetoothDevice);
            }
        }

        @Override // b3.d
        public void onOobBonded(String str, String str2) {
            x3.a.a("BydBTManager", "---onOobBonded---from:" + str + ", mac: " + m4.d.d(str2));
            WeakReference<b> weakReference = this.f9265a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9265a.get().p0(str, str2);
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class i implements b3.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f9267a;

        public i(b bVar) {
            this.f9267a = new WeakReference<>(bVar);
        }

        @Override // b3.e
        public void onConnectStateChanged(BluetoothDevice bluetoothDevice, ConnectState connectState) {
            WeakReference<b> weakReference = this.f9267a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            x3.a.a("BydBTManager", "onConnectStateChanged", "device: " + m4.d.c(bluetoothDevice));
            this.f9267a.get().b0(bluetoothDevice, connectState);
        }

        @Override // b3.e
        public void onConnectedBtNameChanged(BTDevice bTDevice) {
            x3.a.a("BydBTManager", "onConnectedBtNameChanged", "device: " + bTDevice);
            this.f9267a.get().d0(bTDevice);
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class j implements b3.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f9269a;

        public j(b bVar) {
            this.f9269a = new WeakReference<>(bVar);
        }

        @Override // b3.f
        public void a(BTDevice bTDevice) {
            WeakReference<b> weakReference = this.f9269a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9269a.get().i0(bTDevice);
        }

        @Override // b3.f
        public void onDiscoveryFinished() {
            WeakReference<b> weakReference = this.f9269a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            x3.a.a("BydBTManager", "---onDiscoveryFinished---");
            this.f9269a.get().j0();
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class k implements b3.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f9271a;

        public k(b bVar) {
            this.f9271a = new WeakReference<>(bVar);
        }

        @Override // b3.h
        public void onReceivedPairRequest(BluetoothDevice bluetoothDevice, boolean z7, int i8) {
            x3.a.a("BydBTManager", "---onReceivedPairRequest---device: " + m4.d.c(bluetoothDevice) + ", needToInput: " + z7);
            WeakReference<b> weakReference = this.f9271a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9271a.get().r0(bluetoothDevice, z7, i8);
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class l implements b3.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f9273a;

        public l(b bVar) {
            this.f9273a = new WeakReference<>(bVar);
        }

        @Override // b3.i
        public void onConnectedChanged(int i8, BluetoothDevice bluetoothDevice, ConnectState connectState) {
            WeakReference<b> weakReference = this.f9273a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            x3.a.a("BydBTManager", ", profile: " + i8 + ", device: " + m4.d.c(bluetoothDevice) + ", state: " + connectState);
            this.f9273a.get().k0(i8, bluetoothDevice, connectState);
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class m implements b3.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f9275a;

        public m(b bVar) {
            this.f9275a = new WeakReference<>(bVar);
        }

        @Override // b3.j
        public void onBtOff() {
            WeakReference<b> weakReference = this.f9275a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9275a.get().f9242r = false;
            Iterator it = this.f9275a.get().f9229e.values().iterator();
            while (it.hasNext()) {
                ((b3.j) it.next()).onBtOff();
            }
        }

        @Override // b3.j
        public void onBtOn() {
            WeakReference<b> weakReference = this.f9275a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            Iterator it = this.f9275a.get().f9229e.values().iterator();
            while (it.hasNext()) {
                ((b3.j) it.next()).onBtOn();
            }
        }

        @Override // b3.j
        public void onBtTurningOff() {
            WeakReference<b> weakReference = this.f9275a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            a.c.a().f();
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BluetoothDevice f9277a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f9278b;

        public n() {
        }

        public /* synthetic */ n(b bVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public static class o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9280a = new b(null);

        public static b a() {
            return f9280a;
        }
    }

    public /* synthetic */ b(a aVar) {
        this();
    }

    public void A(boolean z7) {
        boolean z8;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return;
        }
        int state = defaultAdapter.getState();
        x3.a.a("BydBTManager", "---enable---BT cur state: " + state);
        if (state == 12 || state == 11) {
            z8 = true;
        } else {
            if (state != 10) {
            }
            z8 = false;
        }
        if (z8 == z7) {
            x3.a.c("BydBTManager", "---enable---Do nothing on the same enable state: " + z7);
            return;
        }
        x3.a.a("BydBTManager", "---enable---enable: " + z7);
        if (z7) {
            defaultAdapter.enable();
        } else {
            p2.g.a().e(new RunnableC0146b(defaultAdapter), Priority.HIGH);
        }
    }

    public final void A0(Context context) {
        x3.a.a("BydBTManager", "----registerReceiver-----");
        if (this.f9226b == null) {
            this.f9226b = new BtActionReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("com.ts.carplay.action.OOB_BONDED_FOR_BT");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.NAME_CHANGED");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_EVENT");
        intentFilter.addAction("android_auto_new_device_pair");
        context.getApplicationContext().registerReceiver(this.f9226b, intentFilter);
    }

    public void B(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        x3.a.a("BydBTManager", "forceDisconnectOnAclLayer", "device = " + m4.d.c(bluetoothDevice));
        m4.a.d(BluetoothAdapter.getDefaultAdapter(), "removeAcl", new Class[]{BluetoothDevice.class, Integer.TYPE}, new Object[]{bluetoothDevice, 1});
    }

    public final void B0() {
        if (this.f9225a == null) {
            return;
        }
        x3.a.a("BydBTManager", "----registerScanReceiver-----isScannerRegistered: " + this.f9228d);
        if (this.f9228d) {
            return;
        }
        this.f9228d = true;
        if (this.f9227c == null) {
            this.f9227c = new BtScanReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.f9225a.getApplicationContext().registerReceiver(this.f9227c, intentFilter);
    }

    public BluetoothDevice C(String str) {
        if (TextUtils.isEmpty(str)) {
            x3.a.b("BydBTManager", "getBondedDeviceByMacId", ", mac is empty");
            return null;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            x3.a.b("BydBTManager", "getBondedDeviceByMacId", ", adapter is empty");
            return null;
        }
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            x3.a.b("BydBTManager", "getBondedDeviceByMacId", ", set is empty");
            return null;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (str.equals(bluetoothDevice.getAddress())) {
                return bluetoothDevice;
            }
        }
        x3.a.b("BydBTManager", "getBondedDeviceByMacId", ", mac not match in set:" + bondedDevices);
        return null;
    }

    public void C0() {
        x3.a.a("BydBTManager", "-----release-----");
        a.c.a().d();
        a3.a.a();
        V0();
        U0();
        K0();
        if (this.f9229e == null) {
            this.f9229e = new ConcurrentHashMap<>();
        }
        if (this.f9230f != null) {
            this.f9230f.clear();
        }
        if (this.f9231g != null) {
            this.f9231g.clear();
        }
        if (this.f9232h != null) {
            this.f9232h.clear();
        }
        if (this.f9233i != null) {
            this.f9233i.clear();
        }
        if (this.f9234j != null) {
            this.f9234j.clear();
        }
        if (this.f9235k != null) {
            this.f9235k.clear();
        }
        ConcurrentHashMap<String, BluetoothDevice> concurrentHashMap = this.f9241q;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        if (this.f9237m != null) {
            throw null;
        }
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        this.f9246v = false;
        D0();
    }

    public List<BondDevice> D() {
        Set<BluetoothDevice> bondedDevices;
        char c8;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || (bondedDevices = defaultAdapter.getBondedDevices()) == null || bondedDevices.size() <= 0) {
            return null;
        }
        int i8 = 2;
        char c9 = 0;
        x3.a.c("BydBTManager", "getBondedDevices", ", set.size: " + bondedDevices.size());
        c3.a aVarA = a.c.a();
        ArrayList arrayList = new ArrayList();
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            BondDevice bondDevice = new BondDevice(new BTDevice(bluetoothDevice));
            BTMajorType type = bondDevice.getType();
            BTMajorType bTMajorType = BTMajorType.PHONE;
            if (type != bTMajorType) {
                int i9 = this.f9245u.getInt(bondDevice.getMac().hashCode() + "", -1);
                String[] strArr = new String[i8];
                strArr[c9] = "getBondedDevices";
                strArr[1] = ", device: " + m4.d.f(bondDevice.getName()) + ", hash: " + i9;
                x3.a.a("BydBTManager", strArr);
                if (i9 != -1) {
                    bondDevice.setType(bTMajorType);
                }
            }
            if (e3.a.c(bondDevice.getType()) || bondDevice.getType() == BTMajorType.UNKNOW) {
                bondDevice.setPreConnectedTime(L(bluetoothDevice.getAddress()));
                bondDevice.setPreDisconnectedTime(M(bluetoothDevice.getAddress()));
                boolean zBooleanValue = ((Boolean) m4.a.b(bluetoothDevice, "isConnected")).booleanValue();
                bondDevice.setConnected(zBooleanValue);
                if (zBooleanValue) {
                    if (aVarA.q(bluetoothDevice)) {
                        c8 = 0;
                        aVarA.i(false);
                    } else {
                        c8 = 0;
                    }
                    if (aVarA.o(bluetoothDevice)) {
                        int iN = N(bluetoothDevice);
                        String[] strArr2 = new String[i8];
                        strArr2[c8] = "getBondedDevices";
                        strArr2[1] = ", bd: " + m4.d.c(bluetoothDevice) + ", level: " + iN;
                        x3.a.a("BydBTManager", strArr2);
                        bondDevice.setLevel(Z(iN));
                    }
                    this.f9240p = bluetoothDevice;
                    i8 = 2;
                }
                String[] strArr3 = new String[i8];
                strArr3[0] = "getBondedDevices";
                strArr3[1] = ", result: " + zBooleanValue + ", bd: " + m4.d.c(bluetoothDevice) + ", level: " + bondDevice.getLevel();
                x3.a.a("BydBTManager", strArr3);
                arrayList.add(bondDevice);
                c9 = (char) 0;
                i8 = 2;
            } else {
                String[] strArr4 = new String[i8];
                strArr4[0] = "getBondedDevices";
                strArr4[1] = ", device is not support! and do unpairDevice, device: " + m4.d.f(bondDevice.getName());
                x3.a.b("BydBTManager", strArr4);
                L0(bluetoothDevice);
                c9 = 0;
            }
        }
        return arrayList;
    }

    public final void D0() {
        if (this.f9250z != null) {
            this.f9250z.cancel();
            this.f9250z = null;
        }
    }

    public String E() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter == null ? "" : defaultAdapter.getName();
    }

    public final void E0(BluetoothDevice bluetoothDevice) {
        String address;
        ConcurrentHashMap<String, BluetoothDevice> concurrentHashMap;
        BluetoothDevice bluetoothDevice2;
        x3.a.a("BydBTManager", "removeConnectingDevice", ", device: " + m4.d.c(bluetoothDevice));
        if (bluetoothDevice == null || (address = bluetoothDevice.getAddress()) == null || (concurrentHashMap = this.f9241q) == null || (bluetoothDevice2 = concurrentHashMap.get(address)) == null) {
            return;
        }
        f0(bluetoothDevice2);
        D0();
        this.f9241q.remove(address);
    }

    public BluetoothDevice F() {
        List<BluetoothDevice> listG = G();
        a aVar = null;
        if (listG == null || listG.isEmpty()) {
            return null;
        }
        c3.a aVarA = a.c.a();
        n nVar = new n(this, aVar);
        nVar.f9278b = -1;
        for (BluetoothDevice bluetoothDevice : listG) {
            int iJ = aVarA.j(bluetoothDevice);
            if (iJ > nVar.f9278b) {
                nVar.f9278b = iJ;
                nVar.f9277a = bluetoothDevice;
            }
        }
        return nVar.f9277a;
    }

    public void F0() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        x3.a.a("BydBTManager", "--setBtDiscoverable--mode = " + defaultAdapter.getScanMode());
        x3.a.a("BydBTManager", "--setBtDiscoverable--0--timeout = " + ((Integer) m4.a.b(defaultAdapter, "getDiscoverableTimeout")).intValue());
        try {
            Method method = BluetoothAdapter.class.getMethod("setScanMode", Integer.TYPE, Long.TYPE);
            method.setAccessible(true);
            x3.a.a("BydBTManager", "--setBtDiscoverable--setScanMode result = " + ((Boolean) method.invoke(defaultAdapter, 23, 15000L)).booleanValue());
        } catch (Exception e8) {
            e8.printStackTrace();
            x3.a.b("BydBTManager", "setDiscoverableTimeout failure, e:" + e8.toString());
        }
        x3.a.a("BydBTManager", "--setBtDiscoverable--1--timeout1 = " + ((Integer) m4.a.b(defaultAdapter, "getDiscoverableTimeout")).intValue());
    }

    public List<BluetoothDevice> G() {
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (((Boolean) m4.a.b(bluetoothDevice, "isConnected")).booleanValue()) {
                arrayList.add(bluetoothDevice);
            }
        }
        x3.a.a("BydBTManager", "getConnectedDeviceList:" + arrayList.size());
        return arrayList;
    }

    public void G0(String str) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || str == null || str.isEmpty()) {
            return;
        }
        x3.a.a("BydBTManager", "---setBtName---name: " + str);
        defaultAdapter.setName(str);
        a.c.a().e();
    }

    public String H() {
        BluetoothDevice bluetoothDeviceF = F();
        return bluetoothDeviceF != null ? bluetoothDeviceF.getName() : "";
    }

    public final void H0(String str, long j8) {
        SharedPreferences sharedPreferences = this.f9243s;
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putLong(str.hashCode() + "", j8);
        editorEdit.commit();
    }

    public List<BluetoothDevice> I() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            x3.a.c("BydBTManager", "getConnectedDevices", ", set.size: " + bondedDevices.size());
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if (((Boolean) m4.a.b(bluetoothDevice, "isConnected")).booleanValue()) {
                    arrayList.add(bluetoothDevice);
                }
            }
        }
        return arrayList;
    }

    public final void I0(String str, long j8) {
        SharedPreferences sharedPreferences = this.f9244t;
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putLong(str.hashCode() + "", j8);
        editorEdit.commit();
    }

    public int J() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        int i8 = 0;
        if (defaultAdapter != null && defaultAdapter.getBondedDevices() != null) {
            Iterator<BluetoothDevice> it = defaultAdapter.getBondedDevices().iterator();
            while (it.hasNext()) {
                if (((Boolean) m4.a.b(it.next(), "isConnected")).booleanValue()) {
                    i8++;
                }
            }
        }
        return i8;
    }

    public void J0() {
        x3.a.a("BydBTManager", "----startScan-----");
        if (T()) {
            return;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        x3.a.a("BydBTManager", "---startScan---lastDiscoveryTime: " + m4.d.b(((Long) m4.a.b(defaultAdapter, "getDiscoveryEndMillis")).longValue(), BydDateUtils.DEF_FORMAT));
        if (defaultAdapter.isDiscovering()) {
            defaultAdapter.cancelDiscovery();
        }
        if (!defaultAdapter.startDiscovery()) {
            x3.a.b("BydBTManager", "---startScan---startDiscovery failed.");
            return;
        }
        x3.a.a("BydBTManager", "---startScan---startDiscovery success.");
        this.f9247w = System.currentTimeMillis();
        if (this.f9249y == null) {
            this.f9249y = new Timer();
        }
        this.f9249y.schedule(new c(defaultAdapter), 15000L);
    }

    public int K(BluetoothDevice bluetoothDevice) {
        return a.c.a().j(bluetoothDevice);
    }

    public void K0() {
        if (T()) {
            return;
        }
        x3.a.a("BydBTManager", "---stopScan---cancelDiscovery-----");
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        if (this.f9249y != null) {
            this.f9249y.cancel();
            this.f9249y = null;
        }
    }

    public long L(String str) {
        SharedPreferences sharedPreferences = this.f9243s;
        if (sharedPreferences == null) {
            return 0L;
        }
        return sharedPreferences.getLong(str.hashCode() + "", 0L);
    }

    public void L0(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        ((Boolean) m4.a.b(bluetoothDevice, "removeBond")).booleanValue();
        String strA = q2.b.a(this.f9225a, CarPlayPager.NO_SHOW_MAC_LIST_CACHE);
        if (q2.c.b(strA) || !strA.contains(bluetoothDevice.getAddress())) {
            return;
        }
        q2.b.b(this.f9225a, CarPlayPager.NO_SHOW_MAC_LIST_CACHE, strA.replaceAll(bluetoothDevice.getAddress(), ""));
    }

    public final long M(String str) {
        SharedPreferences sharedPreferences = this.f9244t;
        if (sharedPreferences == null) {
            return 0L;
        }
        return sharedPreferences.getLong(str.hashCode() + "", 0L);
    }

    public void M0(String str) {
        if (this.f9232h == null) {
            return;
        }
        this.f9232h.remove(str);
    }

    public int N(BluetoothDevice bluetoothDevice) {
        Bundle bundleK = a.c.a().k(bluetoothDevice);
        if (bundleK != null) {
            return bundleK.getInt("android.bluetooth.headsetclient.extra.BATTERY_LEVEL");
        }
        return 0;
    }

    public void N0(String str) {
        if (this.f9233i == null) {
            return;
        }
        this.f9233i.remove(str);
    }

    public BluetoothDevice O() {
        return this.f9238n;
    }

    public void O0(String str) {
        if (this.f9230f == null) {
            return;
        }
        if (this.f9230f.size() <= 1) {
            V0();
        }
        this.f9230f.remove(str);
    }

    public final boolean P(String str) {
        o2.a aVarA = o2.c.b().a();
        if (aVarA == null) {
            x3.a.b("BydBTManager", "dataService ==null");
            return false;
        }
        List<AndroidAutoDevice> androidAutoDevices = aVarA.getAndroidAutoDevices();
        List<CarPlayDevice> carPlayDevices = aVarA.getCarPlayDevices();
        if (androidAutoDevices != null) {
            Iterator<AndroidAutoDevice> it = androidAutoDevices.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().getBtAddr())) {
                    x3.a.a("BydBTManager", "device in aa");
                    return true;
                }
            }
        }
        if (carPlayDevices != null) {
            Iterator<CarPlayDevice> it2 = carPlayDevices.iterator();
            while (it2.hasNext()) {
                if (str.equals(it2.next().getBtAddr())) {
                    x3.a.a("BydBTManager", "device in cp");
                    return true;
                }
            }
        }
        return false;
    }

    public void P0(String str) {
        if (this.f9231g == null) {
            return;
        }
        this.f9231g.remove(str);
    }

    public boolean Q(Context context) {
        x3.a.a("BydBTManager", "----init-----");
        if (context == null || this.f9246v) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        this.f9225a = applicationContext;
        this.f9243s = applicationContext.getSharedPreferences("key_bt_connected_time", 0);
        this.f9244t = applicationContext.getSharedPreferences("bt_disconnected_time", 0);
        this.f9245u = applicationContext.getSharedPreferences("bt_oob", 0);
        F0();
        a.c.a().a();
        a3.a.e("bt_sate", new m(this));
        a3.a.e("bt_discover", new j(this));
        a3.a.e("bt_pair_request", new k(this));
        a3.a.e("bt_bond_sate", new h(this));
        a3.a.e("bt_connect_state", new i(this));
        a3.a.e("bt_profile_connect_state", new l(this));
        a3.a.e("bt_battery_changed", new g(this));
        a3.a.e("aa_new_device_pair", new f(this, this, null));
        A0(this.f9225a);
        if (this.f9229e == null) {
            this.f9229e = new ConcurrentHashMap<>();
        }
        if (this.f9230f == null) {
            this.f9230f = new ConcurrentHashMap<>();
        }
        if (this.f9231g == null) {
            this.f9231g = new ConcurrentHashMap<>();
        }
        if (this.f9232h == null) {
            this.f9232h = new ConcurrentHashMap<>();
        }
        if (this.f9233i == null) {
            this.f9233i = new ConcurrentHashMap<>();
        }
        if (this.f9234j == null) {
            this.f9234j = new ConcurrentHashMap<>();
        }
        if (this.f9235k == null) {
            this.f9235k = new ConcurrentHashMap<>();
        }
        if (this.f9236l == null) {
            this.f9236l = new ConcurrentHashMap<>();
        }
        a.c.a().m(applicationContext, new a());
        if (this.f9241q == null) {
            this.f9241q = new ConcurrentHashMap<>();
        }
        this.f9246v = true;
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public void Q0(String str) {
        if (this.f9235k == null) {
            return;
        }
        this.f9235k.remove(str);
    }

    public boolean R(BluetoothDevice bluetoothDevice) {
        return a.c.a().n(bluetoothDevice);
    }

    public void R0(String str) {
        if (this.f9229e == null) {
            return;
        }
        this.f9229e.remove(str);
    }

    public final boolean S(BluetoothDevice bluetoothDevice) {
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices != null && !bondedDevices.isEmpty() && bluetoothDevice != null) {
            Iterator<BluetoothDevice> it = bondedDevices.iterator();
            while (it.hasNext()) {
                if (it.next().getAddress().equals(bluetoothDevice.getAddress())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void S0(String str) {
        if (this.f9236l == null) {
            return;
        }
        this.f9236l.remove(str);
    }

    public boolean T() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter == null || !defaultAdapter.isEnabled();
    }

    public void T0(String str) {
        if (this.f9234j == null) {
            return;
        }
        this.f9234j.remove(str);
    }

    public boolean U() {
        int iIntValue = ((Integer) m4.a.b(BluetoothAdapter.getDefaultAdapter(), "getConnectionState")).intValue();
        x3.a.a("BydBTManager", "isConnected state: " + iIntValue + ", BluetoothAdapter.STATE_CONNECTED: 2");
        return iIntValue == 2;
    }

    public final void U0() {
        Context context;
        x3.a.a("BydBTManager", "----unregisterReceiver-----");
        BtActionReceiver btActionReceiver = this.f9226b;
        if (btActionReceiver == null || (context = this.f9225a) == null) {
            return;
        }
        try {
            context.unregisterReceiver(btActionReceiver);
        } catch (Exception unused) {
        }
    }

    public boolean V(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        boolean zBooleanValue = ((Boolean) m4.a.b(bluetoothDevice, "isConnected")).booleanValue();
        if (e3.a.a(bluetoothDevice) == BTMajorType.PHONE && zBooleanValue) {
            return X(bluetoothDevice) || R(bluetoothDevice);
        }
        return zBooleanValue;
    }

    public final void V0() {
        Context context;
        x3.a.a("BydBTManager", "----unregisterScanReceiver-----isScannerRegistered: " + this.f9228d);
        if (this.f9228d) {
            this.f9228d = false;
            BtScanReceiver btScanReceiver = this.f9227c;
            if (btScanReceiver == null || (context = this.f9225a) == null) {
                return;
            }
            try {
                context.unregisterReceiver(btScanReceiver);
            } catch (Exception unused) {
            }
        }
    }

    public boolean W() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public boolean X(BluetoothDevice bluetoothDevice) {
        return a.c.a().o(bluetoothDevice);
    }

    public boolean Y() {
        return this.f9242r;
    }

    public BTPowerLevel Z(int i8) {
        return (i8 == 0 || i8 == 1) ? BTPowerLevel.LEVEL_4 : i8 != 2 ? i8 != 3 ? i8 != 4 ? i8 != 5 ? BTPowerLevel.LEVEL_0 : BTPowerLevel.LEVEL_0 : BTPowerLevel.LEVEL_1 : BTPowerLevel.LEVEL_2 : BTPowerLevel.LEVEL_3;
    }

    public final void a0(BluetoothDevice bluetoothDevice) {
        this.f9242r = false;
        if (bluetoothDevice != null && bluetoothDevice.getAddress() != null) {
            String address = bluetoothDevice.getAddress();
            int i8 = this.f9245u.getInt(address.hashCode() + "", -1);
            x3.a.a("BydBTManager", "---onBtBondNone---hash: " + i8);
            if (i8 != -1) {
                SharedPreferences.Editor editorEdit = this.f9245u.edit();
                editorEdit.remove(address.hashCode() + "");
                editorEdit.commit();
            }
            E0(bluetoothDevice);
        }
        if (this.f9232h == null || this.f9232h.isEmpty()) {
            return;
        }
        Iterator<b3.d> it = this.f9232h.values().iterator();
        while (it.hasNext()) {
            it.next().onBtBondNone(bluetoothDevice);
        }
    }

    public final void b0(BluetoothDevice bluetoothDevice, ConnectState connectState) {
        Iterator<b3.e> it = this.f9233i.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectStateChanged(bluetoothDevice, connectState);
        }
        if (connectState != ConnectState.CONNECTED) {
            if (connectState == ConnectState.DISCONNECTED) {
                h0(bluetoothDevice);
            }
        } else if (S(bluetoothDevice)) {
            g0(bluetoothDevice);
        } else {
            x3.a.c("BydBTManager", "当前设备为非配对的设备,return");
        }
    }

    public final void c0(BluetoothDevice bluetoothDevice) {
        Iterator<b3.e> it = this.f9233i.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectFailed(bluetoothDevice);
        }
    }

    public final void d0(BTDevice bTDevice) {
        x3.a.a("BydBTManager", "---onConnectedBtNameChanged--device = " + bTDevice);
        if (this.f9238n != null) {
            n0(bTDevice);
        }
        bTDevice.setPreConnectedTime(L(bTDevice.getMac()));
        bTDevice.setPreDisconnectedTime(M(bTDevice.getMac()));
        Iterator<b3.e> it = this.f9233i.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectedBtNameChanged(bTDevice);
        }
    }

    public final void e0(BluetoothDevice bluetoothDevice) {
        x3.a.a("BydBTManager", "onDeviceBonded", "-----device: " + m4.d.f(bluetoothDevice.getName()) + ", " + m4.d.d(bluetoothDevice.getAddress()));
        this.f9242r = false;
        if (this.f9232h == null || this.f9232h.isEmpty()) {
            return;
        }
        for (b3.d dVar : this.f9232h.values()) {
            x3.a.a("BydBTManager", "-----callback: " + dVar);
            dVar.onBtBonded(bluetoothDevice);
        }
        if (e3.a.b(bluetoothDevice)) {
            return;
        }
        new Timer().schedule(new e(bluetoothDevice), 1000L);
    }

    public final void f0(BluetoothDevice bluetoothDevice) {
        Iterator<b3.e> it = this.f9233i.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectFailed(bluetoothDevice);
        }
    }

    public void g0(BluetoothDevice bluetoothDevice) {
        if (this.f9240p == null) {
            this.f9240p = F();
        }
        x3.a.a("BydBTManager", "onDeviceConnected", ", device: " + m4.d.c(bluetoothDevice) + ", deviceConnected: " + m4.d.c(this.f9240p));
        if (bluetoothDevice != null) {
            H0(bluetoothDevice.getAddress(), System.currentTimeMillis());
        }
        this.f9238n = null;
        this.f9240p = bluetoothDevice;
        D0();
        ConcurrentHashMap<String, BluetoothDevice> concurrentHashMap = this.f9241q;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(bluetoothDevice.getAddress());
        }
    }

    public void h0(BluetoothDevice bluetoothDevice) {
        x3.a.a("BydBTManager", "onDeviceDisconnected", ", device: " + m4.d.c(bluetoothDevice));
        this.f9238n = null;
        if (this.f9240p != null && this.f9240p.getAddress().equals(bluetoothDevice.getAddress())) {
            this.f9240p = null;
        }
        if (bluetoothDevice != null) {
            I0(bluetoothDevice.getAddress(), System.currentTimeMillis());
        }
        E0(bluetoothDevice);
    }

    public final void i0(BTDevice bTDevice) {
        x3.a.a("BydBTManager", "---onDeviceDiscovered---device = " + bTDevice);
        if (this.f9238n != null) {
            n0(bTDevice);
        }
        bTDevice.setPreConnectedTime(L(bTDevice.getMac()));
        bTDevice.setPreDisconnectedTime(M(bTDevice.getMac()));
        Iterator<b3.g> it = this.f9230f.values().iterator();
        while (it.hasNext()) {
            it.next().onDeviceFound(bTDevice);
        }
    }

    public final void j0() {
        x3.a.a("BydBTManager", "---onDiscoveryFinished-----");
        if (this.f9239o == null && this.f9238n != null) {
            o0(this.f9238n);
        }
        if (this.f9230f != null && this.f9230f.size() > 0) {
            Iterator<b3.g> it = this.f9230f.values().iterator();
            while (it.hasNext()) {
                it.next().onDiscoveryFinished();
            }
        }
        this.f9238n = null;
    }

    public void k0(int i8, BluetoothDevice bluetoothDevice, ConnectState connectState) {
        x3.a.a("BydBTManager", "onProfileConnectedChanged", ", profile: " + i8 + ", device: " + m4.d.c(bluetoothDevice) + ", state: " + connectState);
        Iterator<b3.i> it = this.f9234j.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectedChanged(i8, bluetoothDevice, connectState);
        }
    }

    public void l0(int i8) {
        x3.a.a("BydBTManager", "onProfileServiceConnected", ", profile: " + i8);
        Iterator<b3.i> it = this.f9234j.values().iterator();
        while (it.hasNext()) {
            it.next().onServiceConnected(i8);
        }
    }

    public void m0(int i8) {
        x3.a.a("BydBTManager", "onProfileServiceDisconnected", ", profile: " + i8);
        Iterator<b3.i> it = this.f9234j.values().iterator();
        while (it.hasNext()) {
            it.next().onServiceDisconnected(i8);
        }
    }

    public final void n0(BTDevice bTDevice) {
        x3.a.a("BydBTManager", "device = " + bTDevice);
        this.f9239o = bTDevice;
        K0();
    }

    public final void o0(BluetoothDevice bluetoothDevice) {
        x3.a.a("BydBTManager", "device = " + bluetoothDevice);
        Iterator<b3.g> it = this.f9230f.values().iterator();
        while (it.hasNext()) {
            it.next().onTargetNotFound(bluetoothDevice);
        }
    }

    public final void p0(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        x3.a.a("BydBTManager", "---oobBonded---from: " + str + ", mac: " + m4.d.d(str2));
        SharedPreferences.Editor editorEdit = this.f9245u.edit();
        StringBuilder sb = new StringBuilder();
        sb.append("---oobBonded---hash: ");
        sb.append(str2.hashCode());
        x3.a.a("BydBTManager", sb.toString());
        editorEdit.putInt(str2.hashCode() + "", str2.hashCode());
        editorEdit.commit();
        if (this.f9232h == null || this.f9232h.isEmpty()) {
            return;
        }
        for (b3.d dVar : this.f9232h.values()) {
            x3.a.a("BydBTManager", "---oobBonded---callback: " + dVar);
            dVar.onOobBonded(str, str2);
        }
    }

    public boolean q0(BluetoothDevice bluetoothDevice) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return false;
        }
        if (defaultAdapter.isDiscovering()) {
            defaultAdapter.cancelDiscovery();
        }
        this.f9242r = true;
        return bluetoothDevice.createBond();
    }

    public final void r0(BluetoothDevice bluetoothDevice, boolean z7, int i8) {
        x3.a.a("BydBTManager", "receivedPairRequest", ", d: " + m4.d.f(bluetoothDevice.getName()) + ", needToInput: " + z7 + ", pairCode: " + i8);
        Iterator<b3.h> it = this.f9231g.values().iterator();
        while (it.hasNext()) {
            it.next().onReceivedPairRequest(bluetoothDevice, z7, i8);
        }
    }

    public boolean s(BluetoothDevice bluetoothDevice) {
        Boolean bool;
        Boolean bool2 = null;
        try {
            bool = (Boolean) BluetoothDevice.class.getMethod("cancelBondProcess", new Class[0]).invoke(bluetoothDevice, new Object[0]);
        } catch (IllegalAccessException e8) {
            e = e8;
        } catch (NoSuchMethodException e9) {
            e = e9;
        } catch (InvocationTargetException e10) {
            e = e10;
        }
        try {
            x3.a.c("BydBTManager", "---cancelPairDevice---result: " + bool + ", d: " + m4.d.c(bluetoothDevice));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e11) {
            e = e11;
            bool2 = bool;
            x3.a.b("BydBTManager", "---cancelPairDevice--- e: " + e.toString());
            bool = bool2;
        }
        return bool != null && bool.booleanValue();
    }

    public void s0(String str, b3.d dVar) {
        if (this.f9232h == null) {
            return;
        }
        this.f9232h.put(str, dVar);
    }

    public boolean t(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        boolean zU = u(bluetoothDevice);
        x3.a.a("BydBTManager", "---connectDevicePaired---result: " + zU + ", d: " + m4.d.c(bluetoothDevice));
        if (!zU) {
            return false;
        }
        this.f9248x = System.currentTimeMillis();
        this.f9241q.put(bluetoothDevice.getAddress(), bluetoothDevice);
        if (this.f9250z == null) {
            this.f9250z = new Timer();
        }
        this.f9250z.schedule(new d(bluetoothDevice), 15000L);
        return true;
    }

    public void t0(String str, b3.e eVar) {
        if (this.f9233i == null) {
            return;
        }
        this.f9233i.put(str, eVar);
    }

    public final boolean u(BluetoothDevice bluetoothDevice) {
        return a.c.a().c(bluetoothDevice);
    }

    public void u0(String str, b3.g gVar) {
        if (this.f9230f == null) {
            return;
        }
        B0();
        this.f9230f.put(str, gVar);
    }

    public void v() {
        x3.a.a("BydBTManager", "---disconnect---");
        if (this.f9240p != null) {
            y(this.f9240p);
            z(this.f9240p);
        }
        if (this.f9237m != null) {
            throw null;
        }
        this.f9240p = null;
    }

    public void v0(String str, b3.h hVar) {
        if (this.f9231g == null) {
            return;
        }
        this.f9231g.put(str, hVar);
    }

    public void w(BluetoothDevice bluetoothDevice, boolean z7, boolean z8) {
        x3.a.a("BydBTManager", "---disconnect---");
        c3.a aVarA = a.c.a();
        aVarA.e(bluetoothDevice);
        aVarA.f(bluetoothDevice);
        y(bluetoothDevice);
        if (!z7) {
            if (z8) {
                B(bluetoothDevice);
            } else {
                z(bluetoothDevice);
            }
        }
        if (this.f9240p == null || bluetoothDevice == null || !this.f9240p.getAddress().equals(bluetoothDevice.getAddress())) {
            return;
        }
        this.f9240p = null;
    }

    public void w0(String str, b3.c cVar) {
        if (this.f9235k == null) {
            return;
        }
        this.f9235k.put(str, cVar);
    }

    public void x() {
        x3.a.a("BydBTManager", "---disconnectAllDevice---");
        List<BluetoothDevice> listI = I();
        if (listI == null || listI.size() <= 0) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : listI) {
            y(bluetoothDevice);
            B(bluetoothDevice);
        }
    }

    public void x0(String str, b3.j jVar) {
        if (this.f9229e == null) {
            return;
        }
        this.f9229e.put(str, jVar);
    }

    public void y(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        x3.a.a("BydBTManager", "disconnectAllProfiles", "device = " + m4.d.f(bluetoothDevice.getName()));
        m4.a.c(BluetoothAdapter.getDefaultAdapter(), "disconnectAllEnabledProfiles", BluetoothDevice.class, bluetoothDevice);
        h0(bluetoothDevice);
    }

    public void y0(String str, b3.b bVar) {
        if (this.f9236l == null) {
            return;
        }
        this.f9236l.put(str, bVar);
    }

    public void z(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        x3.a.a("BydBTManager", "disconnectOnAclLayer", "device = " + m4.d.c(bluetoothDevice));
        Class[] clsArr = {BluetoothDevice.class, Integer.TYPE};
        Object[] objArr = {bluetoothDevice, 1};
        if (!P(bluetoothDevice.getAddress())) {
            m4.a.d(BluetoothAdapter.getDefaultAdapter(), "removeAcl", clsArr, objArr);
            return;
        }
        x3.a.a("BydBTManager", "not do removeAcl, reason: device = " + m4.d.c(bluetoothDevice) + "--inCPAAList");
    }

    public void z0(String str, b3.i iVar) {
        if (this.f9234j == null) {
            this.f9234j = new ConcurrentHashMap<>();
        }
        this.f9234j.put(str, iVar);
    }

    public b() {
        this.f9228d = false;
        this.f9242r = false;
        this.f9246v = false;
        this.f9247w = 0L;
        this.f9248x = 0L;
        this.f9249y = null;
        this.f9250z = null;
    }
}
