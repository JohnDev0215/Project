package com.byd.uereport.dialog.air;

import android.content.Context;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.view.View;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.widget.CountDownTimerTextView;
import g5.c;

/* JADX INFO: loaded from: classes2.dex */
public class AcAssistModeDialog extends g {

    public class a implements CountDownTimerTextView.c {
        public a() {
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void a() {
            AcAssistModeDialog.this.dismiss();
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void b() {
            AcAssistModeDialog.this.dismiss();
        }
    }

    public AcAssistModeDialog(Context context) {
        super(context);
    }

    @Override // b5.g
    public void initContent(View view) {
        CountDownTimerTextView countDownTimerTextView = (CountDownTimerTextView) view.findViewById(R.id.tv_no);
        view.findViewById(R.id.tv_yes).setOnClickListener(this);
        countDownTimerTextView.setOnBtnCallBackListener(new a());
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_ac_assist_mode_tips_right_rudder : R.layout.dialog_ac_assist_mode_tips;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.tv_yes) {
            return;
        }
        BYDAutoSettingDevice.getInstance(this.mContext).setACAutoAir(2);
        dismiss();
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = 0;
    }
}
