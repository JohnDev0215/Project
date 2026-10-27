package r4;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.CountDownTimer;
import com.byd.smarttravel.PlanBean;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: ServerUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: compiled from: ServerUtils.java */
    public class a extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7813a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f7814b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(long j8, long j9, Context context) {
            super(j8, j9);
            this.f7814b = context;
            this.f7813a = false;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() throws Throwable {
            this.f7813a = BYDAutoChargingDevice.getInstance(this.f7814b).getBatteryManagementDeviceState() == 1;
            Intent intent = new Intent("com.byd.smartcharge.plan_change_res");
            intent.putExtra("res", this.f7813a ? 1 : 2);
            this.f7814b.sendBroadcast(intent);
            BydLogHelper.logE("broadcast, com.byd.smartcharge.plan_change_res, onFinish, result = " + this.f7813a);
            f.b("broadcast, com.byd.smartcharge.plan_change_res, onFinish, result = " + this.f7813a);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j8) throws Throwable {
            boolean z7 = BYDAutoChargingDevice.getInstance(this.f7814b).getBatteryManagementDeviceState() == 1;
            this.f7813a = z7;
            if (z7) {
                Intent intent = new Intent("com.byd.smartcharge.plan_change_res");
                intent.putExtra("res", 1);
                this.f7814b.sendBroadcast(intent);
                f.b("broadcast, com.byd.smartcharge.plan_change_res, onTick, result = " + this.f7813a);
                BydLogHelper.logE("broadcast, com.byd.smartcharge.plan_change_res, onTick, result = " + this.f7813a);
                cancel();
            }
        }
    }

    public static PlanBean a(Context context) {
        PlanBean planBean = new PlanBean();
        try {
            planBean.f3007e = e.b(context, "KEY_MODE");
            planBean.f3008f = e.b(context, "KEY_CHARGE_SWITCH");
            planBean.f3009g = e.b(context, "KEY_USECAR_SWITCH");
            planBean.f3020r = TimeZone.getDefault().getID();
            planBean.f3010h = e.d(context, "KEY_CHARGE_START_TIME");
            planBean.f3011i = e.d(context, "KEY_CHARGE_END_TIME");
            planBean.f3012j = e.b(context, "KEY_CHARGE_FULL");
            planBean.f3013k = e.b(context, "KEY_CHARGE_NEXTDAY");
            planBean.f3014l = e.f(context, "KEY_CHARGE_CYCLE");
            planBean.f3015m = e.d(context, "KEY_USECAR_START_TIME");
            planBean.f3016n = e.f(context, "KEY_USECAR_CYCLE");
            planBean.f3017o = e.d(context, "KEY_PRICE_START_TIME");
            planBean.f3018p = e.d(context, "KEY_PRICE_END_TIME");
            planBean.f3019q = e.b(context, "KEY_PRICE_FULL");
            planBean.f3021s = e.b(context, "gun_state_tips");
            planBean.f3022t = e.b(context, "acc_off_tips");
        } catch (Exception e8) {
            BydLogHelper.logE(e8.getMessage());
        }
        return planBean;
    }

    public static void b(Context context, Intent intent) throws Throwable {
        int intExtra;
        int intExtra2;
        boolean z7;
        int i8;
        int i9;
        int i10;
        byte b8;
        int i11;
        if (!g5.c.f(context)) {
            f.b("[应用指令]----APN is Off");
            return;
        }
        if (g5.c.h()) {
            try {
                int intExtra3 = intent.getIntExtra("plan_switch", 2);
                int intExtra4 = intent.getIntExtra("goout_switch", 2);
                int intExtra5 = intent.getIntExtra("plan_use_type", 1);
                f.b("[云服务]----charge_switch：" + intExtra3);
                f.b("[云服务]----usecar_switch：" + intExtra4);
                f.b("[云服务]----plan_type：" + intExtra5);
                boolean zC = e.c(context, "KEY_MODE", true);
                boolean z8 = intExtra5 != 2;
                if (zC != z8) {
                    f.b("[云服务]----模式切换中");
                }
                e.g(context, "KEY_MODE", z8);
                e.g(context, "KEY_CHARGE_SWITCH", intExtra3 == 1);
                e.g(context, "KEY_USECAR_SWITCH", intExtra4 == 1);
                f.b("[云服务]----timeZone：" + intent.getStringExtra("time_zone"));
                intExtra = intent.getIntExtra("time_start_hour", 0);
                intExtra2 = intent.getIntExtra("time_start_min", 0);
                f.b("[云服务]----shour：" + intExtra);
                f.b("[云服务]----sminute：" + intExtra2);
                Calendar calendar = Calendar.getInstance();
                int i12 = calendar.get(1);
                int i13 = calendar.get(2);
                int i14 = calendar.get(5);
                calendar.set(i12, i13, i14, intExtra, intExtra2, 0);
                calendar.set(14, 0);
                if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                    f.b("[云服务]----明日计划");
                    calendar.add(5, 1);
                    z7 = true;
                } else {
                    z7 = false;
                }
                e.h(context, "KEY_CHARGE_START_TIME", calendar.getTimeInMillis());
                int intExtra6 = intent.getIntExtra("time_end_hour", -1);
                int intExtra7 = intent.getIntExtra("time_end_min", -1);
                f.b("[云服务]----ehour：" + intExtra6);
                f.b("[云服务]----eminute：" + intExtra7);
                if (intExtra6 == -1 || intExtra7 == -1) {
                    i8 = intExtra7;
                    i9 = intExtra6;
                    i10 = 14;
                    e.g(context, "KEY_CHARGE_FULL", true);
                    b8 = 0;
                    e.g(context, "KEY_CHARGE_NEXTDAY", false);
                } else {
                    e.g(context, "KEY_CHARGE_FULL", false);
                    Calendar calendar2 = Calendar.getInstance();
                    i8 = intExtra7;
                    i9 = intExtra6;
                    i10 = 14;
                    calendar2.set(i12, i13, i14, intExtra6, i8, 0);
                    calendar2.set(14, 0);
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTimeInMillis(calendar.getTimeInMillis());
                    int i15 = calendar3.get(11);
                    int i16 = calendar3.get(12);
                    Calendar calendar4 = Calendar.getInstance();
                    calendar4.setTimeInMillis(calendar2.getTimeInMillis());
                    if ((i15 * 60) + i16 > (calendar4.get(11) * 60) + calendar4.get(12)) {
                        calendar2.add(5, 1);
                        e.g(context, "KEY_CHARGE_NEXTDAY", true);
                    } else {
                        e.g(context, "KEY_CHARGE_NEXTDAY", false);
                    }
                    if (z7) {
                        calendar2.add(5, 1);
                    }
                    e.h(context, "KEY_CHARGE_END_TIME", calendar2.getTimeInMillis());
                    b8 = 0;
                }
                String strA = b.a(intent.getByteExtra("repeat_mode", b8));
                e.i(context, "KEY_CHARGE_CYCLE", strA);
                f.b("[云服务]----cycle：" + strA);
                int intExtra8 = intent.getIntExtra("time_goout_hour_min", 0);
                int intExtra9 = intent.getIntExtra("time_goout_min", 0);
                f.b("[云服务]----usecarhour：" + intExtra8);
                f.b("[云服务]----usecarminute：" + intExtra9);
                Calendar calendar5 = Calendar.getInstance();
                calendar5.set(i12, i13, i14, intExtra8, intExtra9, 0);
                calendar5.set(i10, 0);
                if (calendar5.getTimeInMillis() <= System.currentTimeMillis()) {
                    calendar5.add(5, 1);
                }
                e.h(context, "KEY_USECAR_START_TIME", calendar5.getTimeInMillis());
                String strA2 = b.a(intent.getByteExtra("goout_repeat_mode", (byte) 0));
                e.i(context, "KEY_USECAR_CYCLE", strA2);
                f.b("[云服务]----usecarCycle：" + strA2);
                int intExtra10 = intent.getIntExtra("time_eprice_start_hour", 0);
                int intExtra11 = intent.getIntExtra("time_eprice_start_min", 0);
                int intExtra12 = intent.getIntExtra("time_eprice_end_hour", 0);
                int intExtra13 = intent.getIntExtra("time_eprice_end_min", 0);
                f.b("[云服务]----pshour：" + intExtra10);
                f.b("[云服务]----psminute：" + intExtra11);
                f.b("[云服务]----pehour：" + intExtra12);
                f.b("[云服务]----peminute：" + intExtra13);
                if (intExtra10 > 23 || intExtra10 < 0) {
                    intExtra10 = 0;
                }
                if (intExtra12 > 23 || intExtra12 < 0) {
                    intExtra12 = 0;
                }
                if (intExtra11 > 59 || intExtra11 < 0) {
                    intExtra11 = 0;
                }
                if (intExtra13 > 59 || intExtra13 < 0) {
                    intExtra13 = 0;
                }
                int i17 = (intExtra10 * 60) + intExtra11;
                int i18 = (intExtra12 * 60) + intExtra13;
                if (i17 == i18) {
                    e.g(context, "KEY_PRICE_FULL", true);
                    e.h(context, "KEY_PRICE_START_TIME", 0L);
                    e.h(context, "KEY_PRICE_END_TIME", 0L);
                } else {
                    e.g(context, "KEY_PRICE_FULL", false);
                    e.h(context, "KEY_PRICE_START_TIME", i17);
                    e.h(context, "KEY_PRICE_END_TIME", i18);
                }
                c.t(context, zC != z8 ? "TYPE_ALARM_CLOUD_MODE" : "TYPE_ALARM_CLOUD");
                e.i(context, "KEY_TIME_ZONE", TimeZone.getDefault().getID());
            } catch (Exception e8) {
                BydLogHelper.logE(e8.getMessage());
            }
            if (intExtra2 % 5 == 0) {
                i11 = i8;
                if (i11 % 5 != 0) {
                }
                Intent intent2 = new Intent("com.byd.smartcharge.plan_res");
                intent2.putExtra("result", 1);
                context.sendBroadcast(intent2);
            }
            i11 = i8;
            String str = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss SSS", Locale.CHINA).format(new Date()) + "--savePlan shour:" + intExtra + ",sminute:" + intExtra2 + ",ehour:" + i9 + ",eminute:" + i11 + ",TimeZone:" + TimeZone.getDefault().getID();
            BydLogHelper.logE("smarttravel", str);
            e.i(context, "KEY_BUG_MSG_CLOUD", str);
            Intent intent22 = new Intent("com.byd.smartcharge.plan_res");
            intent22.putExtra("result", 1);
            context.sendBroadcast(intent22);
        }
    }

    public static void c(Context context, Intent intent) throws Throwable {
        if (g5.c.f(context) && g5.c.h()) {
            int intExtra = intent.getIntExtra("charge_cmd", 0);
            if (intExtra == 1) {
                try {
                    e.g(context, "KEY_CHARGE_GUN_TAG", true);
                    if (g5.c.h()) {
                        BYDAutoChargingDevice.getInstance(context).setChargePlanState(0);
                    } else {
                        BYDAutoChargingDevice.getInstance(context).setCarPlan(1, 1, 127, 127, 15, 0, 31, 63);
                    }
                    BydLogHelper.logE("broadcast, com.byd.smartcharge.plan_change_res, onFinish, cmd = " + intExtra);
                    f.b("broadcast, com.byd.smartcharge.plan_change_res, onFinish, cmd = " + intExtra);
                    boolean zC = e.c(context, "KEY_MODE", false);
                    if (b.c(context) == 0 && zC) {
                        e.g(context, "KEY_CHARGE_SWITCH", false);
                        context.sendBroadcast(new Intent("com.byd.smartcharge.ui_plan"));
                    }
                } catch (Exception e8) {
                    BydLogHelper.logE(e8.getMessage());
                }
            }
            new a(10250L, 500L, context).start();
        }
    }

    public static void d(Context context, PlanBean planBean) throws Throwable {
        if (planBean == null) {
            return;
        }
        try {
            f.b("[应用指令]----" + planBean.toString());
            e.g(context, "KEY_MODE", planBean.f3007e);
            e.g(context, "KEY_CHARGE_SWITCH", planBean.f3008f);
            e.g(context, "KEY_USECAR_SWITCH", planBean.f3009g);
            e.i(context, "KEY_TIME_ZONE", TimeZone.getDefault().getID());
            e.h(context, "KEY_CHARGE_START_TIME", planBean.f3010h);
            e.h(context, "KEY_CHARGE_END_TIME", planBean.f3011i);
            e.g(context, "KEY_CHARGE_FULL", planBean.f3012j);
            e.g(context, "KEY_CHARGE_NEXTDAY", planBean.f3013k);
            e.i(context, "KEY_CHARGE_CYCLE", planBean.f3014l);
            e.h(context, "KEY_USECAR_START_TIME", planBean.f3015m);
            e.i(context, "KEY_USECAR_CYCLE", planBean.f3016n);
            e.h(context, "KEY_PRICE_START_TIME", planBean.f3017o);
            e.h(context, "KEY_PRICE_END_TIME", planBean.f3018p);
            e.g(context, "KEY_PRICE_FULL", planBean.f3019q);
            e.g(context, "gun_state_tips", planBean.f3021s);
            e.g(context, "acc_off_tips", planBean.f3022t);
            c.t(context, planBean.f3006d);
        } catch (Exception e8) {
            BydLogHelper.logE(e8.getMessage());
        }
    }

    public static void e(Context context) {
        if (g5.c.f(context) && g5.c.h()) {
            boolean zB = e.b(context, "KEY_CHARGE_GUN_TAG");
            Intent intent = new Intent("com.byd.smartcharge.charge_upload");
            intent.putExtra("charge_mode", zB ? 1 : 0);
            context.sendBroadcast(intent);
        }
    }

    public static void f(Context context) throws Throwable {
        if (!g5.c.f(context)) {
            f.b("[应用指令]----APN is Off");
            return;
        }
        if (g5.c.h()) {
            Intent intent = new Intent("com.byd.smartcharge.plan_upload");
            boolean zB = e.b(context, "KEY_MODE");
            boolean zB2 = e.b(context, "KEY_CHARGE_SWITCH");
            boolean zB3 = e.b(context, "KEY_USECAR_SWITCH");
            intent.putExtra("plan_use_type", zB ? 1 : 2);
            intent.putExtra("plan_switch", zB2 ? 1 : 2);
            intent.putExtra("goout_switch", zB3 ? 1 : 2);
            long jD = e.d(context, "KEY_CHARGE_START_TIME");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(jD);
            intent.putExtra("time_start_hour", calendar.get(11));
            intent.putExtra("time_start_min", calendar.get(12));
            if (e.b(context, "KEY_CHARGE_FULL")) {
                intent.putExtra("time_end_hour", -1);
                intent.putExtra("time_end_min", -1);
            } else {
                long jD2 = e.d(context, "KEY_CHARGE_END_TIME");
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(jD2);
                intent.putExtra("time_end_hour", calendar2.get(11));
                intent.putExtra("time_end_min", calendar2.get(12));
            }
            intent.putExtra("repeat_mode", (byte) Integer.parseInt(e.f(context, "KEY_CHARGE_CYCLE").replace("|", ""), 2));
            long jD3 = e.d(context, "KEY_USECAR_START_TIME");
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTimeInMillis(jD3);
            intent.putExtra("time_goout_hour_min", calendar3.get(11));
            intent.putExtra("time_goout_min", calendar3.get(12));
            intent.putExtra("goout_repeat_mode", (byte) Integer.parseInt(e.f(context, "KEY_USECAR_CYCLE").replace("|", ""), 2));
            long jD4 = e.d(context, "KEY_PRICE_START_TIME");
            long jD5 = e.d(context, "KEY_PRICE_END_TIME");
            if (e.b(context, "KEY_PRICE_FULL")) {
                intent.putExtra("time_eprice_start_hour", -1);
                intent.putExtra("time_eprice_start_min", -1);
                intent.putExtra("time_eprice_end_hour", -1);
                intent.putExtra("time_eprice_end_min", -1);
            } else {
                int i8 = (int) jD4;
                intent.putExtra("time_eprice_start_hour", i8 / 60);
                intent.putExtra("time_eprice_start_min", i8 % 60);
                int i9 = (int) jD5;
                intent.putExtra("time_eprice_end_hour", i9 / 60);
                intent.putExtra("time_eprice_end_min", i9 % 60);
            }
            intent.putExtra("time_zone", TimeZone.getDefault().getID());
            context.sendBroadcast(intent);
            if (calendar.get(12) % 5 != 0) {
                String str = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss SSS", Locale.CHINA).format(new Date()) + "--uploadPlan startTime" + jD + ",TimeZone:" + TimeZone.getDefault().getID();
                BydLogHelper.logE("smarttravel", str);
                e.i(context, "KEY_BUG_MSG", str);
            }
        }
    }

    public static void g(Context context) {
        if (g5.c.f(context) && g5.c.h()) {
            Intent intent = new Intent("com.byd.smartcharge.plan_upload");
            intent.putExtra("plan_use_type", 0);
            intent.putExtra("plan_switch", 1);
            intent.putExtra("goout_switch", 1);
            intent.putExtra("time_start_hour", 0);
            intent.putExtra("time_start_min", 0);
            intent.putExtra("time_end_hour", 0);
            intent.putExtra("time_end_min", 0);
            intent.putExtra("repeat_mode", (byte) 0);
            intent.putExtra("time_goout_hour_min", 0);
            intent.putExtra("time_goout_min", 0);
            intent.putExtra("goout_repeat_mode", (byte) 0);
            intent.putExtra("time_eprice_start_hour", 0);
            intent.putExtra("time_eprice_start_min", 0);
            intent.putExtra("time_eprice_end_hour", 0);
            intent.putExtra("time_eprice_end_min", 0);
            intent.putExtra("time_zone", TimeZone.getDefault().getID());
            context.sendBroadcast(intent);
        }
    }

    public static void h(Context context) throws Throwable {
        if (g5.c.f(context) && g5.c.h()) {
            try {
                boolean z7 = false;
                boolean z8 = BYDAutoBodyworkDevice.getInstance(context).hasMessage(151) == 1;
                int carPlanSupport = BYDAutoStudy.getInstance(context).getCarPlanSupport();
                boolean z9 = carPlanSupport == 0;
                if (z9 && z8) {
                    z7 = true;
                }
                BydLogHelper.logW("smarttravel", "uploadVersion 0x347 isOnline=" + z8 + ", isConfig = " + carPlanSupport);
                f.b("uploadVersion 0x347 isOnline=" + z8 + ", isConfig = " + z9);
                Intent intent = new Intent("com.byd.smartcharge.app.version_upload");
                intent.putExtra("app_version_code", z7 ? 3 : 2);
                context.sendBroadcast(intent);
            } catch (Exception e8) {
                BydLogHelper.logE(e8.getMessage());
            }
        }
    }
}
