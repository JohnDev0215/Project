package com.byd.uereport.dialog.air;

import android.content.Context;
import android.view.View;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.widget.CountDownTimerTextView;

/* JADX INFO: loaded from: classes2.dex */
public class AcAutoCleanDialog extends g {

    public class a implements CountDownTimerTextView.c {
        public a() {
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void a() {
            AcAutoCleanDialog.this.dismiss();
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void b() {
            AcAutoCleanDialog.this.dismiss();
        }
    }

    public AcAutoCleanDialog(Context context) {
        super(context);
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
        return R.layout.dialog_ac_auto_clean_tips;
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
