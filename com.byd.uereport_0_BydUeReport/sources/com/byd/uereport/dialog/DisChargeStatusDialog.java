package com.byd.uereport.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import b5.g;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DisChargeStatusDialog extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3198d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f3199e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3200f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f3201g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f3202h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3203i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3204j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ProgressBar f3205k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ProgressBar f3206l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TextView f3207m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public TextView f3208n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public TextView f3209o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TextView f3210p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ImageView f3211q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ViewGroup f3212r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ViewGroup f3213s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f3214t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3215u;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DisChargeStatusDialog.this.dismiss();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public DisChargeStatusDialog(Context context) {
        super(context);
        this.f3198d = getClass().getSimpleName();
        this.f3214t = "";
        this.f3215u = 200;
        this.f3199e = context;
    }

    public void c(int i8, int i9) {
        this.f3203i = i8;
        this.f3204j = i9;
        ProgressBar progressBar = this.f3205k;
        if (progressBar == null || this.f3207m == null || this.f3206l == null || this.f3208n == null) {
            return;
        }
        if (i8 < 0 || i8 > 100) {
            progressBar.setProgress(0);
            this.f3207m.setText("--%");
        } else {
            progressBar.setProgress(i8);
            this.f3207m.setText(i8 + "%");
        }
        if (i9 < 0 || i9 > 100) {
            this.f3206l.setProgress(0);
            this.f3208n.setText("--%");
            return;
        }
        this.f3206l.setProgress(i9);
        this.f3208n.setText(i9 + "%");
    }

    public void d(int i8) {
        this.f3200f = i8;
        TextView textView = this.f3210p;
        if (textView != null) {
            if (i8 == 12) {
                textView.setText(this.f3199e.getResources().getString(R.string.newenergy_discharge_mps_discharge_finished));
                this.f3211q.setImageDrawable(e0.a.d(this.f3199e, R.drawable.icon_light_public_green_cherk_center_48));
            } else if (i8 == 14) {
                if (BYDAutoChargingDevice.getInstance(this.f3199e).getDischargeOverReason() == 4) {
                    this.f3210p.setText(this.f3199e.getResources().getString(R.string.newenergy_discharge_mps_discharge_abnormal));
                } else {
                    this.f3210p.setText(this.f3199e.getResources().getString(R.string.newenergy_discharge_mps_charge_abnormal));
                }
                this.f3211q.setImageDrawable(e0.a.d(this.f3199e, R.drawable.icon_light_public_redx_center_48));
            }
        }
    }

    @SuppressLint({"StringFormatInvalid"})
    public void e(long j8, String str) {
        this.f3201g = j8;
        this.f3202h = str;
        try {
            TextView textView = this.f3209o;
            if (textView != null) {
                textView.setText(String.format(this.f3199e.getResources().getString(R.string.newenergy_discharge_mps_discharge_time_and_quantity), Long.valueOf(j8 / 60), Long.valueOf(j8 % 60), str));
            }
        } catch (Exception e8) {
            BydLogHelper.logE(e8.getMessage());
        }
    }

    public void f() {
        this.f3200f = BYDAutoChargingDevice.getInstance(this.f3199e).getBatteryManagementDeviceState();
        this.f3201g = BYDAutoChargingDevice.getInstance(this.f3199e).getDischargeTotalTime();
        double vtovDischargeQuantity = BYDAutoChargingDevice.getInstance(this.f3199e).getVtovDischargeQuantity();
        if (vtovDischargeQuantity < 0.0d || vtovDischargeQuantity > 100.10000000149012d) {
            this.f3202h = "--";
        } else {
            this.f3202h = String.format("%.1f", Double.valueOf(vtovDischargeQuantity));
        }
        this.f3203i = BYDAutoOtaDevice.getInstance(this.f3199e).getSocValue();
        this.f3204j = BYDAutoChargingDevice.getInstance(this.f3199e).getVtovChargeBatteryLevelNum();
        d(this.f3200f);
        e(this.f3201g, this.f3202h);
        c(this.f3203i, this.f3204j);
    }

    @Override // b5.g
    public void initContent(View view) {
        this.f3205k = (ProgressBar) view.findViewById(R.id.progressbar_own_car);
        this.f3206l = (ProgressBar) view.findViewById(R.id.progressbar_charge_car);
        this.f3207m = (TextView) view.findViewById(R.id.tv_progress_own_car);
        this.f3208n = (TextView) view.findViewById(R.id.tv_progress_charge_car);
        this.f3210p = (TextView) view.findViewById(R.id.tv_title);
        this.f3211q = (ImageView) view.findViewById(R.id.iv_title);
        this.f3209o = (TextView) view.findViewById(R.id.tv_message);
        this.f3212r = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.f3213s = (ViewGroup) view.findViewById(R.id.ll_dialog_content);
        this.f3212r.setAlpha(0.0f);
        this.f3213s.setAlpha(0.0f);
        this.f3213s.setScaleX(0.9f);
        this.f3213s.setScaleY(0.9f);
        this.f3200f = BYDAutoChargingDevice.getInstance(this.f3199e).getBatteryManagementDeviceState();
        this.f3201g = BYDAutoChargingDevice.getInstance(this.f3199e).getDischargeTotalTime();
        double vtovDischargeQuantity = BYDAutoChargingDevice.getInstance(this.f3199e).getVtovDischargeQuantity();
        if (vtovDischargeQuantity < 0.0d || vtovDischargeQuantity > 100.0999984741211d) {
            this.f3202h = "--";
        } else {
            this.f3202h = String.format("%.1f", Double.valueOf(vtovDischargeQuantity));
        }
        this.f3203i = BYDAutoOtaDevice.getInstance(this.f3199e).getSocValue();
        this.f3204j = BYDAutoChargingDevice.getInstance(this.f3199e).getVtovChargeBatteryLevelNum();
        d(this.f3200f);
        e(this.f3201g, this.f3202h);
        c(this.f3203i, this.f3204j);
        view.findViewById(R.id.btn_ok).setOnClickListener(new a());
        this.f3212r.setOnClickListener(new b());
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return r4.a.a(R.layout.dialog_discharge_status);
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // b5.g, android.app.Dialog
    public void show() {
        super.show();
        this.f3213s.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.f3215u).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        this.f3213s.animate().alpha(1.0f).setDuration(this.f3215u).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        this.f3212r.animate().alpha(1.0f).setDuration(this.f3215u).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }
}
