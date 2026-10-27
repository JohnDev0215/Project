package com.byd.uereport.dialog;

import android.content.Context;
import android.view.View;
import com.byd.uereport.R;

/* JADX INFO: loaded from: classes2.dex */
public class WeakCloudSignalDialog extends BydFullScreenDialog {
    public WeakCloudSignalDialog(Context context) {
        super(context);
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        view.findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.layout_dialog_weak_cloud_signal;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_ok) {
            dismiss();
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }
}
