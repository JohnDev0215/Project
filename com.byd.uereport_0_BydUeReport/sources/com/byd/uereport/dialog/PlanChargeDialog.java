package com.byd.uereport.dialog;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import b5.g;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import g5.m;
import r4.e;
import r4.f;

/* JADX INFO: loaded from: classes2.dex */
public class PlanChargeDialog extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f3267d = 15;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ViewGroup f3268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ViewGroup f3269f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f3270g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextView f3271h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Button f3272i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f3273j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f3274k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f3275l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3276m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3277n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CountDownTimer f3278o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Button f3279p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3280q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f3281r;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class b implements DialogInterface.OnKeyListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i8, KeyEvent keyEvent) throws Throwable {
            f.b("[预约充电]----[提醒弹框]----[特殊按键]----" + i8 + "----" + keyEvent.getAction());
            if ((i8 != 4 && i8 != 3) || keyEvent.getAction() != 1) {
                return false;
            }
            if (PlanChargeDialog.this.f3278o != null) {
                PlanChargeDialog.this.f3278o.cancel();
                long unused = PlanChargeDialog.f3267d = 15L;
            }
            if (dialogInterface != null) {
                PlanChargeDialog.this.dismiss();
            }
            return true;
        }
    }

    public class c extends CountDownTimer {
        public c(long j8, long j9) {
            super(j8, j9);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            PlanChargeDialog.this.dismiss();
            long unused = PlanChargeDialog.f3267d = 15L;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j8) {
            long j9 = j8 / 1000;
            PlanChargeDialog.this.f3279p.setText(String.format(PlanChargeDialog.this.mContext.getResources().getString(R.string.smarttravel_keep_plan), Long.valueOf(j9)));
            long unused = PlanChargeDialog.f3267d = j9;
        }
    }

    public class d implements DialogInterface.OnDismissListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) throws Throwable {
            int powerLevel = BYDAutoBodyworkDevice.getInstance(PlanChargeDialog.this.getContext()).getPowerLevel();
            f.b("[预约充电]----[充电计划]----[下电弹框]----[熄屏]----isOTADialogFlag=" + PlanChargeDialog.this.f3277n + ", powerLevel=" + powerLevel);
            if (powerLevel == 0) {
                BYDAutoTestDevice.getInstance(PlanChargeDialog.this.getContext()).setOffScreenFlag(1, 0);
                if (PlanChargeDialog.this.f3277n) {
                    return;
                }
                BYDAutoOtaDevice.getInstance(PlanChargeDialog.this.getContext()).setBackLightState(2, 2);
            }
        }
    }

    public PlanChargeDialog(Context context) {
        super(context);
        this.f3277n = false;
        this.f3280q = false;
        this.f3281r = 200;
    }

    public int g() {
        return this.f3276m;
    }

    public final void h(int i8) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        this.mLayoutParams = attributes;
        attributes.type = i8 == 2 ? 2044 : 2001;
        getWindow().setAttributes(this.mLayoutParams);
    }

    public void i(boolean z7) {
        this.f3280q = z7;
    }

    @Override // b5.g
    public void initContent(View view) {
        this.f3268e = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.f3269f = (ViewGroup) view.findViewById(R.id.ll_dialog_content);
        this.f3270g = (TextView) view.findViewById(R.id.tv_gun_title);
        this.f3271h = (TextView) view.findViewById(R.id.tv_gun_desc);
        this.f3272i = (Button) view.findViewById(R.id.btn_plan_charge);
        this.f3279p = (Button) view.findViewById(R.id.btn_ok);
        this.f3268e.setAlpha(0.0f);
        this.f3269f.setAlpha(0.0f);
        this.f3269f.setScaleX(0.9f);
        this.f3269f.setScaleY(0.9f);
        this.f3268e.setOnClickListener(new a());
        view.findViewById(R.id.btn_ok).setOnClickListener(this);
        view.findViewById(R.id.btn_plan_charge).setOnClickListener(this);
        m();
        j(this.f3273j, this.f3274k, this.f3275l, this.f3276m);
        l(this.f3276m);
        setOnKeyListener(new b());
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.layout_dialog_plan_charge;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return true;
    }

    public void j(String str, String str2, String str3, int i8) {
        this.f3273j = str;
        this.f3274k = str2;
        this.f3275l = str3;
        this.f3276m = i8;
        TextView textView = this.f3270g;
        if (textView != null) {
            textView.setText(str);
        }
        TextView textView2 = this.f3271h;
        if (textView2 != null) {
            textView2.setText(str2);
        }
        Button button = this.f3272i;
        if (button != null) {
            button.setText(str3);
        }
    }

    public void k(boolean z7) {
        this.f3277n = z7;
    }

    public void l(int i8) {
        this.f3276m = i8;
        h(i8);
        if (i8 == 2) {
            setOnDismissListener(new d());
        }
    }

    public void m() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws Throwable {
        int id = view.getId();
        if (id == R.id.btn_ok) {
            try {
                m.d(getContext(), getContext().getResources().getString(R.string.smarttravel_keep_plan_charge));
                CountDownTimer countDownTimer = this.f3278o;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    f3267d = 15L;
                }
                dismiss();
                return;
            } catch (Exception e8) {
                BydLogHelper.logE(e8.getMessage());
                return;
            }
        }
        if (id != R.id.btn_plan_charge) {
            return;
        }
        try {
            int chargingGunState = BYDAutoChargingDevice.getInstance(getContext()).getChargingGunState();
            if (!(chargingGunState == 1 || chargingGunState == 3)) {
                m.d(getContext(), getContext().getResources().getString(R.string.smarttravel_cancel_plan_charge));
            }
            if (g5.c.h()) {
                BYDAutoChargingDevice.getInstance(getContext()).setChargePlanState(0);
            } else {
                BYDAutoChargingDevice.getInstance(getContext()).setCarPlan(1, 1, 127, 127, 15, 0, 31, 63);
            }
            e.g(getContext(), "KEY_CHARGE_GUN_TAG", true);
            r4.d.e(getContext());
            boolean zC = e.c(getContext(), "KEY_MODE", false);
            if (r4.b.c(getContext()) == 0 && zC) {
                e.g(getContext(), "KEY_CHARGE_SWITCH", false);
                getContext().sendBroadcast(new Intent("com.byd.smartcharge.ui_plan"));
            }
            f.b("[预约充电]----[本地闹钟]----[置空处理]----");
            Intent intent = new Intent("com.byd.smartcharge.plan_alarm");
            intent.putExtra("ALARM_FROM", "TYPE_ALARM_EMPTY");
            ((AlarmManager) getContext().getSystemService("alarm")).setExactAndAllowWhileIdle(0, System.currentTimeMillis(), PendingIntent.getBroadcast(getContext(), 0, intent, 134217728));
            BYDAutoTestDevice.getInstance(getContext()).setOffScreenFlag(1, 0);
            CountDownTimer countDownTimer2 = this.f3278o;
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
                f3267d = 15L;
            }
            dismiss();
        } catch (Exception e9) {
            BydLogHelper.logE(e9.getMessage());
        }
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // b5.g, android.app.Dialog
    public void show() {
        super.show();
        this.f3278o = new c(500 + ((this.f3280q ? f3267d : 15L) * 1000), 1000L).start();
        this.f3269f.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.f3281r).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        this.f3269f.animate().alpha(1.0f).setDuration(this.f3281r).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        this.f3268e.animate().alpha(1.0f).setDuration(this.f3281r).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }
}
