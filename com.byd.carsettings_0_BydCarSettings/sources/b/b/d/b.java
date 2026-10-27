package b.b.d;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import b.b.d.c.a;
import b.b.d.e.h;
import b.b.d.e.i;
import b.b.d.f.a;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.ConnectState;
import com.byd.bt.receive.BtActionReceiver;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: compiled from: BydBTManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f592a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BtActionReceiver f593b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile boolean f594c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, i> f595d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b.b.d.e.e> f596e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile ConcurrentHashMap<String, b.b.d.e.b> f597f;
    public b.b.d.a g;
    public volatile BluetoothDevice h;
    public volatile BTDevice i;
    public volatile BluetoothDevice j;
    public ConcurrentHashMap<String, BluetoothDevice> k;
    public volatile boolean l;
    public SharedPreferences m;
    public SharedPreferences n;
    public SharedPreferences o;
    public volatile boolean p;
    public volatile long q;
    public volatile long r;
    public volatile Timer s;
    public volatile Timer t;

    /* JADX INFO: compiled from: BydBTManager.java */
    public class a implements b.b.d.f.b {
        public a() {
        }

        @Override // b.b.d.f.b
        public void a(int i) {
            b.this.C(i);
        }

        @Override // b.b.d.f.b
        public void onServiceDisconnected(int i) {
            b.this.D(i);
        }
    }

    /* JADX INFO: renamed from: b.b.d.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydBTManager.java */
    public class C0028b implements b.b.d.e.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f599a;

        public /* synthetic */ C0028b(b bVar, b bVar2, a aVar) {
            this(bVar2);
        }

        @Override // b.b.d.e.b
        public void p(int i) {
            b.b.m.c.a.a("BydBTManager", "---onNewDevicePair---op:" + i);
            WeakReference<b> weakReference = this.f599a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            Iterator it = this.f599a.get().f597f.values().iterator();
            while (it.hasNext()) {
                ((b.b.d.e.b) it.next()).p(i);
            }
        }

        public C0028b(b bVar) {
            this.f599a = new WeakReference<>(bVar);
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class c implements b.b.d.e.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f601a;

        public c(b bVar) {
            this.f601a = new WeakReference<>(bVar);
        }

        @Override // b.b.d.e.e
        public void k(BluetoothDevice bluetoothDevice, ConnectState connectState, boolean z) {
            WeakReference<b> weakReference = this.f601a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            b.b.m.c.a.a("BydBTManager", "onConnectStateChanged", "device: " + b.b.m.g.f.d(bluetoothDevice));
            this.f601a.get().y(bluetoothDevice, connectState, z);
        }

        @Override // b.b.d.e.e
        public void n(BTDevice bTDevice) {
            b.b.m.c.a.a("BydBTManager", "onConnectedBtNameChanged", "device: " + bTDevice);
            this.f601a.get().z(bTDevice);
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class d implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f603a;

        public d(b bVar) {
            this.f603a = new WeakReference<>(bVar);
        }

        @Override // b.b.d.e.i
        public void c() {
            WeakReference<b> weakReference = this.f603a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f603a.get().l = false;
            Iterator it = this.f603a.get().f595d.values().iterator();
            while (it.hasNext()) {
                ((i) it.next()).c();
            }
        }

        @Override // b.b.d.e.i
        public void f() {
            WeakReference<b> weakReference = this.f603a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            Iterator it = this.f603a.get().f595d.values().iterator();
            while (it.hasNext()) {
                ((i) it.next()).f();
            }
        }

        @Override // b.b.d.e.i
        public void l() {
            WeakReference<b> weakReference = this.f603a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            a.c.a().f();
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BluetoothDevice f605a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f606b;

        public e() {
        }

        public /* synthetic */ e(b bVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: BydBTManager.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f608a = new b(null);

        public static b a() {
            return f608a;
        }
    }

    public /* synthetic */ b(a aVar) {
        this();
    }

    public void A(BluetoothDevice bluetoothDevice) {
        if (this.j == null) {
            this.j = j();
        }
        b.b.m.c.a.a("BydBTManager", "onDeviceConnected", ", device: " + b.b.m.g.f.d(bluetoothDevice) + ", deviceConnected: " + b.b.m.g.f.d(this.j));
        if (bluetoothDevice != null) {
            N(bluetoothDevice.getAddress(), System.currentTimeMillis());
        }
        this.h = null;
        this.j = bluetoothDevice;
        ConcurrentHashMap<String, BluetoothDevice> concurrentHashMap = this.k;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(bluetoothDevice.getAddress());
        }
    }

    public void B(BluetoothDevice bluetoothDevice) {
        b.b.m.c.a.a("BydBTManager", "onDeviceDisconnected", ", device: " + b.b.m.g.f.d(bluetoothDevice));
        this.h = null;
        if (this.j != null && this.j.getAddress().equals(bluetoothDevice.getAddress())) {
            this.j = null;
        }
        if (bluetoothDevice != null) {
            O(bluetoothDevice.getAddress(), System.currentTimeMillis());
        }
        ConcurrentHashMap<String, BluetoothDevice> concurrentHashMap = this.k;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(bluetoothDevice.getAddress());
        }
    }

    public void C(int i) {
        b.b.m.c.a.a("BydBTManager", "onProfileServiceConnected", ", profile: " + i);
    }

    public void D(int i) {
        b.b.m.c.a.a("BydBTManager", "onProfileServiceDisconnected", ", profile: " + i);
    }

    public final void E(BTDevice bTDevice) {
        b.b.m.c.a.a("BydBTManager", "device = " + bTDevice);
        this.i = bTDevice;
        P();
    }

    public void F(String str, b.b.d.e.d dVar) {
    }

    public void G(String str, b.b.d.e.e eVar) {
        if (this.f596e == null) {
            return;
        }
        this.f596e.put(str, eVar);
    }

    public void H(String str, i iVar) {
        if (this.f595d == null) {
            return;
        }
        this.f595d.put(str, iVar);
    }

    public void I(String str, h hVar) {
    }

    public final void J(Context context) {
        b.b.m.c.a.a("BydBTManager", "----registerReceiver-----");
        if (this.f593b == null) {
            this.f593b = new BtActionReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.NAME_CHANGED");
        intentFilter.addAction("android_auto_new_device_pair");
        context.getApplicationContext().registerReceiver(this.f593b, intentFilter);
    }

    public void K() {
        b.b.m.c.a.a("BydBTManager", "-----release-----");
        a.c.a().d();
        b.b.d.d.a.a();
        X();
        W();
        P();
        if (this.f595d == null) {
            this.f595d = new ConcurrentHashMap<>();
        }
        if (this.f596e != null) {
            this.f596e.clear();
        }
        ConcurrentHashMap<String, BluetoothDevice> concurrentHashMap = this.k;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        if (this.g != null) {
            throw null;
        }
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        this.p = false;
        if (this.t != null) {
            this.t.cancel();
            this.t = null;
        }
    }

    public void L() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        b.b.m.c.a.a("BydBTManager", "--setBtDiscoverable--mode = " + defaultAdapter.getScanMode());
        b.b.m.c.a.a("BydBTManager", "--setBtDiscoverable--0--timeout = " + ((Integer) b.b.m.g.a.a(defaultAdapter, "getDiscoverableTimeout")).intValue());
        try {
            Method method = BluetoothAdapter.class.getMethod("setScanMode", Integer.TYPE, Long.TYPE);
            method.setAccessible(true);
            b.b.m.c.a.a("BydBTManager", "--setBtDiscoverable--setScanMode result = " + ((Boolean) method.invoke(defaultAdapter, 23, 15000L)).booleanValue());
        } catch (Exception e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("BydBTManager", "setDiscoverableTimeout failure, e:" + e2.toString());
        }
        b.b.m.c.a.a("BydBTManager", "--setBtDiscoverable--1--timeout1 = " + ((Integer) b.b.m.g.a.a(defaultAdapter, "getDiscoverableTimeout")).intValue());
    }

    public void M(String str) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || str == null || str.isEmpty()) {
            return;
        }
        b.b.m.c.a.a("BydBTManager", "---setBtName---name: " + str);
        defaultAdapter.setName(str);
        a.c.a().e();
    }

    public final void N(String str, long j) {
        SharedPreferences sharedPreferences = this.m;
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putLong(str.hashCode() + "", j);
        editorEdit.commit();
    }

    public final void O(String str, long j) {
        SharedPreferences sharedPreferences = this.n;
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putLong(str.hashCode() + "", j);
        editorEdit.commit();
    }

    public void P() {
        if (u()) {
            return;
        }
        b.b.m.c.a.a("BydBTManager", "---stopScan---cancelDiscovery-----");
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        if (this.s != null) {
            this.s.cancel();
            this.s = null;
        }
    }

    public void Q() {
        Iterator<BluetoothDevice> it = BluetoothAdapter.getDefaultAdapter().getBondedDevices().iterator();
        while (it.hasNext()) {
            b.b.m.c.a.a("BydBTManager", "unpairAllDevice result:" + ((Boolean) b.b.m.g.a.a(it.next(), "removeBond")).booleanValue());
        }
    }

    public void R(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        b.b.m.c.a.a("BydBTManager", "unpairDevice result:" + ((Boolean) b.b.m.g.a.a(bluetoothDevice, "removeBond")).booleanValue());
    }

    public void S(String str) {
    }

    public void T(String str) {
        if (this.f596e == null) {
            return;
        }
        this.f596e.remove(str);
    }

    public void U(String str) {
        if (this.f595d == null) {
            return;
        }
        this.f595d.remove(str);
    }

    public void V(String str) {
    }

    public final void W() {
        Context context;
        b.b.m.c.a.a("BydBTManager", "----unregisterReceiver-----");
        BtActionReceiver btActionReceiver = this.f593b;
        if (btActionReceiver == null || (context = this.f592a) == null) {
            return;
        }
        try {
            context.unregisterReceiver(btActionReceiver);
        } catch (Exception unused) {
        }
    }

    public final void X() {
        b.b.m.c.a.a("BydBTManager", "----unregisterScanReceiver-----isScannerRegistered: " + this.f594c);
    }

    public void f(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        b.b.m.c.a.a("BydBTManager", "disconnectAllProfiles", "device = " + b.b.m.g.f.g(bluetoothDevice.getName()));
        b.b.m.g.a.b(BluetoothAdapter.getDefaultAdapter(), "disconnectAllEnabledProfiles", BluetoothDevice.class, bluetoothDevice);
        B(bluetoothDevice);
    }

    public void g(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        b.b.m.c.a.a("BydBTManager", "disconnectOnAclLayer", "device = " + b.b.m.g.f.d(bluetoothDevice));
        Class[] clsArr = {BluetoothDevice.class, Integer.TYPE};
        Object[] objArr = {bluetoothDevice, 1};
        if (!r(bluetoothDevice.getAddress())) {
            b.b.m.g.a.c(BluetoothAdapter.getDefaultAdapter(), "removeAcl", clsArr, objArr);
            return;
        }
        b.b.m.c.a.a("BydBTManager", "not do removeAcl, reason: device = " + b.b.m.g.f.d(bluetoothDevice) + "inCPAAList");
    }

    public void h(boolean z) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return;
        }
        if (z) {
            defaultAdapter.enable();
        } else {
            defaultAdapter.disable();
        }
    }

    public BluetoothDevice i(String str) {
        if (TextUtils.isEmpty(str)) {
            b.b.m.c.a.c("BydBTManager", "getBondedDeviceByMacId", ", mac is empty");
            return null;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            b.b.m.c.a.c("BydBTManager", "getBondedDeviceByMacId", ", adapter is empty");
            return null;
        }
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            b.b.m.c.a.c("BydBTManager", "getBondedDeviceByMacId", ", set is empty");
            return null;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (str.equals(bluetoothDevice.getAddress())) {
                return bluetoothDevice;
            }
        }
        b.b.m.c.a.c("BydBTManager", "getBondedDeviceByMacId", ", mac not match in set:" + bondedDevices);
        return null;
    }

    public BluetoothDevice j() {
        List<BluetoothDevice> listK = k();
        a aVar = null;
        if (listK == null || listK.isEmpty()) {
            return null;
        }
        b.b.d.f.a aVarA = a.c.a();
        e eVar = new e(this, aVar);
        eVar.f606b = -1;
        for (BluetoothDevice bluetoothDevice : listK) {
            int iH = aVarA.h(bluetoothDevice);
            if (iH > eVar.f606b) {
                eVar.f606b = iH;
                eVar.f605a = bluetoothDevice;
            }
        }
        return eVar.f605a;
    }

    public List<BluetoothDevice> k() {
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (((Boolean) b.b.m.g.a.a(bluetoothDevice, "isConnected")).booleanValue()) {
                arrayList.add(bluetoothDevice);
            }
        }
        return arrayList;
    }

    public List<BluetoothDevice> l() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            b.b.m.c.a.d("BydBTManager", "getConnectedDevices", ", set.size: " + bondedDevices.size());
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if (((Boolean) b.b.m.g.a.a(bluetoothDevice, "isConnected")).booleanValue()) {
                    arrayList.add(bluetoothDevice);
                }
            }
        }
        return arrayList;
    }

    public long m(String str) {
        SharedPreferences sharedPreferences = this.m;
        if (sharedPreferences == null) {
            return 0L;
        }
        return sharedPreferences.getLong(str.hashCode() + "", 0L);
    }

    public final long n(String str) {
        SharedPreferences sharedPreferences = this.n;
        if (sharedPreferences == null) {
            return 0L;
        }
        return sharedPreferences.getLong(str.hashCode() + "", 0L);
    }

    public BluetoothDevice o() {
        List<BluetoothDevice> listK = k();
        if (listK == null || listK.isEmpty()) {
            b.b.m.c.a.c("BydBTManager", "---getDisplayConnectedDevice---list: " + listK);
            return null;
        }
        b.b.m.c.a.a("BydBTManager", "---getDisplayConnectedDevice---list.size: " + listK.size());
        if (listK.size() < 2) {
            return listK.get(0);
        }
        b.b.d.f.a aVarA = a.c.a();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (BluetoothDevice bluetoothDevice : listK) {
            if (aVarA.k(bluetoothDevice)) {
                arrayList.add(bluetoothDevice);
            }
            if (aVarA.j(bluetoothDevice)) {
                arrayList2.add(bluetoothDevice);
            }
        }
        b.b.m.c.a.a("BydBTManager", "---getDisplayConnectedDevice---hfpList.size: " + arrayList.size());
        b.b.m.c.a.a("BydBTManager", "---getDisplayConnectedDevice---a2dpList.size: " + arrayList2.size());
        return !arrayList.isEmpty() ? arrayList.size() == 1 ? arrayList.get(0) : p(arrayList) : !arrayList2.isEmpty() ? arrayList2.size() == 1 ? arrayList2.get(0) : p(arrayList2) : p(listK);
    }

    public BluetoothDevice p(List<BluetoothDevice> list) {
        b.b.d.f.a aVarA = a.c.a();
        BluetoothDevice bluetoothDevice = null;
        int i = -1;
        for (BluetoothDevice bluetoothDevice2 : list) {
            int iH = aVarA.h(bluetoothDevice2);
            if (iH > i) {
                bluetoothDevice = bluetoothDevice2;
                i = iH;
            }
        }
        return bluetoothDevice;
    }

    public BluetoothDevice q() {
        return this.h;
    }

    public final boolean r(String str) {
        b.b.a.e.a aVarA = b.b.a.e.c.b().a();
        List<AndroidAutoDevice> listC = aVarA.c();
        List<CarPlayDevice> listA = aVarA.a();
        if (listC != null) {
            Iterator<AndroidAutoDevice> it = listC.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().getBtAddr())) {
                    b.b.m.c.a.a("BydBTManager", "device in aa");
                    return true;
                }
            }
        }
        if (listA == null) {
            return false;
        }
        Iterator<CarPlayDevice> it2 = listA.iterator();
        while (it2.hasNext()) {
            if (str.equals(it2.next().getBtAddr())) {
                b.b.m.c.a.a("BydBTManager", "device in cp");
                return true;
            }
        }
        return false;
    }

    public boolean s(Context context) {
        b.b.m.c.a.a("BydBTManager", "----init-----");
        if (context == null || this.p) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        this.f592a = applicationContext;
        this.m = applicationContext.getSharedPreferences("key_bt_connected_time", 0);
        this.n = applicationContext.getSharedPreferences("bt_disconnected_time", 0);
        this.o = applicationContext.getSharedPreferences("bt_oob", 0);
        L();
        a.c.a().a();
        b.b.d.d.a.d("bt_sate", new d(this));
        b.b.d.d.a.d("bt_connect_state", new c(this));
        b.b.d.d.a.d("aa_new_device_pair", new C0028b(this, this, null));
        J(this.f592a);
        if (this.f595d == null) {
            this.f595d = new ConcurrentHashMap<>();
        }
        if (this.f596e == null) {
            this.f596e = new ConcurrentHashMap<>();
        }
        if (this.f597f == null) {
            this.f597f = new ConcurrentHashMap<>();
        }
        a.c.a().i(applicationContext, new a());
        if (this.k == null) {
            this.k = new ConcurrentHashMap<>();
        }
        this.p = true;
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public final boolean t(BluetoothDevice bluetoothDevice) {
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

    public boolean u() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter == null || !defaultAdapter.isEnabled();
    }

    public boolean v() {
        int iIntValue = ((Integer) b.b.m.g.a.a(BluetoothAdapter.getDefaultAdapter(), "getConnectionState")).intValue();
        b.b.m.c.a.a("BydBTManager", "isConnected state: " + iIntValue + ", BluetoothAdapter.STATE_CONNECTED: 2");
        return iIntValue == 2;
    }

    public boolean w() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public boolean x() {
        return this.l;
    }

    public final void y(BluetoothDevice bluetoothDevice, ConnectState connectState, boolean z) {
        Iterator<b.b.d.e.e> it = this.f596e.values().iterator();
        while (it.hasNext()) {
            it.next().k(bluetoothDevice, connectState, z);
        }
        if (connectState != ConnectState.CONNECTED) {
            if (connectState == ConnectState.DISCONNECTED) {
                B(bluetoothDevice);
            }
        } else if (t(bluetoothDevice)) {
            A(bluetoothDevice);
        } else {
            b.b.m.c.a.a("BydBTManager", "当前设备为非配对的设备,return");
        }
    }

    public final void z(BTDevice bTDevice) {
        b.b.m.c.a.a("BydBTManager", "---onConnectedBtNameChanged--device = " + bTDevice);
        if (this.h != null) {
            E(bTDevice);
        }
        bTDevice.setPreConnectedTime(m(bTDevice.getMac()));
        bTDevice.setPreDisconnectedTime(n(bTDevice.getMac()));
        Iterator<b.b.d.e.e> it = this.f596e.values().iterator();
        while (it.hasNext()) {
            it.next().n(bTDevice);
        }
    }

    public b() {
        this.f594c = false;
        this.l = false;
        this.p = false;
        this.q = 0L;
        this.r = 0L;
        this.s = null;
        this.t = null;
    }
}
