package b.b.f;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import b.b.a.f.h;
import b.b.g.r.z;
import com.ts.car.core.ServiceProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import net.sqlcipher.database.SQLiteDatabase;
import org.greenrobot.eventbus.EventBus;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoDeviceListListener;
import ts.car.service.androidauto.IAndroidAutoDeviceListManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.androidauto.data.ConnectRequest;

/* JADX INFO: compiled from: AndroidAutoManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f631a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f632b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile boolean f635e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f636f;
    public ConcurrentHashMap<String, IAndroidAutoSessionStatusListener> g;
    public ConcurrentHashMap<String, IAndroidAutoAppListener> h;
    public ConcurrentHashMap<String, g> i;
    public g j;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public IAndroidAutoDeviceListManager f633c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public IAndroidAutoAppManager f634d = null;
    public final IAndroidAutoAppListener k = new C0033a();
    public final IAndroidAutoDeviceListListener l = new b();
    public final IAndroidAutoSessionStatusListener m = new c();
    public final IServiceConnectionListener n = new d();
    public final IServiceConnectionListener o = new e();
    public final IAndroidAutoAppListener p = new f();

    /* JADX INFO: renamed from: b.b.f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidAutoManager.java */
    public class C0033a implements IAndroidAutoAppListener {

        /* JADX INFO: renamed from: b.b.f.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AndroidAutoManager.java */
        public class RunnableC0034a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f638a;

            public RunnableC0034a(int i) {
                this.f638a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean z = this.f638a == 1;
                if (z) {
                    z.a();
                }
                if (a.this.j != null) {
                    a.this.j.c(z);
                }
                if (a.this.i == null || a.this.i.isEmpty()) {
                    return;
                }
                Iterator it = a.this.i.values().iterator();
                while (it.hasNext()) {
                    ((g) it.next()).c(z);
                }
            }
        }

        public C0033a() {
        }

        @Override // ts.car.service.androidauto.IAndroidAutoAppListener
        public void onVideoFocusStatusChanged(int i) {
            b.b.m.c.a.a("AndroidAutoManager", "onVideoFocusStatusChanged:" + i);
            h.a().d(new RunnableC0034a(i));
        }
    }

    /* JADX INFO: compiled from: AndroidAutoManager.java */
    public class b implements IAndroidAutoDeviceListListener {

        /* JADX INFO: renamed from: b.b.f.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AndroidAutoManager.java */
        public class RunnableC0035a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AndroidAutoDevice f641a;

            public RunnableC0035a(AndroidAutoDevice androidAutoDevice) {
                this.f641a = androidAutoDevice;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (a.this.j != null) {
                    a.this.j.e(this.f641a.getDeviceStatus(), this.f641a);
                }
                if (a.this.i == null || a.this.i.isEmpty()) {
                    return;
                }
                Iterator it = a.this.i.values().iterator();
                while (it.hasNext()) {
                    ((g) it.next()).e(this.f641a.getDeviceStatus(), this.f641a);
                }
            }
        }

        public b() {
        }

        @Override // ts.car.service.androidauto.IAndroidAutoDeviceListListener
        public void onAndroidAutoDeviceStatusChanged(AndroidAutoDevice androidAutoDevice) {
            b.b.m.c.a.a("AndroidAutoManager", "onAndroidAutoDeviceStatusChanged:" + androidAutoDevice.getDeviceStatus());
            h.a().d(new RunnableC0035a(androidAutoDevice));
        }

        @Override // ts.car.service.androidauto.IAndroidAutoDeviceListListener
        public void onProjectionRequested(AndroidAutoDevice androidAutoDevice, int i) {
            b.b.m.c.a.a("AndroidAutoManager", "onProjectionRequested:" + androidAutoDevice);
            if (a.this.i == null || a.this.i.isEmpty()) {
                return;
            }
            Iterator it = a.this.i.values().iterator();
            while (it.hasNext()) {
                ((g) it.next()).d(androidAutoDevice);
            }
        }
    }

    /* JADX INFO: compiled from: AndroidAutoManager.java */
    public class c implements IAndroidAutoSessionStatusListener {

        /* JADX INFO: renamed from: b.b.f.a$c$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AndroidAutoManager.java */
        public class RunnableC0036a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f644a;

            public RunnableC0036a(int i) {
                this.f644a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (a.this.j != null) {
                    a.this.j.a(4 == this.f644a);
                }
                if (a.this.g == null || a.this.g.isEmpty()) {
                    return;
                }
                for (g gVar : a.this.i.values()) {
                    if (gVar != null) {
                        gVar.b(this.f644a);
                    }
                }
            }
        }

        public c() {
        }

        @Override // ts.car.service.androidauto.IAndroidAutoSessionStatusListener
        public void onSessionStatusChanged(int i, int i2, String str) {
            b.b.m.c.a.a("AndroidAutoManager", "onSessionStateChanged:" + i);
            h.a().d(new RunnableC0036a(i));
        }
    }

    /* JADX INFO: compiled from: AndroidAutoManager.java */
    public class d implements IServiceConnectionListener {
        public d() {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            a.this.f635e = true;
            try {
                a.this.f634d.registerAndroidAutoAppListener(a.this.k);
                a.this.f634d.registerAndroidAutoSessionStatusListener(a.this.m);
                b.b.m.c.a.d("AndroidAutoManager", "--onServiceConnected--mac: " + b.b.m.g.f.e(a.this.f634d.getProjectionBluetoothAddress()));
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            b.b.m.c.a.d("AndroidAutoManager", "mAndroidAutoAppManager.onServiceConnected:" + iPlatformService);
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            a.this.f635e = false;
            if (a.this.f634d != null) {
                try {
                    a.this.f634d.unregisterAndroidAutoAppListener(a.this.k);
                    a.this.f634d.unregisterAndroidAutoSessionStatusListener(a.this.m);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            b.b.m.c.a.d("AndroidAutoManager", "mAndroidAutoAppManager.onServiceDisconnected:" + iPlatformService);
        }
    }

    /* JADX INFO: compiled from: AndroidAutoManager.java */
    public class e implements IServiceConnectionListener {
        public e() {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            try {
                a.this.f633c.registerAndroidAutoDeviceListListener(a.this.l);
                a.this.f636f = true;
                EventBus.getDefault().post(new b.b.a.c.a(230008));
                b.b.m.c.a.d("AndroidAutoManager", "mAndroidAutoDeviceManager.onServiceConnected:" + iPlatformService);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            a.this.f636f = false;
            if (a.this.f633c != null) {
                try {
                    a.this.f633c.unregisterAndroidAutoDeviceListListener(a.this.l);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            b.b.m.c.a.d("AndroidAutoManager", "mAndroidAutoDeviceManager.onServiceDisconnected:" + iPlatformService);
        }
    }

    /* JADX INFO: compiled from: AndroidAutoManager.java */
    public class f implements IAndroidAutoAppListener {

        /* JADX INFO: renamed from: b.b.f.a$f$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AndroidAutoManager.java */
        public class RunnableC0037a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f649a;

            public RunnableC0037a(String str) {
                this.f649a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (IAndroidAutoAppListener iAndroidAutoAppListener : a.this.h.values()) {
                    if (iAndroidAutoAppListener != null) {
                        iAndroidAutoAppListener.onProjectionBluetoothAddressChanged(this.f649a);
                    }
                }
            }
        }

        public f() {
        }

        @Override // ts.car.service.androidauto.IAndroidAutoAppListener
        public void onProjectionBluetoothAddressChanged(String str) {
            b.b.m.c.a.a("AndroidAutoManager", "--onProjectionBluetoothAddressChanged--address: " + str);
            if (a.this.f635e) {
                h.a().j(new RunnableC0037a(str));
            }
        }
    }

    /* JADX INFO: compiled from: AndroidAutoManager.java */
    public interface g {
        default void a(boolean z) {
        }

        default void b(int i) {
        }

        default void c(boolean z) {
        }

        default void d(AndroidAutoDevice androidAutoDevice) {
        }

        default void e(int i, AndroidAutoDevice androidAutoDevice) {
        }
    }

    public a(Context context) {
        this.f632b = context.getApplicationContext();
    }

    public static a t(Context context) {
        if (f631a == null) {
            synchronized (a.class) {
                if (f631a == null) {
                    f631a = new a(context.getApplicationContext());
                }
            }
        }
        return f631a;
    }

    public void A() {
        Intent intent = new Intent();
        intent.setClassName(b.b.f.b.f653c, b.b.f.b.f654d);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        this.f632b.startActivity(intent);
    }

    public void B() {
        b.b.m.c.a.a("AndroidAutoManager", "AndroidAuto unregisterConnectionListener");
        IAndroidAutoAppManager iAndroidAutoAppManager = this.f634d;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.unregisterConnectionListener(this.n);
            try {
                this.f634d.unregisterAndroidAutoAppListener(this.k);
                this.f634d.unregisterAndroidAutoSessionStatusListener(this.m);
            } catch (RemoteException | RuntimeException e2) {
                e2.printStackTrace();
            }
        }
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.f633c;
        if (iAndroidAutoDeviceListManager != null) {
            iAndroidAutoDeviceListManager.unregisterConnectionListener(this.o);
            try {
                this.f633c.unregisterAndroidAutoDeviceListListener(this.l);
            } catch (RemoteException | RuntimeException e3) {
                e3.printStackTrace();
            }
        }
        ConcurrentHashMap<String, g> concurrentHashMap = this.i;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public void C(String str) {
        ConcurrentHashMap<String, g> concurrentHashMap = this.i;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public void m(ConnectRequest connectRequest) {
        if (this.f633c == null) {
            b.b.m.c.a.c("AndroidAutoManager", "mAndroidAutoDeviceManager == null");
            return;
        }
        if (!this.f636f) {
            b.b.m.c.a.c("AndroidAutoManager", "mDevicesConnected not connect");
            return;
        }
        try {
            b.b.m.c.a.a("AndroidAutoManager", "connectAndroidAuto request:" + connectRequest.getDeviceId());
            Intent intent = new Intent("carsettings_device_connect_androidauto_action");
            intent.setPackage("com.byd.uereport");
            intent.putExtra("deviceId", connectRequest.getDeviceId());
            intent.putExtra("conType", connectRequest.getConnectType());
            this.f632b.sendBroadcast(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("AndroidAutoManager", "connectAndroidAuto ,RemoteException:" + e2);
        }
    }

    public void n(String str) {
        if (this.f633c == null) {
            b.b.m.c.a.c("AndroidAutoManager", "mAndroidAutoDeviceManager == null");
            return;
        }
        if (!this.f636f) {
            b.b.m.c.a.c("AndroidAutoManager", "mDevicesConnected not connect");
            return;
        }
        try {
            this.f633c.deleteAndroidAutoDevice(str);
            b.b.m.c.a.a("AndroidAutoManager", "deleteAndroidAutoDevice devId:" + str);
        } catch (RemoteException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("AndroidAutoManager", "deleteAndroidAutoDevice ,RemoteException:" + e2);
        }
    }

    public void o(String str) {
        if (this.f633c == null) {
            b.b.m.c.a.c("AndroidAutoManager", "mAndroidAutoDeviceManager == null");
            return;
        }
        if (!this.f636f) {
            b.b.m.c.a.c("AndroidAutoManager", "mDevicesConnected not connect");
            return;
        }
        try {
            this.f633c.disconnectAndroidAuto(str);
            b.b.m.c.a.a("AndroidAutoManager", "disconnectAndroidAuto deviceId:" + str);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public List<AndroidAutoDevice> p() {
        List<AndroidAutoDevice> arrayList = new ArrayList<>();
        if (this.f633c == null) {
            b.b.m.c.a.c("AndroidAutoManager", "mAndroidAutoDeviceManager == null");
            return arrayList;
        }
        if (!this.f636f) {
            b.b.m.c.a.c("AndroidAutoManager", "mDevicesConnected not connect");
            return arrayList;
        }
        try {
            arrayList = this.f633c.getAndroidAutoDeviceList();
        } catch (RemoteException | RuntimeException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("AndroidAutoManager", "getAndroidAutoDevices,RemoteException:" + e2);
        }
        b.b.m.c.a.a("AndroidAutoManager", "getAndroidAutoDevices:" + arrayList);
        return arrayList;
    }

    public int q() {
        int androidAutoSessionStatus;
        if (this.f634d == null) {
            b.b.m.c.a.c("AndroidAutoManager", "mAndroidAutoAppManager == null");
            return 0;
        }
        if (!this.f635e) {
            b.b.m.c.a.c("AndroidAutoManager", "mAppConnected not connect");
            return 0;
        }
        try {
            androidAutoSessionStatus = this.f634d.getAndroidAutoSessionStatus();
        } catch (RemoteException | RuntimeException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("AndroidAutoManager", "getAndroidAutoStatus,RemoteException:" + e2);
            androidAutoSessionStatus = 0;
        }
        b.b.m.c.a.a("AndroidAutoManager", "getAndroidAutoStatus:" + androidAutoSessionStatus);
        return androidAutoSessionStatus;
    }

    public AndroidAutoDevice r() {
        for (AndroidAutoDevice androidAutoDevice : p()) {
            if (androidAutoDevice.getDeviceStatus() == 4) {
                return androidAutoDevice;
            }
        }
        return null;
    }

    public AndroidAutoDevice s() {
        for (AndroidAutoDevice androidAutoDevice : p()) {
            if (androidAutoDevice.getDeviceStatus() == 3) {
                return androidAutoDevice;
            }
        }
        return null;
    }

    public void u() {
        if (this.g == null) {
            this.g = new ConcurrentHashMap<>();
        }
        if (this.h == null) {
            this.h = new ConcurrentHashMap<>();
        }
        if (this.i == null) {
            this.i = new ConcurrentHashMap<>();
        }
        if (this.f633c == null) {
            this.f633c = (IAndroidAutoDeviceListManager) ServiceProvider.get(IAndroidAutoDeviceListManager.class, this.f632b);
        }
        if (this.f634d == null) {
            this.f634d = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.f632b);
        }
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.f633c;
        if (iAndroidAutoDeviceListManager != null) {
            iAndroidAutoDeviceListManager.registerConnectionListener(this.o);
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = this.f634d;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.n);
        }
    }

    public boolean v() {
        return q() == 4;
    }

    public boolean w() {
        return q() == 3;
    }

    public boolean x() {
        AndroidAutoDevice androidAutoDeviceR = r();
        return (androidAutoDeviceR == null || androidAutoDeviceR.isUsbDevice()) ? false : true;
    }

    public void y(String str, g gVar) {
        ConcurrentHashMap<String, g> concurrentHashMap = this.i;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, gVar);
    }

    public void z(g gVar) {
        this.j = gVar;
    }
}
