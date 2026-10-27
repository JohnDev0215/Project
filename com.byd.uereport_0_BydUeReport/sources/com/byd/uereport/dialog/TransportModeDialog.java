package com.byd.uereport.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import g5.c;

/* JADX INFO: loaded from: classes2.dex */
public class TransportModeDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3319d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CountDownTimer f3320e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3321f;

    public class a extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f3322a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(long j8, long j9, TextView textView) {
            super(j8, j9);
            this.f3322a = textView;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (TransportModeDialog.this.isShowing()) {
                TransportModeDialog.this.dismiss();
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j8) {
            if (TransportModeDialog.this.isShowing()) {
                this.f3322a.setText(TransportModeDialog.this.mContext.getString(R.string.transport_mode_ensure) + " ( " + TransportModeDialog.this.f3321f + "s )");
                TransportModeDialog.this.f3321f = (int) (j8 / 1000);
            }
        }
    }

    public TransportModeDialog(Context context) {
        super(context);
        this.f3321f = 10;
    }

    @SuppressLint({"StringFormatInvalid"})
    public static String f(Context context, int i8) {
        if (i8 == 0) {
            String string = context.getString(R.string.transport_mode_enter_failure_tip);
            String str = c.h() ? String.format(string, "30-35%") : String.format(string, "35-40%");
            BydLogHelper.logD("TransportModeDialog", str);
            return str;
        }
        if (i8 == 1) {
            return context.getString(R.string.transport_mode_start_tip);
        }
        if (i8 == 2) {
            return context.getString(R.string.transport_mode_exit_failure_tip);
        }
        if (i8 != 3) {
            return null;
        }
        return context.getString(R.string.transport_mode_uninstall_failure_tip);
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        h();
        super.dismiss();
    }

    public int e() {
        return this.f3321f;
    }

    public int g() {
        return this.f3319d;
    }

    public final void h() {
        CountDownTimer countDownTimer = this.f3320e;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f3320e = null;
        }
    }

    public void i(int i8) {
        this.f3321f = i8;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_ensure);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_content);
        View viewFindViewById = view.findViewById(R.id.tv_ensure);
        String strF = f(this.mContext, this.f3319d);
        if (!TextUtils.isEmpty(strF)) {
            textView2.setText(strF);
        }
        if (this.f3319d == 1) {
            this.f3320e = new a(this.f3321f * 1000, 1000L, textView).start();
        }
        viewFindViewById.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_transport_mode;
    }

    public void j(int i8) {
        this.f3319d = i8;
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
