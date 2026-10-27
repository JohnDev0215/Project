package b.b.d.c;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: BTAdvertiser.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AdvertiseSettings f609a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f610b;

    /* JADX INFO: compiled from: BTAdvertiser.java */
    public class b extends AdvertiseCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<a> f611a;

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartFailure(int i) {
            b.b.m.c.a.a("BTAdvertiser", "--onStartFailure--errorCode: " + i);
            WeakReference<a> weakReference = this.f611a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f611a.get().b(i);
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartSuccess(AdvertiseSettings advertiseSettings) {
            b.b.m.c.a.a("BTAdvertiser", "--onStartSuccess--settingsInEffect: " + advertiseSettings.toString());
            WeakReference<a> weakReference = this.f611a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f611a.get().c(advertiseSettings);
        }

        public b(a aVar) {
            this.f611a = new WeakReference<>(aVar);
        }
    }

    /* JADX INFO: compiled from: BTAdvertiser.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static a f613a = new a();

        public static a a() {
            return f613a;
        }
    }

    public void a() {
        this.f609a = new AdvertiseSettings.Builder().setAdvertiseMode(1).setTxPowerLevel(3).setConnectable(true).build();
        this.f610b = new b(this);
    }

    public void b(int i) {
        b.b.m.c.a.a("BTAdvertiser", "--onAdvertiseStartFailure--errorCode: " + i);
    }

    public void c(AdvertiseSettings advertiseSettings) {
        b.b.m.c.a.a("BTAdvertiser", "--onAdvertiseStartSuccess--as: " + advertiseSettings.toString());
    }

    public void d() {
        this.f609a = null;
        this.f610b = null;
    }

    public void e() {
    }

    public void f() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter.isEnabled() && (bluetoothLeAdvertiser = defaultAdapter.getBluetoothLeAdvertiser()) != null) {
            bluetoothLeAdvertiser.stopAdvertising(this.f610b);
        }
    }

    public a() {
    }
}
