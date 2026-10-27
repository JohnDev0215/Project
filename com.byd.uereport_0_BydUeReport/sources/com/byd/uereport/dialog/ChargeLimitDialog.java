package com.byd.uereport.dialog;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import com.byd.uereport.R;

/* JADX INFO: loaded from: classes2.dex */
public class ChargeLimitDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CountDownTimer f3164d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3165e;

    public class a extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f3166a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(long j8, long j9, TextView textView) {
            super(j8, j9);
            this.f3166a = textView;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (ChargeLimitDialog.this.isShowing()) {
                ChargeLimitDialog.this.dismiss();
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j8) {
            if (ChargeLimitDialog.this.isShowing()) {
                this.f3166a.setText(ChargeLimitDialog.this.mContext.getString(R.string.text_dialog_charge_limit_btn) + " ( " + ChargeLimitDialog.this.f3165e + "s )");
                ChargeLimitDialog.this.f3165e = (int) (j8 / 1000);
            }
        }
    }

    public ChargeLimitDialog(Context context) {
        super(context);
        this.f3165e = 5;
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        e();
        super.dismiss();
    }

    public final void e() {
        CountDownTimer countDownTimer = this.f3164d;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f3164d = null;
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.btn_ok);
        this.f3164d = new a(this.f3165e * 1000, 1000L, textView).start();
        textView.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_charge_limit;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() != R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }
}
