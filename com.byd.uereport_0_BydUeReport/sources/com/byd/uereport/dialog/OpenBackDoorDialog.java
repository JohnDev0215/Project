package com.byd.uereport.dialog;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import g5.c;

/* JADX INFO: loaded from: classes2.dex */
public class OpenBackDoorDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CountDownTimer f3241d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3242e;

    public class a extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f3243a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(long j8, long j9, TextView textView) {
            super(j8, j9);
            this.f3243a = textView;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (OpenBackDoorDialog.this.isShowing()) {
                OpenBackDoorDialog.this.dismiss();
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j8) {
            if (OpenBackDoorDialog.this.isShowing()) {
                this.f3243a.setText(OpenBackDoorDialog.this.mContext.getString(R.string.text_button_cancel_backdoor) + " ( " + OpenBackDoorDialog.this.f3242e + "s ) ");
                OpenBackDoorDialog.this.f3242e = (int) (j8 / 1000);
            }
        }
    }

    public OpenBackDoorDialog(Context context) {
        super(context);
        this.f3242e = 30;
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        f();
        super.dismiss();
    }

    public int e() {
        return this.f3242e;
    }

    public final void f() {
        CountDownTimer countDownTimer = this.f3241d;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f3241d = null;
        }
    }

    public void g(int i8) {
        this.f3242e = i8;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_yes);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_no);
        this.f3241d = new a(this.f3242e * 1000, 1000L, textView2).start();
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_open_back_door_right_rudder : R.layout.dialog_open_back_door;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.tv_no) {
            dismiss();
        } else {
            if (id != R.id.tv_yes) {
                return;
            }
            BydLogHelper.logD("direct to open back door ..");
            BYDAutoBodyworkDevice.getInstance(this.mContext).settingLuggage(1);
            dismiss();
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }
}
