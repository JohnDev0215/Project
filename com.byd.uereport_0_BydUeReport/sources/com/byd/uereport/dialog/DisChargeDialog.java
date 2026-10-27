package com.byd.uereport.dialog;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import b5.g;
import com.byd.bt.model.BTDevice;
import com.byd.uereport.R;
import com.byd.uereport.VehicleService;
import com.byd.uereport.widget.BydSwitch;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import g5.k;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class DisChargeDialog extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f3175d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f3176e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BydSwitch f3177f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public BydSwitch f3178g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ViewGroup f3179h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ViewGroup f3180i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f3181j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f3182k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3183l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3184m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbsBYDAutoChargingListener f3185n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Handler f3186o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3187p;

    public class a implements CompoundButton.OnCheckedChangeListener {
        public a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
            g5.a.c("----swDisCharge----" + z7);
            BYDAutoChargingDevice.getInstance(DisChargeDialog.this.mContext).setDischargeToggle(z7 ? 1 : 2);
            DisChargeDialog.this.f3186o.removeMessages(4097);
            DisChargeDialog.this.f3186o.sendEmptyMessageDelayed(4097, BTDevice.CONNECTED_TIME_STANDARD);
        }
    }

    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
            g5.a.c("----swLowPower----" + z7);
            BYDAutoChargingDevice.getInstance(DisChargeDialog.this.mContext).setPorducePowerWhenLowPower(z7 ? 1 : 2);
            DisChargeDialog.this.f3186o.removeMessages(4098);
            DisChargeDialog.this.f3186o.sendEmptyMessageDelayed(4098, BTDevice.CONNECTED_TIME_STANDARD);
        }
    }

    public class c extends AbsBYDAutoChargingListener {

        public class a implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int f3191d;

            public a(int i8) {
                this.f3191d = i8;
            }

            @Override // java.lang.Runnable
            public void run() {
                BydSwitch bydSwitch = DisChargeDialog.this.f3177f;
                int i8 = this.f3191d;
                boolean z7 = true;
                if (i8 != 1 && i8 != 2) {
                    z7 = false;
                }
                bydSwitch.a(z7, false);
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int f3193d;

            public b(int i8) {
                this.f3193d = i8;
            }

            @Override // java.lang.Runnable
            public void run() {
                DisChargeDialog.this.f3178g.a(this.f3193d == 1, false);
            }
        }

        /* JADX INFO: renamed from: com.byd.uereport.dialog.DisChargeDialog$c$c, reason: collision with other inner class name */
        public class RunnableC0023c implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ long f3195d;

            public RunnableC0023c(long j8) {
                this.f3195d = j8;
            }

            @Override // java.lang.Runnable
            public void run() {
                StringBuilder sb;
                StringBuilder sb2;
                long j8 = this.f3195d;
                long j9 = ((j8 / 1000) / 60) / 60;
                long j10 = ((j8 / 1000) / 60) - (60 * j9);
                DisChargeDialog disChargeDialog = DisChargeDialog.this;
                if (j9 > 9) {
                    sb = new StringBuilder();
                    sb.append(j9);
                    sb.append("");
                } else {
                    sb = new StringBuilder();
                    sb.append("0");
                    sb.append(j9);
                }
                disChargeDialog.h(sb.toString());
                DisChargeDialog disChargeDialog2 = DisChargeDialog.this;
                if (j10 > 9) {
                    sb2 = new StringBuilder();
                    sb2.append(j10);
                    sb2.append("");
                } else {
                    sb2 = new StringBuilder();
                    sb2.append("0");
                    sb2.append(j10);
                }
                disChargeDialog2.i(sb2.toString());
            }
        }

        public c() {
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeTimeChanged(long j8) {
            super.onDischargeTimeChanged(j8);
            new Handler(Looper.getMainLooper()).post(new RunnableC0023c(j8));
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeToggleChanged(int i8) {
            super.onDischargeToggleChanged(i8);
            if (DisChargeDialog.this.f3186o.hasMessages(4097)) {
                return;
            }
            new Handler(Looper.getMainLooper()).post(new a(i8));
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onPorducePowerWhenLowPowerChanged(int i8) {
            super.onPorducePowerWhenLowPowerChanged(i8);
            if (DisChargeDialog.this.f3186o.hasMessages(4098)) {
                return;
            }
            if (i8 == 1 || i8 == 2) {
                new Handler(Looper.getMainLooper()).post(new b(i8));
            }
        }
    }

    public static class d extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<DisChargeDialog> f3197a;

        public d(DisChargeDialog disChargeDialog) {
            this.f3197a = new WeakReference<>(disChargeDialog);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.f3197a.get() == null) {
                return;
            }
            try {
                int i8 = message.what;
                boolean z7 = true;
                if (i8 == 4097) {
                    int dischargeToggle = BYDAutoChargingDevice.getInstance(this.f3197a.get().mContext).getDischargeToggle();
                    if (dischargeToggle != 1 && dischargeToggle != 2) {
                        z7 = false;
                    }
                    this.f3197a.get().f3177f.a(z7, false);
                } else if (i8 == 4098) {
                    if (BYDAutoChargingDevice.getInstance(this.f3197a.get().mContext).getPorducePowerWhenLowPower() != 1) {
                        z7 = false;
                    }
                    this.f3197a.get().f3178g.a(z7, false);
                }
            } catch (Exception e8) {
                g5.a.c(e8.getMessage());
            }
        }
    }

    public DisChargeDialog(Context context) {
        super(context);
        this.f3181j = "--";
        this.f3182k = "--";
        this.f3183l = true;
        this.f3184m = true;
        this.f3186o = null;
        this.f3187p = 200;
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Handler handler = this.f3186o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        BYDAutoChargingDevice.getInstance(this.mContext).unregisterListener(this.f3185n);
        super.dismiss();
    }

    public void f(boolean z7) {
        this.f3183l = z7;
        BydSwitch bydSwitch = this.f3177f;
        if (bydSwitch != null) {
            bydSwitch.a(z7, false);
        }
    }

    public void g(boolean z7) {
        this.f3184m = z7;
        BydSwitch bydSwitch = this.f3178g;
        if (bydSwitch != null) {
            bydSwitch.a(z7, false);
        }
    }

    public void h(String str) {
        this.f3181j = str;
        TextView textView = this.f3175d;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void i(String str) {
        this.f3182k = str;
        TextView textView = this.f3176e;
        if (textView != null) {
            textView.setText(str);
        }
    }

    @Override // b5.g
    public void initContent(View view) {
        this.f3186o = new d(this);
        this.f3177f = (BydSwitch) view.findViewById(R.id.sw_dis_charge);
        this.f3178g = (BydSwitch) view.findViewById(R.id.sw_low_power);
        this.f3175d = (TextView) view.findViewById(R.id.tv_time_h);
        this.f3176e = (TextView) view.findViewById(R.id.tv_time_min);
        this.f3179h = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.f3180i = (ViewGroup) view.findViewById(R.id.ll_dialog_content);
        this.f3179h.setAlpha(0.0f);
        this.f3180i.setAlpha(0.0f);
        this.f3180i.setScaleX(0.9f);
        this.f3180i.setScaleY(0.9f);
        view.findViewById(R.id.iv_cancel).setOnClickListener(this);
        view.findViewById(R.id.btn_set).setOnClickListener(this);
        this.f3177f.setOnCheckedChangeListener(new a());
        this.f3178g.setOnCheckedChangeListener(new b());
        h(this.f3181j);
        i(this.f3182k);
        int dischargeToggle = BYDAutoChargingDevice.getInstance(this.mContext).getDischargeToggle();
        this.f3183l = dischargeToggle == 1 || dischargeToggle == 2;
        this.f3184m = BYDAutoChargingDevice.getInstance(this.mContext).getPorducePowerWhenLowPower() == 1;
        f(this.f3183l);
        g(this.f3184m);
        BYDAutoChargingDevice bYDAutoChargingDevice = BYDAutoChargingDevice.getInstance(this.mContext);
        c cVar = new c();
        this.f3185n = cVar;
        bYDAutoChargingDevice.registerListener((AbsBYDAutoChargingListener) cVar);
    }

    @Override // b5.g
    public void initData() {
        StringBuilder sb;
        String str;
        this.f3181j = k.f(this.mContext, "set_hour", "0");
        this.f3182k = k.f(this.mContext, "set_min", "0");
        if (!"--".equals(this.f3181j) || !"--".equals(this.f3182k)) {
            k.k(this.mContext, "set_hour", "--");
            k.k(this.mContext, "set_min", "--");
            return;
        }
        long dischargeTime = (BYDAutoChargingDevice.getInstance(this.mContext).getDischargeTime() / 1000) / 60;
        long j8 = dischargeTime / 60;
        long j9 = dischargeTime % 60;
        if (j8 > 9) {
            sb = new StringBuilder();
            sb.append("");
        } else {
            sb = new StringBuilder();
            sb.append("0");
        }
        sb.append(j8);
        this.f3181j = sb.toString();
        if (j9 > 9) {
            str = "" + j9;
        } else {
            str = "0" + j9;
        }
        this.f3182k = str;
    }

    @Override // b5.g
    public int initLayoutRes() {
        return r4.a.a(R.layout.layout_dialog_discharge_layout);
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return true;
    }

    public void j() {
        h(this.f3181j);
        i(this.f3182k);
        int dischargeToggle = BYDAutoChargingDevice.getInstance(this.mContext).getDischargeToggle();
        this.f3183l = dischargeToggle == 1 || dischargeToggle == 2;
        this.f3184m = BYDAutoChargingDevice.getInstance(this.mContext).getPorducePowerWhenLowPower() == 1;
        f(this.f3183l);
        g(this.f3184m);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        StringBuilder sb;
        StringBuilder sb2;
        if (view.getId() == R.id.btn_set) {
            String string = this.f3175d.getText().toString();
            String string2 = this.f3176e.getText().toString();
            if ("--".equals(string) && "--".equals(string2)) {
                k.k(this.mContext, "set_hour", "00");
                k.k(this.mContext, "set_min", "00");
            } else if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                try {
                    int i8 = Integer.parseInt(string);
                    int i9 = Integer.parseInt(string2);
                    Context context = this.mContext;
                    if (i8 > 9) {
                        sb = new StringBuilder();
                        sb.append("");
                        sb.append(i8);
                    } else {
                        sb = new StringBuilder();
                        sb.append("0");
                        sb.append(i8);
                    }
                    k.k(context, "set_hour", sb.toString());
                    Context context2 = this.mContext;
                    if (i9 > 9) {
                        sb2 = new StringBuilder();
                        sb2.append("");
                        sb2.append(i9);
                    } else {
                        sb2 = new StringBuilder();
                        sb2.append("0");
                        sb2.append(i9);
                    }
                    k.k(context2, "set_min", sb2.toString());
                } catch (Exception e8) {
                    g5.a.c(e8.getMessage());
                }
            }
            Context context3 = this.mContext;
            context3.startService(new Intent(context3, (Class<?>) VehicleService.class).putExtra("uiShow", 15));
        }
        dismiss();
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // b5.g, android.app.Dialog
    public void show() {
        super.show();
        this.f3180i.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.f3187p).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        this.f3180i.animate().alpha(1.0f).setDuration(this.f3187p).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        this.f3179h.animate().alpha(1.0f).setDuration(this.f3187p).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }
}
