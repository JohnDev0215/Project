package b.b.f;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiClient;
import android.text.TextUtils;
import b.b.a.f.h;
import b.b.d.b;
import b.b.f.a;
import b.b.f.c;
import com.byd.carplay.model.CPAADevice;
import com.byd.carplay.model.CarPlayType;
import com.byd.systemsettings.hotspot.HotspotManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.androidauto.data.ConnectRequest;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: compiled from: CpaaDeviceManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.f.c f694a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.f.a f695b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.d.b f696c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HotspotManager f697d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SharedPreferences f698e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile String f699f;
    public volatile int g;
    public volatile int h;
    public BluetoothDevice i;
    public b.b.f.g.a j;

    /* JADX INFO: compiled from: CpaaDeviceManager.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f700a;

        public a(String str) {
            this.f700a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.L(this.f700a);
        }
    }

    /* JADX INFO: compiled from: CpaaDeviceManager.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CPAADevice f702a;

        public b(CPAADevice cPAADevice) {
            this.f702a = cPAADevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.K(this.f702a);
        }
    }

    /* JADX INFO: compiled from: CpaaDeviceManager.java */
    public class c implements a.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f704a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public volatile AndroidAutoDevice f705b;

        public /* synthetic */ c(f fVar, f fVar2, a aVar) {
            this(fVar2);
        }

        @Override // b.b.f.a.g
        public void b(int i) {
            WeakReference<f> weakReference = this.f704a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f704a.get().E(4 == i, this.f705b);
        }

        @Override // b.b.f.a.g
        public void c(boolean z) {
            WeakReference<f> weakReference = this.f704a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f704a.get().E(z, this.f705b);
        }

        @Override // b.b.f.a.g
        public void e(int i, AndroidAutoDevice androidAutoDevice) {
            WeakReference<f> weakReference = this.f704a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f704a.get().h = i;
            this.f705b = androidAutoDevice;
            if (i == 1) {
                if (f.this.i != null && f.this.i.getAddress().equals(androidAutoDevice.getBtAddr())) {
                    b.b.m.c.a.a("CpaaDeviceManager", "AA连接状态断开后,再解绑设备");
                    f fVar = f.this;
                    fVar.l(fVar.i.getAddress());
                    f.this.f696c.R(f.this.i);
                    f.this.i = null;
                    EventBus.getDefault().post(new b.b.a.c.a(230010));
                }
                if (f.this.j == null) {
                    return;
                }
                b.b.f.g.a unused = f.this.j;
                throw null;
            }
        }

        public c(f fVar) {
            this.f704a = new WeakReference<>(fVar);
        }
    }

    /* JADX INFO: compiled from: CpaaDeviceManager.java */
    public class d implements c.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f707a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public volatile CarPlayDevice f708b;

        public /* synthetic */ d(f fVar, f fVar2, a aVar) {
            this(fVar2);
        }

        @Override // b.b.f.c.i
        public void c(int i, CarPlayDevice carPlayDevice) {
            WeakReference<f> weakReference = this.f707a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f707a.get().g = i;
            this.f708b = carPlayDevice;
            if (i == 1) {
                if (f.this.i != null && f.this.i.getAddress().equals(carPlayDevice.getBtAddr())) {
                    b.b.m.c.a.a("CpaaDeviceManager", "CP连接状态断开后,再解绑设备");
                    f fVar = f.this;
                    fVar.m(fVar.i.getAddress());
                    f.this.f696c.R(f.this.i);
                    f.this.i = null;
                    EventBus.getDefault().post(new b.b.a.c.a(230010));
                }
                if (f.this.j == null) {
                    return;
                }
                b.b.f.g.a unused = f.this.j;
                throw null;
            }
        }

        @Override // b.b.f.c.i
        public void onCarPlayDisplayStatusChanged(boolean z) {
            WeakReference<f> weakReference = this.f707a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f707a.get().F(z, this.f708b);
        }

        @Override // b.b.f.c.i
        public void onSessionStateChanged(int i) {
            b.b.m.c.a.a("CpaaDeviceManager", "onSessionStateChanged", ", status: " + i);
        }

        public d(f fVar) {
            this.f707a = new WeakReference<>(fVar);
        }
    }

    /* JADX INFO: compiled from: CpaaDeviceManager.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f710a = new f(null);

        public static f a() {
            return f710a;
        }
    }

    public /* synthetic */ f(a aVar) {
        this();
    }

    public boolean A() {
        b.b.f.a aVar = this.f695b;
        if (aVar == null) {
            return false;
        }
        return aVar.w();
    }

    public boolean B() {
        b.b.f.c cVar = this.f694a;
        if (cVar == null) {
            return false;
        }
        return cVar.y();
    }

    public boolean C() {
        b.b.f.c cVar = this.f694a;
        if (cVar == null) {
            return false;
        }
        return cVar.z();
    }

    public boolean D(int i) {
        int profileConnectionState = BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(i);
        b.b.m.c.a.a("CpaaDeviceManager", "ProfileConnectionState：" + profileConnectionState);
        return profileConnectionState == 2;
    }

    public final void E(boolean z, AndroidAutoDevice androidAutoDevice) {
        b.b.m.c.a.d("CpaaDeviceManager", "--onAADisplayStatusChanged--isShow: " + z + ", device: " + androidAutoDevice);
        if (androidAutoDevice == null) {
            return;
        }
        this.f699f = z ? androidAutoDevice.getBtAddr() : null;
    }

    public final void F(boolean z, CarPlayDevice carPlayDevice) {
        b.b.m.c.a.d("CpaaDeviceManager", "--onCPDisplayStatusChanged--isShow: " + z + ", device: " + carPlayDevice);
        if (carPlayDevice == null) {
            return;
        }
        this.f699f = z ? carPlayDevice.getBtAddr() : null;
    }

    public void G(String str, a.g gVar) {
        this.f695b.y(str, gVar);
    }

    public void H(String str, b.b.d.e.d dVar) {
        b.b.d.b bVar = this.f696c;
        if (bVar != null) {
            bVar.F(str, dVar);
        }
    }

    public void I(String str, c.i iVar) {
        this.f694a.B(str, iVar);
    }

    public void J(Context context) {
        b.b.f.c cVar = this.f694a;
        if (cVar != null) {
            cVar.F(f.class.getName());
            this.f694a.E();
        }
        b.b.f.a aVar = this.f695b;
        if (aVar != null) {
            aVar.C(f.class.getName());
            this.f695b.B();
        }
        b.b.d.b bVar = this.f696c;
        if (bVar != null) {
            bVar.K();
        }
        HotspotManager hotspotManager = this.f697d;
        if (hotspotManager != null) {
            hotspotManager.release();
        }
    }

    public void K(CPAADevice cPAADevice) {
        ConnectRequest connectRequest = new ConnectRequest();
        String btMac = cPAADevice.getBtMac();
        if (TextUtils.isEmpty(btMac)) {
            btMac = cPAADevice.getUsbSN();
        }
        connectRequest.setDeviceId(btMac);
        connectRequest.setConnectType(0);
        connectRequest.setUserRequest(true);
        connectRequest.setForceProjection(true);
        this.f695b.m(connectRequest);
    }

    public void L(String str) {
        ts.car.service.carplay.data.ConnectRequest connectRequest = new ts.car.service.carplay.data.ConnectRequest();
        connectRequest.setDeviceId(str);
        connectRequest.setConnectType(2);
        connectRequest.setUserRequest(true);
        this.f694a.o(connectRequest);
    }

    public void M() {
        b.b.f.a aVar = this.f695b;
        if (aVar != null) {
            aVar.A();
        }
    }

    public void N() {
        b.b.f.c cVar = this.f694a;
        if (cVar != null) {
            cVar.D();
        }
    }

    public void O(String str) {
        this.f695b.C(str);
    }

    public void P(String str) {
        b.b.d.b bVar = this.f696c;
        if (bVar != null) {
            bVar.S(str);
        }
    }

    public void Q(String str) {
        this.f694a.F(str);
    }

    public final boolean i(CPAADevice cPAADevice) {
        int deviceStatus;
        b.b.m.c.a.d("CpaaDeviceManager", "---checkConnectedDevice---device: " + cPAADevice);
        List<AndroidAutoDevice> listP = p();
        HashMap map = new HashMap();
        for (AndroidAutoDevice androidAutoDevice : listP) {
            map.put(androidAutoDevice.getBtAddr(), androidAutoDevice.getBtAddr());
            if (cPAADevice.getType() == CarPlayType.CAR_PLAY && ((deviceStatus = androidAutoDevice.getDeviceStatus()) == 4 || deviceStatus == 1)) {
                androidAutoDevice.getBtAddr();
            }
        }
        String btMac = cPAADevice.getBtMac();
        boolean z = false;
        for (CPAADevice cPAADevice2 : q()) {
            if (btMac == null || !btMac.equals(cPAADevice2.getBtMac())) {
                if (cPAADevice2.isConnected()) {
                    b.b.m.c.a.d("CpaaDeviceManager", "--checkConnectedDevice--disconnect. d: " + cPAADevice2);
                    if (CarPlayType.ANDROID_AUTO.equals(cPAADevice2.getType())) {
                        this.f695b.o(cPAADevice2.getBtMac());
                        z = true;
                    } else if (CarPlayType.CAR_PLAY.equals(cPAADevice2.getType())) {
                        this.f694a.q(cPAADevice2.getBtMac());
                    }
                }
            }
        }
        return z;
    }

    public void j(CPAADevice cPAADevice) {
        if (this.f695b == null) {
            return;
        }
        b.b.m.c.a.d("CpaaDeviceManager", "---connectAndroidAuto---device: " + cPAADevice);
        if (i(cPAADevice)) {
            h.a().e(new b(cPAADevice), 1000L);
        } else {
            K(cPAADevice);
        }
    }

    public void k(CPAADevice cPAADevice) {
        if (this.f694a == null) {
            return;
        }
        b.b.m.c.a.d("CpaaDeviceManager", "---connectCarPlay---device: " + cPAADevice);
        String btMac = cPAADevice.getBtMac();
        if (i(cPAADevice)) {
            h.a().e(new a(btMac), 1000L);
        } else {
            L(btMac);
        }
    }

    public void l(String str) {
        b.b.f.a aVar = this.f695b;
        if (aVar != null) {
            aVar.n(str);
        }
    }

    public void m(String str) {
        b.b.f.c cVar = this.f694a;
        if (cVar != null) {
            cVar.p(str);
        }
    }

    public void n(String str) {
        b.b.f.a aVar = this.f695b;
        if (aVar != null) {
            aVar.o(str);
        }
    }

    public void o(String str) {
        b.b.f.c cVar = this.f694a;
        if (cVar != null) {
            cVar.q(str);
        }
    }

    public List<AndroidAutoDevice> p() {
        return this.f695b.p();
    }

    public List<CPAADevice> q() {
        ArrayList arrayList = new ArrayList();
        Iterator<AndroidAutoDevice> it = this.f695b.p().iterator();
        while (it.hasNext()) {
            CPAADevice cPAADevice = new CPAADevice(it.next());
            cPAADevice.setHasBtSignal(v(cPAADevice));
            cPAADevice.setHasWiFiSignal(x(cPAADevice));
            cPAADevice.setHasPhoneSignal(w(cPAADevice));
            String btMac = cPAADevice.getBtMac();
            if (TextUtils.isEmpty(btMac)) {
                btMac = cPAADevice.getUsbSN();
            }
            if (!TextUtils.isEmpty(btMac)) {
                cPAADevice.setLastConnectedTime(this.f698e.getLong(btMac.hashCode() + "", 0L));
            }
            arrayList.add(cPAADevice);
        }
        Iterator<CarPlayDevice> it2 = this.f694a.r().iterator();
        while (it2.hasNext()) {
            CPAADevice cPAADevice2 = new CPAADevice(it2.next());
            cPAADevice2.setHasBtSignal(v(cPAADevice2));
            cPAADevice2.setHasWiFiSignal(x(cPAADevice2));
            cPAADevice2.setHasPhoneSignal(w(cPAADevice2));
            String btMac2 = cPAADevice2.getBtMac();
            if (TextUtils.isEmpty(btMac2)) {
                btMac2 = cPAADevice2.getUsbSN();
            }
            if (!TextUtils.isEmpty(btMac2)) {
                cPAADevice2.setLastConnectedTime(this.f698e.getLong(btMac2.hashCode() + "", 0L));
            }
            arrayList.add(cPAADevice2);
        }
        return arrayList;
    }

    public AndroidAutoDevice r() {
        return this.f695b.r();
    }

    public CarPlayDevice s() {
        return this.f694a.u();
    }

    public AndroidAutoDevice t() {
        return this.f695b.s();
    }

    public CarPlayDevice u() {
        return this.f694a.v();
    }

    public boolean v(CPAADevice cPAADevice) {
        if (!this.f696c.w()) {
            b.b.m.c.a.a("CpaaDeviceManager", "bt no enable-->no show bt");
            return false;
        }
        if (!this.f696c.v()) {
            b.b.m.c.a.a("CpaaDeviceManager", "bt no device connected-->no show bt");
            return false;
        }
        BluetoothDevice bluetoothDeviceJ = this.f696c.j();
        if (bluetoothDeviceJ == null) {
            b.b.m.c.a.a("CpaaDeviceManager", "bt curDevice == null -->no show bt");
            return false;
        }
        String address = bluetoothDeviceJ.getAddress();
        b.b.m.c.a.a("CpaaDeviceManager", "curBtAdr：" + address + ",device.getBtMac：" + cPAADevice.getBtMac());
        if (address == null || !address.equals(cPAADevice.getBtMac())) {
            b.b.m.c.a.a("CpaaDeviceManager", "bt no adr equals-->no show bt");
            return false;
        }
        b.b.m.c.a.a("CpaaDeviceManager", "bt adr equals-->show bt");
        return true;
    }

    public boolean w(CPAADevice cPAADevice) {
        b.b.m.c.a.a("CpaaDeviceManager", "bt phone signal get");
        if (!v(cPAADevice)) {
            return false;
        }
        if (D(16)) {
            b.b.m.c.a.a("CpaaDeviceManager", "bt phone connected ->show phone");
            return true;
        }
        b.b.m.c.a.a("CpaaDeviceManager", "bt phone not connected ->no show phone");
        return false;
    }

    public boolean x(CPAADevice cPAADevice) {
        if (!this.f697d.isHotspotEnable()) {
            b.b.m.c.a.a("CpaaDeviceManager", "Hotspot no enable-->no show wifi");
            return false;
        }
        if (this.f697d.getConnectedCount() <= 0) {
            b.b.m.c.a.a("CpaaDeviceManager", "no connected hotspot count-->no show wifi");
            return false;
        }
        List<WifiClient> apClients = this.f697d.getApClients();
        if (apClients == null || apClients.size() == 0) {
            b.b.m.c.a.a("CpaaDeviceManager", "apWifiClients == null -->no show wifi");
            return false;
        }
        for (WifiClient wifiClient : apClients) {
            if (wifiClient != null && wifiClient.getMacAddress() != null) {
                String string = wifiClient.getMacAddress().toString();
                b.b.m.c.a.a("CpaaDeviceManager", "curWifiAdr：" + string + ",device.getWifiMac：" + cPAADevice.getWifiMac());
                if (string != null && string.equals(cPAADevice.getWifiMac())) {
                    b.b.m.c.a.a("CpaaDeviceManager", "Hotspot wifi adr equals-->show wifi");
                    return true;
                }
            }
        }
        b.b.m.c.a.a("CpaaDeviceManager", "Hotspot no adr equals-->no show wifi");
        return false;
    }

    public void y(Context context) {
        b.b.m.c.a.a("CpaaDeviceManager", "init_start");
        b.b.f.c cVarW = b.b.f.c.w(context);
        this.f694a = cVarW;
        cVarW.x();
        a aVar = null;
        this.f694a.B(f.class.getName(), new d(this, this, aVar));
        b.b.m.c.a.a("CpaaDeviceManager", "init_cp_ok");
        b.b.f.a aVarT = b.b.f.a.t(context);
        this.f695b = aVarT;
        aVarT.u();
        this.f695b.y(f.class.getName(), new c(this, this, aVar));
        b.b.m.c.a.a("CpaaDeviceManager", "init_aa_ok");
        b.b.d.b bVarA = b.f.a();
        this.f696c = bVarA;
        bVarA.s(context);
        b.b.m.c.a.a("CpaaDeviceManager", "init_bt_ok");
        HotspotManager hotspotManager = HotspotManager.Singleton.get();
        this.f697d = hotspotManager;
        hotspotManager.init(context);
        b.b.m.c.a.a("CpaaDeviceManager", "init_hotspot_ok");
        this.f698e = context.getSharedPreferences("key_cpaa_connected_time", 0);
        b.b.m.c.a.a("CpaaDeviceManager", "init_end");
    }

    public boolean z() {
        b.b.f.a aVar = this.f695b;
        if (aVar == null) {
            return false;
        }
        return aVar.v();
    }

    public f() {
    }
}
