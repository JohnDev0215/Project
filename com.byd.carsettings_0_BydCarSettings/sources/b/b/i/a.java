package b.b.i;

import android.car.Car;
import android.content.Context;
import android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;

/* JADX INFO: compiled from: BydPowerManagerDevice.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public BYDAutoLightDevice f1907a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoOtaDevice f1908b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoPowerDevice f1909c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public AbsBYDAutoPowerListener f1910d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbsBYDAutoLightListener f1911e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BYDAutoBodyworkDevice f1912f;
    public BYDCarServiceBase g;
    public volatile boolean h;
    public volatile boolean i;
    public final IServiceConnectionListener j;

    /* JADX INFO: renamed from: b.b.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydPowerManagerDevice.java */
    public class C0079a implements IServiceConnectionListener {
        public C0079a() {
        }

        @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
        public void onServiceConnected(boolean z) {
            b.b.m.c.a.a("BydPowerManagerDevice", "Listener, onServiceConnected:" + z);
            a.this.i = true;
        }

        @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
        public void onServiceDisconnected(boolean z) {
            b.b.m.c.a.a("BydPowerManagerDevice", "onServiceDisconnected:" + z);
            a.this.i = false;
        }
    }

    /* JADX INFO: compiled from: BydPowerManagerDevice.java */
    public class b extends BYDCarServiceBase {
        public b(Context context) {
            super(context);
        }

        @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
        public void onServiceConnected(Car car) {
            b.b.m.c.a.a("BydPowerManagerDevice", "init", ", onServiceConnected car:" + car);
        }

        @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
        public void onServiceDied() {
            b.b.m.c.a.a("BydPowerManagerDevice", "init", ", onServiceDied:");
        }
    }

    /* JADX INFO: compiled from: BydPowerManagerDevice.java */
    public class c extends AbsBYDAutoPowerListener {
        public c() {
        }

        @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
        public void onPowerLevelChanged(int i) {
            b.b.m.c.a.d("BydPowerManagerDevice", "init", "---onPowerLevelChanged---level: " + i);
        }

        @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
        public void onTftBacklightChanged(int i) {
            b.b.m.c.a.d("BydPowerManagerDevice", "init", "---onTftBacklightChanged---value: " + i);
        }
    }

    /* JADX INFO: compiled from: BydPowerManagerDevice.java */
    public class d extends AbsBYDAutoLightListener {
        public d() {
        }

        @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
        public void onLightOff(int i) {
            b.b.m.c.a.a("BydPowerManagerDevice", "init", "---onLightOff---type: " + i);
        }

        @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
        public void onLightOn(int i) {
            b.b.m.c.a.a("BydPowerManagerDevice", "init", "---onLightOn---type: " + i);
        }
    }

    /* JADX INFO: compiled from: BydPowerManagerDevice.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f1917a = new a(null);

        public static a a() {
            return f1917a;
        }
    }

    public /* synthetic */ a(C0079a c0079a) {
        this();
    }

    public void b(Context context) {
        b.b.m.c.a.a("BydPowerManagerDevice", "init", "---init---isInited: " + this.h);
        if (this.h) {
            return;
        }
        this.h = true;
        if (this.f1908b == null) {
            this.f1908b = BYDAutoOtaDevice.getInstance(context.getApplicationContext());
        }
        if (this.g == null) {
            b bVar = new b(context);
            this.g = bVar;
            bVar.registerConnectionListener(this.j);
        }
        if (this.f1912f == null) {
            this.f1912f = BYDAutoBodyworkDevice.getInstance(context.getApplicationContext());
        }
        if (this.f1910d == null) {
            this.f1910d = new c();
        }
        BYDAutoPowerDevice bYDAutoPowerDevice = BYDAutoPowerDevice.getInstance(context.getApplicationContext());
        this.f1909c = bYDAutoPowerDevice;
        if (bYDAutoPowerDevice != null) {
            bYDAutoPowerDevice.registerListener(this.f1910d);
        }
        if (this.f1911e == null) {
            this.f1911e = new d();
        }
        BYDAutoLightDevice bYDAutoLightDevice = BYDAutoLightDevice.getInstance(context.getApplicationContext());
        this.f1907a = bYDAutoLightDevice;
        if (bYDAutoLightDevice != null) {
            bYDAutoLightDevice.registerListener(this.f1911e);
        }
    }

    public void c() {
        BYDAutoPowerDevice bYDAutoPowerDevice;
        b.b.m.c.a.a("BydPowerManagerDevice", "---release---");
        AbsBYDAutoPowerListener absBYDAutoPowerListener = this.f1910d;
        if (absBYDAutoPowerListener != null && (bYDAutoPowerDevice = this.f1909c) != null) {
            bYDAutoPowerDevice.unregisterListener(absBYDAutoPowerListener);
        }
        BYDCarServiceBase bYDCarServiceBase = this.g;
        if (bYDCarServiceBase != null) {
            bYDCarServiceBase.unregisterConnectionListener(this.j);
        }
        this.h = false;
        this.f1910d = null;
        this.f1909c = null;
        this.f1908b = null;
    }

    public a() {
        this.h = false;
        this.i = false;
        this.j = new C0079a();
    }
}
