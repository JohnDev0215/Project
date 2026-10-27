package com.byd.uereport.dialog.air;

import android.content.Context;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.view.View;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.widget.CountDownTimerTextView;
import g5.c;

/* JADX INFO: loaded from: classes2.dex */
public class AcQuickCleanDialog extends g {

    public class a implements CountDownTimerTextView.c {
        public a() {
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void a() {
            BYDAutoAcDevice.getInstance(AcQuickCleanDialog.this.mContext).confirmCleanAirState(0);
            AcQuickCleanDialog.this.dismiss();
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void b() {
            BYDAutoAcDevice.getInstance(AcQuickCleanDialog.this.mContext).confirmCleanAirState(0);
            AcQuickCleanDialog.this.dismiss();
        }
    }

    public AcQuickCleanDialog(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(View view) {
        BYDAutoAcDevice.getInstance(this.mContext).confirmCleanAirState(1);
        dismiss();
    }

    @Override // b5.g
    public void initContent(View view) {
        CountDownTimerTextView countDownTimerTextView = (CountDownTimerTextView) view.findViewById(R.id.tv_no);
        view.findViewById(R.id.tv_yes).setOnClickListener(new View.OnClickListener() { // from class: c5.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f2313d.f(view2);
            }
        });
        countDownTimerTextView.setOnBtnCallBackListener(new a());
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_ac_quick_clean_tip_right_rudder : R.layout.dialog_ac_quick_clean_tip;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = 0;
    }
}
