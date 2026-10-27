package b.b.f;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.byd.btsetting.model.CarPlayParams;
import com.byd.carplay.SystemPanelChangeReceiver;
import com.ts.car.core.ServiceProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import net.sqlcipher.database.SQLiteDatabase;
import org.greenrobot.eventbus.EventBus;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayDeviceListListener;
import ts.car.service.carplay.ICarPlayDeviceListManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.CarPlayDevice;
import ts.car.service.carplay.data.ConnectRequest;

/* JADX INFO: compiled from: CarPlayManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f655a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f656b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f659e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f660f;
    public ConcurrentHashMap<String, i> g;
    public i h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ICarPlayDeviceListManager f657c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ICarPlayAppManager f658d = null;
    public final ICarPlayAppListener i = new a();
    public final ICarPlayStateListener j = new b();
    public final IServiceConnectionListener k = new d();
    public final IServiceConnectionListener l = new e();
    public final ICarPlayDeviceListListener m = new f();

    /* JADX INFO: compiled from: CarPlayManager.java */
    public class a implements ICarPlayAppListener {
        public a() {
        }

        @Override // ts.car.service.carplay.ICarPlayAppListener
        public void onCarPlayDisplayStatusChanged(boolean z) {
            b.b.m.c.a.a("CarPlayManager", "onCarPlayDisplayStatusChanged:" + z);
            if (SystemPanelChangeReceiver.f2564a != 0) {
                b.b.m.c.a.c("CarPlayManager", "状态栏面板非默认状态,进入CP投屏不关闭系统设置弹框,5s后恢复默认状态");
            } else {
                c.this.n(z, -1);
            }
        }
    }

    /* JADX INFO: compiled from: CarPlayManager.java */
    public class b implements ICarPlayStateListener {

        /* JADX INFO: compiled from: CarPlayManager.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f663a;

            public a(int i) {
                this.f663a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.h.a(this.f663a == 2);
            }
        }

        public b() {
        }

        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onSessionStateChanged(int i) {
            b.b.m.c.a.a("CarPlayManager", "onSessionStateChanged:" + i);
            if (c.this.h != null) {
                b.b.a.f.h.a().d(new a(i));
            }
            c.this.A(i);
        }
    }

    /* JADX INFO: renamed from: b.b.f.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CarPlayManager.java */
    public class RunnableC0038c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f665a;

        public RunnableC0038c(int i) {
            this.f665a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (i iVar : c.this.g.values()) {
                if (iVar != null) {
                    iVar.onSessionStateChanged(this.f665a);
                }
            }
        }
    }

    /* JADX INFO: compiled from: CarPlayManager.java */
    public class d implements IServiceConnectionListener {
        public d() {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            c.this.f659e = true;
            try {
                c.this.f658d.registerCarPlayAppListener(c.this.i);
                c.this.f658d.registerCarPlayStateListener(c.this.j);
                EventBus.getDefault().post(new b.b.a.c.a(230008));
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            b.b.m.c.a.d("CarPlayManager", "mCarPlayAppManager.onServiceConnected:" + iPlatformService);
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            c.this.f659e = false;
            try {
                if (c.this.f658d != null) {
                    c.this.f658d.unregisterCarPlayAppListener(c.this.i);
                    c.this.f658d.unregisterCarPlayStateListener(c.this.j);
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            b.b.m.c.a.c("CarPlayManager", "mCarPlayAppManager.onServiceDisconnected:" + iPlatformService);
        }
    }

    /* JADX INFO: compiled from: CarPlayManager.java */
    public class e implements IServiceConnectionListener {
        public e() {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            c.this.f660f = true;
            try {
                if (c.this.f657c != null) {
                    c.this.f657c.registerCarPlayDeviceListListener(c.this.m);
                }
                EventBus.getDefault().post(new b.b.a.c.a(230008));
                b.b.m.c.a.d("CarPlayManager", "mCarPlayDeviceManager.onServiceConnected:" + iPlatformService);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            c.this.f660f = false;
            try {
                if (c.this.f657c != null) {
                    c.this.f657c.unregisterCarPlayDeviceListListener(c.this.m);
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            b.b.m.c.a.d("CarPlayManager", "mCarPlayDeviceManager.onServiceDisconnected:" + iPlatformService);
        }
    }

    /* JADX INFO: compiled from: CarPlayManager.java */
    public class f implements ICarPlayDeviceListListener {
        public f() {
        }

        @Override // ts.car.service.carplay.ICarPlayDeviceListListener
        public void onCarPlayDeviceStatusChanged(CarPlayDevice carPlayDevice) {
            b.b.m.c.a.a("CarPlayManager", "onCarPlayDeviceStatusChanged:" + carPlayDevice);
            c.this.m(carPlayDevice.getDeviceStatus(), carPlayDevice);
        }

        @Override // ts.car.service.carplay.ICarPlayDeviceListListener
        public void onProjectionRequested(CarPlayDevice carPlayDevice, int i) {
            b.b.m.c.a.a("CarPlayManager", "onProjectionRequested:" + carPlayDevice);
            if (c.this.g == null || c.this.g.isEmpty()) {
                return;
            }
            for (i iVar : c.this.g.values()) {
                if (iVar != null) {
                    iVar.b(carPlayDevice);
                }
            }
        }
    }

    /* JADX INFO: compiled from: CarPlayManager.java */
    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f670a;

        public g(boolean z) {
            this.f670a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (i iVar : c.this.g.values()) {
                if (iVar != null) {
                    iVar.onCarPlayDisplayStatusChanged(this.f670a);
                }
            }
        }
    }

    /* JADX INFO: compiled from: CarPlayManager.java */
    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f672a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ CarPlayDevice f673b;

        public h(int i, CarPlayDevice carPlayDevice) {
            this.f672a = i;
            this.f673b = carPlayDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (i iVar : c.this.g.values()) {
                if (iVar != null) {
                    iVar.c(this.f672a, this.f673b);
                }
            }
        }
    }

    /* JADX INFO: compiled from: CarPlayManager.java */
    public interface i {
        default void a(boolean z) {
        }

        default void b(CarPlayDevice carPlayDevice) {
        }

        default void c(int i, CarPlayDevice carPlayDevice) {
        }

        default void onCarPlayDisplayStatusChanged(boolean z) {
        }

        default void onSessionStateChanged(int i) {
        }
    }

    public c(Context context) {
        this.f656b = context;
    }

    public static c w(Context context) {
        if (f655a == null) {
            synchronized (c.class) {
                if (f655a == null) {
                    f655a = new c(context.getApplicationContext());
                }
            }
        }
        return f655a;
    }

    public final void A(int i2) {
        ConcurrentHashMap<String, i> concurrentHashMap = this.g;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        b.b.a.f.h.a().d(new RunnableC0038c(i2));
    }

    public void B(String str, i iVar) {
        ConcurrentHashMap<String, i> concurrentHashMap = this.g;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, iVar);
    }

    public void C(i iVar) {
        this.h = iVar;
    }

    public void D() {
        Intent intent = new Intent();
        intent.setClassName(b.b.f.b.f651a, b.b.f.b.f652b);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        this.f656b.startActivity(intent);
    }

    public void E() {
        b.b.m.c.a.a("CarPlayManager", "CarPlay unregisterConnectionListener");
        ICarPlayAppManager iCarPlayAppManager = this.f658d;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.unregisterConnectionListener(this.k);
            try {
                this.f658d.unregisterCarPlayAppListener(this.i);
                this.f658d.unregisterCarPlayStateListener(this.j);
            } catch (RemoteException | RuntimeException e2) {
                e2.printStackTrace();
            }
        }
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.f657c;
        if (iCarPlayDeviceListManager != null) {
            iCarPlayDeviceListManager.unregisterConnectionListener(this.l);
            try {
                this.f657c.unregisterCarPlayDeviceListListener(this.m);
            } catch (RemoteException | RuntimeException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void F(String str) {
        ConcurrentHashMap<String, i> concurrentHashMap = this.g;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public final void m(int i2, CarPlayDevice carPlayDevice) {
        b.b.m.c.a.a("CarPlayManager", "--carPlayDeviceStatusChanged--getDeviceStatus:" + carPlayDevice.getDeviceStatus());
        EventBus.getDefault().post(new b.b.a.c.a(112, new CarPlayParams(i2, carPlayDevice.getDeviceName(), carPlayDevice.getBtAddr())));
        ConcurrentHashMap<String, i> concurrentHashMap = this.g;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        b.b.a.f.h.a().d(new h(i2, carPlayDevice));
    }

    public final void n(boolean z, int i2) {
        ConcurrentHashMap<String, i> concurrentHashMap = this.g;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        b.b.a.f.h.a().d(new g(z));
    }

    public void o(ConnectRequest connectRequest) {
        if (this.f657c == null) {
            b.b.m.c.a.c("CarPlayManager", "mCarPlayDeviceManager == null");
            return;
        }
        if (!this.f660f) {
            b.b.m.c.a.c("CarPlayManager", "mDevicesConnected not connected");
            return;
        }
        try {
            b.b.m.c.a.a("CarPlayManager", "connectCarPlay request:" + connectRequest.getDeviceId().hashCode());
            Intent intent = new Intent("carsettings_device_connect_carplay_action");
            intent.putExtra("deviceId", connectRequest.getDeviceId());
            intent.setPackage("com.byd.uereport");
            this.f656b.sendBroadcast(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("CarPlayManager", "connectCarPlay ,RemoteException:" + e2);
        }
    }

    public void p(String str) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.f657c;
        if (iCarPlayDeviceListManager == null) {
            b.b.m.c.a.c("CarPlayManager", "mCarPlayDeviceManager == null");
            return;
        }
        if (!this.f660f) {
            b.b.m.c.a.c("CarPlayManager", "mDevicesConnected not connected");
            return;
        }
        try {
            iCarPlayDeviceListManager.deleteCarPlayDevice(str);
            b.b.m.c.a.a("CarPlayManager", "deleteCarPlayDevice devId:" + str);
        } catch (RemoteException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("CarPlayManager", "deleteCarPlayDevice ,RemoteException:" + e2);
        }
    }

    public void q(String str) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.f657c;
        if (iCarPlayDeviceListManager == null) {
            b.b.m.c.a.c("CarPlayManager", "mCarPlayDeviceManager == null");
            return;
        }
        if (!this.f660f) {
            b.b.m.c.a.c("CarPlayManager", "mDevicesConnected not connected");
            return;
        }
        try {
            iCarPlayDeviceListManager.disconnectCarPlay(str);
            b.b.m.c.a.a("CarPlayManager", "disconnectCarPlay deviceId:" + str);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public List<CarPlayDevice> r() {
        List<CarPlayDevice> arrayList = new ArrayList<>();
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.f657c;
        if (iCarPlayDeviceListManager == null) {
            b.b.m.c.a.c("CarPlayManager", "mCarPlayDeviceManager == null");
            return arrayList;
        }
        if (!this.f660f) {
            b.b.m.c.a.c("CarPlayManager", "mDevicesConnected not connected");
            return arrayList;
        }
        try {
            arrayList = iCarPlayDeviceListManager.getCarPlayDeviceList();
        } catch (RemoteException | RuntimeException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("CarPlayManager", "getCarPlayDevices,RemoteException:" + e2);
        }
        b.b.m.c.a.a("CarPlayManager", "getCarPlayDevices:" + arrayList);
        return arrayList;
    }

    public boolean s() {
        ICarPlayAppManager iCarPlayAppManager = this.f658d;
        if (iCarPlayAppManager != null) {
            try {
                return iCarPlayAppManager.getCarPlayDisplayStatus();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public int t() {
        int carPlaySessionStatus;
        ICarPlayAppManager iCarPlayAppManager = this.f658d;
        if (iCarPlayAppManager == null) {
            b.b.m.c.a.c("CarPlayManager", "mCarPlayAppManager == null");
            return 0;
        }
        if (!this.f659e) {
            b.b.m.c.a.c("CarPlayManager", "mAppConnected not connected");
            return 0;
        }
        try {
            carPlaySessionStatus = iCarPlayAppManager.getCarPlaySessionStatus();
        } catch (RemoteException | RuntimeException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("CarPlayManager", "getCarPlayStatus,RemoteException:" + e2);
            carPlaySessionStatus = 0;
        }
        b.b.m.c.a.a("CarPlayManager", "getCarPlayStatus:" + carPlaySessionStatus);
        return carPlaySessionStatus;
    }

    public CarPlayDevice u() {
        for (CarPlayDevice carPlayDevice : r()) {
            if (carPlayDevice.getDeviceStatus() == 4) {
                return carPlayDevice;
            }
        }
        return null;
    }

    public CarPlayDevice v() {
        for (CarPlayDevice carPlayDevice : r()) {
            if (carPlayDevice.getDeviceStatus() == 3) {
                return carPlayDevice;
            }
        }
        return null;
    }

    public void x() {
        this.f657c = (ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, this.f656b);
        this.f658d = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this.f656b);
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.f657c;
        if (iCarPlayDeviceListManager != null) {
            iCarPlayDeviceListManager.registerConnectionListener(this.l);
            b.b.m.c.a.d("CarPlayManager", "mCarPlayDeviceManager != null");
        } else {
            b.b.m.c.a.c("CarPlayManager", "mCarPlayDeviceManager == null");
        }
        ICarPlayAppManager iCarPlayAppManager = this.f658d;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.registerConnectionListener(this.k);
            b.b.m.c.a.d("CarPlayManager", "mCarPlayAppManager != null");
        } else {
            b.b.m.c.a.c("CarPlayManager", "mCarPlayAppManager == null");
        }
        if (this.g == null) {
            this.g = new ConcurrentHashMap<>();
        }
    }

    public boolean y() {
        return t() == 2;
    }

    public boolean z() {
        return t() == 1;
    }
}
