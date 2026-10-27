package b.b.e;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.SystemClock;
import android.text.TextUtils;
import b.b.a.f.h;
import b.b.d.b;
import b.b.d.f.a;
import b.b.f.f;
import b.b.m.g.f;
import com.byd.carsettings.BydApp;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.service.androidauto.data.AndroidAutoDevice;

/* JADX INFO: compiled from: BtSingleConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?> f624a = b.class;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap<String, ?> f625b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ConcurrentHashMap<String, Long> f626c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ConcurrentHashMap<String, String> f627d = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile String f628e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static volatile String f629f = null;
    public static volatile boolean g = true;
    public static volatile boolean h = true;

    /* JADX INFO: compiled from: BtSingleConnection.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f630a;

        public a(String str) {
            this.f630a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.m.c.a.g("BtSingleConnection", "doSingleConnectionStrategy", "-----Strategy READY, mac: " + f.e((String) b.f627d.get(this.f630a)));
            while (!b.g) {
                b.b.m.c.a.g("BtSingleConnection", "doSingleConnectionStrategy", "-----Strategy STANDBY. isStrategyEnd: " + b.g + ", isStrategyStop: " + b.h);
                SystemClock.sleep(10L);
            }
            boolean unused = b.g = false;
            boolean unused2 = b.h = false;
            b.b.m.c.a.g("BtSingleConnection", "doSingleConnectionStrategy", "-----Strategy START. mac: " + f.e((String) b.f627d.get(this.f630a)));
            int i = 0;
            while (i < 105 && b.f627d.get(this.f630a) != null && !b.h) {
                b.o(this.f630a);
                i++;
                SystemClock.sleep(330L);
            }
            b.b.m.c.a.g("BtSingleConnection", "doSingleConnectionStrategy", "-----Strategy END. keepTimes: " + i + ", mac: " + f.e((String) b.f627d.get(this.f630a)));
            if (b.f627d.get(this.f630a) != null) {
                b.f627d.remove(this.f630a);
            }
            b.i();
            boolean unused3 = b.g = true;
        }
    }

    /* JADX INFO: renamed from: b.b.e.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BtSingleConnection.java */
    public interface InterfaceC0032b {
        void a();
    }

    public static boolean h(BluetoothDevice bluetoothDevice) {
        ConcurrentHashMap<String, Long> concurrentHashMap = f626c;
        if (concurrentHashMap.get(bluetoothDevice.getAddress()) == null) {
            return false;
        }
        BigDecimal bigDecimalSubtract = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(concurrentHashMap.get(bluetoothDevice.getAddress()).longValue()));
        b.b.m.c.a.a("BtSingleConnection", "---checkUserAction---delta: " + bigDecimalSubtract.longValue());
        return bigDecimalSubtract.longValue() < 5000;
    }

    public static void i() {
        f628e = null;
        b.b.m.c.a.d("BtSingleConnection", "---delayToClearDisableMap---do clear.");
        b.b.d.f.a aVarA = a.c.a();
        HashMap map = new HashMap();
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            map.put(bluetoothDevice.getAddress(), bluetoothDevice);
        }
        Iterator<String> it = f626c.keySet().iterator();
        while (it.hasNext()) {
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) map.get(it.next());
            AndroidAutoDevice androidAutoDeviceR = b.b.f.a.t(BydApp.getContext()).r();
            if (androidAutoDeviceR != null && bluetoothDevice2 != null && bluetoothDevice2.getAddress() != null && bluetoothDevice2.getAddress().equals(androidAutoDeviceR.getBtAddr())) {
                b.b.m.c.a.a("BtSingleConnection", "当前device AA连接中,不能解禁用AA hfp");
            } else if (bluetoothDevice2 != null) {
                aVarA.f(bluetoothDevice2);
            }
        }
        ConcurrentHashMap<String, Long> concurrentHashMap = f626c;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static void j(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || !h(bluetoothDevice)) {
            return;
        }
        b.b.d.f.a aVarA = a.c.a();
        aVarA.e(bluetoothDevice);
        aVarA.d(bluetoothDevice);
        aVarA.c(bluetoothDevice);
    }

    public static void k(String str) {
        b.b.m.c.a.d("BtSingleConnection", "doSingleConnectionStrategy", "---doSingleConnectionStrategy---");
        if (!g) {
            h = true;
        }
        h.a().j(new a(str));
    }

    public static void l(BluetoothDevice bluetoothDevice, boolean z) {
        b.b.m.c.a.a("BtSingleConnection", "enableAndroidAutoHfp:" + z);
        a.c.a().g(bluetoothDevice, z);
    }

    public static List<BluetoothDevice> m() {
        return b.f.a().l();
    }

    public static void n(String str, InterfaceC0032b interfaceC0032b) {
        b.b.m.c.a.d("BtSingleConnection", "keepSingle", "-----do keep single connection. mac: " + f.e(str));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f628e = str;
        f627d.put(str, str);
        k(str);
        if (interfaceC0032b != null) {
            interfaceC0032b.a();
        }
    }

    public static int o(String str) {
        List<BluetoothDevice> listM = m();
        if (listM.isEmpty()) {
            return 0;
        }
        b.b.d.b bVarA = b.f.a();
        List<AndroidAutoDevice> listP = f.e.a().p();
        int i = 0;
        for (BluetoothDevice bluetoothDevice : listM) {
            if (!bluetoothDevice.getAddress().equals(str)) {
                b.b.m.c.a.d("BtSingleConnection", "--keepSingleConnection--disconnect--d: " + b.b.m.g.f.d(bluetoothDevice));
                i++;
                if (p(listP, bluetoothDevice)) {
                    b.b.m.c.a.d("BtSingleConnection", "--keepSingleConnection--disconnect--aa--d: " + b.b.m.g.f.d(bluetoothDevice));
                    f626c.put(bluetoothDevice.getAddress(), Long.valueOf(System.currentTimeMillis()));
                    j(bluetoothDevice);
                }
                bVarA.f(bluetoothDevice);
                bVarA.g(bluetoothDevice);
            }
        }
        return i;
    }

    public static boolean p(List<AndroidAutoDevice> list, BluetoothDevice bluetoothDevice) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<AndroidAutoDevice> it = list.iterator();
        while (it.hasNext()) {
            if (bluetoothDevice.getAddress().equals(it.next().getBtAddr())) {
                return true;
            }
        }
        return false;
    }
}
