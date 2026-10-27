package com.byd.uereport.dialog.screencast;

import android.view.View;
import android.widget.TextView;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class AndroidAutoUnauthDialog extends BydFullScreenDialog {
    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        ((TextView) view.findViewById(R.id.tv_ok)).setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_android_auto_connect_tip;
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

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g, android.app.Dialog
    public void show() {
        super.show();
    }
}
