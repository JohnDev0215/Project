package com.byd.systemsettings;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class OffHotspotWarningDialog extends OffBtWarningDialog {
    private String mMsg;

    public OffHotspotWarningDialog(Context context) {
        super(context);
    }

    public String getMsg() {
        return this.mMsg;
    }

    @Override // com.byd.systemsettings.OffBtWarningDialog, com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        this.tvMsg.setText(this.mMsg);
    }

    public void setMsg(String str) {
        this.mMsg = str;
    }
}
