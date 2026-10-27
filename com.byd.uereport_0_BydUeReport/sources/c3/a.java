package c3;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import m4.d;

/* JADX INFO: compiled from: BydProfileManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile ConcurrentHashMap<Integer, BluetoothProfile> f2307a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile b f2308b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c3.b f2309c;

    /* JADX INFO: compiled from: BydProfileManager.java */
    public class b implements BluetoothProfile.ServiceListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<a> f2310a;

        public b(a aVar) {
            this.f2310a = new WeakReference<>(aVar);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i8, BluetoothProfile bluetoothProfile) {
            WeakReference<a> weakReference = this.f2310a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f2310a.get().s(i8, bluetoothProfile);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i8) {
            WeakReference<a> weakReference = this.f2310a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f2310a.get().t(i8);
        }
    }

    /* JADX INFO: compiled from: BydProfileManager.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static a f2312a = new a();

        public static a a() {
            return f2312a;
        }
    }

    public boolean c(BluetoothDevice bluetoothDevice) {
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothProfile) this.f2307a.get(16);
        BluetoothA2dpSink bluetoothA2dpSink = (BluetoothProfile) this.f2307a.get(11);
        x3.a.a("BydProfileManager", "---connectHfpAndA2dpProfile---profile: " + bluetoothHeadsetClient + ", a2dpProfile: " + bluetoothA2dpSink);
        if (bluetoothDevice == null || bluetoothHeadsetClient == null || bluetoothA2dpSink == null) {
            return false;
        }
        x3.a.a("BydProfileManager", "---connectHfpAndA2dpProfile---device: " + d.c(bluetoothDevice));
        if (bluetoothHeadsetClient instanceof BluetoothHeadsetClient) {
            bluetoothHeadsetClient.connect(bluetoothDevice);
        }
        if (bluetoothA2dpSink instanceof BluetoothA2dpSink) {
            BluetoothA2dpSink bluetoothA2dpSink2 = bluetoothA2dpSink;
            bluetoothA2dpSink2.setPriority2(bluetoothDevice, 100);
            bluetoothA2dpSink2.connect(bluetoothDevice);
        }
        return true;
    }

    public void d(BluetoothDevice bluetoothDevice) {
        BluetoothProfile bluetoothProfile = this.f2307a.get(16);
        x3.a.a("BydProfileManager", "---disableHfp---profile: " + bluetoothProfile + ", device: " + d.c(bluetoothDevice));
        if (bluetoothProfile == null || !(bluetoothProfile instanceof BluetoothHeadsetClient)) {
            return;
        }
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothHeadsetClient) bluetoothProfile;
        int priority = bluetoothHeadsetClient.getPriority(bluetoothDevice);
        x3.a.a("BydProfileManager", "---disableHfp---priority: " + priority);
        if (priority != 0) {
            v(bluetoothHeadsetClient, bluetoothDevice, 0);
        }
    }

    public void e(BluetoothDevice bluetoothDevice) {
        BluetoothA2dpSink bluetoothA2dpSink = (BluetoothProfile) this.f2307a.get(11);
        x3.a.a("BydProfileManager", "---disconnectA2dp---profile: " + bluetoothA2dpSink + ", device: " + d.c(bluetoothDevice));
        if (bluetoothA2dpSink == null || !(bluetoothA2dpSink instanceof BluetoothA2dpSink)) {
            return;
        }
        bluetoothA2dpSink.disconnect(bluetoothDevice);
    }

    public void f(BluetoothDevice bluetoothDevice) {
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothProfile) this.f2307a.get(16);
        x3.a.a("BydProfileManager", "---disconnectHfp---profile: " + bluetoothHeadsetClient + ", device: " + d.c(bluetoothDevice));
        if (bluetoothHeadsetClient == null || !(bluetoothHeadsetClient instanceof BluetoothHeadsetClient)) {
            return;
        }
        bluetoothHeadsetClient.disconnect(bluetoothDevice);
    }

    public void g(BluetoothDevice bluetoothDevice) {
        BluetoothProfile bluetoothProfile = this.f2307a.get(16);
        x3.a.a("BydProfileManager", "---enableHfp---profile: " + bluetoothProfile + ", device: " + d.c(bluetoothDevice));
        if (bluetoothProfile == null || !(bluetoothProfile instanceof BluetoothHeadsetClient)) {
            return;
        }
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothHeadsetClient) bluetoothProfile;
        int priority = bluetoothHeadsetClient.getPriority(bluetoothDevice);
        x3.a.a("BydProfileManager", "---enableHfp---priority: " + priority);
        if (100 != priority) {
            v(bluetoothHeadsetClient, bluetoothDevice, 100);
        }
    }

    public void h(BluetoothDevice bluetoothDevice, boolean z7) {
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothProfile) this.f2307a.get(16);
        if (bluetoothHeadsetClient == null || !(bluetoothHeadsetClient instanceof BluetoothHeadsetClient)) {
            return;
        }
        BluetoothHeadsetClient bluetoothHeadsetClient2 = bluetoothHeadsetClient;
        if (z7) {
            bluetoothHeadsetClient2.setPriority2(bluetoothDevice, 100);
        } else {
            bluetoothHeadsetClient2.setPriority2(bluetoothDevice, 0);
        }
    }

    public void i(boolean z7) {
        BluetoothPan bluetoothPan = (BluetoothProfile) this.f2307a.get(5);
        if (bluetoothPan == null) {
            return;
        }
        BluetoothPan bluetoothPan2 = bluetoothPan;
        if (bluetoothPan2.isTetheringOn() != z7) {
            bluetoothPan2.setBluetoothTethering(z7);
        }
    }

    public int j(BluetoothDevice bluetoothDevice) {
        int i8 = o(bluetoothDevice) ? 1 : 0;
        if (n(bluetoothDevice)) {
            i8++;
        }
        if (q(bluetoothDevice)) {
            i8++;
        }
        return p(bluetoothDevice) ? i8 + 1 : i8;
    }

    public Bundle k(BluetoothDevice bluetoothDevice) {
        BluetoothHeadsetClient bluetoothHeadsetClient = (BluetoothProfile) this.f2307a.get(16);
        x3.a.a("BydProfileManager", "---getCurrentAgEvents---profile: " + bluetoothHeadsetClient + ", device: " + d.c(bluetoothDevice));
        if (bluetoothHeadsetClient == null || !(bluetoothHeadsetClient instanceof BluetoothHeadsetClient)) {
            return null;
        }
        return bluetoothHeadsetClient.getCurrentAgEvents(bluetoothDevice);
    }

    public BluetoothProfile l(int i8) {
        return this.f2307a.get(Integer.valueOf(i8));
    }

    public synchronized void m(Context context, c3.b bVar) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.f2309c = bVar;
        x3.a.a("BydProfileManager", "---initProfileProxy---");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        defaultAdapter.getProfileProxy(applicationContext, this.f2308b, 16);
        defaultAdapter.getProfileProxy(applicationContext, this.f2308b, 11);
        defaultAdapter.getProfileProxy(applicationContext, this.f2308b, 5);
    }

    public boolean n(BluetoothDevice bluetoothDevice) {
        return r(this.f2307a.get(11), bluetoothDevice);
    }

    public boolean o(BluetoothDevice bluetoothDevice) {
        return r(this.f2307a.get(16), bluetoothDevice);
    }

    public boolean p(BluetoothDevice bluetoothDevice) {
        return r(this.f2307a.get(19), bluetoothDevice);
    }

    public boolean q(BluetoothDevice bluetoothDevice) {
        return r(this.f2307a.get(5), bluetoothDevice);
    }

    public boolean r(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        return (bluetoothProfile == null || bluetoothDevice == null || bluetoothProfile.getConnectionState(bluetoothDevice) != 2) ? false : true;
    }

    public final void s(int i8, BluetoothProfile bluetoothProfile) {
        x3.a.a("BydProfileManager", "---onServiceConnected---profile: " + i8 + ", proxy: " + bluetoothProfile);
        this.f2307a.put(Integer.valueOf(i8), bluetoothProfile);
        c3.b bVar = this.f2309c;
        if (bVar != null) {
            bVar.onServiceConnected(i8);
        }
    }

    public final void t(int i8) {
        x3.a.a("BydProfileManager", "---onServiceDisconnected---profile: " + i8);
        this.f2307a.remove(Integer.valueOf(i8));
        c3.b bVar = this.f2309c;
        if (bVar != null) {
            bVar.onServiceDisconnected(i8);
        }
    }

    public void u() {
        x3.a.a("BydProfileManager", "----release-----");
        if (this.f2307a != null) {
            this.f2307a.clear();
        }
        this.f2308b = null;
        this.f2309c = null;
    }

    public final void v(BluetoothHeadsetClient bluetoothHeadsetClient, BluetoothDevice bluetoothDevice, int i8) {
        if (bluetoothHeadsetClient == null || bluetoothDevice == null) {
            return;
        }
        try {
            Method method = BluetoothHeadsetClient.class.getMethod("setPriority2", BluetoothDevice.class, Integer.TYPE);
            x3.a.a("BydProfileManager", "---setPriority2---method: " + method + ", priority: " + i8);
            method.invoke(bluetoothHeadsetClient, bluetoothDevice, Integer.valueOf(i8));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            e8.printStackTrace();
            x3.a.a("BydProfileManager", "---setPriority2---err, e: " + e8.toString());
        }
    }

    public a() {
        this.f2308b = new b(this);
        this.f2307a = new ConcurrentHashMap<>();
    }
}
