package com.byd.smarttravel;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import b.j.f;
import c.a.b.c.o;
import c.a.d.e;
import c.a.d.i;
import c.a.d.j;
import c.a.d.k;
import com.bydux.api.R;
import com.bydux.api.common.views.window.BydActivity;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class TestActivity extends BydActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient o f1970b;

    public class a extends AbsBYDAutoVehicleHealthListener {
        public a() {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onDataChanged(a.b.c cVar) {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onError(int i, String str) {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onOilMaintainanceHintChanged(int i, int i2) throws Throwable {
            super.onOilMaintainanceHintChanged(i, i2);
            k.b("[预约用车]----[onOilMaintainanceHintChanged]" + i + "----" + i2);
        }
    }

    public class b extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1972a;

        public b(long j, long j2) {
            super(j, j2);
            this.f1972a = false;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() throws Throwable {
            TestActivity.this.getResources().getConfiguration();
            this.f1972a = BYDAutoChargingDevice.getInstance(TestActivity.this.getApplicationContext()).getBatteryManagementDeviceState() == 1;
            TestActivity.this.f1970b.b0.setText(((Object) TestActivity.this.f1970b.b0.getText()) + "\n[定时器]----onFinish----[超时/最后一次]：是否充电" + this.f1972a);
            StringBuilder sb = new StringBuilder();
            sb.append("[定时器]----onFinish----[超时/最后一次]：是否充电");
            sb.append(this.f1972a);
            k.b(sb.toString());
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) throws Throwable {
            k.b("[定时器]----onTick：" + j);
            TestActivity.this.f1970b.b0.setText(((Object) TestActivity.this.f1970b.b0.getText()) + "\n[定时器]----onTick：" + j);
            boolean z = BYDAutoChargingDevice.getInstance(TestActivity.this.getApplicationContext()).getBatteryManagementDeviceState() == 1;
            this.f1972a = z;
            if (z) {
                k.b("[定时器]----onTick----[结束]：正在充电");
                TestActivity.this.f1970b.b0.setText(((Object) TestActivity.this.f1970b.b0.getText()) + "\n[定时器]----onTick----[结束]：正在充电");
                cancel();
            }
        }
    }

    public class c extends AbsMessageCallback {
        public c() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAppointmentTime(int i) throws Throwable {
            super.onAppointmentTime(i);
            k.b("[MCU闹钟]----[3分钟后唤醒]----返回:" + i);
        }
    }

    public static void s(Context context) {
        Intent intent = new Intent("com.byd.smartcharge.plan_upload");
        intent.putExtra("plan_use_type", 1);
        intent.putExtra("plan_switch", 1);
        intent.putExtra("goout_switch", 1);
        intent.putExtra("time_start_hour", 2);
        intent.putExtra("time_start_min", 1);
        intent.putExtra("time_end_hour", 4);
        intent.putExtra("time_end_min", 3);
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

    public static void t(Context context) {
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

    public final void e() {
        Intent intent = new Intent("com.byd.smartcharge.plan_res");
        intent.putExtra("result", 1);
        sendBroadcast(intent);
    }

    public final void f() {
        Intent intent = new Intent("com.byd.smartcharge.plan_change_res");
        intent.putExtra("res", 1);
        sendBroadcast(intent);
    }

    public final void g() {
        BYDAutoChargingDevice.getInstance(this).setCarPlan(1, 0, 127, 127, 15, 0, 31, 63);
        BYDAutoChargingDevice.getInstance(this).setHEVChargePlanStartTime(127, 15, 0, 31, 63);
        BYDAutoChargingDevice.getInstance(this).setHEVChargePlanEndTime(127, 15, 0, 31, 63);
        this.f1970b.b0.setText("[预约用车]----[混动计划复位]：setCarPlan(1, 0, 127, 0x7F, 0xF, 0x0, 0x1F, 0x3F)\nsetHEVChargePlanStartTime(0x7F, 0xF, 0x0, 0x1F, 0x3F)\nsetHEVChargePlanEndTime(0x7F, 0xF, 0x0, 0x1F, 0x3F)");
    }

    public final void h() {
        BYDAutoChargingDevice.getInstance(this).setCarPlan(1, 2, 0, 127, 15, 0, 31, 63);
        BYDAutoChargingDevice.getInstance(this).setHEVChargePlanStartTime(21, 1, 1, 0, 0);
        BYDAutoChargingDevice.getInstance(this).setHEVChargePlanEndTime(127, 15, 0, 31, 63);
    }

    public final void i() {
        this.f1970b.b0.setText("[预约用车]----[立即充电]");
        BYDAutoChargingDevice.getInstance(this).setCarPlan(1, 2, 0, 127, 15, 0, 31, 63);
        BYDAutoStatisticDevice.getInstance(this).resetStatisticDate(1, 1, 1, 1);
        BYDAutoVehicleHealthDevice.getInstance(this).registerListener((AbsBYDAutoVehicleHealthListener) new a());
    }

    public final void j() {
        BYDAutoChargingDevice.getInstance(this).setCarPlan(1, 2, 1, 1, 1, 1, 1, 1);
        BYDAutoChargingDevice.getInstance(this).setHEVChargePlanStartTime(21, 11, 28, 16, 0);
        BYDAutoChargingDevice.getInstance(this).setHEVChargePlanEndTime(21, 11, 28, 18, 0);
        this.f1970b.b0.setText("[预约用车]----[混动计划]：setCarPlan(1, 2, 1, 0x1, 0x1, 0x1, 0x1, 0x1)\nsetHEVChargePlanStartTime(21, 11, 28, 16, 0)\nsetHEVChargePlanEndTime(21, 11, 28, 18, 0)");
    }

    public final void k() throws Throwable {
        byte[] bArrA = e.a(i.d(this, "KEY_PRICE_START_TIME"), i.d(this, "KEY_PRICE_END_TIME"));
        k.b("[预约用车]----[优惠电价]：" + Arrays.toString(bArrA));
        this.f1970b.b0.setText("[预约用车]----[优惠电价]：" + Arrays.toString(bArrA));
        BYDAutoChargingDevice.getInstance(this).setElectricityPriceLevel(bArrA);
    }

    public final void l() {
        BYDAutoChargingDevice.getInstance(this).setElectricityPriceLevel(e.a(0L, 30L));
    }

    public final void m() throws Throwable {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int i = calendar.get(5);
        int i2 = calendar.get(11);
        int i3 = calendar.get(12);
        StringBuilder sb = new StringBuilder();
        sb.append("[MCU闹钟]----[3分钟后唤醒]----setAppointmentTime:");
        sb.append(i);
        sb.append(":");
        sb.append(i2);
        sb.append(":");
        int i4 = i3 + 3;
        sb.append(i4);
        k.b(sb.toString());
        BYDAutoOtaDevice.getInstance(getApplicationContext()).setAppointmentTime(i, i2, i4, 1, 1, new c());
    }

    public final void n() {
        String str = ((((((("[关键配置]\n[隐私政策]--[SystemProperties]:" + j.a("persist.sys.vehicle_privacy_status", "1")) + "\n[CAN在线]--[HAS_0X034700]:" + BYDAutoBodyworkDevice.getInstance(this).hasMessage(151)) + "\n[预约用车]--[getCarPlanSupport]:" + BYDAutoStudy.getInstance(this).getCarPlanSupport()) + "\n[电源档位]--[getPowerLevel]:" + BYDAutoBodyworkDevice.getInstance(this).getPowerLevel()) + "\n[电池状态]--[1:充电|2:结束|9:等待]:" + BYDAutoChargingDevice.getInstance(this).getBatteryManagementDeviceState()) + "\n[服务运行]--[com.byd.smarttravel.ChargeService]:" + r(this, "com.byd.smarttravel.ChargeService")) + "\n[模式配置]--[KEY_MODE]:" + i.b(this, "KEY_MODE")) + "\n[充电开关]--[KEY_CHARGE_SWITCH]:" + i.b(this, "KEY_CHARGE_SWITCH");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i.d(this, "KEY_CHARGE_START_TIME"));
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(i.d(this, "KEY_CHARGE_END_TIME"));
        String str2 = str + "\n[充电开始时间]--[KEY_CHARGE_START_TIME]:" + i + ":" + i2 + ":" + i3 + "-" + i4 + ":" + i5;
        this.f1970b.b0.setText((((((((((((((((((((str2 + "\n[充电结束时间]--[KEY_CHARGE_END_TIME]:" + calendar2.get(1) + ":" + calendar2.get(2) + ":" + calendar2.get(5) + "-" + calendar2.get(11) + ":" + calendar2.get(12)) + "\n[充电周期]--[KEY_CHARGE_CYCLE]:" + i.f(this, "KEY_CHARGE_CYCLE")) + "\n[是否充满]--[KEY_CHARGE_FULL]:" + i.b(this, "KEY_CHARGE_FULL")) + "\n[是否次日]--[KEY_CHARGE_NEXTDAY]:" + i.b(this, "KEY_CHARGE_NEXTDAY")) + "\n[用车开关]--[KEY_USECAR_SWITCH]:" + i.b(this, "KEY_USECAR_SWITCH")) + "\n[用车时间]--[KEY_USECAR_START_TIME]:" + i.d(this, "KEY_USECAR_START_TIME")) + "\n[用车周期]--[KEY_USECAR_CYCLE]:" + i.f(this, "KEY_USECAR_CYCLE")) + "\n[用车电价开始]--[KEY_PRICE_START_TIME]:" + i.d(this, "KEY_PRICE_START_TIME")) + "\n[用车电价结束]--[KEY_PRICE_END_TIME]:" + i.d(this, "KEY_PRICE_END_TIME")) + "\n[时间信息]--[TimeZone]:" + TimeZone.getDefault().getID()) + "\n[一周排序]--[星期天序号]:7") + "\n[一周的第一天]--[星期几]:" + Calendar.getInstance().getFirstDayOfWeek()) + "\n[获取预约用车状态]--:" + BYDAutoChargingDevice.getInstance(this).getCarPlanState()) + "\n[获取预约用车上电状态]--:" + BYDAutoChargingDevice.getInstance(this).getCarPlanAccOnState()) + "\n[获取预约用车电池加热状态]--:" + BYDAutoChargingDevice.getInstance(this).getCarPlanBatteryHeatState()) + "\n[里程值]--:" + BYDAutoStatisticDevice.getInstance(this).getDrivingRangeAll()) + "\n[里程单位]--:" + BYDAutoStatisticDevice.getInstance(this).getDrivingRangeUnit()) + "\n[交流充电电流限值]--:" + BYDAutoStudy.getInstance(this).getSupportAcChargingLimit()) + "\n[制动液提醒]--:" + BYDAutoVehicleHealthDevice.getInstance(this).getOilMaintainanceHint(2)) + "\n[刀片提醒]--:" + BYDAutoVehicleHealthDevice.getInstance(this).getOilMaintainanceHint(3));
    }

    public final void o() {
        BYDAutoChargingDevice.getInstance(this).setEVChargePlanStartTime(11, 11, 11, 11, 11);
        BYDAutoChargingDevice.getInstance(this).setEVChargePlanEndTime(22, 1, 1, 0, 0);
        this.f1970b.b0.setText("[预约用车]----[纯电计划]：setEVChargePlanStartTime(11, 11, 11, 11, 11)\nsetEVChargePlanEndTime(22, 1, 1, 0, 0)\n");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws Throwable {
        switch (view.getId()) {
            case R.id.btn_charge_invalid /* 2131296334 */:
                BYDAutoChargingDevice.getInstance(getApplicationContext()).setChargePlanState(0);
                break;
            case R.id.btn_charge_start /* 2131296335 */:
                BYDAutoChargingDevice.getInstance(getApplicationContext()).setChargePlanState(2);
                break;
            case R.id.btn_clear /* 2131296336 */:
                this.f1970b.b0.setText("");
                BYDAutoChargingDevice.getInstance(this).setEVChargePlanStartTime(127, 15, 0, 31, 63);
                BYDAutoChargingDevice.getInstance(this).setEVChargePlanEndTime(127, 15, 0, 31, 63);
                break;
            case R.id.btn_cloud_1 /* 2131296337 */:
                e();
                break;
            case R.id.btn_cloud_2 /* 2131296338 */:
                f();
                break;
            case R.id.btn_cloud_3 /* 2131296339 */:
                s(this);
                break;
            case R.id.btn_cloud_4 /* 2131296340 */:
                t(this);
                break;
            case R.id.btn_cloud_5 /* 2131296341 */:
                sendBroadcast(new Intent("com.byd.smartcharge.charge_upload").putExtra("charge_mode", 1));
                break;
            case R.id.btn_cloud_6 /* 2131296342 */:
                sendBroadcast(new Intent("com.byd.smartcharge.get.plan"));
                break;
            case R.id.btn_cloud_7 /* 2131296343 */:
                sendBroadcast(new Intent("com.byd.smartcharge.app.version_upload").putExtra("app_version_code", 2));
                break;
            case R.id.btn_dm_charge_close /* 2131296344 */:
                g();
                break;
            case R.id.btn_dm_charge_now /* 2131296345 */:
                i();
                break;
            case R.id.btn_dm_charge_set /* 2131296346 */:
                j();
                break;
            case R.id.btn_dm_charge_setfull /* 2131296347 */:
                h();
                break;
            case R.id.btn_ele_price /* 2131296348 */:
                k();
                break;
            case R.id.btn_ele_price2 /* 2131296349 */:
                l();
                break;
            case R.id.btn_end /* 2131296350 */:
                BYDAutoChargingDevice.getInstance(getApplicationContext()).setChargePlanState(3);
                break;
            case R.id.btn_get /* 2131296351 */:
                n();
                break;
            case R.id.btn_mcu /* 2131296353 */:
                m();
                break;
            case R.id.btn_plan_change /* 2131296355 */:
                o();
                break;
            case R.id.btn_timer /* 2131296358 */:
                p();
                break;
            case R.id.btn_usecar_off /* 2131296359 */:
                BYDAutoChargingDevice.getInstance(this).setCarPlanState(2);
                break;
            case R.id.btn_usecar_on /* 2131296360 */:
                BYDAutoChargingDevice.getInstance(this).setCarPlanState(1);
                break;
            case R.id.btn_usecar_time /* 2131296361 */:
                q();
                break;
            case R.id.btn_wait /* 2131296362 */:
                BYDAutoChargingDevice.getInstance(getApplicationContext()).setChargePlanState(1);
                break;
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
        o oVar = (o) f.d(this, R.layout.activity_test);
        this.f1970b = oVar;
        oVar.Q.setOnClickListener(this);
        this.f1970b.z.setOnClickListener(this);
        this.f1970b.A.setOnClickListener(this);
        this.f1970b.Z.setOnClickListener(this);
        this.f1970b.P.setOnClickListener(this);
        this.f1970b.R.setOnClickListener(this);
        this.f1970b.S.setOnClickListener(this);
        this.f1970b.T.setOnClickListener(this);
        this.f1970b.C.setOnClickListener(this);
        this.f1970b.D.setOnClickListener(this);
        this.f1970b.E.setOnClickListener(this);
        this.f1970b.F.setOnClickListener(this);
        this.f1970b.G.setOnClickListener(this);
        this.f1970b.H.setOnClickListener(this);
        this.f1970b.I.setOnClickListener(this);
        this.f1970b.U.setOnClickListener(this);
        this.f1970b.V.setOnClickListener(this);
        this.f1970b.B.setOnClickListener(this);
        this.f1970b.W.setOnClickListener(this);
        this.f1970b.X.setOnClickListener(this);
        this.f1970b.Y.setOnClickListener(this);
        this.f1970b.N.setOnClickListener(this);
        this.f1970b.L.setOnClickListener(this);
        this.f1970b.M.setOnClickListener(this);
        this.f1970b.J.setOnClickListener(this);
        this.f1970b.K.setOnClickListener(this);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() throws Throwable {
        super.onDestroy();
        k.b("[智慧充电]----测试界面销毁");
    }

    public final void p() throws Throwable {
        BYDAutoChargingDevice.getInstance(this).setChargePlanState(0);
        new b(10000L, 100L).start();
        k.b("[定时器]----开始计时");
        this.f1970b.b0.setText(((Object) this.f1970b.b0.getText()) + "\n\n[定时器]----开始计时");
    }

    public final void q() throws Throwable {
        long jD = i.d(this, "KEY_USECAR_START_TIME");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jD);
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        if (i >= 100) {
            i %= 100;
        }
        BYDAutoChargingDevice.getInstance(this).setCarPlan(1, 2, 127, 23, 9, 19, 24, 30);
        String str = "[预约用车]----[设置时间]：" + i + "/" + (i2 + 1) + "/" + i3 + "-" + i4 + "/" + i5;
        k.b(str);
        this.f1970b.b0.setText(str);
    }

    public final boolean r(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(40);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
