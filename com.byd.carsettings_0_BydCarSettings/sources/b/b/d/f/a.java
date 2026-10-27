package b.b.d.f;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import b.b.m.g.f;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: BydProfileManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile ConcurrentHashMap<Integer, BluetoothProfile> f616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile b f617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.d.f.b f618c;

    /* JADX INFO: compiled from: BydProfileManager.java */
    public class b implements BluetoothProfile.ServiceListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<a> f619a;

        public b(a aVar) {
            this.f619a = new WeakReference<>(aVar);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            WeakReference<a> weakReference = this.f619a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f619a.get().o(i, bluetoothProfile);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            WeakReference<a> weakReference = this.f619a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f619a.get().p(i);
        }
    }

    /* JADX INFO: compiled from: BydProfileManager.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static a f621a = new a();

        public static a a() {
            return f621a;
        }
    }

    public void c(BluetoothDevice bluetoothDevice) {
        BluetoothProfile bluetoothProfile = this.f616a.get(16);
        b.b.m.c.a.a("BydProfileManager", "---disableHfp---profile: " + bluetoothProfile + ", device: " + f.d(bluetoothDevice));
        if (bluetoothProfile == null || !(bluetoothProfile instanceof BluetoothHeadsetClient)) {
            return;
        }
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothHeadsetClient) bluetoothProfile;
        int priority = bluetoothHeadsetClient.getPriority(bluetoothDevice);
        b.b.m.c.a.a("BydProfileManager", "---disableHfp---priority: " + priority);
        if (priority != 0) {
            r(bluetoothHeadsetClient, bluetoothDevice, 0);
        }
    }

    public void d(BluetoothDevice bluetoothDevice) {
        BluetoothA2dpSink bluetoothA2dpSink = (BluetoothProfile) this.f616a.get(11);
        b.b.m.c.a.a("BydProfileManager", "---disconnectA2dp---profile: " + bluetoothA2dpSink + ", device: " + f.d(bluetoothDevice));
        if (bluetoothA2dpSink == null || !(bluetoothA2dpSink instanceof BluetoothA2dpSink)) {
            return;
        }
        bluetoothA2dpSink.disconnect(bluetoothDevice);
    }

    public void e(BluetoothDevice bluetoothDevice) {
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothProfile) this.f616a.get(16);
        b.b.m.c.a.a("BydProfileManager", "---disconnectHfp---profile: " + bluetoothHeadsetClient + ", device: " + f.d(bluetoothDevice));
        if (bluetoothHeadsetClient == null || !(bluetoothHeadsetClient instanceof BluetoothHeadsetClient)) {
            return;
        }
        bluetoothHeadsetClient.disconnect(bluetoothDevice);
    }

    public void f(BluetoothDevice bluetoothDevice) {
        BluetoothProfile bluetoothProfile = this.f616a.get(16);
        b.b.m.c.a.a("BydProfileManager", "---enableHfp---profile: " + bluetoothProfile + ", device: " + f.d(bluetoothDevice));
        if (bluetoothProfile == null || !(bluetoothProfile instanceof BluetoothHeadsetClient)) {
            return;
        }
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothHeadsetClient) bluetoothProfile;
        int priority = bluetoothHeadsetClient.getPriority(bluetoothDevice);
        b.b.m.c.a.a("BydProfileManager", "---enableHfp---priority: " + priority);
        if (100 != priority) {
            r(bluetoothHeadsetClient, bluetoothDevice, 100);
        }
    }

    public void g(BluetoothDevice bluetoothDevice, boolean z) {
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothProfile) this.f616a.get(16);
        if (bluetoothHeadsetClient == null || !(bluetoothHeadsetClient instanceof BluetoothHeadsetClient)) {
            return;
        }
        BluetoothHeadsetClient bluetoothHeadsetClient2 = bluetoothHeadsetClient;
        if (z) {
            bluetoothHeadsetClient2.setPriority2(bluetoothDevice, 100);
        } else {
            bluetoothHeadsetClient2.setPriority2(bluetoothDevice, 0);
        }
    }

    public int h(BluetoothDevice bluetoothDevice) {
        int i = k(bluetoothDevice) ? 1 : 0;
        if (j(bluetoothDevice)) {
            i++;
        }
        if (m(bluetoothDevice)) {
            i++;
        }
        return l(bluetoothDevice) ? i + 1 : i;
    }

    public synchronized void i(Context context, b.b.d.f.b bVar) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.f618c = bVar;
        b.b.m.c.a.a("BydProfileManager", "---initProfileProxy---");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        defaultAdapter.getProfileProxy(applicationContext, this.f617b, 16);
        defaultAdapter.getProfileProxy(applicationContext, this.f617b, 11);
        defaultAdapter.getProfileProxy(applicationContext, this.f617b, 5);
    }

    public boolean j(BluetoothDevice bluetoothDevice) {
        return n(this.f616a.get(11), bluetoothDevice);
    }

    public boolean k(BluetoothDevice bluetoothDevice) {
        return n(this.f616a.get(16), bluetoothDevice);
    }

    public boolean l(BluetoothDevice bluetoothDevice) {
        return n(this.f616a.get(19), bluetoothDevice);
    }

    public boolean m(BluetoothDevice bluetoothDevice) {
        return n(this.f616a.get(5), bluetoothDevice);
    }

    public boolean n(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        return (bluetoothProfile == null || bluetoothDevice == null || bluetoothProfile.getConnectionState(bluetoothDevice) != 2) ? false : true;
    }

    public final void o(int i, BluetoothProfile bluetoothProfile) {
        b.b.m.c.a.a("BydProfileManager", "---onServiceConnected---profile: " + i + ", proxy: " + bluetoothProfile);
        this.f616a.put(Integer.valueOf(i), bluetoothProfile);
        b.b.d.f.b bVar = this.f618c;
        if (bVar != null) {
            bVar.a(i);
        }
    }

    public final void p(int i) {
        b.b.m.c.a.a("BydProfileManager", "---onServiceDisconnected---profile: " + i);
        this.f616a.remove(Integer.valueOf(i));
        b.b.d.f.b bVar = this.f618c;
        if (bVar != null) {
            bVar.onServiceDisconnected(i);
        }
    }

    public void q() {
        b.b.m.c.a.a("BydProfileManager", "----release-----");
        if (this.f616a != null) {
            this.f616a.clear();
        }
        this.f617b = null;
        this.f618c = null;
    }

    public final void r(BluetoothHeadsetClient bluetoothHeadsetClient, BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothHeadsetClient == null || bluetoothDevice == null) {
            return;
        }
        try {
            Method method = BluetoothHeadsetClient.class.getMethod("setPriority2", BluetoothDevice.class, Integer.TYPE);
            b.b.m.c.a.a("BydProfileManager", "---setPriority2---method: " + method + ", priority: " + i);
            method.invoke(bluetoothHeadsetClient, bluetoothDevice, Integer.valueOf(i));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            b.b.m.c.a.a("BydProfileManager", "---setPriority2---err, e: " + e2.toString());
        }
    }

    public a() {
        this.f617b = new b(this);
        this.f616a = new ConcurrentHashMap<>();
    }
}
