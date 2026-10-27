package com.byd.uereport.dialog.air;

import android.view.View;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.widget.CountDownTimerTextView;

/* JADX INFO: loaded from: classes2.dex */
public class AcBtWindDialog extends g {

    public class a implements CountDownTimerTextView.c {
        public a() {
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void a() {
            AcBtWindDialog.this.dismiss();
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void b() {
            AcBtWindDialog.this.dismiss();
        }
    }

    @Override // b5.g
    public void initContent(View view) {
        ((CountDownTimerTextView) view.findViewById(R.id.tv_no)).setOnBtnCallBackListener(new a());
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_ac_bt_wind;
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
