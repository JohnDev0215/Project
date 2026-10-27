package b.b.g.n.k;

import android.bluetooth.BluetoothDevice;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.NetworkInfo;
import android.net.wifi.WifiClient;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import b.b.d.b;
import b.b.f.a;
import b.b.f.c;
import b.b.g.k.q;
import b.b.g.n.q.p;
import b.b.g.p.d;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.ui.fragment.dilink.LinkFragment;
import com.byd.systemsettings.hotspot.HotspotManager;
import com.byd.systemsettings.hotspot.callback.ApEnableStateCallback;
import com.byd.systemsettings.wifi2.BydWifiManager;
import com.byd.systemsettings.wifi2.callback.IEnableStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: compiled from: LinkPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends b.b.m.e.g<LinkFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?> f1112a = e.class;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.d.b f1113b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final BydWifiManager f1114c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HotspotManager f1115d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b.b.g.p.d f1116e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b.b.f.c f1117f;
    public final b.b.f.a g;
    public final p h;
    public final Context i;
    public c.i j;
    public a.g k;
    public ContentResolver l;
    public String m = "";
    public final ContentObserver n = new a(new Handler());
    public final ContentObserver o = new b(new Handler());
    public q p = new c();

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class a extends ContentObserver {

        /* JADX INFO: renamed from: b.b.g.n.k.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: LinkPresenter.java */
        public class RunnableC0055a implements Runnable {
            public RunnableC0055a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                boolean zM = eVar.M(eVar.i);
                b.b.m.c.a.a("LinkPresenter", "mobile.network.switch:" + zM);
                if (e.this.getV() != null) {
                    ((LinkFragment) e.this.getV()).g0(zM);
                }
            }
        }

        public a(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            b.b.a.f.h.a().d(new RunnableC0055a());
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class b extends ContentObserver {
        public b(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            b.b.m.c.a.a("LinkPresenter", "persist.yun.bind.status change");
            if (e.this.getV() != null) {
                ((LinkFragment) e.this.getV()).U();
            }
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class c extends q {

        /* JADX INFO: compiled from: LinkPresenter.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ f.a.a.a.b f1122a;

            public a(f.a.a.a.b bVar) {
                this.f1122a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (e.this.getV() != null) {
                    ((LinkFragment) e.this.getV()).f0(this.f1122a.f3953e);
                    b.b.m.c.a.a("LinkPresenter", "bdq text onDynamicInfoChanged");
                }
            }
        }

        /* JADX INFO: compiled from: LinkPresenter.java */
        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f1124a;

            public b(String str) {
                this.f1124a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (e.this.getV() != null) {
                    ((LinkFragment) e.this.getV()).f0(this.f1124a);
                }
            }
        }

        public c() {
        }

        @Override // f.a.a.a.d
        public void n(String str) throws RemoteException {
            b.b.a.f.h.a().d(new b(str));
        }

        @Override // b.b.g.k.q, f.a.a.a.d
        public void o(f.a.a.a.b bVar) throws RemoteException {
            super.o(bVar);
            if (!"".equals(bVar.f3953e) && !e.this.m.equals(bVar.f3953e)) {
                b.b.a.f.h.a().d(new a(bVar));
            }
            e.this.m = bVar.f3953e;
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class d implements c.i {
        public d() {
        }

        @Override // b.b.f.c.i
        public void a(boolean z) {
            if (e.this.getV() != null) {
                ((LinkFragment) e.this.getV()).a0(z);
            }
        }
    }

    /* JADX INFO: renamed from: b.b.g.n.k.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LinkPresenter.java */
    public class C0056e implements a.g {
        public C0056e() {
        }

        @Override // b.b.f.a.g
        public void a(boolean z) {
            if (e.this.getV() != null) {
                ((LinkFragment) e.this.getV()).T(z);
            }
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class f extends j implements HotspotManager.BydSoftApCallback {
        public f(e eVar) {
            super(eVar);
        }

        @Override // com.byd.systemsettings.hotspot.HotspotManager.BydSoftApCallback
        public void onBlockedClientConnecting(WifiClient wifiClient, int i) {
            if (r()) {
            }
        }

        @Override // com.byd.systemsettings.hotspot.HotspotManager.BydSoftApCallback
        public void onConnectedClientsChanged(List<WifiClient> list) {
            b.b.m.c.a.a("LinkPresenter", "---onConnectedClientsChanged---clients.size = " + list.size());
            if (r()) {
                return;
            }
            ((LinkFragment) this.f1132a.get().getV()).c0(ConnectState.CONNECTED, list.size());
        }

        @Override // com.byd.systemsettings.hotspot.HotspotManager.BydSoftApCallback
        public void onStateChanged(int i, int i2) {
            if (r()) {
            }
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class g extends j implements b.b.d.e.e {
        public g(e eVar) {
            super(eVar);
        }

        @Override // b.b.d.e.e
        public void k(BluetoothDevice bluetoothDevice, ConnectState connectState, boolean z) {
            if (r() || bluetoothDevice == null) {
                b.b.m.c.a.c("LinkPresenter", "onConnectStateChanged return!!");
                return;
            }
            BluetoothDevice bluetoothDeviceG = this.f1132a.get().G();
            b.b.m.c.a.a("LinkPresenter", "---onConnectStateChanged---device = " + b.b.m.g.f.d(bluetoothDevice));
            b.b.m.c.a.a("LinkPresenter", "---onConnectStateChanged---d = " + b.b.m.g.f.d(bluetoothDeviceG));
            if (connectState == ConnectState.CONNECTED) {
                if (bluetoothDeviceG != null) {
                    ((LinkFragment) this.f1132a.get().getV()).W(true, bluetoothDeviceG.getName(), z);
                    return;
                } else {
                    ((LinkFragment) this.f1132a.get().getV()).W(true, bluetoothDevice.getName(), z);
                    return;
                }
            }
            if (connectState == ConnectState.DISCONNECTED) {
                boolean z2 = bluetoothDeviceG != null;
                ((LinkFragment) this.f1132a.get().getV()).W(z2, z2 ? bluetoothDeviceG.getName() : bluetoothDevice.getName(), z);
            }
        }

        @Override // b.b.d.e.e
        public void n(BTDevice bTDevice) {
            b.b.m.c.a.a("LinkPresenter", "onBtNameChanged", ", device:" + bTDevice);
            if (r()) {
                return;
            }
            ((LinkFragment) this.f1132a.get().getV()).V(bTDevice);
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class h extends j implements b.b.d.e.i {
        public h(e eVar) {
            super(eVar);
        }

        @Override // b.b.d.e.i
        public void c() {
            b.b.m.c.a.a("LinkPresenter", "---onBtOff---");
            if (r()) {
                return;
            }
            ((LinkFragment) this.f1132a.get().getV()).Z(false);
        }

        @Override // b.b.d.e.i
        public void f() {
            b.b.m.c.a.a("LinkPresenter", "---onBtOn---");
            if (r()) {
                return;
            }
            ((LinkFragment) this.f1132a.get().getV()).Z(true);
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class i extends j implements ApEnableStateCallback {
        public i(e eVar) {
            super(eVar);
        }

        @Override // com.byd.systemsettings.hotspot.callback.ApEnableStateCallback
        public void onEnableStateChanged(EnableState enableState) {
            b.b.m.c.a.a("LinkPresenter", "---onEnableStateChanged---state = " + enableState);
            if (r()) {
                return;
            }
            ((LinkFragment) this.f1132a.get().getV()).e0(enableState);
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<e> f1132a;

        public j(e eVar) {
            this.f1132a = new WeakReference<>(eVar);
        }

        public boolean r() {
            WeakReference<e> weakReference = this.f1132a;
            return weakReference == null || weakReference.get() == null || this.f1132a.get().getV() == null;
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class k extends j implements b.b.d.e.h {
        public k(e eVar) {
            super(eVar);
        }

        @Override // b.b.d.e.h
        public void a(int i, BluetoothDevice bluetoothDevice, ConnectState connectState) {
            if (r() || bluetoothDevice == null) {
                b.b.m.c.a.c("LinkPresenter", "onConnectedChanged return!!");
            } else {
                ((LinkFragment) this.f1132a.get().getV()).Y(i, bluetoothDevice, connectState);
            }
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class l extends j implements IWifiConnectStateCallback {
        public l(e eVar) {
            super(eVar);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo) {
            b.b.m.c.a.a("LinkPresenter", "---onConnectStateChanged---state = " + connectState);
            if (r()) {
                return;
            }
            ((LinkFragment) this.f1132a.get().getV()).i0(connectState, networkInfo);
        }
    }

    /* JADX INFO: compiled from: LinkPresenter.java */
    public class m extends j implements IEnableStateCallback {
        public m(e eVar) {
            super(eVar);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IEnableStateCallback
        public void onEnableStateChanged(EnableState enableState) {
            b.b.m.c.a.a("LinkPresenter", "---onEnableStateChanged---state = " + enableState);
            if (r()) {
                return;
            }
            ((LinkFragment) this.f1132a.get().getV()).j0(enableState);
        }
    }

    public e() {
        Context context = BydApp.getContext();
        this.i = context;
        this.f1113b = b.f.a();
        this.f1114c = BydWifiManager.Singleton.get();
        this.f1115d = HotspotManager.Singleton.get();
        b.b.g.p.d dVarA = d.g.a();
        this.f1116e = dVarA;
        dVarA.W(context);
        b.b.f.c cVarW = b.b.f.c.w(context);
        this.f1117f = cVarW;
        b.b.f.a aVarT = b.b.f.a.t(context);
        this.g = aVarT;
        p pVarC = p.c();
        this.h = pVarC;
        d dVar = new d();
        this.j = dVar;
        cVarW.C(dVar);
        C0056e c0056e = new C0056e();
        this.k = c0056e;
        aVarT.z(c0056e);
        pVarC.f(this.p);
    }

    public String F() {
        return this.f1114c.getConnectedName();
    }

    public BluetoothDevice G() {
        return this.f1113b.o();
    }

    public String H() {
        return this.f1116e.E(getV().getContext());
    }

    public int I(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "persist.yun.bind.status", 0);
    }

    public boolean J() {
        return this.g.v();
    }

    public boolean K() {
        return this.f1113b.w();
    }

    public boolean L() {
        return this.f1117f.y();
    }

    public boolean M(Context context) {
        return this.h.d(context);
    }

    public boolean N() {
        return this.f1114c.isConnected();
    }

    public void O(Context context, boolean z) {
        this.h.g(context, z);
    }

    public void P() {
    }

    public int getHotspotConnectedCount() {
        return this.f1115d.getConnectedCount();
    }

    public void init(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f1113b.s(applicationContext);
        b.b.d.b bVar = this.f1113b;
        Class<?> cls = f1112a;
        bVar.H(cls.getName(), new h(this));
        this.f1113b.G(cls.getName(), new g(this));
        this.f1113b.I(cls.getName(), new k(this));
        this.f1114c.init(applicationContext);
        this.f1114c.registerWifiStateCallback(cls.getName(), new m(this));
        this.f1114c.registerWifiConnectStateCallback(cls.getName(), new l(this));
        this.f1115d.init(applicationContext);
        this.f1115d.registerHotspotStateCallback(cls.getName(), new i(this));
        this.f1115d.registerSoftApCallback(cls.getName(), new f(this));
        this.f1116e.W(applicationContext);
        ContentResolver contentResolver = applicationContext.getContentResolver();
        this.l = contentResolver;
        if (contentResolver != null) {
            contentResolver.registerContentObserver(Settings.Global.getUriFor(p.f1292a), false, this.n);
            this.l.registerContentObserver(Settings.Global.getUriFor("persist.yun.bind.status"), false, this.o);
        }
    }

    public boolean isHotspotEnable() {
        return this.f1115d.isHotspotEnable();
    }

    public boolean isWifiEnabled() {
        return this.f1114c.isWifiEnabled();
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        this.h.h(this.p);
        this.p = null;
        super.unbindV();
        b.b.d.b bVar = this.f1113b;
        if (bVar != null) {
            Class<?> cls = f1112a;
            bVar.U(cls.getName());
            this.f1113b.T(cls.getName());
            this.f1113b.V(cls.getName());
        }
        BydWifiManager bydWifiManager = this.f1114c;
        if (bydWifiManager != null) {
            Class<?> cls2 = f1112a;
            bydWifiManager.unregisterWifiStateCallback(cls2.getName());
            this.f1114c.unregisterWifiConnectStateCallback(cls2.getName());
        }
        HotspotManager hotspotManager = this.f1115d;
        if (hotspotManager != null) {
            Class<?> cls3 = f1112a;
            hotspotManager.unregisterHotspotStateCallback(cls3.getName());
            this.f1115d.unregisterSoftApCallback(cls3.getName());
        }
        ContentResolver contentResolver = this.l;
        if (contentResolver != null) {
            contentResolver.unregisterContentObserver(this.n);
            this.l.unregisterContentObserver(this.o);
        }
        b.b.f.c cVar = this.f1117f;
        this.j = null;
        cVar.C(null);
        b.b.f.a aVar = this.g;
        this.k = null;
        aVar.z(null);
    }
}
