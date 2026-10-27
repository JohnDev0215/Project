package com.byd.trafficmonitor;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.tbox.BYDAutoTBoxDevice;
import android.hardware.bydauto.tbox.NetWorkQueryType;
import android.os.IBinder;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import c.a.d.o.c;
import c.a.d.p.a;
import c.a.d.p.d;
import c.a.d.s.e;
import c.a.d.s.g;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import com.google.gson.JsonSyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class TrafficMonitorService extends Service {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BYDAutoTBoxDevice f4406d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4404b = "TrafficMonitorService";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ScheduledExecutorService f4405c = Executors.newScheduledThreadPool(2);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4407e = true;

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f4408b;

        public a(boolean z) {
            this.f4408b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            TrafficMonitorService.this.k(this.f4408b);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TrafficMonitorService.this.r();
        }
    }

    public class c implements a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f4411a;

        public c(boolean z) {
            this.f4411a = z;
        }

        @Override // c.a.d.p.a.b
        public void a(String str) {
            BydLogHelper.logE("Get Flow Data errorMsg :" + str);
            if (this.f4411a) {
                TrafficMonitorService.this.f4406d.notifyTBoxTrafficResult(NetWorkQueryType.FAILED);
            }
        }

        @Override // c.a.d.p.a.b
        public void b(String str) {
            BydLogHelper.logD("TrafficMonitorService", "flow server result  :" + Base64.encodeToString(str.getBytes(), 0));
            c.b.b.d dVar = new c.b.b.d();
            try {
                c.a.d.o.c cVar = (c.a.d.o.c) dVar.i(str, c.a.d.o.c.class);
                if (!"0000".equals(cVar.f3596a) || TextUtils.isEmpty(cVar.f3597b)) {
                    BydLogHelper.logE("TrafficMonitorService", "flow server result  :" + str);
                    if (this.f4411a) {
                        TrafficMonitorService.this.f4406d.notifyTBoxCmdResult(2, NetWorkQueryType.FAILED.getValue());
                        return;
                    }
                    return;
                }
                c.a aVar = (c.a) dVar.i(cVar.f3597b, c.a.class);
                TrafficMonitorService.this.j(aVar.n);
                g.b("/vehicleconfig/traffic_data/goods_release_name.data", aVar.f3600c, false);
                String str2 = aVar.f3601d;
                BydLogHelper.logD("server iccid:" + c.a.a.g.a.b(str2));
                g.b("/vehicleconfig/traffic_data/iccid.data", str2, false);
                String str3 = aVar.f3604g;
                try {
                    String strSubstring = str3.substring(str3.indexOf(ListUtils.DEFAULT_JOIN_SEPARATOR) + 1);
                    String strA = g.a("/vehicleconfig/traffic_data/time_zone.data");
                    if (!TextUtils.isEmpty(strA) && !strA.equals(strSubstring)) {
                        TrafficMonitorService.this.l();
                    }
                    g.b("/vehicleconfig/traffic_data/time_zone.data", strSubstring, false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String str4 = aVar.j;
                String str5 = aVar.k;
                BydLogHelper.logD("TrafficMonitorService", "flow server data [ total :" + str4 + ",used:" + str5 + "]");
                int i2 = Integer.parseInt(str4);
                long jMax = Math.max((long) Integer.parseInt(str5), c.a.a.g.a.c(g.a("/vehicleconfig/traffic_data/flow_total_used.data"), 0L));
                g.b("/vehicleconfig/traffic_data/flow_total_used.data", String.valueOf(jMax), false);
                g.b("/vehicleconfig/traffic_data/flow_total.data", str4, false);
                if (TrafficMonitorService.this.f4407e) {
                    if (jMax >= i2) {
                        BydLogHelper.logD("flow has been used up, set back Net off.");
                        TrafficMonitorService.this.n();
                    } else {
                        TrafficMonitorService.this.o();
                    }
                }
                TrafficMonitorService.this.m();
                if (this.f4411a) {
                    TrafficMonitorService.this.f4406d.notifyTBoxCmdResult(2, NetWorkQueryType.SUCCESS.getValue());
                }
            } catch (JsonSyntaxException e3) {
                BydLogHelper.logE("TrafficMonitorService", "parse server result  :" + e3);
                if (this.f4411a) {
                    TrafficMonitorService.this.f4406d.notifyTBoxCmdResult(2, NetWorkQueryType.FAILED.getValue());
                }
            }
        }
    }

    public class d implements d.b {
        public d() {
        }

        @Override // c.a.d.p.d.b
        public void a(String str) {
            BydLogHelper.logE("sim info upload errorMsg :" + str);
        }

        @Override // c.a.d.p.d.b
        public void b(String str) {
            BydLogHelper.logD("sim info upload result :" + str);
        }
    }

    public static void p(Context context) {
        Intent intent = new Intent("com.byd.traffic_monitor.CHECK_DATA_PACKAGE");
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    public static void q(Context context) {
        Intent intent = new Intent("com.byd.traffic_monitor.SIM_INFO_UPLOAD");
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    public final void j(String str) {
        BydLogHelper.logD("cusTel:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        g.b("/vehicleconfig/traffic_data/cus_tel.data", String.valueOf(str), false);
        String string = new StringBuilder(str).reverse().toString();
        char cCharAt = string.charAt(0);
        if (cCharAt == '1') {
            l();
        }
        if (cCharAt == '2') {
            SystemProperties.set("persist.sys.idps_report_status", "1");
        } else {
            SystemProperties.set("persist.sys.idps_report_status", "0");
        }
        if (string.length() >= 2 && string.charAt(1) == '1') {
            Settings.Global.putInt(getContentResolver(), "persist.mobile.network.switch", 0);
        }
        if (string.length() >= 3) {
            char cCharAt2 = string.charAt(2);
            if (cCharAt2 == '1') {
                o();
                this.f4407e = false;
            } else if (cCharAt2 == '2') {
                n();
                this.f4407e = false;
            } else if (cCharAt2 == '3') {
                o();
                this.f4407e = false;
            }
        }
        if (string.length() >= 4) {
            if (string.charAt(3) == '1') {
                BydLogHelper.logD("setAPN3State:false");
                BYDAutoTBoxDevice.getInstance(getApplicationContext()).setAPN3State(false);
            } else {
                BydLogHelper.logD("setAPN3State:true");
                BYDAutoTBoxDevice.getInstance(getApplicationContext()).setAPN3State(true);
            }
        }
        if (string.length() >= 6) {
            char cCharAt3 = string.charAt(5);
            if (cCharAt3 == '1') {
                Settings.Global.putInt(getContentResolver(), "persist.mobile.isa.switch", 0);
            } else if (cCharAt3 == '3') {
                Settings.Global.putInt(getContentResolver(), "persist.mobile.isa.switch", 1);
            }
            if (cCharAt3 == '4') {
                BydLogHelper.logD("set sys browser persist 1");
                SystemProperties.set("persist.sys.brower_status", "1");
                Settings.Global.putInt(getContentResolver(), "persist.mobile.browser.switch", 0);
            } else {
                BydLogHelper.logD("set sys browser persist 0");
                SystemProperties.set("persist.sys.brower_status", "0");
                Settings.Global.putInt(getContentResolver(), "persist.mobile.browser.switch", 1);
            }
        }
    }

    public final void k(boolean z) {
        new c.a.d.p.a(getApplicationContext()).d(new c(z));
    }

    public final void l() {
        BydLogHelper.logD("------------------resetFlow--------------");
        long jC = c.a.a.g.a.c(g.a("/vehicleconfig/traffic_data/flow_local_used.data"), 0L);
        BydLogHelper.logD("C flow data :" + jC);
        BydLogHelper.logD("A flow data :" + c.a.a.g.a.c(g.a("/vehicleconfig/traffic_data/flow_total_used.data"), 0L));
        long jC2 = c.a.a.g.a.c(g.a("/vehicleconfig/traffic_data/flow_local_used_backup.data"), 0L);
        BydLogHelper.logD("localFlowKb:" + jC + ",localFlowBackupKb :" + jC2);
        StringBuilder sb = new StringBuilder();
        sb.append("B flow data :");
        sb.append(jC2);
        BydLogHelper.logD(sb.toString());
        g.b("/vehicleconfig/traffic_data/flow_total_used.data", String.valueOf(jC + jC2), false);
        BydLogHelper.logD("B flow new data :" + c.a.a.g.a.c(g.a("/vehicleconfig/traffic_data/flow_local_used_backup.data"), 0L));
        BydLogHelper.logD("A flow new data :" + c.a.a.g.a.c(g.a("/vehicleconfig/traffic_data/flow_total_used.data"), 0L));
        BydLogHelper.logD("-------------resetFlow  Finish--------------");
    }

    public final void m() {
        Intent intent = new Intent();
        intent.setAction("update_ui");
        b.p.a.a.b(getApplicationContext()).d(intent);
    }

    public final void n() {
        Settings.Global.putInt(getContentResolver(), "persist.mobile.network.traffic", 1);
    }

    public final void o() {
        Settings.Global.putInt(getContentResolver(), "persist.mobile.network.traffic", 0);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logD("-------------------------onCreate-----------------------");
        this.f4406d = BYDAutoTBoxDevice.getInstance(getApplicationContext());
        e.b(getApplicationContext());
        c.a.d.d.e();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ScheduledExecutorService scheduledExecutorService = this.f4405c;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        this.f4405c.shutdown();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        BydLogHelper.logD("-------------------------onStartCommand-----------------------");
        if (intent != null) {
            String action = intent.getAction();
            boolean z = intent.getIntExtra("isNotifyResult", 0) == 1;
            if ("com.byd.traffic_monitor.CHECK_DATA_PACKAGE".equals(action)) {
                BydLogHelper.logD("ACTION_MSG_CHECK_DATA_PACKAGE");
                this.f4405c.schedule(new a(z), 2L, TimeUnit.SECONDS);
            } else if ("com.byd.traffic_monitor.SIM_INFO_UPLOAD".equals(action)) {
                BydLogHelper.logD("ACTION_MSG_SIM_INFO_UPLOAD");
                this.f4405c.schedule(new b(), 2L, TimeUnit.SECONDS);
            }
        }
        return super.onStartCommand(intent, i2, i3);
    }

    public final void r() {
        new c.a.d.p.d(getApplicationContext()).c(new d());
    }
}
