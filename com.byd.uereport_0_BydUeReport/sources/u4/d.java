package u4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.hardware.bydauto.tbox.AbsBYDTBoxListener;
import android.hardware.bydauto.tbox.BYDAutoTBoxDevice;
import android.hardware.bydauto.tbox.NetWorkQueryType;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import com.byd.trafficmonitor.model.TrafficInfo;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: TrafficMonitorHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile d f8213a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f8216d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BYDAutoTBoxDevice f8217e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public x4.e f8218f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public v4.a f8219g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8221i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ScheduledExecutorService f8214b = Executors.newScheduledThreadPool(2);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f8215c = "GMT+0";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8220h = true;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f8222j = "com.byd.traffic_monitor.CHECK_DATA_PACKAGE";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final String f8223k = "com.byd.traffic_monitor.SIM_INFO_UPLOAD";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f8224l = "com.byd.traffic_monitor.CDR_UPLOAD";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f8225m = "com.byd.traffic_monitor.SIM_CHANGE";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public BroadcastReceiver f8226n = new c();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final AbsBYDTBoxListener f8227o = new g();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ContentObserver f8228p = new h(new Handler());

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ContentObserver f8229q = new j(new Handler());

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ContentObserver f8230r = new k(new Handler());

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public BroadcastReceiver f8231s = new a();

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class a extends BroadcastReceiver {

        /* JADX INFO: renamed from: u4.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TrafficMonitorHandler.java */
        public class RunnableC0129a implements Runnable {
            public RunnableC0129a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (d.this.f8221i) {
                    d.this.p();
                } else {
                    BydLogHelper.logD("current time is not correct, give up this calculate flow .");
                }
            }
        }

        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                BydLogHelper.logE(intent.getDataString() + ":new installed. calculate flow right now !");
                d.this.f8214b.execute(new RunnableC0129a());
            }
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.Q(true);
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class c extends BroadcastReceiver {
        public c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            d.this.r();
        }
    }

    /* JADX INFO: renamed from: u4.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class RunnableC0130d implements Runnable {
        public RunnableC0130d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.L(false);
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.M();
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.o();
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class g extends AbsBYDTBoxListener {
        public g() {
        }

        @Override // android.hardware.bydauto.tbox.AbsBYDTBoxListener, b.d
        public void isConnected(boolean z7) {
            super.isConnected(z7);
        }

        @Override // android.hardware.bydauto.tbox.AbsBYDTBoxListener
        public void onTBoxCmd2IVI(int i8) {
            super.onTBoxCmd2IVI(i8);
            BydLogHelper.logD("onTBoxCmd2IVI :" + i8);
            if (i8 == 1) {
                d.this.H();
                d.this.f8217e.notifyTBoxCmdResult(1, NetWorkQueryType.SUCCESS.getValue());
            } else if (i8 == 2) {
                d.this.L(true);
            } else {
                if (i8 != 3) {
                    return;
                }
                d.this.C();
            }
        }

        @Override // android.hardware.bydauto.tbox.AbsBYDTBoxListener
        public void onUpdateTrafficData() {
            super.onUpdateTrafficData();
            BydLogHelper.logD("onUpdateTrafficData");
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class h extends ContentObserver {
        public h(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z7) {
            super.onChange(z7);
            BydLogHelper.logD("network switch change.");
            d.this.t();
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean zIsConnectTbox = BYDAutoYunDevice.getInstance(d.this.f8216d).isConnectTbox();
            BydLogHelper.logD("isConnectTBox :" + zIsConnectTbox);
            if (!zIsConnectTbox) {
                BydLogHelper.logD("try 60 times every second to check tBox.");
                int i8 = 60;
                while (i8 > 0) {
                    BydLogHelper.logD("this times:" + i8);
                    i8 += -1;
                    try {
                        Thread.currentThread();
                        Thread.sleep(1000L);
                    } catch (InterruptedException e8) {
                        e8.printStackTrace();
                    }
                    if (BYDAutoYunDevice.getInstance(d.this.f8216d).isConnectTbox()) {
                        break;
                    }
                }
            }
            if (!BYDAutoYunDevice.getInstance(d.this.f8216d).isConnectTbox()) {
                BydLogHelper.logD("after 60 times check, tBox is not connect , return.");
                return;
            }
            String string = Settings.Global.getString(d.this.f8216d.getContentResolver(), "persist.mobile.network.traffic");
            String string2 = Settings.Global.getString(d.this.f8216d.getContentResolver(), "persist.mobile.network.switch");
            if (TextUtils.isEmpty(string)) {
                string = "0";
            }
            if (TextUtils.isEmpty(string2)) {
                string2 = "0";
            }
            BydLogHelper.logD("fore network switch:" + string2 + ",back network switch:" + string);
            if (string.equals("0") && string2.equals("0")) {
                BydLogHelper.logD("both network switch is on, open apn2 with zk interface.");
                d.this.f8217e.setMobileNetworkState(true);
            } else {
                BydLogHelper.logD("either fore or back network switch is off. close apn2 with zk interface.");
                d.this.f8217e.setMobileNetworkState(false);
            }
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class j extends ContentObserver {
        public j(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z7) {
            super.onChange(z7);
            String string = Settings.Global.getString(d.this.f8216d.getContentResolver(), "persist.mobile.isa.switch");
            int iC = x4.a.c(string, -1);
            d dVar = d.this;
            int iX = dVar.x(dVar.f8216d, "com.neusoft.na.ehp");
            BydLogHelper.logD("isaSwitchState:" + string + ",isaUid:" + iX);
            if (iC == -1 || iX == -1) {
                return;
            }
            if (iC == 0) {
                d.this.f8218f.b(iX);
                d.this.f8219g.f(iX, TrafficInfo.f3025d);
            } else if (iC == 1) {
                d.this.f8218f.a(iX);
                d.this.f8219g.f(iX, TrafficInfo.f3026e);
            }
        }
    }

    /* JADX INFO: compiled from: TrafficMonitorHandler.java */
    public class k extends ContentObserver {
        public k(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z7) {
            super.onChange(z7);
            int iC = x4.a.c(Settings.Global.getString(d.this.f8216d.getContentResolver(), "persist.mobile.browser.switch"), -1);
            int i8 = 0;
            if (iC == 0) {
                List<TrafficInfo> listD = d.this.f8219g.d();
                while (i8 < listD.size()) {
                    TrafficInfo trafficInfo = listD.get(i8);
                    String strL = trafficInfo.l();
                    d dVar = d.this;
                    int iX = dVar.x(dVar.f8216d, strL);
                    if (!u4.c.f8212c.contains(strL)) {
                        BydLogHelper.logE("enable network:" + strL);
                        d.this.f8218f.b(iX);
                        trafficInfo.o(TrafficInfo.f3025d);
                        d.this.f8219g.g(trafficInfo);
                    }
                    i8++;
                }
                return;
            }
            if (iC == 1) {
                List<TrafficInfo> listD2 = d.this.f8219g.d();
                while (i8 < listD2.size()) {
                    TrafficInfo trafficInfo2 = listD2.get(i8);
                    String strL2 = trafficInfo2.l();
                    d dVar2 = d.this;
                    int iX2 = dVar2.x(dVar2.f8216d, strL2);
                    if (!u4.c.f8212c.contains(strL2)) {
                        BydLogHelper.logE("disable network:" + strL2);
                        d.this.f8218f.a(iX2);
                        trafficInfo2.o(TrafficInfo.f3026e);
                        d.this.f8219g.g(trafficInfo2);
                    }
                    i8++;
                }
            }
        }
    }

    public d(Context context) {
        this.f8216d = context.getApplicationContext();
        z();
    }

    public static d w(Context context) {
        if (f8213a == null) {
            synchronized (v4.a.class) {
                if (f8213a == null) {
                    f8213a = new d(context);
                }
            }
        }
        return f8213a;
    }

    public final boolean A() {
        long jD = x4.a.d(x4.d.a("/vehicleconfig/traffic_data/last_update_time.data"));
        String strA = x4.d.a("/vehicleconfig/traffic_data/time_zone.data");
        long jC = !TextUtils.isEmpty(strA) ? x4.b.c(TimeZone.getTimeZone(strA)) : x4.b.c(TimeZone.getTimeZone("GMT+0"));
        BydLogHelper.logD("startTimeOfMonth:" + jC + ",lastUpdateTime:" + jD);
        return jC > jD;
    }

    public final boolean B() {
        String strB = u4.a.c(this.f8216d).b();
        String strA = x4.d.a("/vehicleconfig/traffic_data/iccid.data");
        if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strB) || strA.equals(strB) || "null".equals(strB)) {
            return false;
        }
        BydLogHelper.logD("iccid:" + x4.a.b(strB) + ",saveIccid:" + x4.a.b(strA));
        x4.d.b("/vehicleconfig/traffic_data/iccid.data", strB, false);
        return true;
    }

    public void C() {
        this.f8214b.execute(new b());
    }

    public final void D() {
        String strA = x4.d.a("/vehicleconfig/traffic_data/cus_tel.data");
        BydLogHelper.logD("cusTel:" + strA);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        String string = new StringBuilder(strA).reverse().toString();
        if (string.length() < 3 || string.charAt(2) == '0') {
            return;
        }
        this.f8220h = false;
    }

    public void E() {
        G();
        F();
        t();
        r();
        v();
        this.f8214b.scheduleAtFixedRate(new f(), 30L, 60L, TimeUnit.SECONDS);
        Context context = this.f8216d;
        if (context != null && context.getContentResolver() != null) {
            this.f8216d.getContentResolver().registerContentObserver(Settings.Global.getUriFor("persist.mobile.isa.switch"), false, this.f8229q);
            this.f8216d.getContentResolver().registerContentObserver(Settings.Global.getUriFor("persist.mobile.browser.switch"), false, this.f8230r);
            this.f8216d.getContentResolver().registerContentObserver(Settings.Global.getUriFor("persist.mobile.network.switch"), false, this.f8228p);
            this.f8216d.getContentResolver().registerContentObserver(Settings.Global.getUriFor("persist.mobile.network.traffic"), false, this.f8228p);
        }
        BYDAutoTBoxDevice bYDAutoTBoxDevice = this.f8217e;
        if (bYDAutoTBoxDevice != null) {
            bYDAutoTBoxDevice.registerListener(this.f8227o);
        }
    }

    public final void F() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.f8216d.registerReceiver(this.f8231s, intentFilter);
    }

    public final void G() {
        this.f8216d.registerReceiver(this.f8226n, new IntentFilter("android.intent.action.TIME_TICK"));
    }

    public final void H() {
        BydLogHelper.logD("------------------resetFlow--------------");
        long jE = x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_local_used.data"), 0L);
        BydLogHelper.logD("C flow data :" + jE);
        BydLogHelper.logD("A flow data :" + x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_total_used.data"), 0L));
        long jE2 = x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_local_used_backup.data"), 0L);
        BydLogHelper.logD("localFlowKb:" + jE + ",localFlowBackupKb :" + jE2);
        StringBuilder sb = new StringBuilder();
        sb.append("B flow data :");
        sb.append(jE2);
        BydLogHelper.logD(sb.toString());
        x4.d.b("/vehicleconfig/traffic_data/flow_total_used.data", String.valueOf(jE + jE2), false);
        BydLogHelper.logD("B flow new data :" + x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_local_used_backup.data"), 0L));
        BydLogHelper.logD("A flow new data :" + x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_total_used.data"), 0L));
        BydLogHelper.logD("-------------resetFlow  Finish--------------");
    }

    public final void I() {
        BydLogHelper.logD("---------------------restartQueryFlow-----------------------");
        K();
        L(false);
    }

    public final void J() {
        Settings.Global.putInt(this.f8216d.getContentResolver(), "persist.mobile.network.traffic", 1);
    }

    public final void K() {
        Settings.Global.putInt(this.f8216d.getContentResolver(), "persist.mobile.network.traffic", 0);
    }

    public final void L(boolean z7) {
        if (this.f8216d == null) {
            return;
        }
        Intent intent = new Intent("com.byd.traffic_monitor.CHECK_DATA_PACKAGE");
        intent.setPackage("com.byd.trafficmonitor");
        if (z7) {
            intent.putExtra("isNotifyResult", 1);
        }
        this.f8216d.startService(intent);
    }

    public final void M() {
        if (this.f8216d == null) {
            return;
        }
        Intent intent = new Intent("com.byd.traffic_monitor.SIM_INFO_UPLOAD");
        intent.setPackage("com.byd.trafficmonitor");
        intent.putExtra("isNotifyResult", 1);
        this.f8216d.startService(intent);
    }

    public void N() {
        Context context = this.f8216d;
        if (context != null && context.getContentResolver() != null) {
            this.f8216d.getContentResolver().unregisterContentObserver(this.f8229q);
            this.f8216d.getContentResolver().unregisterContentObserver(this.f8230r);
            this.f8216d.getContentResolver().unregisterContentObserver(this.f8228p);
        }
        this.f8216d.unregisterReceiver(this.f8226n);
        this.f8216d.unregisterReceiver(this.f8231s);
        BYDAutoTBoxDevice bYDAutoTBoxDevice = this.f8217e;
        if (bYDAutoTBoxDevice != null) {
            bYDAutoTBoxDevice.unregisterListener(this.f8227o);
        }
        this.f8214b.shutdown();
    }

    public final void O(List<TrafficInfo> list) {
        HashMap map = new HashMap();
        List<TrafficInfo> listD = v4.a.c(this.f8216d).d();
        for (int i8 = 0; i8 < listD.size(); i8++) {
            TrafficInfo trafficInfo = listD.get(i8);
            map.put(Integer.valueOf(trafficInfo.m()), trafficInfo);
        }
        for (int i9 = 0; i9 < list.size(); i9++) {
            TrafficInfo trafficInfo2 = list.get(i9);
            int iM = trafficInfo2.m();
            if (map.containsKey(Integer.valueOf(iM))) {
                TrafficInfo trafficInfo3 = (TrafficInfo) map.get(Integer.valueOf(iM));
                long j8 = trafficInfo3.j();
                long jK = trafficInfo3.k();
                if (j8 + jK > trafficInfo2.j() + trafficInfo2.k()) {
                    trafficInfo2.q(j8);
                    trafficInfo2.r(jK);
                }
                String strL = trafficInfo3.l();
                if (TextUtils.isEmpty(trafficInfo2.l())) {
                    trafficInfo2.s(strL);
                }
                String strG = trafficInfo3.g();
                if (TextUtils.isEmpty(trafficInfo2.g())) {
                    trafficInfo2.n(strG);
                }
                v4.a.c(this.f8216d).g(trafficInfo2);
            } else {
                BydLogHelper.logD("add database trafficInfo:" + trafficInfo2);
                v4.a.c(this.f8216d).a(trafficInfo2);
            }
        }
    }

    public void P(int i8, int i9) {
        this.f8219g.f(i8, i9);
    }

    public final void Q(boolean z7) {
        BydLogHelper.logD("------------uploadCDR---------");
        w4.a aVar = new w4.a();
        String string = Settings.Global.getString(this.f8216d.getContentResolver(), "persist.mobile.network.traffic");
        BydLogHelper.logD("Traffic State:" + string);
        String string2 = Settings.Global.getString(this.f8216d.getContentResolver(), "persist.mobile.network.switch");
        BydLogHelper.logD("Switch State:" + string2);
        if (string2 == null || string2.equals("0")) {
            aVar.f8743b = "1";
        } else {
            aVar.f8743b = "2";
        }
        if (string == null || string.equals("0")) {
            aVar.f8742a = "1";
        } else {
            aVar.f8742a = "2";
        }
        long jE = x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_total.data"), 0L);
        if (jE == -1) {
            jE = 0;
        }
        aVar.f8747f = x4.f.d(jE + "");
        aVar.f8745d = x4.f.d(x4.a.e(x4.d.a("/data/traffic_data/flow_today_used.data"), 0L) + "");
        long jE2 = x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_total_used.data"), 0L);
        long j8 = jE2 != -1 ? jE2 : 0L;
        aVar.f8746e = x4.f.d(j8 + "");
        aVar.f8744c = x4.f.d((jE - j8) + "");
        aVar.f8748g = x4.b.a();
        aVar.f8749h = x4.c.a(this.f8216d).b();
        String strR = new k6.d().r(aVar);
        int length = strR.length();
        BydLogHelper.logD("cdrStr :" + strR);
        this.f8217e.uploadCDR(length, strR);
        if (z7) {
            this.f8217e.notifyTBoxCmdResult(3, NetWorkQueryType.SUCCESS.getValue());
        }
    }

    public final void o() {
        s();
        BydLogHelper.logD("check local flow used every min .");
        if (B()) {
            BydLogHelper.logD("check sim card changed.");
            I();
            return;
        }
        D();
        if (!this.f8221i) {
            BydLogHelper.logD("current time is not correct, give up this calculate flow .");
            return;
        }
        long jP = p();
        long jE = x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_total.data"), -1L);
        BydLogHelper.logD("totalFlowKb:" + jE + ",realUsedFlowKb :" + jP);
        if (this.f8220h && jE >= 0 && jP >= jE) {
            BydLogHelper.logD("flow has been used up, set back Net off.");
            J();
        }
        q();
    }

    public final long p() {
        long j8;
        String strA = x4.d.a("/vehicleconfig/traffic_data/time_zone.data");
        TimeZone timeZone = !TextUtils.isEmpty(strA) ? TimeZone.getTimeZone(strA) : TimeZone.getTimeZone("GMT+0");
        BydLogHelper.logD("timeZone:" + timeZone.getID());
        long jC = x4.b.c(timeZone);
        int iD = x4.b.d(timeZone.getID(), "GMT+0");
        BydLogHelper.logD("calculate this month flow .........");
        O(u(x4.f.c(this.f8216d, jC)));
        List<TrafficInfo> listD = v4.a.c(this.f8216d).d();
        long j9 = 0;
        for (int i8 = 0; i8 < listD.size(); i8++) {
            TrafficInfo trafficInfo = listD.get(i8);
            j9 = j9 + trafficInfo.j() + trafficInfo.k();
        }
        long j10 = j9 / 1024;
        long jE = x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_local_used.data"), 0L);
        long jE2 = x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_total_used.data"), 0L);
        BydLogHelper.logD("usedFlowFromLastUpdate:" + j10 + ",localFlowKb:" + jE);
        if (j10 >= jE) {
            j8 = jE2 + (j10 - jE);
        } else {
            x4.d.b("/vehicleconfig/traffic_data/flow_local_used_backup.data", String.valueOf(jE + x4.a.e(x4.d.a("/vehicleconfig/traffic_data/flow_local_used_backup.data"), 0L)), false);
            j8 = jE2 + j10;
        }
        BydLogHelper.logD("totalUsedFlow:" + j8);
        x4.d.b("/vehicleconfig/traffic_data/flow_local_used.data", String.valueOf(j10), false);
        x4.d.b("/vehicleconfig/traffic_data/flow_total_used.data", String.valueOf(j8), false);
        BydLogHelper.logD("System.currentTimeMillis:" + System.currentTimeMillis() + ",diffTimeZoneRawOffset:" + iD);
        x4.d.b("/vehicleconfig/traffic_data/last_update_time.data", String.valueOf(System.currentTimeMillis() - ((long) iD)), false);
        return j8;
    }

    public final void q() {
        BydLogHelper.logD("calculate today flow .........");
        if (!this.f8221i) {
            BydLogHelper.logD("current time is not correct, give up the calculate today flow this time.");
            return;
        }
        long jB = x4.b.b();
        if (jB > x4.a.d(x4.d.a("/vehicleconfig/traffic_data/today_start_time.data"))) {
            BydLogHelper.logD("new day, clear day flow data .");
            x4.d.b("/data/traffic_data/flow_today_used.data", String.valueOf(0), false);
            x4.d.b("/vehicleconfig/traffic_data/today_start_time.data", String.valueOf(jB), false);
        }
        long j8 = 0;
        ArrayList<TrafficInfo> arrayListC = x4.f.c(this.f8216d, jB);
        for (int i8 = 0; i8 < arrayListC.size(); i8++) {
            TrafficInfo trafficInfo = arrayListC.get(i8);
            j8 = j8 + trafficInfo.j() + trafficInfo.k();
        }
        long j9 = j8 / 1024;
        BydLogHelper.logD("todayUsedFlow:" + j9);
        long jD = x4.a.d(x4.d.a("/data/traffic_data/flow_today_used.data"));
        if (jD >= j9) {
            j9 = jD;
        }
        x4.d.b("/data/traffic_data/flow_today_used.data", String.valueOf(j9), false);
    }

    public final void r() {
        if (this.f8221i) {
            return;
        }
        int i8 = Calendar.getInstance().get(1);
        BydLogHelper.logD("year:" + i8);
        if (i8 != 2017) {
            this.f8221i = true;
            s();
        }
    }

    public final void s() {
        if (this.f8221i && A()) {
            BydLogHelper.logD("this month ACC ON first time.");
            x4.d.b("/vehicleconfig/traffic_data/flow_total_used.data", String.valueOf(0), false);
            x4.d.b("/vehicleconfig/traffic_data/flow_local_used.data", String.valueOf(0), false);
            x4.d.b("/vehicleconfig/traffic_data/flow_local_used_backup.data", String.valueOf(0), false);
            this.f8219g.b();
            K();
        }
    }

    public void t() {
        this.f8214b.execute(new i());
    }

    public final ArrayList<TrafficInfo> u(ArrayList<TrafficInfo> arrayList) {
        ArrayList<TrafficInfo> arrayList2 = new ArrayList<>();
        if (x4.a.c(Settings.Global.getString(this.f8216d.getContentResolver(), "persist.mobile.browser.switch"), -1) == 1) {
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                TrafficInfo trafficInfo = arrayList.get(i8);
                String strL = trafficInfo.l();
                int iX = x(this.f8216d, strL);
                if (!TextUtils.isEmpty(strL) && !u4.c.f8212c.contains(strL) && trafficInfo.h() != TrafficInfo.f3026e) {
                    BydLogHelper.logE("disable network:" + strL);
                    this.f8218f.a(iX);
                    trafficInfo.o(TrafficInfo.f3026e);
                }
                arrayList2.add(trafficInfo);
            }
        } else {
            arrayList2.addAll(arrayList);
        }
        return arrayList2;
    }

    public void v() {
        ScheduledExecutorService scheduledExecutorService = this.f8214b;
        RunnableC0130d runnableC0130d = new RunnableC0130d();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        scheduledExecutorService.schedule(runnableC0130d, 60L, timeUnit);
        this.f8214b.schedule(new e(), 90L, timeUnit);
    }

    public final int x(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo == null) {
                return -1;
            }
            return applicationInfo.uid;
        } catch (PackageManager.NameNotFoundException e8) {
            e8.printStackTrace();
            return -1;
        }
    }

    public List<TrafficInfo> y() {
        return this.f8219g.d();
    }

    public final void z() {
        this.f8217e = BYDAutoTBoxDevice.getInstance(this.f8216d);
        this.f8218f = x4.e.c(this.f8216d);
        this.f8219g = v4.a.c(this.f8216d);
        x4.c.a(this.f8216d).c();
    }
}
