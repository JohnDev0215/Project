package com.byd.systemsettings;

import android.content.Context;
import android.view.View;
import com.byd.uereport.dialog.BydFullScreenDialog;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceTipsDialog extends BydFullScreenDialog {
    public DeviceTipsDialog(Context context) {
        super(context);
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        view.findViewById(com.byd.uereport.R.id.btn_ok_dialog_hint_1_btn).setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return com.byd.uereport.R.layout.dialog_layout_device_tips;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == com.byd.uereport.R.id.btn_ok_dialog_hint_1_btn) {
            dismiss();
        }
    }
}
