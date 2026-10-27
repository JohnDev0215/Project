package com.byd.uereport.dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import com.byd.uereport.R;

/* JADX INFO: loaded from: classes2.dex */
public class ChargePortCoverTipDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CountDownTimer f3168d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3169e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BroadcastReceiver f3170f;

    public class a extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f3171a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(long j8, long j9, TextView textView) {
            super(j8, j9);
            this.f3171a = textView;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (ChargePortCoverTipDialog.this.isShowing()) {
                BYDAutoEnergyDevice.getInstance(ChargePortCoverTipDialog.this.mContext).setConfirmChargePortCover(2);
                ChargePortCoverTipDialog.this.dismiss();
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j8) {
            if (ChargePortCoverTipDialog.this.isShowing()) {
                this.f3171a.setText(ChargePortCoverTipDialog.this.mContext.getString(R.string.button_dialog_charge_port_cover_confirm) + " ( " + ChargePortCoverTipDialog.this.f3169e + " s )");
                ChargePortCoverTipDialog.this.f3169e = (int) (j8 / 1000);
            }
        }
    }

    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g5.a.a("receiver avm foreground broadcast.");
            BYDAutoEnergyDevice.getInstance(ChargePortCoverTipDialog.this.mContext).setConfirmChargePortCover(1);
            ChargePortCoverTipDialog.this.dismiss();
        }
    }

    public ChargePortCoverTipDialog(Context context) {
        super(context);
        this.f3169e = 15;
        this.f3170f = new b();
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        f();
        a1.a.b(this.mContext).e(this.f3170f);
        super.dismiss();
    }

    public int e() {
        return this.f3169e;
    }

    public final void f() {
        CountDownTimer countDownTimer = this.f3168d;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f3168d = null;
        }
    }

    public void g(int i8) {
        this.f3169e = i8;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_ensure);
        View viewFindViewById = view.findViewById(R.id.tv_ensure);
        this.f3168d = new a(this.f3169e * 1000, 1000L, textView).start();
        viewFindViewById.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
        a1.a.b(this.mContext).c(this.f3170f, new IntentFilter("action_avm_foreground"));
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_charge_port_cover_tip;
    }

    @Override // b5.g
    public boolean isCanceledOnTouchOutside() {
        return false;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tv_ensure) {
            BYDAutoEnergyDevice.getInstance(this.mContext).setConfirmChargePortCover(1);
            dismiss();
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }
}
