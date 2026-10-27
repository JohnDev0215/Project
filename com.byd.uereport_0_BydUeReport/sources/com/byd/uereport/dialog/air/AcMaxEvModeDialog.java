package com.byd.uereport.dialog.air;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.widget.CountDownTimerTextView;
import g5.k;

/* JADX INFO: loaded from: classes2.dex */
public class AcMaxEvModeDialog extends g {

    public class a implements CountDownTimerTextView.c {
        public a() {
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void a() {
            AcMaxEvModeDialog.this.dismiss();
        }

        @Override // com.byd.uereport.widget.CountDownTimerTextView.c
        public void b() {
            AcMaxEvModeDialog.this.dismiss();
        }
    }

    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
            k.g(AcMaxEvModeDialog.this.mContext, "max_ev_mode", z7);
        }
    }

    public AcMaxEvModeDialog(Context context) {
        super(context);
    }

    @Override // b5.g
    public void initContent(View view) {
        CountDownTimerTextView countDownTimerTextView = (CountDownTimerTextView) view.findViewById(R.id.tv_yes);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_no_longer_prompt);
        countDownTimerTextView.setOnBtnCallBackListener(new a());
        checkBox.setOnCheckedChangeListener(new b());
        checkBox.setChecked(k.b(this.mContext, "max_ev_mode", false));
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_ac_max_ev_mode;
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
