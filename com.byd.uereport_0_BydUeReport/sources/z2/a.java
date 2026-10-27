package z2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: BTAdvertiser.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AdvertiseSettings f9495a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f9496b;

    /* JADX INFO: compiled from: BTAdvertiser.java */
    public class b extends AdvertiseCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<a> f9497a;

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartFailure(int i8) {
            x3.a.a("BTAdvertiser", "--onStartFailure--errorCode: " + i8);
            WeakReference<a> weakReference = this.f9497a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9497a.get().b(i8);
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartSuccess(AdvertiseSettings advertiseSettings) {
            x3.a.a("BTAdvertiser", "--onStartSuccess--settingsInEffect: " + advertiseSettings.toString());
            WeakReference<a> weakReference = this.f9497a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f9497a.get().c(advertiseSettings);
        }

        public b(a aVar) {
            this.f9497a = new WeakReference<>(aVar);
        }
    }

    /* JADX INFO: compiled from: BTAdvertiser.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static a f9499a = new a();

        public static a a() {
            return f9499a;
        }
    }

    public void a() {
        this.f9495a = new AdvertiseSettings.Builder().setAdvertiseMode(1).setTxPowerLevel(3).setConnectable(true).build();
        this.f9496b = new b(this);
    }

    public void b(int i8) {
        x3.a.a("BTAdvertiser", "--onAdvertiseStartFailure--errorCode: " + i8);
    }

    public void c(AdvertiseSettings advertiseSettings) {
        x3.a.a("BTAdvertiser", "--onAdvertiseStartSuccess--as: " + advertiseSettings.toString());
    }

    public void d() {
        this.f9495a = null;
        this.f9496b = null;
    }

    public void e() {
    }

    public void f() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter.isEnabled() && (bluetoothLeAdvertiser = defaultAdapter.getBluetoothLeAdvertiser()) != null) {
            bluetoothLeAdvertiser.stopAdvertising(this.f9496b);
        }
    }

    public a() {
    }
}
