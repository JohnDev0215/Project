package com.byd.uereport.dialog;

import android.view.View;
import android.widget.Toast;
import b5.g;
import com.byd.uereport.R;

/* JADX INFO: loaded from: classes2.dex */
public class MyDialog extends g {
    @Override // b5.g
    public void initContent(View view) {
        view.findViewById(R.id.btn_test).setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_test;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_test) {
            Toast.makeText(this.mContext, "dismiss", 0).show();
            dismiss();
        }
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_300;
    }
}
