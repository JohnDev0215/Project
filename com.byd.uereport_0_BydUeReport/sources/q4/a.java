package q4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.os.RemoteException;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Calendar;
import java.util.TimeZone;
import r4.d;
import r4.e;
import r4.f;

/* JADX INFO: compiled from: ChargeHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f7413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f7414b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoChargingDevice f7415c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BYDAutoBodyworkDevice f7416d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7417e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public q4.b f7419g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f7418f = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f7420h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public BroadcastReceiver f7421i = new C0107a();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AbsBYDAutoChargingListener f7422j = new b();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AbsBYDAutoBodyworkListener f7423k = new c();

    /* JADX INFO: renamed from: q4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChargeHandler.java */
    public class C0107a extends BroadcastReceiver {
        public C0107a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws Throwable {
            String action = intent.getAction();
            f.b("[云控广播]----" + action);
            action.hashCode();
            switch (action) {
                case "com.byd.smartcharge.plan_alarm":
                    r4.c.t(a.this.f7414b, intent.getStringExtra("ALARM_FROM"));
                    break;
                case "com.byd.smartcharge.plan_change":
                    if (intent.getExtras() != null) {
                        f.b("[云服务]----[计划改变]----" + intent.getExtras().toString());
                    }
                    d.c(context, intent);
                    break;
                case "com.byd.smartcharge.ui_plan":
                    try {
                        if (a.this.f7419g != null) {
                            a.this.f7419g.a0(0, d.a(context));
                        }
                        break;
                    } catch (RemoteException e8) {
                        BydLogHelper.logE(e8.getMessage());
                        return;
                    }
                    break;
                case "android.intent.action.TIMEZONE_CHANGED":
                    f.b("[时区变化]----");
                    d.g(context);
                    a.this.j(context);
                    break;
                case "com.byd.smartcharge.plan":
                    if (intent.getExtras() != null) {
                        f.b("[云服务]----[计划同步]----" + intent.getExtras().toString());
                    }
                    d.b(context, intent);
                    try {
                        if (a.this.f7419g != null) {
                            a.this.f7419g.a0(0, d.a(context));
                        }
                        break;
                    } catch (RemoteException e9) {
                        BydLogHelper.logE(e9.getMessage());
                        return;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: ChargeHandler.java */
    public class b extends AbsBYDAutoChargingListener {
        public b() {
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingGunStateChanged(int i8) throws Throwable {
            super.onChargingGunStateChanged(i8);
            f.b("[智慧充电]----[onChargingGunStateChanged]：上个状态：" + a.this.f7417e + ",目前状态：" + i8);
            if (a.this.f7417e != i8 && (i8 == 1 || i8 == 3)) {
                a.this.f7417e = i8;
                boolean zB = e.b(a.this.f7414b, "KEY_MODE");
                boolean zB2 = e.b(a.this.f7414b, "KEY_CHARGE_SWITCH");
                boolean zB3 = e.b(a.this.f7414b, "gun_state_tips");
                boolean zC = e.c(a.this.f7414b, "KEY_CHARGE_GUN_TAG", false);
                int powerLevel = a.this.f7416d.getPowerLevel();
                f.b("[智慧充电]----[onChargingGunStateChanged]：isChargeMode=" + zB + ",isSwitch=" + zB2 + ",isGunTips=" + zB3 + ",isChargeNow=" + zC + ",level=" + powerLevel);
                if (!zC && zB && zB2 && zB3 && (powerLevel == 2 || powerLevel == 3)) {
                    if (a.this.f7420h) {
                        f.b("[智慧充电]----[onChargingGunStateChanged]：枪连接状态生效");
                        r4.c.t(a.this.f7414b, "TYPE_ALARM_GUN_SWITH_HINT");
                        return;
                    } else {
                        f.b("[智慧充电]----[onChargingGunStateChanged]：枪连接状态无效");
                        a.this.f7417e = 0;
                        a.this.f7420h = true;
                        return;
                    }
                }
            }
            a.this.f7417e = i8;
            if (i8 == 1 || i8 == 3) {
                a.this.o("TYPE_ALARM_GUN", false);
            }
        }
    }

    /* JADX INFO: compiled from: ChargeHandler.java */
    public class c extends AbsBYDAutoBodyworkListener {
        public c() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i8) throws Throwable {
            super.onPowerLevelChanged(i8);
            f.b("[电源档位]----值：" + i8);
            if (i8 == 2 || i8 == 3) {
                a.this.f7420h = true;
            }
            if (a.this.f7418f == i8 || !(i8 == 0 || i8 == 2 || i8 == 3)) {
                a.this.f7418f = i8;
                return;
            }
            a.this.f7418f = i8;
            a.this.o("TYPE_ALARM_POWER", i8 == 0);
            if (i8 == 2) {
                d.g(a.this.f7414b);
                d.h(a.this.f7414b);
                f3.f.a().c(a.this.f7414b, true);
            }
        }
    }

    public a(Context context) throws Throwable {
        this.f7414b = context.getApplicationContext();
        l();
    }

    public static a k(Context context) {
        if (f7413a == null) {
            synchronized (a.class) {
                if (f7413a == null) {
                    f7413a = new a(context);
                }
            }
        }
        return f7413a;
    }

    public final void j(Context context) throws Throwable {
        boolean z7;
        int i8;
        try {
            String strF = e.f(context, "KEY_TIME_ZONE");
            f.b("[时区变化]----旧时区：" + strF + "----新时区：" + TimeZone.getDefault().getID());
            long jD = e.d(context, "KEY_CHARGE_START_TIME");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(strF));
            calendar.setTimeInMillis(jD);
            int i9 = calendar.get(11);
            int i10 = calendar.get(12);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(11, i9);
            calendar2.set(12, i10);
            calendar2.set(13, 0);
            calendar2.set(14, 0);
            if (calendar2.getTimeInMillis() < System.currentTimeMillis()) {
                calendar2.add(5, 1);
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z8 = z7;
            e.h(context, "KEY_CHARGE_START_TIME", calendar2.getTimeInMillis());
            long jD2 = e.d(context, "KEY_CHARGE_END_TIME");
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTimeZone(TimeZone.getTimeZone(strF));
            calendar3.setTimeInMillis(jD2);
            int i11 = calendar3.get(11);
            int i12 = calendar3.get(12);
            Calendar calendar4 = Calendar.getInstance();
            calendar4.set(11, i11);
            calendar4.set(12, i12);
            calendar4.set(13, 0);
            calendar4.set(14, 0);
            if ((i9 * 60) + i10 > (i11 * 60) + i12) {
                i8 = 1;
                calendar4.add(5, 1);
            } else {
                i8 = 1;
            }
            if (z8) {
                calendar4.add(5, i8);
            }
            e.h(context, "KEY_CHARGE_END_TIME", calendar4.getTimeInMillis());
            long jD3 = e.d(context, "KEY_USECAR_START_TIME");
            Calendar calendar5 = Calendar.getInstance();
            calendar5.setTimeZone(TimeZone.getTimeZone(strF));
            calendar5.setTimeInMillis(jD3);
            int i13 = calendar5.get(11);
            int i14 = calendar5.get(12);
            Calendar calendar6 = Calendar.getInstance();
            calendar6.set(11, i13);
            calendar6.set(12, i14);
            calendar6.set(13, 0);
            calendar6.set(14, 0);
            if (calendar6.getTimeInMillis() < System.currentTimeMillis()) {
                calendar6.add(5, 1);
            }
            e.h(context, "KEY_USECAR_START_TIME", calendar6.getTimeInMillis());
            e.i(context, "KEY_TIME_ZONE", TimeZone.getDefault().getID());
            r4.c.t(context, "TYPE_ALARM_DIALOG");
        } catch (Exception e8) {
            e = e8;
        }
        try {
            try {
                q4.b bVar = this.f7419g;
                if (bVar != null) {
                    bVar.a0(0, d.a(context));
                }
            } catch (RemoteException e9) {
                BydLogHelper.logE(e9.getMessage());
            }
        } catch (Exception e10) {
            e = e10;
            BydLogHelper.logE(e.getMessage());
        }
    }

    public final void l() throws Throwable {
        f.b("[智慧充电]----[服务启动]:" + hashCode());
        try {
            BYDAutoChargingDevice bYDAutoChargingDevice = BYDAutoChargingDevice.getInstance(this.f7414b);
            this.f7415c = bYDAutoChargingDevice;
            bYDAutoChargingDevice.registerListener(this.f7422j);
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.f7414b);
            this.f7416d = bYDAutoBodyworkDevice;
            bYDAutoBodyworkDevice.registerListener(this.f7423k);
            f.b("[智慧充电]----[APN开关]:" + g5.c.f(this.f7414b));
            o("TYPE_ALARM_SERVICE", false);
            d.h(this.f7414b);
            d.e(this.f7414b);
        } catch (Exception e8) {
            BydLogHelper.logE(e8.getMessage());
        }
    }

    public void m() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.byd.smartcharge.plan");
        intentFilter.addAction("com.byd.smartcharge.plan_change");
        intentFilter.addAction("com.byd.smartcharge.plan_alarm");
        intentFilter.addAction("com.byd.smartcharge.ui_plan");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        this.f7414b.registerReceiver(this.f7421i, intentFilter);
    }

    public void n(q4.b bVar) {
        this.f7419g = bVar;
    }

    public final void o(String str, boolean z7) throws Throwable {
        boolean z8 = BYDAutoChargingDevice.getInstance(this.f7414b).getBatteryManagementDeviceState() == 1;
        f.b("[" + str + "]----充电状态：" + z8);
        if (!g5.c.h()) {
            if (!z8) {
                r4.c.t(this.f7414b, str);
                return;
            }
            f.b("[" + str + "]----HEV车况：检测到当前为充电状态，车辆正在充电中上下电不下发预约");
            r4.c.t(this.f7414b, "TYPE_ALARM_POWER_NOT_SEND");
            return;
        }
        if (!z8 || !z7) {
            r4.c.t(this.f7414b, str);
            return;
        }
        f.b("[" + str + "]----EV车况：检测到当前为充电状态，车辆正在充电中且下电不下发预约");
        r4.c.t(this.f7414b, "TYPE_ALARM_POWER_NOT_SEND");
    }

    public void p() {
        this.f7415c.unregisterListener(this.f7422j);
        this.f7416d.unregisterListener(this.f7423k);
        f.b("[服务存活]----[unRegister]----");
        this.f7414b.unregisterReceiver(this.f7421i);
    }
}
