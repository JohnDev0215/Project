package ts.car.diag.manager;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager;
import com.ts.lib.car.hardware.carbody.CarbodyEventChangeValue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class r {
    private static HandlerThread p;
    private static Handler q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<String, String> f1627c;
    private int g;
    private c.a.a.a.h h;
    private final c i;
    private Context j;
    private Car k;
    private CarVendorCarbodyManager l;
    private transient int m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<String, List<String>> f1625a = new HashMap<>();
    private List<String> d = new ArrayList();
    private final ExecutorService e = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    private final Object f = new Object();
    private final CarConnectionCallback n = new a();
    private final CarVendorCarbodyManager.OnCarCarbodyEventListener o = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<String, Boolean> f1626b = new HashMap<>();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends CarConnectionCallback {
        a() {
        }

        public void onConnected(Car car) {
            q.h("NetworkManager", "Car connected in onServiceConnected start");
            try {
                r.this.l = (CarVendorCarbodyManager) car.getCarManager("carbody");
                if (r.this.l == null) {
                    r.this.u();
                    q.b("NetworkManager", "mCarVendorCarbodyManager null return");
                } else {
                    r.this.l.addCarCarbodyEventListener(r.this.o);
                    r.this.m = r.this.l.getPowerLevel();
                }
            } catch (Exception e) {
                q.b("NetworkManager", "CarConnectionCallback error");
                r.this.u();
                e.printStackTrace();
            }
        }

        public void onDisconnected(Car car) {
            q.h("NetworkManager", "onDisconnected");
            r.this.k = null;
            r.this.l = null;
            r.this.u();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements CarVendorCarbodyManager.OnCarCarbodyEventListener {
        b() {
        }

        public void onCarbodyErrorEvent(int i, int i2) {
        }

        public void onCarbodyEventChange(CarbodyEventChangeValue carbodyEventChangeValue) {
            if (carbodyEventChangeValue == null) {
                q.b("NetworkManager", "onCarbodyEventChange carbodyEventChangeValue == null");
                return;
            }
            int propertyId = carbodyEventChangeValue.getPropertyId();
            if (propertyId == 557862927) {
                r.this.i(propertyId, carbodyEventChangeValue.getPowerLevel());
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r.this.t();
        }

        /* synthetic */ c(r rVar, a aVar) {
            this();
        }
    }

    public r(Context context) {
        this.j = context;
        l();
        this.i = new c(this, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i, int i2) {
        q.e("NetworkManager", "diagSignalChange propId : " + x(i) + " state : " + i2);
        if (i == 557862927) {
            this.m = i2;
            if (i2 == 1) {
                q.e("NetworkManager", "diagSignalChange propId : " + x(i) + " Signal OFF : " + i2);
                q.removeCallbacksAndMessages(null);
                return;
            }
            if (i2 == 2) {
                q.e("NetworkManager", "diagSignalChange propId : " + x(i) + " Signal ACC : " + i2);
                return;
            }
            if (i2 != 3) {
                q.e("NetworkManager", "diagSignalChange propId : " + x(i) + " Signal UNKNOW : " + i2);
                return;
            }
            q.e("NetworkManager", "diagSignalChange propId : " + x(i) + " Signal ON : " + i2);
            q.removeCallbacksAndMessages(null);
            q.postDelayed(this.i, TimeUnit.MINUTES.toMillis(1L));
        }
    }

    private void l() {
        this.f1625a = new HashMap<>();
        ArrayList arrayList = new ArrayList();
        arrayList.add("172.16.5.101");
        arrayList.add("172.16.4.101");
        arrayList.add("172.16.3.101");
        arrayList.add("172.16.2.101");
        this.f1625a.put("TBOX", arrayList);
        this.f1625a.put("ADAS", new ArrayList());
        this.f1625a.put("HID_NETWORK_LEFT", new ArrayList());
        this.f1625a.put("HID_NETWORK_RIGHT", new ArrayList());
        this.f1625a.put("HUD_NET_WORK", new ArrayList());
        this.f1625a.put("UAV", new ArrayList());
        HashMap<String, String> map = new HashMap<>();
        this.f1627c = map;
        map.put("TBOX", "9F4313");
        this.f1627c.put("ADAS", "9F3F13");
        this.f1627c.put("HID_NETWORK_LEFT", "9F4013");
        this.f1627c.put("HID_NETWORK_RIGHT", "9F4113");
        this.f1627c.put("HUD_NET_WORK", "9F4213");
        this.f1627c.put("UAV", "9F4413");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o(String str) {
        y(str, this.f1625a.get(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q() {
        for (final String str : this.f1625a.keySet()) {
            this.e.execute(new Runnable() { // from class: ts.car.diag.manager.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1589c.o(str);
                }
            });
        }
    }

    private boolean s(String str) {
        try {
            Runtime runtime = Runtime.getRuntime();
            StringBuilder sb = new StringBuilder();
            sb.append("ping -c 1 -w 1 ");
            sb.append(str);
            return runtime.exec(sb.toString()).waitFor() == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        q.h("NetworkManager", "reConnectCar!!!");
        q.postDelayed(new Runnable() { // from class: ts.car.diag.manager.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f1590c.m();
            }
        }, 10000L);
    }

    private void w(String str, boolean z) {
        synchronized (this.f) {
            this.f1626b.put(str, Boolean.valueOf(z));
            int i = this.g + 1;
            this.g = i;
            if (i == this.f1625a.size()) {
                h();
            }
        }
    }

    private String x(int i) {
        return Integer.toHexString(i);
    }

    private void y(String str, List<String> list) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            if (s(list.get(i))) {
                z = true;
                break;
            }
            i++;
        }
        w(str, z);
    }

    public void h() {
        if (this.m != 3) {
            q.h("NetworkManager", "mAccSign is " + this.m + " != BODYWORK_POWER_LEVEL_ON is not dealData");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Boolean> entry : this.f1626b.entrySet()) {
            int i = 0;
            String str = this.f1627c.get(entry.getKey());
            if (Boolean.FALSE.equals(entry.getValue())) {
                i = 1;
                arrayList.add(str);
            }
            c.a.a.a.h hVar = this.h;
            if (hVar != null) {
                try {
                    hVar.e(Integer.parseInt(str, 16), i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.d = arrayList;
        q.removeCallbacksAndMessages(null);
        q.postDelayed(this.i, TimeUnit.SECONDS.toMillis(10L));
    }

    public List<String> j() {
        q.e("NetworkManager", "getDtcList : " + this.d);
        return this.d;
    }

    public void k(c.a.a.a.h hVar) {
        this.h = hVar;
        HandlerThread handlerThread = new HandlerThread("NetworkManager");
        p = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(p.getLooper());
        q = handler;
        handler.removeCallbacksAndMessages(null);
        q.postDelayed(this.i, TimeUnit.MINUTES.toMillis(1L));
        m();
    }

    public void m() {
        q.e("NetworkManager", "initCar start");
        Car carCreateCar = Car.createCar(this.j, this.n);
        this.k = carCreateCar;
        if (carCreateCar == null) {
            u();
        } else {
            carCreateCar.connect();
            q.e("NetworkManager", "initCar end");
        }
    }

    public void r() {
        q.removeCallbacksAndMessages(null);
    }

    public void t() {
        synchronized (this.f) {
            this.g = 0;
            this.e.execute(new Runnable() { // from class: ts.car.diag.manager.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1588c.q();
                }
            });
        }
    }

    public void v(c.a.a.a.h hVar) {
        this.h = hVar;
    }
}
